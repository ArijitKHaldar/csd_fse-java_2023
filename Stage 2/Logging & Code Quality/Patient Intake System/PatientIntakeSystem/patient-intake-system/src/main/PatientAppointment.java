package main;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PatientAppointment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String name;
	private LocalDateTime dte;
	private Doctor doc;

	public PatientAppointment(String name, LocalDateTime localDateTime, Doctor doc) {
		super();
		this.name = name;
		this.dte = localDateTime;
		this.doc = doc;
	}

}
