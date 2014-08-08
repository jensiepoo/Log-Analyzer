import javax.swing.JTextPane;


public class TimeFrame {
	private String log; 
	private JTextPane textArea;
	private String[] x;
	private String[] y;
	
	
	public TimeFrame(JTextPane textArea, String[] x, String[]y){
		this.textArea = textArea;
		log = textArea.getText();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param textArea
	 * @param x
	 * Note: This is the case where the user wants all entries happened 
	 * before the specified time. 
	 * E.g. 07-05 11:25:48.333 - xx-xx xx:xx:xx.xxx 
	 */
	public TimeFrame(JTextPane textArea, String[] x){
		this.textArea = textArea;
		log = textArea.getText();
		this.x = x;
	}
	
	/**
	 * @param y
	 * @param textArea 
	 * Note: This is the case where the user wants all entries happened 
	 * before the specified time. 
	 * E.g. xx-xx xx:xx:xx.xxx - 07-05 11:25:48.333
	 */
	public TimeFrame(String[] y, JTextPane textArea){
		this.textArea = textArea;
		log = textArea.getText();
		this.y = y;
	}

	/**
	 * Parses the segment of radio log in the specified time frame. 
	 */
	public String duration(String[] from, String[] to){
		int x = log.indexOf(from[0]+"-"+from[1]+" "+from[2]+":"+from[3]+":"+from[4]+"."+from[5]);
		int y = log.lastIndexOf(to[0]+"-"+to[1]+" "+to[2]+":"+to[3]+":"+to[4]+"."+to[5]);
		int z = log.indexOf('\n', y);
		String a = log.substring(x,z); 
		return a;
	}
}
