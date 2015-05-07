class IdeaDB {

    private List coreList;
    private Heap ideaHeap;
    private BinaryTree studentSSNTree;
    private BinaryTree studentIDTree;
    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
        studentSSNTree = new BinaryTree();
        studentIDTree = new BinaryTree(true);
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
    public void deleteStudent(Student targetStudent){
        studentSSNTree.delete(targetStudent);
        studentIDTree.delete(targetStudent);
    }
    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
        temp.flip();
        return temp;
    } // sell
    public Idea getBestIdea(){
        Idea bestIdea=ideaHeap.findMin();
        if (bestIdea!=null){
            System.out.println("The best idea is Idea #" + bestIdea.getSeqNum() + "\nrated at" + bestIdea.getRating() + "here is the description: \n" + bestIdea.getDesc());//debug
        }
        return bestIdea;
    }
<<<<<<< HEAD
    public Student getStudent(int key, boolean usingSSN){
        if(usingSSN==true){
=======


    public Idea getStudent(int key, boolean usingSSN){
        if(usingSSN==True){
>>>>>>> eb1e164eebd65953b3547ffa3f96ab4bfa1afcea
            return studentSSNTree.search(key);
        }
        else{
            return studentIDTree.search(key);
        }
    }


} // class
