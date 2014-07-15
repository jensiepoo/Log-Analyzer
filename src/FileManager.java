import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager extends StringFilter{
	public FileManager(String keyword) {
	}
	
	/**
	 * Save the given text to the given filename.
	 * @param canonicalFilename Like /Users/al/foo/bar.txt
	 * @param text All the text you want to save to the file as one String.
	 * @throws IOException
	 */
	public static void writeFile(String canonicalFilename, String text) 
	throws IOException
	{
		  File file = new File (canonicalFilename);
		  BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
		  out.write("wtfffffffffffffffffffff");
		  out.close();
	}
}