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


    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange1.getCentralValue(), .000000001d);
    }
    
    @Test
    public void containsValueShouldBeTrue() {
    	assertTrue("The Range of 0 to 100 should contain the number 43", 
    			exampleRange2.contains(43));
    }
    
    @Test
    public void returnedUpperBoundShouldBeOneHundered() {
    	assertEquals("The Upper bound for the range of 0 to 100 should be 100", 
    			100, exampleRange2.getUpperBound(), .000000001d);
    }
    
    @Test
    public void returneLowerBoundShouldBeZero() {
    	assertEquals("The Lower bound for the range of 0 to 100 should be 0", 
    			0, exampleRange2.getLowerBound(), .000000001d);
    }
    
    @Test
    public void theStringVersionShouldBeCorrect() {
    	String expected = new String("Range0.0,100.0]");
    	assertEquals("The string version of the range of 0 to 100 shoudl be Range[0,100]", 
    			expected, exampleRange2.toString());
    }
    
    @Test
    public void theRangeShouldNotIntersect() {
    	assertFalse("The range from -1 to 1 should not interect with a lower bound of 4", 
    			exampleRange1.intersects(4, 14));
    }
    

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
