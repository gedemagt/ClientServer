package com.jesper.timer;

import java.util.ArrayList;
import java.util.List;

import com.jesper.GUI;

public class TimerCollection {
	
	private static List<CostumTimer> timers = new ArrayList<CostumTimer>();
	
	public static void add(CostumTimer ct)
	{
		timers.add(ct);
		GUI.updateTray();
		System.out.print("Added: "+timers.size());
	}
	
	public static void remove(CostumTimer ct)
	{
		timers.remove(ct);
		GUI.updateTray();
		System.out.print("Removed: "+timers.size());
	}
	
	public static String getTasks()
	{
		String res = "";
		for(CostumTimer ct : timers)
		{
			res += "," + ct.toString();
		}
		return res;
	}
	

	public static String stopTask(int index) {
		String res = timers.get(index).toString();
		timers.get(index).stop();
		timers.remove(index);
		GUI.updateTray();
		return res;
		
	}
}
