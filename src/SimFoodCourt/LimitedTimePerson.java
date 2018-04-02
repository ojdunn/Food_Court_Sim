package SimFoodCourt;

//import java.awt.Color;

/**************************************************************************
 * LimitedTimePerson class: This class represents a limited time person.
 * Child of Person class. 
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class LimitedTimePerson extends Person {
		/*public LimitedTimePerson(){
			setColor(Color.ORANGE);
		}*/

		/******************************************************************
		 * Sets the time to make an order.
		 * @param time time to make an order
		 * @return none
		 *****************************************************************/
		public void setEateryTime(double time) {
			this.eateryTime = time*0.5;
		}	

		/******************************************************************
		 * Sets the time person will wait.
		 * @param Time time person will wait
		 * @return none
		 *****************************************************************/
		public void setLeaveTime(double Time) {
			this.leaveTime = Time*0.5;
		}
}
