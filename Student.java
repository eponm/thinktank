
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

    String name;
    String username;
    int socSecNum; // Must be 0001–9999
    int studentID; // Must be 0001–9999
    Queue ideaQueue; // This may need to be its own object.
    float avgIdeaRating;

    Student leftChild;
    Student rightChild;
    Student parentNode;
    // Constructor
    // Params: Last name, short username, 4-digit SSN, 4-digit student number.
    public Student(String nameIn, String usernameIn, int socSecNumIn, int studentIDIn) {

        // Place all of the input data into instance variables.
        name = nameIn;
        username = usernameIn; // Checks for 4-digit-ness should be done beforehand
        socSecNum = socSecNumIn;
        studentID = studentIDIn;
        Queue ideaQueue = new Queue(10);

    } // Constructor


    //displayStudent()
    // to display student information as described in specifications


    //displayIdeas()
    // to display 10 recent ideas


    //addToQueue(Idea newIdea)
    // to add an idea into the student's queue
    // also recalculates average rating of student's 10 ideas

    //editNames()
    // to change last name and username
    public Student editNames(String lastName, String newUsername){
        name=lastName;
        username=newUsername;
        return this;
    }


    // Set the left child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setLeftChild(Student nextStudent) {
        leftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightChild(Student nextStudent) {
        rightChild = nextStudent;
        return this;
    } // setRightChild


    // Set the parent of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setParent(Student nextStudent) {
        parentNode = nextStudent;
        return this;
    } // setParent



} // Student
