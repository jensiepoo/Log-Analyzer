import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<String, int[]> ht = new HashMap<String, int[]>();

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
	public ArrayList<int[]> recordQMI(int limit){
		String copy = getLog();
		int foundIndex = 0;
		ArrayList<int[]> arr = new ArrayList<int[]>();
		
		while (copy.indexOf("[Request]", foundIndex) != -1 && copy.indexOf("[Response]", foundIndex)!= -1){
			int req = copy.indexOf("[Request]", foundIndex); //position of request
			int res = copy.indexOf("[Response]", foundIndex); // position of response
			if(req < res && req != -1){
				foundIndex = req;
				if(!ht.containsKey(matchingID(foundIndex))){ //if not found, add it to hashtable. 
					ht.put(matchingID(foundIndex), time(copy, foundIndex)); 
				}
				else{
					int[] value = ht.get(matchingID(foundIndex));
					int[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new int[]{value[6], value2[6]});
						ht.remove(matchingID(foundIndex));
					}
				}
			}
			else{
				foundIndex = res;
				if(!ht.containsKey(matchingID(foundIndex))){
					ht.put(matchingID(foundIndex), time(copy, foundIndex)); 
				}
				else{
					int[] value = ht.get(matchingID(foundIndex));
					int[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new int[]{value[6], value2[6]});
						ht.remove(matchingID(foundIndex));
					}
				}
			foundIndex ++; // increment so indexOf wouldn't find the same index
			}	
		}
		return arr;
	}

	/**
	 * 
	 * @param ht
	 * @return true if log has been cut, false if the log is complete
	 */
	public boolean cutLog(Hashtable<String[], int[]> ht){
		if(ht.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
}
