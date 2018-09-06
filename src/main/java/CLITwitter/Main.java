package CLITwitter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        MessageRepository messageRepository = new MessageRepository();
        UserRepository userRepository = new UserRepository();
        Clock aClock = new Clock();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        WallRepository wallRepository = new WallRepository();
        MessagePrinter messagePrinter = new MessagePrinter(aClock,consoleHandler);
        CommandHandler commandHandler = new CommandHandler(messageRepository,userRepository,aClock,
                wallRepository,messagePrinter);

        Scanner sc = new Scanner(System.in);
        while(true){
            String message = sc.nextLine();
            commandHandler.handle(message);
        }
    }
}
