package seatingPackage;

public class Chart {
	
	private static class Location {
		
		//INSTANCE VARIABLES
		int row;
		int col;
		
		//CONSTRUCTOR
		public Location(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	//INSTANCE VARIABLES
	private String[][] names = new String [5][5];
	
	//GETTERS
	public String getStudentName(int row, int col) {return names[row][col];}
	
	//CONSTRUCTOR: This constructor clears the names in the chart.
	public Chart() {
		clearNames();
	}
	
	/*
	 * This method moves the specified student to the specified empty seat
	 * if possible. If the location is empty or if the there is no
	 * student by the passed in name exists it returns false.
	 * Otherwise, the student is moved to the seat, the
	 * student's previous seat becomes empty and true is returned.
	 */
	public boolean moveStudentToEmptySeat(String name, int row, int col) {
		name = trimLastName(name);
		
		Location loc = findLocation(name);
		if (loc == null) {
			return false;
		}
		
		if (!names[row][col].isEmpty()) {
			return false;
		}
		
		names[row][col] = name;
		names[loc.row][loc.col] = "";
		return true;
	}

	/*
	 * This method adds the first name of the 
	 * student passed in by the parameter to
	 * the first empty seat if possible. If there 
	 * are no empty seats it returns false. Otherwise 
	 * it adds the student to the first empty seat and 
	 * returns true.
	 */
	public boolean addStudentName(String name) {
		name = trimLastName(name);
		
		Location loc = findLocation("");
		if(loc == null) {
			return false;
		}
		names[loc.row][loc.col] = name;
		return true;
	}
	
	/*
	 * This method removes the student specified in the parameter.
	 * If the student is not found it returns false.
	 * Otherwise it removes that student and returns true.
	 */
	public boolean removeStudentName(String name) {				
		name = trimLastName(name);
		
		Location location = findLocation(name);
		if(location == null) {
			return false;
		}
		names[location.row][location.col] = "";
		return true;
	}
	
	/*
	 * This method swaps the two students given in the parameter.
	 * If either of the students are not found it returns false.
	 * Otherwise it swaps them and returns true.
	 */
	public boolean swapStudentName(String name1, String name2) {
		name1 = trimLastName(name1);
		name2 = trimLastName(name2);
		
		Location location1 = findLocation(name1);
		Location location2 = findLocation(name2);

		if(location1 == null ||location2==null) {
			return false;
		}
		names[location1.row][location1.col] = name2;
		names[location2.row][location2.col] = name1;
		return true;
		
	}

	/*
	 * This method finds the location of the 
	 * student given in the parameter and 
	 * returns it. If the student is not
	 * found then null is returned.
	 */
	private Location findLocation(String name) {
		
		for(int c = 0; c < names[0].length; c++) {
			for(int r = 0; r < names.length;r++) {
				if(names[r][c].equalsIgnoreCase(name)) {
					return new Location(r, c);
 				}
					
			}
		}
		return null;
	}
	
	/*
	 * This methods clears the names of all the students in
	 * the names double array (essentially wiping them off
	 * the chart).
	 */
	public void clearNames() {
		
		for(int r = 0; r < names.length; r++) {
			for(int c = 0; c < names[r].length; c++) {
				
				names[r][c] = new String();
				
			}
		}
	}
	
	/*
	 * This method removes the last name of a
	 * student and returns the first name only.
	 */
	private String trimLastName(String name) {
		name = name.trim();
		String pieces[] = name.split(" ");
		if (pieces.length < 2)
			return name;
		
		pieces[pieces.length - 1] = pieces[pieces.length - 1].charAt(0) + ".";
		return String.join(" ", pieces);
	}

}
