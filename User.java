
public class User {
	
	//instance variables - protected for access from sub class
	protected String userName;
	protected String password;
	
	//constructors
	public User(){
		
	}

	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	//getters for editing
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	//setters for editing user info
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
