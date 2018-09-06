package CLITwitter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class MessagePrinterTest {

    Clock aClock;
    ConsoleHandler consoleHandler;
    @Before
    public void setUp() throws Exception {
        aClock = mock(Clock.class);
        consoleHandler = mock(ConsoleHandler.class);
    }

    @Test
    public void test_message_for_timeline(){
        MessagePrinter messagePrinter = new MessagePrinter(aClock, consoleHandler);
        User aUser = new User("Alice");
        LocalDateTime currentTime = LocalDateTime.now();
        String messageText = "I love the weather today";
        when(aClock.getTimeDifference(currentTime)).thenReturn("(5 minutes ago)");
        Message aMessage = new Message(aUser,messageText,currentTime);
        messagePrinter.printForTimeline(asList(aMessage));
        verify(consoleHandler).printMessage("I love the weather today (5 minutes ago)");
    }

    @Test
    public void test_messages_for_timeline_in_order(){
        MessagePrinter messagePrinter = new MessagePrinter(aClock, consoleHandler);
        User aUser = new User("Alice");
        LocalDateTime currentTimeSecondsAgo = LocalDateTime.now().minusSeconds(10);
        LocalDateTime currentTimeMinutesAgo = LocalDateTime.now().minusMinutes(5);
        String messageText1 = "I love the weather today";
        String messageText2 = "Now it's raining";
        when(aClock.getTimeDifference(currentTimeSecondsAgo)).thenReturn("(10 seconds ago)");
        when(aClock.getTimeDifference(currentTimeMinutesAgo)).thenReturn("(5 minutes ago)");
        Message firstMessage = new Message(aUser, messageText1,currentTimeMinutesAgo);
        Message secondMessage = new Message(aUser, messageText2, currentTimeSecondsAgo);
        messagePrinter.printForTimeline(asList(firstMessage, secondMessage));
        InOrder inOrder = inOrder(consoleHandler);
        inOrder.verify(consoleHandler).printMessage("Now it's raining (10 seconds ago)");
        inOrder.verify(consoleHandler).printMessage("I love the weather today (5 minutes ago)");
    }

    @Test
    public void test_format_messages_for_timelines(){
        MessagePrinter messagePrinter = new MessagePrinter(aClock, consoleHandler);

        User aUserCharlie = new User("Charlie");
        LocalDateTime timeCharlie = LocalDateTime.now().minusMinutes(5);
        when(aClock.getTimeDifference(timeCharlie)).thenReturn("(5 minutes ago)");
        String messageTextCharlie = "I'm in new York Today! Anyone want to have a coffee?";
        Message messageCharlie = new Message(aUserCharlie, messageTextCharlie,timeCharlie);

        User aUserAlice = new User("Alice");
        LocalDateTime timeAlice = LocalDateTime.now().minusSeconds(10);
        when(aClock.getTimeDifference(timeAlice)).thenReturn("(10 seconds ago)");
        String messageTextAlice = "Now it's raining";
        Message messageAlice = new Message(aUserAlice, messageTextAlice,timeAlice);


        messagePrinter.printForWall(asList(messageCharlie, messageAlice));
        InOrder inOrder = inOrder(consoleHandler);
        inOrder.verify(consoleHandler).printMessage("Alice - Now it's raining (10 seconds ago)");
        inOrder.verify(consoleHandler).printMessage("Charlie - I'm in new York Today! Anyone want to have a coffee? (5 minutes ago)");
    }


}