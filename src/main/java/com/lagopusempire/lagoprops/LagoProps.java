package com.lagopusempire.lagoprops;

import java.io.IOException;

public interface LagoProps
{
    public Prop<String> getString(String key);
    public Prop<Integer> getInt(String key);
    public Prop<Double> getDouble(String key);
    
    public <T> void set(String key, T value);
    
    public void save() throws IOException;
}
