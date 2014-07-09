import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileManager extends StringFilter{
	public FileManager(String keyword) {
		super(keyword);
		// TODO Auto-generated constructor stub
	}

	public static String readFile(String fileName) throws IOException {
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
}
