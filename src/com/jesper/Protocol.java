package com.jesper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import com.jesper.timer.CostumTimer;
import com.jesper.timer.TimerCollection;

public class Protocol {

	private String state = "WAITING";
	private final String COMMAND_ACCEPTED = "TotallySpiesHaveConfirmedYourTask!";
	private Calendar cal;
	private String timestamp;

	public String processInput(String theInput) throws IOException{
		cal = Calendar.getInstance();
		timestamp = (new Timestamp(cal.getTimeInMillis())).toString(); // Tiden NU!

		if(state.equals("WAITING"))
		{
			state = "TERMINATING";
			return "Sup?";
		}
		else
		{
			String [] temp = theInput.split(",");
			String pin = temp[0];
			int delay = Integer.parseInt(temp[1]);
			String new_timestamp = (new Timestamp(cal.getTimeInMillis() + delay)).toString();
			String command = temp[2];

			if(pin.equals(ReadFile.readPin()))
			{
				String res;
				switch(command)
				{
				case "knap1":
					state = "WAITING";
					TimerCollection.add(new CostumTimer(delay,ReadFile.readCommand(),new_timestamp));
					res = "The " + ReadFile.readCommand() + "-command has been executed!," + COMMAND_ACCEPTED;
					return res;
				case "knap2":
					state = "WAITING";
					TimerCollection.add(new CostumTimer(delay,"playmusic",new_timestamp));
					res = "IT'S THE FINAL COUNTDOWN!!!!,"+COMMAND_ACCEPTED;
					return res;
				case "gettasks":
					state = "WAITING";
					return "Tasks:" + TimerCollection.getTasks();
				case "delete":
					state = "WAITING";
					res = TimerCollection.stopTask(Integer.parseInt(temp[3]));
					return res + " has been stopped!";
				default:
					state = "WAITING";
					TimerCollection.add(new CostumTimer(delay,command,new_timestamp));
					res = "The " + command + "-command has been executed!," + COMMAND_ACCEPTED;
					return res;
				}
			}
			else
			{
				state = "WAITING";
				ReadFile.Log(timestamp + ": " + "Wrong pin!");
				return "Wrong pin!";
			}
		}
	}
}
