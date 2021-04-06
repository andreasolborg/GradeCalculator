package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Calculation {
//henter fra feltene
	
	double avg;
	
	public Calculation() {
		
	}

	//MÅ HENTE HASHMAP SINE VALUES (fra userprofiles) OG LEGGE DISSE INN I STRING, "grades"
	
	public String Calculate(List<String> grades) {
		int prosent = 0;
		double karakter = 0;
		
//		String[] arr = grades.split("");
		for (int i = 0; i < grades.size(); i++) {
			if(grades.get(i).equals("E")) {
				prosent += 52;
				karakter += 1;
			} else if(grades.get(i).equals("D")) { 
				prosent += 64;
				karakter += 2;
			} else if(grades.get(i).equals("C")) {
				prosent += 76;
				karakter += 3;
			} else if(grades.get(i).equals("B")) {
				prosent += 88;
				karakter += 4;
			}
			else if(grades.get(i).equals("A")) {
				prosent += 100;
				karakter += 5;
			}
			else {
				prosent += 0;
				karakter += 0;
			}
			
		}
		avg = karakter/grades.size();
		String result = String.format("%.2f", avg);
		return result;
	}
	
	public static void main(String[] args) {
		List<String> grades = Arrays.asList("A", "A", "A");
		System.out.println(grades.toString());
		Calculation calc = new Calculation();	
		System.out.println(calc.Calculate(grades));
	}
	
}
