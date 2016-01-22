package ca.ubc.ece.eece210.mp2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GenreTest {
    @Test
    public void test5() {
        //Create an album
        ArrayList<String> songlist1 = new ArrayList<String>();
        songlist1.add("WhiteAlbum");
        songlist1.add("SoundOfDestiny");
        Album album1 = new Album("WhiteAlbum","Yukino", songlist1);
        
        //Create a genre contains album1
        Genre genre1 = new Genre("Jpop");
        genre1.genre.add(album1);
        
        //Create a genre contains the genre with album1
        Genre genre = new Genre("Pop");
        genre.genre.add(genre1);
        Genre.genreMap.put(genre.genreName, genre);
        
        String genreString = new String("\n" + "<Genre>" + "\n" +
                                        "<GenreName>" + "\n" +
                                        "Pop" + "\n" +
                                        "\n" + "<Genre>" + "\n" +
                                        "<GenreName>" + "\n" +
                                        "Jpop" + "\n" +
                                        album1.toString() + "\n" +
                                        "</GenreName>" + "\n" +
                                        "</Genre>" + "\n" + "\n" +
                                        "</GenreName>" + "\n" +
                                        "</Genre>" + "\n");
        assertTrue(genreString.equals(genre.toString()));
    }
}