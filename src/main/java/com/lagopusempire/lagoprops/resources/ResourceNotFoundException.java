package com.lagopusempire.lagoprops.resources;

/**
 * This exception is thrown if LagoProps looks for a resource in a jar and it
 *      cannot find said resource.
 * @author Zora
 */
public class ResourceNotFoundException extends RuntimeException
{
    ResourceNotFoundException(String message)
    {
        super(message);
    }
}
