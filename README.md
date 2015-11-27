# Thinktank
The G. Parker East Coast Thinktank database repository!

#THINK TANK --the project
We have a great money making idea, but need a data base to keep track of our employees. We plan to hire all the students on campus as part of a big think tank. Students will be paid to think about the really big problems that face society. We'll gather the answers to our world's problems and sell them to the highest bidder. Anyway, to do this, we need a system to manage students who we plan to employ.
The user should be able to call up any record (in an average less than O(n) time) using the student's SSN. The display should show the person's last name, email login name, ssn, and the average score of the last ideas that they have submitted (maximum of 10). In addition, you should be able to access the student's ideas (maximum of 10) from this screen. Once displayed, the user should be able to delete the record or modify the last name and email login name. There should also be a way to add new records and to add new ideas.

Ideas are made up of an idea number, the submitter's SSN, a short text describing the idea and the idea rating. Ideas are turned in by the student and rated by our executive team. A score (0 to 100) is assigned to each idea and it is added to the student's record. Only the last 10 ideas are to be kept for each student, so after that, whenever a new idea comes in, the oldest one is thrown out of the student's record (although it should stay in our overall collection of ideas). Our company will be using these ideas often, so the best one should be accessible in constant time and we should be able to add new ones or delete the best one (which happens when it sells) in O(lg n) time. Note: when the best one is deleted from the pool of all of the ideas, it should not be deleted from the student's list of ideas.

Include a way for the user to look up a student's email login name using the student number in less than O(n) average time. The user should also be able to print to the screen a list of the students (student number, name, SSN, average score), ordered by student number, in O(n) time. Your program should store the contents of the database to a file when it is shut down and retrieve the data when started back up again.

## Coding practices
* Indentations are 4 spaces, *always*.
* Comments precede methods and may be broken into separate lines if doing so would help the clarity of the comments.
* There are no empty lines between comments and code blocks.
* Blocks that open a new pair of braces include the open brace on the same line, preceded by a single space.
* Closing braces are on their own lines, followed immediately by a space, two slashes, another space, and the first (or first few) keywords of the block it closes.
* Two empty lines follow every method; a single empty line may be used within big blocks of code to aid in clarity.
* Be sure to add spaces where necessary. This include around logical operators (such as `&&`, `==`), seqences of parameters (such as `for (int j==1, j=2, j++)`), and between function headers and their braces.
Example:
```java
// Prints the keys of the nodes in the tree in order.
// Magically works. No idea why or how.
public void traverse() {
    Node current = t;
    traverse(current);
    System.out.println();
} // traverse


// This is the part that makes things happen.
private void traverse(Node current) {
    if (current != null) {
        traverse(current.getLeft());
        System.out.print(current.getKey() + " ");
        traverse(current.getRight());
    } // if
} //  traverse
```

