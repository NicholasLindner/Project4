package seatingPackage;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window {
	
	public static class TwoNames{
		
		//INSTANCE VARIABLES
		
		String name1;
		String name2;
		
		//CONSTRUCTOR
		public TwoNames(String n1, String n2) {
			name1=n1;
			name2=n2;
		}
		
	}
	
	public static class MoveInfo {
		
		//INSTANCE VARIABLES
		String name;
		int emptySeatRow;
		int emptySeatCol;
		
		//CONSTRUCTOR
		public MoveInfo(String name, int r, int c) {
			this.name = name;
			emptySeatRow = r;
			emptySeatCol = c;
		}
		
	}
	
	/*
	 * This method creates a message.
	 */
	public static void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/*
	 * This method creates a panel which has a label, text field, and 
	 * an OK option. It returns the text that the user typed in when 
	 * they press the OK button. It returns null otherwise (this would
	 * only happen when the user clicks the x button at the top right
	 * to exit the game). 
	 */
	public String getUserInput(String text, String title){
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel(text);
		String[] options = {"OK"};
		JTextField txt = new JTextField(10);
		panel.add(lbl);
		panel.add(txt);
		int selectedOption = JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options , options[0]);

		if(selectedOption == 0)
		    return txt.getText();
		return null;
	}
	
	/*
	 * This method creates a panel that has a label, a text field, and an 
	 * OK option. This method returns the text that the user typed in when 
	 * they press the OK button. It returns null otherwise (this would
	 * only happen when the user clicks the x button at the top right
	 * to exit the game). 
	 */
	public static String showStudentDialog() {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel nameLbl = new JLabel("Student name:");
		JTextField nameField = new JTextField(10);
		
		panel.add(nameLbl);
		panel.add(nameField);

		
		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter Student Name", JOptionPane.NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options , options[0]);

		if(selectedOption == 0) {
		
		    return nameField.getText();

		}
		return null;
	}
	
	/*
	 * This method creates a panel that has two labels, two text fields, and an 
	 * OK option. This method returns the two names that the user typed in (as
	 * a TwoNames object) when the OK button is pressed. It returns null otherwise
	 * (this would only happen when the user clicks the x button at the top right
	 * to exit the game). 
	 */
	public static TwoNames showSwapDialog() {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel nameLbl = new JLabel("First student's name:");
		JLabel nameLbl2 = new JLabel("Second student's name: ");
		JTextField nameField = new JTextField(10);
		JTextField name2Field = new JTextField(10);

		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLbl);
		panel.add(nameField);
		panel.add(nameLbl2);
		panel.add(name2Field);

		
		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Swap Students", JOptionPane.NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options , options[0]);

		if(selectedOption == 0) {
		
		    return new TwoNames(nameField.getText(), name2Field.getText());

		}
		return null;

	}
	
	/*
	 * This method creates a panel that has a label, a text field, and an 
	 * OK option. This method returns the text that the user typed in when 
	 * they press the OK button. It returns null otherwise (this would
	 * only happen when the user clicks the x button at the top right
	 * to exit the game). 
	 */
	public static String showLoadDialog() {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel nameLbl = new JLabel("Student names (Seperate names with commas):");
		JTextField nameField = new JTextField(10);
		
		panel.add(nameLbl);
		panel.add(nameField);

		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Enter The Student's Names", JOptionPane.NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options , options[0]);

		if(selectedOption == 0) {
		    return nameField.getText();
		}
		return null;

	}
	
	/*
	 * This method creates a panel that has a 3 labels, 3 text fields, and an 
	 * OK option. This method returns the text that the user typed in when 
	 * they press the OK button (as a MoveInfo object). It returns null otherwise (this would
	 * only happen when the user clicks the x button at the top right
	 * to exit the game). 
	 */
	public static MoveInfo showMoveDialog() {
		String[] options = {"OK"};
		JPanel panel = new JPanel();
		JLabel nameLbl = new JLabel("Student name:");
		JTextField nameField = new JTextField(10);
		JLabel rowLbl = new JLabel("Empty seat row (zero-based):");
		JTextField rowField = new JTextField(2);
		JLabel colLbl = new JLabel("Empty seat column (zero-based): ");
		JTextField colField = new JTextField(2);

		panel.setLayout(new GridLayout(3,2));
		panel.add(nameLbl);
		panel.add(nameField);
		panel.add(rowLbl);
		panel.add(rowField);
		panel.add(colLbl);
		panel.add(colField);

		
		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Move Student to Empty Seat", JOptionPane.NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options , options[0]);

		if(selectedOption == 0) {
		
			try {
				int row = Integer.parseInt(rowField.getText());
				int col = Integer.parseInt(colField.getText());
			    return new MoveInfo(nameField.getText(), row, col);
			}
			catch (Exception e) {
				return null;
			}
		}
		return null;

	}
}