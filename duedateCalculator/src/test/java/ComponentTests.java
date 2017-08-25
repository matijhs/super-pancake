import com.mate.DueDateCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Test class of helper component methods.
 */
public class ComponentTests {

    private Calendar submitTime;
    private DueDateCalculator dueDateCalculator;

    @Before
    public void setUp() throws Exception {
        submitTime = Calendar.getInstance();
        dueDateCalculator = new DueDateCalculator();
    }

    @Test
    public void isWeekendShouldReturnTrue() {
        //given
        submitTime.set(2017, Calendar.AUGUST, 26);   // a weekend date

        //when
        boolean result = dueDateCalculator.isWeekend(submitTime);

        //then
        assertTrue(result);
    }

    @Test
    public void isWeekendShouldReturnFalse() {
        //given
        submitTime.set(2017, Calendar.AUGUST, 21);   // a weekday

        //when
        boolean result = dueDateCalculator.isWeekend(submitTime);

        //then
        assertFalse(result);
    }

    @Test
    public void isWorkingHourShouldReturnTrue() {
        //given
        Calendar submitTime1 = Calendar.getInstance();
        Calendar submitTime2 = Calendar.getInstance();
        Calendar submitTime3 = Calendar.getInstance();
        Calendar submitTime4 = Calendar.getInstance();

        // set all of them inside working hours
        submitTime1.set(Calendar.HOUR_OF_DAY, 10);

        submitTime2.set(Calendar.HOUR_OF_DAY, 16);
        submitTime2.set(Calendar.MINUTE, 59);

        submitTime3.set(Calendar.HOUR_OF_DAY, 13);
        submitTime3.set(Calendar.MINUTE, 30);

        submitTime4.set(Calendar.HOUR_OF_DAY, 9);
        submitTime4.set(Calendar.MINUTE, 5);

        //when
        boolean result1 = dueDateCalculator.isWorkingHour(submitTime1);
        boolean result2 = dueDateCalculator.isWorkingHour(submitTime2);
        boolean result3 = dueDateCalculator.isWorkingHour(submitTime3);
        boolean result4 = dueDateCalculator.isWorkingHour(submitTime4);

        //then
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
    }

    @Test
    public void isWorkingHourShouldReturnFalse() {
        //given
        Calendar submitTime1 = Calendar.getInstance();
        Calendar submitTime2 = Calendar.getInstance();
        Calendar submitTime3 = Calendar.getInstance();
        Calendar submitTime4 = Calendar.getInstance();

        // set all of them outside of working hours
        submitTime1.set(Calendar.HOUR_OF_DAY, 8);

        submitTime2.set(Calendar.HOUR_OF_DAY, 17);
        submitTime2.set(Calendar.MINUTE, 30);

        submitTime3.set(Calendar.HOUR_OF_DAY, 0);
        submitTime3.set(Calendar.MINUTE, 0);

        submitTime4.set(Calendar.HOUR_OF_DAY, 17);
        submitTime4.set(Calendar.MINUTE, 17);

        //when
        boolean result1 = dueDateCalculator.isWorkingHour(submitTime1);
        boolean result2 = dueDateCalculator.isWorkingHour(submitTime2);
        boolean result3 = dueDateCalculator.isWorkingHour(submitTime3);
        boolean result4 = dueDateCalculator.isWorkingHour(submitTime4);

        //then
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

}
