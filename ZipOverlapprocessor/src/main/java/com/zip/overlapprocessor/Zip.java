package com.zip.overlapprocessor;

public class Zip {
	
  private int lower;
  private int upper;

  public Zip(int lower, int upper) {
    this.lower = lower;
    this.upper = upper;
  }

  public int getLower() {
    return lower;
  }

  public void setLower(int lower) {
    this.lower = lower;
  }

  public int getUpper() {
    return upper;
  }

  public void setUpper(int upper) {
    this.upper = upper;
  }
  public void print() {
	  System.out.println("[" + this.getLower() + "," + this.getUpper() + "]");
  }
}
