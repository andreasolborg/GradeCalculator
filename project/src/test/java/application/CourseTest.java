package application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions.*;

public class CourseTest {
	
	private Course course;
	
	@BeforeEach
	public void setUp() {
		Course course = new Course("Diskmat", "C");
	}
	@Test
	void testChangeCourseGrade() {
		course.setCourseName("D");
		assertTrue("D".equals(course.getCourseName()));
	}
 
}
