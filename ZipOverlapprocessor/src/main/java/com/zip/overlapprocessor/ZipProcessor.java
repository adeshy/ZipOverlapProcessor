package com.zip.overlapprocessor;

import java.util.LinkedList;
import java.util.List;

public class ZipProcessor {
	
  private String zipRanges;

  public ZipProcessor(String zipRanges) {
    this.zipRanges = zipRanges;
  }

  public List<Zip> stripZipcode() {
    String[] zipcodeIntervals = zipRanges.split(" ");
    return loadZipcode(zipcodeIntervals);
  }

  public int stringToInt(String zipcode) {
    return Integer.parseInt(zipcode);
  }

  public boolean checkZipLength(int zipcode) {
    if ((int) (Math.log10(zipcode) + 1) != 5)
      return false;
    return true;
  }

  public boolean compareZipcodeRange(int lowerBound, int upperBound) {
    if (lowerBound > upperBound)
      return false;
    return true;
  }

  public boolean validateZipRange(int lowerBound, int upperBound) {
    if (!checkZipLength(lowerBound) && !checkZipLength(upperBound))
      throw new IllegalArgumentException(lowerBound + " " + upperBound + ": " + "Zip should have 5 digits");
    if (compareZipcodeRange(lowerBound, upperBound) == false)
      throw new IllegalArgumentException(
          lowerBound + " " + upperBound + ":  " + "Zip lower bound should be less than upper bound");
    return true;
  }

  public Zip validateAndAdd(String[] zipRange) {
    if (zipRange.length != 2)
      throw new IllegalArgumentException(zipRange[0] + " Zip should have lower and upper bounds");
    int lowerBound = stringToInt(zipRange[0]);
    int upperBound = stringToInt(zipRange[1]);
    Zip zip = null;
    if (validateZipRange(lowerBound, upperBound) == true)
      zip = new Zip(lowerBound, upperBound);
    return zip;
  }

  public Zip getZipRange(String zipcodeRange) {
    return validateAndAdd(zipcodeRange.replaceAll("\\[|\\]", "").split(","));
  }

  public List<Zip> loadZipcode(String[] zipcodeRange) {
    List<Zip> zipcodesList = new LinkedList<Zip>();
    for (int i = 0; i < zipcodeRange.length; i++) {
      zipcodesList.add(getZipRange(zipcodeRange[i]));
    }
    return zipcodesList;
  }
}
