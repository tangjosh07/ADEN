import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import myfileio.MyFileIO;

public class ListController {
	private ArrayList<Employee> employees;
	private static final boolean DEBUG = false;
	private MyFileIO fileIO = new MyFileIO();
	
	public ListController () {
		employees = new ArrayList<Employee>();
		initializeData();
	}

	// adds a new employee
	String addEmployee(String firstName, String lastName, String SSN, String age, String pronouns, String salary, String years, String dept) {
		// TODO #7
		// controller needs to convert the numeric string data -
		// use Integer.parseInt() or Double.parseDouble() for ints and doubles
		// years should be int, salary should be a double....
		// Then, add the new employee to the employees list!
		// for initial demo and debugging, set DEBUG to true;
		String msg = "";
		if (firstName.equals("") || lastName.equals("")||SSN.equals("")||age.equals("")||salary.equals("")||years.equals("")||dept.equals("")) {
			msg =  "Required Field Empty";
		}
		try {
			Integer.parseInt(age);
		} catch (Exception e) {
			return "Age must be an integer";
		}
		try {
			Integer.parseInt(years);
		} catch (Exception e) {
			return "Years must be an integer";
		}
		try {
			Double.parseDouble(salary);
		} catch (Exception e) {
			return "Salary must be a double";
		}
		if (!firstName.matches("[a-zA-Z-]{1,}")) {
			msg = "First Name not a valid entry";
		}
		else if (!lastName.matches("[a-zA-Z-]{1,}")) {
			msg = "Last Name not a valid entry";
		}
		else if (!SSN.matches("\\d{3}-\\d{2}-\\d{4}")) {
			msg = "SSN invalid";
		}
		else if (duplicateSSN(SSN)) {
			msg = "Duplicate SSN";
		}
		else if (Integer.parseInt(age) < 16) {
			msg = "Age too small";
		}
		else if (Integer.parseInt(age) > 65) {
			System.out.println("Old person");
			Employee employee = new Employee(firstName,lastName,SSN,Integer.parseInt(age),pronouns,Double.parseDouble(salary),Integer.parseInt(years),dept);
			employees.add(employee);
		}
		else if (Integer.parseInt(age) - Integer.parseInt(years) < 16) {
			msg = "Anomaly between age and years of experience";
		}
		else if (Double.parseDouble(salary) < 30000) {
			msg = "Salary too small";
		}
		else if (Double.parseDouble(salary) > 30000000) {
			msg = "Salary too large";
		}
		else {
			Employee employee = new Employee(firstName,lastName,SSN,Integer.parseInt(age),pronouns,Double.parseDouble(salary),Integer.parseInt(years),dept);
			employees.add(employee);
		}
		return msg;
	}
	
	
	// returns a string array of the employee information to be viewed
	public String[] getEmployeeDataStr() {
		// temporary placeholder for compilation reasons - will remove later...
		String[] strings = new String[employees.size()];
		for (int i = 0; i < employees.size(); i++) {
			strings[i] = employees.get(i).toString();
		}
		return strings;
		
	}
	
	public void saveEmployeeData() {
		sortByID();
		fileIO.createEmptyFile("empDB.dat");
		File f = fileIO.getFileHandle("empDB.dat");
		if (fileIO.checkTextFile(f, false) == fileIO.WRITE_EXISTS || fileIO.checkTextFile(f, false) == fileIO.FILE_OK) {
			BufferedWriter br = fileIO.openBufferedWriter(f);
			for (Employee e: employees) {
				String write = e.getEmpID()+"|,|"+e.getFirstName()+"|,|"+e.getLastName()+"|,|"+e.getSSN()+"|,|"+e.getAge()+"|,|"+e.getPronouns()+"|,|"+String.format("%.2f", ((long) (e.getSalary()*100)/100.0))+"|,|"+e.getYears()+"|,|"+e.getDept();
				try {
					br.write(write + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			fileIO.closeFile(br);
		}
	}
	
	public void initializeData() {
		Employee.resetStaticID();
		File f = fileIO.getFileHandle("empDB.dat");
		if (fileIO.checkTextFile(f,true) == fileIO.FILE_OK) {
			BufferedReader br = fileIO.openBufferedReader(f);
			String line = "";
			try {
				while (line != null) {
					line = br.readLine();
					if (line == null) {
						break;
					}
					String[] splits = line.split("\\|,\\|");
					addEmployee(splits[1],splits[2],splits[3],splits[4],splits[5],splits[6],splits[7],splits[8]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileIO.closeFile(br);
		}
	}
	
	private boolean duplicateSSN(String SSN) {
		for (Employee e : employees) {
			if (e.getSSN().equals(SSN))
				return true;
		}
		return false;
	}
	
	public void sortByID() {
		Collections.sort(employees, new ByID());
	}
	public void sortByName() {
		Collections.sort(employees, new ByName());
	}
	public void sortBySalary() {
		Collections.sort(employees, new BySalary());
	}
	
	public int getNumEmployees() {
		return employees.size();
	}

	private class ByID implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Integer.compare(e1.getEmpID(),e2.getEmpID());
		}
	}
	private class BySalary implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			return Double.compare(e1.getSalary(), e2.getSalary());
		}
	}
	private class ByName implements Comparator<Employee> {
		public int compare(Employee e1, Employee e2) {
			int compare = e1.getLastName().compareTo(e2.getLastName());
			if (compare == 0) {
				return e1.getFirstName().compareTo(e2.getFirstName());
			}
			return compare;
		}
	}
}

 
