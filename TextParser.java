import java.util.Scanner; // For scanning, obviously.
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


// This assumes that your input is UTF-8! Make sure that only a UTF-8 file gets passed in!

public class TextParser {

    // Some reusable things that ought not to get changed while operating
    // If they do change, bad magic happens, probably

    private final Path filePath; // Stores the path of the file being read in
    private final static Charset ENCODING = StandardCharsets.UTF_8; // Change encoding type here


    // Main method to verify that the module is functional
    // REQUIRES A TEST FILE NAMED TEST.TXT WITH KEY VALUE PAIRS AS KEY=VALUE
    public static void main(String... aArgs) throws IOException {
        System.out.println("::: Testing TextParser :::");
        // Safe to assume that test.txt will be in same directory? Sure, why not
        TextParser parser = new TextParser("test.txt");
        // Call over to the method to read in lines
        parser.readAll();
        System.out.println("Test complete. Great work, team.");
        // That was easy
    } // main


    // Constructor
    public TextParser(String fileName){
        // Get and store the full path and name of the file.
        // This makes working with the file a little bit safer, hopefully.
        filePath = Paths.get(fileName);
    } // constructor


    // Calls the line processing method and will throw an exception if something goes wrong
    public void readAll() throws IOException {
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
            while (scanner.hasNextLine()){
                readLineIn(scanner.nextLine());
            } // while
        } // try
    } // readLineInByLine


    private void readLineIn(String line){
        // Instantiate a scanner to parse each line
        Scanner scanner = new Scanner(line);
        // Tell the scanner to delimit with each equals sign
        scanner.useDelimiter("=");
        // Get the next line, if there is one
        if (scanner.hasNext()) {
            // Lines will be pairs of data as "key = value", hopefully...
            String name = scanner.next();
            String value = scanner.next();
            // Print values out (uses .trim() to eliminate surrounding whitespace)
            System.out.println("Key   " + name.trim() + "   with value   " + value.trim());
        } // if
        else {
            System.out.println("[This line is either missing or not valid]");//debug
        } // else
    } // readLineIn


} // class
