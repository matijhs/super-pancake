import com.mate.DueDateCalculator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Calendar;

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

    @Test
    public void callWithInvalidSubmitDateParamShouldThrowException() {

        // given
        submitDate.set(2017, Calendar.AUGUST, 24, 8, 30);
        int turnAroundTime = 10;

        // then
        exceptions.expect(RuntimeException.class);
        exceptions.expectMessage("Invalid submitDate parameter value");

        // when
        dueDateCalculator.calculateDueDate(submitDate, turnAroundTime);
    }

    @Test
    public void callWithInvalidTurnAroundParamShouldThrowException() {

        // given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 30);
        int turnAroundTime = 0;

        // then
        exceptions.expect(RuntimeException.class);
        exceptions.expectMessage("Invalid turnaround parameter value");

        // when
        dueDateCalculator.calculateDueDate(submitDate, turnAroundTime);
    }

    @Test
    public void lessThanOneDayTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 0, 0);
        int turnaroundTime1 = 7;
        int turnaroundTime2 = 1;
        int turnaroundTime3 = 4;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 24, 16, 0, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 24, 10, 0, 0);
        Calendar expectedResult3 = Calendar.getInstance();
        expectedResult3.set(2017, Calendar.AUGUST, 24, 13, 0, 0);

        //when
        Calendar resolveDate1 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime1);
        Calendar resolveDate2 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime2);
        Calendar resolveDate3 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime3);

        //then
        assertEquals(expectedResult1.getTime().toString(), resolveDate1.getTime().toString());
        assertEquals(expectedResult2.getTime().toString(), resolveDate2.getTime().toString());
        assertEquals(expectedResult3.getTime().toString(), resolveDate3.getTime().toString());
    }

    @Test
    public void moreThanOneDayTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 21, 9, 0, 0);
        int turnaroundTime1 = 12;
        int turnaroundTime2 = 16;
        int turnaroundTime3 = 25;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 22, 13, 0, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 23, 9, 0, 0);
        Calendar expectedResult3 = Calendar.getInstance();
        expectedResult3.set(2017, Calendar.AUGUST, 24, 10, 0, 0);

        //when
        Calendar resolveDate1 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime1);
        Calendar resolveDate2 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime2);
        Calendar resolveDate3 = dueDateCalculator.calculateDueDate(submitDate, turnaroundTime3);

        //then
        assertEquals(expectedResult1.getTime().toString(), resolveDate1.getTime().toString());
        assertEquals(expectedResult2.getTime().toString(), resolveDate2.getTime().toString());
        assertEquals(expectedResult3.getTime().toString(), resolveDate3.getTime().toString());
    }

    @Test
    public void weekendTurnaroundTimeTest() {
        //given
        submitDate.set(2017, Calendar.AUGUST, 24, 9, 0, 0);
        int turnaroundTime1 = 12;
        int turnaroundTime2 = 16;
        int turnaroundTime3 = 25;

        Calendar expectedResult1 = Calendar.getInstance();
        expectedResult1.set(2017, Calendar.AUGUST, 25, 13, 0, 0);
        Calendar expectedResult2 = Calendar.getInstance();
        expectedResult2.set(2017, Calendar.AUGUST, 28, 9, 0, 0);
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
