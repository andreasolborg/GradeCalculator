package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public interface saver {
//	public void loadData(String filename) throws FileNotFoundException;
	public void saveUserData(String filename, HashMap<String, String> Users) throws FileNotFoundException;
	public void loadUserData(String filename, HashMap<String, String> Users) throws FileNotFoundException;
	public void saveUserGrades(String filename, HashMap<String, HashMap<String, String>> outerMap) throws FileNotFoundException;
	public void loadToOuterMap(String filename, HashMap<String, HashMap<String, String>> outerMap) throws FileNotFoundException, Exception;
	String getFilePath(String filename);
}