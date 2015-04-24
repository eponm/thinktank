/*
Jack Beal
COM-212
Node for hashing

*/

public class Node {

    private String name;
    private int ssn;

    public Node(String nameIn, int ssnIn) {
        name = nameIn;
        ssn =  ssnIn;
    } //Node

    public void setName(String newName) {
        name = newName;
    } //setName

    public void setSSN(int newSSN) {
        ssn = newSSN;
    } //setSSN

    public String getName() {
        return name;
    } //getName

    public int getSSN() {
        return ssn;
    } //getSSN

    public int getKey() {
        //String lastFour = Integer.toString(ssn);
        int lastFour = ssn % 10000;
        return lastFour;
    } //getKey

} // class Node
