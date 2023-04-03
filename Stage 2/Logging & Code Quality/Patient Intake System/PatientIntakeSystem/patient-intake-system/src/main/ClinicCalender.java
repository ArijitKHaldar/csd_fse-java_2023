package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ClinicCalender {
	private List<PatientAppointment> appList;
	private LocalDate today;

	public ClinicCalender(LocalDate today) {
	      this.today = today;
	      this.appList = new ArrayList<>();
	}

	public String addAppointment(String name,String d,String dname) {
		Doctor doc = Doctor.valueOf(dname.toLowerCase());
		LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(d, today);
	    PatientAppointment p = new PatientAppointment(name, localDateTime, doc);
		Boolean val = appList.add(p);
		if(val)
			return "Appointment booked!!";
		else
			return "Booking Failed!!";
	}

	public  List<PatientAppointment> viewAllAppointment(){
		return appList;
	}

}
