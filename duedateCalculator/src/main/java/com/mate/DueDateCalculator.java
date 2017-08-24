package com.mate;


import java.util.Calendar;

public class DueDateCalculator {

    private static final int startWorkTime = 9;
    private static final int endWorkTime = 17;

    public DueDateCalculator() {
    }
    
    /**
     * @param submitDate     the submit date of the task.
     * @param turnaroundTime the amount of time needed to complete the task.
     * @return resolve time of the issue
     */
    public Calendar calculateDueDate(final Calendar submitDate, int turnaroundTime) {

        if (turnaroundTime == 0) throw new RuntimeException("Invalid turnaround parameter value");

        // if submitDate is outside of working hours
        if (!isWorkingHour(submitDate) || isWeekend(submitDate)) throw new RuntimeException("Invalid submitDate parameter value");

        Calendar resolveDate = Calendar.getInstance();
        resolveDate.setTime(submitDate.getTime());

        while (turnaroundTime > 0) {
            resolveDate.roll(Calendar.HOUR_OF_DAY, 1);
            turnaroundTime--;

            // in case work time ends or its weekend day++
            while (isWeekend(resolveDate) || !isWorkingHour(resolveDate)) {
                startNextWorkDay(resolveDate);
            }
        }

        return resolveDate;
    }

    /**
     * This method increments the day field of the given parameter and set the hours field to the value of startWorkTime.
     *
     * @param date
     */
    public void startNextWorkDay(Calendar date) {
        date.add(Calendar.DAY_OF_WEEK, 1);
        date.set(Calendar.HOUR_OF_DAY, startWorkTime);
    }

    /**
     * This method checks if the parameter date's time is between our working hours.
     *
     * @param date
     * @return true if time is between 9AM and 7PM, otherwise false.
     */
    public boolean isWorkingHour(Calendar date) {
        return (date.get(Calendar.HOUR_OF_DAY) >= startWorkTime) && (date.get(Calendar.HOUR_OF_DAY) < endWorkTime);
    }

    /**
     * This method checks if the given parameter date is a weekend.
     *
     * @param date
     * @return true if the given date is a weekday, false in case of weekend.
     */
    public boolean isWeekend(Calendar date) {
        return (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }


}
