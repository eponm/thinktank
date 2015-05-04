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


    // Insert - adds a new idea
    public void insert(Idea newIdea) {
        // Set seqNum in newIdea
        newIdea.setSeqNum(seqNum);
        seqNum++;
        // Add idea to list
        coreList.insert(newIdea);
        // Add idea to heap
        ideaHeap.insert(newIdea);
        // Flip idea's inHeap value
        newIdea.inHeap(true);

        // Access student from BST
        // Add idea to student's queue

    } // insert


    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        temp = ideaHeap.deleteMin();
    } // sell


} // class
