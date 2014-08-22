import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.SwingUtilities;


public class PopupMenu implements MouseListener{
	private JPopupMenu menu = new JPopupMenu();
	private JPanel jpane;
	private JCheckBox chckbxNewCheckBox;
	private Hashtable<String, Color> ht;
	private String keyword;
	private JMenuItem item = new JMenuItem("Delete");
	private JMenuItem item1 = new JMenuItem("Choose Filter");
	private JFormattedTextField jft;
	private Hashtable<String, JCheckBox> checkboxarr;
	private Config config; 
	/**
	 * @param keyword 
	 * @param ht 
	 * @param checkboxarr 
	 * @wbp.parser.entryPoint
	 * Adds popupmenu for all the checkboxes. 
	 */
	public PopupMenu(JPanel jpane, final JCheckBox chckbxNewCheckBox,
			final Hashtable<String, Color> ht, String keyword, 
			Hashtable<String, JCheckBox> checkboxarr, Config config, 
			final JFormattedTextField jft) {
		this.jpane = jpane;
		this.chckbxNewCheckBox = chckbxNewCheckBox;
		this.ht = ht;
		this.keyword = keyword; 
		this.checkboxarr = checkboxarr;
		this.config = config;
		this.jft = jft;
		jft.setText("000000");
		jft.setToolTipText("Set the duration limit in ms for QMI TO. E.g. 50ms," +
				" and returns QMI pair that exceeds that time limit.");
		if(!keyword.equals("dial")&&!keyword.equals("data")&&
				!keyword.equals("no")&&!keyword.equals("radio")&&
				!keyword.equals("qmi")){
			menu.add(item);
		}
		if(!keyword.equals("qmi")){
			menu.add(item1);
		}

		if(keyword.equals("qmi")){
			menu.add(jft);
		}

		
		ColorPane key = new ColorPane(chckbxNewCheckBox, ht, keyword, config);
		item1.addActionListener(key);
		jft.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				jft.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			menu.show(e.getComponent(), e.getX(), e.getY());
			
			//When the delete option is pressed, delete the keyword from the
			//configuration file and the hashtable of keywords. 
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						config.delete(keyword);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jpane.remove(chckbxNewCheckBox);
					checkboxarr.remove(chckbxNewCheckBox);
					jpane.updateUI(); 
					ht.remove(keyword);
				}
			});
		}
	}
}