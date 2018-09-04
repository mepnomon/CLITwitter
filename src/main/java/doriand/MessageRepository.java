package doriand;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MessageRepository {

    List<Message> messages;

    public MessageRepository() {
        messages = new ArrayList<>();
    }

    public void save(Message aMessage) {
        messages.add(aMessage);
    }

    public List<Message> getMessagesForUser(User aUser) {
        return filterMessagesForUser(aUser);
    }

    private List<Message> filterMessagesForUser(User aUser){
        return messages.stream()
                .filter(message -> message.belongsTo(aUser))
                .collect(toList());
    }
}
