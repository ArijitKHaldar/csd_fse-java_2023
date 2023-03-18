import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.io.IOException;


public class UserInterface {

	public static void main(String[] args) throws IOException {

		Path path1 = Paths.get("SchoolBusAllocation/studentDetails.txt");
		Path path2 = Paths.get("SchoolBusAllocation/studentDetailsWithBusNo.txt");
        String studDetails=readFile(path1);
        System.out.println("The details of the students read from studentDetails.txt file:\n"+studDetails);
	    writeFile(studDetails,path2);
	    long mismatchPos=checkMismatch(path1,path2);
	    System.out.println("The mismatched position is "+mismatchPos);
        
	}

    // Use Files.readString() to read the contents from studentDetails.txt file
	public static String readFile(Path path1) {

		// Fill the code here
		String content = "";
		try {
		    content = Files.readString(path1);
		} catch (IOException e) {
		    e.printStackTrace();
		}

		return content;

	}

    // Use Files.writetring() to write the contents to studentDetailsWithBusNo.txt file
	public static void writeFile(String studentDetails, Path path2) {

		// FIll the code here
		String line[] = studentDetails.split("\n"), final_string = "";
		for (int i = 0; i < line.length; i++) {
		    if(line[i].contains("Washington"))
		        line[i] += ",BusNo-3";
		    else if(line[i].contains("Chicago"))
		        line[i] += ",BusNo-4";
		    else if(line[i].contains("Boston"))
		        line[i] += ",BusNo-5";
		}
		for (int i = 0; i < line.length; i++) {
		    if(i == line.length - 1)
		        final_string+=line[i];
		    else
    		    final_string+=line[i]+"\n";
		}
		try {
	            Files.writeString(path2, final_string,StandardOpenOption.APPEND);
	    } catch (IOException e) {
		        e.printStackTrace();
	    }
	}

    // Use Files.mismatch() to compare two files file
	public static long checkMismatch(Path path1, Path path2) {
        
        long mis_match = 0;
		// Fill the code here
		try {
            mis_match = Files.mismatch(path1,path2);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return mis_match;

	}

}
