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
public class ItemTest {
    
    public ItemTest() {
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
     * Test of setUserId method, of class Item.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        int userId = 1;
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        instance.setUserId(userId);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Item.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "prabushi";
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
//        /fail("The test case is a prototype.");
    }

    /**
     * Test of setPrivilege method, of class Item.
     */
    @Test
    public void testSetPrivilege() {
        System.out.println("setPrivilege");
        String privilege = "user";
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        instance.setPrivilege(privilege);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class Item.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "2014-10-05";
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setLogout method, of class Item.
     */
    @Test
    public void testSetLogout() {
        System.out.println("setLogout");
        String logout = "2014-14-10";
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        instance.setLogout(logout);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class Item.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        int expResult = 1;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Item.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        String expResult = "prabushi";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPrivilege method, of class Item.
     */
    @Test
    public void testGetPrivilege() {
        System.out.println("getPrivilege");
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        String expResult = "user";
        String result = instance.getPrivilege();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class Item.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        String expResult = "2014-10-05";
        String result = instance.getLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLogout method, of class Item.
     */
    @Test
    public void testGetLogout() {
        System.out.println("getLogout");
        Item instance = new Item(1,"prabushi","user","2014-10-05","2014-14-10");
        String expResult = "2014-14-10";
        String result = instance.getLogout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}