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
            System.out.println("¡Muy mal magico mas!");
        } // catch

        Scanner hiveMind = new Scanner(System.in);

        boolean quit = false;
        String answer;
        Idea bestIdea;

        System.out.println("> Welcome, we are hiveMind and we are here to help you!\n");

        while(quit==false){
            System.out.println();
            System.out.println("=== Main Menu ===");
            System.out.println("> Please pick a function from one of the following. (Enter Q to save and quit)");
            System.out.println("  A) Top Idea\n  B) Add Idea\n  C) Student Records");
            System.out.print(": ");
            answer = hiveMind.nextLine();
            //System.out.println("+"+answer+"+");  <---debug
            if (answer.equals("A")||answer.equals("a")){
                System.out.println();
                bestIdea=ideas.getBestIdea();
                if(bestIdea==null){
                    quit=false;
                } // if bestidea is null
                else{
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
                } // else
            } // if 146

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
                    System.out.println("> Please enter a rating for the idea.") ;
                    System.out.println("> Enter a number in range 0-100.");
                    System.out.print(": ");
                    String ratingString=hiveMind.nextLine();
                    boolean done=false;
                    int rating;
                    while(done!=false){
                        try{
                            rating = Integer.parseInt(ratingString);

                            done=true;
                        } // try
                        catch(NumberFormatException ex){
                            System.out.println("! Please enter an INTEGER between 0-100");
                        } // catch
                        System.out.print(": ");
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
                    System.out.println("!!! That student was not found in our database !!! \n If you want to add the student, or try to search the student by their ID number, press C in the main menu.");
                    System.out.println("Returning to main menu");
                    System.out.println();
                    quit = false;
                } // else
            }//else if
            else if (answer.equals("C")||answer.equals("c")){
                boolean done = false;
                while(done==false){
                    System.out.println();
                    System.out.println("=== Student Records ===\n  A) Add Student\n  B) Student Lookup\n  C) Main Menu");
                    System.out.print(": ");
                    answer = hiveMind.nextLine();
                    if (answer.equals("A")|| answer.equals("a")){
                        System.out.println();
                        System.out.println("> Please enter the student's name");
                        String name=hiveMind.nextLine();
                        System.out.println();
                        //SSN = "Please enter 4 digit student SSN"
                        System.out.println("> Please enter the last 4 digits of the student's Social Security Number");
                        String ssnStr=hiveMind.nextLine();
                        int ssn=Integer.parseInt(ssnStr);
                        while (ssn>9999) {
                            System.out.println();
                            System.out.println("> That was larger than 4 digits. Please enter only a 4 digit SSN number");
                            ssnStr=hiveMind.nextLine();
                            ssn=Integer.parseInt(ssnStr);
                        } // while
                        //studentNumber= "Please enter 4 digit student number"
                        System.out.println();
                        System.out.println("> Please enter the 4 digit student ID number");
                        String idStr=hiveMind.nextLine();
                        int idNum=Integer.parseInt(idStr);
                        while (idNum>9999) {
                            System.out.println();
                            System.out.println("! That was larger than 4 digits. Please enter only a 4 digit ID number");
                            idStr=hiveMind.nextLine();
                            idNum=Integer.parseInt(idStr);
                        } // while
                        //username= "Please enter student email address"
                        System.out.println();
                        System.out.println("> Now please enter the student email user name");
                        String userNum= hiveMind.nextLine();
                        Student newStudent = new Student(name, userNum, ssn, idNum);
                        ideas.addStudent(newStudent);
                        System.out.println();
                        System.out.println("> The student has been added to the database");
                        System.out.println();
                        done=true;
                    } // if 229
                    else if(answer.equals("B")||answer.equals("b")){
                        System.out.println();
                        System.out.println("=== Student Search ===");
                        System.out.println("> Would you like to search or by SSN or studentID?\n  A) SSN\n  B) StudentID");
                        boolean finished=false;
                        Student foundStudent=null;
                        while(finished==false){

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
                                finished=true;
                            } // if

                            else if(answer.equals("B")||answer.equals("b")){
                                System.out.println();
                                System.out.println("> Please enter the last 4 digits of the student's ID");
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
                                finished=true;
                            } // else if
                            else{
                                System.out.println("! Not a menu option.");
                            } // else
                        }//while loop (239)

                        if(foundStudent==null){
                            System.out.println("! There is no student in our records with that number. Returning to main menu");
                            System.out.println();
                            finished=true;
                        } // if
                        else{
                            foundStudent.displayStudent();
                            boolean completed=false;
                            System.out.println("=== Student Options ===");
                            System.out.println("  A) Edit Information\n  B) See Ideas\n  C) Delete Record\n  D) Return to Main Menu");
                            System.out.println();
                            while(completed==false){
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
                                    completed=true;
                                } // if 333
                                else if(answer.equals("B")||answer.equals("b")){
                                    foundStudent.displayIdeas();

                                } // else if
                                else if(answer.equals("C")||answer.equals("c")){
                                    System.out.println();
                                    ideas.deleteStudent(foundStudent);
                                    completed=true;
                                } // else if
                                else if(answer.equals("D")||answer.equals("d")){
                                    System.out.println();
                                    completed=true;
                                } // else if
                            } // while 330
                        } // else 242
                    } // else if 185
                    else if(answer.equals("C")||answer.equals("c")){
                        done=true;
                    }
                }//while loop 143
            }
            else if (answer.equals("Q")){
                quit=true;
            } // else if
            else{
                System.out.println();
                System.out.println("! Not a valid menu choice. Please chose again.");
                System.out.println();
            } // else
        } // big big while loop

    // Strap in, it's time to save
    try ( // Try doing it all again in reverse order
        OutputStream file = new FileOutputStream("savedstate.ser");
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
    ) {
        output.writeObject(ideas);
    }
    catch(IOException x) {
        System.out.println("Bad magic happened! Can't output the file.");
    }


    System.out.println("Closing. Have a nice day!");

    } // MAIN

} // Thinktank
