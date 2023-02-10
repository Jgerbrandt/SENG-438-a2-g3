package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Range;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest{
	
	private Mockery mockingCont;
	private Values2D myValues;
	private KeyedValues myKeyedValues;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	
    	//Setup Mock for the Values2D test object
    	mockingCont = new Mockery();
    	myValues = mockingCont.mock(Values2D.class);
    	mockingCont.checking(new Expectations() {
	         {
	             one(myValues).getRowCount();
	             will(returnValue(3));
	             one(myValues).getColumnCount();
	             will(returnValue(2));
	             
	             one(myValues).getValue(0, 0);
	             will(returnValue(7.5));
	             one(myValues).getValue(1, 0);
	             will(returnValue(2.5));
	             one(myValues).getValue(2, 0);
	             will(returnValue(0.5));
	             one(myValues).getValue(0, 1);
	             will(returnValue(1.0));
	             one(myValues).getValue(1, 1);
	             will(returnValue(6.9));
	             one(myValues).getValue(2, 1);
	             will(returnValue(4.2));
	             
	             one(myValues).getValue((int)with(any(Integer.class)), (int)with(any(Integer.class)));
	             will(throwException(new IndexOutOfBoundsException("bad arg for get value")));
	         }  
	     });
    	
    	//Setup Mock for the KeyedValue test object
    	//create the list of keys that will be returned with the use of the function
    	List<Double> keyList = new ArrayList<>();
    	keyList.add((double) 9);
    	keyList.add((double) 4);
    	keyList.add((double) 13);

    	
    	mockingCont = new Mockery();
    	myKeyedValues = mockingCont.mock(KeyedValues.class);
    	mockingCont.checking(new Expectations() {
    		{
    			allowing(myKeyedValues).getItemCount();
    			will(returnValue(3));
    			
    			allowing(myKeyedValues).getIndex(5);
    			will(returnValue(0));
    			allowing(myKeyedValues).getKey(0);
    			will(returnValue(0));
    			allowing(myKeyedValues).getValue(0);
    			will(returnValue(5));
    			
    			allowing(myKeyedValues).getIndex(9);
    			will(returnValue(1));
    			allowing(myKeyedValues).getKey(1);
    			will(returnValue(1));
    			allowing(myKeyedValues).getValue(1);
    			will(returnValue(9));
    			
    			allowing(myKeyedValues).getIndex(2);
    			will(returnValue(2));
    			allowing(myKeyedValues).getKey(2);
    			will(returnValue(2));
    			allowing(myKeyedValues).getValue(2);
    			will(returnValue(2));
    			
    			allowing(myKeyedValues).getKeys();
    			will(returnValue(keyList));
    
    			allowing(myKeyedValues).getValue(with(any(Double.class)));
    			will(returnValue(null));
    		}
    	});
 
    	
    	//Setup the expected Result for the cumulative values
    	
    	
    }
	

    
    //----------------------------------------------------------
    // Testing the column total method (Given in MD)
    //----------------------------------------------------------
    
	 @Test //Pre-made Mocking Test
	 public void calculateColumnTotalForTwoValues() {
		Mockery mockingContext = new Mockery();
    	Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }  
	     });
		 double result = DataUtilities.calculateColumnTotal(values, 0);
		 assertEquals("the total of the values in the first "
		     		+ "(and only) column of the 2D table should be 10",
		    		 result, 10.0, .000000001d);
	 }
	 
	 @Test
	 public void calculateColumnTotalForThreeValuesAndTwoRows() {
		 double result = DataUtilities.calculateColumnTotal(myValues, 0);
	     assertEquals("the total of the values in the first "
	     		+ " column of the 2D table should be 10.5",
	    		 result, 10.5, .000000001d);
	 }
	 
	 @Test
	 public void tryToUseNegativeIndexForTheColumn() {
		 try {
			 DataUtilities.calculateColumnTotal(myValues, -1);
		   } catch(Exception e) {
		      fail("The function should return zero if the input INDEX is wrong"
		      		+ "not throw an exception but instead give the value of 0");
		   }
		 
//		 assertEquals("The function should return zero if the input is wrong",
//				 0, DataUtilities.calculateColumnTotal(myValues, -1), .000000001d);
	 }
	 
	 @Test
	 public void tryToUseLargerThanPossibleIndexForTheColumn() {
		 try {
			 DataUtilities.calculateColumnTotal(myValues, 12);
		   } catch(Exception e) {
		      fail("The function should return zero if the input INDEX is wrong"
		      		+ "not throw an exception but instead give the value of 0");
		   }
	 }
	 

    //----------------------------------------------------------
    // Testing the row total method
    //----------------------------------------------------------
	 
	 @Test
	 public void calculateRowTotalForTwoValues() {
		 double result = DataUtilities.calculateRowTotal(myValues, 1);
		 assertEquals("the total of the values in the second "
		     		+ "of 3 column of the 2D table should be 9.4",
		    		 9.4, result, .000000001d);
	 }
	 
	 @Test
	 public void tryToUseNegativeIndexForTheRowIndex() {
		 try {
			 DataUtilities.calculateRowTotal(myValues, -1);
		   } catch(Exception e) {
		      fail("The function should return zero if the input INDEX is wrong"
		      		+ "not throw an exception but instead give the value of 0");
		   }
	 }
	 
	 @Test
	 public void tryToUseLargerThanPossibleIndexForTheRow() {
		 try {
			 DataUtilities.calculateColumnTotal(myValues, 12);
		   } catch(Exception e) {
		      fail("The function should return zero if the input INDEX is wrong"
		      		+ "not throw an exception but instead give the value of 0");
		   }
	 }

	 
    //----------------------------------------------------------
    // Testing Create Number Array method
    //----------------------------------------------------------
	 
	 @Test
	 public void createNumberArrayFromDoubleArray() {
		 
		 double[] dblArr = new double[] {12.0, 10.0, 3.0};
		 
		 Number[] expNmbrArr = new Number[] {12.0, 10.0, 3.0};
		 
		 //Error: create number array seemingly drops the last value and returns null for it
		 Number[] actNmbrArr = DataUtilities.createNumberArray(dblArr);
		 
		 assertArrayEquals("The array created by the createNumberArray should be"
		 		+ "the same as the expect array",expNmbrArr, actNmbrArr);
	 }
	 
	 @Test
	 public void createNumberArrayWithEmptyArray() {
		 
		 double[] dblArr = new double[] {};
		 
		 Number[] expNmbrArr = new Number[] {};
		
		 Number[] actNmbrArr = DataUtilities.createNumberArray(dblArr);
		 
		 assertArrayEquals("The array created by the createNumberArray should be"
		 		+ "an empty Number array",expNmbrArr, actNmbrArr);
	 }
	 
	 @Test
	 public void checkThatNoExceptionIsThrownForArrayWithZeroWithCreateNumberArray() {
		 
		 try {
			 double[] testArr = new double[] {11,0};
			 DataUtilities.createNumberArray(testArr);
		   } catch(InvalidParameterException e) {
		      fail("Should not have thrown any exception for array with 0 value");
		   }
	 }
	 
	 @Test
	 public void checkThatNoExceptionIsThrownForArrayCreatedWithNegativeNum() {
		 
		 try {
			 double[] testArr = new double[] {-11,-4.0};
			 DataUtilities.createNumberArray(testArr);
		   } catch(InvalidParameterException e) {
		      fail("Should not have thrown any exception for array with negative numbers");
		   }
	 }
	 
	 
    //----------------------------------------------------------
    // Testing Create 2D Number Array Method
    //----------------------------------------------------------
	 
	 @Test
	 public void createNumber2DFromDoubleMatrix() {
		 double[][] dblMat = new double[][] {{12.0, 10.0, 3.0},{01,3.23,12}};
		 
		 Number[][] expNmbrMat = new Number[][] {{12.0, 10.0, 3.0},{01,3.23,12}};
		 
		 //Error: create number matrix seemingly drops the last value of the first 
		 //array and returns null for it instead
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void createNumberArray2DWithEmptyMainArray() {
		 
		 double[][] dblMat = new double[][] {};
		 
		 Number[][] expNmbrMat = new Number[][] {};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void createNumberArray2DWithEmptySubArray() {
		 
		 double[][] dblMat = new double[][] {{}};
		 
		 Number[][] expNmbrMat = new Number[][] {{}};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void createNumberArray2DWithDifferentlySizedSubArrays() {
		 
		 double[][] dblMat = new double[][] {{1},{1,2}};
		 
		 Number[][] expNmbrMat = new Number[][] {{1},{1,2}};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void checkThatNoExceptionIsThrownForArrayWithZeroWithCreateNumberArray2D() {
		 
		 try {
			 double[][] dblMat = new double[][] {{0},{1,2}};
			 DataUtilities.createNumberArray2D(dblMat);
		   } catch(InvalidParameterException e) {
		      fail("Should not have thrown any exception for array with 0 value");
		   }
	 }
	 
	 @Test
	 public void checkThatNoExceptionIsThrownForArrayCreatedWithNegativeNumCreateNumberArray2D() {
		 try {
			 double[][] dblMat = new double[][] {{-12},{-2}};
			 DataUtilities.createNumberArray2D(dblMat);
		   } catch(InvalidParameterException e) {
		      fail("Should not have thrown any exception for array with NEGATIVE numbers");
		   }
	 }
	 
	 
    //----------------------------------------------------------
    // Testing the Cumulative Percentages method
    //----------------------------------------------------------
	 
	 @Test
	 public void checkThatNullArgThrowsException(){
		 try {
			 KeyedValues nullObj = null;
			 DataUtilities.getCumulativePercentages(nullObj);
		   } catch(InvalidParameterException e) {
		      
		   } catch(Exception e) {
			   fail("Did not throw the correct exception when a null value was passed");
		   }
	 }
	 
	 @Test //This test is very weird idk about it
	 public void checkThatPercentageIsCalculatedCorrectlyForFirstValue() {
		 assertEquals("The Values from the cumulative percentage function should be",
				 0.3125 , DataUtilities.getCumulativePercentages(myKeyedValues).getValue(0));
	 }
	 
	 @Test //This test is very weird idk about it
	 public void checkThatPercentageIsCalculatedCorrectlyForSecondValue() {
		 assertEquals("The Values from the cumulative percentage function should be",
				 0.875 , DataUtilities.getCumulativePercentages(myKeyedValues).getValue(1));
	 }
	 @Test //This test is very weird idk about it
	 public void checkThatPercentageIsCalculatedCorrectlyForThirdValue() {
		 assertEquals("The Values from the cumulative percentage function should be",
				 1 , DataUtilities.getCumulativePercentages(myKeyedValues).getValue(2));
	 }
	 
	 
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
