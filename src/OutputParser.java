import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class OutputParser extends StringFilter implements ActionListener{
	private String log = "";  
	private JTextPane jtp;
	private Hashtable<Integer, Color> ht = new Hashtable<Integer, Color>();	//index and color
	
	//parsed text and color, used only in this class
	private Hashtable<String, Color> ht1 = new Hashtable<String,Color>();
	
	//stores issue and color
	private Hashtable<String, Color> ht2 = new Hashtable<String,Color>();
	private JTextPane jtp1 = new JTextPane(); //textpane for qmi timeout
	private JScrollPane jsp; //scrollpane for setting viewport
	
	private JTextPane jtp2 = new JTextPane(); //textpane for fatal errors 
	private JScrollPane jsp1;// scrollpane for setting fatal error viewport
	private JTextPane jtpcopy; 
	
	//add hashtable as a parameter
	public OutputParser(JTextPane jtp, Hashtable<String,Color> ht2
			,JScrollPane jsp, JScrollPane jsp1){ //, JTabbedPane jtab, 
		this.jtp = jtp;
		this.ht2 = ht2;
		
		this.jsp = jsp; 
		this.jsp1 = jsp1; 
	}

	/**
	 * Expand the Hashtable<index, color> to Hashtable<text, color> for comparison.
	 */
	public void expand(){
		for(int i : ht.keySet()){
			ht1.put(getLine(log, i), ht.get(i));
		}
	}

	/**
     * Utility method for setting the font and color of a JTextPane. The
     * result is roughly equivalent to calling setFont(...) and 
     * setForeground(...) on an AWT TextArea.
     */
//    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c) {
	public static void setJTextPaneFont(JTextPane jtp, Color c, int from, int to) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
//        StyleConstants.setFontFamily(attrs, font.getFamily());
//        StyleConstants.setFontSize(attrs, font.getSize());
//        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
//        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        // Set the font color
        StyleConstants.setForeground(attrs, c);
        
        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();
        
        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(from, to, attrs, false);
    }
	
//	public static void reset(JTextPane jtp) {
//        // Start with the current input attributes for the JTextPane. This
//        // should ensure that we do not wipe out any existing attributes
//        // (such as alignment or other paragraph attributes) currently
//        // set on the text area.
//        MutableAttributeSet attrs = jtp.getInputAttributes();
//
//        // Set the font family, size, and style, based on properties of
//        // the Font object. Note that JTextPane supports a number of
//        // character attributes beyond those supported by the Font class.
//        // For example, underline, strike-through, super- and sub-script.
////        StyleConstants.setFontFamily(attrs, font.getFamily());
////        StyleConstants.setFontSize(attrs, font.getSize());
////        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
////        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
//
//        // Set the font color
//        StyleConstants.setForeground(attrs, c);
//        
//        // Retrieve the pane's document object
//        StyledDocument doc = jtp.getStyledDocument();
//        // Replace the style for the entire document. We exceed the length
//        // of the document by 1 so that text entered at the end of the
//        // document uses the attributes.
//        doc.setCharacterAttributes(from, to, attrs, false);
//        doc.removeStyle()
//    }
	
	
	
	public String getString(ArrayList<Integer> arr, 
			String acc, String header){
		Iterator<Integer> fatalIter = arr.iterator();
		int counter = 1; 
		if(arr.size() > 0){
			acc += header + '\n' + '\n';
			while(fatalIter.hasNext()){
				int next = fatalIter.next();
				acc += Integer.toString(counter) + ". \n "+ StringFilter.getLine(log, 
						next) + '\n' + '\n';
				counter ++;
			}
		}
		return acc;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		log = jtp.getText();
		jtpcopy = jtp;
		String text = ""; 
		Set<String> keys = ht2.keySet();
		Iterator<String> iter = keys.iterator();
    	String accumulator = "";
    	System.out.println("# of items to be analyzed: " + ht2.size());
		while(iter.hasNext()){
			String next = iter.next();
			if(next.equals("no")){
				NoService ns = new NoService(jtp, ht2.get("no"), ht);
				ht = ns.noService(0);
			}
			else if(next.equals("data")){
				// to be implemented () should be easy
			}
			else if(next.equals("dial")){
				// to be implemented ()
			}
			else if(next.equals("radio")){
				RadioChange rc = new RadioChange(jtp, ht2.get("radio"),
						ht);
				int mem;
				if(log.indexOf("VOICE_REGISTRATION_STATE {") != -1){
					mem = RadioChange.lastTech(log.substring
							(log.indexOf("VOICE_REGISTRATION_STATE {")),10);
					ht = rc.find(0, mem);
				}
			}
			else if(next.equals("qmi")){
				QMITimeout qmi = new QMITimeout(jtp, ht2.get("qmi"));
				ArrayList<String[]> arr = qmi.recordQMI(0);
				Iterator<String[]> qmiIter = arr.iterator();
				int counter = 1; 
				while(qmiIter.hasNext()){
					String[] str= qmiIter.next();
					text += Integer.toString(counter) + ". \n "+StringFilter.getLine(log, 
							Integer.parseInt(str[0])) + '\n' + '\n';
					text += StringFilter.getLine(log, 
							Integer.parseInt(str[1])) + '\n' + '\n' + '\n' + '\n';
					counter ++;
				}
				if(qmi.cutLog()){
					text = "!!!!!!!Cut Log!!!!!!!!" + '\n' + '\n' + "[Request] without response = " + text;
				}
				jtp1.setText(text);
		    	jsp.setViewportView(jtp1);
			}
			else{
				StringFilter sf = new StringFilter(jtp, ht2.get(next), next);
				ht = sf.lookup(next);
			}
		}
		if(log.length() > 0){
			StringFilter fatal = new StringFilter (jtp, "Modem is RESET");
			accumulator = getString(fatal.lookupArr("Modem is RESET"), 
					accumulator, "Modem Crash");
			StringFilter fatal1 = new StringFilter(jtp, " rild ");
			accumulator = getString(fatal1.lookupArr("rild"),
					accumulator, "Rild Crash");
			StringFilter fatal2 = new StringFilter(jtp, "phone died");
			accumulator = getString(fatal2.lookupArr("phone died"),
					accumulator, "Phone Crash");
			jtp2.setText(accumulator);
			jsp1.setViewportView(jtp2);
		}
		expand();
		Set<String> set = ht1.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String next = iterator.next(); 
			setJTextPaneFont(jtp, ht1.get(next), log.indexOf(next), next.length());
		}
	}
}