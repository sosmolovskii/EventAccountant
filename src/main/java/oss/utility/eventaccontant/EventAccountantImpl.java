package oss.utility.eventaccontant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
        return handler.getEventAmount(Instant.now().minus(1, ChronoUnit.MINUTES).getEpochSecond());
    }

    @Override
    public long getEventAmountPerLastHour() {
        return handler.getEventAmount(Instant.now().minus(1, ChronoUnit.HOURS).getEpochSecond());
    }

    @Override
    public long getEventAmountPerLastDay() {
        return handler.getEventAmount(Instant.now().minus(1, ChronoUnit.DAYS).getEpochSecond());
    }
}
