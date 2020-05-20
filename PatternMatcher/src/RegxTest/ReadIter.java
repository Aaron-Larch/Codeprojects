package RegxTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadIter {
	public static void main(String[] args) throws IOException {
		// The RE pattern
		Pattern patt1 = Pattern.compile("[A-Za-z][a-z]+");
		// A FileReader (see the I/O chapter)
		BufferedReader read = new BufferedReader(new FileReader(args[0]));
		// For each line of input, try matching in it.
		String line;
		while ((line = read.readLine()) != null) {
			// For each match in the line, extract and print it.
			Matcher mat = patt1.matcher(line);
			while (mat.find()) {
				// Simplest method:
				// System.out.println(m.group(0));
				// Get the starting position of the text
				int start = mat.start(0);
				// Get ending position
				int end = mat.end(0);
				// Print whatever matched.
				// Use CharacterIterator.substring(offset, end);
				System.out.println(line.substring(start, end));
			}
		}
		read.close();
	}
}
