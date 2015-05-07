
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

        // Load a scanner
        try {
            Scanner sc = new Scanner(saveFile, ENCODING.name());

            String key;
            String val;

            System.out.println(sc.nextLine());//debug
        }

        catch (IOException x) {
            System.out.println("Got an IOException: " + x.getMessage());
        }
        Scanner hiveMind = new Scanner(System.in);

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
        System.out.println("Welcome, we are hiveMind and we are here to help you!\nPlease pick a function from one of the following. (Enter Q to save and quit)");
        System.out.println("A)Top Idea\nB)Add Idea\nC)Student Lookup");
        boolean quit = false;
        String answer;
        Idea bestIdea;
        while(quit==false){
            System.out.println();
            System.out.print("--------> ");
            answer = hiveMind.nextLine();
            //System.out.println("+"+answer+"+");  <---debug
            if (answer.equals("A")||answer.equals("a")){
                System.out.println();
                bestIdea=ideas.getBestIdea();
                if(bestIdea==null){
                    quit=false;
                }
                else{
                    System.out.println("Would you like to sell this idea?\n(Y)es\n(N)o");
                    System.out.print("--------> ");
                    answer = hiveMind.next();
                    if (answer.equals("Y")||answer.equals("y")){
                        bestIdea = ideas.sell();
                        try {
                            PrintWriter scribe = new PrintWriter("BestIdea.txt");
                            scribe.println(bestIdea.getDesc());
                            scribe.close();
                            Path bestIdeaPath = Paths.get("BestIdea.txt");
                            System.out.println(bestIdeaPath);
                        }
                        catch(IOException e){
                            System.out.println("Output error: "+e);
                        }
                    }
                }
            }
            else if (answer.equals("B")||answer.equals("b")){


            }
            else if (answer.equals("C")||answer.equals("c")){
                //student=studentLookup();
                if(student==null){
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
                }
            }
            else if (answer.equals("Q")){
                quit=true;
            }
            else{
                System.out.println("Not a menu selection. Please try again");
            }
        }
        //dataBase.save()

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
    public Student studentLookup(IdeaDB dataBase){
        Scanner drone = new Scanner(System.in);
        System.out.println("Would you like to search by SSN or studentID?\nA)SSN\nB)StudentID");
        boolean quit==false
        String answer;
        while(quit==false){
            System.out.println();
            System.out.print("---------> ");
            answer = drone.next();
            if(answer.equals("A")||answer.equals("a")){
                System.out.println();
                System.out.println("Please enter the last 4 digits of the student's SSN");
                System.out.print("------->");
                int ssn = drone.nextInt();
                while(ssn>9999){
                    System.out.println("The SSN must be 4 digits");
                    System.out.print("------->");
                    int ssn = drone.nextInt();
                }
                Student foundStudent=dataBase.getStudent(ssn, true); 
                quit=true
            }
            else if(answer.equals("B")||answer.equals("b")){
                System.out.println();
                System.out.println("Please enter the last 4 digits of the student's SSN");
                System.out.print("------->");
                int id = drone.nextInt();
                while(id>9999){
                    System.out.println("The ID must be 4 digits");
                    System.out.print("------->");
                    int id = drone.nextInt();
                }
                Student foundStudent=dataBase.getStudent(id, false);
                quit=true 

            }
            else{
                System.out.println("Not a menu option.");
            }
        }
        return foundStudent;
    }


} // Thinktank
