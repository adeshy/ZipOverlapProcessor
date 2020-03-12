package com.zip.overlapprocessor;

import java.util.List;
import java.util.Scanner;

public class Application {
  private static Scanner scan;

  public static void main(String[] args) {
	  
    scan = new Scanner(System.in);
    String zipRanges = scan.nextLine();
    
    ZipProcessor zipProcessor = new ZipProcessor(zipRanges);
    
    List<Zip> zipList = zipProcessor.stripZipcode();
    ZipMerger zipmerger = new ZipMerger();
    
    List<Zip> sortedZipList = zipmerger.sortByLowerBounds(zipList);
    List<Zip> mergedZipList = zipmerger.mergeZipcodes(sortedZipList);
    
    for (Zip zip : mergedZipList) 
    		zip.print();
 
  }
}
