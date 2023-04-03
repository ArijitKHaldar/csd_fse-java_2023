package com.cts.exception;

public class InvalidTraineeDataException extends Exception{
	 String str1;
	 public InvalidTraineeDataException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("\nMyException Occurred: "+str1) ;
	   }

}
