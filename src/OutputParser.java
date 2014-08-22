import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class OutputParser extends StringFilter implements ActionListener {
	private String log = "";
	private JTextPane jtp;

	// index and color
	private Hashtable<Integer, Color> ht = new Hashtable<Integer, Color>();
	
	// stores issue and color
	private Hashtable<String, Color> ht2 = new Hashtable<String, Color>();
	private JTextPane jtp1 = new JTextPane(); // textpane for qmi timeout
	private JScrollPane jsp; // scrollpane for setting viewport

	private JTextPane jtp2 = new JTextPane(); // textpane for fatal errors
	private JScrollPane jsp1;// scrollpane for setting fatal error viewport
	private JTextPane jtpcopy;
	private int counter = 0;
	private ArrayList<String> from;
	private ArrayList<String> to;
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedPane_3;
	private JFormattedTextField jft;
	private JTextPane jtp3 = new JTextPane(); // textpane for aggregate

	// //for removing console window
	// private JPopupMenu menu = new JPopupMenu();
	// private JPanel jpane;
	// private JMenuItem item = new JMenuItem("Delete");

	// add hashtable as a parameter
	public OutputParser(JTextPane jtp, Hashtable<String, Color> ht2,
			JScrollPane jsp, JScrollPane jsp1, ArrayList<String> from,
			ArrayList<String> to, JTabbedPane tabbedPane,
			JTabbedPane tabbedPane_3, JFormattedTextField jft) { // ,
																	// JTabbedPane
																	// jtab,
		this.jtp = jtp;
		this.ht2 = ht2;
		this.jsp = jsp;
		this.jsp1 = jsp1;
		this.from = from;
		this.to = to;
		this.tabbedPane = tabbedPane;
		this.tabbedPane_3 = tabbedPane_3;
		this.jft = jft;
	}

//	/**
//	 * Expand the Hashtable<index, color> to Hashtable<text, color> for
//	 * comparison.
//	 */
//	public void expand() {
//		ht1.clear();
//		for (int i : ht.keySet()) {
//			ht1.put(getLine(log, i), ht.get(i));
//		}
//	}

	/**
	 * @param jtp
	 * @param c
	 * @param from
	 * @param to
	 *            Sets a particular line in the text with the designated color.
	 */
	public static void setJTextPaneFont(JTextPane jtp, Color c, int from, int to) {
		// // Start with the current input attributes for the JTextPane. This
		// // should ensure that we do not wipe out any existing attributes
		// // (such as alignment or other paragraph attributes) currently
		// // set on the text area.
		//
		// StyleContext sc = StyleContext.getDefaultStyleContext();
		//
		// AttributeSet attrs = sc.addAttribute(SimpleAttributeSet.EMPTY,
		// StyleConstants.Foreground, c);
		// // Set the font color
		//
		// // Retrieve the pane's document object
		// StyledDocument doc = jtp.getStyledDocument();
		//
		// // Replace the style for the entire document. We exceed the length
		// // of the document by 1 so that text entered at the end of the
		// // document uses the attributes.
		// doc.setCharacterAttributes(from, to, attrs, true);
		// jtp.updateUI();

		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, c);
		StyledDocument doc = jtp.getStyledDocument();
		doc.setCharacterAttributes(from, to, attrs, true);
	}

	/**
	 * @param tp
	 * @param c
	 * @param msg
	 *            Appends colored text to a the JTextPane
	 */
	public static void appendToPane(JTextPane tp, Color c, String msg) {
		// StyleContext sc = StyleContext.getDefaultStyleContext();
		// AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
		// StyleConstants.Foreground, c);
		//
		// // aset = sc.addAttribute(aset, StyleConstants.FontFamily,
		// "Lucida Console");
		// // aset = sc.addAttribute(aset, StyleConstants.Alignment,
		// StyleConstants.ALIGN_JUSTIFIED);
		//
		// int len = tp.getDocument().getLength();
		// tp.setCaretPosition(len);
		// tp.setCharacterAttributes(aset, false);
		// tp.replaceSelection(msg);

		StyledDocument doc = tp.getStyledDocument();

		SimpleAttributeSet attrs = new SimpleAttributeSet();
		StyleConstants.setForeground(attrs, c);

		// Style style = tp.addStyle(msg, null);
		// StyleConstants.setForeground(style, c);

		try {
			doc.insertString(doc.getLength(), msg, attrs);
		} catch (BadLocationException e) {
		}
	}

	/**
	 * @param arr
	 * @param acc
	 * @param header
	 * @return Returns a string with fatal errors found in the text.
	 */
	public String getString(ArrayList<Integer> arr, String acc, String header) {
		Iterator<Integer> fatalIter = arr.iterator();
		int counter = 1;
		if (arr.size() > 0) {
			acc += header + '\n' + '\n';
			while (fatalIter.hasNext()) {
				int next = fatalIter.next();
				acc += Integer.toString(counter) + ". \n "
						+ StringFilter.getLine(log, next) + '\n' + '\n';
				counter++;
			}
		}
		return acc;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		jtp.removeStyle();
		jtp1.setText(""); // qmi pane
		jtp2.setText(""); // fatal pane
		tabbedPane_3.removeAll();
		jtp3.setText(""); // aggregated results
		if ((JScrollPane) tabbedPane.getSelectedComponent() != null) {
			JScrollPane c = (JScrollPane) tabbedPane.getSelectedComponent();
			jtp = (JTextPane) c.getViewport().getView(); // main pane

			// Time frame object sets the time frame of the text. Chops off
			// lines with times before and after the time frame.
			TimeFrame tf = new TimeFrame(jtp, from, to);
			tf.duration();

			// Clears the hashtable of indices everytime analyze is pressed.
			// Re-analyzes everything.
			ht.clear();
			log = jtp.getText();

			String text = "";

			// the list of keywords to be analyzed
			Set<String> keys = ht2.keySet();
			Iterator<String> iter = keys.iterator();
			String accumulator = "";
			System.out.println("# of items to be analyzed: " + ht2.size());

			// loops through the table of keywords to be analyzed.
			while (iter.hasNext()) {
				String next = iter.next();
				if (next.equals("no")) {
					NoService ns = new NoService(jtp, ht2.get("no"), ht,
							tabbedPane_3);
					ht.putAll(ns.noService(0));
				} else if (next.equals("data")) {
					Hashtable<Integer, Color> temp = new Hashtable<Integer, Color>();
					Regex dial = new Regex(
							"> SETUP_DATA_CALL|< SETUP_DATA_CALL|" +
							"> DEACTIV|< DEACTIV|> RADIO_POWER|" +
							"< RADIO_POWER|< UNSOL_DATA_CALL_LIST_CHANGED");
					StringFilter sf1 = new StringFilter(jtp, ht2.get("data"),
							next, tabbedPane_3);
					for(String a: dial.pattern()){
						temp.putAll(sf1.lookup1(a));
						ht.putAll(sf1.lookup1(a));
					}
					JTextPane allText = sf1.getTextPane();
					int[] array = new int[temp.size()];
					Set<Integer> set = temp.keySet();
					Iterator<Integer> iterator = set.iterator();
					int counter = 0;
					while(iterator.hasNext()){
						array[counter] = iterator.next();
						counter++;
					}
					Arrays.sort(array);
					for(int i : array){
						OutputParser.appendToPane(
								allText,
								ht2.get("data"),
								log.substring(log.lastIndexOf('\n', i) + 1,
										log.indexOf('\n', i))
										+ "\n");
					}
				} else if (next.equals("dial")) {
					Hashtable<Integer, Color> temp = new Hashtable<Integer, Color>();
					Regex dial = new Regex(
							"< LAST_CALL_FAIL_CAUSE|> ANSWER|> DIAL|< DIAL|> HANGUP|"+
							"< HANGUP|< GET_CURRENT_CALL|Number sent|< UNSOL_CALL_RING|"+
							"< UNSOL_CDMA_CALL_WAITING|> CDMA_FLASH");
					StringFilter sf1 = new StringFilter(jtp, ht2.get("dial"),
							next, tabbedPane_3);
					for(String a: dial.pattern()){
						temp.putAll(sf1.lookup1(a));
						ht.putAll(sf1.lookup1(a));
					}
					
					JTextPane allText = sf1.getTextPane();
					int[] array = new int[temp.size()];
					Set<Integer> set = temp.keySet();
					Iterator<Integer> iterator = set.iterator();
					int counter = 0;
					while(iterator.hasNext()){
						array[counter] = iterator.next();
						counter++;
					}
					Arrays.sort(array);
					for(int i : array){
						OutputParser.appendToPane(
								allText,
								ht2.get("dial"),
								log.substring(log.lastIndexOf('\n', i) + 1,
										log.indexOf('\n', i))
										+ "\n");
					}
				} else if (next.equals("radio")) {
					RadioChange rc = new RadioChange(jtp, ht2.get("radio"), ht,
							tabbedPane_3);
					int mem;
					if (log.indexOf("VOICE_REGISTRATION_STATE {") != -1) {
						mem = RadioChange.lastTech(log.substring(log
								.indexOf("VOICE_REGISTRATION_STATE {")), 10);
						ht.putAll(rc.find());
					}
				} else if (next.equals("qmi")) { // qmi is put in a separate
													// pane.
					QMITimeout qmi = new QMITimeout(jtp);

					// Pairs of qmi request and response
					ArrayList<String[]> arr = qmi.recordQMI(Integer
							.parseInt(jft.getText()));
					Iterator<String[]> qmiIter = arr.iterator();
					int counter = 1;

					// Parses and formats the pairs of resquest and responses in
					// a separate JTextPane
					while (qmiIter.hasNext()) {
						String[] str = qmiIter.next();
						text += Integer.toString(counter)
								+ ". \n "
								+ StringFilter.getLine(log,
										Integer.parseInt(str[0])) + '\n' + '\n';
						text += StringFilter.getLine(log,
								Integer.parseInt(str[1]))
								+ '\n' + '\n' + '\n' + '\n';
						counter++;
					}

					// Outputs Cut log is log is verified to be incomplete.
					if (qmi.cutLog()) {
						text = "!!!!!!!Cut Log!!!!!!!!" + '\n' + '\n'
								+ "[Request] without response = \n \n" + text;
					}
					jtp1.setText(text);
					jsp.setViewportView(jtp1);
				} else {
					StringFilter sf = new StringFilter(jtp, ht2.get(next),
							next, tabbedPane_3);
					ht.putAll(sf.lookup(next));
				}
			}

			// Checks for fatal errors.
			if (log.length() > 0) {
//				StringFilter fatal = new StringFilter(jtp, "Modem is RESET");
				accumulator = getString(StringFilter.lookupArr("Modem is RESET", log),
						accumulator, "Modem Crash");
//				StringFilter fatal1 = new StringFilter(jtp, " rild ");
				accumulator = getString(StringFilter.lookupArr("rild", log), accumulator,
						"Rild Crash");
//				StringFilter fatal2 = new StringFilter(jtp, "phone died");
				accumulator = getString(StringFilter.lookupArr("phone died",log),
						accumulator, "Phone Crash");
				jtp2.setText(accumulator);
				jsp1.setViewportView(jtp2);
			}
			// from.clear();
			// to.clear();
			
			JScrollPane temp = new JScrollPane();
			temp.setName("All Results");
			temp.setViewportView(jtp3);
			tabbedPane_3.add(temp, 0);
			
			int[] array1 = new int[ht.size()];
			Set<Integer> set = ht.keySet();
			Iterator<Integer> iterator = set.iterator();
			int counter1 = 0;
			while(iterator.hasNext()){
				array1[counter1] = iterator.next();
				counter1++;
			}
			Arrays.sort(array1);
			for(int i : array1){
				appendToPane(
						jtp3,
						ht.get(i),
						log.substring(log.lastIndexOf('\n', i) + 1,
								log.indexOf('\n', i))
								+ "\n");
			}
			
			jtp3.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					int x = me.getX();
					int y = me.getY();
					int startOffset = jtp3.viewToModel(new Point(x, y));
					String text = jtp3.getText();
					if (startOffset != -1) {
						String z = StringFilter.getLine(text, startOffset);
						System.out.println("line: " + z);
						int w = log.indexOf(z.trim());
						System.out.println(w);

						jtp.setCaretPosition(w);
					}
				}
			});
			
			counter++;
		}
	}
}