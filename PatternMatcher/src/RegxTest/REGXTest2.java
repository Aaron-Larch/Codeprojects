package RegxTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REGXTest2 {
	 public static void main(String[] argv) {
		 String patt = "Q[^u]\\d+\\.";
		 Pattern r = Pattern.compile(patt);
		 String line = "Order QT300. Now!";
		 Matcher m = r.matcher(line);
		 if (m.find()) {
			 System.out.println(patt + " matches \"" +m.group(0) +"\" in \"" + line + "\"");
			 } else {System.out.println("NO MATCH");}
		 
		 //sort a string?
		 String inputLine = "Adams, John Quincy";
		 // Construct an RE with parens to "grab" both field1 and field2
		 Pattern r1 = Pattern.compile("(.*), (.*)");
		 Matcher m1 = r1.matcher(inputLine);
		 if (!m1.matches())
			 throw new IllegalArgumentException("Bad input");
		 System.out.println(m1.group(2) + ' ' + m1.group(1));
		 System.out.println(inputLine);
	}
}
