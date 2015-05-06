/* Idea.java

    Each Idea includes:
        (Generated) An idea number
        (Given) The SSN of the student who submitted it
        (Given) A description
        (Given) A rating

        All of the above are pretty simple.

*/

class Idea {

    // To see whether or not an idea was in the heap upon last exit
    boolean inHeap = false;

    int seqNum; // Assigned sequentially beginning from 0001
    int submittorKey;
    String description;
    int rating; // Must be 000–100

    // For list traversal
    Idea prior = null;
    Idea next = null;

    // Constructor
    // Params: idea number, submittor SSN, a string description, and a rating.
    public Idea(int seqNumIn, int submittorKeyIn, String descriptionIn, int ratingIn) {
        seqNum = seqNumIn;
        submittorKey = submittorKeyIn;
        description = descriptionIn;
        rating = ratingIn;
    } // Constructor


    /*********************
    *   ACCESS METHODS   *
    *********************/

    // Access the idea's next idea in a list
    public Idea getNext() {
        return next;
    } // getNext


    // Access the idea's prior idea in a list
    public Idea getPrior() {
        return prior;
    } // getNext


    // Access the idea's seqNum
    public int getSeqNum() {
        return seqNum;
    } // getSeqNum


    // Access the idea's submittor's key
    public int getKey() {
        return submittorKey;
    } // getKey


    // Access the idea's description
    public String getDesc() {
        return description;
    } // getDesc


    // access the idea's rating
    public int getRating() {
        return rating;
    } // getRating


    // Checks whether an idea was in the heap on last exit
    public boolean isInHeap() {
        return inHeap;
    } // isInHeap



    /*********************
    *   SETTER METHODS   *
    *********************/

    // Set the idea's next idea
    public void setNext(Idea nextIn) {
        next = nextIn;
    } // setNext


    // Set the idea's prior idea
    public void setPrior(Idea priorIn) {
        prior = priorIn;
    } // setNext


    // Set the seqNum
    public void setSeqNum(int seqNumIn) {
        seqNum = seqNumIn;
    } //setSeqNum


    // Set the submittorKey
    public void setKey(int keyIn) {
        submittorKey = keyIn;
    } // setKey


    // Set the description
    public void setDesc(String descIn) {
        description = descIn;
    } // setDesc

    // Set the rating
    public void setRating(int ratingIn) {
        rating = ratingIn;
    } // setRating


    // Will switch whether an idea will be read into the heap again on the next load of the saved state
    public boolean flip() {
        if (inHeap == false) {
            inHeap = true;
        } // else
        else {
            inHeap = false;
        } // else
        return inHeap;
    } // flip



} // Idea
