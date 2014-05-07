package com.mxinteligente.infra;

public class Timer20 implements Runnable {

	  public static void main(String[] args) {
		  Timer20 t = new Timer20();
	    try {
	      new Thread(t).start();
	    } catch (RuntimeException e) {
	      System.out.println("** RuntimeException from main");
	    }

	    System.out.println("Main stoped");

	  }

	  @Override
	  public void run() {
	    try {
	      while (true) {
	        System.out.println("** Started");

	        Thread.sleep(2000);

	        throw new RuntimeException("exception from thread");
	      }
	    } catch (RuntimeException e) {
	      System.out.println("** RuntimeException from thread");
	      throw e;
	    } catch (InterruptedException e) {

	    }
	  }
	}