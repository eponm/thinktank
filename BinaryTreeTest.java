class TestBST {
    public static void main(String[] args){
	BinaryTree a = new BinaryTree();
	System.out.println("isEmptyTree = " + a.isEmptyTree());
	Student xStudent = new Student("Jane","jj",9576,2739);
	Student yStudent = new Student("Joe","joejoe", 7777,7890);
	Student zStudent = new Student("Jack","jackattack", 2234,7234);
	Student kStudent = new Student("Jill","hillJill", 9345,6856);
	Student aStudent = new Student("Abe","abeBabe", 1235,3788);
	Student bStudent = new Student("Beth","sethBeth", 9645,7898);
	Student cStudent = new Student("Chuck","chuckDuck", 6234,5238);
	Student dStudent = new Student("Dot", "thuglife",9665,6858);
	Student mStudent = new Student("Mike","fight", 7245,2237);
	Student nStudent = new Student("Nick","theo", 7345,7857);
	Student oStudent = new Student("Otis", "Boatis", 6545,2222);
	a.ssnInsert(xStudent);
	a.ssnInsert(yStudent);
	a.ssnInsert(zStudent);
	a.ssnInsert(kStudent);
	a.traverse();
	a.ssnInsert(mStudent);
	a.ssnInsert(nStudent);
	System.out.println("search");
	System.out.println(a.searchSSN(9576).getName());
	System.out.println(a.searchSSN(7777).getName());
	System.out.println(a.searchSSN(2234).getName());
	System.out.println(a.searchSSN(9345).getName());
	a.traverse();
	System.out.println("searchFor 7777");
	System.out.println(a.searchSSN(7777).getName());
	System.out.println("delete9576");
	a.ssnDelete(xStudent);
	a.traverse();
	System.out.println("delete7245");
	a.ssnDelete(mStudent);
	a.traverse();
	System.out.println("delete7345");
	a.ssnDelete(nStudent);
	a.traverse();
	System.out.println("ssnInsert1234");
	a.ssnInsert(aStudent);
	a.traverse();
	System.out.println("ssnInsert6545");
	a.ssnInsert(oStudent);
	a.traverse();
	System.out.println("delete2234");
	a.ssnDelete(zStudent);
	a.traverse();
	System.out.println("isEmptyTree = " + a.isEmptyTree());
    }
}