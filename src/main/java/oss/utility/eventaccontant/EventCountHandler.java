package oss.utility.eventaccontant;

/**
 *
 */
public interface EventCountHandler {


    void addEvent(long second);

    long getEventAmount(long periodStartSecond);
}
