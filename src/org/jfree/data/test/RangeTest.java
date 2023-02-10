package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange1;
    private Range exampleRange2;
    private Range exampleRange3;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    	exampleRange1 = new Range(-1, 1);
    	exampleRange2 = new Range(0,100);
    	exampleRange3 = new Range(-50,-5);
    }
    
    //----------------------------------------------------------
    // Testing For the Contains Method
    //----------------------------------------------------------
    
    @Test
    public void containsValueShouldBeTrue() {
    	assertTrue("The Range of 0 to 100 should contain the number 43", 
    			exampleRange2.contains(43));
    }
    
    @Test
    public void containsValueOver() {
    	assertFalse("The Range of -1 to 1 should not contain the number 43", 
    			exampleRange1.contains(43));
    }

    @Test
    public void containsValueUnder() {
    	assertFalse("The Range of -1 to 1 should not contain the number -43", 
    			exampleRange1.contains(-43));
    }
    
    @Test 
    public void containsValueUpperEdge() {
    	assertTrue("The Range of 0 to 100 should contain the number 100 - Edge case",
    			exampleRange2.contains(100));
    }
    
    @Test 
    public void containsValueLowerEdge() {
    	assertTrue("The Range of 0 to 100 should contain the lower bound 0",
    			exampleRange2.contains(0));
    }
    
    @Test
    public void containsNegValue() {
    	assertFalse("The Range of 0 to 100 shoud not contain the number -12",
    			exampleRange2.contains(-12));
    }
    
    @Test
    public void containtsOnNegRange() {
    	assertTrue("The range of -50 to -5 should contain the value -17", 
    			exampleRange3.contains(-17));
    }
    
    //----------------------------------------------------------
    // Testing For the Upper Bound Method
    //----------------------------------------------------------
    
    @Test
    public void returnedUpperBoundShouldBeOneHundered() {
    	assertEquals("The Upper bound for the range of 0 to 100 should be 100", 
    			100, exampleRange2.getUpperBound(), .000000001d);
    }
    
    @Test
    public void returnedUpperBoundShouldNotBeSix() {
    	assertFalse("The Upper bound for the range of 0 to 100 should be 100 not 6", 
    			6 == exampleRange2.getUpperBound());
    }
    
    @Test
    public void checkUpperBoundOnNegRange() {
    	assertEquals("The upper bound for the range of -50 to -5 should be -5",
    			-5, exampleRange3.getUpperBound(), .000000001d);
    }
    
    //----------------------------------------------------------
    // Testing For the Lower Bound Method
    //----------------------------------------------------------
    
    @Test
    public void returneLowerBoundShouldBeZero() {
    	assertEquals("The Lower bound for the range of 0 to 100 should be 0", 
    			0, exampleRange2.getLowerBound(), .000000001d);
    }
    
    @Test
    public void returnedLowerBoundShouldNotBeNegativeTwelve() {
    	assertFalse("The Lower bound for the range of 0 to 100 should be 0 not -12", 
    			-12 == exampleRange2.getLowerBound());
    }
    
    @Test 
    public void checkLowerBoundOnNegRange() {
    	assertEquals("The lower bound for the range of -50 to -5 should be -50",
    			-50, exampleRange3.getLowerBound(), .000000001d);
    }

    
    //----------------------------------------------------------
    // Testing For the Get Length Method
    //----------------------------------------------------------
    
    @Test
    public void theLengthValueShouldBeTwo() {
    	assertEquals("The length of the range of -1 to 1 should be 2", 
    			2.0, exampleRange1.getLength(), .000000001d);
    }
    
    @Test 
    public void lengthValueShouldNotBeFour() {
    	assertFalse("The length of the range of 0 to 100 should be 100 not 4",
    			4.0 == exampleRange2.getLength());
    }
    
    @Test 
    public void negativeRangeLength() {
    	assertTrue("The length of the range of -50 to -5 should be 45",
    			exampleRange3.getLength() == 45.0);
    }

    
    //----------------------------------------------------------
    // Testing the intersect method
    //----------------------------------------------------------
    
    @Test
    public void theRangeShouldNotIntersect() {
    	assertFalse("The range from -1 to 1 should not intersect with a lower bound of 4", 
    			exampleRange1.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectFullOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with a lower bound of 4", 
    			exampleRange2.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectUpperOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with a lower bound of 97", 
    			exampleRange2.intersects(97, 130));
    }

    @Test
    public void theRangeShouldIntersectLowerOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with an upper bound of 4", 
    			exampleRange2.intersects(-20, 4));
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
