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
public class ShortageTest {
    
    public ShortageTest() {
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
     * Test of getName method, of class Shortage.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Shortage instance = new Shortage("abc",5,6,3);
        String expResult = "abc";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEachHas method, of class Shortage.
     */
    @Test
    public void testGetEachHas() {
        System.out.println("getEachHas");
        Shortage instance = new Shortage("abc",5,6,3);
        int expResult = 5;
        int result = instance.getEachHas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Shortage.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "abc";
        Shortage instance = new Shortage("abc",5,6,3);
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setEachHas method, of class Shortage.
     */
    @Test
    public void testSetEachHas() {
        System.out.println("setEachHas");
        int eachHas = 5;
        Shortage instance = new Shortage("abc",5,6,3);
        instance.setEachHas(eachHas);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAvailable method, of class Shortage.
     */
    @Test
    public void testSetAvailable() {
        System.out.println("setAvailable");
        int available = 0;
        Shortage instance = new Shortage("abc",5,6,3);
        instance.setAvailable(available);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setToBuy method, of class Shortage.
     */
    @Test
    public void testSetToBuy() {
        System.out.println("setToBuy");
        int toBuy = 3;
        Shortage instance = new Shortage("abc",5,6,3);
        instance.setToBuy(toBuy);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailable method, of class Shortage.
     */
    @Test
    public void testGetAvailable() {
        System.out.println("getAvailable");
        Shortage instance = new Shortage("abc",5,6,3);
        int expResult = 6;
        int result = instance.getAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getToBuy method, of class Shortage.
     */
    @Test
    public void testGetToBuy() {
        System.out.println("getToBuy");
        Shortage instance = new Shortage("abc",5,6,3);
        int expResult = 3;
        int result = instance.getToBuy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}