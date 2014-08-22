import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpener implements ActionListener {
	protected JTabbedPane tabbedPane;
	// private String tabName = "";

	private Hashtable<String, Color> ht = new Hashtable<String, Color>(); // loading
																			// configuration
																			// file
	private File f = new File(System.getProperty("user.dir")
			+ "\\config.txt"); //\\src
	protected Config config;
	
	// Keeps a copy of all the checkboxes for further manipulation later.
	private Hashtable<String, JCheckBox> checkboxarr;
	private JTextField txtKeyword;
	private JPanel jpane;
	
	private StringBuilder sb1; // for configurationfile
	private Hashtable<String, Color> ht1; // Issue and color hashtable
	private ArrayList<String> text = new ArrayList<String>();
	private int counter; // used to check how many times file opener has been
							// clicked.
	private JTextPane textArea = new JTextPane();
	private JFormattedTextField from, to, jft;

	
	/**
	 * @param tabbedPane
	 * @param textArea
	 * @param checkboxarr
	 * @param jpane
	 * @param config
	 * @param txtKeyword
	 * @param sb1
	 * @param ht1
	 * @param from
	 * @param to
	 * @param jft
	 * @throws IOException
	 * Constructs a fileopener, and prints out the text into the scrollpane
	 * in the tabbedpane. 
	 */
	public FileOpener(JTabbedPane tabbedPane, JTextPane textArea,
			Hashtable<String, JCheckBox> checkboxarr, JPanel jpane,
			Config config, JTextField txtKeyword, StringBuilder sb1,
			Hashtable<String, Color> ht1, JFormattedTextField from,
			JFormattedTextField to, JFormattedTextField jft) throws IOException {
		this.tabbedPane = tabbedPane;
		this.checkboxarr = checkboxarr;
		this.jpane = jpane;
		this.config = config;
		this.txtKeyword = txtKeyword;
		this.sb1 = sb1;
		this.ht1 = ht1;
		this.from = from;
		this.to = to;
		this.jft = jft;
		this.textArea = textArea;
		
//		f.createNewFile();
	}

	/**
	 * @param fileName
	 * @return Text read from a particular file. 
	 * @throws IOException
	 */
	public String readFile(File fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder(); // for file text
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
	
	
	/**
	 * @throws IOException
	 * Given the configuration file, get the color and keyword, and place
	 * into the hashtable of keywords along with color for other methods to 
	 * look up.
	 */
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
					ht.put(line.substring(0, line.indexOf(',')), new Color(0,
							0, 0));
				}
				line = br1.readLine();
			}
		} finally {
			br1.close();
		}
	}

	
	/**
	 * @param text
	 * @return int list of RGB parsed from the format [r=x, g=y, b=z].
	 */
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

	/**
	 * Enables the default checkboxes and time frame setting. 
	 */
	public void enable() {
		for (JCheckBox jcb : checkboxarr.values()) {
			jcb.setEnabled(true);
		}
		txtKeyword.setEnabled(true);
		from.setEnabled(true);
		to.setEnabled(true);
		// try {
		// readConfig();
		// } catch (IOException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
	}

	
	/**
	 * @throws IOException
	 * 
	 * This method creates a file chooser
	 * gets all the files and tab the text into the scrollpane. 
	 * 
	 */
	public void setup() throws IOException {
		String x = ""; //constructs the text
		
		//FileChooser with the default directory set to the directory 
		//last opened. 
		JFileChooser chooser = new JFileChooser(config.getDir());
		
		chooser.setDragEnabled(true);
		
		//allows multiple file import. 
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"txt files", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			for (File w : chooser.getSelectedFiles()) {
				try {
					x = readFile(w);
					//creates a new textpane for every txt file. 
					JTextPane jtp = new JTextPane();
					jtp.setText(x);
					
					//creates a new scrollpane for every txt file. 
					JScrollPane scrollPane = new JScrollPane();

					scrollPane.setViewportView(jtp);
					scrollPane.setName(w.getName());
					
					jtp.setCaretPosition(0);
					jtp.getCaret().setVisible(true);
					//add a new tab
					tabbedPane.addTab(w.getName(), scrollPane);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				config.setDir(w.getPath());	//update the last-opened directory
			}
		}
	}

	
	/**
	 * Updates the fields during the first file load. Read the configuration file
	 * and loads all the checkboxes in the config file. 
	 */
	public void setupCheck() {
		if (!ht.isEmpty()) {
			// loading configuration when first booted up.
			Set<String> set = ht.keySet();
			Iterator<String> iter = set.iterator();
			while (iter.hasNext()) {
				final String keyword = iter.next();
				
				// loading non default checkboxes(other than the 5)into jpane.
				if (!keyword.equals("dial") && !keyword.equals("data")
						&& !keyword.equals("no") && !keyword.equals("radio")
						&& !keyword.equals("qmi")) {
					final JCheckBox chckbxNewCheckBox = new JCheckBox(keyword);
					GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
					gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_chckbxNewCheckBox.gridy = 0;

					//update colors of checkboxes. 
					chckbxNewCheckBox.setForeground(ht.get(keyword));
					jpane.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
					jpane.updateUI();
					chckbxNewCheckBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (chckbxNewCheckBox.isSelected()) {
								ht1.put(keyword,
										chckbxNewCheckBox.getForeground());
							} else {
								ht1.remove(keyword);
							}
						}
					});

					PopupMenu pm = new PopupMenu(jpane, chckbxNewCheckBox, ht,
							keyword, checkboxarr, config, jft);
					chckbxNewCheckBox.addMouseListener(pm);
				} else { // default 5 checkboxes along with colors.
					checkboxarr.get(keyword).setForeground(ht.get(keyword));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jft.setEnabled(true);
		enable();
		
		try {
			setup();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (counter == 0) {
			try {
				update();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setupCheck();
		}
		counter++;
	}
}