import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddKeyword implements ActionListener {
	private JPanel jpane;
	private String keyword;
	private JTextField jtf;
	private Hashtable<String, Color> ht;
	private Hashtable<String, JCheckBox> checkboxarr;
	private Config config;
	private JFormattedTextField jft;
	
	/**
	 * @param jpane
	 * @param jtf
	 * @param ht
	 * @param checkboxarr
	 * @param config
	 * @param jft
	 * This class constructs a new keyword whenever a user inputs a 
	 * new keyword and presses enter. The keyword is placed in the 
	 * JPanel in a ScrollPane. 
	 */
	public AddKeyword(JPanel jpane, JTextField jtf,
			Hashtable<String, Color> ht,
			Hashtable<String, JCheckBox> checkboxarr, Config config,
			JFormattedTextField jft) {
		this.jpane = jpane;
		this.jtf = jtf;
		this.ht = ht;
		this.checkboxarr = checkboxarr;
		this.config = config;
		this.jft = jft;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//keep a copy of the text. Assigns the field keyword to the 
		//current text in jtextpane.
		keyword = jtf.getText();			

		
		//Constructs the checkbox along with the constraints of the boxes.
		final JCheckBox chckbxNewCheckBox = new JCheckBox(keyword);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridy = 0;
		
		//Add checkboxes into the JPanel along with the constraints. 
		jpane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		jpane.updateUI();
		
		
		try {
			//whenever a new keyword is entered, update configuration file.
			config.update(keyword);
		}catch (IOException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Assign an actionlistener for every new checkbox(keyword) entered. 
		//Whenever a checkbox is checked, update the hashtable which 
		//contains all the keywords to be filtered. 
		//If checkbox is unchecked, remove from hashtable. 
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					ht.put(keyword, chckbxNewCheckBox.getForeground());
				} else {
					ht.remove(keyword);
				}
			}
		});
		
		//Instantiate popup menu objects, which gives every checkbox the
		//option of choosing filter color, or deleting the checkbox. 
		PopupMenu pm = new PopupMenu(jpane, chckbxNewCheckBox, ht, keyword,
				checkboxarr, config, jft);
		chckbxNewCheckBox.addMouseListener(pm);
	}
}