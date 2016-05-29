package com.rostlab.sifts.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Longes
 */
public class FWriter {
	
	
	private static File 			file 	= null;
	private static BufferedWriter 	writer 	= null;
	
	
	public static void openFile(String filename)
	{
		try
		{
			file = new File(filename);
			if (file.exists())
			{
				file.delete();
			}
			else
			{
				File parent = file.getParentFile();
				if (parent != null && !parent.exists())
				{
					parent.mkdirs();
				}
			}
			writer = new BufferedWriter(new FileWriter(file));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void openFileAppend(String filename)
	{
		try
		{
			file = new File(filename);
			if (!file.exists())
			{
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file, true));
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
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void writeLine(String line)
	{
		isInitialized();
		try
		{
			writer.write(line+"\n");
			writer.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void write(String line)
	{
		isInitialized();
		try
		{
			writer.write(line);
			writer.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void delete()
	{
		isInitialized();
		try
		{
			if (writer != null)
			{
				writer.close();
			}
			
			if (file != null && file.exists())
			{
				file.delete();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static void delete(String filename)
	{
		try
		{
			File tmpFile = new File(filename);
			if (tmpFile != null && tmpFile.exists())
			{
				tmpFile.delete();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private static boolean isInitialized()
	{
		if (file != null && writer != null)
		{
			return true;
		}
		else
		{
			System.err.println("Error: FileWriter not yet initialized!");
			System.exit(1);
		}
		
		return false;
	}
}
