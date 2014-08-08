import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Hashtable<String, JCheckBox> checkboxarr;
	private Config config; 
	/**
	 * @param keyword 
	 * @param ht 
	 * @param checkboxarr 
	 * @wbp.parser.entryPoint
	 */
	public PopupMenu(JPanel jpane, JCheckBox chckbxNewCheckBox,
			Hashtable<String, Color> ht, String keyword, 
			Hashtable<String, JCheckBox> checkboxarr, Config config) {
		this.jpane = jpane;
		this.chckbxNewCheckBox = chckbxNewCheckBox;
		this.ht = ht;
		this.keyword = keyword; 
		this.checkboxarr = checkboxarr;
		this.config = config;
		
		if(!keyword.equals("dial")&&!keyword.equals("data")&&
				!keyword.equals("no")&&!keyword.equals("radio")&&
				!keyword.equals("qmi")){
			menu.add(item);
		}
		menu.add(item1);
		ColorPane key = new ColorPane(chckbxNewCheckBox, ht, keyword, config);
		item1.addActionListener(key);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			menu.show(e.getComponent(), e.getX(), e.getY());
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