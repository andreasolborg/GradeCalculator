//jeg må lage en løkke eller en metode som kan hente ut dette fra UserGrades: navn, fag = karakter. dette skal skje når man registrerer nye fag
//så må jeg kjøre metodene som legger de til hashmapet, (legg til fag knappen)
//deretter må dette hashmappet skrive til fil, og overwrite det som står der fra før.



package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class SaveHandler implements saver {
	
	public final static String SAVE_FOLDER = "";
	public String userString;
	public List<String> userList = new ArrayList<String>();
	

	public void loadUserData(String filename, HashMap<String, String> Users) throws FileNotFoundException {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			String s;
			while((s = br.readLine()) != null) {
				Users.put(s, "");
			}
		} catch (Exception e) {
			System.out.println("Error loading users.");
		}
		
	}

	public void saveUserData(String filename, HashMap<String, String> Users) throws FileNotFoundException {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename)));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			for(Map.Entry me :Users.entrySet()) {
				bw.append(me.getKey() +"\n");                    //saves the userNames in seperate textFile
			}
			br.close();
			bw.close();
		}catch(Exception ex) {
			return;
		}
}
		
	public void saveUserGrades(String filename, HashMap<String, HashMap<String, String>> outerMap) throws FileNotFoundException {
		try {
//			System.out.println("UserProfile.outerMap.toString(): --------------- " + UserProfile.outerMap.toString());	
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename)));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			
			for(Map.Entry me : outerMap.entrySet()) {	
				bw.write(me.getKey() + ";" + me.getValue().toString().replaceAll("(\\{|\\})", "")+"\n");

			}
			br.close();
			bw.close();					
			}catch(Exception ex) {
		return;
	}
}

	public void loadToOuterMap(String filename, HashMap<String, HashMap<String, String>> outerMap) throws Exception {
		UserProfile.userProfiles.clear();
		String[] stringSplitter = null;	
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename), true));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			String s;
			while((s = br.readLine()) != null) {
				if(s.contains("=")) {
				stringSplitter = s.split(";");                                 //splits names and courses
				String [] gradesSplitter = stringSplitter[1].split(", ");      //splits courses
				UserProfile userProfile = new UserProfile(stringSplitter[0], new HashMap<String, String>(), false);
				outerMap.put(userProfile.getUsernameInput(), new HashMap<>());
				for(int i = 0; i < gradesSplitter.length; i++) {
					Course course = new Course(gradesSplitter[i].split("=")[0], gradesSplitter[i].split("=")[1].substring(0,1));
					userProfile.getUserGrades().put(course.getCourseName(), course.getGrade());
					userProfile.setUserGrades(userProfile.getUserGrades());
					userProfile.courseObjects.add(course);		//adds the new Course-objects into current user list of userGrades
					outerMap.put(userProfile.getUsernameInput(), userProfile.getUserGrades());			
				}
			} else {
				stringSplitter = s.split(";");      
				UserProfile userProfile = new UserProfile(stringSplitter[0], new HashMap<String, String>(), false);
				outerMap.put(userProfile.getUsernameInput(), new HashMap<>());
			}
				
			}
			br.close();
			bw.close();	
		}
		

	public String getFilePath(String filename) {
		return SAVE_FOLDER + filename + ".txt";
	}

}