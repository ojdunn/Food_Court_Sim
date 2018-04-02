package SimFoodCourt;

/**************************************************************************
 * Register class: This class represents a register in the checkout area.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Register implements ClockListener {
	/** this is the person at the register buying food */
	private Person person = null;
	/** time until next person is added to register or complete */
	private int timeOfNextEvent = 0;
	/** time of the last event */
//	private int timeOfLastEvent = 0;
	/** number of transactions for register */
	private int numTransactionsReg = 0;
	/** if the register is open */
//	private boolean isOpen = true;
	/** total time spend in food court for all people ending at this
	 *  register */
	private int totalTransactionTimeReg = 0;
	
	/**********************************************************************
	 * Adds a person to the registers. Only one person can be at the 
	 * register.
	 * @param person
	 * @return none
	 *********************************************************************/
	public void add(Person person)
	{
		this.person = person;
	}
	
	/**********************************************************************
	 * Runs the register event.
	 * @param tick current second
	 * @return none
	 * @Override
	 *********************************************************************/
	public void event(int tick) {
		if(person != null) {
			// this runs just before setting up next purchase period
			// prevent repeats for same person
			if(timeOfNextEvent > 0) // don't add for initial
				numTransactionsReg++; // for this register only
			
			timeOfNextEvent = tick + (int) (person.getCashiersTime() + 1);
			// calculate time spent in food court
			//person.setTickTimeEnd(tick); // this is same as current tick time (not needed?)
			totalTransactionTimeReg += tick - person.getTickTimeStart();
			person = null; // they leave the food court
		}
	}
	
	/**********************************************************************
	 * Used to only call event method in register class every event time.
	 * @param none
	 * @return time of next event
	 *********************************************************************/
	public int getNextEvent() {
		return timeOfNextEvent;
	}
	
	/**********************************************************************
	 * Checks if the register is open.
	 * @param none
	 * @return is the register is open
	 *********************************************************************/
	public boolean isOpen() {
		return person == null;
	}

	/**********************************************************************
	 * Gets number of transactions at this register.
	 * @param none
	 * @return number of transactions at this register
	 *********************************************************************/
	public int getThroughPut() {
		return numTransactionsReg;
	}
	
	/**********************************************************************
	 * Returns the average transaction time ending at this register from
	 * start to finish. Does not include deserters.
	 * @param none
	 * @return average transaction time ending at this register from
	 * start to finish
	 *********************************************************************/
	public double getAvgTimeTransactionReg() {
		if(numTransactionsReg == 0)
			return 0;
		return (double)totalTransactionTimeReg / numTransactionsReg; 
	}
	
}
