package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Genre;
import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;

import org.junit.Test;

public class CatalogueQueryTest {

	@Test
	public void testquery() {
		Genre jazz = new Genre ("Jazz");
		Genre pop = new Genre("Pop");
		Genre firstjazz = new Genre("FirstJazz");
		Genre minijazz = new Genre("MiniJazz");
		Genre hippop = new Genre ("HipPop");
		
		List<String> songlist1 = new ArrayList<String>();
		songlist1.add("Song1");
		songlist1.add("Song2");
		List<String> songlist2 = new ArrayList<String>();
		songlist2.add("Songa");
		songlist2.add("Songb");
		List<String> songlist3 = new ArrayList<String>();
		songlist3.add("Song!");
		songlist3.add("Song!!");
		List<String> songlist4 = new ArrayList<String>();
		songlist4.add("Song?");
		songlist4.add("Song??");
		List<String> songlist5 = new ArrayList<String>();
		songlist5.add("Song$$");
		songlist5.add("Song$$$");
		Album album1 = new Album("Louis and Angels", "Louis", songlist1);
		Album album2 = new Album("ABCELou", "Jason", songlist2);
		Album album3 = new Album("1234W", "Ruth", songlist3);
		Album album4 = new Album("YYYY4", "Louis", songlist4);
		Album album5 = new Album("DFDSEWLou", "Ruth", songlist5);
		album1.setGenre(jazz);
		album2.setGenre(firstjazz);
		album3.setGenre(minijazz);
		album4.setGenre(hippop);
		firstjazz.addToGenre(minijazz);
		jazz.addToGenre(firstjazz);
		pop.addToGenre(hippop);
		Catalogue catalogue = new Catalogue();
		catalogue.add(album5);
		catalogue.add(jazz);
		catalogue.add(pop);
		
		List<Album> albums1 = catalogue.query("in (\"FirstJazz\")");
        assertTrue(albums1.contains(album3)&&albums1.contains(album2));
        
        List<Album> albums2 = catalogue.query("in (\"Jazz\") && matches (\".*Lou.*\") || by (\"Ruth\")");
        assertTrue(albums2.contains(album1)&&albums2.contains(album2)&&albums2.contains(album3)&&albums2.contains(album5));
        
        List<Album> albums3 = catalogue.query("(in (\"FirstJazz\") || matches (\".*4.*\")) && (matches (\".*W.*\") || by (\"Ruth\"))");
        assertTrue(albums3.contains(album3));
	}

}
