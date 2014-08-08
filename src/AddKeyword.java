import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddKeyword implements ActionListener {
	private JPanel jpane;
	private String keyword;
	private JTextField jtf;
	private Hashtable<String, Color> ht;
	private Hashtable<String, JCheckBox> checkboxarr;
	private Config config;

	public AddKeyword(JPanel jpane, JTextField jtf,
			Hashtable<String, Color> ht,
			Hashtable<String, JCheckBox> checkboxarr, Config config) {
		this.jpane = jpane;
		this.jtf = jtf;
		this.ht = ht;
		this.checkboxarr = checkboxarr;
		this.config = config;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		keyword = jtf.getText();

		final JCheckBox chckbxNewCheckBox = new JCheckBox(keyword);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridy = 0;
		
		jpane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		jpane.updateUI();
		try {
			config.update(keyword);
		}catch (IOException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					ht.put(keyword, chckbxNewCheckBox.getForeground());
				} else {
					ht.remove(keyword);
				}
			}
		});
		PopupMenu pm = new PopupMenu(jpane, chckbxNewCheckBox, ht, keyword,
				checkboxarr, config);
		chckbxNewCheckBox.addMouseListener(pm);
	}
}