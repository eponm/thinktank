import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!

/*BinaryTree.java
    head - Node type pointer that will point to head
    Methods:
        isEmptyTree()
        searchSNN()
            searchSNNTwo()<----private helper function
        ssnInsert()
            ssnInsertTwo()<----^^
        traverse()
            traverseTwo()<---^^
        ssnDelete()
            ssnDeleteTwo()<---^^
            popMaxSSN()<----^^
        printTree()
            printTree2()<---^^
*/

public class BinaryTree implements Serializable {
    private Student head;
    private boolean flipKeyToNum;

    //Constructor
    //sets head to null
    // public BinaryTree(boolean keyFlip) {
    public BinaryTree(){
        head = null;
        int n = 0;
    }//Constructor



    //isEmptyTree
    //Returns boolean value True if head points to null, else returns False
    public boolean isEmptyTree(){
        return head==null;
    }//isEmptyTree


    //searchSNN
    //returns Student
    //Params: target Student SSN
    public Student searchSNN(int key) {
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
    } // else (if no flip)



    // searchSNN method helper, used when searchSNNing for Student that isn't head
    // Params: Student, target Student SSN
    public Student searchSNNTwo(Student branch, int key){
        if (key<branch.getSSN()){//if key is smaller than branch key
            return searchSNNTwo(branch.getLeftSSN(),key);
        }
        else if (key>branch.getSSN()){//if key is smaller than branch key
            return searchSNNTwo(branch.getRightSSN(),key);
        }
        else if (branch.getSSN() == key) { //if key equals branch key
            return branch;
        }
        else {
            return null;
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
    }


    //ssnInsert helper
    //Params: Student in tree, Student to be ssnInserted
    private void ssnInsertTwo(Student branch,Student newStudent){
        if(newStudent.getSSN()<branch.getSSN()){//if Student is smaller branch
            if(branch.getLeftSSN()==null){//if the branch left child is null
                branch.setLeftSSN(newStudent);
                System.out.println("Left was null SSN");
                System.out.println();
                this.traverse();
            }
            else{//recursive call for next left branch down
                ssnInsertTwo(branch.getLeftSSN(),newStudent);
                System.out.println("Getting Left SSN");
            }
        }
        else{//if newStudent is larger than branch
            if(branch.getRightSSN()==null){//if the branch right child is null
                branch.setRightSSN(newStudent);
                System.out.println("Right was null SSN");
                System.out.println();
                this.traverse();
            }
            else{//recursive call or next right branch down
                ssnInsertTwo(branch.getRightSSN(),newStudent);
                System.out.println("Getting right SSN");
            }
        }
    }


    //traverse
    //prints tree to consol from left to right
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
            System.out.println("gettingLeft traverse");
            traverseTwo(branch.getLeftSSN());
            System.out.println(branch.getSSN()+" ");
            System.out.println("gettingRight traverse");
            traverseTwo(branch.getRightSSN());
        }
    }//traverse


    //delete
    //deletes a target node in a BST
    //Params: Student in BST
    public void ssnDelete(Student target){
        /*
        make note that tree prioritizes elements of higher value
        i.e. : when replacing elements, the tree will go LEFT, then the right-most
        leaf, rather than going RIGHT and getting the leftmost leaf because the ladder is
        of higher value.
        */
        if(head==null) return;
        else{
            int key = head.getSSN();
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
                    //leaf of the RIGHT side if and only if getLeftSSN() of head=null.
                    this.delete(temp2);
                    head=temp2;
                    head.setRightSSN(temp.getLeftSSN());
                    temp.setRightSSN(null);
                }
                else{// if head has two children
                    Student temp=head;
                    Student temp2=popMaxSSN(head.getLeftSSN(),1);
                    this.delete(temp2);
                    head=temp2;
                    head.setLeftSSN(temp.getLeftSSN());
                    head.setRightSSN(temp.getRightSSN());
                    temp.setLeftSSN(temp.getLeftSSN());
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
        Student parent = raeget.getParentSSN();
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
                        parent.setLeftSSN(successor);
                        successor.setRightSSN(target.getRightSSN());
                    }
                    else{//if target has a left child
                        successor=popMaxSSN(target.getLeftSSN(),1);
                        parent.setLeftSSN(successor);
                        successor.setLeftSSN(target.getLeftSSN());
                    }
                }
                else{//if target is on the right side of the parent
                    if(target.getLeftSSN()==null){//if target has a left child
                        successor=popMaxSSN(target.getRightSSN(),0);
                        parent.setRightSSN(successor);
                        successor.setRightSSN(target.getRightSSN());
                    }
                    else{//if target has a right child
                        successor=popMaxSSN(target.getLeftSSN(),1);
                        parent.setRightSSN(successor);
                        successor.setLeftSSN(target.getLeftSSN());
                    }
                }
            }
            else{//if the target has two children
                if(side==0){//if the target is on the left side of the parent
                    successor=popMaxSSN(target.getLeftSSN(),1);
                    parent.setLeftSSN(successor);
                    successor.setRightSSN(target.getRightSSN());
                    successor.setLeftSSN(target.getLeftSSN());
                }
                else{//if the target is on the right side of the parent
                    successor=popMaxSSN(target.getLeftSSN(),1);
                    parent.setRightSSN(successor);
                    successor.setRightSSN(target.getRightSSN());
                    successor.setLeftSSN(target.getLeftSSN());
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
    private Student popMaxSSNSSN(Student branch, int side){
        if(side==0&&branch.getLeftSSN()!=null){//if going left and there is no left child
            return popMaxSSN(branch.getLeftSSN(),0);
        }
        else if(side==1&&branch.getRightSSN()!=null){//if going right and there is no right child
            return popMaxSSN(branch.getRightSSN(),1);
        }
        else{//if going and there is a child
            if(side==0){//if going left
                parent = branch.getParentSSN();
                parent.setLeftSSNSSN(null);
                return branch;
            }
            else{//if going right
                parent = branch.getParentSSN();
                parent.setRightSSNSSN(null);
                return branch;
            }
        }
    }//delete

    //Clone of popMaxSSNSSN but used for cases when searchSNNing with ID
    private Student popMaxSSNID(Student branch, int side){
        if(side==0&&branch.getLeftID()!=null){//if going left and there is no left child
            return popMaxSSN(branch.getLeftID(),0);
        }
        else if(side==1&&branch.getRightID()!=null){//if going right and there is no right child
            return popMaxSSN(branch.getRightID(),1);
        }
        else{//if going and there is a child
            if(side==0){//if going left
                parent = branch.getParentID();
                parent.setLeftSSNID(null);
                return branch;
            }
            else{//if going right
                parent = branch.getParentID();
                parent.setRightSSNID(null);
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
            System.out.println("Student = "+"\""+tree.getName()+"\",\"" +tree.getUsername()+"\",\"'"+tree.getSSN()+"\",\""+tree.getStudentID()+"\"\n");
            printTree2(tree.getLeftSSN(), out);
            printTree2(tree.getRightSSN(), out);
        } // if
    } // printTree2
} // BinaryTree
