

//provide utility functions for ModifiedFilter
/**
 * @author Jensen_kuo
 *
 */
public abstract class StringFilter {
	private String log;

	//default Constructor
	public StringFilter(){
	}
	
	public StringFilter(String log) {
		setLog(log);
	}
	
	public void setLog(String log){
		this.log = log;
	}
	
	public String getLog(){
		return log;
	}
	
	
	/**
	 * check time elapsed(in ms) between two times, if second input time is earlier than first input time, output -1
	 * used by Stored as int[int month, int day, int hr, int min, int sec, int ms] 
	 * @param from
	 * @param to
	 * @return 
	 */
	public static int[] timeElapsedInMs(int[] from, int[] to){		
		return new int[]{-1}; // put in [x] of size 1;
	}
	
	/**
	 * @param log1
	 * @param from
	 * @return a line that includes the index
	 */
	public static String getLine(String log1, int from){
		return (log1.substring(log1.lastIndexOf('\n', from)+1, log1.indexOf('\n', from)+1));
	}
	
	/**
	 * This method is used to get values in between [] for matching request and response return 
	 * in the format [Serv_ID, Msg_ID, Trans_ID]
	 * fromIndex: index where [request] or [response is found]
	 */
	public String[] matchingID(int fromIndex){
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
		return (new String[]{sID, mID, tID});
	}
	
	/**
	 * @param log2
	 * @return the noService line of log
	 */
	public static String noService(String log2){
		if(log2.charAt(log2.indexOf("VOICE_REGISTRATION_STATE {")+ "VOICE_REGISTRATION_STATE {".length()) == '1' ||
				log2.charAt(log2.indexOf("VOICE_REGISTRATION_STATE {")+ "VOICE_REGISTRATION_STATE {".length()) == '5'){
			return getLine(log2, log2.indexOf("VOICE_REGISTRATION_STATE {"));
		}
		else{
			return null;
		}
	}
	
	/**
	 * 	Given a specific index(line) parse the time in [int month, int day, int hr, int min, int sec, int ms] order
	 */
	public static int[] time(String log, int index){
		int[] time = new int[6];
		String line = getLine(log, index);
		time[0] = (Integer.parseInt(line.substring(0, 2)));
		time[1] = (Integer.parseInt(line.substring(3, 5)));
		time[2] = (Integer.parseInt(line.substring(6, 8)));
		time[3] = (Integer.parseInt(line.substring(9, 11)));
		time[4] = (Integer.parseInt(line.substring(12, 14)));
		time[5] = (Integer.parseInt(line.substring(15, 18)));
		return time;
	}
}










//BufferedReader br = new BufferedReader(new FileReader(fileName));
//try {
//  StringBuilder sb = new StringBuilder();
//  String line = br.readLine();
//      
//  for(String a: stringFilter){
//      while (line != null && line.indexOf(a)!= -1) {
//      	sb.insert(0, '/'+ a + '/');
//	        sb.append(line);
//	        sb.append("\n");
//	        line = br.readLine();
//	    }
//  }
//  return sb.toString();
//	} finally {
//      br.close();
//  }
