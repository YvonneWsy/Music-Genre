package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {
	protected static Map<String, Genre> genreMap = new HashMap<String, Genre>();
	
	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) throws IllegalArgumentException{
		
		// before construsct a new genre first check if the genre has already exist or not
		if(genreMap.containsKey(name)){
			throw new IllegalArgumentException("This Genre already exist");
		}
		else{
		// TODO implement
			genreName = name;
			genre = new ArrayList<Element>();
			
			// put the genre into the genreMap to keep track of all the genre that have been created
			genreMap.put(name, this);
		}
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		// TODO implement
		int counter = 0;
		String[] split = new String[]{};
		ArrayList<String> stringList = new ArrayList<String>();
		ArrayList<String> newList = new ArrayList<String>();
		split = stringRepresentation.split("\n");
		
		for(int i = 0; i<split.length; i++){
			stringList.add(split[i]);
		}
		
		// get the genre name first
		Genre genre = new Genre(stringList.get(3));

		for(int i=0; i<stringList.size(); i++){
		
		    // check if the genre object has subgenres in it
			if(stringList.get(i).equals("<Genre>") && (i!=1)){
				stringList.remove(stringList.lastIndexOf("</Genre>"));
				
				// make a copy of the sub genre part and make it back to the stringRepresentation
				for(int j = i-1; j <= stringList.lastIndexOf("</Genre>"); j++){
					newList.add(stringList.get(j));
				}
				StringBuilder builder = new StringBuilder();
				for(int j = 0; j < newList.size(); j++){
					builder.append(newList.get(j));
					if(newList.get(j)!="\n"){
						builder.append("\n");
					}
				}
				
				//add the subgenre into the genre object and recursive back
				Genre subgenre = new Genre(newList.get(3));
				restoreCollection(builder.toString());
				genre.addChild(subgenre);
				
				//check if the stringRepresentation only contains album or nor 
			}else if((stringList.get(i).equals("<Genre>") && (i==1)) || counter==1){
				Album album = null;

				StringBuilder albumBuilder = new StringBuilder();
						
						//using the album method in the Album class to restore all the album and add it to the genre object
					for(int j = 4; j<stringList.indexOf("</GenreBelongTo>");){
						albumBuilder.append(stringList.get(j));
						stringList.remove(4);
						albumBuilder.append("\n");
					}
						
					if(stringList.indexOf("</GenreBelongTo>") == 4){
						albumBuilder.append("</GenreBelongTo>"+"\n");
					}
					stringList.remove(4);
					album = new Album(albumBuilder.toString());
					genre.addChild(album);
			}
		}
		return genre;
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return
	 */
	public String toString() {
		// TODO implement
		StringBuilder builder = new StringBuilder();
		StringBuilder elementStringRepresentation = new StringBuilder();
		
		// if the element is a album if will automatically use the toString method in the Album class
		// otherwise if will recursive back 
		for(int i = 0; i < genreMap.get(genreName).genre.size(); i++){
			elementStringRepresentation.append(genreMap.get(genreName).genre.get(i).toString());
		}
		builder.append("\n" + "<Genre>" + "\n" +
					   "<GenreName>" + "\n" +
					   genreName + "\n" +
                       elementStringRepresentation + "\n" +
					   "</GenreName>" + "\n" +
					   "</Genre>" + "\n");
		return builder.toString();
	}

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b
	 *            the element to be added to the collection.
	 */
	public void addToGenre(Element b) {
		addChild(b);
	}
	
	/**
	 * Returns true, since a genre can contain other albums and/or
	 * genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}
}