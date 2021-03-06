import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextPane;

public class QMITimeout extends StringFilter{
	private String log; 
	private HashMap<String, String[]> ht = new HashMap<String, String[]>();
	private ArrayList<String[]> arr = new ArrayList<String[]>();
	public QMITimeout(JTextPane textArea){
		log = textArea.getText();
	}
	
	/**
	 * This method records the request and response pair by recording every line that ends with 
	 * [request] and [response] in a hashtable. During the process of recording, when it finds 
	 * a second instance where the String key matches, calculate the time elapsed. 
	 * 
	 * Stores two lines in a size 2 String array.
	 */
	public ArrayList<String[]> recordQMI(int limit){
		String copy = log;
		int foundIndex = 0;
		
		while (copy.indexOf("[Request]", foundIndex) != -1 || copy.indexOf("[Response]", foundIndex)!= -1){
			int req = copy.indexOf("[Request]", foundIndex); //position of request
			int res = copy.indexOf("[Response]", foundIndex); // position of response
			
			//when request is found before a response is found
			if(req < res && req != -1){
				foundIndex = req; 
				
				//The same set of ID's has not been found in the hashtable. 
				//Add it to hashtable, along with its time. 
				if(!ht.containsKey(matchingID(log, foundIndex))){
					ht.put(matchingID(log, foundIndex), time(copy, foundIndex)); 
				}
				else{
					//If the same set of ID's has been found in the hashtable.
					//Calculate the duration between request and response
					//if it exceeds the limit that user inputed, 
					//place in an array and outputs to the JTextPane. 
					String[] value = ht.get(matchingID(log, foundIndex));
					String[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new String[]{value[6], value2[6]});
					}
					ht.remove(matchingID(log, foundIndex));
				}
			}
			else{
				foundIndex = res;
				if(!ht.containsKey(matchingID(log, foundIndex))){
					ht.put(matchingID(log, foundIndex), time(copy, foundIndex)); 					
				}
				else{
					String[] value = ht.get(matchingID(log, foundIndex));
					String[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new String[]{value[6], value2[6]});
					}
					ht.remove(matchingID(log, foundIndex));
				}
			}	
			foundIndex ++; // increment so indexOf wouldn't find the same index
		}
		return arr;
	}
	
	/**
	 * @param ht
	 * @return true if log has been cut, false if the log is complete
	 */
	public boolean cutLog(){
		if(ht.size() > 0){
			return true;
		}
		else{
			return false;
		}
	}
}
