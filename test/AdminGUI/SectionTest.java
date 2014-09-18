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
     * Test of getEachHas method, of class Section.
     */
    @Test
    public void testGetEachHas() {
        System.out.println("getEachHas");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        int expResult = 2;
        int result = instance.getEachHas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setSectionId method, of class Section.
     */
    @Test
    public void testSetSectionId() {
        System.out.println("setSectionId");
        int sectionId = 15;
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setSectionId(sectionId);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSectionId method, of class Section.
     */
    @Test
    public void testGetSectionId() {
        System.out.println("getSectionId");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        int expResult = 15;
        int result = instance.getSectionId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setExpiry method, of class Section.
     */
    @Test
    public void testSetExpiry() {
        System.out.println("setExpiry");
        String expiry = "2014-05-01";
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setExpiry(expiry);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCode method, of class Section.
     */
    @Test
    public void testSetCode() {
        System.out.println("setCode");
        int code = 1;
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setCode(code);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantity method, of class Section.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 2;
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setQuantity(quantity);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setBatchNo method, of class Section.
     */
    @Test
    public void testSetBatchNo() {
        System.out.println("setBatchNo");
        String batchNo = "3n";
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setBatchNo(batchNo);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAvailable method, of class Section.
     */
    @Test
    public void testSetAvailable() {
        System.out.println("setAvailable");
        int available = 5;
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setAvailable(available);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Section.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "panadol";
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getExpiry method, of class Section.
     */
    @Test
    public void testGetExpiry() {
        System.out.println("getExpiry");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        String expResult = "2014-05-01";
        String result = instance.getExpiry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCode method, of class Section.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        int expResult = 1;
        int result = instance.getCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setEachHas method, of class Section.
     */
    @Test
    public void testSetEachHas() {
        System.out.println("setEachHas");
        int eachHas = 2;
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setEachHas(eachHas);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setCompany method, of class Section.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "panadol";
        Section instance =new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        instance.setCompany(company);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getCompany method, of class Section.
     */
//    @Test
//    public void testGetCompany() {
//        System.out.println("getCompany");
//        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
//        String expResult = "panadol";
//        String result = instance.getCompany();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//       // fail("The test case is a prototype.");
//    }

    /**
     * Test of getQuantity method, of class Section.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        int expResult = 2;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getBatchNo method, of class Section.
     */
    @Test
    public void testGetBatchNo() {
        System.out.println("getBatchNo");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        String expResult = "3n";
        String result = instance.getBatchNo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailable method, of class Section.
     */
    @Test
    public void testGetAvailable() {
        System.out.println("getAvailable");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        int expResult = 5;
        int result = instance.getAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Section.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Section instance = new Section("2014-05-01",1,2,"3n",5,"panadol",15,2);
        String expResult = "panadol";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}