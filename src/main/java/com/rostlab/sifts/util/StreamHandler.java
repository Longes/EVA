package com.rostlab.sifts.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Longes
 */
public class StreamHandler extends Thread{
	
	
	private InputStream inputStream = null;
	private String 		label 		= null;

	public StreamHandler(InputStream inputStream, String label)
	{
		this.inputStream 	= inputStream;
		this.label 			= label;
	}

	public void run()
	{
		try
		{
			InputStreamReader streamReader 	= new InputStreamReader(this.inputStream);
			BufferedReader 	bufferedReader 	= new BufferedReader(streamReader);
			String line = bufferedReader.readLine();
			while (line != null)
			{
				System.out.println(this.label+" > "+line);
				line = bufferedReader.readLine();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
