import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class NoService{
	private String log;
	private Color color; 
	private Hashtable<Integer, Color> ht; //Index and Color 
	private JTextPane textArea;
	private JTabbedPane tabbedPane_3;
	private JTextPane allText = new JTextPane();
	private JScrollPane temp = new JScrollPane();
	
	
	
	/**
	 * @param textArea
	 * @param color
	 * @param ht
	 * @param tabbedPane_3
	 * Sets up the mouselistener for aggregated results in the bottom pane.
	 * When user clicks a particular line, the cursor jumps to the line
	 * in the top pane. 
	 */
	public NoService(final JTextPane textArea, Color color, Hashtable<Integer, Color> ht,
			JTabbedPane tabbedPane_3){
		this.textArea = textArea;
		log = textArea.getText();
		this.color = color; 
		this.ht = ht;
		this.tabbedPane_3 = tabbedPane_3;
		
		temp.setViewportView(allText);
		tabbedPane_3.addTab("No Service", temp);

		allText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				int startOffset = allText.viewToModel(new Point(x, y));
				String text = allText.getText();
				if (startOffset != -1) {
					String z = StringFilter.getLine(text, startOffset);
					System.out.println("line: "
						+ z);
					int w = log.indexOf(z.trim());
					System.out.println(w);
					
					textArea.setCaretPosition(w);
				}
			}
		});
		
//		tabbedPane_3.setForegroundAt(tabbedPane_3., color);
	}
	
	/**
	 * @param log
	 * @return a Hashtable of all the no service indices,
	 */
	public Hashtable<Integer, Color> noService(int fromIndex){
		if (log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex) != -1){
			if (log.charAt(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+ "VOICE_REGISTRATION_STATE {".length()) != '1' &&
					log.charAt(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+ "VOICE_REGISTRATION_STATE {".length()) != '5'){
				int from = log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex);
				ht.put(from, color);
				OutputParser.setJTextPaneFont(textArea, color, 
						log.lastIndexOf('\n',from)+1,
						StringFilter.getLine(log, from).length());
				OutputParser.appendToPane(allText, color, 
						log.substring(log.lastIndexOf('\n',from)+1, 
								log.indexOf('\n', from)));
				noService(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+1);
			}
			else{
				noService(log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex)+1);
			}
		}
		return ht;
	}
}
