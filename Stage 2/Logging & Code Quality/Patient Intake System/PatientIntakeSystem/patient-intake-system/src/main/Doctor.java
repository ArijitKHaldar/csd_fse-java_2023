package main;

import lombok.Getter;
//import lombok.Setter;

@Getter
//@Setter
public enum Doctor {
	   john("T John", "MD"),
	   nikhil("V Nikhil", "MBBS,DM");

	   private String name;
	   private String qual;

	   Doctor(String name, String qual) {
	      this.name = name;
	      this.qual = qual;
	   }

	}
