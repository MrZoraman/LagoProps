package com.lagopusempire.lagoprops;

import java.io.IOException;

/**
 * The main class of this library. It is through implementations of this class
 *      that you read and write data.
 * @author MrZoraman
 */
public interface LagoProps
{
    /**
     * Get a string property.
     * @param key The key (should be unique)
     * @return The property. It is with this instance that you can read
     *      the data itself and/or write new data to the properties.
     */
    public Prop<String> getString(String key);
    
    /**
     * Get an int property.
     * @param key The key (should be unique)
     * @return The property. It is with this instance that you can read
     *      the data itself and/or write new data to the properties.
     */
    public Prop<Integer> getInt(String key);
    
    /**
     * Get an double property.
     * @param key The key (should be unique)
     * @return The property. It is with this instance that you can read
     *      the data itself and/or write new data to the properties.
     */
    public Prop<Double> getDouble(String key);
    
    <T> void set(String key, T value);
    
    /**
     * Saves the data to the disk.
     * Data is set via the {@link Prop#set(java.lang.Object} method in the {@link Prop} class.
     * @throws IOException 
     */
    public void save() throws IOException;
}
