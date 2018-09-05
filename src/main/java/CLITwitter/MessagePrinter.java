package CLITwitter;

import java.util.Comparator;
import java.util.List;

public class MessagePrinter {


    private final Clock aClock;
    private final ConsoleHandler consoleHandler;

    public MessagePrinter(Clock aClock, ConsoleHandler consoleHandler){

        this.aClock = aClock;
        this.consoleHandler = consoleHandler;
    }


    public void formatMessageForTimeline(List<Message> messageList){

        messageList.sort(Comparator.comparing(Message::getPostTime).reversed());
        for (Message message : messageList) {
           consoleHandler.printMessage(message.formatForTimeLine(aClock));
        }
    }

    public void formatMessageForWall(List<Message> messageList){
        messageList.sort(Comparator.comparing(Message::getPostTime).reversed());
        for (Message message : messageList) {
            consoleHandler.printMessage(message.formatMessageForWall(aClock));
        }
    }
}
