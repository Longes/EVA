package com.rostlab.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Longes
 */
public class FReader {
	
	
	private static File 			file 	= null;
	private static BufferedReader 	reader 	= null;
	
	
	public static void openFile(String filename)
	{
		try
		{
			file = new File(filename);
			reader = new BufferedReader(new FileReader(file));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void closeFile()
	{
		isInitialized();
		try
		{
			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static String readLine()
	{
		isInitialized();
		try
		{
			if (reader.ready())
			{
				return reader.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	
	private static boolean isInitialized()
	{
		if (file != null && reader != null)
		{
			return true;
		}
		else
		{
			System.err.println("Error: FileReader not yet initialized!");
			System.exit(1);
		}
		
		return false;
	}
}
