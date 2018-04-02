package SimFoodCourt;

/**************************************************************************
 * Clock class: Used to process all events in the simulation.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class Clock {
	/** array of clock listeners */
	private ClockListener[] myListeners; // all have event(int) method
	/** number of clock listeners in array */
	private int numListeners;
	/** max number of clock listeners that can store in array */
	private int MAX = 100;
	/** records of time to stop and resume clock as changes are made in
	 * between to the simulation */
	private int currentTime = 0, endTime = 0;

	public Clock() {
		numListeners = 0;
		myListeners = new ClockListener[MAX];
	}

	/**********************************************************************
	 * Runs the simulation until the ending time.
	 * @param none
	 * @return none
	 *********************************************************************/
	public void run(int endingTime) {
		for (int currentTime = 0; currentTime <= endingTime; currentTime++) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(currentTime);
		}
	}

/*	public void stop() {
		boolean eventHappened = false;
		// suspend run of clock, allow changes to sim
		if(currentTime != 0 && endTime != 0) { // need this info to resume
			while(!eventHappened) {
				// trigger resume() once all changes made
				// if(eventHappeded) resume(); 
			}
		}
	}
	
	public void resume() {
		for (int i = currentTime; i <= endTime; currentTime++) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(currentTime);
		}
	}*/

	/**********************************************************************
	 * Adds a listener to the simulation.
	 * @param cl the clock listener
	 * @return none
	 *********************************************************************/
	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}

	/**********************************************************************
	 * Returns the clock listeners.
	 * @param none
	 * @return myListeners
	 *********************************************************************/
	public ClockListener[] getMyListeners() {
		return myListeners;
	}

	/**********************************************************************
	 * Sets the clock listeners.
	 * @param myListeners array of clock listeners
	 * @return none
	 *********************************************************************/
	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}

	/**********************************************************************
	 * Gets the number of clock listeners.
	 * @param none
	 * @return numListeners
	 *********************************************************************/
	public int getNumListeners() {
		return numListeners;
	}

	/**********************************************************************
	 * Sets the number of clock listeners.
	 * @param numListeners
	 * @return none
	 *********************************************************************/
	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	/**********************************************************************
	 * Gets the size of listeners array.
	 * @param none
	 * @return MAX max size of listeners array
	 *********************************************************************/
	public int getMAX() {
		return MAX;
	}
}