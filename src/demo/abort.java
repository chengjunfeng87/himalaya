package demo;
import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.sikuli.script.Region;

public class abort {
	

	public int test() throws InterruptedException {
		// TODO Auto-generated method stub
		
		Screen s = new Screen();
		try {
			s.click("img/1461037301649.png");
			s.wait(2.0);
			s.click("img/1461037362677.png");
			s.wait(2.0);
			s.type("Histocore");
			s.click("img/1461037405618.png");
			s.wait("img/1461037454411.png");
			s.click("img/1461037483333.png");
			s.click("img/1461037528235.png");
			s.wait("img/1461037570175.png");
			s.click("img/1461037585877.png");
			s.wait(2.0);
			s.type("test");
			s.click("img/1461038983031.png");
			s.wait(2.0);
			
			s.click("img/1461048438933.png");
			s.wait(2.0);
			s.click("img/1461048775209.png");
			s.wait(2.0);
			s.click("img/1461039077222.png");
		
			s.wait(2.0);
			
			s.click("img/1461039096634.png");
			s.wait(2.0);
			s.click("img/1461039125052.png");
			s.wait(2.0);
//			Image t = s.find("img/1461043844763.png");
			Match t1 = s.find("img/1461043844763.png").right().find("img/1461043884982.png");
			Location l1 = new Location(t1.x+30,t1.y+20);
			Location l2 = new Location(t1.x+30,t1.y-65);
			s.dragDrop(l1, l2);
			s.wait(2.0);
			s.click("img/1461048191970.png");
			s.wait(2.0);
			s.click("img/1461048867126.png");
			s.wait(2.0);
			Match t2 = s.find("img/1461142425561.png").below().find("img/1461048938888.png");
			s.click(t2);
			s.wait(2.0);
			s.click("img/1461048963314.png");
			s.wait(2.0);
			s.click("img/1461048991333.png");
			s.wait(2.0);
			s.click("img/1461049012972.png");
			s.wait(2.0);
			s.click("img/1461050145613.png");
			Match t3 = s.find("img/1461050544682.png").below().find("img/1461050661481.png");
			l1 = new Location(t3.x+30,t3.y+20);
			l2 = new Location(t3.x+30,t3.y-65);
			s.dragDrop(l1,l2);
			s.click("img/1461050145613.png");
			s.wait(2.0);
			s.click("img/1461052093199.png");
			s.wait(1.0);
			s.click("img/1461052112821.png");
			s.wait(2.0);
			s.click("img/1461142729143.png");
			s.wait(2.0);
			s.click("img/1461142753368.png");
			s.wait("img/1461142801026.png",60);
			
		} catch(FindFailed e)
		{
			e.printStackTrace();
		}
		String path = new String("./bash/ssh.sh ");
		String Instrument = new String("10.10.234.92");
		Process process = null;
		int result = 9999;
		try {
			Match OK_button = s.find("img/1461050145613.png");
			OK_button.click();
			while (s.exists("img/1461567265682.png") != null) {
				
				process = Runtime.getRuntime().exec(path + Instrument);
				result = process.waitFor();
				s.click(OK_button);
				s.wait(2.0);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("test result is..."+ ((Integer)result).toString());
		return 0;
		
//-------------------------------------------------------//
//-------------------------------------------------------//
		//Case2...//
//-------------------------------------------------------//
//-------------------------------------------------------//
//		String className = "demo.Export";
//
//		try {
//			Object xyz = Class.forName(className).newInstance();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
