class IdeaDB {

    private List coreList;
    private Heap ideaHeap;

    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
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
    } // insert


    // "Sell" - deletes top-rated idea
    public void sell() {
        return; //debug
    } // sell


} // class
