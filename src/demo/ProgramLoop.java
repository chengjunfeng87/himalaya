package demo;

import java.io.IOException;

import org.sikuli.script.FindFailed;

public class ProgramLoop {
	Utils u=new Utils();
	String IP,Path;
	
	public void test(String IP){
		IP=IP;
		u.LoginWithSupervisor();
		String end_time;
		try {
			end_time = u.StartProgram("img/program/program1.png", 0);
			u.WaitProgramFinish(end_time);
			
			if(u.DrainRetort()) System.out.println("Drain retort...");
			else {System.out.println("Can't find Drain retort 5mins later after program endtime"); System.exit(1);}
			u.wait(90.0);
			
			u.CompleteAndRemoveSpecimen(IP);
			u.wait(60.0);
			
			u.ConfirmReadyToStartNewProgram();
		} catch (FindFailed | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
