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

        if (!user.isPresent()) {
            userRepository.save(aUser);
        }

        // get a msg for a user if the user exists and the msg body is empty
        if(getMessage(message) == null){
            messageRepository.getMessagesForUser(aUser);
        } else {
            saveMessageToRepo(aUser, message);
        }
    }


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

        if(splitMessage.length < 2 ){
            return null;
        }
        return splitMessage[1].trim();
    }
}
