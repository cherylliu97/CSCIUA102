import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Admin extends User implements AdminMethods {

	// the functions of the Admin class is based on an AL of courses
	// and an AL of students
	private static ArrayList<Course> courses = new ArrayList<>();
	private ArrayList<Student> students = new ArrayList<>();

	// constructor
	public Admin() {
		userName = "Admin";
		password = "Admin001";
	}

	public Admin(ArrayList<Course> c) {
		userName = "Admin";
		password = "Admin001";
		this.courses = c;
	}

	// getter for course array list for students
	public static ArrayList<Course> getCourses() {
		return courses;
	}

	// create a course
	public Course create(String name, String ID, int max, int now, ArrayList<String> names, String instructor, int sec,
			String loc) {
		Course c = new Course(name, ID, max, now, names, instructor, sec, loc);
		courses.add(c);
		return c;
	}

	// delete a course
	public void delete(Course c) {
		courses.remove(c);
	}

	// edit a course
	public void edit(Course c) {
		// scanner to enable editing
		Scanner edit = new Scanner(System.in);

		// instance variables to edit
		String name;
		String ID;
		int max;
		int now;
		ArrayList<String> names = new ArrayList<>();
		String stuName;
		String instructor;
		int section;
		String location;

		// editing course name
		System.out.println("Please enter new course name:");
		name = edit.nextLine();
		c.setCourse_Name(name);

		// editing course ID
		System.out.println("Please enter new course ID");
		ID = edit.nextLine();
		c.setCourse_ID(ID);

		// editing maximum capacity
		System.out.println("Please enter your desired maximum number of the student:");
		max = edit.nextInt();
		c.setMaximum_Students(max);

		// editing current amount of student
		System.out.println("Please enter current number of students in this class:");
		now = edit.nextInt();
		c.setCurrent_Students(now);

		// editing registered student names
		System.out.println("Please enter a list of student names in this class (i.e.: John Doe,Mary Smith): ");
		stuName = edit.next();
		String[] temp = stuName.split(",");
		for (int i = 0; i < temp.length; i++) {
			names.add(temp[i]);
		}
		c.setList_Of_Names(names);

		// editing instructor name
		System.out.println("Please enter the name of new instructor:");
		instructor = edit.nextLine();
		c.setCourse_Instructor(instructor);

		// editing section number
		System.out.println("Please enter new section number: ");
		section = edit.nextInt();
		c.setCourse_Section_Number(section);

		// editing location
		System.out.println("Please enter the new course location: ");
		location = edit.nextLine();
		c.setCourse_Location(location);
	}

	// display information
	public void display(String courseID) {
		int count = 0;
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourse_ID().equalsIgnoreCase(courseID)) {
				count++;
				System.out.println("Course name: " + courses.get(i).getCourse_Name());
				System.out.println("Course ID: " + courses.get(i).getCourse_ID());
				System.out.println("Maximum allowed: " + courses.get(i).getMaximum_Students());
				System.out.println("Current number of registration: " + courses.get(i).getCurrent_Students());
				System.out.println("Students enrolled: " + courses.get(i).getList_Of_Names().toString());
				System.out.println("Section number: " + courses.get(i).getCourse_Section_Number());
				System.out.println("Instructor: " + courses.get(i).getCourse_Instructor());
				System.out.println("Location: " + courses.get(i).getCourse_Location());
			}
		}
		// when entry cannot be found in the list of course names
		if (count == 0) {
			System.out.println("Entry not found.");
		}
	}

	// register student
	public void reg(String fullName, String courseID) {
		// create new Student object
		String f = fullName.split(" ")[0];
		String l = fullName.split(" ")[fullName.split(" ").length - 1];
		ArrayList<Course> stuC = new ArrayList<>();
		Student stu = new Student(f, l, stuC);

		// check if course if valid
		int count = 0;
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourse_ID().equalsIgnoreCase(courseID)) {
				count++;
				courses.get(i).getList_Of_Names().add(fullName);
				courses.get(i).Current_Students++;
				stuC.add(courses.get(i));
				students.add(stu);
				System.out.println("You have registered " + fullName + " into " + courses.get(i).getCourse_Name());
			}
		}
		if (count == 0) {
			System.out.println("Course entry not found.");
		}
	}

	// view all courses
	public void viewAll() {
		System.out.println("Here are all courses currently in the data base: ");
		for (Course c : courses) {
			System.out.println("Course name: " + c.getCourse_Name());
			System.out.println("Course ID: " + c.getCourse_ID());
			System.out.println("Maximum allowed: " + c.getMaximum_Students());
			System.out.println("Current number of registration: " + c.getCurrent_Students());
			System.out.println();
		}
	}

	// view full courses
	public void viewFull() {
		int count = 0;
		System.out.println("Here are the courses that are currently full:");
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getMaximum_Students() == courses.get(i).getCurrent_Students()) {
				count++;
				System.out.println(courses.get(i).getCourse_ID() + " " + courses.get(i).getCourse_Name());
			}
		}
		if (count == 0) {
			System.out.println("................");
			System.out.println("There are currently no full courses in the data base.");
		}
	}

	// write to a file the list of full courses
	public void writeFull() throws Exception {
		// create a text file named "full" in the directory
		PrintWriter full = new PrintWriter("text/full.txt");

		// check all courses in the array list
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCurrent_Students() == courses.get(i).getMaximum_Students()) {
				full.println(courses.get(i).getCourse_Name());
				System.out.println("Course " + courses.get(i).getCourse_Name() + " has been added to file.");
			}
			full.close();
		}
	}

	// view names of student being registered in a specific course
	public void viewStuName(Course c) {
		System.out.println("Here are the students registered in " + c.getCourse_Name() + ": ");
		for (int i = 0; i < c.List_Of_Names.size(); i++) {
			System.out.println(c.List_Of_Names.get(i));
		}
	}

	// view the list of courses a given student is being registered on
	public void viewStuCourse(String name) {
//		// int count = 0;
//		for (int i = 0; i < students.size(); i++) {
//			if (students.get(i).getFull().equals(name)) {
//				for (int j = 0; i < students.get(i).getReg().size(); j++) {
//					System.out.println(students.get(i).getReg().get(j).getCourse_Name());
//				}
//			}
//		}
		for(Course c : courses){
			if(c.getList_Of_Names().contains(name)){
				System.out.println(c.getCourse_Name());
			}
		}

	}

	// sort courses based on current number of registers
	@SuppressWarnings("unchecked")
	public void sortCourses() {
		ArrayList<Integer> temp = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			temp.add(courses.get(i).getCurrent_Students());
		}
		Collections.sort(temp);
		ArrayList<Course> sortedCourses = new ArrayList<>(courses.size());

		for (int i = 0; i < temp.size(); i++) {
			for (Course c : courses) {
				if (c.getCurrent_Students() == temp.get(i)) {
					sortedCourses.add(c);
				}
			}
		}
		Set<Course> sorted = new LinkedHashSet<>(sortedCourses);
		for (Course c : sorted) {
			System.out
					.println(c.getCourse_ID() + " " + c.getCourse_Name() + ", Section " + c.getCourse_Section_Number());
			System.out.println("Current enrollment: " + c.getCurrent_Students());
			System.out.println();
		}
	}

	// log in
	public void logIn() throws Exception {
		System.out.println("Welcome to Admin's course registration system! Please log in to continue.");

		// variables to enter when login
		String inputUserName;
		String inputPassword;

		// variables to enable choosing between options
		int option1 = 0;
		int option2 = 0;
		String function = "";
		int count = 0;

		// variables to input while operating
		String courseName = "";
		String courseID;
		String maxStr;
		String currStr;
		String instru;
		String secStr;

		// other variables
		int sec;
		String fullName;

		// prompt user input
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username:");
		inputUserName = input.nextLine();
		System.out.println("Please enter your password:");
		inputPassword = input.nextLine();

		// check if inputs match
		if (inputUserName.equals("Admin") && inputPassword.equals("Admin001")) {
			// show that login is successful
			System.out.println("Login successful!");
			System.out.println();
			System.out.println("Please enter the function you would like to proceed with: ");
			System.out.println("Choose either \'Course Management\' or \'Reports\' to begin");
			System.out.println("You can also enter 'Exit' to end the program.");
			function = input.nextLine();
			// display menu for the user
			if (function.equalsIgnoreCase("Course Management") || function.equalsIgnoreCase("cm")) {
				while (option1 != 6) {
					System.out.println();
					System.out.println("Here are what you can do under Course Management.");
					System.out.println("Please enter the number of function you would like to proceed with:");
					System.out.println("1. Create a new course");
					System.out.println("2. Delete a course");
					System.out.println("3. Edit a course");
					System.out.println("4. Display information for a given course");
					System.out.println("5. Register a student");
					System.out.println("6. Back to main menu");
					option1 = input.nextInt();
					// let the user choose what to proceed with by entering
					// numbers
					if (option1 == 1) {
						Scanner opt = new Scanner(System.in);
						System.out.println("What is the name of the course you would like to create?");
						courseName = opt.nextLine();
						System.out.println("What is the ID you would like to assign to your course?");
						courseID = opt.nextLine();
						System.out.println("What is the maximum capacity of this course?");
						maxStr = opt.nextLine();
						System.out.println("How many students are currently enrolled in this class?");
						currStr = opt.nextLine();
						System.out.println("Who is the instructor of this class?");
						instru = opt.nextLine();
						System.out.println("What is the section number of this course?");
						secStr = opt.nextLine();
						System.out.println("What is the location of the classroom?");
						String newLoc = opt.nextLine();
						ArrayList<String> initialName = new ArrayList<>();
						int maxInt = Integer.parseInt(maxStr);
						int actualCurr = Integer.parseInt(currStr);
						int newSec = Integer.parseInt(secStr);
						create(courseName, courseID, maxInt, actualCurr, initialName, instru, newSec, newLoc);
						System.out.println("You have successfully created course: " + courseName);
					} else if (option1 == 2) {
						System.out
								.println("Which course would you like to delete? Enter ID first, then section number.");
						courseID = input.next();
						sec = input.nextInt();
						for (Course c : courses) {
							if (c.getCourse_ID().equals(courseID) && c.getCourse_Section_Number() == sec) {
								delete(c);
							}
						}
					} else if (option1 == 3) {
						System.out.println("Which course would you like to edit? Enter course ID.");
						courseID = input.next();
						for (Course c : courses) {
							if (c.getCourse_ID().equals(courseID)) {
								System.out.println("You are editing: " + c.getCourse_Name());
								edit(c);
							}
						}
					} else if (option1 == 4) {
						System.out.println("Which course's information would you like to check? Enter course ID.");
						courseID = input.next();
						display(courseID);
					} else if (option1 == 5) {
						Scanner regInput = new Scanner(System.in);
						System.out.println("For whom would you like to register for a class? Enter student full name.");
						fullName = regInput.nextLine();
						System.out.println("And which class? Enter course ID.");
						courseID = regInput.nextLine();
						reg(fullName, courseID);
					} else if (option1 == 6) {
						logIn();
					}
				}
			} else if (function.equalsIgnoreCase("Reports") || function.equalsIgnoreCase("r")) {
				while (option2 != 7) {
					System.out.println();
					System.out.println("Here are what you can do under Reports.");
					System.out.println("Please enter the number of the function you would like to proceed with.");
					System.out.println("1. View all courses");
					System.out.println("2. View all courses that are full");
					System.out.println("3. Write to a file the list of full courses");
					System.out.println("4. View the names of students in a course");
					System.out.println("5. Check a student's all registered classes");
					System.out.println("6. Sort courses based on number of registered students");
					System.out.println("7. Back to main menu");
					option2 = input.nextInt();
					if (option2 == 1) {
						viewAll();
					} else if (option2 == 2) {
						viewFull();
					} else if (option2 == 3) {
						writeFull();
					} else if (option2 == 4) {
						System.out.println("Please enter the course ID you would like to check.");
						courseID = input.next();
						for (Course c : courses) {
							if (courseID.equals(c.getCourse_ID())) {
								viewStuName(c);
							}
						}
					} else if (option2 == 5) {
						Scanner stuCheck = new Scanner(System.in);
						System.out.println("Please enter the student name you would like to check.");
						fullName = stuCheck.nextLine();
						viewStuCourse(fullName);
					} else if (option2 == 6) {
						sortCourses();
						System.out.println(
								"Here are the list of courses sorted by the number of registered students, from the least to the most");
					} else if (option2 == 7) {
						System.out.print("We will go back to the main menu.");
						logIn();
					}
				}
			} else if (function.equalsIgnoreCase("exit")) {
				System.out.println("you have logged out.");
				System.exit(0);
			}
		}else {
			System.out.println("Incorrect username or password.");
			exit();
		}
	}
	// exit
	public void exit() {
		System.out.println("You have logged out.");
		System.exit(0);
	}
}