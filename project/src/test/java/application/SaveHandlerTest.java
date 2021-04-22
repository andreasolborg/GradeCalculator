package application;	

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveHandlerTest {
	
	HashMap<String, String> testSavingUserDataMap = new HashMap<String, String>(); 
	HashMap<String, String> testLoadingUserDataMap = new HashMap<String, String>(); 
	HashMap<String, HashMap<String, String>> testSaveOuterMap = new HashMap<String, HashMap<String, String>>();
	HashMap<String, HashMap<String, String>> testLoadOuterMap = new HashMap<String, HashMap<String, String>>();
//	private HashMap<String, String> userGrades;
	UserProfile userProfile1 = new UserProfile("Andy", new HashMap<String, String>(), false);  //lager to nye brukere som jeg skal teste
	UserProfile userProfile2 = new UserProfile("Johan", new HashMap<String, String>(), false);
	
	
	@BeforeEach
	public void setUp() throws Exception {
		testSaveOuterMap.clear();
		testLoadOuterMap.clear();
		testLoadingUserDataMap.clear();
		testSavingUserDataMap.clear();
		
		
		
//		System.out.println(testSaveOuterMap.toString());
		
		testSavingUserDataMap.put(userProfile1.getUsernameInput(), "");  //adding to User map. Which is a map containing usernames only. No values
		testSavingUserDataMap.put(userProfile2.getUsernameInput(), "");
		
	}
	
	@Test
	public void testSaveUserData() throws IOException {
		SaveHandler saveHandler = new SaveHandler();
		saveHandler.saveUserData("TestUserData", testSavingUserDataMap);
		saveHandler.loadUserData("TestUserData", testLoadingUserDataMap);
		Assertions.assertEquals(testLoadingUserDataMap, testSavingUserDataMap);
	}
	
	@Test
	public void testSaveUserGrades() throws Exception {
		SaveHandler saveHandler = new SaveHandler();
		
		Course course1 = new Course("TestCourse1", "A");
		Course course2 = new Course("TestCourse2", "A");		//creating Course objects
		Course course3 = new Course("TestCourse10", "B");
		Course course4 = new Course("TestCourse11", "B");
		
		userProfile1.userGrades.put(course1.getCourseName(), course1.getGrade());
		userProfile1.userGrades.put(course2.getCourseName(), course2.getGrade());    //adding the courses to the 2 profiles individual hashMap
		userProfile2.userGrades.put(course3.getCourseName(), course3.getGrade());
		userProfile2.userGrades.put(course4.getCourseName(), course4.getGrade());

		testSaveOuterMap.put(userProfile1.getUsernameInput(), userProfile1.userGrades);
		testSaveOuterMap.put(userProfile2.getUsernameInput(), userProfile2.userGrades);
		
		saveHandler.saveUserGrades("TestUserGrades", testSaveOuterMap);
		saveHandler.loadToOuterMap("TestUserGrades", testLoadOuterMap);
		Assertions.assertEquals(testLoadOuterMap, testSaveOuterMap);
	}
	
	@Test
	public void testBlankUserGradesInputToOuterMap() throws Exception {
		SaveHandler saveHandler = new SaveHandler();
		testSaveOuterMap.put(userProfile1.getUsernameInput(), userProfile1.userGrades);
		testSaveOuterMap.put(userProfile2.getUsernameInput(), userProfile2.userGrades);
		
		saveHandler.saveUserGrades("TestUserGrades", testSaveOuterMap);
		saveHandler.loadToOuterMap("TestUserGrades", testLoadOuterMap);
		Assertions.assertEquals(testLoadOuterMap, testSaveOuterMap);
	}
	
}
