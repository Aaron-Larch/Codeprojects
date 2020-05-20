package RegxTest;

import java.util.regex.Pattern;

public class RESimple {

	public static void main(String[] argv) {
		String pattern = "^War\\ [^u^dog]\\d+\\.";
		String[] input = {"War A777. is the next flight. It is on time.","War uack, Wara56, War uack!", "War dog7"};
		Pattern p = Pattern.compile(pattern);
		for (String in : input) {
			boolean found = p.matcher(in).lookingAt();
			System.out.println("'" + pattern + "'" +(found ? " matches '" : " doesn't match '") + in + "'");
		}
		String pattern2 = ".*Q[^u]\\d+\\..*";
		String line = "Order QT300. Now!";
		if (line.matches(pattern2)) {
			System.out.println(line + " matches \"" + pattern2 + "\"");
			} else {
				System.out.println("NO MATCH");
				}  
	}
}
