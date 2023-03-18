import java.util.stream.Stream;

public class UserInterface {
	
	public static void main(String args[]) {
		
        String str1="     Hello \n     Welcome to\n     New String Methods in Java     ";
        String str2="Programming";
        int n=5;
        boolean res=validateSubmission(str1);
        if(res==true)
        System.out.println("The String is empty");
        else
        System.out.println("The String is not empty");
        Stream<String> res1=extractLines(str1);
        System.out.println("The extracted lines from the String are");
        res1.forEach(s->System.out.println(s.trim()));
        String res2=removeWhiteSpaces(str1);
        System.out.println("The String after removing the leading and trailing whitespaces is");
        System.out.println(res2);
        String res3=checkSubmission(str2,n);
        System.out.println("The String repeated after removing the vowels is");
        System.out.println(res3);
	}
	
	// Use isBlank() method here
	public static boolean validateSubmission(String str) 
	{
		// Fill the code
		if(str.isBlank())
		    return true;
		else
		    return false;
	}
	
	// Use lines() method here
	public static Stream<String> extractLines(String str) 
	{
		// Fill the code
		
		return str.lines();
	}
	
	// Use strip() method here
	public static String removeWhiteSpaces(String str) 
	{
		// Fill the code
		
		return str.strip();
	}
	
	// Use repeat() method here
	public static String checkSubmission(String str,int n) 
	{
		// Fill the code
		str = str.replaceAll("[aeiou]", "");
		str = str.replaceAll("[AEIOU]", "");
		return str.repeat(n);
	}
}
