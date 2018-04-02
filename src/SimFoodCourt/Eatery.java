package SimFoodCourt;

//import java.util.ArrayList;

/**************************************************************************
 * Eatery class: This class represents a specific place to order food. 
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Eatery implements ClockListener { // includes event(int) 
	/** represents a line of people for a specific eatery/booth */
//	private ArrayList<Person> Q = new ArrayList<Person>();
	/** represents a line of people for a specific eatery/booth (linked
	 * list version) */
	private LinkedListQPerson Q = new LinkedListQPerson();
	/** time until next person is added to eatery Q or moves to checkout */
	private int timeOfNextEvent = 0;
	/** record of largest size of the queue */
	private int maxQlength = 0;
	/** this is the person at the Eatery and last added to checkout Q */
	private Person person = null, last = null; 
	/** number of orders */
	private int numOrders = 0;
	/** number of customers that left without ordering */
	private int deserters = 0;
	
	/**********************************************************************
	 * Adds a person to the booth
	 * @param Person person
	 *********************************************************************/
	public void add(Person person)
	{
		Q.enQ(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	/**********************************************************************
	 * Runs the eatery event.
	 * @param tick current second
	 * @return none
	 * @Override
	 *********************************************************************/
	public void event(int tick) {
		// person leaves booth for cashier Q after next person makes order
		if(tick >= timeOfNextEvent) { // don't add same person >1
			// make sure not null or the same object added last before 
			// adding to Q
			// Notice the delay that takes place here 
			if(person != null) { 
				// take this person to the checkout station
				if(last == null || !last.equals(person)) {
					// update for wait time in checkout Q
					person.setTickTime(tick + 1);
					person.getCheckout().add(person); 
					last = person; // prevent duplicate in checkout Q
					numOrders++; // an order is completed (go to checkout)
				}
			}
			
			if(Q.get(0) != null) {
				person = Q.deQ(); // do not send this person as of yet
				// time to order food + 1 (represents other delay?)
				// add 1, as eatery time may be zero
				timeOfNextEvent = tick + (int)(person.getEateryTime()+1);
			}
		}
		
		// person leaves Q if too much time passes
		if(Q.size() > 0) {
			Person temp; // used for loop
			
			for(int i = 0; i < Q.size(); i++) {
				temp = Q.get(i);
				// leave booth Q if too much time passes
				if(temp != null && tick >= temp.getTickTime() + 
						temp.getLeaveTime()) {
					Q.remove(i); // leaves the booth Q
					deserters++; // update count of customers that left Q
					// removing a cell decreases the size by 1
					// -set index back 1 to check next cell after next 
					// loop iteration
					if(Q.size() > 0)
						i--;
					else
						break; // leave loop
				}
			}
		}
	}	
		
	/**********************************************************************
	 * Gets the number left in line.
	 * @param none
	 * @return number left in line
	 *********************************************************************/
	public int getLeftInLine() {
		return Q.size();
	}
	
	/**********************************************************************
	 * Gets the max size of the line.
	 * @param none
	 * @return max size of line
	 *********************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/**********************************************************************
	 * Gets the max size of the line.
	 * @param none
	 * @return max size of line
	 *********************************************************************/
	public int getThroughPut() {
		return numOrders;
	}
	
	/**********************************************************************
	 * Gets the number of people that left the line.
	 * @param none
	 * @return number of people that left the line
	 *********************************************************************/
	public int getNumDeserters() {
		return deserters;
	}
}