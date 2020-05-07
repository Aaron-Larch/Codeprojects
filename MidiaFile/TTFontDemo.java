package MidiaFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TTFontDemo extends JLabel{
	private static final long serialVersionUID = -2774152065764538894L;
	// Construct a TTFontDemo -- Create a Font from TTF.
	public TTFontDemo(String fontFileName, String text)throws IOException, FontFormatException {
		super(text, JLabel.CENTER);setBackground(Color.white);
		// First, see if we can load the font file.
		InputStream is = this.getClass().getResourceAsStream(fontFileName);
		if (is == null) {throw new IOException("Cannot open " + fontFileName);}
		// createFont makes a 1-point font, bit hard to read :-)
		Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);
		// So scale it to 24 pt.
		Font ttfReal = ttfBase.deriveFont(Font.PLAIN, 24);setFont(ttfReal);
		}
	// Simple main program for TTFontDemo 
	public static void main(String[] args) throws Exception {
		String DEFAULT_MESSAGE ="What hath man wrought? Or at least rendered?";
		// Loaded as Resource so don't need graphics/ in front
		String DEFAULT_FONTFILE = "Kellyag_.ttf";
		String message = args.length == 1 ? args[0] : DEFAULT_MESSAGE;
		JFrame f = new JFrame("TrueType Font Demo");
		TTFontDemo ttfd = new TTFontDemo(DEFAULT_FONTFILE, message);
		f.getContentPane().add(ttfd);
		f.setBounds(100, 100, 700, 250);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
