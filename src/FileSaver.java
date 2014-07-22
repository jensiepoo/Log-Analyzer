import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileSaver extends StringFilter implements ActionListener{
	private String text = "";
	private TextArea textArea;
	
	public FileSaver() {
	}
	public FileSaver(String text) {
		this.text = text;
	}
	
	public FileSaver(TextArea textArea) {
		this.textArea = textArea;
	}
	
	/**
	 * Save the given text to the given filename.
	 * @param canonicalFilename Like /Users/al/foo/bar.txt
	 * @param text All the text you want to save to the file as one String.
	 * @throws IOException
	 */
	public static void writeFile(String fileName, String text) 
	throws IOException
	{
		  File file = new File (fileName);
		  BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
		  out.write(text);
		  out.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {				//saving a file and saves content in Text area into a named file
		JFileChooser chooser = new JFileChooser();
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	try {
				writeFile(chooser.getCurrentDirectory().getCanonicalPath()+ '\\' + chooser.getSelectedFile().getName() , textArea.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	}
}

