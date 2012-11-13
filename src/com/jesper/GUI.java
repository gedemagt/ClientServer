package com.jesper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.jesper.timer.TimerCollection;

public class GUI {
	
	static PopupMenu pop_up1;
	static TrayIcon trayIcon;
	static SystemTray tray;
	static MenuItem activeItem, IPItem, portItem, pinItem,exitItem;
	static boolean bar_is_set=false;
	
	public static void main(String[] args) throws IOException
	{
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        
        ReadFile.readFile();
        
        
        trayIcon =new TrayIcon(Toolkit.getDefaultToolkit().getImage("images/server.png").getScaledInstance(16, 16, Image.SCALE_SMOOTH), null);
        tray = SystemTray.getSystemTray();
        
        // Create a pop-up menu components
        IPItem = new MenuItem("IP: " + getIp());
        portItem = new MenuItem("Port: "+ ReadFile.readHost());
        pinItem = new MenuItem("Pinkode: "+ ReadFile.readPin());
        exitItem = new MenuItem("Exit");
        
        ActionListener exitListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
        };
        
        exitItem.addActionListener(exitListener);
       
        if(!ReadFile.hide())
        {
	        //Add components to pop-up menu
        	updateTray();
        }
        Server.start();
	}

	public static void updateTray()
	{
		PopupMenu pop_up = new PopupMenu();
		// Tilføj alle de obligatoriske ting
		pop_up.add(IPItem);
		pop_up.add(portItem);
		pop_up.add(pinItem);
		pop_up.addSeparator();
		pop_up.add(exitItem);
		pop_up.addSeparator();
		
		// Hent opgaver og split dem per ,
		String[] temp = TimerCollection.getTasks().split(",");

		// Hvis der kun er "" skrives følgende
		if(temp.length<2)
		{
			pop_up.add(new MenuItem("No active jobs"));
		}
		// Ellers laves en MenuItem for hver opgave
		else
		{
			for(int i=1; i<temp.length; i++)
			{
				// Lav en MenuItem og put en ActionListener på!
				MenuItem temp_item = new MenuItem(temp[i]);
//				temp_item.addActionListener(new ActionListener(){
//
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						
//					}
//
//				});
				pop_up.add(temp_item);			
			}
		}
		trayIcon.setPopupMenu(pop_up);
		try {
			tray.remove(trayIcon);
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        
	}
	
	private static String getIp()
	{
		//Get an instance of InetAddress for the local computer
		String ipAddress = "Can\'t find IP-address";
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
			ipAddress = inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ipAddress;
	}
}
