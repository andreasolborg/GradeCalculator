package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable{

	public static boolean isLoggedIn;
	public static String loggedInUser;				//static pga sendern over til gradesController
	private UserProfile userProfile;
	private static UserProfile activeAccount;
	private String throwingText;
	
	@FXML
	public Button loginButton, registerButton;
	@FXML
	private TextField usernameInput;	
	@FXML	
	private AnchorPane rootPane;
	@FXML
	private Text loggedInText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SaveHandler saveHandler = new SaveHandler();
		try {
			saveHandler.loadUserData("UserData", UserProfile.Users);
			System.out.println(UserProfile.Users.toString());
			saveHandler.loadToOuterMap("UserGrades", UserProfile.outerMap);
		} catch (Exception e) {
			loggedInText.setText("Something went wrong loading data!");
		}
		
		if(loggedInUser != null) {
			loggedInText.setText("Velkommen " + loggedInUser);
			usernameInput.setText(loggedInUser);
		}

			System.out.println(UserProfile.userProfiles);
			for(int i = 0; i<UserProfile.userProfiles.size(); i++) {
				UserProfile userProfile = (UserProfile) UserProfile.userProfiles.get(i);
				System.out.println(userProfile.getUsernameInput() + " har hashmap: " + userProfile.getUserGrades());
			}
	}
	
	public void verifyLoginCredentials(ActionEvent event) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
		isLoggedIn = false;
		String username = usernameInput.getText();
		
		try {
		if(username.isBlank()) {
			throw new IllegalArgumentException("Invalid login credentials!");
		}
		for(int i = 0; i<UserProfile.userProfiles.size(); i++) {																//loop through list of userProfile objects. Finds if it is registrered and sets user active.
			UserProfile userProfile = (UserProfile) UserProfile.userProfiles.get(i);
			userProfile.setLoggedIn(false);
			if(userProfile.getUsernameInput().equals(username)) {
				userProfile.setLoggedIn(true);
				System.out.println(userProfile.getUsernameInput() + "logged in:   " + userProfile.isLoggedIn());
				isLoggedIn = true;
				loggedInUser = username;
				loggedInText.setText("Velkommen " + loggedInUser);
				setActiveAccount(userProfile);
			}
		}
	}catch(Exception e) {
		loggedInText.setText("Inputfield cannot be blank. ");
	}
		
		
		
//		for (String line = br.readLine(); line != null; line = br.readLine()) {
//			line = line.split(";")[0];
//			System.out.println(line);  prints all registrererd users
//			if(line.equals(username)){
//				isLoggedIn = true;
//				loggedInUser = username;
//				loggedInText.setText("Velkommen " + loggedInUser);
//			}
//		}

		
//		if (!isLoggedIn) {
//			loggedInText.setText("User doesn't exist.");
//		}
	}	
	
	public void registerUser(ActionEvent event) throws IOException {
		try {
		String username = usernameInput.getText();
		BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
//			line = line.split(";")[0];
			if(line.equals(username)){
				throw new IllegalArgumentException("User already exists");
			}
		} br.close();
		UserProfile.registerUser(username, new HashMap<String, String>(), false);   //makes new UserProfile
		
		loggedInText.setText("Registration of " + username + " completed.");
		
		}catch(Exception e) {
			loggedInText.setText("User already exists, or field is blank.");
		}
	}

	public void openGradesWindow(ActionEvent event) throws IOException {  //good
		try {
		if(getActiveAccount().isLoggedIn()) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("grades.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
		}
		} catch(Exception e){
			loggedInText.setText("Log in first!");
		}
	}

	
	public Text getLoggedInText() {
		return loggedInText;
	}

	public void setLoggedInText(Text loggedInText) {
		this.loggedInText = loggedInText;
	}


	public static UserProfile getActiveAccount() {
		return activeAccount;
	}


	public static void setActiveAccount(UserProfile activeAccount) {
		MainController.activeAccount = activeAccount;
	}
	
}
