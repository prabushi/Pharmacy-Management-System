/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperUserGUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DELL
 */
public class MonthlyProfitTest {
    
    public MonthlyProfitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class MonthlyProfit.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MonthlyProfit expResult = MonthlyProfit.getInstance();
        MonthlyProfit result = MonthlyProfit.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}