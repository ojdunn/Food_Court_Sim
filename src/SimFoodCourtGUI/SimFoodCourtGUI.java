package SimFoodCourtGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import SimFoodCourt.*; // sim logic package

/**************************************************************************
 * SimFoodCourtGUI class: This class creates the GUI for the simulated food 
 * court.
 * 
 * @author Owen Dunn and Josh Stewart
 * @version 4/18/2017, Project 4, CIS 163
 *************************************************************************/
public class SimFoodCourtGUI extends JPanel {
	/** Simulation logic using SimFoodCourt package */
	private Sim s;
	/** top-level frame object */
	private JFrame frame;
	/** panels for user input, buttons, output, sim control, sim
	 * modification */
	private JPanel inputPanel, buttonPanel, outputPanel, simControlPanel,
		modifySimPanel;
	/** labels for user input, output results */
	private JLabel inputLabel, secondsToNextLabel, lineLabel, 
		avgSecondsCashierLabel, totalTimeLabel, avgSecondsEateryLabel, 
		secondsBeforeLeaveLabel, numEateriesLabel, outputLabel, lineLabel2,
		throughputLabel, throughputResultLabel, AvgBuyTimeLabel,
		AvgBuyTimeResultLabel, desertersLabel, desertersResultLabel,
		maxQCashierLabel, maxQCashierResultLabel, regularLabel, 
		regularResult, limitedLabel, limitedResult, specialLabel,
		specialResult, registersLabel;
	/** Text Fields to select sim variables */
	private JTextField personRate, cashierTime, totalTime, eateryTime,
		leaveTime, numEatery, numRegisters;
	/** JButtons for some sim actions: start, quit */
	private JButton startButton, quitButton;
	/** listener for JButton and JTextField events */
	private Listener listener;
	/** keeps track if sim has initially run */
	private boolean simRan = false;
	
	public SimFoodCourtGUI() {
		 // create listener for keyboard and button events
        listener = new Listener();
		
		// create top-level frame object
		renderFrame();
		
		// create panel to hold sim control panel
		renderControlPanel();
		
		// create input panel
		renderInputPanel();
		
		// create button panel (to start/quit sim)
		// set up listener for board grid panel
		//frame.addListener(listener);
		renderButtonPanel();
		
		// create output panel (to display sim results)
		renderOutputPanel();
		
		frame.add(simControlPanel, BorderLayout.SOUTH);
		frame.pack();
	}
	
	private void renderFrame() {
		frame = new JFrame("Food Court Simulation");
		frame.setLayout(new BorderLayout());
		//frame.setBackground(Color.lightGray); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setPreferredSize(new Dimension(600,600));
		//frame.pack();
		frame.setVisible(true);	
	}
	
	private void renderControlPanel() {
		simControlPanel = new JPanel();
		simControlPanel.setLayout(new BoxLayout(simControlPanel, 
								  BoxLayout.Y_AXIS));
		//simControlPanel.setPreferredSize(new Dimension(600,600));
		//simControlPanel.pack();	
	}
	
	private void renderInputPanel() {
		inputPanel = new JPanel();
		inputPanel.setOpaque(true);
		inputPanel.setLayout(new GridLayout(9, 2));
		
		// create JLabels for input directions
		inputLabel = new JLabel("Input Information");
		lineLabel = new JLabel
				("--------------------------------------------");
		secondsToNextLabel = new JLabel("Seconds to the Next Person");
		avgSecondsCashierLabel = new JLabel("Average Seconds per Cashier");
		totalTimeLabel = new JLabel("Total Time in Seconds");
		avgSecondsEateryLabel = new JLabel("Average Seconds per Eatery");
		secondsBeforeLeaveLabel = new JLabel("Seconds Before Person Leaves");
		numEateriesLabel = new JLabel("Number of Eateries");
		registersLabel = new JLabel("Number of Registers");
		
		// TODO: create input cells for user input
		personRate = new JTextField(6); // parameter is input max chars
		cashierTime = new JTextField(6);
		totalTime = new JTextField(6);
		eateryTime = new JTextField(6);
		leaveTime = new JTextField(6);
		numEatery = new JTextField(6);
		numRegisters = new JTextField(6);
		
		// add labels and JTextField input cells to panel
		//-add text input after each matching label to render in proper order
		inputPanel.add(inputLabel); // add label
		inputPanel.add(lineLabel);
		inputPanel.add(secondsToNextLabel);
		inputPanel.add(personRate); // add matching text input field
		inputPanel.add(avgSecondsCashierLabel);
		inputPanel.add(cashierTime);
		inputPanel.add(totalTimeLabel);
		inputPanel.add(totalTime);
		inputPanel.add(avgSecondsEateryLabel);
		inputPanel.add(eateryTime);
		inputPanel.add(secondsBeforeLeaveLabel);
		inputPanel.add(leaveTime);
		inputPanel.add(numEateriesLabel);
		inputPanel.add(numEatery);
		inputPanel.add(registersLabel);
		inputPanel.add(numRegisters);
		
		// add JTextFields to action listener
		personRate.addActionListener(listener);
		cashierTime.addActionListener(listener);
		totalTime.addActionListener(listener);
		eateryTime.addActionListener(listener);
		leaveTime.addActionListener(listener);
		numEatery.addActionListener(listener);
		numRegisters.addActionListener(listener);
		
		// add input panel to top frame
		simControlPanel.add(inputPanel);
		frame.pack();
	}
	
	private void renderButtonPanel() {
		buttonPanel = new JPanel();
		startButton = new JButton("Start/Repeat Simulation");
		quitButton = new JButton("Quit Simulation");
		
		// add buttons to button panel
		// add buttons to action listener
		startButton.addActionListener(listener);
		buttonPanel.add(startButton);
		quitButton.addActionListener(listener);
		buttonPanel.add(quitButton);
		
		// add button panel to top frame
		simControlPanel.add(buttonPanel);
		frame.pack();
	}
	
	// TODO add max Q for a booth
	// TODO add types of people stats
	private void renderOutputPanel() {
		outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayout(8, 2));
		
		// create all JLabels
		outputLabel = new JLabel("Output Information");
		lineLabel2 = new JLabel
				("--------------------------------------------");
		throughputLabel = new JLabel("Throughput:");
		throughputResultLabel = new JLabel();
		AvgBuyTimeLabel = new JLabel("Average time for a Person from start to finish:");
		AvgBuyTimeResultLabel = new JLabel();
		desertersLabel = new JLabel("Number of people left line:");
		desertersResultLabel = new JLabel();
		maxQCashierLabel = new JLabel("Max Q length cashier line:");
		maxQCashierResultLabel = new JLabel();
		regularLabel = new JLabel("Regular Customers:");
		regularResult = new JLabel();
		limitedLabel = new JLabel("Limited Time Customers:");
		limitedResult = new JLabel();
		specialLabel = new JLabel("Special Needs Customers:");
		specialResult = new JLabel();
		
		// add JLabels to output panel
		outputPanel.add(outputLabel);
		outputPanel.add(lineLabel2);
		outputPanel.add(throughputLabel);
		outputPanel.add(throughputResultLabel);
		outputPanel.add(AvgBuyTimeLabel);
		outputPanel.add(AvgBuyTimeResultLabel);
		outputPanel.add(desertersLabel);
		outputPanel.add(desertersResultLabel);
		outputPanel.add(maxQCashierLabel);
		outputPanel.add(maxQCashierResultLabel);
		outputPanel.add(regularLabel);
		outputPanel.add(regularResult);
		outputPanel.add(limitedLabel);
		outputPanel.add(limitedResult);
		outputPanel.add(specialLabel);
		outputPanel.add(specialResult);
		
		// add output panel to top frame
		simControlPanel.add(outputPanel);
		frame.pack();
	}
	
	private void renderSim() {
		s = new Sim();
	}
	
	/**********************************************************************
	 * Listener class: This represents a listener for all on screen 
	 * GUI button pressing events. The start, quit actions are performed 
	 * when the respective buttons are pressed.
	 *********************************************************************/
	private class Listener implements ActionListener {
		/******************************************************************
		 * This method activates whenever a button on the GUI is pressed or
		 * return is pressed in a text field.
		 * @Override
		 *****************************************************************/
		public void actionPerformed(ActionEvent e) { 
			// button actions
			if(e.getSource() == startButton) {
				// start simulation
				// TODO make sure all user input set needed to start the sim
				if(simRan == true) { 
					renderSim(); // run a new sim
				}
				
				s.run(s.getTotalTime()); // run entire sim
				simRan = true;
				
				// display results on output panel
				throughputResultLabel.setText(s.getTotalTransactions() +
						" people with Max = " + 
						s.getTotalTime() / s.getPersonRate());
				AvgBuyTimeResultLabel.setText
					(s.getAvgTimeTransaction() + " seconds");
				desertersResultLabel.setText(s.getTotalDeserters() + " people");
				maxQCashierResultLabel.setText("" + s.getMaxQCashier());
				regularResult.setText("" + s.getNumRegular());
				limitedResult.setText("" + s.getNumLimited());
				specialResult.setText("" + s.getNumSpecial());
			}
			
			if(e.getSource() == quitButton) {
				// display results as they currently stand? or exit?
				// exit program
				frame.dispatchEvent
					(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));	
			}
			
			// text field actions
			if(e.getSource() == personRate) { 
				String text = personRate.getText();
				int rate = Integer.parseInt(text);
				if(rate > 0 && rate <= 100000) {
					s.setPersonRate(rate);
				}
				else {
					// TODO display instructions
				}
			}
			
			if(e.getSource() == cashierTime) {
				String text = cashierTime.getText();
				int time = Integer.parseInt(text);
				if(/*time instanceof Integer && */time > 0 && time <= 100000) {
					s.setCashierTime(time);
				}
				else {
					// TODO display instructions
				}			
			}
			
			if(e.getSource() == totalTime) {
				String text = totalTime.getText();
				int time = Integer.parseInt(text);
				if(time > 0 && time <= 100000) {
					s.setTotalTime(time);
				}
				else {
					// TODO display instructions
				}
			}
			
			if(e.getSource() == eateryTime) {
				String text = eateryTime.getText();
				int time = Integer.parseInt(text);
				if(time > 0 && time <= 100000) {
					s.setEateryTime(time);
				}
				else {
					// TODO display instructions
				}
			}
			
			if(e.getSource() == leaveTime) {
				String text = leaveTime.getText();
				int time = Integer.parseInt(text);
				if(time > 0 && time <= 100000) {
					s.setLeaveTime(time);
				}
				else {
					// TODO display instructions
				}
			}
			
			if(e.getSource() == numEatery) {
				String text = numEatery.getText();
				int n = Integer.parseInt(text);
				if(/*n instanceof Integer && */n > 0 && n <= 10) {
					s.setNumEatery(n);
				}
				else {
					// TODO display instructions
				}
			}
			if(e.getSource() == numRegisters) {
				String text = numRegisters.getText();
				int n = Integer.parseInt(text);
				if(/*n instanceof Integer && */n > 0 && n <= 10) {
					s.setNumRegisters(n);
				}
				else {
					// TODO display instructions
				}
			}
		}
	}
	
	public static void main(String[] arg) {
		SimFoodCourtGUI g = new SimFoodCourtGUI();
		g.renderSim();	
    }
}
