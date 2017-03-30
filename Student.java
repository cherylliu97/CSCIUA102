import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StuMethods{
	
	private String first;
	private String last;
	private String full = first + " " + last;
	private ArrayList<Course> registered = new ArrayList<>();
	private ArrayList<String> userNames = new ArrayList<>();
	private ArrayList<String> pass = new ArrayList<>();
	
	/**
	 * Constructors
	 */
	public Student(ArrayList<Course> registered){
		super();
		this.registered = registered;
		userNames.add(getUserName());
		pass.add(getPassword());
	}
	//for admin edit purpose
	public Student(String first, String last, ArrayList<Course> registered){
		this.first = first;
		this.last = last;
		this.registered = registered;
		userNames.add(getUserName());
		pass.add(getPassword());
	}
	//all instance variables included
	public Student(String first, String last, String full, String userName, String password, ArrayList<Course> registered){
		super(userName, password);
		this.first = first;
		this.last = last;
		this.full = full;
		this.registered = registered;
		userNames.add(getUserName());
		pass.add(getPassword());
	}
	
	/**
	 * Getters and setters
	 */
	//setters
	public void setFirst(String first) {
		this.first = first;
	}
	public void setLast(String last) {
		this.last = last;
	}
	//getters
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
	public ArrayList<Course> getReg(){
		return registered;
	}
	public String getFull(){
		return full;
	}
	
	/**
	 * Methods to call
	 */
	//view all courses
	public void viewAll(){
		System.out.println("Here are all courses currently in the data base: ");
		for(Course c : Admin.getCourses()){
			System.out.println("Course name: " + c.getCourse_Name());
			System.out.println("Course ID: " + c.getCourse_ID());
			System.out.println("Maximum allowed: " + c.getMaximum_Students());
			System.out.println("Current number of registration: " + c.getCurrent_Students());
			System.out.println();
		}
		System.out.println();
		return;
	}
	//view all non-full courses
	public void viewNotFull(){
		System.out.println("Here are all the courses that are not full:");
		for(Course c : Admin.getCourses()){
			if(c.getCurrent_Students() < c.getMaximum_Students()){
				System.out.println("Course name: " + c.getCourse_Name());
				System.out.println("Course ID: " + c.getCourse_ID());
				System.out.println("Currently enrolled: " + c.getCurrent_Students());
				System.out.println();
			}
		}
		System.out.println();
		return;
	}
	//register for a course
	public void reg(){
		//importing scanner for registration
		Scanner regInput = new Scanner (System.in);
		
		//instance variables to add
		String courseName;
		String secStr;
		String fullName;
		
		//prompt user registration input
		System.out.println("Please enter FULL course name: ");
		courseName = regInput.nextLine();
		System.out.println("Please enter section number: ");
		secStr = regInput.nextLine();
		System.out.println("Please enter your FULL name (first last): ");
		fullName = regInput.nextLine();
		
		//searching through database
		int courseSec = Integer.parseInt(secStr);
		for(Course c : Admin.getCourses()){
			//if the course is entered correctly
			if(c.getCourse_Name().equals(courseName)){
				//if section number is correct
				if(c.getCourse_Section_Number() == courseSec && fullName.equals(full)){
					//register success if the course is not full
					if(c.getCurrent_Students() < c.getMaximum_Students()){
						c.Current_Students++;
						c.getList_Of_Names().add(fullName);
						registered.add(c);
						System.out.println("You have successfully registered for " + c.getCourse_Name() + ", section " + c.getCourse_Section_Number());
						return;
					}
					else{
						System.out.println("The course has reached its maximum registration.");
						return;
					}
				}
				else{
					System.out.println("Data entry not found.");
					return;
				}
			}
		}
		System.out.println();
		return;
	}
	//withdraw from a course
	public void withdraw(){
		//prompt user input
		System.out.println("Enter the name of course you would like to be withdrawn from: ");
		Scanner w = new Scanner(System.in);
		String courseName = w.nextLine();
			for(Course c : registered){
				if(c.getCourse_Name().equals(courseName)){
					c.Current_Students--;
					c.getList_Of_Names().remove("" + getFirst() + getLast());
					registered.remove(c);
					System.out.println("You have successfully withdrawed from " + c.getCourse_Name());
					return;
				}
				else{
					System.out.println("Your input is not valid.");
				}
			}
		}
	//view all currently registered courses
	public void viewReg(){
		if(registered.size() > 0){
			System.out.println("Here are the courses you're currently registered in: ");
			for(Course c : registered){
				System.out.println(c.getCourse_ID() + " " + c.getCourse_Name());
			}
		}
		if(registered.size() == 0){
			System.out.println("You're not enrolled in any courses.");
		}
		
		System.out.println();
	}
	
	public void logIn() throws Exception{
				
		//instance variables to be initialized
		String userName;
		String password;
		String fullName;
		String courseID;
		String courseName;
		
		//variables to activate user choices
		int option = 0;
		
		//displaying interface
		System.out.println("Welcome to student course registration system! Please log in to continue.");
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		userName = input.nextLine();
		System.out.println("Please enter your password: ");
		password = input.nextLine();
		if(userNames.contains(userName)){
			if(userNames.indexOf(userName) == pass.indexOf(password)){
			System.out.println("Login successful!");
			System.out.println("You are logged in as:" + getFull());
			while(option != 6){
			System.out.println();
			System.out.println("Please enter the function you would like to proceed with.");
			System.out.println("1. View all courses available");
			System.out.println("2. View all courses that are not full");
			System.out.println("3. Register for a course");
			System.out.println("4. Withdraw from a course");
			System.out.println("5. View courses you're currently enrolled in");
			System.out.println("6. Exit");
		
			option = input.nextInt();
			//options to choose from
			switch(option){
			case 1: viewAll();
					break;
			case 2: viewNotFull();
					break;
			case 3: reg();
					break;
			case 4: withdraw();
					break;
			case 5: viewReg();
					break;
			case 6: exit();
					break;
			}
		}
	}
		}
		else{
			System.out.println("Your username or password is not correct. Please try again.");
		}
			
		
		//getting user input
	}
	
	//exit
	public static void exit(){
		System.out.println("You have successfully logged out.");
		return;
	}
	
	//the method to check if it's an integer
		public boolean containsInteger(String s){
			for(int i = 0; i < s.length(); i++){
				if(Character.isDigit(s.charAt(i))){
					return true;
				}
			}
			return false;
		}
	//
}
