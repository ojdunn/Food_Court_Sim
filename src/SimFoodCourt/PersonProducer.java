package SimFoodCourt;

import java.util.Random;
import java.util.ArrayList;

/**************************************************************************
 * PersonProducer class: This class creates a new person for a specific
 * eatery every given number of ticks. A random number generator produces a
 * number for each person to determine how long they take to order food.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class PersonProducer implements ClockListener {
	/** time of next person generation */
	private int nextPerson = 0;
	/** person */
	private Person person;
	/** number of seconds before a new person arrives in food court */
	private int numOfTicksNextPerson;
	/** avg time person takes to order */
	private int averageEateryTime;
	/** avg time cashier takes */
	private int averageCashierTime;
	/** time before person leaves */
	private int leaveTime;
	/** checkout location */
	private Checkout checkout;
	/** number of booths */
	//private int numBooths = 5;
	/** food court booths */
	private ArrayList<Eatery> booths;
	/** random number generator */
	private Random r = new Random();
	/** num of each type of person */
	private int nSpecial, nLimited, nRegular;

	public PersonProducer(ArrayList<Eatery> booths, Checkout checkout, 
						  int numOfTicksNextPerson, int averageEateryTime, 
						  int averageCashierTime, int leaveTime) {
		this.booths = booths;
		this.numOfTicksNextPerson = numOfTicksNextPerson; 
		this.averageEateryTime = averageEateryTime;
		this.averageCashierTime = averageCashierTime;
		this.leaveTime = leaveTime;
		this.checkout = checkout;
		r.setSeed(10);    // This will cause the same random numbers
	}
	
	/**********************************************************************
	 * Processes person generator event.
	 * @param tick current second
	 * @return none
	 * @Override
	 *********************************************************************/
	public void event(int tick) { 
		// add a new person once tick count reaches nextPerson value
		// -person added at count n * numOfTicksNextPerson, n=0,1,2,...
		if(nextPerson <= tick) { 
			nextPerson = tick + numOfTicksNextPerson;
			
			// determine person type
			int x = r.nextInt(10) + 1;
			if(x < 8) { // 70% chance
				person = new RegularPerson();
				nRegular++;
			}
			else if(x < 10) { // 20% chance
				person = new LimitedTimePerson();
				nLimited++;
			}
			else { // x is 10, 10% chance
				person = new SpecialNeedsPerson();
				nSpecial++;
			}
			
			//int rNumber=(int)(Math.random()*100); //(generate 0 to 100)

			// occasionally produces 0 results - make 1 instead?
			// -nextGau returns from 0 to 1 gaus. distribution
			person.setEateryTime(Math.max(0, 
					averageEateryTime*0.5*r.nextGaussian() + 
					averageEateryTime +.5));
			/*System.out.println(Math.max(0, 
					averageEateryTime*0.5*r.nextGaussian() + 
					averageEateryTime +.5));*/
			person.setCashiersTime(Math.max(0, 
					averageCashierTime*0.5*r.nextGaussian() + 
					averageCashierTime +.5));
			person.setLeaveTime(leaveTime);
			person.setTickTime(tick);
			person.setTickTimeStart(tick);
			
			// add person to a random booth
			booths.get(r.nextInt(booths.size())).add(person);
			
			// set location to go after order at booth complete
			person.setCheckout(checkout);
		}
	}
	
	/**********************************************************************
	 * This method returns the number of regular people.
	 * @param none
	 * @return num regular people
	 *********************************************************************/
	public int getNumRegular() {
		return nRegular;
	}
	
	/**********************************************************************
	 * This method returns the number of special needs people.
	 * @param none
	 * @return num special needs people
	 *********************************************************************/
	public int getNumSpecial() {
		return nSpecial;
	}
	
	/**********************************************************************
	 * This method returns the number of limited time people.
	 * @param none
	 * @return num limited time people
	 *********************************************************************/
	public int getNumLimited() {
		return nLimited;
	}
}
