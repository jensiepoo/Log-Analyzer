import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class SearchPane extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextPane jtp;
	private JLabel lblNewLabel_1 = new JLabel();
	private Highlighter highlighter;

	/**
	 * Create the frame.
	 */
	public SearchPane(final JTabbedPane tabbedPane) {
		
		setTitle("Find/Regular Expression");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 482, 195);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Normal");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(8, 93, 107, 23);
		contentPane.add(rdbtnNewRadioButton);

		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
				"Regular Expression");
		rdbtnNewRadioButton_1.setBounds(113, 93, 156, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		InputMap inputMap = contentPane
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "exit");
		contentPane.getActionMap().put("exit", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (highlighter != null) {
					highlighter.removeAllHighlights();
				}
				dispose();
			}
		});

		JLabel lblNewLabel = new JLabel("Find what : ");
		lblNewLabel.setFont(UIManager.getFont("ToolTip.font"));
		lblNewLabel.setBounds(10, 10, 62, 26);
		contentPane.add(lblNewLabel);

		final JCheckBox chckbxWrapAround = new JCheckBox("Wrap Around");
		chckbxWrapAround.setSelected(true);
		chckbxWrapAround.setBounds(6, 42, 135, 23);
		contentPane.add(chckbxWrapAround);

		final JButton btnNewButton = new JButton("Find Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((JScrollPane) tabbedPane.getSelectedComponent() != null) {
					JScrollPane c = (JScrollPane) tabbedPane
							.getSelectedComponent();
					jtp = (JTextPane) c.getViewport().getView(); // main pane
					if(rdbtnNewRadioButton_1.isSelected() && chckbxWrapAround.isSelected()){
						regWrap();
					}
					else if(rdbtnNewRadioButton_1.isSelected() && !chckbxWrapAround.isSelected()){
						reg();
					}
					else if (rdbtnNewRadioButton.isSelected() &&chckbxWrapAround.isSelected()){
						wrap(textField.getText());
					} else {
							find(textField.getText());
					}
				}	
			}
		});
		btnNewButton.setFont(UIManager.getFont("ToolTip.font"));
		btnNewButton.setToolTipText("");
		btnNewButton.setBounds(333, 13, 131, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(72, 14, 251, 21);
		textField.requestFocus();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((JScrollPane) tabbedPane.getSelectedComponent() != null) {
					JScrollPane c = (JScrollPane) tabbedPane
							.getSelectedComponent();
					jtp = (JTextPane) c.getViewport().getView(); // main pane
					if(rdbtnNewRadioButton_1.isSelected() && chckbxWrapAround.isSelected()){
						regWrap();
					}
					else if(rdbtnNewRadioButton_1.isSelected() && !chckbxWrapAround.isSelected()){
						reg();
					}
					else if (rdbtnNewRadioButton.isSelected() &&chckbxWrapAround.isSelected()){
						wrap(textField.getText());
					} else {
							find(textField.getText());
					}
				}	
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblSearchMode = new JLabel("Search Mode : ");
		lblSearchMode.setFont(UIManager.getFont("ToolTip.font"));
		lblSearchMode.setBounds(10, 67, 131, 26);
		contentPane.add(lblSearchMode);

		

		JButton btnFindPrevious = new JButton("Find Previous");
		btnFindPrevious.setToolTipText("");
		btnFindPrevious.setFont(UIManager.getFont("ToolTip.font"));
		btnFindPrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if ((JScrollPane) tabbedPane.getSelectedComponent() != null) {
					JScrollPane c = (JScrollPane) tabbedPane
							.getSelectedComponent();
					jtp = (JTextPane) c.getViewport().getView(); // main pane
					if (chckbxWrapAround.isSelected() && rdbtnNewRadioButton_1.isSelected()) {
						regWrapLast();
					}
					else if(!chckbxWrapAround.isSelected() && rdbtnNewRadioButton_1.isSelected()){
						regLast();
					}
					else if(chckbxWrapAround.isSelected() && rdbtnNewRadioButton.isSelected()){
						prev(textField.getText());
					} else {
						prev1(textField.getText());
					}
				}
			}
		});
		btnFindPrevious.setBounds(333, 50, 131, 23);
		contentPane.add(btnFindPrevious);

		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton_1.setSelected(false);
			}
		});

		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(true);
				rdbtnNewRadioButton.setSelected(false);
			}
		});
	}

	/**
	 * @param keyword
	 *            Prints text at the bottom of the search pane whenever a
	 *            keyword is not found.
	 */
	public void noResult(String keyword) {
		lblNewLabel_1.setText("Find: Can't Find the text \"" + keyword + "\"");
		lblNewLabel_1.setFont(UIManager.getFont("ToolBar.font"));
		lblNewLabel_1.setForeground(Color.red);
		lblNewLabel_1.setBounds(10, 140, 456, 15);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(true);
		contentPane.updateUI();
	}

	public void highLight(String str, int index) {
		highlighter = jtp.getHighlighter();
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
				Color.yellow);

		highlighter.removeAllHighlights();

		try {
			highlighter.addHighlight(index, index + str.length(), painter);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtp.getCaret().setVisible(true);
	}

	/**
	 * @param jtp
	 * @param str
	 * @param from
	 *            Wrap around - from where the caret is Finds the next
	 *            occurrence of the string in the jtextpane, highlights it, and
	 *            moves the caret to the front of the string.
	 */
	public void wrap(String str) {
		if (jtp.getText().indexOf(str) != -1) {
			lblNewLabel_1.setText("");
			if (jtp.getText().indexOf(str, jtp.getCaretPosition()) != -1) {
				highLight(str,
						jtp.getText().indexOf(str, jtp.getCaretPosition()));
				jtp.setCaretPosition(jtp.getText().indexOf(str,
						jtp.getCaretPosition())
						+ str.length()); // +1 so that it finds the
				// next
			} else {
				jtp.setCaretPosition(0);
				wrap(str);
			}
		} else {
			noResult(str);
		}
	}

	/**
	 * @param jtp
	 * @param str
	 * @param from
	 *            None wrap around - from where the caret is. Finds the next
	 *            occurrence of the string in the jtextpane, highlights it, and
	 *            moves the caret to the front of the string.
	 */
	public void find(String str) {
		if (jtp.getText().indexOf(str, jtp.getCaretPosition()) != -1) {
			lblNewLabel_1.setText("");
			highLight(str, jtp.getText().indexOf(str, jtp.getCaretPosition()));
			jtp.setCaretPosition(jtp.getText().indexOf(str,
					jtp.getCaretPosition())
					+ str.length()); // +length so that it finds the
			// next
		} else {
			noResult(str);
		}
	}

	/**
	 * @param jtp
	 * @param str
	 * @param from
	 *            Wrap around - from where the caret is Finds the next
	 *            occurrence(in reverse) of the string in the jtextpane,
	 *            highlights it, and moves the caret to the front of the string.
	 */
	public void prev(String str) {
		if (jtp.getText().indexOf(str) != -1) {
			lblNewLabel_1.setText("");
			if (jtp.getText().lastIndexOf(str, jtp.getCaretPosition() - 1) != -1) {
				highLight(
						str,
						jtp.getText().lastIndexOf(str,
								jtp.getCaretPosition() - 1));
				jtp.setCaretPosition(jtp.getText().lastIndexOf(str,
						jtp.getCaretPosition() - 1)); // +1 so that it finds the
				// next
			} else {
				jtp.setCaretPosition(jtp.getText().length());
				prev(str);
			}
		} else {
			noResult(str);
		}
	}

	/**
	 * @param jtp
	 * @param str
	 * @param from
	 *            Non-Wrap around - from where the caret is Finds the next
	 *            occurrence(in reverse) of the string in the jtextpane,
	 *            highlights it, and moves the caret to the front of the string.
	 */
	public void prev1(String str) {
		if (jtp.getText().lastIndexOf(str, jtp.getCaretPosition() - 1) != -1) {
			lblNewLabel_1.setText("");
			highLight(str,
					jtp.getText().lastIndexOf(str, jtp.getCaretPosition() - 1));
			jtp.setCaretPosition(jtp.getText().lastIndexOf(str,
					jtp.getCaretPosition() - 1)); // +1 so that it finds the

		} else {
			noResult(str);
		}
	}
	
	
	
	public void regWrap(){
		Hashtable<Integer, String> temp = new Hashtable<Integer, String>();
		Regex reg = new Regex(textField.getText());
		for(String a : reg.pattern()){
			temp.putAll(StringFilter.lookupArr1
					(a, jtp.getText()));
		}
	
		int[] array = new int[temp.size()];
		Set<Integer> set = temp.keySet();
		Iterator<Integer> iterator = set.iterator();
		int counter = 0;
		while(iterator.hasNext()){
			array[counter] = iterator.next();
			counter++;
		}
		Arrays.sort(array);
		if(array.length == 0){
			noResult(textField.getText());
		}
		else{
			lblNewLabel_1.setText("");
			boolean found = false; 
			for(int i = 0; i < array.length; i++){
				if(array[i] > jtp.getCaretPosition()){
					String x = temp.get(array[i]);
					jtp.setCaretPosition(jtp.getCaretPosition()+1); // +1 so that it finds the
					highLight(x,
							jtp.getText().indexOf(x, jtp.getCaretPosition()));
					jtp.setCaretPosition(jtp.getText().indexOf(x,
							jtp.getCaretPosition())
							+ x.length()); // +1 so that it finds the
					found = true;
					break;
				}
			}
			if(!found){
				jtp.setCaretPosition(0);
				regWrap();
			}
		}
	}
	
	
	public void reg(){
		Hashtable<Integer, String> temp = new Hashtable<Integer, String>();
		Regex reg = new Regex(textField.getText());
		for(String a : reg.pattern()){
			temp.putAll(StringFilter.lookupArr1
					(a, jtp.getText()));
		}
	
		int[] array = new int[temp.size()];
		Set<Integer> set = temp.keySet();
		Iterator<Integer> iterator = set.iterator();
		int counter = 0;
		while(iterator.hasNext()){
			array[counter] = iterator.next();
			counter++;
		}
		Arrays.sort(array);
		if(array.length == 0){
			noResult(textField.getText());
		}
		else{
			lblNewLabel_1.setText("");
			boolean found = false; 
			for(int i = 0; i < array.length; i++){
				if(array[i] > jtp.getCaretPosition()){
					String x = temp.get(array[i]);
					jtp.setCaretPosition(jtp.getCaretPosition()+1); // +1 so that it finds the
					highLight(x,
							jtp.getText().indexOf(x, jtp.getCaretPosition()));
					jtp.setCaretPosition(jtp.getText().indexOf(x,
							jtp.getCaretPosition())
							+ x.length()); // +1 so that it finds the
					found = true;
					break;
				}
			}
			if(!found){
				noResult(textField.getText());
			}
		}
	}
	
	public void regLast(){
		Hashtable<Integer, String> temp = new Hashtable<Integer, String>();
		Regex reg = new Regex(textField.getText());
		for(String a : reg.pattern()){
			temp.putAll(StringFilter.lookupArr1
					(a, jtp.getText()));
		}
	
		int[] array = new int[temp.size()];
		Set<Integer> set = temp.keySet();
		Iterator<Integer> iterator = set.iterator();
		int counter = 0;
		while(iterator.hasNext()){
			array[counter] = iterator.next();
			counter++;
		}
		Arrays.sort(array);
		for(int i = 0; i < array.length / 2; i++)
		{
		    int temp1 = array[i];
		    array[i] = array[array.length - i - 1];
		    array[array.length - i - 1] = temp1;
		}
		if(array.length == 0){
			noResult(textField.getText());
		}
		else{
			lblNewLabel_1.setText("");
			boolean found = false; 
			for(int i = 0; i < array.length; i++){
				if(array[i] < jtp.getCaretPosition()){
					String x = temp.get(array[i]);
					jtp.setCaretPosition(jtp.getCaretPosition()-1); // +1 so that it finds the
					highLight(x,
							jtp.getText().lastIndexOf(x, jtp.getCaretPosition()));
					jtp.setCaretPosition(jtp.getText().lastIndexOf(x,
							jtp.getCaretPosition())); // +1 so that it finds the
					found = true;
					break;
				}
			}
			if(!found){
				noResult(textField.getText());
			}
		}
	}
	
	
	public void regWrapLast(){
		Hashtable<Integer, String> temp = new Hashtable<Integer, String>();
		Regex reg = new Regex(textField.getText());
		for(String a : reg.pattern()){
			temp.putAll(StringFilter.lookupArr1
					(a, jtp.getText()));
		}
	
		int[] array = new int[temp.size()];
		Set<Integer> set = temp.keySet();
		Iterator<Integer> iterator = set.iterator();
		int counter = 0;
		while(iterator.hasNext()){
			array[counter] = iterator.next();
			counter++;
		}
		Arrays.sort(array);
		for(int i = 0; i < array.length / 2; i++)
		{
		    int temp1 = array[i];
		    array[i] = array[array.length - i - 1];
		    array[array.length - i - 1] = temp1;
		}
		if(array.length == 0){
			noResult(textField.getText());
		}
		else{
			lblNewLabel_1.setText("");
			boolean found = false; 
			for(int i = 0; i < array.length; i++){
				if(array[i] < jtp.getCaretPosition()){
					String x = temp.get(array[i]);
					jtp.setCaretPosition(jtp.getCaretPosition()-1); // +1 so that it finds the
					highLight(x,
							jtp.getText().lastIndexOf(x, jtp.getCaretPosition()));
					jtp.setCaretPosition(jtp.getText().lastIndexOf(x,
							jtp.getCaretPosition())); // +1 so that it finds the
					found = true;
					break;
				}
			}
			if(!found){
				jtp.setCaretPosition(jtp.getText().length());
				regWrapLast();
			}
		}
	}
	
	
}
