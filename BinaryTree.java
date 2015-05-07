
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
            else if (key < head.getStudentID()) { //if key is smaller than head key
                return searchTwo(head.getLeft(), key);
            } // if
            else if (key > head.getKey()) { //if key is larger than head key
                return searchTwo(head.getRight(), key);
            } // else if
            else if (key == head.getStudentID()) { //if the key equals head key
                return head;
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
                return searchTwo(branch.getLeft(),key);
            }//if
            else if (key>branch.getStudentID()){//if key is smaller than branch key
                return searchTwo(branch.getRight(),key);
            }//else if
            else if (branch.getStudentID() == key) { //if key equals branch key
                return branch;
            }//else if
            else {
                return null;
            }//else
        }//if flipKeyToNum
        else{
            if (key<branch.getKey()){//if key is smaller than branch key
                return searchTwo(branch.getLeft(),key);
            }//if
            else if (key>branch.getKey()){//if key is smaller than branch key
                return searchTwo(branch.getRight(),key);
            }//else if
            else if (branch.getKey() == key) { //if key equals branch key
                return branch;
            }//else if
            else {
                return null;
            }//else
        }//else
    } // search


    // insert
    // inserts Student into BST
    // Params: Student to be inserted
    public void insert(Student newStudent){
        if (head==null) { // if there is no head
            head=newStudent;
        }//if
        else { // if there is a head
            insertTwo(head,newStudent);
        }//else
    }//insert(student)


    //insert helper
    //Params: Student in tree, Student to be inserted
    private void insertTwo(Student branch,Student newStudent){
        if (flipKeyToNum == true){
            if(newStudent.getStudentID()<branch.getStudentID()){//if Student is smaller branch
                if(branch.getLeft()==null){//if the branch left child is null
                    branch.setLeft(newStudent);
                }//if
                else{//recursive call for next left branch down
                    insertTwo(branch.getLeft(),newStudent);
                }//else
            }//if flipKeyToNum
            else{//if newStudent is larger than branch
                if(branch.getRight()==null){//if the branch right child is null
                    branch.setRight(newStudent);
                }//else
                else{//recursive call or next right branch down
                    insertTwo(branch.getRight(),newStudent);
                }//else
            }//else if newStud is larger
        }//if flipKeyToNum==true
        else{
            if(newStudent.getKey()<branch.getKey()){//if Student is smaller branch
                if(branch.getLeft()==null){//if the branch left child is null
                    branch.setLeft(newStudent);
                }//if
                else{//recursive call for next left branch down
                    insertTwo(branch.getLeft(),newStudent);
                }//else
            }//if key<branch key
            else{//if newStudent is larger than branch
                if(branch.getRight()==null){//if the branch right child is null
                    branch.setRight(newStudent);
                }//if
                else{//recursive call or next right branch down
                    insertTwo(branch.getRight(),newStudent);
                }//else
            }//else
        }//else
    }//insert


    //traverse
    //prints tree to consol from left to right
    public void traverse(){
        if(head!=null){
            traverseTwo(head.getLeft());
            System.out.print(head.getKey()+" ");
            traverseTwo(head.getRight());
        }//if
    }//transverse

    //traverse helper
    private void traverseTwo(Student branch){
        if(branch!=null){
            traverseTwo(branch.getLeft());
            System.out.print(branch.getKey()+" ");
            traverseTwo(branch.getRight());
        }//if 
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
            }//if key>head
            else if(key>head.getStudentID()){//if Student key is smaller than head
                int side=1;
                deleteTwo(head.getRight(),key,head,side);
            }//if key<head
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeft()==null&&head.getRight()==null){//if head has no children
                    head=null;
                }//if head ! have children
                else if(head.getLeft()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMax(head.getRight(),head,0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT side if and only if getLeft() of head=null.
                    this.delete(temp2);
                    head=temp2;
                    head.setRight(temp.getLeft());
                    temp.setRight(null);
                }//ELSE if head has right child
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMax(head.getLeft(),head,1);
                    this.delete(temp2);
                    head=temp2;
                    head.setLeft(temp.getLeft());
                    head.setRight(temp.getRight());
                    temp.setLeft(temp.getLeft());
                }//else if head has two children
            }//if Student key equals head
        }//else if flipKeyToNum==true
        else{
            int key = head.getKey();
            if(key<head.getKey()){//if Student key is larger than head
                int side=0;
                deleteTwo(head.getLeft(),key,head,side);
            }//if key>head
            else if(key>head.getKey()){//if Student key is smaller than head
                int side=1;
                deleteTwo(head.getRight(),key,head,side);
            }//if key<head
            else{//if Student key equals head
                /*
                pricipal of prioritizing higher values illustrated in this case:
                */
                if(head.getLeft()==null&&head.getRight()==null){//if head has no children
                    head=null;
                }//if head has no children
                else if(head.getLeft()==null){// if head has a right child
                    Student temp=head;
                    Student temp2=popMax(head.getRight(),head,0); //note how it only gets the maximum leftmost
                    //leaf of the RIGHT side if and only if getLeft() of head=null.
                    this.delete(temp2);
                    head=temp2;
                    head.setRight(temp.getLeft());
                    temp.setRight(null);
                }//else if head has right child
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMax(head.getLeft(),head,1);
                    this.delete(temp2);
                    head=temp2;
                    head.setLeft(temp.getLeft());
                    head.setRight(temp.getRight());
                    temp.setLeft(temp.getLeft());
                }//else head has 2 children
            }//else if student equals head
        }//else
    }//delete(Student target)


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
            }//if key<target
            else if(key>target.getStudentID()){//if Student key is larger than target key
                side=1;
                deleteTwo(target.getRight(),key,target,side);
            }//if key>target
            else{//if Student key equals target key
                if(target.getLeft()==null&&target.getRight()==null){//if the target has no children
                    if(side==0){//if the target is on the left side of parent
                        parent.setLeft(null);
                    }//if target on left
                    else{//if the target is on the right side of parent
                        parent.setRight(null);
                    }//if target on right
                }//if target has no children
                else if(target.getLeft()==null||target.getRight()==null){//if the target has either a right or left child
                    if(side==0){//if target is on the left side of parent
                        if(target.getLeft()==null){//if the target has a right child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setLeft(successor);
                            successor.setRight(target.getRight());
                        }//if target has right child
                        else{//if target has a left child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setLeft(successor);
                            successor.setLeft(target.getLeft());
                        }//if target has left child
                    }//else if target has either right or left child
                    else{//if target is on the right side of the parent
                        if(target.getLeft()==null){//if target has a left child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setRight(successor);
                            successor.setRight(target.getRight());
                        }//if target has left child
                        else{//if target has a right child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setRight(successor);
                            successor.setLeft(target.getLeft());
                        }//if target has right child
                    }//if target on right side of parent
                }//if target has either right or left child
                else{//if the target has two children
                    if(side==0){//if the target is on the left side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setLeft(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }//if target on left side of parent
                    else{//if the target is on the right side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setRight(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }//ielse if target on right side of parent
                }//else if target has two children
            }//if student key == target key
        }//if flipKeyToNum==true
        else{
            if(key<target.getKey()){//if Student key is smaller than target key
                side=0;
                deleteTwo(target.getLeft(),key,target,side);
            }//if student key<target
            else if(key>target.getKey()){//if Student key is larger than target key
                side=1;
                deleteTwo(target.getRight(),key,target,side);
            }//if student key>target
            else{//if Student key equals target key
                if(target.getLeft()==null&&target.getRight()==null){//if the target has no children
                    if(side==0){//if the target is on the left side of parent
                        parent.setLeft(null);
                    }//if target on left of parent
                    else{//if the target is on the right side of parent
                        parent.setRight(null);
                    }//if target on right
                }//if target has two children
                else if(target.getLeft()==null||target.getRight()==null){//if the target has either a right or left child
                    if(side==0){//if target is on the left side of parent
                        if(target.getLeft()==null){//if the target has a right child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setLeft(successor);
                            successor.setRight(target.getRight());
                        }//if target has right child
                        else{//if target has a left child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setLeft(successor);
                            successor.setLeft(target.getLeft());
                        }//if target has left child
                    }//if target on left of parent
                    else{//if target is on the right side of the parent
                        if(target.getLeft()==null){//if target has a left child
                            Student successor=popMax(target.getRight(),parent,0);
                            parent.setRight(successor);
                            successor.setRight(target.getRight());
                        }//if target has left child
                        else{//if target has a right child
                            Student successor=popMax(target.getLeft(),parent,1);
                            parent.setRight(successor);
                            successor.setLeft(target.getLeft());
                        }//if target has right child
                    }//if target is on right side of parent
                }//if target has a right or left child
                else{//if the target has two children
                    if(side==0){//if the target is on the left side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setLeft(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }//if target is on the left of parent
                    else{//if the target is on the right side of the parent
                        Student successor=popMax(target.getLeft(),parent,1);
                        parent.setRight(successor);
                        successor.setRight(target.getRight());
                        successor.setLeft(target.getLeft());
                    }//if target is on the right of parent
                }//if the target has two children
            }//if student key<target key
        }//else 
    }//delete two


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
        }//if going left & no left child
        else if(side==1&&branch.getRight()!=null){//if going right and there is no right child
            return popMax(branch.getRight(),branch,1);
        }//if going right & no right child
        else{//if going and there is a child
            if(side==0){//if going left
                parent.setLeft(null);
                return branch;
            }//if going left
            else{//if going right
                parent.setRight(null);
                return branch;
            }//if going right
        }//if going and there is a child
    }//delete


    //printTree()
    //prints Students in BST with their left and right Children
    public void printTree() {
        System.out.println();
        printTree2(head);
        System.out.println();
    }//printTree()


    //helper function
    //Params: Student
    private void printTree2(Student tree) {
        if (tree != null) {
            System.out.println("node = " + tree.getKey());
                if (tree.getLeft() != null)
                System.out.println("left = " + tree.getLeft().getKey());
                else
                    System.out.print("left = null");
                if (tree.getRight() != null)
                System.out.println("right = " + tree.getRight().getKey());
                else
                    System.out.println("right = null");
            printTree2(tree.getLeft());
            printTree2(tree.getRight());
        } // if
    } // printTree2
} // BinaryTree
