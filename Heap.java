//heap -- min number is next number helped
public class Heap {
    int n; //counter for 
    Node[] heap;//array of nodes
    
    //constructor
    //params: n, node[]
    public class(int n, Node[] heap) {
        int n=0;
        heap=new Node[128];
    }//constructor
    
//isEmptyHeap() checks if heap is empty    
    public boolean isEmptyHeap() {
        return n==0;
    }
    
//findMin();finds and returns the only accessible value in the heap
    public String findMin() {
        if (isEmptyHeap()==true) {
            //return 0;
            System.out.println("There are no ideas here");//debug
        }
        else {
            System.out.println("The best idea is Idea #" + heap[0].getSeqNum() + "/nrated at" + heap[0].getRating() + "here is the description: /n" + heap.getDesc());//debug
        }
    }  
    
 //printHeap() prints all the values in the heap in order  
 // debug
    public void printHeap() {
        for (int i=0;i<(n);i++) {
            System.out.println(heap[i].getKey());
        }
    } 
    
 //insert(Node x); inserts node into heap reorders heap   
    public void insert(Node x) {
        heap[n]=x;
        if (n!=0) {
            swap(n-1);
        }
        n+=1;
    } 
    
//helper function for inserted nodes
     public void swap(int index) {
         int parentIndex=index/2;
         //if parent is the root
         if (index==2) {
             if (heap[0].getKey()> heap[index+1].getKey()) {
                  Node a=heap[0];
                  Node b=heap[index+1];
                  heap[index+1]=a;
                  heap[0]=b;
             }//swaps parent/child
         }//if parent is the root
         //for all other swaps, finds parent with direct index
         while (heap[parentIndex].getKey() > heap[index+1].getKey()){
            Node b=heap[index+1];
            heap[index+1]=heap[parentIndex];
            heap[parentIndex]=b;
            swap(parentIndex); 
         }
     }
    
//helper function for rearranging after nodes are deleted
    public void swap2(int m) {
        int j=0;
         while (heap[m*2].getKey() > ((heap[m*2+1].getKey() || heap[m*2+2].getKey())){
            if (heap[m*2].getKey() > heap[m*2+1].getKey()){
                Node b=heap[m*2+1];
                Node a=heap[m*2];
                heap[m*2+1]=null;
                heap[m*2]=null;
                heap[m*2+1]=a;
                heap[m*2]=b;
                swap(m*2+1); 
            }
            else {
                Node b=heap[m*2+2];
                Node a=heap[m*2];
                heap[m*2+2]=null;
                heap[m*2]=null;
                heap[m*2+2]=a;
                heap[m*2]=b;
                swap(m*2+2); 
            }
            
             //for the other child EDIT
         }
     }
   
//deleteMin(); deletes the min, reassigns the min, reorders tree
    public Node deleteMin() {
        if (n==0) {
            return null;
        }   
        Node newMin=heap[n-1];
        heap[0]=newMin;
        heap[n-1]=null;
        n--;
        if (n>1) {
            swap2(0);
        }
        return heap[0];
    }   
}