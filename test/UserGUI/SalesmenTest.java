/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGUI;

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
public class SalesmenTest {
    
    public SalesmenTest() {
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
     * Test of getInstance method, of class Salesmen.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Salesmen expResult = Salesmen.getInstance();
        Salesmen result = Salesmen.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}