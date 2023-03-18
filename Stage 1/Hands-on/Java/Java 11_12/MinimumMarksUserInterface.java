import java.util.*;
import java.util.stream.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of students");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the student details");
		String markDetails[] = new String[num];
		for (int i = 0; i < num; i++) {
			markDetails[i] = sc.nextLine();
		}

		Map<String, Double> studMap = retrieveMinAndMaxMarksDetails(getStudentList(markDetails));

		for (Map.Entry entry : studMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

	}

	// Create Student objects, set the values and return the List<Student>
	
	public static List<Student> getStudentList(String[] studArray) {

		// Fill the code here
        List<Student> stu = new ArrayList<>();
        for(String e : studArray) {
            String arr[] = e.split(":");
            Student stud = new Student(arr[0],arr[1],Double.parseDouble(arr[2]));
            stu.add(stud);
        }
		return stu;
	}

	// Make use of Collectors.teeing method to find the maximum and minimum marks of students and return the map(student name as key and minimum or maximum marks as value)
	// Also use local variable syntax for lambda parameters
	
	public static Map<String, Double> retrieveMinAndMaxMarksDetails(List<Student> studentList) {

		// Fill the code here
		Map<String, Double> hash = studentList.stream().collect(Collectors.teeing(Collectors.maxBy(Comparator.comparing(Student::getMarks)), Collectors.minBy(Comparator.comparing(Student::getMarks)), (var s1, var s2) -> {
		    Map<String, Double> hm = new HashMap<>();
		    hm.put(s1.get().getName(), s1.get().getMarks());
		    hm.put(s2.get().getName(), s2.get().getMarks());
		    return hm;
		}
        ));
		return hash;
	}
}
