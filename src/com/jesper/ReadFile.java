package com.jesper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

	private static String host;
	private static String pin;
	private static String command;
	private static boolean hide = false;
	private static final String SECRET_CODE = "Hemmelig kode!";
	
	public static  void readFile()
	{
		checkIfExists("settings.txt");
		List<String> res = new ArrayList<String>();
		try{
			// Open the file that is the first 
			// command line parameter

			FileInputStream fstream = new FileInputStream("settings.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				// Print the content on the console
				res.add(strLine);
			}
			//Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		host = res.get(0);
		pin = res.get(1);
		command = res.get(2);
		if(res.get(2).equals(SECRET_CODE)){hide = true;};
	}

	private static void checkIfExists(String filename)
	{
		try {
			File f = new File(filename);
			if(!f.exists())
			{
				FileWriter fstream = new FileWriter(filename);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write("4444");
				out.newLine();
				out.write("3567");
				out.newLine();
				out.write("calc");
				out.newLine();
				out.write("Dette er ikke den rigtigt kode til ninja-mode");
				out.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readHost()
	{
		return host;
	}
	
	public static String readPin()
	{
		return pin;
	}
	
	public static String readCommand()
	{
		return command;
	}

	
	public static boolean hide()
	{
		return hide;
	}
	
	public static void Log(String s)
	{
		try {
			File f = new File("Log.txt");
			FileWriter fstream = new FileWriter("Log.txt",true);
			BufferedWriter out = new BufferedWriter(fstream);
			if(!f.exists())
			{
				out.write("LOG");
			}
			else
			{
				out.newLine();
				out.write(s);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
