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
	public static String loggedInUser;
	
	@FXML
	public Button loginButton, registerButton;
	@FXML
	private TextField usernameInput, yearInput;	
	@FXML	
	private AnchorPane rootPane;
	@FXML
	private Text loggedInText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(loggedInUser != null) {
			loggedInText.setText("Velkommen " + loggedInUser);
			usernameInput.setText(loggedInUser);
		}
	
	
		SaveHandler saveHandler = new SaveHandler();
		try {
			saveHandler.loadToOuterMap("UserGrades");
			System.out.println(UserProfile.outerMap.toString());
//			System.out.println(UserProfile.userProfiles);
			for(int i = 0; i<UserProfile.userProfiles.size(); i++) {
				UserProfile userProfile = (UserProfile) UserProfile.userProfiles.get(i);
				System.out.println(userProfile.getUsernameInput() + " har hashmap: " + userProfile.getUserGrades() + ". født i " + userProfile.getYearInput());
			}

		} catch
		(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void verifyLoginCredentials(ActionEvent event) throws IOException {
		int year = Integer.parseInt(yearInput.getText());
		String username = usernameInput.getText();
		isLoggedIn = false;
		BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
		if(username.isBlank()) {
			throw new IllegalArgumentException("Invalid login credentials!");
		}
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			line = line.split(";")[0];
//			System.out.println(line);  prints all registrererd users
			if(line.equals(username)){
				isLoggedIn = true;
				loggedInUser = username;
				loggedInText.setText("Velkommen " + loggedInUser);
			}
		}
		if (!isLoggedIn) {
			loggedInText.setText("User doesn't exist.");
		}
	}	
	
	public void registerUser(ActionEvent event) throws IOException {
		int year = Integer.parseInt(yearInput.getText());
		String username = usernameInput.getText();
		BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			line = line.split(";")[0];
			if(line.equals(username)){
				
				loggedInText.setText("User already exists");
				throw new IllegalArgumentException("User already exists");
			}
		}
		
		UserProfile.registerUser(username, year, new HashMap<String, String>(), false);
		loggedInText.setText("Velkommen " + username);
		isLoggedIn = true;
	}

	public void openGradesWindow(ActionEvent event) throws IOException {  //good
		if(isLoggedIn) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("grades.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
		}else {
			loggedInText.setText("Log in first");
		}
	}
	
	public void openAvgWindow(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("average.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}
	
	public void openSettings(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node)	 event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}
	
	public Text getLoggedInText() {
		return loggedInText;
	}

	public void setLoggedInText(Text isLoggedIn) {
		this.loggedInText = isLoggedIn;
	}
	
}
