
public class StringFilter {
	private String keyword;

	public StringFilter(String keyword){
		this.setKeyword(keyword);
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String filter(String log, String str){
		int lastIndex = 0;
		while(lastIndex != -1){
			lastIndex = log.indexOf(str,lastIndex);
			if( lastIndex != -1){
				lastIndex+=str.length();
		    }
		}
	}
	//To determine whether a keyword exists in a given line
	public boolean check(String log, String key){
		//ToDO
		return true;
	}
	
	public 
	
	
	
	// log.lastIndexOf(str, fromIndex)





















	}
}
