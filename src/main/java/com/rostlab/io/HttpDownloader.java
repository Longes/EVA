package com.rostlab.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.zip.GZIPInputStream;

/**
 * Created by Longes
 */
public class HttpDownloader {
	
	
	private static BufferedInputStream 	bInputStream 	= null;
	private static GZIPInputStream 		gzInputStream 	= null;
	private static BufferedOutputStream bOutputStream 	= null;
	private static FileOutputStream 	fOutputStream 	= null;
	private static HashSet<String> 		invalidUrls 	= new HashSet<String>();
	
	
	public static boolean downloadToFile(String url, String filename)
	{
		if (invalidUrls.contains(url))
		{
			System.err.println("Error: File not found: "+url);
			
			return false;
		}
		
		boolean deleteFile = true;
		try
		{
			System.out.println("Downloading: "+url);
			File parent = (new File(filename)).getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			bInputStream = new BufferedInputStream((new URL(url)).openStream());
			fOutputStream = new FileOutputStream(new File(filename));
			bOutputStream = new BufferedOutputStream(fOutputStream);
			
			int read = 0;
			int written = 0;
			byte buffer[] = new byte[1024];
			
			while ((read = bInputStream.read(buffer, 0, 1024)) >= 0)
			{
				bOutputStream.write(buffer, 0, read);
				written += read;
			}
			bOutputStream.flush();
			System.out.println(written + " byte(s) written.");
			if (bInputStream != null) 	{bInputStream.close();}
			if (fOutputStream != null) 	{fOutputStream.close();}
			if (bOutputStream != null) 	{bOutputStream.close();}
			if (written > 0) 			{deleteFile = false;}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error: File not found: "+url);
			invalidUrls.add(url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		finally
		{
			if (deleteFile)
			{
				File file = new File(filename);
				if (file.exists() && !file.delete()) {file.deleteOnExit();}
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean downloadAndDecompressToFile(String url, String filename)
	{
		if (invalidUrls.contains(url))
		{
			System.err.println("Error: File not found: "+url);
			return false;
		}
		boolean deleteFile = true;
		try
		{
			System.out.println("Downloading: "+url);
			File parent = (new File(filename)).getParentFile();
			if (parent != null && !parent.exists()) {parent.mkdirs();}
			bInputStream 	= new BufferedInputStream((new URL(url)).openStream());
			gzInputStream 	= new GZIPInputStream(bInputStream);
			fOutputStream 	= new FileOutputStream(new File(filename));
			bOutputStream 	= new BufferedOutputStream(fOutputStream);
			int read 		= 0;
			int written 	= 0;
			byte buffer[] 	= new byte[1024];
			while ((read = gzInputStream.read(buffer, 0, 1024)) >= 0)
			{
				bOutputStream.write(buffer, 0, read);
				written += read;
			}
			bOutputStream.flush();
			System.out.println(written+" byte(s) written.");
			if (bInputStream != null) 	{bInputStream.close();}
			if (gzInputStream != null) 	{gzInputStream.close();}
			if (fOutputStream != null) 	{fOutputStream.close();}
			if (bOutputStream != null) 	{bOutputStream.close();}
			if (written > 0) 			{deleteFile = false;}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Error: File not found: "+url);
			invalidUrls.add(url);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		finally
		{
			if (deleteFile)
			{
				File file = new File(filename);
				if (file.exists() && !file.delete()) {file.deleteOnExit();}
				return false;
			}
		}
		
		return true;
	}

}
