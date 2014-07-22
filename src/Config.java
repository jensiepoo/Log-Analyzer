import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTabbedPane;

public class Config extends FileSaver implements ActionListener{
	private String item;
	private JTabbedPane tabbedPane;
	public TextArea textArea;
	private String tabName = "";
	private static File f = new File("C:\\Users\\Jensen_Kuo\\workspace\\log analyzer\\src\\config");
	
	public Config(JTabbedPane tabbedPane) {
		this.setTabbedPane(tabbedPane);
	}

	public Config(JTabbedPane tabbedPane, TextArea textArea) {
		this.setTabbedPane(tabbedPane);
		this.textArea = textArea;
	}
	
	public Config(String item){
		this.setItem(item);
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public void update(String item) throws IOException{
		  BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
		  BufferedReader br = new BufferedReader(new FileReader(f));
		  boolean found = false; 
		  String line = br.readLine();
		  while(line != null){
			  if(line.equals(item)){
				  found = true;
				  break;
			  }
			  line = br.readLine();
		  }
		  if(!found){
			  out.append(item.trim());
			  out.close();
		  }
	}
	
	public static void delete(String item) throws IOException{
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		StringBuilder sb = new StringBuilder();
	    String line = br1.readLine();
	    while(line != null){
	    	boolean x = line.trim().equals(item.trim());
	    	if(x){
	    		line = br1.readLine();
	    	}
	    	else{
		    	sb.append(line);
		    	sb.append('\n');
		    	line = br1.readLine();
	    	}
	    }
	    writeFile("C:\\Users\\Jensen_Kuo\\workspace\\log analyzer\\src\\config", sb.toString());
	}
	
	public String read() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
