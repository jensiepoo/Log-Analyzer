import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JTextPane;

public class NoService{
	private String log;
	private Color color; 
	private Hashtable<Integer, Color> ht; //Index and Color 
	
	public NoService(JTextPane textArea, Color color, Hashtable<Integer, Color> ht){
		log = textArea.getText();
		this.color = color; 
		this.ht = ht;
	}
	
	/**
	 * @param log
	 * @return an Hashtable of all the no service indices,
	 *  can later be added to String using getLine.
	 */
	public Hashtable<Integer, Color> noService(int fromIndex){
		if (log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex) != -1){
			if (log.charAt(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+ "VOICE_REGISTRATION_STATE {".length()) != '1' &&
					log.charAt(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+ "VOICE_REGISTRATION_STATE {".length()) != '5'){
				ht.put(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex), color);
				noService(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+1);
			}
			else{
				noService(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+1);
			}
		}
		return ht;
	}
}
