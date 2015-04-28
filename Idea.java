/* Idea.java

    Each Idea includes:
        (Generated) An idea number
        (Given) The SSN of the student who submitted it
        (Given) A description
        (Given) A rating

        All of the above are pretty simple.

*/

class Idea {

    int seqNum; // Assigned sequentially beginning from 0001
    int submittorKey;
    String description;
    int rating; // Must be 000–100

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


    /*********************
    *   SETTER METHODS   *
    *********************/


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


} // Idea
