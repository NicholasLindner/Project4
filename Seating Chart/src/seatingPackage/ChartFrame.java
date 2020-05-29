package seatingPackage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChartFrame extends JFrame{
	
	//INSTANCE VARIABLES
	private Chart chart;
	private JButton removeBtn;
	private JButton addBtn;
	private JButton swapBtn;
	private JButton loadBtn;
	private JButton moveBtn;
	private JLabel[][] seatGrid= new JLabel[5][5];
	
	/*
	* CONSTRUCTOR: This constructor initializes the game
	* instance variable and sets up the entire GUI frame
	* using a grid layout (it also uses a forloop to help set up the 
	* seating chart itself). It also updates the game.
	*/
	public ChartFrame(Chart chart) {
		this.chart = chart;
		
		for(int r  =  0; r < seatGrid.length; r++) {
			for(int c  =  0; c < seatGrid[r].length; c++) {
				JLabel label = new JLabel();
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				seatGrid[r][c] = label;
			}
		}
		
		removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onRemoveClicked();
			}
		});
		
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onAddClicked();
			}
		});
		
		swapBtn = new JButton("Swap");
		swapBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSwapClicked();
			}
		});
		
		loadBtn = new JButton("Load");
		loadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoadClicked();
			}
		});
		
		moveBtn = new JButton("Move");
		moveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onMoveClicked();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setLayout(new GridLayout(7, 5));
		
		for(int r  =  0; r < seatGrid.length; r++) {
			for(int c  =  0; c < seatGrid[r].length; c++) {
				panel.add(seatGrid[r][c]);
			}
		}
		
		
		for(int i = 0; i < seatGrid[0].length; i++) {
			panel.add(Box.createHorizontalGlue());
		}

		panel.add(removeBtn);
		panel.add(addBtn);
		panel.add(loadBtn);
		panel.add(swapBtn);
		panel.add(moveBtn);

		add(panel);
		setSize(500, 400);
		setVisible(true);
		setTitle("Seating Chart");
		update();

	}
	
	/*
	 * This method shows a window when the add button is 
	 * clicked which allows the user to type in a name.
	 * If the given name is not empty and there are no 
	 * seats available, a message saying there are no seats available 
	 * shows up and the it updates. If given name is not empty and there are  
	 * seats still available, it only updates. If the given name is 
	 * empty then nothing happens.
	 */
	private void onAddClicked() {
		
		String givenName = Window.showStudentDialog();
		
		if (givenName != null) {
			
			if(!chart.addStudentName(givenName)) {
				Window.msg("There are no seats available.");
			}
			update();
		}
		
	}
	
	/*
	 * This method gets called when the remove
	 * button is clicked. The user can type in a name.
	 * If that given name is empty, nothing 
	 * happens. Otherwise it checks if the given name
	 * exists. If it does not then a message saying 
	 * that this student was not found shows up and the
	 * game updates. If the name is found the game just updates.
	 */
	private void onRemoveClicked() {
		
		String givenName = Window.showStudentDialog();
		
		if (givenName != null) {
			if(!chart.removeStudentName(givenName)) {
				Window.msg("This student was not found.");
			}
			update();
		}

	}
	
	/*
	 * This method gets called when the swap
	 * button is clicked. The user can type in two names.
	 * If that given names are empty, nothing 
	 * happens. Otherwise it checks if the given names
	 * exist. If either names are not found, then a message saying 
	 * that at least one student was not found shows up and the
	 * game updates. If the name is found the game just updates.
	 */
	private void onSwapClicked() {

		Window.TwoNames givenNames = Window.showSwapDialog();
		
		if (givenNames != null) {
			if(!chart.swapStudentName(givenNames.name1, givenNames.name2)) {
				Window.msg("At least one student was not found.");
			}
			update();
		}
	}
	
	/*
	 * This method gets called when the load
	 * button is clicked. The user can type in a list of names.
	 * If that given names are empty, the chart clears.
	 * Otherwise it separates each name using comas and puts 
	 * them in an array. Then the method goes through that array,
	 * trims the names, and adds them each into the chart. Finally 
	 * it updates.
	 * 
	 */
	private void onLoadClicked() {
		String allGivenNames = Window.showLoadDialog();
		
		if(allGivenNames != null) {
			chart.clearNames();
		
			String names[] = allGivenNames.split(",");
			for (String name : names) {
				name = name.trim();
				chart.addStudentName(name);
			}
			
			update();
		}
	}
	
	/*
	 * This method gets called when the move
	 * button is clicked. The user can type in a name.
	 * If the move info is empty, nothing 
	 * happens. Otherwise it checks if the student with that
	 * name can be moved. If they can't be moved, then a message saying 
	 * that the student could not be moved because either there is no
	 * student with that name of the seat is not empty, and the
	 * game updates. If the name is found the game just updates.
	 */
	private void onMoveClicked() {
		Window.MoveInfo moveInfo = Window.showMoveDialog();
		
		if (moveInfo != null) {
			if(!chart.moveStudentToEmptySeat(moveInfo.name, moveInfo.emptySeatRow, moveInfo.emptySeatCol)) {
				Window.msg("Unable to move student. Either there is no student with this name or the other seat is not empty.");
			}
			update();
		}
	}
	
	/*
	 * This method updates the game by going through
	 * the seatGrid and setting each element to the 
	 * text of the corresponding spot in the Chart
	 * called chart.
	 */
	private void update() {
		
		for(int r = 0; r < seatGrid.length; r++) {
			for(int c =0; c< seatGrid[0].length; c++) {
				seatGrid[r][c].setText(chart.getStudentName(r, c));
			}
		}
	}
	
}
