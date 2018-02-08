package oss.utility.eventaccontant;

import java.time.Duration;
import java.time.Instant;

/**
 *  Implements {@link oss.utility.eventaccontant.EventAccountant} using {@link EventCountHandlerImpl}
 */
public class EventAccountantImpl implements EventAccountant {

    private EventCountHandler handler = new EventCountHandlerImpl();

    @Override
    public void processEvent() {
        handler.addEvent(Instant.now().getEpochSecond());
    }

    @Override
    public long getEventAmountPerLastMinute() {
        return handler.getEventAmount(Instant.now().minus(Duration.ofMinutes(1)).getEpochSecond());
    }

    @Override
    public long getEventAmountPerLastHour() {
        return handler.getEventAmount(Instant.now().minus(Duration.ofHours(1)).getEpochSecond());
    }

    @Override
    public long getEventAmountPerLastDay() {
        return handler.getEventAmount(Instant.now().minus(Duration.ofDays(1)).getEpochSecond());
    }
}
