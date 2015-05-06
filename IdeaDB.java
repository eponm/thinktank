class IdeaDB {

    private List coreList;
    private Heap ideaHeap;
    private BinaryTree studentTree;

    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
        studentTree = new BinaryTree();
    } // constructor


    // insertIdea - adds a new idea
    public void insertIdea(Idea newIdea) {
        // Get the submittor's key
        int key = newIdea.getKey();

        // Put the idea coreList, ideaHeap and studentTree:
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
        Student student = studentTree.search(key);
        student.addToQueue(newIdea);
    } // insert

    //addStudent
    //adds a student to IdeaDB
    public void addStudent(Student newStudent){
        studentTree.insert(newStudent);
    }//addStudent


    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
    } // sell


} // class
