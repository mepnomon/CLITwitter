package CLITwitter;

import java.util.Comparator;
import java.util.List;

public class MessagePrinter {
    private final Clock aClock;
    private final ConsoleHandler consoleHandler;

    /**
     * Constructs a message printer
     * @param aClock
     * @param consoleHandler
     */
    public MessagePrinter(Clock aClock, ConsoleHandler consoleHandler){
        this.aClock = aClock;
        this.consoleHandler = consoleHandler;
    }

    /**
     * Prints a message for a user's timeline
     * @param messageList Messages to print
     */
    public void printForTimeline(List<Message> messageList){

        messageList.sort(Comparator.comparing(Message::getPostTime).reversed());
        for (Message message : messageList) {
           consoleHandler.printMessage(message.formatForTimeLine(aClock));
        }
    }

    /**
     * Prints messages for a User's wall.
     * @param messageList Messages to print.
     */
    public void printForWall(List<Message> messageList){

        messageList.sort(Comparator.comparing(Message::getPostTime).reversed());
        for (Message message : messageList) {
            consoleHandler.printMessage(message.formatMessageForWall(aClock));
        }
    }
}
