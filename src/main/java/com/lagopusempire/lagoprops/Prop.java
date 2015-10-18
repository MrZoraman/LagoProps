package com.lagopusempire.lagoprops;

/**
 * Represents a property that can be read from and written to.
 * You should never construct this class yourself, but rather let an implementation of {@link LagoProps} get it for you.
 * @author MrZoraman
 * @param <T> This is the type of data stored by this property. So far valid options are INTEGER, DOUBLE and STRING.
 */
public class Prop<T>
{
    private Prop(LagoProps props, String key, T data, boolean isNull, boolean isError, String originalData)
    {
        this.props = props;
        this.key = key;
        this.data = data;
        this.isNull = isNull;
        this.isError = isError;
        this.originalData = originalData;
    }
    
    public Prop(LagoProps props, String key)
    {
        this(props, key, null, true, false, "");
    }
    
    public Prop(LagoProps props, String key, T data, String originalData)
    {
        this(props, key, data, false, false, originalData);
    }
    
    public Prop(LagoProps props, String key, boolean error, String originalData)
    {
        this(props, key, null, true, true, originalData);
    }
    
    private final LagoProps props;
    private final String key;
    private T data;
    private boolean isNull;
    private boolean isError;
    private final String originalData;
    
    /**
     * Get the data stored by this property.
     * Be sure to check some of the other flags to make sure it is ok to use!
     * @return The data, if reading was successful.
     */
    public T get()
    {
        return data;
    }
    
    /**
     * Checks if the data was null or not.
     * @return True if nothing was found for the given key, false if something was found.
     */
    public boolean isNull()
    {
        return isNull;
    }
    
    /**
     * Sets a new value for this property.
     * @param value The value to save to the config file. Changes won't take
     *      until {@link LagoProps#save()} is called.
     */
    public void set(T value)
    {
        if(value == null)
        {
            throw new IllegalArgumentException("Cannot set prop to null!");
        }
        
        props.set(key, value);
        isError = false;
        isNull = false;
        data = value;
    }
    
    /**
     * Checks if there was an error reading or not.
     * @return true of there was a failure reading the data. This is likey
     *      cause by a failure to parse a number.
     */
    public boolean isError()
    {
        return isError;
    }
    
    /**
     * This is for debug and feedback purposes. Gets the data that LagoProps
     *      found, if any.
     * @return The original data that is in the file itself. It may be valid,
     *      but it may not be.
     */
    public String originalData()
    {
        return originalData;
    }
}
