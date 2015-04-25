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

    StringBuffer surname;
    StringBuffer username;
    int socSecNum; // Must be 0001–9999
    int studentID; // Must be 0001–9999
    //Queue ideaQueue; // This may need to be its own object.
    float avgIdeaRating;

    Node leftChild;
    Node rightChild;
    Node parentNode;

    // Constructor
    // Params: Last name, short username, 4-digit SSN, 4-digit student number.
    public Student(StringBuffer surnameIn, StringBuffer usernameIn, int socSecNumIn, int studentIDIn) {

        // Place all of the input data into instance variables.
        surname = surnameIn;
        username = usernameIn; // Checks for 4-digit-ness should be done beforehand
        socSecNum = socSecNumIn;
        studentID = studentIDIn;

    } // Constructor


//  REQUIRED METHODS:
    //displayStudent()
        // to display student information as described in specifications
    //displayIdeas()
        // to display 10 recent ideas
    //deleteStudent()
        // to delete record (unsure if should delete ideas as well)
    //editNames()
        // to change last name and username


    // Set the left child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setLeftChild(Student nextStudent) {
        leftChild = nextStudent;
        return this;
    } // setLeftChild


    // Set the right child of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setRightChild(Node nextStudent) {
        rightChild = nextStudent;
        return this;
    } // setRightChild


    // Set the parent of a Student in a BST to nextStudent.
    // NOTE: this returns the Student with the modified pointers.
    public Student setParent(Student nextStudent) {
        leftChild = nextStudent;
        return this;
    } // setParent



} // Student
