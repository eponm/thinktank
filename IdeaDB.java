import java.io.PrintWriter; // For writing, obviously, I mean like...DUH!

class IdeaDB {

    private List coreList;
    private Heap ideaHeap;
    private BinaryTree studentSSNTree;
//    private BinaryTree studentIDTree;
    private int seqNum;

    // Constructor
    public IdeaDB() {
        seqNum = 1;
        coreList = new List();
        ideaHeap = new Heap();
        studentSSNTree = new BinaryTree();
//        studentIDTree = new BinaryTree(true);
    } // constructor


    public boolean printStudents(PrintWriter out) {
        studentSSNTree.printTree(out);
        return true;
    }

    public boolean printIdeas(PrintWriter out) {
        for (int i=0; i<coreList.length(); i++) {
            // Params: submittor SSN, description, rating, seqnum
            out.write("idea = \""+coreList.access(i).getKey()+"\",\"" +coreList.access(i).getDesc()+"\",\"'"+coreList.access(i).getSeqNum()+"\",\""+coreList.access(i).getRating()+"\""+coreList.access(i).isInHeap()+"\n");
        }
        return true;
    }


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
        Student student = studentSSNTree.search(key);
        student.addToQueue(newIdea);
        int id = student.getStudentID();
  //      student = studentIDTree.search(id);
   //     student.addToQueue(newIdea);
    } // insert


    public void reInsertIdea(Idea newIdea) {
        // Get the submittor's key
        int key = newIdea.getKey();

        // Put the idea coreList, ideaHeap and studentSSNTree:
        // Set seqNum in newIdea
        newIdea.setSeqNum(seqNum);
        seqNum++;
        // Add idea to list
        coreList.insert(newIdea);
        // Add idea to heap if necessary
        if (newIdea.isInHeap()) {
            ideaHeap.insert(newIdea);
        }

        // Add the idea to the student's queue
        Student student = studentSSNTree.search(key);
        student.addToQueue(newIdea);
        int id = student.getStudentID();
  //      student = studentIDTree.search(id);
   //     student.addToQueue(newIdea);
    } // insert



    //addStudent
    //adds a student to IdeaDB
    public void addStudent(Student newStudent) {
        // Doesn't double stored data because of how Java handles objects
        studentSSNTree.insert(newStudent);
        //studentIDTree.insert(newStudent);
    }//addStudent


    //deleteStudent
    //deletes student from both trees
    public void deleteStudent(Student targetStudent){
        studentSSNTree.delete(targetStudent);
//        studentIDTree.delete(targetStudent);
    }
    // "Sell" - deletes top-rated idea and returns it
    public Idea sell() {
        // Store the min in a temporary slot and get rid of it from the heap
        Idea temp = ideaHeap.deleteMin();
        temp.flip();
        return temp;
    } // sell
    public Idea getBestIdea(){
        Idea bestIdea=ideaHeap.findMin();
        if (bestIdea!=null){
            System.out.println("The best idea is Idea #" + bestIdea.getSeqNum() + "\nrated at" + bestIdea.getRating() + "here is the description: \n" + bestIdea.getDesc());//debug
        }
        return bestIdea;
    }

    public Student getStudent(int key){
        //if(usingSSN==true){
            return studentSSNTree.search(key);
        //}
        // else{
        //     return studentIDTree.search(key);
        // }
    }

} // class
