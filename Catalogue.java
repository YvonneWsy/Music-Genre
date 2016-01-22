package ca.ubc.ece.eece210.mp2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Container class for all the albums and genres. Its main 
 * responsibility is to save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {
	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		// TODO implement
		ArrayList<String> catalogue = new ArrayList<String>();
	}

	/**
	 * Builds a new catalogue and restores its contents from the 
	 * given file.
	 * 
	 * @param fileName
	 *            the file from where to restore the library.
	 */
	public Catalogue(String fileName) {
		// TODO implement
		 try {
	            FileInputStream input = new FileInputStream(fileName);
	        } catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
	        }
		 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
		    while ((line = reader.readLine()) != null) {
		        Genre genre = Genre.restoreCollection(line);
		    }
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Saved the contents of the catalogue to the given file.
	 * @param fileName the file where to save the library
	 */
	public void saveCatalogueToFile(String fileName) {
		// TODO implement
		File file = new File("catalogue.txt");
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			for(Map.Entry<String, Genre> entry : Genre.genreMap.entrySet()){
				writer.write(entry.getValue().toString());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}