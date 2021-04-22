package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class UserProfileTest {
	
	UserProfile userProfile;
	HashMap<String, String> testMap = new HashMap<String, String>();
	

	
	private boolean checkState(UserProfile userProfile, String username, HashMap<String, String> userGrades, boolean isLoggedIn){
		return (userProfile.getUsernameInput() == username && userProfile.getUserGrades() == userGrades && userProfile.isLoggedIn() == isLoggedIn);
	}

	@BeforeEach
	public void setUp() throws Exception {
		testMap.put("", "");
		userProfile = new UserProfile("Andreas", new HashMap<String, String>(), false);
	}
	
	@Test
	public void testConstructor() {	
		Assertions.assertTrue(checkState(userProfile, "Andreas", userProfile.getUserGrades(), false));    //constructed object is set isLoggedIn = false by default
		Assertions.assertNotEquals(userProfile.getUserGrades(), testMap);    //userProfile.userGrades is always blank
		Assertions.assertEquals("Andreas", userProfile.getUsernameInput());
		Assertions.assertEquals(new HashMap<String, String>(), userProfile.getUserGrades());
		Assertions.assertEquals(false, userProfile.isLoggedIn());
	
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> {
			new UserProfile("Andy1999", new HashMap<String, String>(), false);    //numbers in username
		});
		
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> {
			userProfile.validUsername("1999");
		});
		
	}
	
	@Test
	public void testGetUsernameInput() {
		Assertions.assertEquals(userProfile.getUsernameInput(), "Andreas");
	}
	
	@Test
	public void testSetUsernameInput() {
		userProfile.setUsernameInput("Fredrik");
		Assertions.assertEquals(userProfile.getUsernameInput(), "Fredrik");
	}
	@Test
	public void testGetUserGrades() {
		Assertions.assertEquals(userProfile.getUserGrades(), new HashMap<String, String>());
	}
	@Test
	public void testSetUserGrades() {
		HashMap<String, String> testMap1 = new HashMap<String, String>();
		testMap1.put("TestCourse1", "A");
		testMap1.put("TestCourse2", "A");
		testMap1.put("TestCourse3", "A");
		userProfile.setUserGrades(testMap1);
		Assertions.assertEquals(userProfile.getUserGrades(), testMap1);
		
	}
	@Test
	public void testIsLoggedIn() {
		Assertions.assertEquals(userProfile.isLoggedIn(), false);
	}
	@Test
	public void testSetLoggedIn() {
		userProfile.setLoggedIn(true);
		Assertions.assertEquals(userProfile.isLoggedIn(), true);
	}

}
