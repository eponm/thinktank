class IdeaDB {

    private List coreList;
    private Heap ideaHeap;
    private BinaryTree studentKeyTree;
    private BinaryTree studentNumTree;

    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
        studentKeyTree = new BinaryTree();
        studentNumTree = new BinaryTree(true);

    } // constructor



    // insertIdea - adds a new idea
    public void insertIdea(Idea newIdea) {
        // Get the submittor's key
        int key = newIdea.getKey();

        // Put the idea coreList, ideaHeap and studentKeyTree:
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
        Student student = studentKeyTree.search(key);
        student.addToQueue(newIdea);
    } // insert

    //addStudent
    //adds a student to IdeaDB
    public void addStudent(Student newStudent) {
        // Doesn't double stored data because of how Java handles objects
        studentKeyTree.insert(newStudent);
        studentNumTree.insert(newStudent);
    }//addStudent


    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
        return temp;
    } // sell


} // class
