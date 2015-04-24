/* BST.java

    DEPENDENCIES:
        Node.java (with left, right, and parent pointers)

    This BST takes in nodes and makes a binary search tree out of them.
    Super simple.

*/

public class BST {

    // Initialize the root
    private Node t;

    // Constructor
    public BST() {
        t = null;
    } // BST


    // Returns true when a tree is empty
    public boolean isEmpty() {
        return t==null;
    } // isEmpty


    // Find the node in the tree that matches a given 4-digit SSN key
    // Immediately calls search(current, key) in order to recurse.
    public Node search(int target) {
        Node top = t;
        return search(t, target);
    } // search


    // Searches for a given key in a given node's subtrees
    private Node search(Node current, int key) {

        if (current == null) {
            return null;
        } // Handles empty trees

        else if (key == current.getKey()) {
            return current;
        } // Handles immediate matches

        else if (key < current.getKey()) {
            return search(current.getLeft(), key);
        } // Handles key being lessthan current node and recurses

        else {
            return search(current.getRight(), key);
        } // Handles key being greaterthan current node and recurses
    } // search


    // Puts a node into the tree.
    // Immediately calls insert2(parent, nodeIn) in order to recurse.
    public void insert(Node nodeIn) {
        if (isEmpty()) {
            t = nodeIn;
        } // Handles empty trees

        else {
            insert2(t, nodeIn);
        } // Recurses to get to the bottom of the tree
    } // insert


    // Puts a node into the tree.
    private void insert2(Node parent, Node nodeIn) {

        if (nodeIn.getKey() > parent.getKey()) {
            if (parent.getRight() != null) {
                insert2 (parent.getRight(), nodeIn);
            } // if
            else {
                parent.setRight(nodeIn);
                nodeIn.setParent(parent);
            } // else
        } // if
        else {
            if (parent.getLeft() != null) {
                insert2(parent.getLeft(), nodeIn);
            } // if
            else {
                parent.setLeft(nodeIn);
                nodeIn.setParent(parent);
            } // else
        } // else
    } // insert2


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


    // Prints a more verbose tree layout.
    public void printTree() {
        printTree2(t);
        System.out.println();
    } // printTree


    // This part recurses.
    private void printTree2(Node tree) {
        if (tree != null) {
            System.out.print(tree.getKey() + " -- ");
            if (tree.getLeft() != null) {
                System.out.print("Left: " + tree.getLeft().getKey() + " ");
            } // if
            else {
                System.out.print("Left: null ");
            } // else

            if (tree.getRight() != null) {
                System.out.print("Right: " + tree.getRight().getKey() + " ");
            } // if
            else {
                System.out.print("Right: null ");
            } // else

            if (tree.getParent() != null) {
                System.out.println("Parent: " + tree.strParent());
            } // if
            else {
                System.out.println("Parent: null ");
            } // else

            printTree2(tree.getLeft());
            printTree2(tree.getRight());

        } // if
    } // printtree2


    // Returns the node of maximum value in the tree.
    private Node getMax(Node root) {
        if (root.getRight() == null) {
            return root;
        } // if
        else {
            return getMax(root.getRight());
        } // else
    } // getMax


    // Deletes the first node to match the input target node.
    // Immediately calls delete2(targetIn, position)
    public void delete(Node targetIn) {
        if (search(targetIn.getKey()) == null) {
            System.out.println("No such node to delete");
            return;
        } // if
        delete2(targetIn, t);
    } // delete


    // Recursive part.
    private void delete2(Node targetIn, Node position) {

        // Handle empty trees
        if (isEmpty()) return;

        // Then search down to the target node
        if (targetIn.getKey() < position.getKey()) {
            delete2(targetIn, position.getLeft());
        } // if

        else if (targetIn.getKey() > position.getKey()) {
            delete2(targetIn, position.getRight());
        } // Both will fail when the target is found, and move on...

        else {
            // If the node has two children:
            if (position.getLeft() != null && position.getRight() != null) {
                // Get the max node in its left subtree:
                // Rewrite the target with the max's data
                Node max = getMax(position.getLeft());
                Node temp = max;
                position.setName(max.getName());
                position.setSSN(max.getSSN());
                // Then wipe the max of the subtree
                max.getParent().remChild(max);
            } //if

            // If the node has a right child:
            else if (position.getRight() != null) {
                // Move its parent to the next node:
                Node temp = position;
                position = position.getLeft();
                // Then wipe the target
                temp = null;
            } //else if

            // If the node has a left child instead:
            else if (position.getRight() != null) {
                Node temp = position;
                position = position.getRight();
                temp = null;
            } //else if

            else {
                // If the node has no children, remove it.
                position.getParent().remChild(position);
            } // else
        } // else
    } // delete2


} //class
