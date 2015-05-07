import java.util.Scanner;
class ParseFile {
    //instance variables??
    String file;
    //constructor??
    public ParseFile () {
        file=file;
    }
    //methods!
    public void parse(String file) {
        //whole bunch of conditionals that sort what type of function 
        //will be called
        
        //first you read in everything, then split by '\n'
        //splits file by newline
        String[] lineList= file.split("\n");
        for (int i=0; i<lineList.length; i++) {
            String line=lineList[i];
            String[] classString= line.split("=");
            for (int j=0; j<classString.length; i++) {
                //now look for the first object in this array
                // because the lines look like this:
                // student = karos, jason, 2323, 4343
                //when it splits it is ['student','karos,jason,2323,4343']
                
                //for STUDENTS
                if (classString[0]=="student") {
                    String[] params=classString[1].split(",");
                    //now it looks like ['karos','jason','2323','4343']
                    //read in here
                    Student student=new Student(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                    
                }//student if
                
                //for IDEAS
                else if (classString[0]=="idea") {
                    String[] params=classString[1].split(",");
                    //now looks like ['ssn','description','rating
                    //read in here
                    Idea idea=new Idea(Integer.parseInt(params[0]),params[1],Integer.parseInt(params[2]));
                }//idea if
            
        }
        
        //then for each string there, split by '='
         
        //for each of those, search the first string, see what function
        //it will be
        
        //create a series of conditionals that account for each of these
        //funtions, place parameters in the function
    }
    }
}