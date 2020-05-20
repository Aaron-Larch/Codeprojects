package RegxTest;

import java.util.regex.Pattern;

public class CanonEqDemo {
	public static void main(String[] args) {
		String pattStr = "\u00e9gal";
		// egal
		String[] input = {"\u00e9gal", // egal - this one had better match :-)
						"e\u0301gal", // e + "Combining acute accent"
						"e\u02cagal", // e + "modifier letter acute accent"
						"e'gal", // e + single quote
						"e\u00b4gal", // e + Latin-1 
						"acute"};
		Pattern pattern = Pattern.compile(pattStr, Pattern.CANON_EQ);
		for (int i = 0; i < input.length; i++) {
			if (pattern.matcher(input[i]).matches()) {
				System.out.println(pattStr + " matches input " + input[i]);
			} else {
				System.out.println(pattStr + " does not match input " + input[i]);
			}
		}
	}
}
