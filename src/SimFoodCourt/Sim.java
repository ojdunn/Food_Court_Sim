package SimFoodCourt;

import java.util.ArrayList;

/**************************************************************************
 * Sim class: This class simulates a food court.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Sim {
	/** number of booths */
	private static int numBooths = 5;
	/** number of seconds before a new person arrives in food court */
	private static int numOfTicksNextPerson = 20;
	/** average time it takes to order food at booth */
	private static int averageEateryTime = 60;
	/** how long for cashier to complete their order */
	private static int averageCashierTime = 10;
	/** how long before person will leave booth */
	private static int leaveTime = 900;
	/** number of registers at checkout */
	private static int numRegisters = 2;
	/** total time of simulation in seconds */
	private static int totalTime = 10000;
	/** clock for simulation */
	private Clock clk;
	/** location to pay for food */
	private static Checkout checkout;
	/** locations to order food */
	private ArrayList<Eatery> booths;
	/** generates people to go to food court */
	private PersonProducer produce;
	
	public Sim() { // use to setup number booths, etc. from GUI
		clk = new Clock(); // used to process other objects in time
		
		// create food booths
		booths = new ArrayList<Eatery>();
		for(int i = 0; i < numBooths; i++) {
			booths.add(new Eatery());
		}
		
		// create checkout area
		checkout = new Checkout(numRegisters); // used to process orders	
		
		// create person generator
		produce = new PersonProducer(booths, checkout, 
				numOfTicksNextPerson, averageEateryTime, 
				averageCashierTime, leaveTime);	
		
		// add simulation objects to ClockListener to process events
		clk.add(produce);
		// add user defined number of booths
		for(int i = 0; i < numBooths; i++) {
			clk.add(booths.get(i));
		}
		// add a checkout with desired number of registers
		clk.add(checkout);
	}
	
	/**********************************************************************
	 * This method runs the simulation for the seconds passed. It runs from 
	 * 0 to ending time (passed value), all objects added to the clock 
	 * object process an event for each count.
	 * @param totalTime
	 * @return none
	 *********************************************************************/
	public void run(int totalTime) {
		clk.run(totalTime);
	}
	
	/**********************************************************************
	 * Sets the number of ticks between person production.
	 * @param rate ticks for a person generation
	 * @return none
	 *********************************************************************/
	public void setPersonRate(int rate) {
		numOfTicksNextPerson = rate;
	}
	
	/**********************************************************************
	 * Gets the number of ticks between person production.
	 * @param none 
	 * @return rate ticks for a person generation
	 *********************************************************************/
	public int getPersonRate() {
		return numOfTicksNextPerson;
	}
	
	/**********************************************************************
	 * Sets the average cashier time.
	 * @param time 
	 * @return none
	 *********************************************************************/
	public void setCashierTime(int time) {
		averageCashierTime = time;
	}
	
	/**********************************************************************
	 * Gets the average cashier time.
	 * @param none
	 * @return average cashier time
	 *********************************************************************/
	public int getCashierTime() {
		return averageCashierTime;
	}
	
	/**********************************************************************
	 * Sets the total time in seconds.
	 * @param time 
	 * @return none
	 *********************************************************************/
	public void setTotalTime(int time) {
		totalTime = time;
	}
	
	/**********************************************************************
	 * Gets the total time in seconds.
	 * @param none
	 * @return average cashier time
	 *********************************************************************/
	public int getTotalTime() {
		return totalTime;
	}
	
	/**********************************************************************
	 * Sets the average eatery time for a person.
	 * @param time 
	 * @return none
	 *********************************************************************/
	public void setEateryTime(int time) {
		averageEateryTime = time;
	}
	
	/**********************************************************************
	 * Gets the average eatery time for a person.
	 * @param none
	 * @return average eatery time
	 *********************************************************************/
	public int getEateryTime() {
		return averageEateryTime;
	}
	
	/**********************************************************************
	 * Sets the leave time for a person.
	 * @param time 
	 * @return none
	 *********************************************************************/
	public void setLeaveTime(int time) {
		leaveTime = time;
	}
	
	/**********************************************************************
	 * Gets the leave time for a person.
	 * @param none
	 * @return leave time
	 *********************************************************************/
	public int getLeaveTime() {
		return leaveTime;
	}
	
	/**********************************************************************
	 * Sets the number of food booths.
	 * @param n number of booths 
	 * @return none
	 *********************************************************************/
	public void setNumEatery(int n) {
		numBooths = n;
	}
	
	/**********************************************************************
	 * Gets the number of food booths.
	 * @param none
	 * @return number of food booths
	 *********************************************************************/
	public int getNumEatery() {
		return numBooths;
	}
	
	/**********************************************************************
	 * Sets the number of registers in checkout.
	 * @param n number of registers 
	 * @return none
	 *********************************************************************/
	public void setNumRegisters(int n) {
		numRegisters = n;
	}
	
	/**********************************************************************
	 * Gets the number of registers in checkout.
	 * @param none
	 * @return number of registers
	 *********************************************************************/
	public int getNumRegisters() {
		return numRegisters;
	}
	
	/**********************************************************************
	 * Gets the total number of people that completed a transaction.
	 * @param none
	 * @return total number of people that completed a transaction
	 *********************************************************************/
	public int getTotalTransactions() {
		return checkout.getThroughPut();
	}
	
	/**********************************************************************
	 * Gets the total number of people that left the booth lines and 
	 * checkout line.
	 * @param none
	 * @return total number of people that left the booth lines and 
	 * checkout line
	 *********************************************************************/
	public int getTotalDeserters() {
		return getBoothDeserters() + checkout.getNumDeserters();
	}
	
	/**********************************************************************
	 * Gets the total number of people that left the checkout line.
	 * @param none
	 * @return total number of people that left the checkout line
	 *********************************************************************/
	public int getCheckoutDeserters() {
		return checkout.getNumDeserters();
	}
	
	/**********************************************************************
	 * Gets the total number of people that left booth lines.
	 * @param none
	 * @return total number of people that left booth lines
	 *********************************************************************/
	public int getBoothDeserters() {
		int total = 0;
		for(int i = 0; i < numBooths; i++) {
			total += booths.get(i).getNumDeserters();
		}
		
		return total;
	}
	
	/**********************************************************************
	 * Gets the total number of people still in booth lines.
	 * @param none
	 * @return total number of people still in booth lines
	 *********************************************************************/
	public int getBoothsLeftInLine() {
		int total = 0;
		for(int i = 0; i < numBooths; i++) {
			total += booths.get(i).getLeftInLine();
		}
		
		return total;
	}
	
	/**********************************************************************
	 * This method returns the average transaction time from start to
	 * finish.
	 * @param none
	 * @return average transaction time from start to finish
	 *********************************************************************/
	public int getAvgTimeTransaction() {
		return (int)checkout.getAvgTimeTransaction();
	}
	
	// This method returns the number of people that left checkout line 
	
	/**********************************************************************
	 * This method returns the max Q length of cashier line.
	 * @param none
	 * @return max Q length of cashier line
	 *********************************************************************/
	public int getMaxQCashier() {
		return checkout.getMaxQlength();
	}
	
	/**********************************************************************
	 * This method returns the number of regular people.
	 * @param none
	 * @return num regular people
	 *********************************************************************/
	public int getNumRegular() {
		return produce.getNumRegular();
	}
	
	/**********************************************************************
	 * This method returns the number of special needs people.
	 * @param none
	 * @return num special needs people
	 *********************************************************************/
	public int getNumSpecial() {
		return produce.getNumSpecial();
	}
	
	/**********************************************************************
	 * This method returns the number of limited time people.
	 * @param none
	 * @return num limited time people
	 *********************************************************************/
	public int getNumLimited() {
		return produce.getNumLimited();
	}
	
	/*// This method stops the simulation.
	public void stopSim() {
		clk.stop();
	}
	
	// This method resumes the simulation. 
	public void resumeSim() {
		clk.resume();
	}*/
		
	// notes: total people not 500 for all places, why? 
	public static void main (String[] args) {
			Sim s = new Sim();

			// run the simulation for the passed time
			s.run(totalTime);
			
			// print the results/statistics
			System.out.println("Throughput: " + 
					checkout.getThroughPut() + " people with Max = " 
					+ s.getTotalTime() / s.getPersonRate());
			System.out.println
					("Average time for a Person from start to finish: " + 
					(int)checkout.getAvgTimeTransaction() + " seconds");
			System.out.println("Number of people left booth lines: " + 
					s.getTotalDeserters() + " people");
			System.out.println("Number of people left checkout line: " + 
					checkout.getNumDeserters() + " people");
			System.out.println("People that are still in booth Q's: " + 
					s.getBoothsLeftInLine() + " people.");
			System.out.println("People that are still in the checkout Q: " + 
					checkout.getLeftInLine() + " people.");
			System.out.println("Max Q length cashier line: " + 
								checkout.getMaxQlength() + " people.");
			// TODO add max Q for a booth
			// TODO add types of people stats

		}
	}
