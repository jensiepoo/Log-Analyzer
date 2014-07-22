import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import java.awt.List;
import java.awt.ScrollPane;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Scrollbar;
import javax.swing.JSpinner;
import java.awt.Choice;
import java.awt.Checkbox;
import javax.swing.JList;
import javax.swing.Box;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.awt.Panel;


public class log_parser extends JFrame {
	private JTextArea txtrSdfgasdfg;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFileChooser j = new JFileChooser();
		j.setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log_parser frame = new log_parser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public log_parser() {
		setMinimumSize(new Dimension(700, 640));
		setTitle("Log Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 533);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File ");
		menuBar.add(mnFile);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		final TextArea textArea = new TextArea();
		
//		JScrollPane scrollPane = new JScrollPane();
//		tabbedPane.addtab
//		tabbedPane.addTab("New tab", null, scrollPane, null);
//		textArea.add(jsp);
//		textArea.setVisible(true);
		

		tabbedPane.addTab("New tab", null, textArea, null);
		FileOpener fo = new FileOpener(tabbedPane, textArea);
		JMenuItem mntmOpenFile = new JMenuItem("Open File...                         ");
		mntmOpenFile.addActionListener(fo);
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmSaveFileCtrl = new JMenuItem("Save As...                              Ctrl + S");
//		FileSaver fs = new FileSaver(textArea);
//		mntmSaveFileCtrl.addActionListener(fs);
		mnFile.add(mntmSaveFileCtrl);
		
		JMenu mnCustomize = new JMenu("Customize");
		menuBar.add(mnCustomize);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{480, 30, 30, 30, 30, 30, 30, 30, };
		gridBagLayout.rowHeights = new int[]{29, 26, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 512, 0, 0};
		gridBagLayout.columnWeights = new double[]{2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(700, 10));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{80, 74, 80, 115, 97, 0};
		gbl_panel_1.rowHeights = new int[]{24, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JCheckBox chckbxDialFlow = new JCheckBox("Dial Flow");
		chckbxDialFlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ModifiedFilter mf = new ModifiedFilter("Dial Flow");
			}
		});
		GridBagConstraints gbc_chckbxDialFlow = new GridBagConstraints();
		gbc_chckbxDialFlow.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDialFlow.gridx = 0;
		gbc_chckbxDialFlow.gridy = 0;
		panel_1.add(chckbxDialFlow, gbc_chckbxDialFlow);
		
		JCheckBox chckbxDataCall = new JCheckBox("Data Call");
		chckbxDataCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("datacall");                    // TO-DO
			}
		});
		GridBagConstraints gbc_chckbxDataCall = new GridBagConstraints();
		gbc_chckbxDataCall.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDataCall.gridx = 1;
		gbc_chckbxDataCall.gridy = 0;
		panel_1.add(chckbxDataCall, gbc_chckbxDataCall);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("No Service");
//		StringFilter sf = new StringFilter(textArea, "No Service"); 
//		chckbxNewCheckBox_1.addActionListener(sf);
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_1.gridx = 2;
		gbc_chckbxNewCheckBox_1.gridy = 0;
		panel_1.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		JCheckBox chckbxRadioTechChange = new JCheckBox("Radio Tech Change");
		GridBagConstraints gbc_chckbxRadioTechChange = new GridBagConstraints();
		gbc_chckbxRadioTechChange.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxRadioTechChange.gridx = 3;
		gbc_chckbxRadioTechChange.gridy = 0;
		panel_1.add(chckbxRadioTechChange, gbc_chckbxRadioTechChange);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("QMI Timeout");
		ModifiedFilter qmi = new ModifiedFilter(textArea, "QMI TO");
		chckbxNewCheckBox.addActionListener(qmi);

		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 4;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 30, 30, 30, 30, 35, 0, 30, 30, 30, 30, 30, 35, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JFormattedTextField frmtdtxtfldMon = new JFormattedTextField();
//		frmtdtxtfldMon.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				in
//				while(frmtdtxtfldMon.getText())
//				String a = textArea.getText().substring(
//				
//				
//				textArea.setText(
//			}
//		});
		frmtdtxtfldMon.setText("Mon");
		GridBagConstraints gbc_frmtdtxtfldMon = new GridBagConstraints();
		gbc_frmtdtxtfldMon.insets = new Insets(0, 2, 0, 5);
		gbc_frmtdtxtfldMon.anchor = GridBagConstraints.WEST;
		gbc_frmtdtxtfldMon.gridx = 0;
		gbc_frmtdtxtfldMon.gridy = 0;
		panel.add(frmtdtxtfldMon, gbc_frmtdtxtfldMon);
		
		JFormattedTextField frmtdtxtfldDay = new JFormattedTextField();
		frmtdtxtfldDay.setText("Day");
		GridBagConstraints gbc_frmtdtxtfldDay = new GridBagConstraints();
		gbc_frmtdtxtfldDay.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldDay.gridx = 1;
		gbc_frmtdtxtfldDay.gridy = 0;
		panel.add(frmtdtxtfldDay, gbc_frmtdtxtfldDay);
		
		JFormattedTextField frmtdtxtfldHr = new JFormattedTextField();
		frmtdtxtfldHr.setText("Hr");
		GridBagConstraints gbc_frmtdtxtfldHr = new GridBagConstraints();
		gbc_frmtdtxtfldHr.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldHr.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldHr.gridx = 2;
		gbc_frmtdtxtfldHr.gridy = 0;
		panel.add(frmtdtxtfldHr, gbc_frmtdtxtfldHr);
		
		JFormattedTextField frmtdtxtfldMin = new JFormattedTextField();
		frmtdtxtfldMin.setText("Min");
		GridBagConstraints gbc_frmtdtxtfldMin = new GridBagConstraints();
		gbc_frmtdtxtfldMin.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldMin.gridx = 3;
		gbc_frmtdtxtfldMin.gridy = 0;
		panel.add(frmtdtxtfldMin, gbc_frmtdtxtfldMin);
		
		JFormattedTextField frmtdtxtfldSec = new JFormattedTextField();
		frmtdtxtfldSec.setText("Sec");
		GridBagConstraints gbc_frmtdtxtfldSec = new GridBagConstraints();
		gbc_frmtdtxtfldSec.insets = new Insets(0, 0, 0, 5);
		gbc_frmtdtxtfldSec.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldSec.gridx = 4;
		gbc_frmtdtxtfldSec.gridy = 0;
		panel.add(frmtdtxtfldSec, gbc_frmtdtxtfldSec);
		
		JFormattedTextField frmtdtxtfldMs = new JFormattedTextField();
		frmtdtxtfldMs.setText("Ms");
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
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("Mon");
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 7;
		gbc_formattedTextField.gridy = 0;
		panel.add(formattedTextField, gbc_formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("Day");
		GridBagConstraints gbc_formattedTextField_1 = new GridBagConstraints();
		gbc_formattedTextField_1.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_1.gridx = 8;
		gbc_formattedTextField_1.gridy = 0;
		panel.add(formattedTextField_1, gbc_formattedTextField_1);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setText("Hr");
		GridBagConstraints gbc_formattedTextField_2 = new GridBagConstraints();
		gbc_formattedTextField_2.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_2.gridx = 9;
		gbc_formattedTextField_2.gridy = 0;
		panel.add(formattedTextField_2, gbc_formattedTextField_2);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setText("Min");
		GridBagConstraints gbc_formattedTextField_3 = new GridBagConstraints();
		gbc_formattedTextField_3.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_3.gridx = 10;
		gbc_formattedTextField_3.gridy = 0;
		panel.add(formattedTextField_3, gbc_formattedTextField_3);
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setText("Sec");
		GridBagConstraints gbc_formattedTextField_4 = new GridBagConstraints();
		gbc_formattedTextField_4.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_4.gridx = 11;
		gbc_formattedTextField_4.gridy = 0;
		panel.add(formattedTextField_4, gbc_formattedTextField_4);
		
		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setText("Ms");
		GridBagConstraints gbc_formattedTextField_5 = new GridBagConstraints();
		gbc_formattedTextField_5.insets = new Insets(0, 0, 0, 5);
		gbc_formattedTextField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField_5.gridx = 12;
		gbc_formattedTextField_5.gridy = 0;
		panel.add(formattedTextField_5, gbc_formattedTextField_5);
		
		JButton btnStart = new JButton("Start");
//		OutputParser out = new OurputParser();
//		btnStart.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 2;
		gbc_btnStart.gridy = 1;
		getContentPane().add(btnStart, gbc_btnStart);
		
	
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 17;
		gbc_tabbedPane.insets = new Insets(0, 4, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		getContentPane().add(tabbedPane, gbc_tabbedPane);

		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 19;
		getContentPane().add(progressBar, gbc_progressBar);
	}
}
