package com.lagopusempire.lagoprops.impls;

import com.lagopusempire.lagoprops.LagoProps;
import com.lagopusempire.lagoprops.Prop;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesLProps implements LagoProps
{
    private final Properties data;
    private final OutputStream saveStream;
    
    public PropertiesLProps(InputStream dataStream, InputStream defaultDataStream, OutputStream saveStream) throws IOException
    {
        this.saveStream = saveStream;
        
        if(defaultDataStream != null)
        {
            Properties defaultProps = new Properties();
            defaultProps.load(defaultDataStream);
            data = new Properties(defaultProps);
        }
        else
        {
            data = new Properties();
        }
        
        if(dataStream != null)
        {
            data.load(dataStream);
        }
    }
    
    public PropertiesLProps(InputStream dataStream, OutputStream saveStream) throws IOException
    {
        this(dataStream, null, saveStream);
    }
    
    public PropertiesLProps(InputStream dataStream, InputStream defaultDataStream) throws IOException
    {
        this(dataStream, defaultDataStream, null);
    }
    
    public PropertiesLProps(InputStream dataStream) throws IOException
    {
        this(dataStream, null, null);
    }

    @Override
    public Prop<String> getString(String key)
    {
        if(data.containsKey(key))
        {
            String s = data.getProperty(key);
            return new Prop<>(this, key, s, s);
        }
        else
        {
            return new Prop<>(this, key);
        }
    }

    @Override
    public Prop<Integer> getInt(String key)
    {
        if(data.containsKey(key))
        {
            String str = data.getProperty(key);
            if(str.matches("^[0-9]+"))
            {
                int i = Integer.parseInt(str);
                return new Prop<>(this, key, i, str);
            }
            else
            {
                return new Prop<>(this, key, true, str);
            }
        }
        else
        {
            return new Prop<>(this, key);
        }
    }

    @Override
    public Prop<Double> getDouble(String key)
    {
        if(data.containsKey(key))
        {
            String str = data.getProperty(key);
            if(str.matches("[1-9]\\d*(\\.\\d+)?$"))
            {
                double d = Double.parseDouble(str);
                return new Prop<>(this, key, d, str);
            }
            else
            {
                return new Prop<>(this, key, true, str);
            }
        }
        else
        {
            return new Prop<>(this, key);
        }
    }

    @Override
    public <T> void set(String key, T value)
    {
        data.setProperty(key, value.toString());
    }

    @Override
    public void save() throws IOException
    {
        if(saveStream == null)
        {
            throw new IllegalStateException("PropertiesLProps constructed without a save stream!");
        }
        else
        {
            data.store(saveStream, null);
        }
    }
}
