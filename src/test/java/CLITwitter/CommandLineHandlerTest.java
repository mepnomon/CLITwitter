package CLITwitter;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandLineHandlerTest {

    CommandLineHandler commandLineHandler;
    CommandHandler commandHandler;
    @Before
    public void setUp() throws Exception {

        commandHandler = mock(CommandHandler.class);
        commandLineHandler = new CommandLineHandler();
    }

    @Test
    public void user_publishes_messages(){

        String message = "Message";
        //when(commandHandler.handle(message)).thenReturn("Nothing");
        fail("Not yet implemented");
    }

}