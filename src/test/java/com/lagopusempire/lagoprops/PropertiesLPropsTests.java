package com.lagopusempire.lagoprops;

import com.lagopusempire.lagoprops.impls.PropertiesLProps;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesLPropsTests
{
    @Test
    public void testRead()
    {
        String input = "test=a\n"
                + "test2=3\n"
                + "test3=2.5\n";
        InputStream stream = TestUtils.stringToInputStream(input);
        
        LagoProps props = null;
        try
        {
            props = new PropertiesLProps(stream);
        }
        catch (IOException ex)
        {
            fail();
            return;
        }
        
        Prop<String> p1 = props.getString("test");
        assertEquals("a", p1.get());
        
        Prop<Integer> p2 = props.getInt("test2");
        assertEquals(3, (int) p2.get());
        
        Prop<Double> p3 = props.getDouble("test3");
        assertEquals(2.5, (double) p3.get(), 0.00001);
    }
}
