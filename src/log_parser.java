import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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


public class log_parser extends JFrame {
	private JTextArea txtrSdfgasdfg;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFileChooser j = new JFileChooser();
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
		setTitle("Log Parser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 354);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File ");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...                         ");
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmSaveFileCtrl = new JMenuItem("Save As...                              Ctrl + S");
		mnFile.add(mntmSaveFileCtrl);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}
}
