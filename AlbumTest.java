package ca.ubc.ece.eece210.mp2;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MP2Test {
    
    @Test
    public void test1() {
        Genre genre = new Genre("Anime");
        ArrayList<String> songlist = new ArrayList<String>();
        songlist.add("ToTheBeginning");
        songlist.add("SeventhHeaven");
        Album album = new Album("ufotable", "kalafina", songlist);
        genre.addChild(album);
        assertTrue(genre.getChildren().contains(album));
    }
    
    @Test
    public void test2() {
        Genre genre = new Genre("Unclassified");
        ArrayList<String> songlist = new ArrayList<String>();
        songlist.add("ToTheBeginning");
        songlist.add("SeventhHeaven");
        Album album = new Album("ufotable", "kalafina", songlist);
        genre.addChild(album);
        if(genre.getChildren().contains(album)){
            genre.genre.remove(album);
        }
        assertTrue(!genre.getChildren().contains(album));
    }
    
    @Test
    public void test3() {
        Genre genre = new Genre("Pop");
        ArrayList<String> songlist = new ArrayList<String>();
        songlist.add("Abnormalize");
        songlist.add("unravel");
        songlist.add("Enigmatic feeling");
        Album album = new Album("Dark", "凛として时雨", songlist);
        genre.addChild(album);
        String output = new String("<GenreBelongTo>" + "\n" +
                                   "Pop" + "\n" +
                                   "<Album>" + "\n" +
                                   "<Title>" + "Dark" + "</Title>" + "\n" +
                                   "<Performer>" + "凛として时雨" + "</Performer>" + "\n" +
                                   "<Song>Abnormalize</Song>" + "\n" +
                                   "<Song>unravel</Song>" + "\n" +
                                   "<Song>Enigmatic feeling</Song>" + "\n" +
                                   "</Album>" + "\n" +
                                   "</GenreBelongTo>" + "\n");
        assertTrue(album.toString().equals(output));
    }
    
    @Test
    public void test4() {
        String stringRepresentation = new String("<GenreBelongTo>" + "\n" +
                                                 "JPop" + "\n" +
                                                 "<Album>" + "\n" +
                                                 "<Title>" + "Dark" + "</Title>" + "\n" +
                                                 "<Performer>" + "凛として时雨" + "</Performer>" + "\n" +
                                                 "<Song>Abnormalize</Song>" + "\n" +
                                                 "<Song>unravel</Song>" + "\n" +
                                                 "<Song>Enigmatic feeling</Song>" + "\n" +
                                                 "</Album>" + "\n" +
                                                 "</GenreBelongTo>" + "\n");
        ArrayList<String> songlist = new ArrayList<String>();
        songlist.add("Abnormalize");
        songlist.add("unravel");
        songlist.add("Enigmatic feeling");
        Album albumStringTest = new Album(stringRepresentation);
        Genre genre = new Genre("JPop");
        genre.addChild(albumStringTest);
        assertTrue(albumStringTest.getGenre().genreName.equals("JPop"));
        assertTrue(albumStringTest.getTitle().equals("Dark"));
        assertTrue(albumStringTest.getPerformer().equals("凛として时雨"));
        for(int i = 0; i < songlist.size(); i++){
            assertTrue(albumStringTest.getSonglist().get(i).equals(songlist.get(i)));
        }
    }
    
}