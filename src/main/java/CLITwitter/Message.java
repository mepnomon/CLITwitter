package CLITwitter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Message object
 * @author Dorian Dressler
 */
public class Message {
    private final User aUser;
    private final String message;
    private final LocalDateTime postTime;

    /**
     * Constructs a Message
     * @param aUser a User
     * @param message the message body
     * @param postTime timestamp of a post
     */
    public Message(User aUser, String message, LocalDateTime postTime) {
        this.aUser = aUser;
        this.message = message;
        this.postTime = postTime;
    }

    /**
     * Returns true if user matches
     * @param aUser the user
     * @return a boolean
     */
    public boolean belongsTo(User aUser){
        return this.aUser == aUser;
    }

    /**
     * Formats for the timeline
     * @param aClock a clock object
     * @return formatted message body and time of the post for the timeline
     */
    public String formatForTimeLine(Clock aClock){
        return message + " " + aClock.getTimeDifference(postTime);
    }
    public String formatMessageForWall(Clock aClock) {
        return aUser.getUserName() +" - " +  message + " " + aClock.getTimeDifference(postTime);
    }

    /**
     * @return timestamp of a post
     */
    public LocalDateTime getPostTime(){
        return postTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(aUser, message1.aUser) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(postTime, message1.postTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aUser, message, postTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "aUser=" + aUser +
                ", message='" + message + '\'' +
                ", postTime=" + postTime +
                '}';
    }
}
