class Segmenter implements Serializable {
    private int listLength;
    private int treeSize;

    public Segmenter() {
        listLength=0;
        treeSize=0;
    }

    //*************getMethods************

    //Returns listLength int
    public int getListLength(){
        return listLength;
    }//getListLength


    //Returns tree size int
    public int getTreeSize(){
        return treeSize;
    }//get List Length

    //**************setMethods************

    //Sets listLength to given int
    public void setListLength(int newSize){
        listLength=newSize;
    }//setListLength

    //Sets treeSize to given int
    public void setTreeSize(int newSize){
        treeSize=newSize;
    }//setTreeSize
}
