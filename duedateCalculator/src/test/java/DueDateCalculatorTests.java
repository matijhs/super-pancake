import com.mate.DueDateCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Test class of DueDateCalculator.calculateDueDate method.
 */
public class DueDateCalculatorTests {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private DueDateCalculator dueDateCalculator;
    private Calendar submitDate;

    @Before
    public void setUp() throws Exception {
        dueDateCalculator = new DueDateCalculator();
        submitDate = Calendar.getInstance();
    }

    /**
     * Test calculateDueDate with date input outside of our working hours. The below exception should be thrown.
     * @throws RuntimeException Invalid submitDate parameter value
     */
    @Test
    public void callWithInvalidSubmitDateParamShouldThrowException() {

        // given
        submitDate.set(2017, Calendar.AUGUST, 24, 8, 30);
        int turnaroundTime = 10;

        // then
        exceptions.expect(RuntimeException.class);
        exceptions.expectMessage("Invalid submitDate parameter value");

        // when
        dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);
    }

    /**
     * Test calculateDueDate with valid date and 0 (invalid) turnaroundTime parameter value.
     * The below exception should be thrown.
     * @throws RuntimeException Invalid turnaround parameter value
     */
    @Test
    public void callWithInvalidTurnAroundParamShouldThrowException() {

        // given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 30);
        int turnaroundTime = 0;

        // then
        exceptions.expect(RuntimeException.class);
        exceptions.expectMessage("Invalid turnaround parameter value");

        // when
        dueDateCalculator.calculateDueDate(submitDate, turnaroundTime);
    }

    /**
     * Test calculateDueDate with valid date and turnaroundTime value.
     * The resolve dates are expected to be on the same working day as the submitDate.
     */
    @Test
    public void lessThanOneDayTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 30, 0);
        int turnaroundTime1 = 7;
        int turnaroundTime2 = 1;
        int turnaroundTime3 = 4;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 24, 16, 30, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 24, 10, 30, 0);
        Calendar expectedResult3 = Calendar.getInstance();
        expectedResult3.set(2017, Calendar.AUGUST, 24, 13, 30, 0);

        //when
        Calendar resolveDate1 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime1);
        Calendar resolveDate2 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime2);
        Calendar resolveDate3 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime3);

        //then
        assertEquals(expectedResult1.getTime().toString(), resolveDate1.getTime().toString());
        assertEquals(expectedResult2.getTime().toString(), resolveDate2.getTime().toString());
        assertEquals(expectedResult3.getTime().toString(), resolveDate3.getTime().toString());
    }

    /**
     * Test calculateDueDate with valid date and turnaroundTime value.
     * The resolve dates are expected to be on a different working day than the given submitDate.
     */
    @Test
    public void moreThanOneDayTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 21, 9, 30, 0);
        int turnaroundTime1 = 12;
        int turnaroundTime2 = 16;
        int turnaroundTime3 = 25;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 22, 13, 30, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 23, 9, 30, 0);
        Calendar expectedResult3 = Calendar.getInstance();
        expectedResult3.set(2017, Calendar.AUGUST, 24, 10, 30, 0);

        //when
        Calendar resolveDate1 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime1);
        Calendar resolveDate2 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime2);
        Calendar resolveDate3 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime3);

        //then
        assertEquals(expectedResult1.getTime().toString(), resolveDate1.getTime().toString());
        assertEquals(expectedResult2.getTime().toString(), resolveDate2.getTime().toString());
        assertEquals(expectedResult3.getTime().toString(), resolveDate3.getTime().toString());
    }

    /**
     * Test calculateDueDate with valid date and turnaroundTime value.
     * The submitDate and turnaroundTime are given as that
     * the resolve dates are expected to be on a working day next week.
     */
    @Test
    public void weekendTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 0, 0);
        int turnaroundTime1 = 12;
        int turnaroundTime2 = 18;
        int turnaroundTime3 = 25;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 25, 13, 0, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 28, 11, 0, 0);
        Calendar expectedResult3 = Calendar.getInstance();
        expectedResult3.set(2017, Calendar.AUGUST, 29, 10, 0, 0);

        //when
        Calendar resolveDate1 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime1);
        Calendar resolveDate2 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime2);
        Calendar resolveDate3 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime3);

        //then
        assertEquals(expectedResult1.getTime().toString(), resolveDate1.getTime().toString());
        assertEquals(expectedResult2.getTime().toString(), resolveDate2.getTime().toString());
        assertEquals(expectedResult3.getTime().toString(), resolveDate3.getTime().toString());
    }
}
