import java.util.Scanner;

public class Queue {

    private int size; // Left TBD in the constructors
    private int front;
    private int end;
    private int n;
    private Idea[] queue;

    public Queue() {
        size = 128;
        Idea[] queue = new Idea[128];
        front = 0;
        end = 0;
        n = 0;
        // for(int i=0;i<128;i++){
        //     queue[i]=null;
        // }
    } // default constructor


    public Queue(int sizeIn) {
        size = sizeIn;
        Idea[] queue = new Idea[size];
        front = 0;
        end = 0;
        n = 0;
        // for(int i=0;i<128;i++){
        //     queue[i]=null;
        // }
    } // size-specific constructor


    public void enqueue(Idea x) {
        queue[end]=x;
        end=(end + 1) % size;
    } // constructor

/*
// Can't use getKey() with Idea objects. Edit later if necessary.

    public void printQueue() {
        System.out.println(front);
        System.out.println(end);
        if (front <= end) {
           for(int i = front; i < end; i++) {
               System.out.println(queue[i].getKey());
           } // for
        } // if
        else {
           for(int i = front; i < 10; i++) {
               System.out.println(queue[i].getKey());
           } // for
           for(int i = 0; i < end; i++) {
               System.out.println(queue[i].getKey());
           } // for
        } // else
    } // printQueue

*/

    public boolean isEmpty() {
        if (front == end) {
            return true;
        }
        else {
            return false;
        }
    } // isEmpty


    public Idea front() {
        if (isEmpty()) {
            return null;
        }
        else {
            return queue[front];
        }
    } // front


    public Idea dequeue() {
        Idea temp=queue[front];
        front=(front + 1) % size;
        return temp;
    } // dequeue

    //getAll()
    //returns all items in queue in an array
    public Idea[] getAll(){
        Idea[] all = new Idea[size];
        System.arraycopy(queue, 0, all, 0, size);
        return all;
    }
} // class
