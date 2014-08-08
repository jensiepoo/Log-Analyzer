import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config extends FileSaver {
	private static File f = new File(System.getProperty("user.dir")
			+ "\\src\\config.txt");
	private static File f1 = new File(System.getProperty("user.dir")
			+ "\\src\\directory.txt");
	private StringBuilder sb;

	public Config(StringBuilder sb) {
		this.sb = sb;
	}

	public String getDir() throws IOException{
		BufferedReader br= new BufferedReader(new FileReader(f1));
		String line = br.readLine();
		return line;
	}
	
	public void setDir(String dir) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(f1));
		out.write(dir);
		out.close();
	}
	
	public void update(String item, Color col) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		String line = br1.readLine();
		boolean found = false;
		while (line != null) {
			if (line.substring(0, line.indexOf(',')).equals(item)) {
				clean(item);
				line = line.substring(0, line.indexOf(',')) + ", Color: "
						+ col.toString() + '\n';
				sb.append(line);
				found = true;
			}
			line = br1.readLine();
		}
		if (!found) {
			sb.append(item.trim() + ", Color: " + col.toString() + '\n');
		}
		br1.close();
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(sb.toString());
		out.close();
	}

	public void clean(String item) {
		String str = sb.toString();
		System.out.println("sb " + str);
		int start, end;
		start = str.indexOf(item);
		System.out.println("start : " + start);
		end = str.indexOf('\n', start) + 1;
		System.out.println("end : " + end);
		sb.delete(start, end);
		System.out.println(sb.toString());
	}

	// line = line.substring(0, line.indexOf(':')+2) + color.toString();
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
		sb = sb1;
	}
	
	// record into config file without a color
	public void update(String item) throws IOException { 
		BufferedReader br1 = new BufferedReader(new FileReader(f));
		String line = br1.readLine();
		boolean found = false;
		while (line != null) {
			if (line.substring(0, line.indexOf(',')).equals(item)) {
				clean(item);
				line = line.substring(0, line.indexOf(',')) + ",\n";
				sb.append(line);
				found = true;
			}
			line = br1.readLine();
		}
		if (!found) {
			sb.append(item.trim() + ",\n");
		}
		br1.close();
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		out.write(sb.toString());
		out.close();
	}
}
