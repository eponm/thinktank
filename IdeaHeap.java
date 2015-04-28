/* IdeaHeap.java

    IdeaHeap inherits generic Heap characteristics and extends it for use in thinktank.
    Nobody is totally sure how necessary this is.

*/

class IdeaHeap extends Heap {


    // Add code for tracking and assigning Idea numbering (sequential) to this code.
    public void insert(Node x) {
        heap[n]=x;
        if (n!=0) {
            swap(n-1);
        }
        n+=1;
    } // insert

} // Idea
