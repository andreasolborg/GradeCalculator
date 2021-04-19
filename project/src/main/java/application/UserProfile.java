package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProfile {
	
	private String usernameInput;
	private int yearInput;
	static String fileName = "saver.txt";
	private boolean isLoggedIn = false;
	
	static HashMap<String, HashMap<String, String>> outerMap = new HashMap<>();   //2d hashmap
	static HashMap<String, String> Users = new HashMap<>();
	public HashMap<String, String> userGrades;
	public static List<Object> userProfiles = new ArrayList<Object>();
	
	
	public static void makeGradeMap(HashMap userGrades) { //prøver lage metode slik at hvert student sine karakterer er et eget objekt. så må jeg endre hashmap til (username, object (der object er hashmap)))
		//discarded
	}

	public UserProfile(String usernameInput, HashMap<String, String> userGrades, boolean isLoggedIn) {
		validUsername(usernameInput);
		this.userGrades = new HashMap<String, String>();
		this.usernameInput = usernameInput;
		this.setLoggedIn(false);
	}
	
	public UserProfile() {  //empty constructor
		
	}
	

	
	public static void registerUser(String user, HashMap<String, String> userGrades, boolean isLoggedIn) throws FileNotFoundException {               //lager ny UserProfile, legger til i HashMap "Users"	
		SaveHandler saveHandler = new SaveHandler();
		UserProfile userProfile = new UserProfile(user, new HashMap<String, String>(), false);	
		userProfile.userGrades.put("Legg inn fag", "A");
		System.out.println(userProfile.userGrades);
		Users.put(userProfile.getUsernameInput(), "");
		outerMap.put(userProfile.getUsernameInput(), userProfile.userGrades);
		saveHandler.saveUserData("UserData");	
		saveHandler.saveUserGrades("UserGrades");
		//
	}
	
	

	public void validUsername(String user) {	     //valideringsmetode
		if (user.isEmpty() || user.isBlank()) {
			throw new IllegalArgumentException("Year must be >= 0 and user !blank");
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
		this.usernameInput = usernameInput;
	}

	public int getYearInput() {
		return yearInput;
	}

	public void setYearInput(int yearInput) {
		this.yearInput = yearInput;
	}
	
	public HashMap<String, String> getUserGrades() {
		return userGrades;
	}

	public void setUserGrades(HashMap<String, String> userGrades) {
		this.userGrades = userGrades;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
