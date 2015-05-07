
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

class Student {

    private String name;
    private String username;
    private int socSecNum; // Must be 0001–9999
    private int studentID; // Must be 0001–9999
    private Queue ideaQueue; // This may need to be its own object.
    private float avgIdeaRating;

    private Student sleftChild;
    private Student srightChild;
    private Student sparentNode;

    private Student nleftChild;
    private Student nrightChild;
    private Student nparentNode;


    // Constructor
    // Params: Last name, short username, 4-digit SSN, 4-digit student number.
    public Student(String nameIn, String usernameIn, int socSecNumIn, int studentIDIn) {

        // Place all of the input data into instance variables.
        name = nameIn;
        username = usernameIn; // Checks for 4-digit-ness should be done beforehand
        socSecNum = socSecNumIn;
        studentID = studentIDIn;
        Queue ideaQueue = new Queue(10);
        leftChild=null;
        rightChild=null;

    } // Constructor


    //displayStudent()
    // to display student information as described in specifications
    public void displayStudent(){
        System.out.println("Name: "+name);
        System.out.println("    Username: "+username);
        System.out.println("    Average Idea Rating: "+avgIdeaRating);
        System.out.println("    Student ID: "+studentID);
        System.out.println("    Social Security Number: "+socSecNum);
    } // displayStudent

    //displayIdeas()
    // to display 10 recent ideas
    public void displayIdeas(){
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
        ideaQueue.enqueue(newIdea);
        avgIdeaRating=0;
        int i=0;
        Idea[] ideas = ideaQueue.getAll();
        while(ideas[i]!=null){
            avgIdeaRating=avgIdeaRating+ideas[i].getRating();
            i++;
        } // while
        avgIdeaRating=avgIdeaRating/i;
    }//adds Idea to Queue and re-averages avgIdeaRating

    // Following are for SSN trees

    //getLeft()
    //gets leftChild
    public Student getLeftS(){
        return sleftChild;
    }//gets leftChild

    //getRight()
    //gets rightChild
    public Student getRightS(){
        return srightChild;
    }//gets rightChild

    public Student getParentS(){
        return sparentNode;
    }//gets rightChild


    // Following are for Student Number trees

    //getLeft()
    //gets leftChild
    public Student getLeftN(){
        return nleftChild;
    }//gets leftChild

    //getRight()
    //gets rightChild
    public Student getRightN(){
        return nrightChild;
    }//gets rightChild

    public Student getParentN(){
        return nparentNode;
    }//gets rightChild


    //getKey()
    //returns the last 4 digits of the SSN
    public int getKey(){
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
    public int getStudentID(){
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
    public Student setLeftS(Student nextStudent) {
        sleftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightS(Student nextStudent) {
        srightChild = nextStudent;
        return this;
    } // setRightChild

    public Student setParentS(Student nextParent) {
        sparentNode = nextParent;
        return this;
    } // setParentS


    // Set the left child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setLeftN(Student nextStudent) {
        nleftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightN(Student nextStudent) {
        nrightChild = nextStudent;
        return this;
    } // setRightChild

    public Student setParentN(Student nextParent) {
        nparentNode = nextParent;
        return this;
    } // setParentN



} // Student
