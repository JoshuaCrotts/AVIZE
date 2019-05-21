/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uncg.save.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Joshua
 */
public class TitleAndMenuBarControllerTest {
    
    public TitleAndMenuBarControllerTest()
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
     * Test of initialize method, of class TitleAndMenuBarController.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testInitialize()
    {
        System.out.println( "initialize" );
        URL url = null;
        ResourceBundle rb = null;
        TitleAndMenuBarController instance = new TitleAndMenuBarController();
        instance.initialize( url, rb );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }

    /**
     * Test of setParentController method, of class TitleAndMenuBarController.
     */
    @Ignore("Test is ignored as a demonstration")
    @org.junit.Test
    public void testSetParentController()
    {
        System.out.println( "setParentController" );
        RootPaneController control = null;
        TitleAndMenuBarController instance = new TitleAndMenuBarController();
        instance.setParentController( control );
        // TODO review the generated test code and remove the default call to fail.
        fail( "The test case is a prototype." );
    }
    
}
