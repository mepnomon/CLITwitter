package doriand;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Unit test for simple App.
 */
public class CommandHandlerTest
{
    Clock aClock;
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
}
