/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordsofincreasingperiodicelements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Matt Cleinman
 */
public class WordsOfIncreasingPeriodicElements {


    public static void main(String[] args) {
        
        // for the elements
        HashMap<String,Integer> elementList = new HashMap<String,Integer>();
        
        // load elements and numbers
        // elements should be capitalized normally - He for Helium, etc.
        // first column is element abbrevation, second is the atomic number
        File file = new File("ElementList.csv");
        String aLine;
        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                aLine = scanner.nextLine();
                int commaSpot = aLine.indexOf(',');
                elementList.put(aLine.substring(0,commaSpot),Integer.parseInt(aLine.substring(commaSpot+1)));
                
                //System.out.println(aLine.substring(0,commaSpot));
                //System.out.println(Integer.parseInt(aLine.substring(commaSpot+1)));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }
        
        // load file for words
        // list from: https://gist.githubusercontent.com/jeffThompson/7789182/raw/2a01261691b6790e5800fad41f13395ad0230a80/Words%2520In%2520The%2520Periodic%2520Table:%2520Results
        // you may use an alternative list, but words must be capitalized as elements - so Helium/Nitrogen would be "HeN", not "HEN" or "Hen"
        File wordFile = new File("WordFile.txt");
        String thisWord;
        try {

            Scanner scanner = new Scanner(wordFile);

            while (scanner.hasNextLine()) {
                // get word
                thisWord = scanner.nextLine();
                
                // if strictly increasing...
                int lastElement = 0;
                int elementStart = 0;
                int elementEnd;
                int elementCount = 0;
                
                while (elementStart < thisWord.length()) {
                    
                    elementEnd = elementStart + 1;

                    // if this letter is a lowercase one, get two letters
                    if (elementEnd < thisWord.length()) {
                        if (thisWord.substring(elementEnd,elementEnd+1).matches("[a-z]")) {
                            elementEnd = elementEnd + 1;
                        }
                    }
                    
                    // get the element
                    String thisElement = thisWord.substring(elementStart, elementEnd);
                    elementCount++;
                        
                    //System.out.println(thisElement);
                    
                    if (elementList.get(thisElement) > lastElement) {
                        lastElement = elementList.get(thisElement);
                        elementStart = elementEnd;
                    } else {
                        break;
                    }
                }
                 
                // if it got through whole word and it has at least 4 elements
                if (elementStart == thisWord.length() && elementCount >= 4) {
                    //... output to screen
                    System.out.println(thisWord);
                }
                
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        }

    }
 
}
