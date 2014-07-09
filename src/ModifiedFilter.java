public class ModifiedFilter {
	private String issue;
	private String start;
	private String end;

	public ModifiedFilter(String issue){
		this.issue = issue;
		switch(issue){
			case "Dial Flow": 
				start = "> DIAL";
				end = "> HANGUP";
				break;
//			case "Data Call Flow":  
//				start = "SETUP_DATA_CALL";
//				end = "UNSOL_DATA_CALL_LIST_CHANGED";
//				break;
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
	
	
	
	public String lookup(String log, ModifiedFilter mf){
		//try to get important line only
		
		
		String colon = ":";
		int lastIndex = 0;
		int index = log.indexOf(mf.issue);
		return log;
		while(lastIndex != -1){
			lastIndex = log.indexOf(colon, lastIndex);
			log.in ; 
		}
	}
}

