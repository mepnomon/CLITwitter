package CLITwitter;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ClockTest {


    Clock aClock;

    @Before
    public void setUp() throws Exception {
        aClock = new Clock();
    }

    @Test
    public void test_second(){
        String expectedResult = "(1 second ago)";
        LocalDateTime postTime = LocalDateTime.now().minusSeconds(1);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_seconds(){
        String expectedResult = "(2 seconds ago)";
        LocalDateTime postTime = LocalDateTime.now().minusSeconds(2);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_minute(){
        String expectedResult = "(1 minute ago)";
        LocalDateTime postTime = LocalDateTime.now().minusMinutes(1);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_minutes(){
        String expectedResult = "(10 minutes ago)";
        LocalDateTime postTime = LocalDateTime.now().minusMinutes(10);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_hour(){
        String expectedResult = "(1 hour ago)";
        LocalDateTime postTime = LocalDateTime.now().minusMinutes(60);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_hours(){
        String expectedResult = "(2 hours ago)";
        LocalDateTime postTime = LocalDateTime.now().minusHours(2);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_day(){
        String expectedResult = "(1 day ago)";
        LocalDateTime postTime = LocalDateTime.now().minusHours(24);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }

    @Test
    public void test_days(){
        String expectedResult = "(2 days ago)";
        LocalDateTime postTime = LocalDateTime.now().minusDays(2);
        assertEquals(expectedResult,aClock.getTimeDifference(postTime));
    }
}