import java.util.Scanner;
public class Queue {
    int front=0;
    int end=0;
    int n=0;
    Idea[] queue= new Idea[10];
    
    public void enqueue(Idea x) {
        queue[end]=x;
        end=(end+1) % 10;
    }

    public void printQueue() {
        System.out.println(front);
        System.out.println(end);
        if (front <= end)
           for(int i = front; i < end; i++) 
               System.out.println(queue[i].getKey());
        else {
           for(int i = front; i < 10; i++) 
               System.out.println(queue[i].getKey());          
           for(int i = 0; i < end; i++) 
               System.out.println(queue[i].getKey());
        }          
    }
    public boolean isEmpty() {
        if (front==end) {
            return true;
        }
        else {
            return false;
        }
    }

    public Idea front() {
        if (isEmpty() ) {
            return null;
        }
        else {
            return queue[front];
        }
    }

    public Idea dequeue() {
        Idea temp=queue[front];
        front=(front+1)% 10;
        return temp;
    }
}