import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextPane;


public class TimeFrame {
	private String log; 
	private JTextPane textArea;
	private ArrayList<String> from;
	private ArrayList<String> to;
	private String copy;
	
	public TimeFrame(JTextPane textArea, ArrayList<String> from, ArrayList<String> to){
		this.textArea = textArea;
		log = textArea.getText();
		this.from = from;
		this.to = to;
	}
	
//	/**
//	 * @param y
//	 * @param textArea 
//	 * Note: This is the case where the user wants all entries happened 
//	 * before the specified time. 
//	 * E.g. xx-xx xx:xx:xx.xxx - 07-05 11:25:48.333
//	 */
//	public TimeFrame(String[] y, JTextPane textArea){
//		this.textArea = textArea;
//		log = textArea.getText();
//		this.y = y;
//	}
	   
	public static String helper(ArrayList<String> arr){
		int length = arr.size();
		switch(length){
		case 0: return "";
		case 1: return arr.get(0) + "-";
		case 2: return arr.get(0) + "-" + arr.get(1) + " ";
		case 3: return arr.get(0) + "-" + arr.get(1) + " " + arr.get(2) + ":";
		case 4: return arr.get(0) + "-" + arr.get(1) + " " + arr.get(2) + ":" 
							+ arr.get(3) + ":";
		case 5: return arr.get(0) + "-" + arr.get(1) + " " + arr.get(2) + ":" 
							+ arr.get(3) + ":" + arr.get(4) + ".";
		case 6: return arr.get(0) + "-" + arr.get(1) + " " + arr.get(2) + ":" 
							+ arr.get(3) + ":" + arr.get(4) + "." + arr.get(5);
		default: return "";
		}
	}
	
	
//	/**
//	 * check time elapsed(in ms) between two times, if second input time is earlier than first input time, output -1
//	 * used by Stored as int[int month, int day, int hr, int min, int sec, int ms] 
//	 * @param from
//	 * @param to
//	 * @return the absolute value of timeElapsed since there can only be a request and a response per ID. 
//	 */
//	public static int timeElapsedInMs(String[] from, String[] to){		
//		SimpleDateFormat format = new SimpleDateFormat("MM/DD HH:mm:ss.SSS");
//		Date d1 = null, d2 = null;
//		long diff;
//		try {
//		    d1 = format.parse(from[0]+ "/" + from[1]+ " " + from[2]+ ":" + from[3]+ ":" + from[4]+ "." + from[5]);
//		    d2 = format.parse(to[0]+ "/" + to[1]+ " " + to[2]+ ":" + to[3]+ ":" + to[4]+ "." + to[5]);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}    
//		if(d1.compareTo(d2) >= 0){
//			diff = d1.getTime() - d2.getTime();
//		}
//		else{
//			diff = d2.getTime() - d1.getTime();
//		}
//		return (int) diff; 
//	}
//	
	
	
	
	/**
	 * Parses the segment of radio log in the specified time frame. 
	 */
	public void duration(){
		int x,y,z;
		String a;
//		System.out.println("from = " + helper(from));
//		System.out.println("to = "+ helper(to));
		if(helper(from).equals("") || log.indexOf(helper(from))== -1){
			x = 0;
		}
		else{
			System.out.println(log.indexOf(helper(from)));
			x = log.indexOf(helper(from));
		}
		if(helper(to).equals("") || log.indexOf(helper(to))== -1){
			a = log.substring(x);
		}
		else{
			if(log.lastIndexOf(helper(to))!= -1){
				y = log.lastIndexOf(helper(to));
				z = log.indexOf('\n', y);
			}
			else{
				y = log.length()+1;
			    z = y;
			}
			
			a = log.substring(x, z);
		}

		textArea.setText(a);
		textArea.updateUI();
	}
}
