package oss.utility.eventaccontant;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;


public class EventAccountantImplTest {

    @Test
    public void zeroEventTest() {
        assertEventCount(new EventAccountantImpl(), 0);
    }

    @Test
    public void oneEventTest() {
        EventAccountantImpl accountant = new EventAccountantImpl();

        accountant.processEvent();

        assertEventCount(accountant, 1);
    }

    @Test
    public void severalEventTest() {
        EventAccountantImpl accountant = new EventAccountantImpl();

        int i = 0;
        for (; i < new Random().nextInt(100); i++) {
            accountant.processEvent();
        }

        assertEventCount(accountant, i);
    }


    private void assertEventCount(EventAccountantImpl accountant, long count) {
        assertTrue("There are " + count + " events happened per last minute",
                accountant.getEventAmountPerLastMinute() == count);
        assertTrue("There are " + count + " events happened per last hour",
                accountant.getEventAmountPerLastHour() == count);
        assertTrue("There are " + count + " events happened per last day",
                accountant.getEventAmountPerLastDay() == count);
    }
}