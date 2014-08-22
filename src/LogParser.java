import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.dnd.DropTarget;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.event.FocusEvent;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

public class LogParser extends JFrame {
	private JTextArea txtrSdfgasdfg;
	private JMenuBar menuBar;
	private JTextField txtKeyword;
	// ArrayList used to store whether or not certain issues have to be
	// analyzed.
	private Hashtable<String, Color> ht = new Hashtable<String, Color>();
	private Hashtable<String, JCheckBox> checkboxarr = new Hashtable<String, JCheckBox>();
	private StringBuilder sb = new StringBuilder();
	private Config config = new Config();
	private ArrayList<String> from = new ArrayList<String>();
	private ArrayList<String> to = new ArrayList<String>();
	private JTabbedPane tabbedPane;
	private JFormattedTextField jft;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFileChooser j = new JFileChooser();
		j.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogParser frame = new LogParser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @throws IOException
	 */
	public LogParser() throws IOException {
		JFormattedTextField jft = new JFormattedTextField();
		jft.setEnabled(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\Jensen_Kuo\\Desktop\\HTC-Logo-350x350 (1).jpg"));
		setMinimumSize(new Dimension(700, 640));
		setTitle("Log Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 533);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File ");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 462, -45, 30, };
		gridBagLayout.rowHeights = new int[] { 29, 26, 30, 0, 30, 30, 30, 30,
				30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		;
		;
		;
		;

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 3;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 0;

		getContentPane().add(scrollPane_3, gbc_scrollPane_3);
		
		

		// JPanel panel_1 = new JPanel();
		//
		// GridBagLayout gbl_panel_1 = new GridBagLayout();
		// gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		// gbl_panel_1.rowHeights = new int[] { 0, 0 };
		// gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
		// Double.MIN_VALUE };
		// gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		// panel_1.setLayout(gbl_panel_1);


		final JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 8;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		// getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 80, 74, 80, 115, 97, 81, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 12, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);
		scrollPane_3.setViewportView(panel_1);

		final JCheckBox chckbxDialFlow = new JCheckBox("Dial Flow");
		checkboxarr.put("dial", chckbxDialFlow);
		chckbxDialFlow.setEnabled(false);
		GridBagConstraints gbc_chckbxDialFlow = new GridBagConstraints();
		gbc_chckbxDialFlow.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDialFlow.gridx = 0;
		gbc_chckbxDialFlow.gridy = 0;
		panel_1.add(chckbxDialFlow, gbc_chckbxDialFlow);
		chckbxDialFlow.setMinimumSize(new Dimension(80, 23));
		PopupMenu dial = new PopupMenu(panel_1, chckbxDialFlow, ht, "dial",
				checkboxarr, config, jft);
		chckbxDialFlow.addMouseListener(dial);
		chckbxDialFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDialFlow.isSelected()) {
					ht.put("dial", chckbxDialFlow.getForeground());
				} else {
					ht.remove("dial");
				}
			}
		});

		final JCheckBox chckbxDataCall = new JCheckBox("Data Call");
		checkboxarr.put("data", chckbxDataCall);
		chckbxDataCall.setEnabled(false);
		GridBagConstraints gbc_chckbxDataCall = new GridBagConstraints();
		gbc_chckbxDataCall.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDataCall.gridx = 1;
		gbc_chckbxDataCall.gridy = 0;
		panel_1.add(chckbxDataCall, gbc_chckbxDataCall);
		PopupMenu data = new PopupMenu(panel_1, chckbxDataCall, ht, "data",
				checkboxarr, config, jft);
		chckbxDataCall.addMouseListener(data);
		chckbxDataCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxDataCall.isSelected()) {
					ht.put("data", chckbxDataCall.getForeground());
				} else {
					ht.remove("data");
				}
			}
		});

		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("No Service");
		checkboxarr.put("no", chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setEnabled(false);
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_1.gridx = 2;
		gbc_chckbxNewCheckBox_1.gridy = 0;
		panel_1.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		PopupMenu no = new PopupMenu(panel_1, chckbxNewCheckBox_1, ht, "no",
				checkboxarr, config, jft);
		chckbxNewCheckBox_1.addMouseListener(no);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_1.isSelected()) {
					ht.put("no", chckbxNewCheckBox_1.getForeground());
				} else {
					ht.remove("no");
				}
			}
		});
		;

		final JCheckBox chckbxRadioTechChange = new JCheckBox(
				"Radio Tech Change");
		checkboxarr.put("radio", chckbxRadioTechChange);
		chckbxRadioTechChange.setEnabled(false);
		GridBagConstraints gbc_chckbxRadioTechChange = new GridBagConstraints();
		gbc_chckbxRadioTechChange.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxRadioTechChange.gridx = 3;
		gbc_chckbxRadioTechChange.gridy = 0;
		panel_1.add(chckbxRadioTechChange, gbc_chckbxRadioTechChange);
		PopupMenu radio = new PopupMenu(panel_1, chckbxRadioTechChange, ht,
				"radio", checkboxarr, config, jft);
		chckbxRadioTechChange.addMouseListener(radio);
		chckbxRadioTechChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxRadioTechChange.isSelected()) {
					ht.put("radio", chckbxRadioTechChange.getForeground());
				} else {
					ht.remove("radio");
				}
			}
		});
		;

		final JCheckBox chckbxNewCheckBox = new JCheckBox("QMI Timeout");
		checkboxarr.put("qmi", chckbxNewCheckBox);
		chckbxNewCheckBox.setEnabled(false);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 4;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		PopupMenu qmi = new PopupMenu(panel_1, chckbxNewCheckBox, ht, "qmi",
				checkboxarr, config, jft);
		chckbxNewCheckBox.addMouseListener(qmi);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					ht.put("qmi", chckbxNewCheckBox.getForeground());
				} else {
					ht.remove("qmi");
				}
			}
		});

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 30, 30, 30, 30, 30, 35, 0, 30, 30,
				30, 30, 30, 35, 110, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

	
		final JFormattedTextField frmtdtxtfldMon = new JFormattedTextField();
		frmtdtxtfldMon.setEnabled(false);
		frmtdtxtfldMon.setText("Mon");
		frmtdtxtfldMon.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldMon.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldMon = new GridBagConstraints();
		gbc_frmtdtxtfldMon.insets = new Insets(0, 2, 0, 5);
		gbc_frmtdtxtfldMon.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMon.gridx = 0;
		gbc_frmtdtxtfldMon.gridy = 0;
		panel.add(frmtdtxtfldMon, gbc_frmtdtxtfldMon);

		final JFormattedTextField frmtdtxtfldDay = new JFormattedTextField();
		frmtdtxtfldDay.setEnabled(false);
		frmtdtxtfldDay.setText("Day");
		frmtdtxtfldDay.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldDay.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldDay = new GridBagConstraints();
		gbc_frmtdtxtfldDay.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldDay.gridx = 1;
		gbc_frmtdtxtfldDay.gridy = 0;
		panel.add(frmtdtxtfldDay, gbc_frmtdtxtfldDay);

		final JFormattedTextField frmtdtxtfldHr = new JFormattedTextField();
		frmtdtxtfldHr.setEnabled(false);
		frmtdtxtfldHr.setText("Hr");
		frmtdtxtfldHr.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldHr.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldHr = new GridBagConstraints();
		gbc_frmtdtxtfldHr.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldHr.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldHr.gridx = 2;
		gbc_frmtdtxtfldHr.gridy = 0;
		panel.add(frmtdtxtfldHr, gbc_frmtdtxtfldHr);

		final JFormattedTextField frmtdtxtfldMin = new JFormattedTextField();
		frmtdtxtfldMin.setEnabled(false);
		frmtdtxtfldMin.setText("Min");
		frmtdtxtfldMin.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldMin.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldMin = new GridBagConstraints();
		gbc_frmtdtxtfldMin.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMin.gridx = 3;
		gbc_frmtdtxtfldMin.gridy = 0;
		panel.add(frmtdtxtfldMin, gbc_frmtdtxtfldMin);

		final JFormattedTextField frmtdtxtfldSec = new JFormattedTextField();
		frmtdtxtfldSec.setEnabled(false);
		frmtdtxtfldSec.setText("Sec");
		frmtdtxtfldSec.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldSec.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldSec = new GridBagConstraints();
		gbc_frmtdtxtfldSec.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldSec.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldSec.gridx = 4;
		gbc_frmtdtxtfldSec.gridy = 0;
		panel.add(frmtdtxtfldSec, gbc_frmtdtxtfldSec);

		final JFormattedTextField frmtdtxtfldMs = new JFormattedTextField();
		frmtdtxtfldMs.setEnabled(false);
		frmtdtxtfldMs.setText("Ms");
		frmtdtxtfldMs.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				frmtdtxtfldMs.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_frmtdtxtfldMs = new GridBagConstraints();
		gbc_frmtdtxtfldMs.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldMs.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMs.gridx = 5;
		gbc_frmtdtxtfldMs.gridy = 0;
		panel.add(frmtdtxtfldMs, gbc_frmtdtxtfldMs);

		JLabel lblTo = new JLabel("to");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTo.anchor = GridBagConstraints.EAST;
		gbc_lblTo.gridx = 6;
		gbc_lblTo.gridy = 0;
		panel.add(lblTo, gbc_lblTo);

		final JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setEnabled(false);
		formattedTextField.setText("Mon");
		formattedTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 7;
		gbc_formattedTextField.gridy = 0;
		panel.add(formattedTextField, gbc_formattedTextField);

		final JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setEnabled(false);
		formattedTextField_1.setText("Day");
		formattedTextField_1.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField_1.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_formattedTextField_1 = new GridBagConstraints();
		gbc_formattedTextField_1.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_1.gridx = 8;
		gbc_formattedTextField_1.gridy = 0;
		panel.add(formattedTextField_1, gbc_formattedTextField_1);

		final JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setEnabled(false);
		formattedTextField_2.setText("Hr");
		formattedTextField_2.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField_2.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_formattedTextField_2 = new GridBagConstraints();
		gbc_formattedTextField_2.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_2.gridx = 9;
		gbc_formattedTextField_2.gridy = 0;
		panel.add(formattedTextField_2, gbc_formattedTextField_2);

		final JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setEnabled(false);
		formattedTextField_3.setText("Min");
		formattedTextField_3.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField_3.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_formattedTextField_3 = new GridBagConstraints();
		gbc_formattedTextField_3.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_3.gridx = 10;
		gbc_formattedTextField_3.gridy = 0;
		panel.add(formattedTextField_3, gbc_formattedTextField_3);

		final JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setEnabled(false);
		formattedTextField_4.setText("Sec");
		formattedTextField_4.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField_4.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_formattedTextField_4 = new GridBagConstraints();
		gbc_formattedTextField_4.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_4.gridx = 11;
		gbc_formattedTextField_4.gridy = 0;
		panel.add(formattedTextField_4, gbc_formattedTextField_4);

		final JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setEnabled(false);
		formattedTextField_5.setText("Ms");
		formattedTextField_5.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTextField_5.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		GridBagConstraints gbc_formattedTextField_5 = new GridBagConstraints();
		gbc_formattedTextField_5.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_5.gridx = 12;
		gbc_formattedTextField_5.gridy = 0;
		panel.add(formattedTextField_5, gbc_formattedTextField_5);

		txtKeyword = new JTextField();
		txtKeyword.setEnabled(false);
		txtKeyword.setText("Enter keyword");
		txtKeyword.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtKeyword.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridBagConstraints gbc_txtKeyword = new GridBagConstraints();
		gbc_txtKeyword.anchor = GridBagConstraints.WEST;
		gbc_txtKeyword.gridx = 13;
		gbc_txtKeyword.gridy = 0;
		panel.add(txtKeyword, gbc_txtKeyword);
		txtKeyword.setColumns(10);
		AddKeyword akw = new AddKeyword(panel_1, txtKeyword, ht, checkboxarr,
				config, jft);
		txtKeyword.addActionListener(akw);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 10;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		getContentPane().add(tabbedPane, gbc_tabbedPane);

		final JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textPane);
		
	
		menuBar.add(mnFile);
		JMenuItem mntmOpenFile = new JMenuItem(
				"Open File...                            Ctrl + O");
		mnFile.add(mntmOpenFile);
		final FileOpener fo = new FileOpener(tabbedPane, textPane, checkboxarr,
				panel_1, config, txtKeyword, sb, ht, frmtdtxtfldMon,
				formattedTextField, jft);// , textPane
		
		
//		frmtdtxtfldDuration.
		GridBagConstraints gbc_frmtdtxtfldDuration = new GridBagConstraints();
		gbc_frmtdtxtfldDuration.insets = new Insets(3, 0, 0, 5);
		gbc_frmtdtxtfldDuration.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldDuration.gridx = 5;
		gbc_frmtdtxtfldDuration.gridy = 0;
		panel_1.add(jft, gbc_frmtdtxtfldDuration);
		mntmOpenFile.addActionListener(fo);

		JMenuItem mntmSaveFileCtrl = new JMenuItem(
				"Save As...                              Ctrl + S");
		final FileSaver fs = new FileSaver(tabbedPane, config);
		mntmSaveFileCtrl.addActionListener(fs);
		mnFile.add(mntmSaveFileCtrl);
		
		JMenuItem mntmFindKeywordCtrl = new JMenuItem("Find Keyword" +
				"...                     Ctrl + F");
		mnFile.add(mntmFindKeywordCtrl);
//		final FileSaver fs = new FileSaver(textPane);
//		mntmSaveFileCtrl.addActionListener(fs);
//		
//		
		
		

		// Create the drag and drop listener
		DropListener mydropper = new DropListener(tabbedPane, textPane,
				checkboxarr, panel, config, txtKeyword, sb, ht, frmtdtxtfldMon,
				formattedTextField, jft);
		// Connect the label with a drag and drop listener
		new DropTarget(tabbedPane, mydropper);
		
		
		
//		This block of code does the key bindings with control F and 
//		and control S for saving file, and control o
		final SearchPane sp = new SearchPane(tabbedPane);
		InputMap inputMap = scrollPane_3
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("control F"), "search");
		inputMap.put(KeyStroke.getKeyStroke("control O"), "open");
		inputMap.put(KeyStroke.getKeyStroke("control S"), "save");
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "exit");
		scrollPane_3.getActionMap().put("search", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {

				sp.setVisible(true);
			}
		});
		scrollPane_3.getActionMap().put("open", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				fo.actionPerformed(e);
			}
		});
		scrollPane_3.getActionMap().put("save", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				fs.actionPerformed(e);
			}
		});
		scrollPane_3.getActionMap().put("exit", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.gridwidth = 2;
		gbc_tabbedPane_1.gridheight = 11;
		gbc_tabbedPane_1.insets = new Insets(0, 0, 5, 3);
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 1;
		gbc_tabbedPane_1.gridy = 2;
		getContentPane().add(tabbedPane_1, gbc_tabbedPane_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane_1.addTab("QMI Timeout", null, scrollPane_1, null);

		JButton btnNewButton = new JButton("Analyze");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		getContentPane().add(btnNewButton, gbc_btnNewButton);

		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_2 = new GridBagConstraints();
		gbc_tabbedPane_2.gridwidth = 2;
		gbc_tabbedPane_2.gridheight = 6;
		gbc_tabbedPane_2.insets = new Insets(0, 0, 5, 3);
		gbc_tabbedPane_2.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_2.gridx = 1;
		gbc_tabbedPane_2.gridy = 13;
		getContentPane().add(tabbedPane_2, gbc_tabbedPane_2);

		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_3 = new GridBagConstraints();
		gbc_tabbedPane_3.gridheight = 7;
		gbc_tabbedPane_3.insets = new Insets(0, 0, 0, 5);
		gbc_tabbedPane_3.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_3.gridx = 0;
		gbc_tabbedPane_3.gridy = 12;
		getContentPane().add(tabbedPane_3, gbc_tabbedPane_3);

		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane_2.addTab("Fatal Errors", null, scrollPane_2, null);

		OutputParser output = new OutputParser(textPane, ht, scrollPane_1,
				scrollPane_2, from, to, tabbedPane, tabbedPane_3, jft);
		btnNewButton.addActionListener(output);

		frmtdtxtfldMon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldMon.getText();
				if (!a.equals("")) {
					from.add(0, a);
					frmtdtxtfldDay.setEnabled(true);
					frmtdtxtfldDay.requestFocus();
				}
			}
		});

		frmtdtxtfldDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldDay.getText();
				if (!a.equals("")) {
					from.add(1, a);
					frmtdtxtfldHr.setEnabled(true);
					frmtdtxtfldHr.requestFocus();
				}
			}

		});
		frmtdtxtfldHr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldHr.getText();
				if (!a.equals("")) {
					from.add(2, a);
					frmtdtxtfldMin.setEnabled(true);
					frmtdtxtfldMin.requestFocus();
				}
			}
		});
		frmtdtxtfldMin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldMin.getText();
				if (!a.equals("")) {
					from.add(3, a);
					frmtdtxtfldSec.setEnabled(true);
					frmtdtxtfldSec.requestFocus();
				}
			}
		});
		frmtdtxtfldSec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldSec.getText();
				if (!a.equals("")) {
					from.add(4, frmtdtxtfldSec.getText());
					frmtdtxtfldMs.setEnabled(true);
					frmtdtxtfldMs.requestFocus();
				}
			}
		});
		frmtdtxtfldMs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = frmtdtxtfldMs.getText();
				if (!a.equals("")) {
					from.add(5, a);
					formattedTextField.requestFocus();
				}
			}
		});

		formattedTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField.getText();
				if (!a.equals("")) {
					to.add(0, a);
					formattedTextField_1.setEnabled(true);
					formattedTextField_1.requestFocus();
				}
			}
		});
		formattedTextField_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField_1.getText();
				if (!a.equals("")) {
					to.add(1, a);
					formattedTextField_2.setEnabled(true);
					formattedTextField_2.requestFocus();
				}
			}
		});
		formattedTextField_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField_2.getText();
				if (!a.equals("")) {
					to.add(2, a);
					formattedTextField_3.setEnabled(true);
					formattedTextField_3.requestFocus();
				}
			}
		});
		formattedTextField_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField_3.getText();
				if (!a.equals("")) {
					to.add(3, a);
					formattedTextField_4.setEnabled(true);
					formattedTextField_4.requestFocus();
				}
			}
		});
		formattedTextField_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField_4.getText();
				if (!a.equals("")) {
					to.add(4, a);
					formattedTextField_5.setEnabled(true);
					formattedTextField_5.requestFocus();
				}
			}
		});
		formattedTextField_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String a = formattedTextField_5.getText();
				if (!a.equals("")) {
					to.add(5, a);
				}
			}
		});
	}
}
