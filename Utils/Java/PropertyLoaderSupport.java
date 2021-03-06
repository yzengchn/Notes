package com.haosen.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


/**
 * @ClassName: PropertyLoader
 * @version 1.0
 * @Desc: 读取属性文件
 */
public class PropertyLoaderSupport
{
    
    private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
    
    public static String read(String fileName, String key)
    {
        
        if (!propertiesMap.containsKey(fileName) || propertiesMap.get(fileName) == null)
        {
            loadProperty(fileName);
        }
        
        Properties properties = propertiesMap.get(fileName);
        
        return properties.getProperty(key);
    }
    
    public static String getKey(String fileName,String key)
    {
        return read(fileName, key);
    }
    
    /**
     * 描述：加载属性文件
     * 
     * @param fileName
     */
    private static void loadProperty(String fileName)
    {
        Properties properties = new Properties();
        
        try
        {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(fileName);
            InputStream in = new BufferedInputStream(resource.getInputStream());
            properties.load(in);
            propertiesMap.put(fileName, properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 读取.properties文件对应的标签值
     * 
     * @param path
     * @param propertieName
     * @return
     * @throws Exception 
     */
    public static String getPropertieValue(String path, String propertieName)
    {
        Properties props = new Properties();
//        InputStream in = PropertyLoaderSupport.class.getResourceAsStream(path);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(path);
        InputStream in =null;
        
        try
        {
            in = new BufferedInputStream(resource.getInputStream());
            props.load(in);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        finally {
            if(in!=null){
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        return props.getProperty(propertieName);
    }
}
