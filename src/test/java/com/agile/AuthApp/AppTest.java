package com.agile.authapp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //Sample to test mock
        App app = mock(App.class);

        when(app.anotherMessage()).thenReturn(true);
        when(app.ShowMsg()).thenCallRealMethod();

        boolean actual = app.ShowMsg();

        assertTrue(actual);
        verify(app, times(1)).anotherMessage();
    }

    public void testAppMock() throws IOException
    {
        //Sample to test out.print
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        App.ShowMessage();

        outputStream.flush();

        String message = new String(outputStream.toByteArray());

        assertTrue(message.contains("test"));        
    }
}
