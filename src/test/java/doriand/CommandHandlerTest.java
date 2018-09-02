package doriand;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for the CommandHandler.
 */
public class CommandHandlerTest {

    private Clock aClock;
    private MessageRepository messageRepository;
    private CommandHandler commandHandler;
    private UserRepository userRepository;
    private static final String MESSAGE = "Alice -> I love the weather today";


    @Before
    public void setUp() throws Exception {

        aClock = mock(Clock.class);
        messageRepository = mock(MessageRepository.class);
        userRepository = mock(UserRepository.class);
        commandHandler = new CommandHandler(messageRepository, userRepository, aClock);
    }

    @Test
    public void post_message_to_wall() {

        LocalDateTime currentTime = LocalDateTime.now();
        when(aClock.now()).thenReturn(currentTime);
        commandHandler.handle(MESSAGE);
        User aUser = new User("Alice");
        Message aMessage = new Message(aUser,"I love the weather today",currentTime);
        verify(messageRepository).save(aMessage);
    }

    @Test
    public void post_message_with_new_user() {

        when(userRepository.getUserByName("Alice")).thenReturn(Optional.empty());
        commandHandler.handle(MESSAGE);
        User alice = new User("Alice");
        verify(userRepository).save(alice);
    }

    @Test
    public void read_a_message_for_user(){

        LocalDateTime currentTime = LocalDateTime.now();
        User aUser = new User("Alice");

        List<Message> returnMessageList = new ArrayList<Message>() {{add(
                new Message(aUser, "I love the weather today",currentTime));}};

        when(userRepository.getUserByName("Alice")).thenReturn(Optional.of(aUser));
        when(messageRepository.getMessagesForUser(aUser)).thenReturn(returnMessageList);
        commandHandler.handle("Alice");
        verify(messageRepository).getMessagesForUser(aUser);
    }

    @Test
    public void read_two_messages_for_user(){

        User aUser = new User("Bob");
        LocalDateTime currentTime = LocalDateTime.now();
        List<Message> returnMessageList = new ArrayList<Message>() {
            {add(new Message(aUser,"Good game though.", currentTime));}
            {add(new Message(aUser,"Damn! We lost!", currentTime));}
        };
        when(userRepository.getUserByName("Bob")).thenReturn(Optional.of(aUser));
        when(messageRepository.getMessagesForUser(aUser)).thenReturn(returnMessageList);
        commandHandler.handle("Bob");
        verify(messageRepository).getMessagesForUser(aUser);
    }

    @Test
    public void follow_users(){

        commandHandler.handle("Charlie wall");
        Assert.fail("not yet implemented");
    }
}
