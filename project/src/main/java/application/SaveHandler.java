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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class SaveHandler implements saver {

	static List<String> gradesList = new ArrayList<String>();
	static String[] gradeSplitter;
	static HashMap<String, String> outerMap1 = new HashMap<>();
	
	public final static String SAVE_FOLDER = "";

	public void loadData(String filename) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(
				new FileReader(getFilePath(filename)));
	}

	public void saveUserData(String filename) throws FileNotFoundException {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename), true));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			
			for(Map.Entry me : UserProfile.Users.entrySet()) {
				bw.append(me.getKey() + ";" + me.getValue()+"\n");
			}
			br.close();
			bw.close();
		}catch(Exception ex) {
			return;
		}
}
		
	public void saveUserGrades(String filename) throws FileNotFoundException {
		try {
//			System.out.println("UserProfile.outerMap.toString(): --------------- " + UserProfile.outerMap.toString());	
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename)));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			
			for(Map.Entry me : UserProfile.outerMap.entrySet()) {	
				bw.write(me.getKey() + ";" + me.getValue().toString().replaceAll("(\\{|\\})", "")+"\n");
//				System.out.println("me.getKey() + me.getValue().toString() -------------->" + me.getKey() + me.getValue().toString());
			}
			
			br.close();
			bw.close();					
			}catch(Exception ex) {
		return;
	}
}
	
	public void saveUserData1(String filename) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(getFilePath(filename))) {
				System.out.println(UserProfile.Users);
				loadToOuterMap(getFilePath(filename));
				for(Map.Entry me : UserProfile.Users.entrySet()) {
					writer.println("Du er logget inn som: "+ me.getKey() + ", year: " + UserProfile.Users.get(me.getKey()));
			}
		}
}

	public void saveUserGrades1(String filename) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(getFilePath(filename))),true)) {
//				System.out.println(UserProfile.outerMap);
//				List<String> info = new ArrayList<>();
				for(Map.Entry me : UserProfile.outerMap.entrySet()) {
					writer.println(me.getKey() + "har karakterene: " + UserProfile.outerMap.get(me.getKey()));
			}
		}
}
	 
	public void loadToOuterMap(String filename) throws FileNotFoundException {
		String[] stringSplitter = null;	
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(getFilePath(filename), true));
			BufferedReader br = new BufferedReader(
					new FileReader(getFilePath(filename)));
			String s;
			while((s = br.readLine()) != null) {
				if(s.contains("=")) {
				stringSplitter = s.split(";");                                 //splits names and grades
				String [] gradesSplitter = stringSplitter[1].split(", ");
				
//																																							System.out.println(stringSplitter[0]);
//																																							System.out.println(stringSplitter[1].split(", ")[0].split("=")[0]);				   //prints first course for ea person							
//																																							System.out.println(stringSplitter[0]);
//				UserProfile.userGrades = new HashMap<>();
				UserProfile userProfile = new UserProfile(stringSplitter[0], 1337, new HashMap<String, String>(), false);
				UserProfile.outerMap.put(userProfile.getUsernameInput(), new HashMap<>());
				for(int i = 0; i < gradesSplitter.length; i++) {
					Course course = new Course(gradesSplitter[i].split("=")[0], gradesSplitter[i].split("=")[1]);
					userProfile.userGrades.put(course.getCourseName(), course.getGrade());
					userProfile.setUserGrades(userProfile.userGrades);
					UserProfile.outerMap.put(userProfile.getUsernameInput(), userProfile.userGrades);			
//					UserProfile.outerMap.get(userProfile.getUsernameInput()).put(course.getCourseName(), course.getGrade());

//																																							System.out.println(gradesSplitter[i].split("=")[0]);
//																																							System.out.println(gradesSplitter[i].split("=")[1]);   //hver brukers karakter
//																																							UserProfile.addGrades(gradesSplitter[i].split("=")[0], gradesSplitter[i].split("=")[1]);								
//					UserProfile.outerMap.get(stringSplitter[0]).put(gradesSplitter[i].split("=")[0], gradesSplitter[i].split("=")[1]);						
//					(stringSplitter[0], UserProfile.addGrades(gradesSplitter[i].split("=")[0], gradesSplitter[i].split("=")[1]));  //maa putte et unikt hashmap, ikke userGrades

				} UserProfile.userProfiles.add(userProfile);
			}
				
			}
//			System.out.println(UserProfile.userGrades.toString());
			br.close();
			bw.close();	
		}catch(Exception ex) {
			System.err.println(ex);
			return;
		}
		}
		
	public String getFilePath(String filename) {
		return SAVE_FOLDER + filename + ".txt";
	}

}