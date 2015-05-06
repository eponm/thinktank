class IdeaDB {

    private List coreList;
    private Heap ideaHeap;
<<<<<<< HEAD
    private BinaryTree studentSSNTree;
    private BinaryTree studentIDTree;
=======
    private BinaryTree studentKeyTree;
    private BinaryTree studentNumTree;
>>>>>>> 9367bcd64b455cc4cab8ca9101b96f59ca47b391

    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
<<<<<<< HEAD
        studentSSNTree = new BinaryTree();
        studentIDTree = new BinaryTree(true);
        //this.rebuild
    } // constructor
=======
        studentKeyTree = new BinaryTree();
        studentNumTree = new BinaryTree(true);
>>>>>>> 9367bcd64b455cc4cab8ca9101b96f59ca47b391

    } // constructor



    // insertIdea - adds a new idea
    public void insertIdea(Idea newIdea) {
        // Get the submittor's key
        int key = newIdea.getKey();

        // Put the idea coreList, ideaHeap and studentSSNTree:
        // Set seqNum in newIdea
        newIdea.setSeqNum(seqNum);
        seqNum++;
        // Add idea to list
        coreList.insert(newIdea);
        // Add idea to heap
        ideaHeap.insert(newIdea);
        // Flip idea's inHeap value
        newIdea.flip();

        // Add the idea to the student's queue
        Student student = studentSSNTree.search(key);
        student.addToQueue(newIdea);
    } // insert

    //addStudent
    //adds a student to IdeaDB
    public void addStudent(Student newStudent) {
        // Doesn't double stored data because of how Java handles objects
        studentSSNTree.insert(newStudent);
        studentIDTree.insert(newStudent);
    }//addStudent

    //deleteStudent
    //deletes student from both trees
    public void deleteStuent(Student targetStudent){
        int ssn = targetStudent.getKey();
        int studentID = targetStudent.getStudentID();
        studentSSNTree.delete
    }
    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
        return temp;
    } // sell


} // class
