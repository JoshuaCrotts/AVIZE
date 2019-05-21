/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uncg.save.models;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Joshua
 */
public class ArgumentModelTest {
    
    public ArgumentModelTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of hasCQ method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testHasCQ()
    {
        System.out.println( "hasCQ" );
        ArgumentModel instance = new ArgumentModel();
        boolean expResult = false;
        boolean result = instance.hasCQ();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setCQ method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testSetCQ()
    {
        System.out.println( "setCQ" );
        boolean value = false;
        ArgumentModel instance = new ArgumentModel();
        instance.setCQ( value );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getTitle method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetTitle()
    {
        System.out.println( "getTitle" );
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of containsPremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testContainsPremise()
    {
        System.out.println( "containsPremise" );
        int position = 0;
        ArgumentModel instance = new ArgumentModel();
        boolean expResult = false;
        boolean result = instance.containsPremise( position );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getPremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetPremise()
    {
        System.out.println( "getPremise" );
        int position = 0;
        ArgumentModel instance = new ArgumentModel();
        PremiseModel expResult = null;
        PremiseModel result = instance.getPremise( position );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getCriticalQuestion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetCriticalQuestion()
    {
        System.out.println( "getCriticalQuestion" );
        int key = 0;
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getCriticalQuestion( key );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getConclusion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetConclusion()
    {
        System.out.println( "getConclusion" );
        ArgumentModel instance = new ArgumentModel();
        PremiseModel expResult = null;
        PremiseModel result = instance.getConclusion();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setConclusion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testSetConclusion()
    {
        System.out.println( "setConclusion" );
        PropositionModel conclusion = null;
        ArgumentModel instance = new ArgumentModel();
        instance.setConclusion( conclusion );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of removeConclusion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testRemoveConclusion()
    {
        System.out.println( "removeConclusion" );
        ArgumentModel instance = new ArgumentModel();
        instance.removeConclusion();
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of addPremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testAddPremise()
    {
        System.out.println( "addPremise" );
        PropositionModel prop = null;
        int position = 0;
        ArgumentModel instance = new ArgumentModel();
        instance.addPremise( prop, position );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of removePremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testRemovePremise_int()
    {
        System.out.println( "removePremise" );
        int position = 0;
        ArgumentModel instance = new ArgumentModel();
        instance.removePremise( position );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of removePremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testRemovePremise_PropositionModel()
    {
        System.out.println( "removePremise" );
        PropositionModel prop = null;
        ArgumentModel instance = new ArgumentModel();
        instance.removePremise( prop );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getPatchNumCQs method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetPatchNumCQs()
    {
        System.out.println( "getPatchNumCQs" );
        ArgumentModel instance = new ArgumentModel();
        int expResult = 0;
        int result = instance.getPatchNumCQs();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getPatchCriticalQuestion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetPatchCriticalQuestion()
    {
        System.out.println( "getPatchCriticalQuestion" );
        int i = 0;
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getPatchCriticalQuestion( i );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getSchemeConclusion method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetSchemeConclusion()
    {
        System.out.println( "getSchemeConclusion" );
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getSchemeConclusion();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getSchemePremise method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetSchemePremise()
    {
        System.out.println( "getSchemePremise" );
        int premiseNumber = 0;
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getSchemePremise( premiseNumber );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getSchemeNumPremises method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetSchemeNumPremises()
    {
        System.out.println( "getSchemeNumPremises" );
        ArgumentModel instance = new ArgumentModel();
        int expResult = 0;
        int result = instance.getSchemeNumPremises();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of getSchemeTitle method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testGetSchemeTitle()
    {
        System.out.println( "getSchemeTitle" );
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.getSchemeTitle();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of addCQArgument method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testAddCQArgument()
    {
        System.out.println( "addCQArgument" );
        ArgumentModel arg = null;
        ArgumentModel instance = new ArgumentModel();
        instance.addCQArgument( arg );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of removeCQArgument method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testRemoveCQArgument()
    {
        System.out.println( "removeCQArgument" );
        ArgumentModel arg = null;
        ArgumentModel instance = new ArgumentModel();
        instance.removeCQArgument( arg );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toArray method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testToArray()
    {
        System.out.println( "toArray" );
        ArgumentModel instance = new ArgumentModel();
        String[] expResult = null;
        String[] result = instance.toArray();
        assertArrayEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of toString method, of class ArgumentModel.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testToString()
    {
        System.out.println( "toString" );
        ArgumentModel instance = new ArgumentModel();
        String expResult = "";
        String result = instance.toString();
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }
    
}
