import java.util.*;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.lang.invoke.MethodHandles;

public class UserInterface {

	public static void main(String[] args) {
		
        Scanner sc=new Scanner(System.in);
        String fname,lname,fname1,lname1;
        System.out.println("Enter the first name of resident1");
        fname=sc.next();
        System.out.println("Enter the last name of resident1");
        lname=sc.next();
        Optional res1=concatNames(fname,lname);
        String n1=res1.get().toString();
        System.out.println("Enter the first name of resident2");
        fname1=sc.next();
        System.out.println("Enter the last name of resident2");
        lname1=sc.next();
        Optional res2=concatNames(fname1,lname1);
        String n2=res2.get().toString();
        int res3=calculateLength(n1,n2);
        String res4=stringIndentation(n1, 5);
        String res5=stringIndentation(n2, 5);
        System.out.println("Enter the search character");
        char ch=sc.next().charAt(0);
        String res6=displayName(n1,ch);
        System.out.println("The fullname of resident1 is");
        System.out.println(res1.get());
        System.out.println("The fullname of resident2 is");
        System.out.println(res2.get());
        System.out.println("The length of the bigger name is");
        System.out.println(res3);
        System.out.println("Names after indentation");
        System.out.print(res4);
        System.out.print(res5);
        System.out.println("The number of times the search character occurs in resident1's fullname is");
        System.out.println(res6);
	}
	
	// Use describeConstable method
	public static Optional concatNames(String fname, String lname)
	{
		// Fill the code
		String value = fname.toUpperCase()+" "+lname.toUpperCase();
		return value.describeConstable();

	}
	
	// Use transform method
	public static int calculateLength(String name1,String name2)
	{
		// Fill the code
		int size = name1.transform(t -> {
		    if (name1.length() > name2.length())
		        return name1.length();
		    else
		        return name2.length();
		});
		return size;

	}
	
	// Use indent method
	public static String stringIndentation(String data,int val)
	{
		// Fill the code
		
		return data.indent(val);

	}
	
	
	// Use resolveConstantDesc method
	public static String displayName(String msg, char ch)
	{
		// Fill the code
        int count = 0;
		for (int i=0; i< msg.length(); i++) {
		    if(msg.charAt(i) == Character.toUpperCase(ch))
		        count++;
		}
		String result = Character.toString(ch)+" appears "+Integer.toString(count)+" times";
		result = result.resolveConstantDesc(MethodHandles.lookup());
        return result;
	}
}
