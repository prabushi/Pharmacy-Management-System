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
public class AdminPageTest {
    
    public AdminPageTest() {
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
     * Test of Set method, of class AdminPage.
     */
    @Test
    public void testSet() {
        System.out.println("Set");
        AdminPage instance = AdminPage.getInstance();
        instance.Set();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class AdminPage.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        AdminPage instance = AdminPage.getInstance();
        instance.edit();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of StockeditName method, of class AdminPage.
     */
    @Test
    public void testStockeditName() {
        System.out.println("StockeditName");
        AdminPage instance = AdminPage.getInstance();
        instance.StockeditName();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of StockeditCode method, of class AdminPage.
     */
    @Test
    public void testStockeditCode() {
        System.out.println("StockeditCode");
        AdminPage instance = AdminPage.getInstance();
        instance.StockeditCode();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of codeSearch method, of class AdminPage.
     */
    @Test
    public void testCodeSearch() {
        System.out.println("codeSearch");
        AdminPage instance = AdminPage.getInstance();
        instance.codeSearch();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStock method, of class AdminPage.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        AdminPage instance = AdminPage.getInstance();
        instance.getStock();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getShortExpiry method, of class AdminPage.
     */
    @Test
    public void testGetShortExpiry() {
        System.out.println("getShortExpiry");
        AdminPage instance = AdminPage.getInstance();
        instance.getShortExpiry();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDailyRevenue method, of class AdminPage.
     */
    @Test
    public void testGetDailyRevenue() {
        System.out.println("getDailyRevenue");
        AdminPage instance = AdminPage.getInstance();
        instance.getDailyRevenue();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getMonthlyRevenue method, of class AdminPage.
     */
    @Test
    public void testGetMonthlyRevenue() {
        System.out.println("getMonthlyRevenue");
        AdminPage instance = AdminPage.getInstance();
        instance.getMonthlyRevenue();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getExpiry method, of class AdminPage.
     */
    @Test
    public void testGetExpiry() {
        System.out.println("getExpiry");
        AdminPage instance = AdminPage.getInstance();
        instance.getExpiry();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getShortageList method, of class AdminPage.
     */
    @Test
    public void testGetShortageList() {
        System.out.println("getShortageList");
        AdminPage instance = AdminPage.getInstance();
        instance.getShortageList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class AdminPage.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AdminPage expResult = AdminPage.getInstance();
        AdminPage result = AdminPage.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}