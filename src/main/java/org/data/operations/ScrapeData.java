package org.data.operations;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
  * ScrapeData.java
  * Author : Archit Joshi
  * Version :
  * Revisions :
  */

public class ScrapeData {

    /**
     * This method scrapes the data from the website
     * @param startingLetter : The starting letter of the superhero name
     * @return : An arrayList of hyperlinks to the superhero pages
     */

    public ArrayList< String > dataScrape( String startingLetter) {
        try{
            Document doc = Jsoup
                    .connect(" https://superheroes.fandom.com/wiki/List_of_DC_Comics_Characters")
                    .header("Accept-Language", "*")
                    .get();
            String[] startsWith = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                    "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

            // Parse the h2 elements inside class 'main-container'
            Elements h2Elements = doc.select(".mw-parser-output h2");
            // Get the h2 element reflecting the ordinal number of the starting letter
            Element h2Element = h2Elements
                                .get( java.util.Arrays.asList(startsWith)
                                        .indexOf(startingLetter)+1 );
//            System.out.println(h2Element.text());
            Element ulElement = h2Element.nextElementSibling();
            assert ulElement != null;
            // Append the list of hyperlinks to an arrayList
            Elements aElements = ulElement.select("a[href]");
            ArrayList<String> links = new ArrayList<>();
            for (Element aElement : aElements) {
                System.out.println(aElement.attr("href"));
                links.add(aElement.attr("href"));
            }

            return links;
        } catch( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    public static void main(String[] args) {
        ScrapeData scrapeData = new ScrapeData();
        String[] startsWith = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // Scrape data for all the letters from A-Z
        ArrayList< String > links = scrapeData.dataScrape( "B" );

    }
}
