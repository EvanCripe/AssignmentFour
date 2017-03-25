package lab6c202;

import java.io.*;
import java.util.*;

/**
 * AssignmentFour.java Purpose is to read from a file and check if the words in 
 * that file are also in a dictionary using linked lists. 
 * @author Evan Cripe
 * @version 1.0 2/18/17
 */
public class AssignmentFour {

    MyLinkedList[] list = new MyLinkedList[26];
    int wordsFound = 0;
    int wordsNotFound = 0;
    long compsFound = 0;
    long compsNotFound = 0;

    /**
     * Constructor that will load list with the words in "random_dictionary.txt"
     * All words that start with 'a' will be stored in list[0], words starting
     * with 'b' will be stored in list[1], and so on all the way up to words
     * starting with 'z' being stored in list[25]
     */
    public AssignmentFour() {
        for (int i = 0; i < list.length; i++) {
            list[i] = new MyLinkedList<String>();
        }//fori
        try {
            File file = new File("random_dictionary.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String line = input.next().toLowerCase();
                list[line.charAt(0) - 97].add(line);
            }//while
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//AssignmentFour

    /**
     * Method that will read from "oliver.txt", parse each word then compare it
     * to the words in the array called list. It will keep count of the number
     * of words in oliver.txt that are also in list. Words in oliver.txt not in
     * list as well as the number of comparisons it goes through.
     */
    public void readOliver() {
        try {
            File file = new File("oliver.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String word = input.next().toLowerCase().replaceAll("[^a-z]", "");
                while (word.isEmpty()) {//go to next token (word) until the token is not empty
                    word = input.next().toLowerCase().replaceAll("[^a-z]", "");
                }//while
                if (list[word.charAt(0) - 97].contains(word)) {
                    wordsFound++;
                    compsFound += MyLinkedList.count;
                }//if    
                else {
                    wordsNotFound++;
                    compsNotFound += MyLinkedList.count;
                }//else    
            }//while
        }//try
        catch (IOException ex) {
            ex.printStackTrace();
        }//catch
    }//readOliver

    public static void main(String[] args) {
        AssignmentFour run = new AssignmentFour();
        run.readOliver();
        System.out.println("Words found: " + run.wordsFound);
        System.out.println("Words not found: " + run.wordsNotFound);
        System.out.println("Comparisons found: " + run.compsFound);
        System.out.println("Comparisons not found: " + run.compsNotFound);
        System.out.printf("Average comparisons found: %6.4f\n", (double) run.compsFound / run.wordsFound);
        System.out.printf("Average comparisons not found: %6.4f\n", (double) run.compsNotFound / run.wordsNotFound);
    }//main 
}//class

/**
 * Changes Made in MyLinkedList.java.
public static int count = 0; //Class variable to keep track of the number of comparisons
                             //done in the contains() method
   public boolean contains(E e){
        Node<E> temp = head;
        count = 0;
        while(temp != null){
            count++;
            if(e.equals(temp.element)){
                return true;
            }//if
            temp = temp.next;
        }//while
        return false;
    }//contains
 * 
 */
