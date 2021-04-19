package application;

public class Course { // i denne klassen lager vi et Course objekt, som inneholder navn på faget og karakteren
	private String courseName;
	private String grade;
	
	public Course(String courseName, String grade) {
		checkValidCourse(grade);
		this.courseName = courseName;
		this.grade = grade;
	}
	
	public void checkValidCourse(String grade) {
		if(!(grade.contains("A") || grade.contains("B") || grade.contains("C") || grade.contains("D") || grade.contains("E")  || grade.contains("F"))) {
			throw new IllegalArgumentException("Invalid grade");
		}
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		checkValidCourse(grade);
		this.grade = grade;
	}


	public static void main(String[] args) {

	}
}
