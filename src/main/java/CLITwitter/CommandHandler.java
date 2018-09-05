package CLITwitter;

import java.util.Optional;

public class CommandHandler {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private Clock aClock;
    private WallRepository wallRepository;

    /**
     * Constructs a new CommandHandler
     * @param messageRepository
     * @param userRepository
     * @param aClock
     */
    public CommandHandler(MessageRepository messageRepository, UserRepository userRepository, Clock aClock, WallRepository wallRepository) {

        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.aClock = aClock;
        this.wallRepository = wallRepository;
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
                //move into posting
                if (!user.isPresent()) {
                    userRepository.save(aUser);
                }
                saveMessageToRepo(aUser, message);
                break;
            case "following":
                addUserToFollow(aUser,getSecondaryUserName(message));
                break;
            case "wall":
                Wall aWall = getWallForUser(aUser);
                aWall.getUsersFollowed();
                //return messages for users followed
                break;
            case "timeline":
                messageRepository.getMessagesForUser(aUser);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    /*

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
        // get message for a user
        return "timeline";
    }

    /*

     */
    private void saveMessageToRepo(User aUser, String message){
        Message newMessage = new Message(aUser, getMessage(message), aClock.now());
        messageRepository.save(newMessage);
    }

    /*

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

    private String getSecondaryUserName(String message){
        String [] splitMessage;
        splitMessage = message.split(" ");
        return splitMessage[2].trim();
    }

    /*

     */
    private String getMessage(String message){
        String[] splitMessage = message.split("->");
        return splitMessage[1].trim();
    }

    private Wall getWallForUser(User aUser){
        return wallRepository.getWallForUser(aUser);
    }

    private void addUserToFollow(User aUser, String following){

        Wall aWall = wallRepository.getWallForUser(aUser);
        Optional<User> userOptional = userRepository.getUserByName(following);
        User followedUser = userOptional.get();
        aWall.addUsersToFollow(followedUser);
    }
}
