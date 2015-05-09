
/* Student.java

    Each student includes:
        (Input) Last name (of type StringBuffer)
        (Input) Username (of type StringBuffer)
        (Input) 4-digit SSN (of type int)
        (Input) 4-digit ID (of type int)
        10 most recent ideas (a Queue object)
        Average rating of 10 most recent ideas (of type float)

    All of the above are simple data figures, except the 10 most recent ideas.
    The 10 most recent ideas will be a queue of pointers to the ideas stored in an Ideaheap.
    The average score is recomputed every time the queue changes.

*/

import java.io.Serializable;

class Student { // implements Serializable

    private String name;
    private String username;
    private int socSecNum; // Must be 0001–9999
    private int studentID; // Must be 0001–9999
    private Queue ideaQueue = new Queue(); // This may need to be its own object.
    private float avgIdeaRating;

    private Student ssnLeftChild;
    private Student ssnRightChild;
    private Student ssnParent;

    private Student idLeftChild;
    private Student idRightChild;
    private Student idParent;


    // Constructor
    // Params: Last name, short username, 4-digit SSN, 4-digit student number.
    public Student(String nameIn, String usernameIn, int socSecNumIn, int studentIDIn) {

        // Place all of the input data into instance variables.
        name = nameIn;
        username = usernameIn; // Checks for 4-digit-ness should be done beforehand
        socSecNum = socSecNumIn;
        studentID = studentIDIn;
        //Queue ideaQueue = new Queue();

        ssnLeftChild = null;
        ssnRightChild = null;
        ssnParent = null;


        idLeftChild = null;
        idRightChild = null;
        idParent = null;

    } // Constructor


    //displayStudent()
    // to display student information as described in specifications
    public void displayStudent(){
        System.out.println("Name: "+name);
        System.out.println("    Username: "+username);
        System.out.println("    Average Idea Rating: "+avgIdeaRating);
        System.out.println("    Student ID: "+studentID);
        String socString;
        if (socSecNum<100) {
            socString= "00" + Integer.toString(socSecNum);
        }
        else if (socSecNum<1000) {
            socString="0" + Integer.toString(socSecNum);
        }
        else {
            socString=Integer.toString(socSecNum);
        }
        System.out.println("    Social Security Number: "+socString);
    } // displayStudent

    //displayIdeas()
    // to display 10 recent ideas
    public void displayIdeas(){
        if (ideaQueue.isEmpty()==true) {
            System.out.println("This student has no ideas");
        }
        for(int i=0;i<10;i++){
            Idea[] ideas = ideaQueue.getAll();
            Idea x = ideas[i];
            if(x==null){
                System.out.println((i+1)+".");
            } // if
            else{
                System.out.println((i+1)+". "+"Rating: "+x.getRating()+" Description: "+x.getDesc());
            } // else
        } // for
    }//display Ideas in queue

    //addToQueue()
    //adds Idea to Queue and re-averages avgIdeaRating
    public void addToQueue(Idea newIdea){
        System.out.println(ideaQueue);
        ideaQueue.enqueue(newIdea);
        int total = 0;
        Idea[] ideas = ideaQueue.getAll();
        for (int i=0; i<ideaQueue.getSize()-1; i++) {
            total = total + ideas[i].getRating();
        } // while
        avgIdeaRating=avgIdeaRating / ideaQueue.getSize();
    }//adds Idea to Queue and re-averages avgIdeaRating

    // Following are for SSN trees

    //getLeft()
    //gets leftChild
    public Student getLeftSSN(){
        return ssnLeftChild;
    }//gets leftChild

    //getRight()
    //gets rightChild
    public Student getRightSSN(){
        return ssnRightChild;
    }//gets rightChild

    public Student getParentSSN(){
        return ssnParent;
    }//gets rightChild


    // Following are for Student Number trees

    //getLeft()
    //gets leftChild
    public Student getLeftID(){
        return idLeftChild;
    }//gets leftChild

    //getRight()
    //gets rightChild
    public Student getRightID(){
        return idRightChild;
    }//gets rightChild

    public Student getParentID(){
        return idParent;
    }//gets rightChild


    //getKey()
    //returns the last 4 digits of the SSN
    public int getSSN(){
        return socSecNum;
    }//getKey()

    //getName
    //returns name
    public String getName(){
        return name;
    }//getName

    //getUsername
    //returns username
    public String getUsername(){
        return username;
    }//getUsername

    //getStudentID
    //returns Student ID
    public int getID(){
        return studentID;
    }//getSudentID

    //editNames()
    // to change last name and username
    public Student editNames(String lastName, String newUsername){
        name=lastName;
        username=newUsername;
        return this;
    } // editNames


    // Set the left child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setLeftSSN(Student nextStudent) {
        ssnLeftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightSSN(Student nextStudent) {
        ssnRightChild = nextStudent;
        return this;
    } // setRightChild

    public Student setParentSSN(Student nextParent) {
        ssnParent = nextParent;
        return this;
    } // setParentS


    // Set the left child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setLeftID(Student nextStudent) {
        idLeftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightID(Student nextStudent) {
        idRightChild = nextStudent;
        return this;
    } // setRightChild

    public Student setParentID(Student nextParent) {
        idParent = nextParent;
        return this;
    } // setParentN

    public int getQueueSize() {
        return ideaQueue.getSize();
    }

} // Student
