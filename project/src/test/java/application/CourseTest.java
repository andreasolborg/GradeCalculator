package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseTest {
	private Course course;

	@BeforeEach
	public void setUp() throws Exception {
		course = new Course("TestCourse", "D");
	}
	
	
	@Test
	public void testChangeCourseProperties() {
		course.setGrade("A");
		Assertions.assertEquals("A", course.getGrade());
		Assertions.assertEquals("TestCourse", course.getCourseName());
		course.setCourseName("ChangeNameTest");
		Assertions.assertEquals("ChangeNameTest", course.getCourseName());
	}
	
	@Test
	public void testShouldThrowIllegalArgumentExceptionWhenGradeIsNotAtoF() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			course.setGrade("G");
		});
	}
	
 
}
