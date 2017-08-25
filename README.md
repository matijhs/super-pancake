# super-pancake
<h5>Due Date Calculator</h5>

<p>The program reads the currently reported problems (bugs) from an issue tracking system and calculates the due date</p>

<p>The following function does the job:</p>
<p>DueDateCalculator.calculateDueDate(Calendar dueDate, int turnaroundTime)</p>

<br>
<p> java.util.Calendar: </p>
<p>
	This abstract class provides methods for manipulating the calendar fields (YEAR, MONTH, DAY, HOUR_OF_DAY, etc...), which makes it a good choice for calculating the required due dates.
</p>
<p> Getting and setting the calendar field values is done by calling the <i>get</i>, <i>set</i> or <i>roll</i> methods, which is handy </p>

<br>
<p>Behaviour of calculateDueDate: </p>
<ul> 

<li> In the body of the function the given submitDate gets rolled by one hour while the turnaroundTime is decremented util it is zero.
<li> In case the turnaroundTime is too large to fit the due date in one working day,
 the DAY_OF_WEEK field of the dueDate is incremented and the HOUR_OF_DAY is set to 9AM (which is our starting working hour).
<li> If we run into a weekend day, the DAY_OF_WEEK field of the dueDate is incremented until the first weekday.
</ul>
