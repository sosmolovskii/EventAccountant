package oss.utility.eventaccontant;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class EventCountHandlerImplLoadTest {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int EVENT_COUNT_PER_THREAD = 100_000_000;

    @Test
    public void testEvent() {
        EventCountHandlerImpl handler = new EventCountHandlerImpl();

        Stream.iterate(0, n -> n + 1)
                .limit(THREAD_COUNT)
                .collect(Collectors.toList())
                .parallelStream()
                .forEach((i) -> {
                    for (int j = 0; j < EVENT_COUNT_PER_THREAD; j++) {
                        Instant now = Instant.now();
                        handler.addEvent(now.getEpochSecond());
                    }
                });

        assertEquals("There are no calculated events", THREAD_COUNT*EVENT_COUNT_PER_THREAD, handler.getEventAmount(Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond()));
        assertTrue("Load factor is too low", 10000 <= handler.getEventAmount(Instant.now().minus(1, ChronoUnit.MINUTES).getEpochSecond())/60);
        System.out.println("Average events count per second:" + handler.getEventAmount(Instant.now().minus(1, ChronoUnit.MINUTES).getEpochSecond())/60);
    }
}