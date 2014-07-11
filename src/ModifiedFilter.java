import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * @author Jensen_kuo
 *
 */
public class ModifiedFilter extends StringFilter{
	private String issue;
	private String start;
	private String end;
	private Hashtable<String[], int[]> ht;

	public ModifiedFilter(String issue){
		this.issue = issue;
		switch(issue){
			case "Dial Flow": 
				start = "> DIAL";
				end = "> HANGUP";
				break;
			case "Data Call Flow":  
				start = "SETUP_DATA_CALL";
				end = "UNSOL_DATA_CALL_LIST_CHANGED";
				break;
			case "QMI Time":
				start = "[request]";
				end = "[response]";
				break;
			case "No Service":
				start = "VOICE_REGISTRATION_STATE {";
				end = ""; 
				break;
			case "Radio Tech Change":
				start = "VOICE_REGISTRATION_STATE {";
				end = "";
				break;	
		}
	}
	
//	public dialFlow
	
	/**
	 * This method records the request and response pair by recording every line that ends with 
	 * [request] and [response] in a hashtable. During the process of recording, when it finds 
	 * a second instance where the String key matches, calculate the time elapsed. 
	 */
	public Hashtable<String[], int[]> recordQMI(){
		String copy = getLog();
		int foundIndex;
		
		while (copy.indexOf("[Request]") != -1 && copy.indexOf("[Response]")!= -1){
			int req = copy.indexOf("[Request]"); //position of request
			int res = copy.indexOf("[Response]"); // position of response
			if(req < res && req != -1){
				foundIndex = req;
				if(!ht.containsKey(matchingID(foundIndex))){     //!!!!make sure hashtable uses .equals!!!!!
					ht.put(matchingID(foundIndex), time(copy, foundIndex)); 
				}
				else{
					int[] duration = timeElapsedInMs(ht.get(matchingID(foundIndex)), time(copy, foundIndex));
					ht.remove(matchingID(foundIndex));
					ht.put(matchingID(foundIndex), duration); 
				}
			}
			else{
				foundIndex = res;
				if(!ht.containsKey(matchingID(foundIndex))){
					ht.put(matchingID(foundIndex), time(copy, foundIndex)); 
				}
				else{
					int[] duration = timeElapsedInMs(ht.get(matchingID(foundIndex)), time(copy, foundIndex));
					ht.remove(matchingID(foundIndex));
					ht.put(matchingID(foundIndex), duration); 
				}
			}
			copy = copy.substring(foundIndex + 3);
		}	
		return ht;
	}
	
	
	/**
	 * Returns 1 if a set is an instance of qmi timeout
	 * 0 if not
	 * -1 if there is a cut log, i.e. a request does not get a response.
	 */
	public int qmiTO(Hashtable<String[], int[]> ht){
		Set<Entry<String[], int[]>> set = ht.entrySet();
		Iterator<Entry<String[], int[]>> it = set.iterator();
		
		 while (it.hasNext()) {
		      Map.Entry<String[], int[]> entry = it.next();
		      System.out.println(entry.getKey() + " : " + entry.getValue());
		      if(entry.getValue())
		    }
		return false;
		return 0;
		
	}
}
