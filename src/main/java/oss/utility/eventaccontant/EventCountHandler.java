package oss.utility.eventaccontant;

/**
 *
 */
public interface EventCountHandler {

    /**
     * Process one event happens at time
     *
     * @param second -
     */
    void addEvent(long second);

    /**
     * Calculate event count occurred in the period (periodStartSecond, now)
     *
     * @param periodStartSecond period start second in past
     * @return event count
     */
    long getEventAmount(long periodStartSecond);

    /**
     * Remove excess entries
     */
    void removeExcess();
}
