import java.util.ArrayList;


public class Regex {//implements Comparable<Regex>
	private String match;
	private ArrayList<String> strings = new ArrayList<String>();
	public Regex(String match){
		this.match = match.trim();
	}
	
	/**
	 * @return an ArrayList<String> that contains the list of Strings 
	 * to be pattern matched and StringFilter.
	 * E.G. < LAST_CALL_FAIL_CAUSE|> ANSWER|> DIAL|< DIAL|
	 */
	public ArrayList<String> pattern(){
		String a;
		//As long as there is '|' in the string. 
		if(match.length()> 0 && match.indexOf('|') != -1){
			//Just in case a user inputs a string starting with
			//  |< LAST_CALL_FAIL_CAUSE|
			if(match.charAt(0) == '|'){
				a = match.substring(1, match.indexOf('|', 1));
				match = match.substring(match.indexOf('|', 1)+1);
			}
			
			//update match string every iteration
			else{
				a = match.substring(0, match.indexOf('|'));
				match = match.substring(match.indexOf('|')+1);
			}
			
			//always add trimmed results
			strings.add(a.trim());
			
			//continuously get the rest of the strings
			pattern();
		}
		else{
			strings.add(match.trim());
		}
		return strings;
	}

//	@Override
//	public int compareTo(Regex reg) {
//		if()
//		return 0;
//	} 
	
}
