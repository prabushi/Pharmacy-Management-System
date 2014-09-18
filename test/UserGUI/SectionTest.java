/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserGUI;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SectionTest {

    public SectionTest() {
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
     * Test of getConsumed method, of class Section.
     */
    @Test
    public void testGetConsumed() {
        System.out.println("getConsumed");
        String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
            int expResult = 0;
            int result = instance.getConsumed();
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            // fail("The test case is a prototype.");
        } catch (Exception e) {
        }
    }

    /**
     * Test of setConsumed method, of class Section.
     */
    @Test
    public void testSetConsumed() {
        System.out.println("setConsumed");
        int consumed = 5;
        String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
            instance.setConsumed(consumed);
            // TODO review the generated test code and remove the default call to fail.
            // fail("The test case is a prototype.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Test of setId method, of class Section.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
            instance.setId(id);
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Test of setQuantity method, of class Section.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 4;
        String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
            instance.setQuantity(quantity);
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Test of setExpiry method, of class Section.
     */
    @Test
    public void testSetExpiry() {
        System.out.println("setExpiry");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String d = "2014-09-01";
        try {
            Date one = formatter.parse(d);
            Date expiry = one;
            Section instance = new Section(1, 4, one, 5);
            instance.setExpiry(expiry);
            // TODO review the generated test code and remove the default call to fail.
            //fail("The test case is a prototype.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Test of getId method, of class Section.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
         String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     //   fail("The test case is a prototype.");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Test of getQuantity method, of class Section.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
         String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
        int expResult = 4;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Test of getExpiry method, of class Section.
     */
    @Test
    public void testGetExpiry() {
        System.out.println("getExpiry");
         String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date one = formatter.parse(d);
            Section instance = new Section(1, 4, one, 5);
        Date expResult = one;
        Date result = instance.getExpiry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        }catch(Exception e){
           System.out.println(e);
        }
    }

    /**
     * Test of compareTo method, of class Section.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        String d = "2014-09-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        try {
            one = formatter.parse(d);
            Section o = new Section(1, 5, one, 6);
            Section instance = o;
            int expResult = 0;
            int result = instance.compareTo(o);
            assertEquals(expResult, result);
        } catch (Exception e) {
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}