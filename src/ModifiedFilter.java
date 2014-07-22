import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;


/**
 * @author Jensen_kuo
 *
 */
public class ModifiedFilter extends StringFilter implements ActionListener{
	private Color color;
	private String issue;
	private String start;
	private String end;
	private TextArea textArea;
	private int timeLimit = 500; // set time limit to default at 500 ms
	private HashMap<String, String[]> ht = new HashMap<String, String[]>();

	public ModifiedFilter(String issue){  //wtf?
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
			case "Radio Tech Change":
				start = "VOICE_REGISTRATION_STATE {";
				end = "";
				break;	
		}
	}
	
//	public dialFlow
	
	public ModifiedFilter(TextArea textArea, String issue) {
		this.textArea = textArea; 
		this.issue = issue;
		super.setLog(textArea.getText());
	}

	/**
	 * This method records the request and response pair by recording every line that ends with 
	 * [request] and [response] in a hashtable. During the process of recording, when it finds 
	 * a second instance where the String key matches, calculate the time elapsed. 
	 * 
	 * Stores two lines in a size 2 String array.
	 */
	public ArrayList<String[]> recordQMI(int limit){
		String copy = super.log;
		System.out.println("this is log: "+log);
		int foundIndex = 0;
		ArrayList<String[]> arr = new ArrayList<String[]>();
		while (copy.indexOf("[Request]", foundIndex) != -1 && copy.indexOf("[Response]", foundIndex)!= -1){
			int req = copy.indexOf("[Request]", foundIndex); //position of request
			int res = copy.indexOf("[Response]", foundIndex); // position of response
			if(req < res && req != -1){
				foundIndex = req;
				System.out.println(foundIndex);
				if(!ht.containsKey(matchingID(foundIndex))){ //if not found, add it to hashtable. 
					ht.put(matchingID(foundIndex), time(copy, foundIndex)); 
				}
				else{
					String[] value = ht.get(matchingID(foundIndex));
					String[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new String[]{value[6], value2[6]});
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
					String[] value = ht.get(matchingID(foundIndex));
					String[] value2 = time(copy, foundIndex);
					int duration = timeElapsedInMs(value, value2);
					if(duration >= limit){
						arr.add(new String[]{value[6], value2[6]});
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		if(issue.equals("No Service")){
//			this.log = textArea.getText();
//			this.noService(0);
////			System.out.println(this.noService(0).size());
////			java.util.Iterator<Integer> iterator = indices.iterator();
////			while(iterator.hasNext()){
////				System.out.println(getLine(log, iterator.next()));
////			}
//		}
		
		if(issue.equals("QMI TO")){
			Iterator<String[]> iterator = recordQMI(timeLimit).iterator();
			while(iterator.hasNext()){
				log += "request: " + iterator.next()[0] + '\n' + "response: " + iterator.next()[1] + '\n';
			}
			textArea.setText(log);
		}
	}
}
