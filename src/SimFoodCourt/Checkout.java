package SimFoodCourt;

import java.util.ArrayList;

/**************************************************************************
 * Checkout class: This class represents the area of the food court where
 * payment is made for food. One Queue, loaded by Person objects after
 * food is received at an eatery, leads to register(s). The register(s)
 * have a cashier, which takes a certain amount of time to process a 
 * purchase, as defined within the Person object, not the cashier himself.
 * Once the Person object pays, they are no longer monitored in 
 * the simulation. 
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Checkout implements ClockListener {
	/** The number of registers in the checkout area */
	private int numRegisters = 2;
	/** Queue for registers wait */
//	private ArrayList<Person> checkoutQ = new ArrayList<Person>();
	/** linked list for Q for registers wait */
	private LinkedListQPerson checkoutQ = new LinkedListQPerson();
	/** list of all registers */
	private ArrayList<Register> registers = new ArrayList<Register>();
	/** person at the checkout and the last to go to a register */
	private Person person = null; 
	/** record of largest size of the checkout queue */
	private int maxQlength = 0;
	/** number of transactions for all registers */
	private int numTransactions = 0;
	/** number of customers that left without paying */
	private int deserters = 0;
	
	/**********************************************************************
	 * Sets up the checkout area by created the wanted number of registers.
	 * @param numRegisters
	 *********************************************************************/
	public Checkout(int numRegisters) {
		this.numRegisters = numRegisters;
		
		// create registers and add the list
		for(int i = 0; i < numRegisters; i++) {
			registers.add(new Register());
		}
	}
	
	/**********************************************************************
	 * Adds a person to the checkout line.
	 * @param person
	 *********************************************************************/
	public void add(Person person) {
		checkoutQ.enQ(person);
		if(checkoutQ.size() > maxQlength)
			maxQlength = checkoutQ.size();
	}
	
	/**********************************************************************
	 * Processes the current event.
	 * @param tick the current second
	 * @Override
	 *********************************************************************/
	public void event(int tick) {
		// process all register events for current time
		for(Register r : registers) {
			// check if register is open: add a person if so
			if(tick >= r.getNextEvent() && checkoutQ.size() > 0) {	
				if(person != null) // make sure event time passes
					numTransactions++; // an order completed at register
				person = checkoutQ.deQ();
				r.add(person); 
				r.event(tick); // run register event				
			}
		}
		
		// person leaves checkout Q if too much time passes (stealing?)
		//-must reset tick count for persons to check for wait time
		if(checkoutQ.size() > 0) {
			Person temp; // used for loop
			
			for(int i = 0; i < checkoutQ.size(); i++) {
				temp = checkoutQ.get(i);
				// leave booth Q if too much time passes
				if(tick >= temp.getTickTime() + temp.getLeaveTime()) {
					checkoutQ.remove(i); // leaves the booth Q
					deserters++; // update count of customers that left Q
					// removing a cell decreases the size by 1
					// -set index back 1 to check next cell after next loop iteration
					if(checkoutQ.size() > 0)
						i--;
					else
						break; // leave loop
				}
			}
		}	
	}
	
	/**********************************************************************
	 * Returns the number of people still in line.
	 * @param none
	 * @return number people still in line
	 *********************************************************************/
	public int getLeftInLine() {
		return checkoutQ.size();
	}
	
	/**********************************************************************
	 * Returns the largest size of the line.
	 * @param none
	 * @return largest size of the line
	 *********************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/**********************************************************************
	 * Returns the total number of transactions.
	 * @param none
	 * @return numTransactions
	 *********************************************************************/
	public int getThroughPut() {
		return numTransactions;
		// retrieve total from all registers (same result as above)
		/*int total = 0;
		for(int i = 0; i < numRegisters; i++) {
			total += registers.get(i).getThroughPut();
		}
		return total;*/
	}
	
	/**********************************************************************
	 * Returns the total number of people who left line.
	 * @param none
	 * @return deserters
	 *********************************************************************/
	public int getNumDeserters() {
		return deserters;
	}
	
	/**********************************************************************
	 * Returns the last person in line.
	 * @param none
	 * @return person last in line
	 *********************************************************************/
	public Person getLastInQ() {
		if(checkoutQ.size() > 0)
			return checkoutQ.get(checkoutQ.size()-1);
		return null;
	}
	
	/**********************************************************************
	 * Returns the average transaction time from start to finish. Does not
	 * include deserters. Retrieves the average from all registers and sums
	 * to find the average over all registers
	 * @param none
	 * @return average time of a transaction
	 *********************************************************************/
	public double getAvgTimeTransaction() {
		double total = 0;
		for(int i = 0; i < numRegisters; i++) {
			total += registers.get(i).getAvgTimeTransactionReg();
		}
		
		if(numRegisters == 0)
			return 0;
		
		return (double)total / numRegisters;
	}

}
