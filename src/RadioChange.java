import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class RadioChange {
	private Hashtable<Integer, Color> ht = new Hashtable<Integer, Color>();
	private String log;
	private Color color;
	private JTextPane textArea;
	private JTabbedPane tabbedPane_3;
	private JTextPane allText = new JTextPane();

	public RadioChange(final JTextPane textArea, Color color,
			Hashtable<Integer, Color> ht, JTabbedPane tabbedPane_3) {
		this.textArea = textArea;
		this.ht = ht;
		log = textArea.getText();
		this.color = color;
		this.tabbedPane_3 = tabbedPane_3;

		JScrollPane temp = new JScrollPane();
		temp.setViewportView(allText);
		tabbedPane_3.addTab("Radio Change", temp);

		allText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				int startOffset = allText.viewToModel(new Point(x, y));
				System.out.println("offset " + startOffset);
				String text = allText.getText();
				if (startOffset != -1) {
					String z = StringFilter.getLine(text, startOffset);
					// System.out.println("line: " + z);
					int w = log.indexOf(z.trim());
					// System.out.println(w);

					textArea.setCaretPosition(w);
//					temp.set
				}	
			}
		});
	};

	/**
	 * @param fromIndex
	 * @param mem
	 * @return an ArrayList of Integers where radio technology changes take
	 *         place.
	 */
	public Hashtable<Integer, Color> find() {
		int fromIndex, mem;
		fromIndex = 0;
		mem = lastTech(
				log.substring(log.indexOf("VOICE_REGISTRATION_STATE {")), 10);
		int x;
		while (log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex) != -1) {
			x = log.indexOf("VOICE_REGISTRATION_STATE {", fromIndex);
			String y = log.substring(x);
			int z = lastTech(y, 10);
			if (z != mem) {
				ht.put(x, color);
				OutputParser.setJTextPaneFont(textArea, color,
						log.lastIndexOf('\n', x) + 1,
						StringFilter.getLine(log, x).length());
				OutputParser.appendToPane(
						allText,
						color,
						log.substring(log.lastIndexOf('\n', x) + 1,
								log.indexOf('\n', x))
								+ "\n");
				mem = z;
				fromIndex = x + 1;
			} else {
				fromIndex = x + 1;
			}
		}
		return ht;
	}

	/**
	 * @param text
	 * @return the technology stored VOICE_REGISTRATION_STATE {}'s 11th index.
	 *         Note: call with counter = 10.
	 */
	public static int lastTech(String text, int counter) {
		if (counter == 0) {
			int counter1 = 0;
			int value = 0;
			while (text.charAt(counter1) == ' ') {
				counter1++;
			}
			value = Integer
					.parseInt(text.substring(counter1, text.indexOf(',')));
			return value;
		} else {
			counter = counter - 1;
			return lastTech(text.substring(text.indexOf(',') + 1), counter);
		}
	}
}
