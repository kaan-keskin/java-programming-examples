package TrafficBot;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.time.*;

public class SimpleTrafficBot {

	public static void main(String[] args) throws Exception{

		//Website addresses
		String[] address = {
				"google.com",
				"google.com",
				"google.com",
				"google.com",
				"google.com",
				"google.com",
				"google.com",
				"google.com"
		};
		int lengthAddress = address.length;
        
		//Killing the live chromium processes.
		String[] processKillArgs = new String[]{"pkill","chromium"};

        //Counter set to 1M hit.
        for(int i =0; i<1000000; i++){
        	
        		//Randomly selected addresses
				int randomAddress1 = ThreadLocalRandom.current().nextInt(0, lengthAddress);
				int randomAddress2 = ThreadLocalRandom.current().nextInt(0, lengthAddress);
				
				//Start Chrome browser in linux 
		        String[] processStartArgs1 = new String[]{"chromium", "--incognito --window-size=320,640 --use-mobile-user-agent='Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; ar) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3'", address[randomAddress1]};
		        String[] processStartArgs2 = new String[]{"chromium", "--incognito --window-size=320,640 --use-mobile-user-agent='Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; ar) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3'", address[randomAddress2]};
		     
		        //Start Chrome browser with a proxychain in linux
		        String[] processStartArgs3 = new String[]{"proxychains", "chromium-browser", "--incognito", address[randomAddress2]};
		        
		        System.out.println("Counter: " + i);

		        int hourOfClock = LocalTime.now().getHour();
		        int minuteOfClock = LocalTime.now().getMinute();
				System.out.print(hourOfClock+" : ");
				System.out.println(minuteOfClock);
				
				TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(2000,3000));
				
				new ProcessBuilder(processStartArgs1).start();
				TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(3000,5000));
				
				new ProcessBuilder(processStartArgs2).start();
				TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(35000,40000));
				
				//Killing the live chromium processes.
	            new ProcessBuilder(processKillArgs).start();
	            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000,3000));

        }

	}
}
