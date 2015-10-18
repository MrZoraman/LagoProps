package com.lagopusempire.lagoprops.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ResourceExporter
{
    private final Class c;
    
    public ResourceExporter(Class c)
    {
        this.c = c;
    }
    
    public void exportResource(String resource) throws IOException
    {
        File file = new File(resource);
        try (InputStream inputStream = getResourceStream(resource); OutputStream outputStream = new FileOutputStream(file))
        {
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = inputStream.read(buffer)) >= 0)
            {
                outputStream.write(buffer, 0, byteCount);
            }
        }
    }
    
    public InputStream getResourceStream(String resource) throws IOException
    {
        final URL resourceUrl = c.getClassLoader().getResource(resource);
        if(resourceUrl == null)
        {
            throw new ResourceNotFoundException("Failed to find resource " + resource + "!");
        }
        return resourceUrl.openStream();
    }
}
