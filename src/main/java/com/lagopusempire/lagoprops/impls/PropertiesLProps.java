package com.lagopusempire.lagoprops.impls;

import com.lagopusempire.lagoprops.LagoProps;
import com.lagopusempire.lagoprops.Prop;
import com.lagopusempire.lagoprops.resources.ResourceExporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesLProps implements LagoProps
{
    private final Properties data;
    private final String fileName;
    
    public PropertiesLProps(String fileName, Class c) throws IOException
    {
        ResourceExporter exporter = new ResourceExporter(c);
        
        File file = new File(fileName);
        if(!file.exists())
        {
            exporter.exportResource(fileName);
        }
        
        Properties defaultProps = new Properties();
        InputStream defaultPropsStream = exporter.getResourceStream(fileName);
        defaultProps.load(defaultPropsStream);
        
        data = new Properties(defaultProps);
        InputStream dataStream = new FileInputStream(file);
        data.load(dataStream);
        
        this.fileName = fileName;
    }
    
//    public PropertiesLProps(String fileName) throws IOException
//    {
//        data = new Properties();
//        File file = new File(fileName);
//        if(!file.exists())
//        {
//            file.createNewFile();
//        }
//        this.fileName = fileName;
//    }

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
        OutputStream saveStream = new FileOutputStream(new File(fileName));
        data.store(saveStream, null);
    }
}
