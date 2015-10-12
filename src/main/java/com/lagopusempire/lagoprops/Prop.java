package com.lagopusempire.lagoprops;

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
    
    public T get()
    {
        return data;
    }
    
    public boolean isNull()
    {
        return isNull;
    }
    
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
    
    public boolean isError()
    {
        return isError;
    }
    
    public String originalData()
    {
        return originalData;
    }
}
