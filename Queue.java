
import java.io.Serializable;
import java.util.Scanner;

class Queue implements Serializable { //implements Serializable
    private int size;
    private int front;
    private int end;
    private int n;
    private Idea[] queue=new Idea[10];

    //constructor :
    //set size to 10, tracks front end and length of list 
    public Queue() {
        size=10;
        front=0;
        end=0;
        n=0;
    }//constructor
    
    //Enqueue() -- puts student into queue, knocks out 1st idea if 11th idea comes in
    //Params: Idea
     public void enqueue(Idea x) {
        if (n==10) {
            front=(front+1)%10;
            queue[end]=x;
            n=10;
        }//if
        else{
            queue[end]=x;
            n++;
         }//else
        end=(end+1) % 10;
    }//enqueue

    //printQueue() prints to screen
    public void printQueue() {
        //System.out.println(front);
        //System.out.println(end);
        if (front <= end)
           for(int i = front; i < end; i++)
               System.out.println(queue[i].getKey());
        else {
           for(int i = front; i < 10; i++)
               System.out.println(queue[i].getKey());
           for(int i = 0; i < end; i++)
               System.out.println(queue[i].getKey());
        }//else
    }//printQueue()
    
    //isEmpty() checks if queue is empty
    public boolean isEmpty() {
        if (front==end) {
            return true;
        }//if
        else {
            return false;
        }//else
    }//isEmpty

    //front() returns first item in queue
    public Idea front() {
        if (isEmpty() ) {
            return null;
        }//if
        else {
            return queue[front];
        }//else
    }//front()

    //dequeue() removes first item from front of queue
    public Idea dequeue() {
        Idea temp=queue[front];
        front=(front+1)% 10;
        return temp;
    }//dequeue

    //getAll() returns the entirety of the student idea queue
    public Idea[] getAll() {
        return queue;
    }//getAll()

    //getSize returns the size of the queue
    public int getSize() {
        return n;
    }//getSize

} // class
