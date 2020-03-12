package com.zip.overlapprocessor;

import java.util.List;

import com.zip.overlapprocessor.Zip;
import com.zip.overlapprocessor.ZipMerger;
import com.zip.overlapprocessor.ZipProcessor;
import com.zip.overlapprocessor.factory.ZipDataSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple Application.
 */
public class ApplicationTest extends TestCase {
  public ApplicationTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    TestSuite suite = new TestSuite(ApplicationTest.class);
    return suite;
  }

  public void testLoadedList() {
    ZipDataSet dataSet = new ZipDataSet();
    String inputDataSet = dataSet.generateRandomZipcodeData(100);
    ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
    List<Zip> zipcodeList = zipProcessor.stripZipcode();
    assertTrue(zipcodeList.size() > 0);
  }

  public void testfinalResultToMatch() {
    ZipDataSet dataSet = new ZipDataSet();
    String inputDataSet = dataSet.generateOverlappingZipcodeData(5);
    ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
    List<Zip> zipcodeList = zipProcessor.stripZipcode();
    ZipMerger zipcode_merger = new ZipMerger();
    List<Zip> sortedZipCodeList = zipcode_merger.sortByLowerBounds(zipcodeList);
    List<Zip> mergedZipcodeList = zipcode_merger.mergeZipcodes(sortedZipCodeList);
    assertTrue(mergedZipcodeList.size() == 1);
  }

  public void testIllegalArgumentException() {
    try {
      String inputDataSet = "[92004,92002] [92003,92004]";
      ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
      List<Zip> zipcodeList = zipProcessor.stripZipcode();
    } catch (IllegalArgumentException e) {
      assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
    }
  }

  public void testExceptionWhenMoreRanges() {
    try {
      String inputDataSet = "[92004,92002,92003] [92003,92004]";
      ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
      List<Zip> zipcodeList = zipProcessor.stripZipcode();
    } catch (IllegalArgumentException e) {
      assertEquals("IllegalArgumentException", e.getClass().getSimpleName());
    }
  }

  public void testExceptionMessageWhenLowerBoundGreater() {
    try {
      String inputDataSet = "[92004,92002] [92003,92004]";
      ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
      List<Zip> zipcodeList = zipProcessor.stripZipcode();
    } catch (IllegalArgumentException e) {
      String expectedMessage = "92004 92002:  Zip lower bound should be less" + " than upper bound";
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  public void testExceptionMessageWhenMoreRangeGiven() {
    try {
      String inputDataSet = "[92004,92002,92003] [92003,92004]";
      ZipProcessor zipProcessor = new ZipProcessor(inputDataSet);
      List<Zip> zipcodeList = zipProcessor.stripZipcode();
    } catch (IllegalArgumentException e) {
      String expectedMessage = "92004 Zip should have lower " + "and upper bounds";
      assertEquals(expectedMessage, e.getMessage());
    }
  }

}
