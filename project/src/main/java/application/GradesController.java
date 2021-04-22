package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class GradesController implements Initializable {
	
	@FXML
	private Text activeUser;
	@FXML
	private TextArea resultBox;
	@FXML
	private Button clearMap;
	@FXML
	private ComboBox<String> course1, course2, course3, course4, course5, course6, course7, course8;
	@FXML
	private TextField courseName1, courseName2, courseName3, courseName4, courseName5, courseName6, courseName7, courseName8;
	
	private boolean isLoggedIn = true;
	public static String loggedInUser = MainController.loggedInUser;
	private List<String> gradesList = new ArrayList<String>();
	private List<String> courseList = new ArrayList<>();
	private String[] gradeSplitter;												//denne var static
	UserProfile activeAccount = MainController.getActiveAccount();
//	private HashMap<String, String> activeAccountMap = new HashMap<String, String>();
	
	List<TextField> courseNameList = new ArrayList<>();                       //two variables used to loop over textfields/combobox values
	List<ComboBox<String>> courseGradeList = new ArrayList<>();
	Set<String> courseSet = new HashSet<String>();
	Collection<String> gradesSet = new ArrayList<String>();
	
	ObservableList<String> list = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");

	
	public void addFieldsToList() {
		activeAccount.courseObjects.clear();
		courseNameList.clear();
		courseGradeList.clear();
		gradesList.clear();
		courseList.clear();
		courseSet.clear();
		
		courseNameList.addAll(Arrays.asList(courseName1, courseName2, courseName3, courseName4, courseName5, courseName6, courseName7, courseName8));  //adds TextField to lists
		courseGradeList.addAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8));
		
		Set<String> courseSet = activeAccount.getUserGrades().keySet();
		Collection<String> gradesSet = activeAccount.getUserGrades().values();  //ny inne i if
		gradesList = new ArrayList<>(gradesSet);
		List<String> courseList = new ArrayList<>(courseSet);
		
		System.out.println("gradesList = " + gradesList);       //tester for liste med navn og hashmap(grades)
		
		for(int i = 0; i < courseList.size(); i++) {
			courseNameList.get(i).setText(courseList.get(i));  										//adds students courseNames to corresponding textfields
			courseGradeList.get(i).getSelectionModel().select(gradesList.get(i));					//adds students grades to corresponding comboBox
		} 
	}
	
	public void openHomeWindow(ActionEvent event) throws IOException {
		SaveHandler saveHandler = new SaveHandler();
		saveHandler.saveUserGrades("UserGrades", UserProfile.outerMap);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
		UserProfile.outerMap.put(activeAccount.getUsernameInput(), activeAccount.getUserGrades());
		System.out.println(activeAccount.getUsernameInput());
//		SaveHandler saveHandler = new SaveHandler();
//		try {
//			saveHandler.loadToOuterMap("UserGrades");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		addFieldsToList();	
		loggedInUser = MainController.loggedInUser;
		isLoggedIn = true;
		activeUser.setText("Bruker: " + activeAccount.getUsernameInput());
		

		course1.setItems(list);
		course2.setItems(list);
		course3.setItems(list);
		course4.setItems(list);
		course5.setItems(list); //adds character collection "A-F" to all fields
		course6.setItems(list);
		course7.setItems(list);
		course8.setItems(list);
		}	

	public void clearMap(ActionEvent event) throws IOException {
		System.out.println(UserProfile.outerMap.get(loggedInUser));
		activeAccount.getUserGrades().clear();
		UserProfile.outerMap.get(loggedInUser).clear(); //denne funker, og fjerner hashmapet til brukeren som er logget in
		FXMLLoader loader = new FXMLLoader(getClass().getResource("grades.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}
	
	public void addGrades(ActionEvent event) throws FileNotFoundException, IOException {
		SaveHandler saveHandler = new SaveHandler();
		activeAccount.getUserGrades().clear();											//for å ikke få ekstra fag når man erstattet et TextField
		UserProfile.outerMap.get(loggedInUser).clear();
		System.out.println("Du er logget inn som " + loggedInUser);
		
		for(int i = 0; i < courseNameList.size(); i++) { 		//Make course-object
			if(!courseNameList.get(i).getText().isEmpty() && !courseGradeList.get(i).getValue().equals(null)) {
				Course courseObject = new Course(courseNameList.get(i).getText(), courseGradeList.get(i).getValue());
				activeAccount.courseObjects.add(courseObject);
				activeAccount.getUserGrades().put(courseObject.getCourseName(), courseObject.getGrade());
			}
		}
		System.out.println("You have " + (courseNameList.size() - gradesList.size()) + " empty fields.");
		activeAccount.getUserGrades().values().removeAll(Collections.singleton(null));										//Removes null objects
		UserProfile.outerMap.put(activeAccount.getUsernameInput(), activeAccount.getUserGrades());							//Put to outermap
		addFieldsToList();
		
		System.out.println("ActiveAccounts list of grades = " + gradesList);       //tester for liste med navn og hashmap(grades)
//		System.out.println(UserProfile.outerMap.get(loggedInUser));  //I want something like this.userProfile.getUserGrades
	
		List<String> values = new ArrayList<>(activeAccount.getUserGrades().values());
		Calculation calc = new Calculation();
		resultBox.setText(activeAccount.getUsernameInput() + " has an average grade score of " + calc.Calculate(values) + "\nYou can add: " + (courseNameList.size() - gradesList.size()) + " more courses.");
		
//		System.out.println(UserProfile.outerMap.toString());
//		System.out.println("UserProfile.outerMap.get(loggedInUser)         " + UserProfile.outerMap.get(loggedInUser));

		saveHandler.saveUserGrades("UserGrades", UserProfile.outerMap);
		
	}

	
	public static void main(String[] args) {

	}
}
