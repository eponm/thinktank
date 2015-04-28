# Thinktank
The G. Parker East Coast Thinktank database repository!

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

