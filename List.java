/* List.java

A linked list.

*/

public class List {

    private Idea head;
    private int length;

    // Constructor
    public List() {
        head = null;
        length = 0;
    } //constructor


    // Returns the int length of the list
    public int length() {
        return length;
    } //length


    // Returns the boolean state of an empty list
    public boolean isEmptyList() {
        return head == null;
    } //isEmptyList


    // Returns the Idea at the requested position
    public Idea access(int pos) {
        if (pos < 0 || pos >= length) {
            return null;
        }
        Idea temp = head;
        for (int j = 0; j < pos; j++) {
            temp = temp.getNext();
        }
        return temp;
    } //access


    // Inserts a Idea at the head of the list
    public void insert(Idea x) {
        x.setNext(head);
        head = x;
        length++;
    } //insert


    // Returns the Idea with a given key (last four digits of SSN)
    public Idea searchReturn(int keyToFind) {
        int count = 0;
        Idea temp = head;

        // Loop until the end of the list
        while (count < length) {
            // Check to see if there is a match
            if (temp.getKey() == keyToFind) {
                // If so, return the Idea
                return temp;
            } //if
            // If not, increment and keep going
            count++;
            temp = temp.getNext();
        } //while
        // Otherwise return nothing
        return null;
    }


    public void searchRemove(int keyToFind) {
        int count = 0;
        Idea current = head;
        Idea prior = null;

        // Handle immediate matches
        if (current.getKey() == keyToFind) {
            head = current.getNext();
            length--;
        }

        else {
            while (count <= length) {
                if (current.getKey() == keyToFind) {
                    prior.setNext(current.getNext());
                    current.setNext(null);
                    length--;
                    return;
                }//if
                prior = current;
                current = current.getNext();
                count ++;
            } //while
        } // else
    } //searchRemove


    public void printList() {
        System.out.println(length);
        Idea currentIdea = this.access(0);
        for (int i = 0; i < length; i++) {
            System.out.println(currentIdea.getKey());
            currentIdea = currentIdea.getNext();
        } //for
    } //printList

} //class
