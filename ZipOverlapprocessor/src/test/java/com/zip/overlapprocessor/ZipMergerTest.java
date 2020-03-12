package com.zip.overlapprocessor;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import com.zip.overlapprocessor.Zip;
import com.zip.overlapprocessor.ZipMerger;
import com.zip.overlapprocessor.ZipProcessor;
import com.zip.overlapprocessor.factory.ZipDataSet;

import junit.framework.TestCase;

public class ZipMergerTest extends TestCase {

  @Test
  public void testMergeZipcodes() {
    ZipDataSet dataSet = new ZipDataSet();
    String zipcodeRanges = dataSet.generateRandomZipcodeData(100);
    ZipProcessor zipProcessor = new ZipProcessor(zipcodeRanges);
    List<Zip> zipcodeList = zipProcessor.stripZipcode();
    ZipMerger zipcode_merger = new ZipMerger();
    List<Zip> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
    List<Zip> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
    assertTrue(mergedZipcodeList.size() > 0);
  }

  public void testOverlappingRangeToReturnOneRange() {
    Zip zipcode1 = new Zip(95000, 95005);
    Zip zipcode2 = new Zip(95002, 95006);
    List<Zip> zipcodeList = new LinkedList<Zip>();
    zipcodeList.add(zipcode1);
    zipcodeList.add(zipcode2);
    ZipMerger zipcode_merger = new ZipMerger();
    List<Zip> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
    List<Zip> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
    assertTrue(mergedZipcodeList.get(0).getUpper() == 95006);
  }

  public void testSortingBeforeMerging() {
    Zip zipcode1 = new Zip(95000, 95005);
    Zip zipcode2 = new Zip(95002, 95006);
    List<Zip> zipcodeList = new LinkedList<Zip>();
    zipcodeList.add(zipcode2);
    zipcodeList.add(zipcode1);
    ZipMerger zipcode_merger = new ZipMerger();
    List<Zip> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
    assertTrue(sortedZipCodeList.get(0) == zipcode1);
  }

  public void testLoadAfterCallingMerge() {
    Zip zipcode1 = new Zip(95000, 95005);
    Zip zipcode2 = new Zip(95007, 95008);
    List<Zip> zipcodeList = new LinkedList<Zip>();
    zipcodeList.add(zipcode1);
    zipcodeList.add(zipcode2);
    ZipMerger zipcode_merger = new ZipMerger();
    List<Zip> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
    List<Zip> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
    assertEquals(mergedZipcodeList, zipcodeList);
  }

}
