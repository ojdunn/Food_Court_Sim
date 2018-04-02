package SimFoodCourt;

//import java.awt.Color;

/**************************************************************************
 * SpecialNeedsPerson class: This class represents a special needs person.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class SpecialNeedsPerson extends Person{	
		/*public SpecialNeedsPerson(){
			setColor(Color.BLUE);
		}*/
		
		/******************************************************************
		 * Sets the time to make an order.
		 * @param time time to make an order
		 * @return none
		 *****************************************************************/
		public void setEateryTime(double time) {
			this.eateryTime = time*4;
		}	
		
		/******************************************************************
		 * Sets time cashier takes.
		 * @param Time time cashier takes
		 * @return none
		 *****************************************************************/
		public void setCashiersTime(double Time) {
			this.cashiersTime = Time*2;
		}

		/******************************************************************
		 * Sets the time person will wait.
		 * @param Time time person will wait
		 * @return none
		 *****************************************************************/
		public void setLeaveTime(double Time) {
			this.leaveTime = Time*3;
		}
	}

