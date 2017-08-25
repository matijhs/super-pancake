package com.mate;

import java.util.Calendar;

/**
 * The program reads the currently reported problems (bugs) from an issue tracking system and calculates
 * the due date following the rules below:
 * <ul>
 * <li>Working hours are 9AM to 5PM every working day (Monday through Friday)
 * <li>The program does not deal with holidays, which means that a holiday on a Thursday is still
 * considered as a working day by the program. Also a working Saturday will still be considered as
 * a nonworking day by the system.
 * <li>The turnaround time is given in working hours, which means for example that 2 days are 16
 * hours. As an example: if a problem was reported at 2:12PM on Tuesday then it is due by
 * 2:12PM on Thursday.
 * <li>A problem can only be reported during working hours, which means that all submit date values
 * fall between 9AM and 5PM.
 </ul>
 */
public class DueDateCalculator {

    /**
     * Start of work time is 9AM
     */
    private static final int startWorkTime = 9;

    /**
     * Start of work time is 5PM
     */
    private static final int endWorkTime = 17;

    public DueDateCalculator() {
    }
    
    /**
     * CalculateDueDate method, which takes the submitDate and
     * turnaroundTime as an input and returns the date and time when the issue is to be resolved.
     *
     * @param submitDate     The submit date of the task.
     * @param turnaroundTime The amount of time needed to complete the task.
     * @return Resolve time of the issue
     */
    public Calendar calculateDueDate(final Calendar submitDate, int turnaroundTime) {

        // invalid parameter handling
        if (turnaroundTime == 0) throw new RuntimeException("Invalid turnaround parameter value");
        if (!isWorkingHour(submitDate) || isWeekend(submitDate)) throw new RuntimeException("Invalid submitDate parameter value");

        Calendar resolveDate = Calendar.getInstance();
        resolveDate.setTime(submitDate.getTime());

        // calculate the resolveDate
        while (turnaroundTime > 0) {
            resolveDate.roll(Calendar.HOUR_OF_DAY, 1);
            turnaroundTime--;

            // in case work time ends or its weekend, day++
            while (isWeekend(resolveDate) || !isWorkingHour(resolveDate)) {
                startNextWorkDay(resolveDate);
            }
        }

        return resolveDate;
    }

    /**
     * This method increments the day field of the given parameter and set the hours field to the value of startWorkTime.
     *
     * @param date the Calendar instance to be modified.
     */
    public void startNextWorkDay(Calendar date) {
        date.add(Calendar.DAY_OF_WEEK, 1);
        date.set(Calendar.HOUR_OF_DAY, startWorkTime);
    }

    /**
     * This method checks if the parameter date's time is between our working hours.
     *
     * @param date the Calendar instance to be modified.
     * @return true if time is between 9AM and 7PM, otherwise false.
     */
    public boolean isWorkingHour(Calendar date) {
        return (date.get(Calendar.HOUR_OF_DAY) >= startWorkTime) && (date.get(Calendar.HOUR_OF_DAY) < endWorkTime);
    }

    /**
     * This method checks if the given parameter date is a weekend.
     *
     * @param date the Calendar instance to be modified.
     * @return true if the given date is a weekday, false in case of weekend.
     */
    public boolean isWeekend(Calendar date) {
        return (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }


}
