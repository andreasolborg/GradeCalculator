package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProfile {
	
	private String usernameInput;
	static String fileName = "saver.txt";
	private boolean isLoggedIn = false;
	
	static HashMap<String, HashMap<String, String>> outerMap = new HashMap<>();   //2d hashmap
	static HashMap<String, String> Users = new HashMap<>();
	HashMap<String, String> userGrades;
	public static List<Object> userProfiles = new ArrayList<Object>();
	public List<Object> courseObjects;
	

	public UserProfile(String usernameInput, HashMap<String, String> userGrades, boolean isLoggedIn) {
		validUsername(usernameInput);
		this.userGrades = new HashMap<String, String>();
		this.usernameInput = usernameInput;
		this.setLoggedIn(false);
		this.courseObjects = new ArrayList<Object>(); 
		userProfiles.add(this);
	}
	
	public UserProfile() {  //empty constructor
		
	}

	public static void registerUser(String user, HashMap<String, String> userGrades, boolean isLoggedIn) throws FileNotFoundException {               //lager ny UserProfile, legger til i HashMap "Users"	
		SaveHandler saveHandler = new SaveHandler();
		UserProfile userProfile = new UserProfile(user, new HashMap<String, String>(), false);	
		System.out.println(userProfile.userGrades);
		Users.put(userProfile.getUsernameInput(), "");
		outerMap.put(userProfile.getUsernameInput(), userProfile.userGrades);
		saveHandler.saveUserData("UserData", Users);	
		saveHandler.saveUserGrades("UserGrades", outerMap);
		//
	}
	
	
	public void validUsername(String user) {	     //valideringsmetode
		if (user.matches(".*[0-9]") || user.isEmpty() || user.isBlank()) {
			throw new IllegalArgumentException("Username cant contain numbers, and cant be bla");
			}
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getUsernameInput();
	}
	
	//Setters og Getters
	public String getUsernameInput() {
		return usernameInput;
	}

	public void setUsernameInput(String usernameInput) {
		validUsername(usernameInput);
		this.usernameInput = usernameInput;
	}

	public HashMap<String, String> getUserGrades() {
		return userGrades;
	}
	

	void setUserGrades(HashMap<String, String> userGrades) {
		this.userGrades = userGrades;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public static void main(String[] args) {
		UserProfile userProfile = new UserProfile("2", new HashMap<String, String>(), false);
		
	}
}
