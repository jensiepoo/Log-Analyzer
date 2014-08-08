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

	public ColorPane(JCheckBox jcheck, Hashtable<String, Color> ht,
			String keyword, Config config) {
		this.jcheck = jcheck;
		this.ht = ht;
		this.keyword = keyword;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame guiFrame = new JFrame();
		Color selectedColor = JColorChooser.showDialog(guiFrame,
				"Pick a Color", Color.black);
		jcheck.setForeground(selectedColor);

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
		if (jcheck.isSelected()) {
			ht.put(keyword, jcheck.getForeground());
		} else {
			ht.remove(keyword);
		}
	}
}
