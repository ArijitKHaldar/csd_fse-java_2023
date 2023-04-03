package com.cts.exception;

public class InvalidTraineeoperationException  extends Exception{
	String str1;
	 public InvalidTraineeoperationException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("\nMyException Occurred: "+str1) ;
	   }
}
