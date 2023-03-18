import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class UserInterface {
	
	public static void main(String args[]) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println( "Enter the Connection ID :" );	
		int connectionId=sc.nextInt();
		System.out.println( "Enter the Connection Type :" );	
		String connectionType=sc.next();
		System.out.println( "Enter the Previous Reading :" );	
		int previousReading=sc.nextInt();
		System.out.println( "Enter the Current Reading :" );	
		int currentReading=sc.nextInt();
		int readingValue =currentReading-previousReading;
		outer:{
		String connection=checkConnectionType(connectionType);
		System.out.println(connection);
		if(connection.equals("Invalid Connection"))
		{
			break outer;
		}
		int billAmount;
		if(readingValue>0 && readingValue<=200)
		{
			billAmount= readingValue*2;
		}
		else
		{
			billAmount = readingValue*5;
		}
		System.out.println("Bill Amount (LongNumberFormat) : "+longNumberFormat(billAmount));
		System.out.println("Bill Amount (ShortNumberFormat) : "+shortNumberFormat(billAmount));

		}	 	  	  		      	 	  	        	 	

}
	// Use Enhanced switch statement to check the connection type
	public static String checkConnectionType(String connectionType) {

		//Fill the Code
		String result = switch(connectionType) {
		    case "SinglePhase", "ThreePhase", "SINGLEPHASE", "THREEPHASE" -> "Valid Connection";
		    default -> "Invalid Connection";
		};
	    return result;	
	
	}

    // Use Compact Number Formatting to convert the number into Long Number Format
	public static String longNumberFormat(int number) {
		
		//Fill the Code
		
        NumberFormat val = NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.LONG);
        return val.format(number);
	
	}
	
	// Use Compact Number Formatting to convert the number into Short Number Format
	public static String shortNumberFormat(int number) {
		
	    //Fill the Code
        NumberFormat val = NumberFormat.getCompactNumberInstance(new Locale("en", "US"), NumberFormat.Style.SHORT);
        
	    return val.format(number);
	}
	
}
