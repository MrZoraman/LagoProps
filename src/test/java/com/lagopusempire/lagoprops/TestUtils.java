package com.lagopusempire.lagoprops;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestUtils
{
    public static InputStream stringToInputStream(String str)
    {
        return new ByteArrayInputStream(str.getBytes());
    }
}
