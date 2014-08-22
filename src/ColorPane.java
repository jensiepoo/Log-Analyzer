import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class ColorPane implements ActionListener {
	private JCheckBox jcheck;
	private String keyword;
	private Hashtable<String, Color> ht;
	private Config config;


	/**
	 * @param jcheck
	 * @param ht
	 * @param keyword
	 * @param config
	 * 	This class gives the user the option of choosing a filter color
	 * for the new keyword entered. Whenever the checkbox is checked
	 * Add the keyword and filter to the configuration file and 
	 * hashtable for the list of strings to be filtered. 
	 * Whenever the checkbox is unchecked, remove from hashtable. 
	 */
	public ColorPane(JCheckBox jcheck, Hashtable<String, Color> ht,
			String keyword, Config config) {
		this.jcheck = jcheck;
		this.ht = ht;
		this.keyword = keyword;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//This constructs a new window of color chooser, which allows 
		//the user to choose the color for that particular string filter.
		JFrame guiFrame = new JFrame();
		Color selectedColor = JColorChooser.showDialog(guiFrame,
				"Pick a Color", Color.black);
		jcheck.setForeground(selectedColor);

		//if the user chose a color, update the configuration file along with
		//its color filter.
		if (selectedColor != null) {
			try {
				config.update(keyword, selectedColor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // keyword,
		} else {
			try {
				config.update(keyword);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // item without selected color
		}
		
		//if the checkbox is unchecked, remove from hashtable. 
		if (jcheck.isSelected()) {
			ht.put(keyword, jcheck.getForeground());
		} else {
			ht.remove(keyword);
		}
	}
}
