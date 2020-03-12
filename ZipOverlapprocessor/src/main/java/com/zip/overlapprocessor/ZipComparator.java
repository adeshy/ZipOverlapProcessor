package com.zip.overlapprocessor;

import java.util.Comparator;

public class ZipComparator implements Comparator<Zip> {
  public int compare(Zip interval1, Zip interval2) {
    if (interval1.getLower() > interval2.getLower())
      return 1;
    else
      return -1;
  }
}
