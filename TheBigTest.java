/* TheBigTest.java
Code to compile all dependencies at once. Probably convenient.
Add in test code as needed. All correctly-coded tests should always pass; otherwise they should be rewritten or removed.
*/

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

class TheBigTest {

    public static void main(String[] args) throws IOException {

        BinaryTree ssnBST = new BinaryTree();
        BinaryTree studentNumBST = new BinaryTree();
        Queue1 testQueue = new Queue1();
        Heap ideaHeap= new Heap();
        
        //5 students, each with at least 2 ideas
        //student number 1: has 10 ideas
        Student testStudent = new Student("testName", "tname", 0123, 6789);
        Idea testIdea = new Idea(9999, "Description goes here.", 100);
        Idea anotherIdea = new Idea(8888, "Another description.", 50);
        //student number 2
        Student testStudent2= new Student("testName2", "tname2" , 0125, 6790);
        Idea testIdea3 = new Idea(7777,"Oh look how smart I am", 75);
        Idea testIdea4 = new Idea(6666, "Wow it's amazing", 25);
        //student number 3
        Student testStudent3 = new Student ("testNmae3", "tnsd3" , 0124,6780);
        Idea testIdea5= new Idea(5555,"I'M A GENIUS GOD DAMMIT!!",10);
        Idea testIdea6= new Idea(4444, "wwwwwwwwooooooofff",11);
        //student number 4
        //student number 5

        //Testing the queue
        System.out.println("test queue start with print");
        System.out.println(testQueue);
        System.out.println("enqueue then print");
        testQueue.enqueue(testIdea);
        testQueue.printQueue();
        System.out.println("enqueue then print");
        testQueue.enqueue(anotherIdea);
        testQueue.printQueue();
        System.out.println("add to queue");
        testStudent.addToQueue(testIdea3);
        

//TESTING THE LIST
        System.out.println("TESTING THE LIST***************");
        List testList = new List();
        testList.insert(testIdea);
        testList.insert(anotherIdea);
        System.out.println("Searching List after an insert. Should return 8888");
        System.out.println(testList.searchReturn(8888).getKey());
        // Outputs "888"
        System.out.println("get prior should equal null");
        System.out.println(testList.searchReturn(8888).getPrior());
        System.out.println("get description of 8888-- should be 'desc. goes here' or somethin or other");
        System.out.println(testList.searchReturn(8888).getDesc());
        System.out.println("now inserting more ideas into Idea List");
        testList.insert(testIdea3);
        testList.insert(testIdea4);
        testList.insert(testIdea5);
        testList.insert(testIdea6);
        System.out.println("printing List");
        System.out.println(" ");
        testList.printList();
        System.out.println(" ");
        System.out.println("search remove 6666 then print ");
        testList.searchRemove(6666);
        testList.printList();
        
   /*     //testing the Heap
        System.out.println();
        System.out.println("testing the HEAP!!");
        ideaHeap.insert(testIdea);
        ideaHeap.insert(anotherIdea);
        ideaHeap.insert(testIdea3);
        ideaHeap.insert(testIdea4);
        ideaHeap.insert(testIdea5);
        System.out.println("inserted ideas-- printing heap");
        ideaHeap.printHeapFormat();

  /*      TextParser reader = new TextParser("test.txt");
        reader.readAll(); */

    } // main

} // class
