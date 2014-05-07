package com.mxinteligente.tests;

import java.util.Calendar;
import java.util.Date;
 
 
public class TimeMilisecond {
  public static void main(String[] argv) {
 
      long lDateTime = new Date().getTime();
      System.out.println("Date() - Time in milliseconds: " + lDateTime);
 
      Calendar lCDateTime = Calendar.getInstance();
      System.out.println("Calender - Time in milliseconds :" + lCDateTime.getTimeInMillis());
 
  }
 
}