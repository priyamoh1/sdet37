package com.crm.comcast.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author -hp-
 *
 */
public class FileUtility
{
public String getpropertyKeyValue(String key) throws IOException
{
	FileInputStream fileinputstream= new FileInputStream(IConstants.filepath);
	Properties properties= new Properties();
	properties.load(fileinputstream);
	String value=properties.getProperty(key);
	return value;
	
}
}
