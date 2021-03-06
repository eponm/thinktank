import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!
import java.io.Serializable;

/*BinaryTree.java
    head - Node type pointer that will point to head
    Methods:
        isEmptyTree()
        searchSNN()
            searchSNNTwo()<----private helper function
        searchID()
            seachIDTwo()<---------^^
        ssnInsert()
            ssnInsertTwo()<----^^
        idInsert()
            idInsertTwo()<----^^
        traverse()
            traverseTwo()<---^^
        idDelete()
            idDeleteTwo()<---^^
            popMaxID()<----^^
        ssnDelete()
            ssnDeleteTwo()<---^^
            popMaxSSN()<----^^
        printTree()
            printTree2()<---^^
*/

public class BinaryTree implements Serializable {
    private Student head;
    int n;

    //Constructor
    //sets head to null
    // public BinaryTree(boolean keyFlip) {
    public BinaryTree(){
        head = null;
        n = 0;
    }//Constructor

    //isEmptyTree
    //Returns boolean value True if head points to null, else returns False
    public boolean isEmptyTree(){
        return head==null;
    }//isEmptyTree

    //searchID
    //returns Student
    //Params: target Student SSN
    public Student searchID(int key) {
        if (head == null) return null; //if there is no head
        else if (key < head.getID()) { //if key is smaller than head key
            return searchIDTwo(head.getLeftID(), key);
        } // if
        else if (key > head.getID()) { //if key is larger than head key
            return searchIDTwo(head.getRightID(), key);
        } // else if
        else if (key == head.getID()) { //if the key equals head key
            return head;
        } // else if
        else {
            return null;
        } // else
    } // else (if no flip)



    // searchID method helper, used when searchIDing for Student that isn't head
    // Params: Student, target Student SSN
    public Student searchIDTwo(Student branch, int key){
        if(branch==null){
            return null;
        }
        else if (key<branch.getID()){//if key is smaller than branch key
            return searchIDTwo(branch.getLeftID(),key);
        }
        else if (key>branch.getID()){//if key is smaller than branch key
            return searchIDTwo(branch.getRightID(),key);
        }
        else{ //if key equals branch key
            return branch;
        }
    } // searchSNN

    //searchSNN
    //returns Student
    //Params: target Student SSN
    public Student searchSSN(int key) {
        if (head == null) return null; //if there is no head
        else if (key < head.getSSN()) { //if key is smaller than head key
            return searchSNNTwo(head.getLeftSSN(), key);
        } // if
        else if (key > head.getSSN()) { //if key is larger than head key
            return searchSNNTwo(head.getRightSSN(), key);
        } // else if
        else if (key == head.getSSN()) { //if the key equals head key
            return head;
        } // else if
        else {
            return null;
        } // else
    } // searchSSN



    // searchSNN method helper, used when searchSNNing for Student that isn't head
    // Params: Student, target Student SSN
    public Student searchSNNTwo(Student branch, int key){
        if(branch==null){
            return null;
        }
        else if (key<branch.getSSN()){//if key is smaller than branch key
            return searchSNNTwo(branch.getLeftSSN(),key);
        }
        else if (key>branch.getSSN()){//if key is smaller than branch key
            return searchSNNTwo(branch.getRightSSN(),key);
        }
        else{ //if key equals branch key
            return branch;
        }
    } // searchSNN


    // ssnInsert
    // ssnInserts Student into BST
    // Params: Student to be ssnInserted
    public void ssnInsert(Student newStudent) {
        if (isEmptyTree()) { // if there is no head
            head=newStudent;
        }
        else { // if there is a head
            ssnInsertTwo(head,newStudent);
        }
        n++;
    }


    //ssnInsert helper
    //Params: Student in tree, Student to be ssnInserted
    private void ssnInsertTwo(Student branch,Student newStudent){
        if(newStudent.getSSN()<branch.getSSN()){//if Student is smaller branch
            if(branch.getLeftSSN()==null){//if the branch left child is null
                branch.setLeftSSN(newStudent);
                newStudent.setParentSSN(branch);
            }
            else{//recursive call for next left branch down
                ssnInsertTwo(branch.getLeftSSN(),newStudent);
            }
        }
        else{//if newStudent is larger than branch
            if(branch.getRightSSN()==null){//if the branch right child is null
                branch.setRightSSN(newStudent);
                newStudent.setParentSSN(branch);

            }
            else{//recursive call or next right branch down
                ssnInsertTwo(branch.getRightSSN(),newStudent);
            }
        }
    }

    // idInsert
    // idInserts Student into BST
    // Params: Student to be idInserted
    public void idInsert(Student newStudent) {
        if (isEmptyTree()) { // if there is no head
            head=newStudent;
        }
        else { // if there is a head
            idInsertTwo(head,newStudent);
        }
        n++;
    }


    //idInsert helper
    //Params: Student in tree, Student to be idInserted
    private void idInsertTwo(Student branch,Student newStudent){
        if(newStudent.getID()<branch.getID()){//if Student is smaller branch
            if(branch.getLeftID()==null){//if the branch left child is null
                branch.setLeftID(newStudent);
                newStudent.setParentID(branch);
            }
            else{//recursive call for next left branch down
                idInsertTwo(branch.getLeftID(),newStudent);
            }
        }
        else{//if newStudent is larger than branch
            if(branch.getRightID()==null){//if the branch right child is null
                branch.setRightID(newStudent);
                newStudent.setParentID(branch);
            }
            else{//recursive call or next right branch down
                idInsertTwo(branch.getRightID(),newStudent);
            }
        }
    }


    //traverse
    //prints tree to consol from left to right
    //make sure it takes ssn and id
    public void traverse(){
        if(head!=null){
            traverseTwo(head.getLeftSSN());
            System.out.print(head.getSSN()+" ");
            traverseTwo(head.getRightSSN());
        }
    }

    //traverse helper
    private void traverseTwo(Student branch){
        if(branch!=null){
            traverseTwo(branch.getLeftSSN());
            System.out.print(branch.getSSN()+" ");
            traverseTwo(branch.getRightSSN());
        }
    }//traverse


    //delete
    //deletes a target node in a BST
    //Params: Student in BST
    public void idDelete(Student target){
        /*
        make note that tree prioritizes elements of higher value
        i.e. : when replacing elements, the tree will go LEFT, then the right-most
        leaf, rather than going RIGHT and getting the leftmost leaf because the ladder is
        of higher value.
        */
        if(head==null) return;
        else{
            int key = target.getID();
            if(key<head.getID()){//if Student key is larger than head
                int side=0;
                idDeleteTwo(head.getLeftID(),key,side);
            }
            else if(key>head.getID()){//if Student key is smaller than head
                int side=1;
                idDeleteTwo(head.getRightID(),key,side);
            }
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeftID()==null&&head.getRightID()==null){//if head has no children
                    head=null;
                }
                else if(head.getLeftID()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMaxID(head.getRightID(),0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT side if and only if getLeftID() of head=null.
                    head=temp2;
                    Student rightChild = temp.getRightID();
                    head.setRightID(rightChild);
                    rightChild.setParentID(head);
                    temp.setRightID(null);
                }
                else if(head.getRightID()==null){//if head only has left child
                    Student temp=head;
                    Student temp2=popMaxID(head.getLeftID(),1);
                    head=temp2;
                    Student leftChild = temp.getLeftID();
                    head.setLeftID(leftChild);
                    leftChild.setParentID(head);
                    temp.setLeftID(null);
                }
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMaxID(head.getLeftID(),1);
                    head=temp2;
                    Student leftChild = temp.getLeftID();
                    Student rightChild = temp.getRightID();
                    head.setLeftID(temp.getLeftID());
                    head.setRightID(temp.getRightID());
                    leftChild.setParentID(head);
                    rightChild.setParentID(head);
                    temp.setLeftID(null);
                }
            }
        }
        n--;
    }

    //delete helper
    //cycles the target down the tree by comparing the target to the SSN of Student that
    //is to be deleted.
    //Params: target Student, SSN of Student to be deleted, Parent of target, interger
    //to represent what side of the parent the target is on (0[left] or 1[right])
    public void idDeleteTwo(Student target, int key, int side){
        Student parent = target.getParentID();
        Student successor;

        if(key<target.getID()){//if Student key is smaller than target key
            side=0;
            idDeleteTwo(target.getLeftID(),key,side);
        }
        else if(key>target.getID()){//if Student key is larger than target key
            side=1;
            idDeleteTwo(target.getRightID(),key,side);
        }
        else{//if Student key equals target key
            if(target.getLeftID()==null&&target.getRightID()==null){//if the target has no children
                if(side==0){//if the target is on the left side of parent
                    parent.setLeftID(null);
                }
                else{//if the target is on the right side of parent
                    parent.setRightID(null);
                }
            }
            else if(target.getLeftID()==null||target.getRightID()==null){//if the target has either a right or left child
                if(side==0){//if target is on the left side of parent
                    if(target.getLeftID()==null){//if the target has a right child
                        successor=popMaxID(target.getRightID(),0);
                        if(successor == target.getRightID()){
                            parent.setLeftID(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setLeftID(successor);
                        successor.setRightID(target.getRightID());
                        successor.setParentID(parent);
                    }
                    else{//if target has a left child
                        successor=popMaxID(target.getLeftID(),1);
                        if(successor == target.getLeftID()){
                            parent.setLeftID(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setLeftID(successor);
                        successor.setLeftID(target.getLeftID());
                        successor.setParentID(parent);
                    }
                }
                else{//if target is on the right side of the parent
                    if(target.getLeftID()==null){//if target has a Right child
                        successor=popMaxID(target.getRightID(),0);
                        if(successor == target.getRightID()){
                            parent.setRightID(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setRightID(successor);
                        successor.setRightID(target.getRightID());
                        successor.setParentID(parent);
                    }
                    else{//if target has a left child
                        successor=popMaxID(target.getLeftID(),1);
                        if(successor == target.getLeftID()){
                            parent.setLeftID(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setRightID(successor);
                        successor.setLeftID(target.getLeftID());
                        successor.setParentID(parent);
                    }
                }
            }
            else{//if the target has two children
                if(side==0){//if the target is on the left side of the parent
                    successor=popMaxID(target.getLeftID(),1);
                    if(successor == target.getLeftID()){
                        parent.setLeftID(successor);
                        successor.setParentID(parent);
                        successor.setRightID(target.getRightID());
                        return;
                    }
                    parent.setLeftID(successor);
                    successor.setRightID(target.getRightID());
                    successor.setLeftID(target.getLeftID());
                    successor.setParentID(parent);
                }
                else{//if the target is on the right side of the parent
                    successor=popMaxID(target.getLeftID(),1);
                    if(successor == target.getRightID()){
                        parent.setRightID(successor);
                        successor.setParentID(parent);
                        successor.setRightID(target.getRightID());
                        return;
                    }
                    parent.setRightID(successor);
                    successor.setRightID(target.getRightID());
                    successor.setLeftID(target.getLeftID());
                    successor.setParentID(parent);
                }
            }
        }
    }
    public void ssnDelete(Student target){
        /*
        make note that tree prioritizes elements of higher value
        i.e. : when replacing elements, the tree will go LEFT, then the right-most
        leaf, rather than going RIGHT and getting the leftmost leaf because the ladder is
        of higher value.
        */
        if(head==null) return;
        else{
            n--;
            int key = target.getSSN();
            if(key<head.getSSN()){//if Student key is larger than head
                int side=0;
                ssnDeleteTwo(head.getLeftSSN(),key,side);
            }
            else if(key>head.getSSN()){//if Student key is smaller than head
                int side=1;
                ssnDeleteTwo(head.getRightSSN(),key,side);
            }
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeftSSN()==null&&head.getRightSSN()==null){//if head has no children
                    head=null;
                }
                else if(head.getLeftSSN()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMaxSSN(head.getRightSSN(),0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT sSSNe if and only if getLeftSSN() of head=null.
                    head=temp2;
                    head.setParentSSN(null);
                    Student rightChild = temp.getRightSSN();
                    head.setRightSSN(rightChild);
                    rightChild.setParentSSN(head);
                    temp.setRightSSN(null);
                }
                else if(head.getRightSSN()==null){//if head only has left child
                    Student temp=head;
                    Student temp2=popMaxSSN(head.getLeftSSN(),1);
                    head=temp2;
                    head.setParentSSN(null);
                    Student leftChild = temp.getLeftSSN();
                    head.setLeftSSN(leftChild);
                    leftChild.setParentSSN(head);
                    temp.setLeftSSN(null);
                }
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMaxSSN(head.getLeftSSN(),1);
                    head=temp2;
                    Student leftChild = temp.getLeftSSN();
                    Student rightChild = temp.getRightSSN();
                    head.setParentSSN(null);
                    head.setLeftSSN(temp.getLeftSSN());
                    head.setRightSSN(temp.getRightSSN());
                    leftChild.setParentSSN(head);
                    rightChild.setParentSSN(head);
                    temp.setLeftSSN(null);
                    temp.setRightSSN(null);
                }
            }
        }
    }


    //delete helper
    //cycles the target down the tree by comparing the target to the SSN of Student that
    //is to be deleted.
    //Params: target Student, SSN of Student to be deleted, Parent of target, interger
    //to represent what side of the parent the target is on (0[left] or 1[right])
    public void ssnDeleteTwo(Student target, int key, int side){
        Student parent = target.getParentSSN();
        Student successor;

        if(key<target.getSSN()){//if Student key is smaller than target key
            side=0;
            ssnDeleteTwo(target.getLeftSSN(),key,side);
        }
        else if(key>target.getSSN()){//if Student key is larger than target key
            side=1;
            ssnDeleteTwo(target.getRightSSN(),key,side);
        }
        else{//if Student key equals target key
            if(target.getLeftSSN()==null&&target.getRightSSN()==null){//if the target has no children
                if(side==0){//if the target is on the left side of parent
                    parent.setLeftSSN(null);
                }
                else{//if the target is on the right side of parent
                    parent.setRightSSN(null);
                }
            }
            else if(target.getLeftSSN()==null||target.getRightSSN()==null){//if the target has either a right or left child
                if(side==0){//if target is on the left side of parent
                    if(target.getLeftSSN()==null){//if the target has a right child
                        successor=popMaxSSN(target.getRightSSN(),0);
                        if(successor == target.getRightSSN()){
                            parent.setLeftSSN(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setLeftSSN(successor);
                        successor.setRightSSN(target.getRightSSN());
                        successor.setParentSSN(parent);
                    }
                    else{//if target has a left child
                        successor=popMaxSSN(target.getLeftSSN(),1);
                        if(successor == target.getLeftSSN()){
                            parent.setLeftSSN(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setLeftSSN(successor);
                        successor.setLeftSSN(target.getLeftSSN());
                        successor.setParentSSN(parent);
                    }
                }
                else{//if target is on the right side of the parent
                    if(target.getLeftSSN()==null){//if target has a left child
                        successor=popMaxSSN(target.getRightSSN(),0);
                        if(successor == target.getRightSSN()){
                            parent.setRightSSN(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setRightSSN(successor);
                        successor.setRightSSN(target.getRightSSN());
                        successor.setParentSSN(parent);
                    }
                    else{//if target has a right child
                        successor=popMaxSSN(target.getLeftSSN(),1);
                        if(successor == target.getLeftSSN()){
                            parent.setRightSSN(successor);
                            successor.setParentSSN(parent);
                            return;
                        }
                        parent.setRightSSN(successor);
                        successor.setLeftSSN(target.getLeftSSN());
                        successor.setParentSSN(parent);
                    }
                }
            }
            else{//if the target has two children
                if(side==0){//if the target is on the left side of the parent
                    successor=popMaxSSN(target.getLeftSSN(),1);
                    if(successor == target.getLeftSSN()){
                        parent.setLeftSSN(successor);
                        successor.setParentSSN(parent);
                        successor.setRightSSN(target.getRightSSN());
                        return;
                    }
                    parent.setLeftSSN(successor);
                    successor.setRightSSN(target.getRightSSN());
                    successor.setLeftSSN(target.getLeftSSN());
                    successor.setParentSSN(parent);
                }
                else{//if the target is on the right side of the parent
                    successor=popMaxSSN(target.getLeftSSN(),1);
                    if(successor == target.getLeftSSN()){
                        parent.setRightSSN(successor);
                        successor.setParentSSN(parent);
                        successor.setRightSSN(target.getRightSSN());
                        return;
                    }
                    parent.setRightSSN(successor);
                    successor.setRightSSN(target.getRightSSN());
                    successor.setLeftSSN(target.getLeftSSN());
                    successor.setParentSSN(parent);
                }
            }
        }
    }


    //popMaxSSN
    /* helper function for getting the maximum depth leaf in a certain direction,
    (used in delete). Takes a Student and an int of either 0 or 1, 0==GO LEFT, 1==GO RIGHT.
    While loops down the line until null until next==null. Then returns the current
    Student the pointer is on.
    */
    //Params: target Student to be deleted, Parent of target, side of the parent (0 or 1)
    private Student popMaxSSN(Student branch, int side){
        if(side==0&&branch.getLeftSSN()!=null){//if going left and there is no left child
            return popMaxSSN(branch.getLeftSSN(),0);
        }
        else if(side==1&&branch.getRightSSN()!=null){//if going right and there is no right child
            return popMaxSSN(branch.getRightSSN(),1);
        }
        else{//if going and there is a child
            if(side==0){//if going left
                Student parent = branch.getParentSSN();
                parent.setLeftSSN(null);
                return branch;
            }
            else{//if going right
                Student parent = branch.getParentSSN();
                parent.setRightSSN(null);
                return branch;
            }
        }
    }//delete

    //Clone of popMaxSSNSSN but used for cases when searchSNNing with ID
    private Student popMaxID(Student branch, int side){
        if(side==0&&branch.getLeftID()!=null){//if going left and there is no left child
            return popMaxID(branch.getLeftID(),0);
        }
        else if(side==1&&branch.getRightID()!=null){//if going right and there is no right child
            return popMaxID(branch.getRightID(),1);
        }
        else{//if going and there is a child
            if(side==0){//if going left
                Student parent = branch.getParentID();
                parent.setLeftID(null);
                return branch;
            }
            else{//if going right
                Student parent = branch.getParentID();
                parent.setRightID(null);
                return branch;
            }
        }
    }//delete


    // Returns an array with a preorder traversal of the SSN tree
    public Student[] treeToArray() {
        Student[] a = new Student[n];
        treeToArray2(head, a, 0);
        return a;
    } // treeToArray

    public void treeToArray2(Student head, Student[] a, int pos) {
        if (head == null) {
            return;
        } // if
        // Add the current node to the array
	a[pos] = head;
	pos++;
	treeToArray2(head.getLeftSSN(), a, pos);
	treeToArray2(head.getRightSSN(), a, pos);
    } // treeToArray2


    //printTree()
    //prints Students in BST with their left and right Children
    public void printTree(PrintWriter out) {
        printTree2(head, out);
    }

    //helper function
    //Params: Student
    private void printTree2(Student tree, PrintWriter out) {
        if (tree != null) {
            // Params: name, username, SSN, studentID
            System.out.println("Student = "+"\""+tree.getName()+"\",\"" +tree.getUsername()+"\",\"'"+tree.getSSN()+"\",\""+tree.getID()+"\"\n");
            printTree2(tree.getLeftSSN(), out);
            printTree2(tree.getRightSSN(), out);
        } // if
    } // printTree2

    //returns size of the tree
    public int size() {
        return n;
    }//size


} // BinaryTree
