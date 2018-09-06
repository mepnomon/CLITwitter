package CLITwitter;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * A repository to store message objects
 * @author Dorian Dressler
 */
public class MessageRepository {

    List<Message> messages;

    /**
     * Constructs new Message Repository
     */
    public MessageRepository() {
        messages = new ArrayList<>();
    }

    /**
     * Saves a message to the repository.
     * @param aMessage the message
     */
    public void save(Message aMessage) {
        messages.add(aMessage);
    }

    /**
     * Returns Messages for specified user.
     * @param aUser the user
     * @return messages for a user.
     */
    public List<Message> getMessagesForUser(User aUser) {
        return filterMessagesForUser(aUser);
    }

    /*
        Filters messages for a user's messages.
     */
    private List<Message> filterMessagesForUser(User aUser){
        return messages.stream()
                .filter(message -> message.belongsTo(aUser))
                .collect(toList());
    }
}
