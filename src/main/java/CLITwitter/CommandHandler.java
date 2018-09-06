package CLITwitter;

import java.util.List;
import java.util.Optional;

/**
 * A command handler.
 * Extracts posts and messages from a user's message
 * @author Dorian Dressler
 */
public class CommandHandler {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private Clock aClock;
    private WallRepository wallRepository;
    private MessagePrinter messagePrinter;

    /**
     * Constructs a new CommandHandler
     * @param messageRepository
     * @param userRepository
     * @param aClock
     */
    public CommandHandler(MessageRepository messageRepository, UserRepository userRepository,
                          Clock aClock, WallRepository wallRepository,
                          MessagePrinter messagePrinter) {

        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.aClock = aClock;
        this.wallRepository = wallRepository;
        this.messagePrinter = messagePrinter;
    }


    /**
     * Handles a user's message.
     * @param message
     */
    public void handle(String message) {

        String name = getUserName(message);
        Optional<User> user = userRepository.getUserByName(name);

        User aUser = user.orElse(new User(name));
        String command = extractCommand(message);
        switch (command) {
            case "post":
                if (!user.isPresent()) {
                    userRepository.save(aUser);
                }
                saveMessageToRepo(aUser, message);
                break;
            case "following":
                followUser(aUser,getSecondaryUserName(message));
                break;
            case "wall":
                printWall(aUser);
                break;
            case "timeline":
                messagePrinter.printForTimeline(messageRepository.getMessagesForUser(aUser));
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /*
        Extracts a command from the user's message.
     */
    private String extractCommand(String message){

        if(message.contains("->")){
            return "post";
        }

        if(message.contains("follows")) {
            return "following";
        }

        if(message.contains("wall")){
            return "wall";
        }
        return "timeline";
    }

    /*
        Constructs a message and saves a message.
     */
    private void saveMessageToRepo(User aUser, String message){
        Message newMessage = new Message(aUser, getMessage(message), aClock.now());
        messageRepository.save(newMessage);
    }

    /*
        Extracts username from a message
     */
    private String getUserName(String message){
        String[] splitMessage;
        if(message.contains("->")) {
            splitMessage = message.split("->");
            return splitMessage[0].trim();
        }
        splitMessage = message.split(" ");
        return splitMessage[0].trim();
    }

    /*
        Extracts a username from a "follows" command
     */
    private String getSecondaryUserName(String message){
        String [] splitMessage;
        splitMessage = message.split(" ");
        return splitMessage[2].trim();
    }

    /*
        Extracts a user's message from message supplied to commandHandler.
     */
    private String getMessage(String message){
        String[] splitMessage = message.split("->");
        return splitMessage[1].trim();
    }

    /*
        get a user's wall
     */
    private Optional<Wall> getWallForUser(User aUser){
        return wallRepository.getWallForUser(aUser);
    }

    /*
        Follow a user.
     */
    private void followUser(User aUser, String following){
        Wall aWall;
        Optional<Wall> optionalWall = wallRepository.getWallForUser(aUser);

        if(!optionalWall.isPresent()){
            aWall = new Wall(aUser);
            wallRepository.addWall(aWall);
        } else {
            aWall = optionalWall.get();
        }

        Optional<User> userOptional = userRepository.getUserByName(following);
        User followedUser = userOptional.get();
        aWall.followUser(followedUser);
    }

    /*
        Prints a wall
     */
    private void printWall(User aUser){
        Optional<Wall> optionalWall = getWallForUser(aUser);
        if(getWallForUser(aUser).isPresent()){
            Wall aWall = optionalWall.get();
            List<User> userList = aWall.isFollowing();
            List<Message> messageList = messageRepository.getMessagesForUser(aUser);
            for (User user1 : userList) {
                messageList.addAll(messageRepository.getMessagesForUser(user1));
            }
            messagePrinter.printForWall(messageList);
        }
    }
}
