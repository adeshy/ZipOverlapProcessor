package com.zip.overlapprocessor.factory;

import org.fluttercode.datafactory.impl.DataFactory;

public class ZipDataSet {
	
  public String generateRandomZipcodeData(int noOfDataSets) {
	  
    DataFactory dataFactory = new DataFactory();
    String dataSet = "";
    for (int i = 0; i < noOfDataSets; i++) {
      int lower = dataFactory.getNumberBetween(10000, 99999);
      int upper = dataFactory.getNumberBetween(lower, 99999);
      dataSet += "[" + lower + "," + upper + "] ";
    }
    return dataSet;
    
  }

  public String generateOverlappingZipcodeData(int noOfDataSets) {
	  
    DataFactory dataFactory = new DataFactory();
    String dataSet = "";
    for (int i = 0; i < noOfDataSets; i++) {
      int lower = dataFactory.getNumberBetween(10000, 99999);
      int upper = dataFactory.getNumberBetween(lower, 99999);
      dataSet += "[" + (lower - 4) + "," + upper + "] ";
    }
    return dataSet;
    
  }
  
}
