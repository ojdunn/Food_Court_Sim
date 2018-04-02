package SimFoodCourt;

/**************************************************************************
 * Person class: This class represents a person.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Person {
	/** master clock time */
	protected int tickTime;  
	/** time person enters a Q for an eatery */
	protected int tickTimeStart; 
	/** time person completes transaction */
	protected int tickTimeEnd;  
	/** time it takes to order food at eatery */
	protected double eateryTime;  
	/** how long for cashier to complete their order */
	protected double cashiersTime; 
	/** how long before person will leave booth */
	protected double leaveTime;  
	/** used to set/get checkout location */
	protected Checkout checkout;  
	/** used for linked list */
	protected Person next = null; 
	
	/******************************************************************
	 * Gets the time person takes to order.
	 * @param none 
	 * @return time person takes to order
	 *****************************************************************/
	public double getEateryTime() {
		return eateryTime;
	}
	
	/******************************************************************
	 * Sets the time to make an order.
	 * @param time time to make an order
	 * @return none
	 *****************************************************************/
	public void setEateryTime(double time) {
		this.eateryTime = time;
	}	
	
	/******************************************************************
	 * Gets the time person arrived at food court.
	 * @param none
	 * @return time person arrived at food court
	 *****************************************************************/
	public int getTickTime() {
		return tickTime;
	}

	/******************************************************************
	 * Sets the time person arrived at food court/checkout.
	 * @param tickTime time person arrived at food court/checkout
	 * @return none
	 *****************************************************************/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	/******************************************************************
	 * Gets the time person arrived at food court.
	 * @param none 
	 * @return tickTimeStart time person arrived at food court
	 *****************************************************************/
	public int getTickTimeStart() {
		return tickTimeStart;
	}
	
	/******************************************************************
	 * Sets the time person arrived at food court.
	 * @param tickTime time person arrived at food court
	 * @return none
	 *****************************************************************/
	public void setTickTimeStart(int tickTimeStart) {
		this.tickTimeStart = tickTimeStart;
	}
	
	/******************************************************************
	 * Gets the time person completed transaction.
	 * @param none 
	 * @return tickTimeEnd time person completed transaction
	 *****************************************************************/
	public int getTickTimeEnd() {
		return tickTimeEnd;
	}
	
	/******************************************************************
	 * Sets the time person completed transaction.
	 * @param tickTimeEnd time person completed transaction
	 * @return none
	 *****************************************************************/
	public void setTickTimeEnd(int tickTimeEnd) {
		this.tickTimeEnd = tickTimeEnd;
	}
	
	/******************************************************************
	 * Sets payment location after order complete.
	 * @param checkout location of checkout
	 * @return none
	 *****************************************************************/
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}
	
	/******************************************************************
	 * Gets the checkout location.
	 * @param none
	 * @return checkout location
	 *****************************************************************/
	public Checkout getCheckout() {
		return checkout;
	}
	
	/******************************************************************
	 * Sets time cashier takes.
	 * @param Time time cashier takes
	 * @return none
	 *****************************************************************/
	public void setCashiersTime(double Time) {
		this.cashiersTime = Time;
	}
	
	/******************************************************************
	 * Gets time cashier takes.
	 * @param none 
	 * @return cashiersTime time cashier takes
	 *****************************************************************/
	public double getCashiersTime() {
		return cashiersTime;
	}
	
	/******************************************************************
	 * Sets the time person will wait.
	 * @param Time time person will wait
	 * @return none
	 *****************************************************************/
	public void setLeaveTime(double Time) {
		this.leaveTime = Time;
	}
	
	/******************************************************************
	 * Gets time before person will leave.
	 * @param none 
	 * @return time before person will leave
	 *****************************************************************/
	public double getLeaveTime() {
		return leaveTime;
	}
	
	/******************************************************************
	 * Sets the next node in the linked list.
	 * @param node next node in the linked list
	 * @return none
	 *****************************************************************/
	public void setNext(Person node) {
		next = node;
	}
	
	/******************************************************************
	 * Gets the next node in the linked list.
	 * @param none 
	 * @return person next node in the linked list
	 *****************************************************************/
	public Person getNext() {
		return next;
	}
}