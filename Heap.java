//heap -- min number is next number helped
public class Heap {
    int n; //counter for 
    Idea[] heap;//array of Ideas
    
    //constructor
    //params: n, Idea[]
    public Heap() {
        n=0;
        heap=new Idea[256];
    }//constructor
    
    //isEmptyHeap() checks if heap is empty    
    public boolean isEmptyHeap() {
        return n==0;
    }//isEmptyHeap
    
    //findMin();finds and returns the only accessible value in the heap
    public void findMinFormat() {
        if (isEmptyHeap()==true) {
            return heap[0];
            System.out.println("There are no ideas here");//debug
        }//if
        else {
            System.out.println("The best idea is Idea #" + heap[0].getSeqNum() + "/nrated at" + heap[0].getRating() + "here is the description: /n" + heap.getDesc());//debug
        }//else
    }//findMin
    
    //find min-- unformatted
    public Idea findMin() {
        return heap[0];
    }//find Min
    
    //printHeap() prints all the values in the heap in order  
    // debug
    public void printHeap() {
        for (int i=0;i<(n);i++) {
            System.out.println(heap[i].getKey());
        }//for
    }//printHeap
    
    
    //print with formatting
    public void printHeapFormat() {
        System.out.println();
        printHeap2(0);
        System.out.println();
    }//printTree()
    
    //helper function for printHeapFormat()
    private void printHeap2(int index) {
        if (heap[index] !=null) {
            System.out.println("Idea = " + heap[index].getKey());
        }
            //left child
            if (heap[index*2+1] !=null){
                System.out.println("left = " + heap[index*2+1].getKey());
            }
            else {
                System.out.print("left = null");
            }
            if (heap[index*2+2]!=null) {
                System.out.println("right = " + heap[index*2+2].getKey());
            }
            else{
                System.out.println("right = null");
            }
            printHeap2(index*2+1);
            printHeap2(index*2+2);
        }
    
    //insert(Idea x); inserts Idea into heap reorders heap   
    public void insert(Idea x) {
        heap[n]=x;
        if (n!=0) {
            swap(n-1);
        }//if
        n+=1;
    }//insert
    
    
    //helper function for inserted Ideas
     public void swap(int index) {
         int parentIndex=index/2;
         //for when there are 2 or 3 items in the array
         if (index==1) {
             //if there are only two items
             if (heap[0].getKey()> heap[1].getKey()) {
                  Idea a=heap[0];
                  Idea b=heap[1];
                  heap[1]=a;
                  heap[0]=b;
                  return;
             }//if
             //if there are 3 items
             else if(heap[2]!=null) {
                 if (heap[0].getKey()> heap[2].getKey()) {
                     Idea a= heap[0];
                     Idea b=heap[2];
                     heap[2]=a;
                     heap[0]=b;
                 }//if
             }//else if 
             else  {
                 return;
             }//else
             
         }//if (index==1)
         
        //if there are 3 things in the array
        else if (index==2) {
            if (heap[0].getKey()> heap[2].getKey()) {
                      Idea a=heap[0];
                      Idea b=heap[2];
                      heap[2]=a;
                      heap[0]=b;
                      return;
                 }//if
        }//else if (index==2)
         
        //for all other swaps at all other lengths, finds parent with direct index
        while (heap[parentIndex].getKey() > heap[index+1].getKey()){
                Idea b=heap[index+1];
                heap[index+1]=heap[parentIndex];
                heap[parentIndex]=b;
                swap(parentIndex);  
         }//while
        }//swap
    
    
    //helper function for rearranging after Ideas are deleted
    public void swap2(int m) {
        int j=0;
        //if the tree has no child i.e. has nothing to swap with
        if (heap[m*2+1]==null && heap[m*2+2]==null) {
            return;
        }//if
        //if there is only a left child 
        else if (heap[m*2+1]==null) {
            swap(m*2+2);
        }//else if 
        //if there is only a right child
        else if (heap[m*2+2]==null) {
            swap(m*2+1);
        }//else if
        //if there is both children-- evaluates which child has lesser key (smaller key number) and swaps
        else {
            while ((heap[m*2].getKey() > heap[m*2+1].getKey()) || (heap[m*2].getKey()> heap[m*2+2].getKey())){
                if (heap[m*2].getKey() > heap[m*2+1].getKey()){
                    Idea b=heap[m*2+1];
                    Idea a=heap[m*2];
                    heap[m*2+1]=null;
                    heap[m*2]=null;
                    heap[m*2+1]=a;
                    heap[m*2]=b;
                    swap(m*2+1); 
                }//if parent>right child
                else {
                    Idea b=heap[m*2+2];
                    Idea a=heap[m*2];
                    heap[m*2+2]=null;
                    heap[m*2]=null;
                    heap[m*2+2]=a;
                    heap[m*2]=b;
                    swap(m*2+2); 
                }//else parent>leftchild
             }//while
        }//else
    }
   
    
    //deleteMin(); deletes the min, reassigns the min, reorders tree
    public Idea deleteMin() {
        if (n==0) {
            return null;
        }//if   
        Idea newMin=heap[n-1];
        heap[0]=newMin;
        heap[n-1]=null;
        n--;
        if (n>1) {
            swap2(0);
        }//if
        return heap[0];
    }//deleteMin()   
}//public class Heap.java