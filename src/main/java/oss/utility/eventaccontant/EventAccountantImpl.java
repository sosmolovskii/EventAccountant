package oss.utility.eventaccontant;

/**
 *  Implements {@link oss.utility.eventaccontant.EventAccountant} using {@link java.util.concurrent.ConcurrentHashMap}
 */
public class EventAccountantImpl implements EventAccountant {

    @Override
    public void newEvent() {

    }

    @Override
    public long getEventAmountPerLastMinute() {
        return 0;
    }

    @Override
    public long getEventAmountPerLastHour() {
        return 0;
    }

    @Override
    public long getEventAmountPerLastDay() {
        return 0;
    }
}
