
import java.util.Scanner; // For scanning, obviously.

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


class ThinkTank {

    public static void main(String[] args) {

        final Charset ENCODING = StandardCharsets.UTF_8;
        final Path saveFile;


        // Look for the save file and read it in through the text parser
        saveFile = Paths.get("savedstate.txt"); // CHANGE SAVE FILE NAME HERE !
        TextParser saveReader = new TextParser(String.valueOf(saveFile));

        // Init an IdeaDB
        // Implicitly initializes the heap and list for ideas
        IdeaDB ideas = new IdeaDB();

        // We're about to try loading a save file.
        // Buckle up, kids.
        try {
            Scanner sc = new Scanner(saveFile, ENCODING.name());
            sc.useDelimiter("="); // Set the delimiter

            String key = sc.next(); // think
            String val = sc.next(); // tank

            // If the header is correct, go ahead and load the save file
            if (key == "think" && val == "tank") {
                System.out.println("Loading saved state...");

                key = sc.next(); val = sc.next(); // Move the holders to date=01 Jan
                System.out.println("> The last saved state is from " + val + ".");

                System.out.println("> Loading students into database...");
                key = sc.next(); val = sc.next(); // Move the holders forward to segment=student

                // Start looping through the save file
                boolean looping = true; // Loops as long as looping is true
                while (looping) {

                    // Loop through the things
                    if (key == "segment" && val == "student") {

                        // As long as the key is "student"...
                        while (key == "student") {
                            // Add each student to the tree
                            // Get rid of quotes and separate values
                            String paramSt = val.substring(1, val.length() - 1);
                            // Split the four params apart
                            String[] paramAr = paramSt.split("\",\"");

                            // Make the student
                            // Params: name, username, SSN, studentID
                            Student newStudent = new Student(paramAr[0], paramAr[1], Integer.parseInt(paramAr[2]), Integer.parseInt(paramAr[3]));

                            // Move the holders forward
                            key = sc.next(); val = sc.next();
                        } // while key is student
                    } // if key is segment

                    // Loop through the things
                    if (key == "segment" && val == "idea") {
                        System.out.println("> Loading ideas into database...");
                        key = sc.next(); val = sc.next(); // Move the holders forward

                        // As long as the key is "idea"...
                        while (key == "idea") {
                            // Add each idea to the list
                            // Get rid of quotes and separate values
                            String paramSt = val.substring(1, val.length() - 1);
                            // Split the three params apart
                            String[] paramAr = paramSt.split("\",\"");

                            // Make the idea
                            // Params: submittor SSN, description, rating, seqnum
                            Idea newIdea = new Idea(Integer.parseInt(paramAr[0]), paramAr[1], Integer.parseInt(paramAr[2]));
                            // Add the idea to the database
                            ideas.insertIdea(newIdea);

                            // Move the holders forward
                            key = sc.next(); val = sc.next();
                        } // while key is idea
                    } // if key is segment

                    // else if key is end
                    else if (key == "END" && val == "END") {
                        looping = false;
                    } // else if

                    System.out.println("> Loaded!");

                } // while

            } // if key and val are think tank

            else { // Case for broken databases and nonexistent saves
                System.out.println("! Saved state does not exist or is damaged.") ;
                System.out.println("> Starting a new database...");
            }

            // If we've made it this far, it must be time to close the scanner, so...
            sc.close();
        } // try

        catch (IOException x) {
            System.out.println("Got an IOException: " + x.getMessage());
        } // catch


        /*
        Menu structure

        |——v Top idea
        |  |——> Sell idea
        |——> Add idea
        |——v Student lookup
        |  |——> Print ideas
        |  L——> Print record
        L——> Print all students


        System.out.println("Welcome, we are hiveMind and we are here to help you!")
        //menu options
        //A)Help Documentation for user
            //prints documentation for user
        //B)Top Idea
            //Would you like to sell the idea?
                //(Y)es
                    //prints idea to file
                    //"Your idea has been saved as 'bestIdea.txt' ""
                //(N)o, main menu()
        //C)Add Idea
            //student = studentLookup()
                //if student!=null
                    //ideaText="Please imput idea"
                    //rating="Please enter a rating for the idea (0 to 100)"
                        //if over 100 rating==100
                    //newIdea = new Idea(seqNum, SSN, ideaText, rating)
                    //ideas.insertIdea(newIdea)
                //if not found
                    //"That student was not found in our database.
                    // Would you like to add them?"
                        //(Y)es
                            //addStudent()
                        //(N)o
                            //main menu
        //D)Student lookup
            //student = studentLookup()
            //if student != null
//*****************************While Loop***************************************
                //student.displayStudent()
                //println()
                //"A)Edit information"
                //"B)See Ideas"
                //"C)Delete record"
                //"D)Return to main menu"
                    //if A
        //*********************sub-While Loop A****************************************
                        //"Would you like to change the email and name for this student?"
                            //(Y)es
                                //answer1="Please enter a new name for the student"
                                //answer2="Please enter a new username for the student"
                                //"The record has been changed! Have a nice day! :)"
                                //goes back to student lookup menu
                            //(N)o
                                //goes back to student lookup menu
        //***********************A****************************************************
                    //else if (B)
        //********************sub-While Loop b*************************************
                        //student.displayIdeas()
                        //"Please enter Q to quit"
        //***********************B*************************************************
                    //if c
        //********************sub-While Loop c*************************************
                        //idea.



            //if student==null:
                //"That student was not found in out databse."
                //"Would you like to add them?"
                    //(Y)es
                        //addStudent()
                    //(N)o
                        //main
        //*************************************************************************


        //addStudent()
            //name = "Please enter student name"
            //SSN = "Please enter 4 digit student SSN"
            //studentNumber= "Please enter 4 digit student number"
            //username= "Please enter student email address"
            //Student newStudent = new Student(name, username, SSN, studentNumber)
            //ideas.addStudent(newStudent)
            //"The student has been added to the database"


        //studentLookup()
            //"Would you like to search by SSN or student number?"
                //(A) SSN
                //(B) student number
            //"What is the (SSN or student number depending)?"
                //search the appropriate BST
                //if found
                    //return Student
                //if not

                    //return null
*/

    } // MAIN


} // Thinktank
