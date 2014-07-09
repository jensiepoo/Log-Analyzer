import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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


public class log_parser extends JFrame {

	private JPanel contentPane;
	private JTextField txtAsdfasdf;
	private JTextField txtMonth;
	private JTextField txtDay;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDay_1;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenFile;
	private JCheckBox chckbxRadioTechChange;
	private JCheckBox checkBox_1;
	private TextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		setResizable(false);
		setTitle("Log Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 616);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("QMI Timeouts");
		chckbxNewCheckBox.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(-4, 23, 89, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxDialFlow = new JCheckBox("Dial Flow");
		chckbxDialFlow.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxDialFlow.setBounds(85, 23, 74, 21);
		contentPane.add(chckbxDialFlow);
		
		JCheckBox chckbxDataCall = new JCheckBox("Data Call");
		chckbxDataCall.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxDataCall.setBounds(155, 23, 67, 21);
		contentPane.add(chckbxDataCall);
		
		txtAsdfasdf = new JTextField();
		txtAsdfasdf.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		txtAsdfasdf.setText("Year");
		txtAsdfasdf.setBounds(6, 50, 35, 21);
		contentPane.add(txtAsdfasdf);
		txtAsdfasdf.setColumns(10);
		
		txtMonth = new JTextField();
		txtMonth.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		txtMonth.setText("Month");
		txtMonth.setColumns(10);
		txtMonth.setBounds(42, 50, 40, 21);
		contentPane.add(txtMonth);
		
		txtDay = new JTextField();
		txtDay.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		txtDay.setText("Day");
		txtDay.setColumns(10);
		txtDay.setBounds(83, 50, 35, 21);
		contentPane.add(txtDay);
		
		textField = new JTextField();
		textField.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		textField.setText("Year");
		textField.setColumns(10);
		textField.setBounds(132, 50, 35, 21);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		textField_1.setText("Month");
		textField_1.setColumns(10);
		textField_1.setBounds(168, 50, 40, 21);
		contentPane.add(textField_1);
		
		txtDay_1 = new JTextField();
		txtDay_1.setToolTipText("Set duration of log to be analyzed. \r\nDefault to analyze the entire log.");
		txtDay_1.setText("Day");
		txtDay_1.setColumns(10);
		txtDay_1.setBounds(209, 50, 35, 21);
		contentPane.add(txtDay_1);
		
		menuBar = new JMenuBar();
		menuBar.setAutoscrolls(true);
		menuBar.setBounds(0, 0, 577, 21);
		contentPane.add(menuBar);
		
		mnFile = new JMenu("File    ");
		menuBar.add(mnFile);
		
		mntmOpenFile = new JMenuItem("Open File...");
		mnFile.add(mntmOpenFile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(401, 163, 2, 2);
		contentPane.add(scrollPane);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(120, 53, 9, 15);
		contentPane.add(lblTo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(53, 144, 191, -31);
		contentPane.add(scrollPane_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(16, 81, 437, 497);
		textField_2.setText("Before change");
		contentPane.add(textField_2);
		
		JCheckBox chckbxNoService = new JCheckBox("No Service");
		chckbxNoService.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxNoService.setBounds(224, 23, 79, 21);
		contentPane.add(chckbxNoService);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setBounds(103, 191, 1, 1);
		contentPane.add(horizontalGlue);
		
		chckbxRadioTechChange = new JCheckBox("Radio Tech Change");
		chckbxRadioTechChange.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxRadioTechChange.setBounds(299, 23, 122, 21);
		contentPane.add(chckbxRadioTechChange);
		
		checkBox_1 = new JCheckBox("No Service");
		checkBox_1.setFont(new Font("Arial", Font.PLAIN, 11));
		checkBox_1.setBounds(423, 23, 79, 21);
		contentPane.add(checkBox_1);
		
		TextField textField_3 = new TextField();
		textField_3.setText("Error Handling");
		textField_3.setBounds(459, 81, 108, 242);
		contentPane.add(textField_3);
		
		textField_4 = new TextField();
		textField_4.setText("Other editor");
		textField_4.setBounds(459, 336, 108, 242);
		contentPane.add(textField_4);
	}
}
