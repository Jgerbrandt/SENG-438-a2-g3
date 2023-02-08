package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange1;
    private Range exampleRange2;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
    	exampleRange1 = new Range(-1, 1);
    	exampleRange2 = new Range(0,100);
    }



//    @Test
//    public void centralValueShouldBeZero() {
//        assertEquals("The central value of -1 and 1 should be 0",
//        0, exampleRange1.getCentralValue(), .000000001d);
//    }
    
    
    //----------------------------------------------------------
    // Testing For the Contains Method
    //----------------------------------------------------------
    
    @Test
    public void containsValueShouldBeTrue() {
    	assertTrue("The Range of 0 to 100 should contain the number 43", 
    			exampleRange2.contains(43));
    }
    
    @Test
    public void containsValueShouldBeFalse() {
    	assertFalse("The Range of -1 to 1 should not contain the number 43", 
    			exampleRange1.contains(43));
    }
    
    
    //----------------------------------------------------------
    // Testing For the Upper Bound Method
    //----------------------------------------------------------
    
    @Test
    public void returnedUpperBoundShouldBeOneHundered() {
    	assertEquals("The Upper bound for the range of 0 to 100 should be 100", 
    			100, exampleRange2.getUpperBound(), .000000001d);
    }
    
    
    //----------------------------------------------------------
    // Testing For the Lower Bound Method
    //----------------------------------------------------------
    
    
    @Test
    public void returneLowerBoundShouldBeZero() {
    	assertEquals("The Lower bound for the range of 0 to 100 should be 0", 
    			0, exampleRange2.getLowerBound(), .000000001d);
    }

    
    //----------------------------------------------------------
    // Testing For the Get Length Method
    //----------------------------------------------------------
    
    @Test
    public void TheLengthValueShouldBeThree() {
    	assertEquals("The length of the range of -1 to 1 should be 2", 
    			2.0, exampleRange1.getLength(), .000000001d);
    }

    
    //----------------------------------------------------------
    // Testing the intersect method
    //----------------------------------------------------------
    
    @Test
    public void theRangeShouldNotIntersect() {
    	assertFalse("The range from -1 to 1 should not interect with a lower bound of 4", 
    			exampleRange1.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectFullOverlap() {
    	assertTrue("The range from 0 to 100 should interect with a lower bound of 4", 
    			exampleRange2.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectPartOverlap() {
    	assertTrue("The range from 0 to 100 should interect with a lower bound of 4", 
    			exampleRange2.intersects(97, 130));
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
