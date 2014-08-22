import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config extends FileSaver {
	//Static file directory for configuration file, and last opened
	//directory, so the user does not have to start from root directory
	//everytime they attempt to open a new file. 
	private static File f = new File(System.getProperty("user.dir")
			+ "\\config.txt"); 
	private static File f1 = new File(System.getProperty("user.dir")
			+ "\\directory.txt"); 
	
	/**
	 * @throws IOException
	 * Constructs configuration file or directory file if they have 
	 * not been created yet.
	 * This class is responsible for updating the configuration and directory
	 * files. And reads from cofig file when program boots up; from directory
	 * file when opening new file(s).
	 */
	public Config() throws IOException {
		f1.createNewFile();
		f.createNewFile();
	}

	
	/**
	 * @return
	 * @throws IOException
	 * Gets the last opened directory written in the directory.txt file.
	 */
	public String getDir() throws IOException{
		BufferedReader br= new BufferedReader(new FileReader(f1));
		String line = br.readLine();
		br.close();
		return line;
	}
	
	
	/**
	 * @param dir
	 * @throws IOException
	 * Writes directory.txt with the latest opened directory. 
	 */
	public void setDir(String dir) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(f1));
		out.write(dir);
		out.close();
	}
	
	
	/**
	 * @param item
	 * @param col
	 * @throws IOException3
	 * Updates the configuration file given the name of the stringfilter(checkbox)
	 * and the color of the filter. If an entry in the configuration file 
	 * has the same name as the item, update the color of that entry. 
	 * 
	 * Calling update("hello", Color.blue) would change the original
	 * entry from ("hello", any color or blank) to ("hello", Color.blue)
	 */
	public void update(String item, Color col) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		String line = br1.readLine();
		StringBuilder sb2 = readConfig(); 	
		boolean found = false;	//checks whether a same keyword has been found.
		while (line != null) {
			//if an entry of the same name is already contained in the
			//configuration file. 
			if (line.substring(0, line.indexOf(',')).equals(item)) {
				//delete the previous entry which contains the same name.
				clean(item, sb2);
				
				//constructs the new line with the format, (Name, Color)
				line = line.substring(0, line.indexOf(',')) + ", Color: "
						+ col.toString() + '\n';
				
				sb2.append(line);
				found = true;
			}
			line = br1.readLine();
		}
		//the keyword has not been found, append to end of file.
		if (!found) {	
			sb2.append(item.trim() + ", Color: " + col.toString() + '\n');
		}
		
		br1.close();
		//Converts stringbuilder to string and write to file. 
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(sb2.toString());
		out.close();
	}

	
	/**
	 * @return
	 * @throws IOException
	 * Reads the configuration file, and store in a stringbuilder.
	 */
	public StringBuilder readConfig() throws IOException {
		StringBuilder sb2 = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {

			String line = br.readLine();
			// line = br.readLine();

			while (line != null) {
				sb2.append(line);
				sb2.append("\n");
				line = br.readLine();
			}
			return sb2;
		} finally {
			br.close();
		}
	}

	
	/**
	 * @param item
	 * @param sb2
	 * Takes in the stringbuilder returned from readConfig(), 
	 * and cleans the previous entries of the stringbuilder that contains
	 * the same keyword.
	 * E.G. calling clean("hello", sb2) would change ("hello", Color.green) to 
	 * empty string. 
	 */
	public void clean(String item, StringBuilder sb2) {
		String str = sb2.toString();
		int start, end;
		start = str.indexOf(item);
		end = str.indexOf('\n', start) + 1;
		sb2.delete(start, end);
	}


	/**
	 * @param item
	 * @throws IOException
	 * Delete all entires in config file with the keyword. 
	 */
	public void delete(String item) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		StringBuilder sb1 = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			boolean x = line.substring(0, line.indexOf(',')).equals(item);
			if (x) {
				line = br.readLine();
			} else {
				sb1.append(line);
				sb1.append('\n');
				line = br.readLine();
			}
		}
		br.close();
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(sb1.toString());
		out.close();
//		sb = sb1;
	}
	
	
	/**
	 * @param item
	 * @throws IOException
	 * Record keyword into config file without a color
	 */
	public void update(String item) throws IOException { 
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		String line = br1.readLine();
		StringBuilder sb2 = readConfig();
		boolean found = false;
		while (line != null) {
			if (line.substring(0, line.indexOf(',')).equals(item)) {
				clean(item, sb2);
				line = line.substring(0, line.indexOf(',')) + ",\n";
				sb2.append(line);
				found = true;
			}
			line = br1.readLine();
		}
		if (!found) {
			sb2.append(item.trim() + ",\n");
		}
		br1.close();
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(sb2.toString());
		out.close();
	}
}
