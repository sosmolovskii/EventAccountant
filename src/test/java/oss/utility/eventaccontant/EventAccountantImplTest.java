package oss.utility.eventaccontant;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class EventAccountantImplTest {

    @Test
    public void zeroEventTest() {
        EventAccountantImpl accountant = new EventAccountantImpl();

        assertTrue("There are no events happened per last minute", accountant.getEventAmountPerLastMinute() == 0);
        assertTrue("There are no events happened per last hour", accountant.getEventAmountPerLastHour() == 0);
        assertTrue("There are no events happened per last day", accountant.getEventAmountPerLastDay() == 0);

    }

    @Test
    public void oneEventTest() {
        EventAccountantImpl accountant = new EventAccountantImpl();

        accountant.newEvent();

        assertTrue("There is 1 event happened per last minute", accountant.getEventAmountPerLastMinute() == 1);
        assertTrue("There are no events happened per last hour", accountant.getEventAmountPerLastHour() == 0);
        assertTrue("There are no events happened per last day", accountant.getEventAmountPerLastDay() == 0);

    }

}