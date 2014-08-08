import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;

public class FileOpener implements ActionListener {
	protected JTabbedPane tabbedPane;
	// private String tabName = "";
	protected JTextPane textArea;
	protected StyledDocument log;
	private Hashtable<String, Color> ht = new Hashtable<String,Color>(); // loading configuration file
	private File f = new File(System.getProperty("user.dir")
			+ "\\src\\config.txt");
	protected Config config;
	// Keeps a copy of all the checkboxes for further manipulation later.
	private Hashtable<String, JCheckBox> checkboxarr;
	private JTextField txtKeyword;
	private JPanel jpane;

	private StringBuilder sb1; //for configurationfile
	private Hashtable<String, Color> ht1; //Issue and color hashtable

	public FileOpener(JTabbedPane tabbedPane, JTextPane textArea,
			Hashtable<String, JCheckBox> checkboxarr, JPanel jpane,
			Config config, JTextField txtKeyword, StringBuilder sb1, 
			Hashtable<String, Color> ht1) {
		this.tabbedPane = tabbedPane;
		this.textArea = textArea;
		log = textArea.getStyledDocument();
		this.checkboxarr = checkboxarr;
		this.jpane = jpane;
		this.config = config;
		this.txtKeyword = txtKeyword;
		this.sb1 = sb1; 
		this.ht1 = ht1; 
	}

	public void readConfig() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			
			String line = br.readLine();
//			line = br.readLine();

			while (line != null) {
				sb1.append(line);
				sb1.append("\n");
				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}
	

	public String readFile(File fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder(); //for file text
		try {
			
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public void update() throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		try {
			String line = br1.readLine();
			while (line != null) {
				if (line.indexOf("r=") != -1) {
					int[] g = getColor(line);
					ht.put(line.substring(0, line.indexOf(',')), new Color(
							g[0], g[1], g[2]));
				} else {
					ht.put(line.substring(0, line.indexOf(',')), new Color(0,0,0));
				}
				line = br1.readLine();
			}
		} finally {
			br1.close();
		}
	}

	// helper method
	// Color: java.awt.Color[r=0,g=0,b=0]
	public static int[] getColor(String text) {
		int[] rgb = new int[3];
		String x, y, z;
		x = text.substring(text.indexOf("r=") + 2,
				text.indexOf(',', text.indexOf("r=") + 2));
		y = text.substring(text.indexOf("g=") + 2,
				text.indexOf(',', text.indexOf("g=") + 2));
		z = text.substring(text.indexOf("b=") + 2,
				text.indexOf(']', text.indexOf("b=") + 2));
		rgb[0] = Integer.parseInt(x);
		rgb[1] = Integer.parseInt(y);
		rgb[2] = Integer.parseInt(z);
		return rgb;
	}
	
	
	public void enable(){
		for(JCheckBox jcb : checkboxarr.values()){
			jcb.setEnabled(true);
		}
		txtKeyword.setEditable(true);
		try {
			readConfig();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	public void setup() throws IOException{
		textArea.removeAll();
		String x = "";
		
		JFileChooser chooser = new JFileChooser(config.getDir());
		chooser.setDragEnabled(true);
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			for (File w : chooser.getSelectedFiles()) {
				try {
					x = x
							+ "File Name: "
							+ w.getName()
							+ '\n'
							+ '\n'
							+ readFile(w)
							+ "=====================================End Of File===================================="
							+ '\n';
					// tabName = w.getName().substring(0,
					// w.getName().indexOf('_'));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				config.setDir(w.getPath());
			}
			if (log.getLength() > 0) {
				tabbedPane.removeAll();
			}
			textArea.setText(x);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(textArea);
			tabbedPane.addTab("radio log", scrollPane);
		}
	}

	public void setupCheck(){
		if (!ht.isEmpty()) {
			// loading configuration when first booted up.
			Set<String> set = ht.keySet();
			Iterator<String> iter = set.iterator();
			while (iter.hasNext()) {
				final String keyword = iter.next();
				// loading non default checkboxes into jpane.
				if (!keyword.equals("dial") && !keyword.equals("data")
						&& !keyword.equals("no") && !keyword.equals("radio")
						&& !keyword.equals("qmi")) {
					final JCheckBox chckbxNewCheckBox = new JCheckBox(keyword);
					GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
					gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_chckbxNewCheckBox.gridy = 0;

					chckbxNewCheckBox.setForeground(ht.get(keyword));
					jpane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
					jpane.updateUI();
					chckbxNewCheckBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (chckbxNewCheckBox.isSelected()) {
								ht1.put(keyword, chckbxNewCheckBox.getForeground());
							} else {
								ht1.remove(keyword);
							}
						}
					});

					PopupMenu pm = new PopupMenu(jpane, chckbxNewCheckBox, ht,
							keyword, checkboxarr, config);
					chckbxNewCheckBox.addMouseListener(pm);
				} else { // default 5 checkboxes along with colors.
					checkboxarr.get(keyword).setForeground(ht.get(keyword));
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		enable();
		
		try {
			setup();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			update();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setupCheck();
	}
}