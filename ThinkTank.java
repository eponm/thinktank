
import java.util.Scanner; // For scanning, obviously.

import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!

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
            } // else

            // If we've made it this far, it must be time to close the scanner, so...
            sc.close();
        } // try

        catch (IOException x) {
            System.out.println("Got an IOException: " + x.getMessage());
        } // catch


        Scanner hiveMind = new Scanner(System.in);

        /* Menu structure

        |——v Top idea
        |  |——> Sell idea
        |——> Add idea
        |——v Student lookup
        |  |——> Print ideas
        |  L——> Print record
        L——> Print all students     */

        System.out.println("Welcome, we are hiveMind and we are here to help you!\nPlease pick a function from one of the following. (Enter Q to save and quit)");
        System.out.println("A)Top Idea\nB)Add Idea\nC)Student Lookup");
        boolean quit = false;
        String answer;
        Idea bestIdea;

        while(quit==false) {
            System.out.println();
            System.out.print(": ");
            answer = hiveMind.nextLine();
            //System.out.println("+"+answer+"+");  <---debug

            if (answer.equals("A")||answer.equals("a")) {
                System.out.println();
                bestIdea=ideas.getBestIdea();
                if(bestIdea==null){
                    quit=false;
                } // if

                else {
                    System.out.println("Would you like to sell this idea?\n(Y)es\n(N)o");
                    System.out.print(": ");
                    answer = hiveMind.next();
                    if (answer.equals("Y")||answer.equals("y")) {
                        bestIdea = ideas.sell();
                        try {
                            PrintWriter scribe = new PrintWriter("BestIdea.txt");
                            scribe.println(bestIdea.getDesc());
                            scribe.close();
                            Path bestIdeaPath = Paths.get("BestIdea.txt");
                            System.out.println(bestIdeaPath);
                        } // try
                        catch(IOException e) {
                            System.out.println("Output error: "+e);
                        } // catch
                    } // if
                } // else
            } // if

            else if (answer.equals("B")||answer.equals("b")) {

                //C)Add Idea
                System.out.println("Please enter the one word username of the student who owns the idea.  If you wish to create a student, just press enter: ");
                String ans=hiveMind.nextLine();
                //student = studentLookup()
                //System.out.println("debugging--studentlookup!!");
                //Student student=studentLookup(answer); //!!!!!!!!!!!!!!!
                //if student!=null
                if (!ans.equals("")) {
                    System.out.println("making student for debug");
                    Student student=new Student("jess", "jspencer", 3434 , 1313);
                    //ideaText="Please imput idea"
                    System.out.println("Please imput idea description on the following line: ");
                    String ideaText=hiveMind.nextLine();
                    //rating="Please enter a rating for the idea (0 to 100)"

                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    System.out.println("Please enter a rating for the idea. Only enter numbers 0-100. If you don't enter a number, hiveMind will die: ");
                    String ratingString=hiveMind.nextLine();
                     //if over 100 rating==100
                    int rating=Integer.parseInt(ratingString);
                    if (rating>100) {
                        rating=100;
                    } // if
                    //newIdea = new Idea(SSN, ideaText, rating)
                    System.out.println("error here-- answer is not a student, cannot getKey()");
                    Idea newIdea=new Idea(student.getKey(),ideaText,rating);
                    //ideas.insertIdea(newIdea)
                    ideas.insertIdea(newIdea);
                } // if
                else {
                    System.out.println("That student was not found in our database. Would you like to add them? (on the following line, enter Y for yes or N for no)");
                    answer=hiveMind.nextLine();
                        //(Y)es
                    if (answer.equals("Y")|| answer.equals("y") ) {
                        System.out.println("Please enter the student's name");
                        String name=hiveMind.nextLine();
                        //SSN = "Please enter 4 digit student SSN"
                        System.out.println("Please enter the last 4 digits of the student's Social Security Number");
                        String ssnStr=hiveMind.nextLine();
                        int ssn=Integer.parseInt(ssnStr);
                        while (ssn>9999) {
                            System.out.println("That was larger than 4 digits. Please enter only a 4 digit SSN number");
                            ssnStr=hiveMind.nextLine();
                            ssn=Integer.parseInt(ssnStr);
                        } // while
                    } // if
                    //studentNumber= "Please enter 4 digit student number"
                    System.out.println("Please enter the 4 digit student ID number");
                    String idStr=hiveMind.nextLine();
                    int idNum=Integer.parseInt(idStr);
                    while (idNum>9999) {
                        System.out.println("That was larger than 4 digits. Please enter only a 4 digit ID number");
                        idStr=hiveMind.nextLine();
                        idNum=Integer.parseInt(idStr);
                    } // while
                    //username= "Please enter student email address"
                    System.out.println("Now please enter the student email user name");
                    String userNum= hiveMind.nextLine();
                    Student newStudent = new Student(name, userNum, ssn, idNum);
                    ideas.addStudent(newStudent);
                    System.out.println("The student has been added to the database");

                    else if (answer.equals("N")||answer.equals("n")) {
                        //(N)o
                        System.out.println("Okay. Please select from the main menu or press quit");
                        System.out.println("Welcome, we are hiveMind and we are here to help you!\nPlease pick a function from one of the following. (Enter Q to quit)");
                        System.out.println("A)Help Documentation\nB)Top Idea\nC)Add Idea\nD)Student Lookup");
                        //main menu
                    } // else if
                } // else
            } // else if

            else if (answer.equals("C")||answer.equals("c")){
                //student=studentLookup();
                if(student==null){
                    System.out.println("There is student in our records with that number. Returning to main menu");
                    quit=false;
                } // if
                else {
                    student.displayStudent();
                    boolean finished=false;
                    System.out.println();
                    System.out.println("A)Edit Information\nB)See Ideas\nC)Delete Record\nReturn to Main Menu");
                    while(finished=false) {
                        break; // FIX ME !
                    } // while
                } // else

           /*     if(student==null){
                    System.out.println("There is student in our records with that number. Returning to main menu");
                    quit=false;
                }
                else{
                    student.displayStudent();
                    boolean finished=false;
                    System.out.println();
                    System.out.println("A)Edit Information\nB)See Ideas\nC)Delete Record\nReturn to Main Menu");
                    while(finished=false){

                    }
                } */

                else if (answer.equals("Q")) {
                    quit=true;
                } // else if
                else {
                    System.out.println("Not a menu selection. Please try again");
                } // else
            } // else if
        }

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
                        //ideaDB.deleteStudent(student)
                        //return to main menu
                    //if d
                        //return to main menu
            //if student==null:
                //"That student was not found in out databse."
                //"Would you like to add them?"
                    //(Y)es
                        //addStudent()
                    //(N)o
                        //main
//*********************************end while loop**************************************

    } // MAIN


    public Student studentLookup(IdeaDB dataBase){
        Scanner drone = new Scanner(System.in);
        System.out.println("Would you like to search by SSN or studentID?\nA)SSN\nB)StudentID");
        boolean quit == false;
        String answer;
        while(quit==false) {
            System.out.println();
            System.out.print(": ");
            answer = drone.next();
            if(answer.equals("A")||answer.equals("a")){
                System.out.println();
                System.out.println("Please enter the last 4 digits of the student's SSN");
                System.out.print(": ");
                int ssn = drone.nextInt();
                while(ssn>9999){
                    System.out.println("The SSN must be 4 digits");
                    System.out.print(": ");
                    int ssn = drone.nextInt();
                }
                Student foundStudent=dataBase.getStudent(ssn, true);
                quit=true;
            }
            else if(answer.equals("B")||answer.equals("b")){
                System.out.println();
                System.out.println("Please enter the last 4 digits of the student's SSN");
                System.out.print(": ");
                int id = drone.nextInt();
                while(id>9999){
                    System.out.println("The ID must be 4 digits");
                    System.out.print(": ");
                    int id = drone.nextInt();
                }
                Student foundStudent=dataBase.getStudent(id, false);
                quit=true;

            }
            else{
                System.out.println("Not a menu option.");
            }
        }
        return foundStudent;
    }


} // Thinktank
