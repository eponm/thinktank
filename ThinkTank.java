import java.util.Scanner; // For scanning, obviously.

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


class ThinkTank {

    private Path saveFile;

    public void main(String[] args) {

        // Look for the save file and read it in through the text parser
        saveFile = Paths.get("savedstate.txt"); // CHANGE SAVE FILE NAME HERE !
        TextParser saveReader = new TextParser(String.valueOf(saveFile));

        // Init an IdeaDB
        // Implicitly initializes the heap and list for ideas
        IdeaDB ideas = new IdeaDB();

        // While segment = STUDENT

        // Add the student to the BST

        // Connect its parent, left, right


        // While segment = IDEA

        // Insert each idea


        /*
        Menu structure

        |——v Top idea
        |  |——> Sell idea
        |——> Add idea
        |——v Student lookup
        |  |——> Print ideas
        |  L——> Print record
        L——> Print all students

        */
        System.out.println("Welcome, we are hiveMind and we are here to help you!")
        //menu options
        //A)Help Documentation for user
            //prints documentation for user
        //B)Top Idea 
            //Would you like to sell the idea?
                //(Y)es
                    //prints idea to file
                    //"Your idea has been saved as 'bestIdea!.txt' ""
                //(N)o, main menu()
        //C)Add Idea
            //SSN = "What is SSN of the submittor?"
                //search BST with SSN
                //if found
                    //ideaText="Please imput idea"
                    //rating="Please enter a rating for the idea (0 to 100)"
                        //if over 100 rating==100
                    //newIdea = new Idea(seqNum, SSN, ideaText, rating)
                    //ideas.insertIdea(newIdea)
                //if not found
                    //"That student was not found in our database.
                    // Would you like to add them?"
                        //(Y)es
                            //string name="Please enter the last name of the student"
                            //string username="Please enter the email of the student"
                            //int studentID="Please enter their student ID"
                        //(N)o
    

    } // MAIN

} // Thinktank
