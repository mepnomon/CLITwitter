package CLITwitter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * A Clock object
 * Generates and returns time offsets.
 * @author Dorian Dressler
 */
public class Clock {

    private final int ONE_DAY     = 24;
    private final int SINGLE_UNIT = 1;
    private final String DAYS     = "days";
    private final String DAY      = "day";
    private final String HOURS    = "hours";
    private final String HOUR     = "hour";
    private final String MINUTE   = "minute";
    private final String MINUTES  = "minutes";
    private final String SECONDS  = "seconds";
    private final String SECOND   = "second";
    private final String FORMATTED_TIME = "(%d %s ago)";



    public LocalDateTime now() {

        return LocalDateTime.now();
    }

    /**
     * Selects appropriate methods to calculate a time offset displayed in a Message.
     * @param postTime the time a post was made
     * @return a formatted String with a time offset
     */
    public String getTimeDifference(LocalDateTime postTime){
        Duration difference = Duration.between(LocalDateTime.now(),postTime);
        String durationString = difference.toString();

        if(durationString.contains("H")){
            return formatHoursAndDays(difference);
        }
        if(durationString.contains("M")) {
            return formatMinutes(difference);
        }
        return formatSeconds(difference);
    }

    /*
        Returns a formatted String for Day(s)/Hour(s)
     */
    private String formatHoursAndDays(Duration difference){
        String timeUnit;
        long diff = Math.abs(difference.toHours());

        if(diff >= ONE_DAY){
            timeUnit = DAYS;
            diff = diff / ONE_DAY;
            if(diff == SINGLE_UNIT){
                timeUnit = DAY;
            }
        } else {
            timeUnit = HOURS;
            if(diff == SINGLE_UNIT) {
                timeUnit = HOUR;
            }
        }
        return String.format(FORMATTED_TIME,diff, timeUnit);
    }

    /*
        Returns a formatted String for Minute(s)
     */
    private String formatMinutes(Duration difference){
        String timeUnit = MINUTES;
        long diff = Math.abs(difference.toMinutes());
        if (diff == SINGLE_UNIT) {
            timeUnit = MINUTE;
        }
        return String.format(FORMATTED_TIME, diff,timeUnit);
    }

    /*
        Returns a formatted String for second(s)
     */
    private String formatSeconds(Duration difference){
        String timeUnit = SECONDS;
        long diff = Math.abs(difference.getSeconds());
        if(diff == SINGLE_UNIT){
            timeUnit = SECOND;
        }
        return String.format(FORMATTED_TIME, diff, timeUnit);
    }
}
