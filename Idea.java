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
    StringBuffer description;
    int rating; // Must be 000–100

    // Constructor
    // Params: idea number, submittor SSN, a string description, and a rating.
    public Idea(int seqNumIn, int submittorKeyIn, StringBuffer descriptionIn, int ratingIn) {
        seqNum = seqNumIn;
        submittorKey = submittorKeyIn;
        description = descriptionIn;
        rating = ratingIn;
    } // Constructor


} // Idea
