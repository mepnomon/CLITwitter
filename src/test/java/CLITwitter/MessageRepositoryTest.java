package CLITwitter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class MessageRepositoryTest {

    MessageRepository messageRepository;

    @Before
    public void setUp() throws Exception {
        messageRepository = new MessageRepository();
    }

    @Test
    public void save_and_get_a_message_for_a_user() {

        LocalDateTime currentTime = LocalDateTime.now();
        User aUser = new User("Alice");
        String message = "I love the weather today.";
        Message aMessage = new Message(aUser, message, currentTime);
        List<Message> expectedMessage = new ArrayList<Message>() {{add(aMessage);}};
        messageRepository.save(aMessage);
        List<Message> returnMessages= messageRepository.getMessagesForUser(aUser);
        Assert.assertEquals(expectedMessage, returnMessages);
    }

    @Test
    public void save_and_get_messages_for_a_user(){

        LocalDateTime currentTime = LocalDateTime.now();
        List<Message> expectedMessage = new ArrayList<>();
        Message aMessage;
        User aUserAlice = new User("Alice");
        User aUserBob = new User("Bob");
        String messageText_1 = "I love the weather today.";
        String messageText_2 = "Damn! We Lost";
        String messageText_3 = "I'm Alice Guybrush Threepwood, mighty pirate.";

        aMessage = new Message(aUserAlice, messageText_1, currentTime);
        messageRepository.save(aMessage);
        expectedMessage.add(aMessage);
        aMessage = new Message(aUserBob, messageText_2, currentTime);
        messageRepository.save(aMessage);
        aMessage = new Message(aUserAlice,messageText_3,currentTime);
        messageRepository.save(aMessage);
        expectedMessage.add(aMessage);
        List<Message> returnMessages = messageRepository.getMessagesForUser(aUserAlice);
        Assert.assertEquals(expectedMessage, returnMessages);
    }
}