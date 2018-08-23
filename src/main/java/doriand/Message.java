package doriand;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {


    private final User aUser;
    private final String message;
    private final LocalDateTime currentTime;

    public Message(User aUser, String message, LocalDateTime currentTime) {

        this.aUser = aUser;
        this.message = message;
        this.currentTime = currentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(aUser, message1.aUser) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(currentTime, message1.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aUser, message, currentTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "aUser=" + aUser +
                ", message='" + message + '\'' +
                ", currentTime=" + currentTime +
                '}';
    }
}
