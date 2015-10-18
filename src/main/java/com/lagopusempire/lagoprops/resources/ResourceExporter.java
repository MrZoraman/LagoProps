package com.lagopusempire.lagoprops.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ResourceExporter
{
    public void exportResource(String resource) throws IOException
    {
        final URL resourceUrl = ResourceExporter.class.getClassLoader().getResource(resource);
        if(resourceUrl == null)
        {
            throw new ResourceNotFoundException("Failed to find resource " + resource + "!");
        }
        File file = new File(resource);
        try (InputStream inputStream = resourceUrl.openStream(); OutputStream outputStream = new FileOutputStream(file))
        {
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = inputStream.read(buffer)) >= 0)
            {
                outputStream.write(buffer, 0, byteCount);
            }
        }
    }
}
