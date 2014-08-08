import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JTextPane;



//provide utility functions for ModifiedFilter
/**
 * @author Jensen_kuo
 *
 */
public class StringFilter{
	private String log;
	private Color color; 
	private JTextPane textArea;
	private Hashtable<Integer, Color> ht = new Hashtable<Integer, Color>();
	private String str;
	
	
	//default Constructor
	public StringFilter(){
	}
	
	public StringFilter(JTextPane textArea, Color color, String str){
		this.textArea = textArea; 
		this.color = color; 
		log = textArea.getText();
		this.str = str;
	}
	
	public StringFilter(JTextPane textArea, String str){
		this.textArea = textArea; 
		log = textArea.getText();
		this.str = str;
	}


	/**
	 * check time elapsed(in ms) between two times, if second input time is earlier than first input time, output -1
	 * used by Stored as int[int month, int day, int hr, int min, int sec, int ms] 
	 * @param from
	 * @param to
	 * @return the absolute value of timeElapsed since there can only be a request and a response per ID. 
	 */
	public static int timeElapsedInMs(String[] from, String[] to){		
		SimpleDateFormat format = new SimpleDateFormat("MM/DD HH:mm:ss.SSS");
		Date d1 = null, d2 = null;
		long diff;
		try {
		    d1 = format.parse(from[0]+ "/" + from[1]+ " " + from[2]+ ":" + from[3]+ ":" + from[4]+ "." + from[5]);
		    d2 = format.parse(to[0]+ "/" + to[1]+ " " + to[2]+ ":" + to[3]+ ":" + to[4]+ "." + to[5]);
		} catch (ParseException e) {
		    e.printStackTrace();
		}    
		if(d1.compareTo(d2) >= 0){
			diff = d1.getTime() - d2.getTime();
		}
		else{
			diff = d2.getTime() - d2.getTime();
		}
		return (int) diff; 
	}
	
	/**
	 * @param log1
	 * @param from
	 * @return a line that includes the index
	 */
	public static String getLine(String log1, int from){
		return (log1.substring(log1.lastIndexOf('\n', from)+1, log1.indexOf('\n', from)));
	}
	
	/**
	 * This method is used to get values in between [] for matching request and response return 
	 * in the format [Serv_ID+ Msg_ID+ Trans_ID]
	 * fromIndex: index where [request] or [response is found]
	 * Return a concatenated string allows comparison in HashMap
	 */
	public static String matchingID(String log, int fromIndex){
		String x = getLine(log, fromIndex);
		String sID, mID, tID;
		int index = x.indexOf("Serv_ID: [")+"Serv_ID: [".length();
		x = x.substring(index);
		sID = x.substring(0, x.indexOf(']'));
		index = x.indexOf("Msg_ID: [")+"Msg_ID: [".length();
		x = x.substring(index);
		mID = x.substring(0, x.indexOf(']'));
		index = x.indexOf("Trans_ID: [")+"Trans_ID: [".length();
		x = x.substring(index);
		tID = x.substring(0, x.indexOf(']'));
		return sID+mID+tID;
	}
	
	/**
	 * 	Given a specific index(line) parse the time in [int month, int day, int hr, int min, int sec, int ms, index] order
	 */
	public static String[] time(String log, int index){
		String[] time = new String[7];
		String line = getLine(log, index);
		time[0] = line.substring(0, 2);
		time[1] = line.substring(3, 5);
		time[2] = line.substring(6, 8);
		time[3] = line.substring(9, 11);
		time[4] = line.substring(12, 14);
		time[5] = line.substring(15, 18);
		time[6] = String.valueOf(index);
		return time;
	}
	
	/**
	 * Parses the segment of radio log in the specified time frame. 
	 */
	public static String duration(String log, String[] from, String[] to){
		int x = log.indexOf(from[0]+"-"+from[1]+" "+from[2]+":"+from[3]+":"+from[4]+"."+from[5]);
		int y = log.lastIndexOf(to[0]+"-"+to[1]+" "+to[2]+":"+to[3]+":"+to[4]+"."+to[5]);
		int z = log.indexOf('\n', y);
		String a = log.substring(x,z); 
		return a;
	}
	
	/**
	 * @param str
	 * updates the ArrayList of indices and later to be parsed by OutputParser.
	 */
	public Hashtable<Integer, Color> lookup(String str){
		int counter = 0;
		while(log.indexOf(str, counter)!= -1){
			ht.put(log.indexOf(str, counter), color);
			counter = log.indexOf(str, counter)+1;
		}
		return ht;
	}
	
	public ArrayList<Integer> lookupArr(String str){
		int counter = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while(log.indexOf(str, counter)!= -1){
			arr.add(log.indexOf(str, counter));
			counter = log.indexOf(str, counter)+1;
		}
		return arr;
	}
}