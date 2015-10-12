package com.lagopusempire.lagoprops.impls;

import com.lagopusempire.lagoprops.LagoProps;
import com.lagopusempire.lagoprops.Prop;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLProps implements LagoProps
{
    private final Properties data;
    private final FileOutputStream saveStream;
    
    public PropertiesLProps(FileInputStream dataStream, FileInputStream defaultDataStream, FileOutputStream saveStream) throws IOException
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
        
        data.load(dataStream);
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
        data.store(saveStream, null);
    }
    
}
