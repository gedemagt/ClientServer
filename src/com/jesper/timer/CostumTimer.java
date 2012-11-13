package com.jesper.timer;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

public class CostumTimer extends Timer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2067562690467601285L;
	private String command;
	private String timestamp;
	private String message;

	public CostumTimer(int arg0, final String command, String timestamp) {

		super(arg0, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(command.equals("playmusic"))
					{
						Desktop d = Desktop.getDesktop();
						d.open(new File(System.getProperty("user.dir")+"\\images\\awesometfc.mp3"));
					}
					else
					{
						Runtime.getRuntime().exec(command);
					}
					TimerCollection.remove(((CostumTimer) e.getSource()));
					((CostumTimer)e.getSource()).stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					((CostumTimer)e.getSource()).stop();
				}

			}

		});
		
		this.command = command;
		this.timestamp = timestamp.substring(11,16);
		this.message = "Executing " + this.command + " at " + this.timestamp;

		this.start();

	}

	public String toString()
	{
		return message;
	}
}
