import java.util.ArrayList;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Course implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final long serialVersionUID2 = -4662155762998056667L;
	//instance variables
	String Course_Name;
	String Course_ID;
	int Maximum_Students;
	int Current_Students;
	ArrayList<String> List_Of_Names;
	String Course_Instructor;
	int Course_Section_Number;
	String Course_Location;
	
	//constructor
	public Course(){
		
	}
	
	//constructor for student course registration
	public Course(String Course_Name, int Course_Section_Number){
		this.Course_Name = Course_Name;
		this.Course_Section_Number = Course_Section_Number;
	}
	
	//comprehensive constructor
	public Course(String course_Name, String course_ID, int maximum_Students, int current_Students,
			ArrayList<String> list_Of_Names, String course_Instructor, int course_Section_Number, String course_Location) {

		Course_Name = course_Name;
		Course_ID = course_ID;
		Maximum_Students = maximum_Students;
		Current_Students = current_Students;
		List_Of_Names = list_Of_Names;
		Course_Instructor = course_Instructor;
		Course_Section_Number = course_Section_Number;
		Course_Location = course_Location;
	}

	//setters to edit course information
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}

	public void setCourse_ID(String course_ID) {
		Course_ID = course_ID;
	}

	public void setMaximum_Students(int maximum_Students) {
		Maximum_Students = maximum_Students;
	}

	public void setCurrent_Students(int current_Students) {
		Current_Students = current_Students;
	}

	public void setList_Of_Names(ArrayList<String> list_Of_Names) {
		List_Of_Names = list_Of_Names;
	}

	public void setCourse_Instructor(String course_Instructor) {
		Course_Instructor = course_Instructor;
	}

	public void setCourse_Section_Number(int course_Section_Number) {
		Course_Section_Number = course_Section_Number;
	}

	public void setCourse_Location(String course_Location) {
		Course_Location = course_Location;
	}

	//getter for editing course info
	public String getCourse_Name() {
		return Course_Name;
	}

	public String getCourse_ID() {
		return Course_ID;
	}

	public int getMaximum_Students() {
		return Maximum_Students;
	}

	public int getCurrent_Students() {
		return Current_Students;
	}

	public ArrayList<String> getList_Of_Names() {
		return List_Of_Names;
	}

	public String getCourse_Instructor() {
		return Course_Instructor;
	}

	public int getCourse_Section_Number() {
		return Course_Section_Number;
	}

	public String getCourse_Location() {
		return Course_Location;
	}
	
}
