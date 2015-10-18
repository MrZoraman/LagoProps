package com.lagopusempire.lagoprops.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * An internal resource exporter. Gets files from inside the jar and either
 *      reads them or copies them to the disk.
 * @author Matt
 */
public class ResourceExporter
{
    private final Class c;
    
    /**
     * Constructor.
     * @param c The class is a part of the jar containing the resource to be
     *      exported.
     */
    public ResourceExporter(Class c)
    {
        this.c = c;
    }
    
    /**
     * Exports the resource to disk.
     * @param resource The name of the resource in the jar (and on the disk)
     * @throws IOException If something goes wrong writing to disk or reading
     *      from the jar.
     * @throws ResourceNotFoundException if the resource cannot be found in the
     *      jar file.
     */
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
    
    /**
     * Just gets an input stream of the resource in the jar file.
     *      It is the caller's responsibility to close the stream when they are
     *      done with it.
     * @param resource The name of the resource in the jar.
     * @return The inputstream for the resource.
     * @throws IOException If something goes wrong reading the resource from the
     *      jar.
     * @throws ResourceNotFoundException if the resource cannot be found in the
     *      jar file.
     */
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
