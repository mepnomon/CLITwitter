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
        return splitMessage[1].trim();
    }
}
