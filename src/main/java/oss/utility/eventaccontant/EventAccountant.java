package oss.utility.eventaccontant;

public interface EventAccountant {

    /**
        Processes new Event
     */
    void newEvent();

    /**
     * Counts events that occurred in the last 60 seconds
     *
     * @return events count
     */
    long getEventAmountPerLastMinute();

    /**
     * Counts events that occurred in the last 60 minutes
     *
     * @return events count
     */
    long getEventAmountPerLastHour();

    /**
     * Counts events that occurred in the last 24 hours
     *
     * @return events count
     */
    long getEventAmountPerLastDay();
}
