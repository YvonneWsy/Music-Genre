package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.Map;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains the information needed to represent 
 * an album in our application.
 * 
 */

public final class Album extends Element {
	private String title = new String();
	private String performer = new String();
	private ArrayList<String> songlist = new ArrayList<String>();
	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title
	 *            the title of the album
	 * @param author
	 *            the performer 
	 * @param songlist
	 * 			  the list of songs in the album
	 */
	public Album(String title, String performer, ArrayList<String> songlist) {
		// TODO implement
		this.title = title;
		this.performer = performer;
		this.songlist = songlist;
	}

	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation
	 *            the string representation
	 */
	public Album(String stringRepresentation) {
		// TODO implement this
		String[] split = new String[]{};
		
		//get rid of the '<' symbol in the tag
		split = stringRepresentation.split("<");
		
		//make the stringRepresentation empty so we can create a new stringRepresentation that does not contain '<' sign
		stringRepresentation = "";
		for(int i = 0; i<split.length; i++){
			stringRepresentation += split[i];
		}
		
		// now get rid of the '>' symbol in the tag
		split = stringRepresentation.split(">");
		

		title = split[3].substring(0, split[3].lastIndexOf("/"));
		
		performer = split[5].substring(0, split[5].lastIndexOf("/"));
		
		for(int i = 1; i<=(split.length-8)/2; i++){
			songlist.add(split[5+2*i].substring(0, split[5+2*i].lastIndexOf("/")));
		}
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist, as well as all the genre
	 * that the book belongs to.
	 * 
	 * @return the string representation
	 */
	public String toString() {
		// TODO implement this
		StringBuilder builder = new StringBuilder();
		StringBuilder arrayBuilder = new StringBuilder();
		
		// add each song in the songlist with open tag and close tag before and after it into a string
		for(int i = 0; i < songlist.size(); i++){
			arrayBuilder.append("<Song>"+songlist.get(i)+"</Song>"+"\n");
		}
		
		// convert the whole album object into a stringRepresentation
		builder.append("<GenreBelongTo>" + "\n" +
					   getGenre().genreName + "\n" +
					   "<Album>" + "\n" + 
		               "<Title>" + title + "</Title>" + "\n" +
				       "<Performer>" + performer + "</Performer>" + "\n" +
		               arrayBuilder +
		               "</Album>" + "\n" + 
		               "</GenreBelongTo>" + "\n" );
		return builder.toString();
	}

	/**
	 * Add the book to the given genre
	 * 
	 * @param genre
	 *            the genre to add the album to.
	 */
	public void addToGenre(Genre genre) {
		// TODO implement this
		//use the addToGenre method in the Genre class
		genre.addToGenre(this);
	}

	/**
	 * Returns the genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		// TODO implement this
		
		//search each element in the genreMap to see if any of them contain this album object
		// cannot use album name to search because two different album objects may have the same name
		for(Map.Entry<String, Genre> entry : Genre.genreMap.entrySet()){
			if(entry.getValue().getChildren().contains(this)){
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {
		// TODO implement this
		return title;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		// TODO implement this
		return performer;
	}
	
	/**
	 * Returns the songlist of the album
	 * 
	 * @return the songlist
	 */
	public ArrayList<String> getSonglist() {
		return songlist;
	}
	
	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}
}

