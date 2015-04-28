
/*BST.java
	head - Node type pointer that will point to head

	Methods:
		isEmptyTree()
		search()
			searchTwo()<----private helper function 
		insert()
			insertTwo()<----^^
		traverse()
			traverseTwo()<---^^
		delete()
			deleteTwo()<---^^
			popMax()<----^^
		printTree()
			printTree2()<---^^
*/

public class BST{
	private Student head;

	//Constructor
	//sets head to null
	public BST(){
		head = null;
	}//Constructor

	//isEmptyTree
	//Returns boolean value True if head points to null, else returns False
	public boolean isEmptyTree(){
		return head==null;
	}//isEmptyTree

	//search
	//returns Student
	//Params: target Student SSN
	public Student search(int key){
		if(head==null) return null;
		else if(key<head.getKey()){
			return searchTwo(head.getLeft(),key);
		}
		else if(key>head.getKey()){
			return searchTwo(head.getRight(),key);		
		}
		else{
			return head;
		}
	}

	//Search method helper, used when searching for Student that isn't head
	//Params: Student, target Student SSN	
	public Student searchTwo(Student branch, int key){
		if(key<branch.getKey()){
			return searchTwo(branch.getLeft(),key);
		}
		else if(key>branch.getKey()){
			return searchTwo(branch.getRight(),key);
		}
		else{
			return branch;
		}
	}//search

	//insert
	//inserts Student into BST
	//Params: Student to be inserted
	public void insert(Student newStudent){
		if(head==null){
			head=newStudent;
		}
		else{
			insertTwo(head,newStudent);
		}
	}

	//insert helper
	//Params: Student in tree, Student to be inserted
	private void insertTwo(Student branch,Student newStudent){
		if(newStudent.getKey()<branch.getKey()){
			if(branch.getLeft()==null){
				branch.setLeft(newStudent);
			}
			else{
				insertTwo(branch.getLeft(),newStudent);
			}
		}
		else{
			if(branch.getRight()==null){
				branch.setRight(newStudent);
			}
			else{
				insertTwo(branch.getRight(),newStudent);
			}
		}
	}//insert

	//traverse
	//prints tree to consol from left to right
	public void traverse(){
		if(head!=null){
			traverseTwo(head.getLeft());
			System.out.print(head.getKey()+" ");
			traverseTwo(head.getRight());
		}
	}

	//traverse helper
	private void traverseTwo(Student branch){
		if(branch!=null){
			traverseTwo(branch.getLeft());
			System.out.print(branch.getKey()+" ");
			traverseTwo(branch.getRight());
		}
	}//traverse

	//delete
	//deletes a target node in a BST
	//Params: Student in BST
	public void delete(Student target){
		/*
		make note that tree prioritizes elements of higher value
		i.e. : when replacing elements, the tree will go LEFT, then the right-most
		leaf, rather than going RIGHT and getting the leftmost leaf because the ladder is
		of higher value. 
		*/
		if(head==null) return;
		int key=target.getKey();
		if(key<head.getKey()){
			int side=0;
			deleteTwo(head.getLeft(),key,head,side);
		}
		else if(key>head.getKey()){
			int side=1;
			deleteTwo(head.getRight(),key,head,side);		
		}
		else{
			/*
			pricipal of prioritizing higher values illustrated in this case:
			*/
			if(head.getLeft()==null&&head.getRight()==null){
				head=null;
			}
			else if(head.getLeft()==null){
				Student temp=head;
				Student temp2=popMax(head.getRight(),head,0); //note how it only gets the maximum leftmost
				//leaf of the RIGHT side if and only if getLeft() of head=null.
				this.delete(temp2);
				head=temp2;
				head.setRight(temp.getLeft());
				temp.setRight(null);
			}
			else{
				Student temp=head;
				Student temp2=popMax(head.getLeft(),head,1);
				this.delete(temp2);
				head=temp2;
				head.setLeft(temp.getLeft());
				head.setRight(temp.getRight());
				temp.setLeft(temp.getLeft());
			}
		}
	}

	//delete helper
	//cycles the target down the tree by comparing the target to the SSN of Student that
	//is to be deleted.
	//Params: target Student, SSN of Student to be deleted, Parent of target, interger
	//to represent what side of the parent the target is on (0[left] or 1[right])
	public void deleteTwo(Student target, int key, Student parent, int side){
		if(key<target.getKey()){
			side=0;
			deleteTwo(target.getLeft(),key,target,side);
		}
		else if(key>target.getKey()){
			side=1;
			deleteTwo(target.getRight(),key,target,side);
		}
		else{
			if(target.getLeft()==null&&target.getRight()==null){
				if(side==0){
					parent.setLeft(null);
				}
				else{
					parent.setRight(null);
				}
			}
			else if(target.getLeft()==null||target.getRight()==null){	
				if(side==0){
					if(target.getLeft()==null){
						Student successor=popMax(target.getRight(),parent,0);
						parent.setLeft(successor);
						successor.setRight(target.getRight());
					}
					else{
						Student successor=popMax(target.getLeft(),parent,1);
						parent.setLeft(successor);
						successor.setLeft(target.getLeft());
					}
				}
				else{
					if(target.getLeft()==null){
						Student successor=popMax(target.getRight(),parent,0);
						parent.setRight(successor);
						successor.setRight(target.getRight());
					}
					else{
						Student successor=popMax(target.getLeft(),parent,1);
						parent.setRight(successor);
						successor.setLeft(target.getLeft());
					}
				}
			}
			else{
				if(side==0){
					Student successor=popMax(target.getLeft(),parent,1);
					parent.setLeft(successor);
					successor.setRight(target.getRight());
					successor.setLeft(target.getLeft());
				}
				else{
					Student successor=popMax(target.getLeft(),parent,1);
					parent.setRight(successor);
					successor.setRight(target.getRight());
					successor.setLeft(target.getLeft());
				}
			}
		}
	}

	//popMax
	/* helper function for getting the maximum depth leaf in a certain direction, 
	(used in delete). Takes a Student and an int of either 0 or 1, 0==GO LEFT, 1==GO RIGHT.
	While loops down the line until null until next==null. Then returns the current
	Student the pointer is on.
	*/
	//Params: target Student to be deleted, Parent of target, side of the parent (0 or 1)
	private Student popMax(Student branch, Student parent, int side){
		if(side==0&&branch.getLeft()!=null){
			return popMax(branch.getLeft(),branch,0);
		}
		else if(side==1&&branch.getRight()!=null){
			return popMax(branch.getRight(),branch,1);
		}
		else{
			if(side==0){
				parent.setLeft(null);
				return branch;
			}
			else{
				parent.setRight(null);
				return branch;
			}
		}
	}//delete

	//printTree()
	//prints Students in BST with their left and right Children
    public void printTree() {
    System.out.println();
	printTree2(head);
	System.out.println();
    }

    //helper function
    //Params: Student			
    private void printTree2(Student tree) {
		if (tree != null) {
		    System.out.print(tree.getKey() + " ");
	            if (tree.getLeft() != null)
		        System.out.print("Left: " + tree.getLeft().getKey() + " ");
	            else
	                System.out.print("Left: null ");
	            if (tree.getRight() != null)
		        System.out.println("Right: " + tree.getRight().getKey() + " ");
	            else
	                System.out.println("Right: null ");
		    printTree2(tree.getLeft());
		    printTree2(tree.getRight());
	    }
	}
}
