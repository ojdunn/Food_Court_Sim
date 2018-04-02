package prj5;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class VisualGUI extends JFrame implements ActionListener, Runnable{
	private boolean isRunning;
	private Sim world;
	JTextArea statsArea;
    JMenuBar Menu;
    JMenu fileMenu;
    JButton Regular, Special, Limited, Start, Stop, Eatery;
    JMenuItem clearItem;
    JMenuItem quitItem;
	
	 public static void main(String arg[]){
	        VisualGUI gui = new VisualGUI();
	        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        gui.setTitle("Checkout Simulation");
	        gui.setSize(600,600);
	        gui.pack();
	        gui.setVisible(true);
	    }
	 public VisualGUI(){

	        // simulation is turned off 
	        isRunning = false;

	        // create the lay out
	        setLayout(new GridBagLayout());
	        GridBagConstraints position = new GridBagConstraints();
	        // Place the simulation on the screen 
	        
	    
	        position.gridx = 0;
	        position.gridy = 1;
	        position.gridwidth = 6;           
	        world = new Sim();
	        add(world, position);
	        

	        // Place a label
	        position.gridx = 6;
	        position.gridy = 0;  
	        add(new JLabel("Live Stats"),position);

	        // Place stats area below the label
	        statsArea = new JTextArea(7,12);
	        statsArea.setBackground(Color.YELLOW);
	        position.gridx = 6;
	        position.gridy = 1;    
	        position.anchor = GridBagConstraints.PAGE_START;
	        add(statsArea, position);  
	       // statsArea.setText(world.getStats());
	        
	        position = new GridBagConstraints();
	        // FIX ME: place each button
	        Regular = new JButton( "Regular" );
	        Regular.setForeground(Color.GREEN);
	        position.gridx = 0;
	        position.gridy = 2;   
	        add(Regular, position);
	        
	        
	        Special = new JButton( "Special" );
	        Special.setForeground(Color.BLUE);
	        position.gridx = 1;
	        position.gridy = 2;   
	        add(Special, position);
	        
	        Limited = new JButton( "Limited" );
	        Limited.setForeground(Color.ORANGE);
	        position.gridx = 2;
	        position.gridy = 2;   
	        add(Limited, position);
	        
	        Start = new JButton( "Start" );
	        Start.setForeground(Color.BLACK);
	        position.gridx = 3;
	        position.gridy = 0;   
	        add(Start, position);
	        
	        Stop = new JButton( "Stop" );
	        Stop.setForeground(Color.BLACK);
	        position.gridx = 4;
	        position.gridy = 0;   
	        add(Stop, position);
	        
	        Eatery = new JButton( "Eatery" );
	        Eatery.setForeground(Color.MAGENTA);
	        position.gridx = 3;
	        position.gridy = 2;   
	        add(Eatery, position);




	        // FIX ME: add action listeners for each button
	        Regular.addActionListener(this);
	        Limited.addActionListener(this);
	        Special.addActionListener(this);
	        Start.addActionListener(this);
	        Stop.addActionListener(this);
	        
	        
	        Menu = new JMenuBar();
	        clearItem = new JMenuItem("Clear");
	        quitItem= new JMenuItem("Quit");
	        clearItem.addActionListener(this);
	        quitItem.addActionListener(this);
	        
	        fileMenu = new JMenu("file");
	        Menu.add(fileMenu);
	        fileMenu.add(clearItem);
	        fileMenu.add(quitItem);
	        setJMenuBar(Menu);
	        

	        // Advanced topic! this must be at the end of this method
	        // start the simulation in separate thread
//	        new Thread(this).start();
	    }
	 public void actionPerformed(ActionEvent e){

	        // FIX ME: exit application if QUIT menu item
	        if (e.getSource() == quitItem){
	            System.exit(1);
	        }
	        // FIX ME: set running variable to true if START button
	        if (e.getSource() == Start){
	            isRunning =true;
	        }

	        // FIX ME: set running variable to false if STOP button
	        if (e.getSource() == Stop){
	            isRunning =false;
	        }
	        // Afterwards, update display and statistics
//	        world.repaint();
//	        statsArea.setText(world.getStats());
	    }
	 public void run(){
	        try {

	            // run forever
	            while(true) {

	                // only update simulation if it is running
	             //   if (isRunning) {
	               //     world.oneStep();
	                 //   statsArea.setText(world.getStats());
	                

	                // pause between steps.  Otherwise, the simulation
	                // would move too quickly to see
	                Thread.sleep(50);
	            }
	        }
	        catch (InterruptedException ex) {
	        }
	    }    
}
