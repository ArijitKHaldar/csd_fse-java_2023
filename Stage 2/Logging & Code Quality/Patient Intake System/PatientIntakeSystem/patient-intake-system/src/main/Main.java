package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static ClinicCalender calender;
	@SuppressWarnings("unused")
	public static void main(String args) {
		calender = new ClinicCalender(LocalDate.now());
		Logger logger = LoggerFactory.getLogger(Main.class);
		Scanner sc = new Scanner(System.in);
		int ch;
		do {

			System.out.println("Enter your choice from the given options:\n");
			System.out.println("1.Add a patient appointment");
			System.out.println("2.View all appointments\n");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Enter the following details");

				System.out.println("Enter the patient name");
				String name = sc.nextLine();

				System.out.println("Enter the date of appointment in (M/d/yyyy h:m a)");
				String d = sc.nextLine();

				System.out.println("Name of doctor");
				String dname=sc.nextLine();

				String val=calender.addAppointment(name,d,dname);
				System.out.println(val);
				break;

			case 2:
				List<PatientAppointment> plist = calender.viewAllAppointment();
				for(PatientAppointment pa: plist) {
					System.out.println(pa);
				}
				break;

			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Enter the right choice");
			}
			sc.close();
		}while(ch!=3);
	}
}
