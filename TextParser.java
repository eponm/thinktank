

import java.util.Scanner; // For scanning, obviously.

// FOLLOWING IMPORT REQUIRED IN ALL UPSTREAM MODULES
import java.io.IOException; // For helpfully throwing exceptions if necessary

import java.nio.charset.Charset; // For explicit character encodings
import java.nio.charset.StandardCharsets; // For explicit character encodings

import java.nio.file.Path; // For dealing with a file's full path and name, just in case
import java.nio.file.Paths; // For dealing with a file's full path and name, just in case


// This assumes that the input is UTF-8! Make sure that only a UTF-8 file gets passed in!

public class TextParser {

    // These get overwritten a lot, so don't code up access or setter methods, please.
    private String[] container = new String[2];
    private String one;
    private String two;

    // Some reusable things that ought not to get changed while operating
    // If they do change, bad magic happens, probably
    private final Path filePath; // Stores the path of the file being read in
    private final static Charset ENCODING = StandardCharsets.UTF_8; // Change encoding type here


    // Constructor
    public TextParser(String fileName){
        // Get and store the full path and name of the file.
        // This makes working with the file a little bit safer, hopefully.
        filePath = Paths.get(fileName);
    } // constructor


    public String[] read() {
        // Load a scanner
        try (Scanner scanner =  new Scanner(saveFile, ENCODING.name())) {
            while (scanner.hasNextLine()){
                scanner.useDelimiter("="); // Tell the scanner to delimit on each equals sign
                if (scanner.hasNext()) { // Get the next line if there is one
                    one = scanner.next().trim();
                    two = scanner.next().trim();
                } // if
                else { // If there is no line
                    one = "Invalid key or line";
                    two = "Invalid value or line";
                } // else
                container[0] = one;
                container[1] = two;

                //some debug
                System.out.println("Container contains the following:");//debug
                for (int i=0; i<2; i++) System.out.println(container[i]);//debug

            } // while
        } // try
        catch (IOException x) {
            System.out.println("Bad magic happened!");
        } // catch
    }


    // Calls the line processing method and will throw an exception if something goes wrong
    public void readAll() throws IOException {
        try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
            while (scanner.hasNextLine()){
                readLineIn(scanner.nextLine());
            } // while
        } // try
        catch (IOException x) {
            System.out.println("Bad magic happened!");
        } // catch
    } // readAll


    public String[] readLineIn(String line){
        // Instantiation
        String[] container = new String[2];
        String one;
        String two;
        Scanner scanner = new Scanner(line);

        // Tell the scanner to delimit on each equals sign
        scanner.useDelimiter("=");
        // Get the next line, if there is one
        if (scanner.hasNext()) {
            // Lines will be pairs of data as "key = value", hopefully...
            one = scanner.next().trim();
            two = scanner.next().trim();
        } // if
        else {
            one = "Invalid key or line";
            two = "Invalid value or line";
        } // else
        container[0] = one;
        container[1] = two;

        // Then return the array
        return container;
    } // readLineIn


} // class
