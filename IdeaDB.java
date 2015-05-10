import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!
import java.io.Serializable;

/* the IdeaDB includes methods to 
    INSERT
    - put idea into Core list of ideas and into Heap of ideas
    - put student into the two trees ordered by ID and by SSN
    ACCESS
    - search, remove, access and save the student ideas/students in student Trees
    - access Best Idea and sell it
    OUTPUT
    - print all the Students
    - print all the ideas
 */



class IdeaDB implements Serializable {

    private List coreList;
    private Heap ideaHeap;
    private BinaryTree studentSSNTree;
    private BinaryTree studentIDTree;
    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
        studentSSNTree = new BinaryTree();
        studentIDTree = new BinaryTree();
    } // constructor

    //save the studentSSNTree
    public Student[] saveTreeArray() {
        Student[] arr = studentSSNTree.treeToArray();
        return arr;
    }//saveTreeArray()


    //printStudents out to screen
    public boolean printStudents(PrintWriter out) {
        studentSSNTree.printTree(out);
        return true;
    }//printStudents


    //printIdeas out to screen
    public boolean printIdeas(PrintWriter out) {
        for (int i=0; i<coreList.length(); i++) {
            // Params: submittor SSN, description, rating, seqnum
            out.write("idea = \""+coreList.access(i).getKey()+"\",\"" +coreList.access(i).getDesc()+"\",\"'"+coreList.access(i).getSeqNum()+"\",\""+coreList.access(i).getRating()+"\""+coreList.access(i).isInHeap()+"\n");
        }//for
        return true;
    }//printIdeas


    // insertIdea - adds a new idea
    public void insertIdea(Idea newIdea) {
        // Get the submittor's key
        int key = newIdea.getKey();

        // Put the idea coreList, ideaHeap and studentSSNTree:
        // Set seqNum in newIdea
        newIdea.setSeqNum(seqNum);
        seqNum++;
        // Add idea to list
        coreList.insert(newIdea);
        // Add idea to heap if necessary
        ideaHeap.insert(newIdea);

        newIdea.flip();

        // Add the idea to the student's queue
        Student student = studentSSNTree.searchSSN(key);
        student.addToQueue(newIdea);
    } // insert


  //   public void reInsertIdea(Idea newIdea) {
  //       // Get the submittor's key
  //       int key = newIdea.getKey();

  //       // Put the idea coreList, ideaHeap and studentSSNTree:
  //       // Set seqNum in newIdea
  //       newIdea.setSeqNum(seqNum);
  //       seqNum++;
  //       // Add idea to list
  //       coreList.insert(newIdea);
  //       // Add idea to heap if necessary
  //       if (newIdea.isInHeap()) {
  //           ideaHeap.insert(newIdea);
  //       }

  //       // Add the idea to the student's queue
  //       Student student = studentSSNTree.search(key);
  //       student.addToQueue(newIdea);
  //       int id = student.getID();
  // //      student = studentIDTree.search(id);
  //  //     student.addToQueue(newIdea);
  //   } // insert



    //addStudent
    //adds a student to IdeaDB
    public void addStudent(Student newStudent) {
        // Doesn't double stored data because of how Java handles objects
        studentSSNTree.ssnInsert(newStudent);
        studentIDTree.idInsert(newStudent);
    }//addStudent


    //deleteStudent
    //deletes student from both trees
    public void deleteStudent(Student targetStudent){
        studentSSNTree.idDelete(targetStudent);
        studentIDTree.ssnDelete(targetStudent);
    }
    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
        temp.flip();
        return temp;
    } // sell

    //getBestIdea() gets the best idea
    public Idea getBestIdea(){
        return ideaHeap.findMin();
    }//getBestIdea()


    //getStudent() returns the student
    public Student getStudent(int key, boolean usingSSN){
        if(usingSSN==true){

            return studentSSNTree.searchSSN(key);
        }//if
        else{
            return studentIDTree.searchID(key);
        }//else
    }//student

} // class
