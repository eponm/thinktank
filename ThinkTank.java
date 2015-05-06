
import java.util.Scanner; // For scanning, obviously.

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


class ThinkTank {

    private final static Charset ENCODING = StandardCharsets.UTF_8; // Change encoding type here
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


        // Load a scanner
        Scanner sc =  new Scanner(saveFile, ENCODING.name())

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


    } // MAIN


    private String[] readLine(Scanner sc) {
        String key;
        String val;
        while (sc.hasNextLine()){
            sc.useDelimiter("="); // Tell the scanner to delimit on each equals sign
            if (sc.hasNext()) { // Get the next line if there is one
                key = sc.next().trim();
                val = sc.next().trim();
            } // if
            else { // If there is no line
                key = "Invalid key or line";
                val = "Invalid value or line";
            } // else
            String[] cont = new String[2];
            cont[0] = key;
            cont[1] = val;
        } // while
    }

} // Thinktank
