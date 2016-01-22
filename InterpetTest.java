package ca.ubc.ece.eece210.mp3.ast;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ca.ubc.ece.eece210.mp3.Album;
import ca.ubc.ece.eece210.mp3.Catalogue;
import ca.ubc.ece.eece210.mp3.Element;
import ca.ubc.ece.eece210.mp3.Genre;
import ca.ubc.ece.eece210.mp3.ast.ASTNode;
import ca.ubc.ece.eece210.mp3.ast.QueryParser;
import ca.ubc.ece.eece210.mp3.ast.QueryTokenizer;
import ca.ubc.ece.eece210.mp3.ast.Token;

import org.junit.Test;

public class InterpetTest {

	@Test
	public void testByNode() {
		Genre genre1 = new Genre("Jazz");
		Genre subgenre1 = new Genre("Psychedelic Jazz");
		Genre subgenre2 = new Genre("MiniJazz");
		Genre genre2 = new Genre("POP");
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
		Album album1 = new Album("Louis and Angels", "Louis Armstrong", songlist1);
		Album album2 = new Album("ABCLou", "Esther", songlist2);
		Album album3 = new Album("YYYY", "Ruth", songlist3);
		Album album4 = new Album("01Lou23", "Ruth", songlist4);
		album1.setGenre(genre1);
		album2.setGenre(subgenre1);
		album4.setGenre(genre2);
		album3.setGenre(subgenre2);
		genre1.addToGenre(subgenre1);
		subgenre1.addToGenre(subgenre2);
		Catalogue argument = new Catalogue();
		argument.add(genre1);
		argument.add(genre2);
		ASTNode bynode1 = new ByNode(new Token(TokenType.BY,"by"));
		bynode1.setArguments("Esther");
		Set<Element> check = new HashSet<Element>();
		check.add(album2);
		
		assertEquals(check,bynode1.interpret(argument));
	}
	@Test
	public void testInNode(){
		Genre genre1 = new Genre("Jazz");
		Genre subgenre1 = new Genre("Psychedelic Jazz");
		Genre subgenre2 = new Genre("MiniJazz");
		Genre genre2 = new Genre("POP");
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
		Album album1 = new Album("Louis and Angels", "Louis Armstrong", songlist1);
		Album album2 = new Album("ABCE", "Louis Armstrong", songlist2);
		Album album3 = new Album("1234", "Louis Armstrong", songlist3);
		Album album4 = new Album("YYYY", "Louis Armstrong", songlist4);
		album1.setGenre(genre1);
		album2.setGenre(subgenre1);
		album3.setGenre(genre2);
		album4.setGenre(subgenre2);
		genre1.addToGenre(subgenre1);
		subgenre1.addToGenre(subgenre2);
		Catalogue argument = new Catalogue();
		argument.add(genre1);
		argument.add(genre2);
		
		ASTNode innode1 = new InNode(new Token(TokenType.IN, "in"));
		innode1.setArguments("MiniJazz");
		Set<Element> albums = new HashSet<Element>();
		//albums.add(album1);
		//albums.add(album2);
		albums.add(album4);
		
		assertEquals(albums,innode1.interpret(argument));
	}
	
	@Test
	public void testMatchNode(){
		Genre genre1 = new Genre("Jazz");
		Genre subgenre1 = new Genre("Psychedelic Jazz");
		Genre subgenre2 = new Genre("MiniJazz");
		Genre genre2 = new Genre("POP");
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
		Album album1 = new Album("Louis and Angels", "Louis Armstrong", songlist1);
		Album album2 = new Album("ABCLou", "Louis Armstrong", songlist2);
		Album album3 = new Album("YYYY", "Louis Armstrong", songlist3);
		Album album4 = new Album("01Lou23", "Louis Armstrong", songlist4);
		album1.setGenre(genre1);
		album2.setGenre(subgenre1);
		album3.setGenre(genre2);
		album4.setGenre(subgenre2);
		genre1.addToGenre(subgenre1);
		subgenre1.addToGenre(subgenre2);
		Catalogue argument = new Catalogue();
		argument.add(genre1);
		argument.add(genre2);
		
		ASTNode matches = new MatchesNode(new Token(TokenType.MATCHES, "matches"));
		matches.setArguments(".*Lou.*");
		Set<Element> albums = new HashSet<Element>();
		albums.add(album1);
		albums.add(album2);
		albums.add(album4);
		
		assertEquals(albums, matches.interpret(argument));
	}
	
	@Test
	public void testAndNode(){
		Genre genre1 = new Genre("Jazz");
		Genre subgenre1 = new Genre("Psychedelic Jazz");
		Genre subgenre2 = new Genre("MiniJazz");
		Genre genre2 = new Genre("POP");
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
		Album album1 = new Album("Louis and Angels", "Louis Armstrong", songlist1);
		Album album2 = new Album("ABCLou", "Esther", songlist2);
		Album album3 = new Album("YYYY", "Ruth", songlist3);
		Album album4 = new Album("01Lou23", "Esther", songlist4);
		album1.setGenre(genre1);
		album2.setGenre(subgenre1);
		album3.setGenre(genre2);
		album4.setGenre(subgenre2);
		genre1.addToGenre(subgenre1);
		subgenre1.addToGenre(subgenre2);
		Catalogue argument = new Catalogue();
		argument.add(genre1);
		argument.add(genre2);
		
		ASTNode bynode1 = new ByNode(new Token(TokenType.BY,"by"));
		bynode1.setArguments("Esther");
		
		ASTNode innode1 = new InNode(new Token(TokenType.IN, "in"));
		innode1.setArguments("Jazz");
		
		ASTNode and = new AndNode(new Token(TokenType.AND, "&&"));
		and.addChild(bynode1);
		and.addChild(innode1);
		
		Set<Element> albums = new HashSet<Element>();
		albums.add(album2);
		albums.add(album4);
		
		assertEquals(albums, and.interpret(argument));
	}
	
	@Test
	public void testOrNode(){
		Genre genre1 = new Genre("Jazz");
		Genre subgenre1 = new Genre("Psychedelic Jazz");
		Genre subgenre2 = new Genre("MiniJazz");
		Genre genre2 = new Genre("POP");
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
		Album album1 = new Album("Louis and Angels", "Louis Armstrong", songlist1);
		Album album2 = new Album("ABCLou", "Esther", songlist2);
		Album album3 = new Album("YYYY", "Ruth", songlist3);
		Album album4 = new Album("01Lou23", "Ruth", songlist4);
		album1.setGenre(genre1);
		album2.setGenre(subgenre1);
		album3.setGenre(genre2);
		album4.setGenre(subgenre2);
		genre1.addToGenre(subgenre1);
		subgenre1.addToGenre(subgenre2);
		Catalogue argument = new Catalogue();
		argument.add(genre1);
		argument.add(genre2);
		
		ASTNode bynode1 = new ByNode(new Token(TokenType.BY,"by"));
		bynode1.setArguments("Esther");
		
		ASTNode innode1 = new InNode(new Token(TokenType.IN, "in"));
		innode1.setArguments("Jazz");
		
		ASTNode or = new OrNode(new Token(TokenType.OR, "||"));
		or.addChild(bynode1);
		or.addChild(innode1);
		
		Set<Element> albums = new HashSet<Element>();
		albums.add(album1);
		albums.add(album2);
		albums.add(album4);
		assertEquals(albums,or.interpret(argument));
	}
}
