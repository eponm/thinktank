import java.util.Scanner; // For scanning, obviously.

import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!
import java.io.FileWriter; //for WRiting, obcviously

import java.io.*;

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


class ThinkTank {

    public static void main(String[] args) {

        System.out.println();

        final Charset ENCODING = StandardCharsets.UTF_8;
        final Path saveFile;

        IdeaDB ideas = new IdeaDB();

        // We're about to try loading a save file.
        // Buckle up, kids.

        try ( // Open up some parameters to try:
            // Make an input stream from the file
            InputStream file = new FileInputStream("savedstate.ser");
            // Load it into a buffer
            InputStream buffer = new BufferedInputStream(file);
            // Use the buffer for object input
            ObjectInput input = new ObjectInputStream (buffer);
        ) {
            // Now read it into a new object
            ideas = (IdeaDB)input.readObject();
        } // try
        // Catch the bad thing
        catch(ClassNotFoundException x) {
            System.out.println("Can't find the class. Maybe was bad magic??");
        } // catch
        catch(IOException x) {
            System.out.println("Saved state may not exist or is blank. Starting from scratch...");
        } // catch

        Scanner hiveMind = new Scanner(System.in);

        boolean quit = false;
        String answer;
        Idea bestIdea;

        System.out.println();
        System.out.println("> Welcome, we are hiveMind and we are here to help you!");


        //
        // TOP OF MENU SECTION
        //

        while(quit==false){

            //
            // MAIN MENU
            //
            System.out.println();
            System.out.println("=== Main Menu ===");
            System.out.println("> Please pick a function from one of the following:");

            System.out.println("  A) Top Idea\n  B) Add Idea\n  C) Student Records\n  Q) Save and quit");
            System.out.print(": ");
            answer = hiveMind.nextLine();
            //System.out.println("+"+answer+"+");  <---debug


            //
            // A: SELL IDEA
            //

            if (answer.equals("A")||answer.equals("a")){
                System.out.println();
                if (ideas.getBestIdea() == null){
                    System.out.println("! There are no ideas here.");
                } // if bestidea is null
                else {
                    System.out.println("> Would you like to sell this idea?\n  (Y)es\n  (N)o");
                    System.out.print(": ");
                    answer = hiveMind.next();
                    if (answer.equals("Y")||answer.equals("y")){
                        bestIdea = ideas.sell();
                        try {
                            PrintWriter scribe = new PrintWriter("BestIdea.txt");
                            scribe.println(bestIdea.getDesc());
                            scribe.close();
                            Path bestIdeaPath = Paths.get("BestIdea.txt");
                            System.out.println(bestIdeaPath);
                        } // try
                        catch(IOException e){
                            System.out.println("! Output error: "+e);
                        } // catch
                    } // if

                    else if (answer.equals("N")||answer.equals("n")) {
                        System.out.println("Maybe next time! \nReturning to menu...");
                    } // else if
                } // else
            } // if 146


            //
            // B: IDEA SUBMISSION
            //

            //submit idea
            else if (answer.equals("B")||answer.equals("b")){
                System.out.println();
                System.out.println("=== Submit Idea ===");
                System.out.println("> Please enter the last 4 digits of the submittor's SSN.");
                System.out.print(": ");
                String ans = hiveMind.nextLine();
                int ssn=Integer.parseInt(ans);
                while(ssn>10000){
                    System.out.println("! The submittor's SSN must be 4 digits.");
                    System.out.print(": ");
                    ans=hiveMind.nextLine();
                    ssn = Integer.parseInt(ans);
                } // while
                Student student = ideas.getStudent(ssn, true);

                if(student!=null){
                    System.out.println("> Please input idea description on the following line: ");
                    String ideaText=hiveMind.nextLine();
                    System.out.println("> Please enter an integer rating for the idea between 0 and 100.") ;
                    System.out.print(": ");
                    String ratingString=hiveMind.nextLine();
                    boolean ideaSubmissionDon=false;
                    int rating;
                    while(ideaSubmissionDon!=false){
                        try{
                            rating = Integer.parseInt(ratingString);
                            ideaSubmissionDon=true;
                        } // try
                        catch(NumberFormatException ex){
                            System.out.println("! Please enter an INTEGER between 0-100");
                        } // catch
                        System.out.println(": ");
                        ratingString=hiveMind.nextLine();
                    } // while
                    rating = Integer.parseInt(ratingString);
                    if (rating>100){
                        rating=100;
                    } // if
                    Idea newIdea=new Idea(ssn,ideaText,rating);
                    ideas.insertIdea(newIdea);

                } // if 186
                else{
                    System.out.println();
                    System.out.println("! SSN not found. Try adding that student first.");
                    System.out.println("Returning to main menu...");
                } // else
            }//else if


            //
            // C: STUDENT RECORDS MENU
            //

            else if (answer.equals("C")||answer.equals("c")){
                boolean recordsMenuDone = false;
                while(recordsMenuDone==false){
                    System.out.println();
                    System.out.println("=== Student Records ===\n  A) Add Student\n  B) Student Lookup\n  C) Main Menu");
                    System.out.print(": ");

                    answer = hiveMind.nextLine();

                    // STUDENT ENTRY

                    if (answer.equals("A")|| answer.equals("a")){
                        System.out.println();
                        System.out.println("> Student's name");
                        System.out.print(": ");
                        String name=hiveMind.nextLine();
                        //SSN = "Please enter 4 digit student SSN"
                        System.out.println("> Student's SSN (4 digits)");
                        System.out.print(": ");
                        String ssnStr=hiveMind.nextLine();
                        boolean ssnIsNum=false;
                        while(ssnIsNum==false){//while ssn imput isn't number
                            try {//try to parse imput as an int
                                int ssn=Integer.parseInt(ssnStr);
                                if(ssn<10000){
                                    ssnIsNum=true;
                                }
                                else{
                                    System.out.println("> That was larger than 4 digits. Please enter only a 4 digit SSN number");
                                    System.out.print(": ");
                                    ssnStr=hiveMind.nextLine();
                                }
                            }//try 192
                            catch(NumberFormatException e) {//if throws a numberformat exeception
                                System.out.println("> Please input a valid integer (4 digits)");
                                System.out.print(":");
                                ssnStr=hiveMind.nextLine();
                            }//catch 19
                        }//while 191
                        int ssn=Integer.parseInt(ssnStr);
                        System.out.println("> Student ID (4 digits)");
                        System.out.print(": ");
                        String idStr=hiveMind.nextLine();
                        boolean idIsNum=false;
                        while(idIsNum==false){//while id imput isn't number
                            try {//try to parse imput as an int
                                int id=Integer.parseInt(idStr);
                                if(id<10000){
                                    idIsNum=true;
                                }
                                else{
                                    System.out.println("> That was larger than 4 digits. Please enter only a 4 digit ID number");
                                    System.out.print(": ");
                                    idStr=hiveMind.nextLine();
                                }
                            }//try 218
                            catch(NumberFormatException e) {//if throws a numberformat exeception
                                System.out.println("> Please input a valid integer (4 digits)");
                                System.out.print(":");
                                idStr=hiveMind.nextLine();
                            }//catch 229
                        }//while 217
                        int idNum = Integer.parseInt(idStr);
                        //username= "Please enter student email address"
                        System.out.println("> Email Address too, please");
                        System.out.print(": ");
                        String userNum= hiveMind.nextLine();
                        Student newStudent = new Student(name, userNum, ssn, idNum);
                        ideas.addStudent(newStudent);
                        System.out.println("The student has been added to the database.");
                        System.out.println();
                        recordsMenuDone=true;
                    } // if 229


                    // STUDENT SEARCH

                    else if(answer.equals("B")||answer.equals("b")){
                        System.out.println();
                        System.out.println("=== Student Search ===");
                        System.out.println("> Would you like to search or by SSN or student ID?\n  A) SSN\n  B) StudentID");
                        boolean studentSearchMenuDone=false;
                        Student foundStudent=null;
                        while(studentSearchMenuDone==false){

                            System.out.print(": ");
                            answer = hiveMind.nextLine();

                            if(answer.equals("A")||answer.equals("a")){
                                System.out.println();
                                System.out.println("> Please enter the last 4 digits of the student's SSN");
                                System.out.print(": ");
                                String ssnStr = hiveMind.nextLine();
                                int ssn = Integer.parseInt(ssnStr);

                                while(ssn>9999){
                                    System.out.println("! The SSN must be 4 digits");
                                    System.out.print(": ");
                                    ssnStr = hiveMind.nextLine();
                                    ssn = Integer.parseInt(ssnStr);
                                } // while
                                foundStudent=ideas.getStudent(ssn, true);
                                studentSearchMenuDone=true;
                            } // if

                            else if(answer.equals("B")||answer.equals("b")){

                                System.out.println("> Please enter the student ID");
                                System.out.print(": ");
                                String idStr = hiveMind.nextLine();
                                int id = Integer.parseInt(idStr);

                                while(id>9999){
                                    System.out.println();
                                    System.out.println("! The ID must be 4 digits");
                                    System.out.println();
                                    System.out.print(": ");
                                    idStr = hiveMind.nextLine();
                                    id = Integer.parseInt(idStr);
                                } // while

                                foundStudent=ideas.getStudent(id, false);
                                studentSearchMenuDone=true;
                            } // else if
                            else{
                                System.out.println("! Not a menu option.");
                            } // else
                        }//while loop (239)

                        if(foundStudent==null){
                            System.out.println("! There is no student in our records with that number.");
                            System.out.println("Returning to main menu...");
                            System.out.println();
                            studentSearchMenuDone=true;
                        } // if

                        else{
                            foundStudent.displayStudent();
                            boolean studentOptionsMenuDone=false;
                            System.out.println("=== Student Options ===");
                            System.out.println("  A) Edit Information\n  B) See Ideas\n  C) Delete Record\n  D) Return to Main Menu");
                            System.out.println();
                            while(studentOptionsMenuDone==false) {
                                System.out.print(": ");
                                answer = hiveMind.nextLine();
                                if(answer.equals("A")||answer.equals("a")){

                                    System.out.println("> Please enter the new last name for the selected student.");
                                    System.out.print(": ");
                                    String newName = hiveMind.nextLine();
                                    System.out.println();
                                    System.out.println("> Please enter the new email for the student.");
                                    System.out.print(": ");
                                    String newUsername = hiveMind.nextLine();
                                    foundStudent.editNames(newName,newUsername);
                                    System.out.println("> The student has been updated!");
                                    studentOptionsMenuDone=true;
                                } // if 333
                                else if(answer.equals("B")||answer.equals("b")){
                                    foundStudent.displayIdeas();
                                    studentOptionsMenuDone=true;

                                } // else if
                                else if(answer.equals("C")||answer.equals("c")){
                                    System.out.println();
                                    ideas.deleteStudent(foundStudent);
                                    studentOptionsMenuDone=true;
                                } // else if
                                else if(answer.equals("D")||answer.equals("d")){
                                    System.out.println();
                                    studentOptionsMenuDone=true;
                                } // else if
                            } // while 330
                        } // else 242
                    } // else if 185

                    // RETURN TO MENU

                    else if(answer.equals("C")||answer.equals("c")){
                        recordsMenuDone=true;
                    }
                }//while loop
            }

            //
            // Q: QUIT, OBVIOUSLY
            //

            else if (answer.equals("Q") || answer.equals("q")){
                quit=true;
            } // else if
            else{
                System.out.println();
                System.out.println("! Not a valid menu choice. Please chose again.");
                System.out.println();
            } // else
        } // big big while loop


    // GO TIME

    // Strap in, it's time to save
    try { // Try doing it all again in reverse order
        OutputStream file = new FileOutputStream("savedstate.ser");
        System.out.println("File output stream OK");//debug
        OutputStream buffer = new BufferedOutputStream(file);
        System.out.println("Buffered output stream OK");//debug
        ObjectOutput output = new ObjectOutputStream(buffer);
        System.out.println("Object output stream OK");//debug
        output.writeObject(ideas);
        System.out.println("Files written OK");//debug
    }
    catch(IOException x) {
        System.out.println("Bad magic happened! Can't output the file. " + x);
    }


    System.out.println("\nClosing. Have a nice day!\n");

    } // MAIN

} // Thinktank
