package demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;
import org.sikuli.basics.Settings;

import edu.unh.iol.dlc.VNCScreen;

import org.sikuli.script.Match;
import org.sikuli.script.Mouse;
import org.sikuli.script.Region;

public class Utils {
	private Screen s;
	private Region r=new Region(0,0,800,800);
	public Utils(){
		Settings.OcrTextSearch=true;
		Settings.OcrTextRead=true;
		s = new Screen();
	}
	public void LoginWithSupervisor(){
		while (r.exists("img/supervisor_status.png") == null){
			try {
				r.click("img/user.png");
				s.wait(1.0);
				r.click("img/supervisor.png");
				s.wait(1.0);
				r.type("Histocore");
				r.click("img/OK_keyboard.png");
				r.wait("img/supervisor_status.png");
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				System.out.println("Login with supervisor fail !!!");
				e.printStackTrace();
			}
			
		}
		
	}
	public String StartProgram (String program_icon,int delaymin) throws FindFailed, InterruptedException{
			System.out.println("Start a new program...");
		
			r.click("img/dashboard.png");
			s.wait(1.0);
			r.click(program_icon);
			s.wait(1.0);
			r.click("img/start_button.png");
			s.wait(1.0);
			Match res_ok;
			Match res_ok2;
			Match res_yes;	
			Match asap;
			int loop=0;
			
			while(true){
				res_ok=r.exists("img/OK_button.png");
				res_yes=r.exists("img/Yes_button.png");
				asap=r.exists("img/ASAP_button.png");
				loop++;
				System.out.println(loop);
				if(asap!=null){
					System.out.println("click asap....");
					asap.click();
					s.wait(1.0);
					break;
				}
				if(res_ok!=null){
					System.out.println("res_ok");
					res_ok.click();
					s.wait(1.0);
				}
				else if(res_yes!=null){
					System.out.println("res_yes");
					res_yes.click();
					s.wait(1.0);
				}
				else {
					System.out.println("no asap???????");
					break;
				}
			
			}
			r.click("img/Run_button.png");
			s.wait(1.0);
			for(int i=0;i<3;i++){
				res_ok=r.exists("img/OK_button.png");
				res_ok2=r.exists("img/Ok_button.png");
				res_yes=r.exists("img/Yes_button.png");
				if(res_ok!=null){
					res_ok.click();
					InitMouse();
					s.wait(1.0);
				}
				else if(res_yes!=null){
					res_yes.click();
					s.wait(1.0);
					InitMouse();
				}
				else if(res_ok2!=null){
					res_ok2.click();
					s.wait(1.0);
					InitMouse();
				}
				else {
					s.wait(1.0);
				}
			}
//			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Match end_of_program;
			
			end_of_program = r.exists("img/end_of_program");
			Region reg = new Region(end_of_program.x,end_of_program.y,end_of_program.w,end_of_program.h*3);
			String[] read_end_time=reg.text().toString().split("\n");
			String date=read_end_time[2].replace(" ","").replace("—", "-").replace("-", "");
			String date2=read_end_time[1].replace(" ","").replace("—", "-");
			String end_time=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)+" "+date2;
//			Date myDate2 = dateFormat2.parse(end_time);
			
			return end_time;
		
	}
	public String Current_GUI_Time(){
		
		Match network_icon = r.exists("img/network_status");
		Region reg = new Region(0,network_icon.y,network_icon.w*6,network_icon.h);
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String[] time_list=reg.text().toString().split("\n");
		String current_gui_time=time_list[1].replace(" ", "").replace("—", "-")+" "+time_list[0].replace(" ", "").replace("—", "-");
//		Date guidate;
		
//		guidate = dateFormat2.parse(time_list[2].replace(" ", "")+" "+time_list[1].replace(" ", ""));
		return current_gui_time;
	}
	public void WaitProgramFinish(String endtime) throws InterruptedException{
		try {
			int loop=0;
			while(true){
				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				dateFormat2.setLenient(false);
				Date guidate = dateFormat2.parse(Current_GUI_Time());
				Date program_end_time=dateFormat2.parse(endtime);
				long current_time_value1 =guidate.getYear()*100000000+guidate.getMonth()*1000000+guidate.getDay()*10000+guidate.getHours()*100+guidate.getMinutes();
				long endtime_value2 =program_end_time.getYear()*100000000+program_end_time.getMonth()*1000000+program_end_time.getDay()*10000+program_end_time.getHours()*100+program_end_time.getMinutes();
				if(current_time_value1<endtime_value2){
					s.wait(60.0);
				}
				else {
					if(loop>0) break;
					s.wait(60.0);
					loop++;
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean DrainRetort() throws FindFailed{
		if(r.exists("img/ConfirmationMessage.png")!=null){
			r.click("img/OK_button");
			InitMouse();
			return true;
		}
		else return false;
	}
	public void CompleteAndRemoveSpecimen(String IP) throws FindFailed, IOException, InterruptedException{
		
			String path = new String("./bash/ssh.sh ");
			System.out.println("instrument ip is..."+IP);
			
			int result = 9999;
			while (true) {
				Process process = null;
				Match m=r.exists("img/ConfirmationMessage");
				if( m != null){
					Match OK_button=r.exists("img/OK_button");
						process = Runtime.getRuntime().exec(path + IP);
						result = process.waitFor();
						s.wait(1.0);
						OK_button.click();
						s.wait(1.0);
				}
				else break;
				
				
			
			}
		
	}
	public void ConfirmReadyToStartNewProgram() throws FindFailed{
		if(r.exists("img/ready_to_start_newprogram.png")!=null){
			Match OK_button=r.find("img/OK_button");
			OK_button.click();
			System.out.println("Program completed,ready to start a new program...");
		}
		else{
			System.out.println("can't find ready to start a new program");
		}
		InitMouse();
		System.out.println("Program complete!!!");
	}
	public void wait(double waitseconds) throws InterruptedException{
		s.wait(waitseconds);
	}
	public void InitMouse(){
		Location location=Mouse.at();
//		Mouse.move(-(location.x),-(location.y));
		Mouse.move(200,200);
	}
	public void CreateTable(String tablename){
		
	}
	public void UpdateTable(String tablename,MapSet<String,String> set){
		
	}
	public void test(){
		
		Current_GUI_Time();
	}
		
	
}
