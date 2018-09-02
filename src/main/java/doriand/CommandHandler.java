package doriand;

import java.util.Optional;

public class CommandHandler {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private Clock aClock;


    /**
     * Constructs a new CommandHandler
     * @param messageRepository
     * @param userRepository
     * @param aClock
     */
    public CommandHandler(MessageRepository messageRepository, UserRepository userRepository, Clock aClock) {

        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.aClock = aClock;
    }


    /**
     * Handles a user's message.
     * @param message
     */
    public void handle(String message) {

        String name = getUserName(message);
        Optional<User> user = userRepository.getUserByName(name);

        User aUser = user.orElse(new User(name));

        // if new user, save it
        if (!user.isPresent()) {
            userRepository.save(aUser);
        }

        String command = extractCommand(message);
        switch (command) {
            case "post":
                saveMessageToRepo(aUser, message);
                break;
            case "following":
                System.out.println("not yet implemented");
                break;
            case "wall":
                System.out.println("not yet implemented");
                break;
            case "timeline":
                messageRepository.getMessagesForUser(aUser);
                break;
            default:
                System.out.println("Command missing");
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
        String[] splitMessage = message.split("->");
        return splitMessage[0].trim();
    }

    /*

     */
    private String getMessage(String message){
        String[] splitMessage = message.split("->");

        //if(splitMessage.length < 2 ){
        //    return null;
        //}
        return splitMessage[1].trim();
    }
}
