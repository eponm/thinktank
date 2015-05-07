import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!

/*BinaryTree.java
    head - Node type pointer that will point to head

    Methods:
        isEmptyTree()
        search()
            searchTwo()<----private helper function
        insert()
            insertTwo()<----^^
        traverse()
            traverseTwo()<---^^
        delete()
            deleteTwo()<---^^
            popMax()<----^^
        printTree()
            printTree2()<---^^
*/

public class BinaryTree {
    private Student head;
    private boolean flipKeyToNum;

    //Constructor
    //sets head to null
    public BinaryTree() {
        head = null;
        flipKeyToNum = false;
    }//Constructor

    public BinaryTree(boolean keyFlip) {
        head = null;
        flipKeyToNum = keyFlip;
    } // Overriding constructor


    //isEmptyTree
    //Returns boolean value True if head points to null, else returns False
    public boolean isEmptyTree(){
        return head==null;
    }//isEmptyTree


    //search
    //returns Student
    //Params: target Student SSN
    public Student search(int key) {

        // CASE FOR SEARCH ON STUDENT ID NUMBER
        if (flipKeyToNum = true) {
            if (head == null) return null; //if there is no head
            else if (key == head.getStudentID()) { //if the key equals head key
                return head;
            } // else if
            else if (key < head.getStudentID()) { //if key is smaller than head key
                if (head.getLeft() != null) {
                    return searchTwo(head.getLeft(), key);
                } // if
            } // if
            else if (key > head.getKey()) { //if key is larger than head key
                if (head.getRight() != null) {
                    return searchTwo(head.getRight(), key);
                } // if
            } // else if
            else {
                return null;
            } // else
        } // if no flip

        // CASE FOR SEARCH ON KEY (DEFAULT)
        else {
            if (head == null) return null; //if there is no head
            else if (key < head.getKey()) { //if key is smaller than head key
                return searchTwo(head.getLeft(), key);
            } // if
            else if (key > head.getKey()) { //if key is larger than head key
                return searchTwo(head.getRight(), key);
            } // else if
            else if (key == head.getKey()) { //if the key equals head key
                return head;
            } // else if
            else {
                return null;
            } // else
        } // else (if no flip)

    } // search


    // Search method helper, used when searching for Student that isn't head
    // Params: Student, target Student SSN
    public Student searchTwo(Student branch, int key){
        if (flipKeyToNum = true){
            if (key<branch.getStudentID()){//if key is smaller than branch key
                if (branch.getLeft() != null) {
                    return searchTwo(branch.getLeft(),key);
                }
            }
            else if (key>branch.getStudentID()){//if key is smaller than branch key
                if (branch.getRight() != null) {
                    return searchTwo(branch.getRight(),key);
                }
            }
            else if (branch.getStudentID() == key) { //if key equals branch key
                return branch;
            }
            else {
                return null;
            }
        }
        else{
            if (key<branch.getKey()){//if key is smaller than branch key
                return searchTwo(branch.getLeft(),key);
            }
            else if (key>branch.getKey()){//if key is smaller than branch key
                return searchTwo(branch.getRight(),key);
            }
            else if (branch.getKey() == key) { //if key equals branch key
                return branch;
            }
            else {
                return null;
            }
        }
    } // search


    // insert
    // inserts Student into BST
    // Params: Student to be inserted
    public void insert(Student newStudent){
        if (isEmptyTree)) { // if there is no head
            head=newStudent;
        }
        else { // if there is a head
            insertTwo(head,newStudent);
        }
    }


    //insert helper
    //Params: Student in tree, Student to be inserted
    private void insertTwo(Student parent, Student newStudent){
        if (flipKeyToNum == true){
            if (nodeIn.getStudentID() > parent.getKey()) {
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
        }
        else{
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
        }
    }//insert


    //traverse
    //prints tree to consol from left to right
    public void traverse(){
        if(head!=null){
            traverseTwo(head.getLeft());
            System.out.print(head.getKey()+" ");
            traverseTwo(head.getRight());
        }
    }

    //traverse helper
    private void traverseTwo(Student branch){
        if(branch!=null){
            traverseTwo(branch.getLeft());
            System.out.print(branch.getKey()+" ");
            traverseTwo(branch.getRight());
        }
    }//traverse


    //delete
    //deletes a target node in a BST
    //Params: Student in BST
    public void delete(Student target){
        /*
        make note that tree prioritizes elements of higher value
        i.e. : when replacing elements, the tree will go LEFT, then the right-most
        leaf, rather than going RIGHT and getting the leftmost leaf because the ladder is
        of higher value.
        */
        if(head==null) return;
        else if (flipKeyToNum==true){
            int key = head.getStudentID();
            if(key<head.getStudentID()){//if Student key is larger than head
                int side=0;
                deleteTwo(head.getLeft(),key,head,side);
            }
            else if(key>head.getStudentID()){//if Student key is smaller than head
                int side=1;
                deleteTwo(head.getRight(),key,head,side);
            }
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeft()==null&&head.getRight()==null){//if head has no children
                    head=null;
                }
                else if(head.getLeft()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMax(head.getRight(),head,0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT side if and only if getLeft() of head=null.
                    this.delete(temp2);
                    head=temp2;
                    head.setRight(temp.getLeft());
                    temp.setRight(null);
                }
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMax(head.getLeft(),head,1);
                    this.delete(temp2);
                    head=temp2;
                    head.setLeft(temp.getLeft());
                    head.setRight(temp.getRight());
                    temp.setLeft(temp.getLeft());
                }
            }
        }
        else{
            int key = head.getKey();
            if(key<head.getKey()){//if Student key is larger than head
                int side=0;
                deleteTwo(head.getLeft(),key,head,side);
            }
            else if(key>head.getKey()){//if Student key is smaller than head
                int side=1;
                deleteTwo(head.getRight(),key,head,side);
            }
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeft()==null&&head.getRight()==null){//if head has no children
                    head=null;
                }
                else if(head.getLeft()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMax(head.getRight(),head,0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT side if and only if getLeft() of head=null.
                    this.delete(temp2);
                    head=temp2;
                    head.setRight(temp.getLeft());
                    temp.setRight(null);
                }
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMax(head.getLeft(),head,1);
                    this.delete(temp2);
                    head=temp2;
                    head.setLeft(temp.getLeft());
                    head.setRight(temp.getRight());
                    temp.setLeft(temp.getLeft());
                }
            }
        }
    }


    //delete helper
    //cycles the target down the tree by comparing the target to the SSN of Student that
    //is to be deleted.
    //Params: target Student, SSN of Student to be deleted, Parent of target, interger
    //to represent what side of the parent the target is on (0[left] or 1[right])
    public void deleteTwo(Student target, int key, Student parent, int side){
        if (flipKeyToNum==true){
            if(key<target.getStudentID()){//if Student key is smaller than target key
                side=0;
                deleteTwo(target.getLeft(),key,target,side);
            }
            else if(key>target.getStudentID()){//if Student key is larger than target key
                side=1;
                deleteTwo(target.getRight(),key,target,side);
            }
            else{//if Student key equals target key
                if(target.getLeft()==null&&target.getRight()==null){//if the target has no children
                    if(side==0){//if the target is on the left side of parent
                        parent.setLeft(null);
                    }
                    else{//if the target is on the right side of parent
                        parent.setRight(null);
                    }
                }
                else if(target.getLeft()==null||target.getRight()==null){//if the target has either a right or left child
                    if(side==0){//if target is on the left side of parent
                        if(target.getLeft()==null){//if the target has a right child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setLeft(successor);
                            successor.setRight(target.getRight());
                        }
                        else{//if target has a left child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setLeft(successor);
                            successor.setLeft(target.getLeft());
                        }
                    }
                    else{//if target is on the right side of the parent
                        if(target.getLeft()==null){//if target has a left child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setRight(successor);
                            successor.setRight(target.getRight());
                        }
                        else{//if target has a right child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setRight(successor);
                            successor.setLeft(target.getLeft());
                        }
                    }
                }
                else{//if the target has two children
                    if(side==0){//if the target is on the left side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setLeft(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }
                    else{//if the target is on the right side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setRight(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }
                }
            }
        }
        else{
            if(key<target.getKey()){//if Student key is smaller than target key
                side=0;
                deleteTwo(target.getLeft(),key,target,side);
            }
            else if(key>target.getKey()){//if Student key is larger than target key
                side=1;
                deleteTwo(target.getRight(),key,target,side);
            }
            else{//if Student key equals target key
                if(target.getLeft()==null&&target.getRight()==null){//if the target has no children
                    if(side==0){//if the target is on the left side of parent
                        parent.setLeft(null);
                    }
                    else{//if the target is on the right side of parent
                        parent.setRight(null);
                    }
                }
                else if(target.getLeft()==null||target.getRight()==null){//if the target has either a right or left child
                    if(side==0){//if target is on the left side of parent
                        if(target.getLeft()==null){//if the target has a right child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setLeft(successor);
                            successor.setRight(target.getRight());
                        }
                        else{//if target has a left child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setLeft(successor);
                            successor.setLeft(target.getLeft());
                        }
                    }
                    else{//if target is on the right side of the parent
                        if(target.getLeft()==null){//if target has a left child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setRight(successor);
                            successor.setRight(target.getRight());
                        }
                        else{//if target has a right child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setRight(successor);
                            successor.setLeft(target.getLeft());
                        }
                    }
                }
                else{//if the target has two children
                    if(side==0){//if the target is on the left side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setLeft(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }
                    else{//if the target is on the right side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setRight(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }
                }
            }
        }
    }


    //popMax
    /* helper function for getting the maximum depth leaf in a certain direction,
    (used in delete). Takes a Student and an int of either 0 or 1, 0==GO LEFT, 1==GO RIGHT.
    While loops down the line until null until next==null. Then returns the current
    Student the pointer is on.
    */
    //Params: target Student to be deleted, Parent of target, side of the parent (0 or 1)
    private Student popMax(Student branch, Student parent, int side){
        if(side==0&&branch.getLeft()!=null){//if going left and there is no left child
            return popMax(branch.getLeft(),branch,0);
        }
        else if(side==1&&branch.getRight()!=null){//if going right and there is no right child
            return popMax(branch.getRight(),branch,1);
        }
        else{//if going and there is a child
            if(side==0){//if going left
                parent.setLeft(null);
                return branch;
            }
            else{//if going right
                parent.setRight(null);
                return branch;
            }
        }
    }//delete


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
            System.out.println("Student = "+"\""+tree.getName()+"\",\"" +tree.getUsername()+"\",\"'"+tree.getKey()+"\",\""+tree.getStudentID()+"\"\n");
            printTree2(tree.getLeft(), out);
            printTree2(tree.getRight(), out);
        } // if
    } // printTree2
} // BinaryTree
