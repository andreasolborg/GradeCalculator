package rester;

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





public class GradesController1 implements Initializable {
	
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
	
	public boolean isLoggedIn = true;
	public static String loggedInUser = MainController.loggedInUser;
	static List<String> gradesList = new ArrayList<String>();
	List<String> courseList = new ArrayList<>();
	static String[] gradeSplitter;
	UserProfile activeAccount = MainController.getActiveAccount();
	HashMap<String, String> activeAccountMap = new HashMap<String, String>();
	
	List<TextField> courseNameList = new ArrayList<>();                       //two variables used to loop over textfields/combobox values
	List<ComboBox<String>> courseGradeList = new ArrayList<>();
	
	ObservableList<String> list = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");

	
	public void addFieldsToList() {
		activeAccount.courseObjects.clear();
		courseNameList.clear();
		courseGradeList.clear();	
		courseNameList.addAll(Arrays.asList(courseName1, courseName2, courseName3, courseName4, courseName5, courseName6, courseName7, courseName8));  //adds TextField to lists
		courseGradeList.addAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8));
		
		Set<String> courseSet = activeAccount.userGrades.keySet();
		Collection<String> gradesSet = activeAccount.userGrades.values();  //ny inne i if
		
		gradesList = new ArrayList<>(gradesSet);
		List<String> courseList = new ArrayList<>(courseSet);
		
		System.out.println("gradesList = " + gradesList);       //tester for liste med navn og hashmap(grades)
		
		for(int i = 0; i < courseList.size(); i++) {
			courseNameList.get(i).setText(courseList.get(i));  										//adds students courseNames to corresponding textfields
			courseGradeList.get(i).getSelectionModel().select(gradesList.get(i));					//adds students grades to corresponding comboBox
		} 
	}
	
	public void openHomeWindow(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		Parent mainRoot = loader.load();
		Scene mainScene = new Scene(mainRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
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
		

//		courseNameList.add(courseName2);
//		courseNameList.add(courseName3);
//		courseNameList.add(courseName4);
//		courseNameList.add(courseName5);
//		courseNameList.add(courseName6);
//		courseNameList.add(courseName7);
//		courseNameList.add(courseName8);
//		courseGradeList.add(course1);
//		courseGradeList.add(course2);
//		courseGradeList.add(course3);
//		courseGradeList.add(course4);
//		courseGradeList.add(course5);
//		courseGradeList.add(course6);
//		courseGradeList.add(course7);
//		courseGradeList.add(course8);
		
//		System.out.println("courseName List: [TextField[id=courseName1, styleClass=text-input text-field], TextField[id=courseName2, styleClass=text-input text-field] " + courseNameList);	
		course1.setItems(list);
		course2.setItems(list);
		course3.setItems(list);
		course4.setItems(list);
		course5.setItems(list); //adds character collection "A-F" to all fields
		course6.setItems(list);
		course7.setItems(list);
		course8.setItems(list);
				
			
		}	
//		System.out.println(gradesSet);
//		System.out.println(courseList);	
		
	
	
	public void clearMap(ActionEvent event) throws IOException {
		System.out.println(UserProfile.outerMap.get(loggedInUser));
		activeAccount.userGrades.clear();
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
		
		
		UserProfile.outerMap.get(loggedInUser).clear();
		System.out.println("Du er logget inn som " + loggedInUser);
		
		//Make course-object
		for(int i = 0; i < courseNameList.size(); i++) {
			if(!courseNameList.get(i).getText().isEmpty() && !courseGradeList.get(i).getValue().equals(null)) {
				System.out.println(courseGradeList.get(i));
				Course courseObject = new Course(courseNameList.get(i).getText(), courseGradeList.get(i).getValue());								//adds students courseNames to corresponding textfields
				activeAccount.courseObjects.add(courseObject);
				activeAccount.userGrades.put(courseObject.getCourseName(), courseObject.getGrade());
			} else {
				System.out.println("You have " + i + " empty fields.");
			}
			
		} 
		
		
		
		
		
//		String str0 = (String)course1.getValue();
//		String str1 = (String)course2.getValue();
//		String str2 = (String)course3.getValue();
//		String str3 = (String)course4.getValue();
//		String str4 = (String)course5.getValue();
//		String str5 = (String)course6.getValue();
//		String str6 = (String)course7.getValue();
//		String str7 = (String)course8.getValue();
//		
//		
//		
//		activeAccount.userGrades.put(courseName1.getText(), str0);
//		activeAccount.userGrades.put(courseName2.getText(), str1);
//		activeAccount.userGrades.put(courseName3.getText(), str2);
//		activeAccount.userGrades.put(courseName4.getText(), str3);
//		activeAccount.userGrades.put(courseName5.getText(), str4);
//		activeAccount.userGrades.put(courseName6.getText(), str5);
//		activeAccount.userGrades.put(courseName7.getText(), str6);
//		activeAccount.userGrades.put(courseName8.getText(), str7);
		activeAccount.userGrades.values().removeAll(Collections.singleton(null));
		
		UserProfile.outerMap.put(activeAccount.getUsernameInput(), activeAccount.userGrades);
		
		
//		UserProfile.outerMap.get(loggedInUser).put(courseName1.getText(), str0);
//		UserProfile.outerMap.get(loggedInUser).put(courseName2.getText(), str1);
//		UserProfile.outerMap.get(loggedInUser).put(courseName3.getText(), str2);
//		UserProfile.outerMap.get(loggedInUser).put(courseName4.getText(), str3);
//		UserProfile.outerMap.get(loggedInUser).put(courseName5.getText(), str4);
//		UserProfile.outerMap.get(loggedInUser).put(courseName6.getText(), str5);
//		UserProfile.outerMap.get(loggedInUser).put(courseName7.getText(), str6);
//		UserProfile.outerMap.get(loggedInUser).put(courseName8.getText(), str7);
		
//		UserProfile.outerMap.get(loggedInUser).values().removeAll(Collections.singleton(null));   //removes null's as appears when blank spaces
		addFieldsToList();
		
		System.out.println("Initialized gradesList upon entering scene (updates after re-login) = " + gradesList);       //tester for liste med navn og hashmap(grades)
		System.out.println(UserProfile.outerMap.get(loggedInUser));  //I want something like this.userProfile.getUserGrades
		
		
//		if(activeAccount.userGrades.isEmpty()) {
//			System.out.println("AAAAAAAAAAAAAAAAAAAaA");
//			UserProfile.outerMap.get(loggedInUser).put("", "F");
//		}
		
//		List<String> values = new ArrayList<>(UserProfile.outerMap.get(loggedInUser).values());                 //LoggedInUsers grades as list
		List<String> values = new ArrayList<>(activeAccount.userGrades.values());
		
		
		Calculation calc = new Calculation();
		resultBox.setText(loggedInUser + " has an average grade score of " + calc.Calculate(values));
		
//		System.out.println(UserProfile.outerMap.toString());
//		System.out.println("UserProfile.outerMap.get(loggedInUser)         " + UserProfile.outerMap.get(loggedInUser));

		saveHandler.saveUserGrades("UserGrades");
		
	}

	
	public static void main(String[] args) {

	}
}
package rester;

public class GradesController1 {

}
