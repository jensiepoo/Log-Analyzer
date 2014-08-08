import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JTextPane;


public class RadioChange{
	private Hashtable<Integer, Color> ht = new Hashtable<Integer, Color>();
	private String log;
	private Color color;
	public RadioChange(JTextPane textArea, Color color
			, Hashtable<Integer, Color> ht){
		this.ht = ht;
		log = textArea.getText();
		this.color = color; 
	};
	
	/**
	 * @param fromIndex
	 * @param mem
	 * @return an ArrayList of Integers where radio technology changes take place. 
	 */
	public Hashtable<Integer,Color> find(int fromIndex, int mem){
		if (log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex) != -1){
			int x = log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex);
			String y = log.substring(x);
			int z = lastTech(y,10);
			
			if (z != mem){
				ht.put(x, color);
				mem = z;
				find(x+1, mem);
			}
			else{
				find(x+1, mem);
			}
		}
		return ht;
	}
	
	/**
	 * @param text
	 * @return the technology stored VOICE_REGISTRATION_STATE {}'s 11th index.
	 * Note: call with counter = 10.
	 */
	public static int lastTech(String text, int counter){ 
		if(counter == 0){
			int counter1 = 0;
			int value = 0;
			while(text.charAt(counter1) != ' '){	
				value = Integer.parseInt(text.substring(0, text.indexOf(',')));
				counter1++;
			}
			return value;
		}
		else{
			counter = counter - 1;
			return lastTech(text.substring(text.indexOf(',')+1), counter); 
		}
	}
}
