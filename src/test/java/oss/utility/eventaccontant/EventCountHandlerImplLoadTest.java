package oss.utility.eventaccontant;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
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

        assertEquals("There are no calculated events", THREAD_COUNT*EVENT_COUNT_PER_THREAD,
                handler.getEventAmount(Instant.now().minus(Duration.ofDays(1)).getEpochSecond()));

        long averageCount =
                handler.getEventAmount(Instant.now().minus(Duration.ofMinutes(1)).getEpochSecond()) / 60;
        assertTrue("Load factor is too low", 10000 <= averageCount);
        System.out.println("Average events count per second:" + averageCount);
    }
}