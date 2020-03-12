package com.zip.overlapprocessor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ZipMerger {
  public List<Zip> sortByLowerBounds(List<Zip> zipRangeList) {
    Collections.sort(zipRangeList, new ZipComparator());
    return zipRangeList;
  }

  public List<Zip> mergeZipcodes(List<Zip> sortedZipList) {
	  
    List<Zip> mergedZipList = new LinkedList<Zip>();
    Zip zip = null;
    
    for (Zip zipInterval : sortedZipList) {
      if (zip == null)
        zip = zipInterval;
      else {
        if (zip.getUpper() >= zipInterval.getLower()) {
          zip.setUpper(Math.max(zip.getUpper(), zipInterval.getUpper()));
        } else {
        	mergedZipList.add(zip);
          zip = zipInterval;
        }
      }
    }
    mergedZipList.add(zip);
    return mergedZipList;
  }

}
