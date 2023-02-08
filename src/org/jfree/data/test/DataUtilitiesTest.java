package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
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
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
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
	             
	         }  
	     });
	     
    }
	
    
	 
    //----------------------------------------------------------
    // Testing the column total method (Given in MD)
    //----------------------------------------------------------
    
	@Test
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
	 
	 
	 
	 
	 
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
