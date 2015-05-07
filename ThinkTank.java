
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner; // For scanning, obviously.

import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!
import java.io.FileWriter; //for WRiting, obcviously

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
            sc.useDelimiter("=|\n"); // Set the delimiter
            System.out.println();

            String key = sc.next().trim(); // think
            String val = sc.next().trim(); // tank

            // If the header is correct, go ahead and load the save file
            if (key.trim().equals("think") && val.trim().equals("tank")) {

                System.out.println("Loading saved state...");

                key = sc.next(); val = sc.next(); // Move the holders to date=01 Jan

                System.out.println("Save state validation no.: " + val + ".");

                System.out.println("Loading students into database...");
                key = sc.next().trim(); val = sc.next().trim(); // Move the holders forward to segment=student

                // Start looping through the save file
                boolean looping = true; // Loops as long as looping is true
                while (looping) {

                    // Loop through the things
                    if (key.trim().equals("segment") && val.trim().equals("student")) {

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

                        } // while key is student
                    } // if key is segment

                    // Loop through the things
                    if (key.equals("segment") && val.equals("idea")) {
                        System.out.println("Loading ideas into database...");
                        key = sc.next().trim(); val = sc.next().trim(); // Move the holders forward

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
                            ideas.reInsertIdea(newIdea);

                        } // while key is idea
                    } // if key is segment

                    // else if key is end
                    else if (key.trim().equals("END") && val.trim().equals("END")) {
                        looping = false;
                    } // else if

                    if (sc.hasNext()) {
                        key = sc.next().trim(); val = sc.next().trim(); // Move the holders forward to segment=student
                    }

                } // while

                System.out.println("> Loaded!");

            } // if key and val are think tank

            else { // Case for broken databases and nonexistent saves
                System.out.println("! Saved state does not exist or is damaged.") ;
                System.out.println("> Starting a new database...");
            } // else

            // If we've made it this far, it must be time to close the scanner, so...
            sc.close();
        } // try

        catch (IOException x) {
            System.out.println("! Got an IOException: " + x.getMessage());
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
                }
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
                        }
                        catch(IOException e){
                            System.out.println("! Output error: "+e);
                        }
                    }
                }
            }
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
                }
                Student student = ideas.getStudent(ssn);
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
                        }
                        catch(NumberFormatException ex){
                            System.out.println("! Please enter an INTEGER between 0-100");
                        }
                        System.out.print(": ");
                        ratingString=hiveMind.nextLine();
                    }
                    rating = Integer.parseInt(ratingString);
                    if (rating>100){
                        rating=100;
                    }
                    Idea newIdea=new Idea(ssn,ideaText,rating);
                    ideas.insertIdea(newIdea);

                }
                else{
                    System.out.println("! That student was not found in our database, returning to main menu.");
                    System.out.println();
                    quit = false;
                }

            }//submit idea
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
                        }
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
                        }
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
                    }
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
                                }
                                foundStudent=ideas.getStudent(ssn);
                                finished=true;
                            }

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
                                }

                                foundStudent=ideas.getStudent(id);
                                finished=true;

                            }
                            else{
                                System.out.println("! Not a menu option.");
                            }
                        }//while loop (239)

                        if(foundStudent==null){
                            System.out.println("! There is no student in our records with that number. Returning to main menu");
                            System.out.println();
                            finished=true;
                        }
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
                                }
                                else if(answer.equals("B")||answer.equals("b")){
                                    foundStudent.displayIdeas();

                                }
                                else if(answer.equals("C")||answer.equals("c")){
                                    System.out.println();
                                    ideas.deleteStudent(foundStudent);
                                    completed=true;
                                }
                                else if(answer.equals("D")||answer.equals("d")){
                                    System.out.println();
                                    completed=true;
                                }
                            }
                        }
                        done=true;
                    }
                    else if(answer.equals("C")||answer.equals("c")){
                        done=true;
                    }
                    else{
                        System.out.println();
                        System.out.println("! Not a valid menu choice. Please chose again.");
                        System.out.println();
                    }

                }
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
            }
            else if (answer.equals("Q")){
                quit=true;
            }
            else{
                System.out.println("! Not a menu selection. Please try again");
            }
        }

    System.out.println("> Getting ready to exit...");
    System.out.println("Writing database to disk...");


    // Write save file out
    try {
        PrintWriter out = new PrintWriter(new FileWriter("savedstate.txt"));
        Calendar calendar = new GregorianCalendar();
        out.write("think = tank\n");
        out.write("verify = " + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.MONTH)+"\n");

        // Write out students
        System.out.println("Writing student data...");
        out.write("segment = student\n");
        ideas.printStudents(out);
        System.out.println("Student data done.");

        // Write out ideas
        System.out.println("Writing idea database...");
        out.write("segment = idea\n");
        ideas.printIdeas(out);
        System.out.println("Idea database done.");
        out.write("END = END");
        out.close();
    }
    catch (IOException x) {
        System.out.println("! Bad magic happened!!!");
    }

    System.out.println("Closing. Have a nice day!");

    } // MAIN

} // Thinktank
