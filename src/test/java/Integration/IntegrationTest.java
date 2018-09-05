package Integration;

import CLITwitter.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntegrationTest {


    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private Clock aClock;
    private ConsoleHandler consoleHandler;
    private WallRepository wallRepository;
    private MessagePrinter messagePrinter;
    private CommandHandler commandHandler;

    @Before
    public void setUp(){
        messageRepository = new MessageRepository();
        userRepository = new UserRepository();
        aClock = mock(Clock.class);
        consoleHandler = mock(ConsoleHandler.class);
        wallRepository = new WallRepository();
        messagePrinter = new MessagePrinter(aClock,consoleHandler);
        commandHandler = new CommandHandler(messageRepository,userRepository,aClock,
                wallRepository,messagePrinter);
    }

    @Test
    public void test_post_message() {

        LocalDateTime currentTime = LocalDateTime.now();
        when(aClock.now()).thenReturn(currentTime);
        when(aClock.getTimeDifference(currentTime)).thenReturn("(0 seconds ago)");
        String message = "Alice -> I totally hate this weather and fuck you.";
        String returnMessage = "I totally hate this weather and fuck you. (0 seconds ago)";
        commandHandler.handle(message);
        commandHandler.handle("Alice");
        verify(consoleHandler).printMessage(returnMessage);
    }
}
