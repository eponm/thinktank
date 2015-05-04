//Jessica Spencer
//Heap
//  array of Ideas (you can assume a max size of 120 or so). Write your own test program that tests each of the heap functions. Make sure that the test program handles all of the possibilities; you will be graded on your test program as well as your class definition. This assignment is due 14 Apr at the start of class. Submit your class description for Heap and Idea, and you test program.

//heap -- min number is next number helped
//heap functions:
//isEmptyHeap()
//findMin();
//deleteMin();
//insert(Idea x);
//printHeap()

public class Heap {
    int n;
    Idea[] heap;

    // Constructor
    public Heap() {
      n = 0;
      heap = new Idea[128];
    } // constructor

    public boolean isEmptyHeap() {
        return n==0;
    }

    public int findMin() {
        if (isEmptyHeap()==true) {
            return 0;
        }
        return heap[0].getKey();
    }


    public void printHeap() {
        for (int i=0;i<(n);i++) {
            System.out.println(heap[i].getKey());
        }
    }


    public void insert(Idea x) {
        heap[n]=x;
        if (n!=0) {
            swap(n-1);
        }
        n+=1;
    }

    //helper function for inserted Ideas
     public void swap(int index) {
         int parentIndex=index/2;
         if (index==2) {
             if (heap[0].getKey()> heap[index+1].getKey()) {
                  Idea a=heap[0];
                  Idea b=heap[index+1];
                  heap[index+1]=a;
                  heap[0]=b;
             }
         }
         while (heap[parentIndex].getKey() > heap[index+1].getKey()){
            Idea b=heap[index+1];
            heap[index+1]=heap[parentIndex];
            heap[parentIndex]=b;
            swap(parentIndex);
         }
     }

    //helper function for rearranging after Ideas are deleted
    public void swap2(int m) {
         while (heap[m].getKey() > heap[m+1].getKey()){
            Idea b=heap[m+1];
            Idea a=heap[m];
            heap[m+1]=null;
            heap[m]=null;
            heap[m+1]=a;
            heap[m]=b;
            swap(m+1);
         }
     }

    public Idea deleteMin() {
        if (n==0) {
            return null;
        }
        Idea newMin=heap[n-1];
        heap[0]=newMin;
        heap[n-1]=null;
        n--;
        if (n>1) {
            swap2(0);
        }
        return heap[0];
    }


}
