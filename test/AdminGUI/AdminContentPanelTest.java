/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminGUI;

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
public class AdminContentPanelTest {
    
    public AdminContentPanelTest() {
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
     * Test of getInstance method, of class AdminContentPanel.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AdminContentPanel expResult = AdminContentPanel.getInstance();
        AdminContentPanel result = AdminContentPanel.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}