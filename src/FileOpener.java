import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOpener implements ActionListener{
	private String x ="";
	private JTabbedPane tabbedPane;
	public TextArea textArea;
	private String tabName = "";
	
	public FileOpener(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public FileOpener(JTabbedPane tabbedPane, TextArea textArea) {
		this.tabbedPane = tabbedPane;
		this.textArea = textArea;
	}

	public static String readFile(File fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
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
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "txt files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	for(File w : chooser.getSelectedFiles()){
		    	try {
					x = x + "File Name: " + w.getName() + '\n' + '\n' +readFile(w) + "=====================================End Of File====================================" + '\n' ;
					tabName = w.getName().substring(0, w.getName().indexOf('_'));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
	    	textArea.append(x);
	    	tabbedPane.addTab(tabName, textArea);
	    }
	}
}
