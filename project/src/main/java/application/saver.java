package application;

import java.io.FileNotFoundException;

public interface saver {
	public void loadData(String filename) throws FileNotFoundException;
	public void saveUserData(String filename) throws FileNotFoundException;
	public void saveUserGrades(String filename) throws FileNotFoundException;
	public void loadToOuterMap(String filename) throws FileNotFoundException;
	String getFilePath(String filename);
}