import java.util.ArrayList;

public interface AdminMethods {
	
	//abstract methods to be implemented in the Admin class

	public abstract void delete(Course c);
	
	public abstract void edit(Course c);
	
	public abstract void display(String courseID);
	
	public abstract void reg(String fullName, String courseID);
	
	public abstract void viewAll();
	
	public abstract void viewFull();
	
	public abstract void sortCourses();
	
	public abstract void logIn() throws Exception;
	
	public abstract void exit();
	
	
}
