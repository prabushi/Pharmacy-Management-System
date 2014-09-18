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
public class DailyProfitTest {
    
    public DailyProfitTest() {
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
     * Test of getInstance method, of class DailyProfit.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DailyProfit expResult = DailyProfit.getInstance();
        DailyProfit result = DailyProfit.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}