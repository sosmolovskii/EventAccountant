package oss.utility.eventaccontant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  Implements {@link oss.utility.eventaccontant.EventCountHandler}
 *  using {@link java.util.concurrent.ConcurrentHashMap}
 *  and {@link java.util.concurrent.atomic.AtomicLong}
 */
public class EventCountHandlerImpl implements EventCountHandler {

    private ConcurrentHashMap<Long, AtomicLong> repository = new ConcurrentHashMap<>();

    @Override
    public void addEvent(long second) {
        repository.putIfAbsent(second, new AtomicLong(0));
        repository.get(second).incrementAndGet();
    }

    @Override
    public long getEventAmount(long periodStartSecond) {
        return repository.entrySet().stream()
                .filter((entry) -> entry.getKey() >= periodStartSecond)
                .mapToLong((entry) -> entry.getValue().get())
                .sum();
    }

    @Override
    public void removeExcess() {
        long border = Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond();
        repository.entrySet().removeIf(entry -> entry.getKey() < border);
    }
}
