package oss.utility.eventaccontant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@RunWith(Theories.class)
public class EventCountHandlerTest extends Assert {

    private static Instant now = Instant.now();

    private EventCountHandler handler = new EventCountHandlerImpl();

    @Before
    public void before() {
        Arrays.asList(getSecondInPast(now, 1, ChronoUnit.HALF_DAYS),
                getSecondInPast(now, 30, ChronoUnit.MINUTES),
                getSecondInPast(now, 1, ChronoUnit.SECONDS))
        .forEach((event) -> handler.addEvent(event));

    }

    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { getSecondInPast(now, 1, ChronoUnit.DAYS), 3L },
            { getSecondInPast(now, 1, ChronoUnit.HOURS), 2L },
            { getSecondInPast(now, 1, ChronoUnit.MINUTES), 1L }
    };

    private static Long getSecondInPast(Instant now, int i, ChronoUnit halfDays) {
        return now.minus(i, halfDays).getEpochSecond();
    }

    @Theory
    public void logicTest(final Object... testData) {
        assertTrue("", handler.getEventAmount((Long)testData[0]) == (Long)testData[1]);
    }
}