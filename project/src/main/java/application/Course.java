package application;

public class Course { // i denne klassen lager vi et Course objekt, som inneholder navn på faget og karakteren
	private String courseName;
	private String grade;
	
	public Course(String courseName, String grade) {
		checkValidGrade(grade);
		checkValidCourse(courseName);
		this.courseName = courseName;
		this.grade = grade;
	}
	
	public void checkValidGrade(String grade) {
		if(!(grade.equals("A") || grade.equals("B") || grade.equals("C") || grade.equals("D") || grade.equals("E")  || grade.equals("F"))) {
			throw new IllegalArgumentException("Invalid grade");
		}
	}
	
	public void checkValidCourse(String courseName) {
		if(courseName.isBlank()) {
			throw new IllegalArgumentException("Coursename is blank.");
		}
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		checkValidCourse(courseName);
		this.courseName = courseName;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		checkValidGrade(grade);
		this.grade = grade;
	}


	public static void main(String[] args) {
		Course course = new Course("Add", "F");
	}
}
