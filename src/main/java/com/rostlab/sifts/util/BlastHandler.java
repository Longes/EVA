package com.rostlab.sifts.util;

/**
 * Created by Longes
 */
public class BlastHandler {
	
	
	private static String pathBlastP 		= null;
	private static String pathPsiBlast 		= null;
	private static String pathMakeBlastDb 	= null;
	
	
	public static void setPathBlastP(String path)
	{
		pathBlastP = path;
	}
	
	public static void setPathPsiBlast(String path)
	{
		pathPsiBlast = path;
	}
	
	public static void setPathMakeBlastDb(String path)
	{
		pathMakeBlastDb = path;
	}
	
	public static void makeBlastDb(String inputFile, String outputFile, String params)
	{
		Process blast = null;
		
		if (params == null)
		{
			params = "-input_type fasta -dbtype prot -hash_index -logfile "+outputFile+".log";
		}
		
		params = pathMakeBlastDb+" -in "+inputFile+" -out "+outputFile+" "+params;
		
		try
		{
			blast = Runtime.getRuntime().exec(params);
			
			blast.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (blast != null)
			{
				blast.destroy();	
			}
		}
	}
	
	public static void runBlastP(String inputFile, String outputFile, String database, String evalue, String params)
	{
		Process blast = null;
		
		if (params == null)
		{
			params = "-outfmt 6 -max_target_seqs 10000000 -seg no -num_threads 1";
		}
		
		params = pathBlastP+" -query "+inputFile+" -out "+outputFile+" -db "+database+" -evalue "+evalue+" "+params;
		
		try
		{
			blast = Runtime.getRuntime().exec(params);
			
			StreamHandler stdout = new StreamHandler(blast.getInputStream(), "BLAST STDOUT");
			StreamHandler stderr = new StreamHandler(blast.getErrorStream(), "BLAST STDERR");
			
			stdout.start();
			stderr.start();
			blast.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (blast != null)
			{
				blast.destroy();	
			}
		}
	}
	
	public static void runPsiBlast(String inputFile, String outputFile, String database, String evalue, String params)
	{
		//TODO
	}

}
