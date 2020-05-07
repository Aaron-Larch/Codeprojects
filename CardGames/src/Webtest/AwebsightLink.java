package Webtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkListener;

public class AwebsightLink extends JEditorPane {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
    private static JPanel panel;
    private static JOptionPane optionPane;
    private static JEditorPane editorPane;

    public static void main(String[] args) {
        frame = new JFrame("Demo frame...");
        panel = new JPanel();
        panel.setBackground(Color.CYAN);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        
		JScrollPane sta = new JScrollPane();
		sta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        optionPane = new JOptionPane();
        optionPane.setSize(new Dimension(400, 300));
        optionPane.setPreferredSize(new Dimension(400, optionPane.getPreferredSize().height));
        sta.setBounds(0,20,800,800);
        editorPane = new JEditorPane();
        panel.add(optionPane, BorderLayout.CENTER);
        optionPane.add(editorPane, BorderLayout.CENTER);
        editorPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        editorPane.setEditable(false);

        HyperlinkListener hyperlinkListener = new     ActivatedHyperlinkListener(frame, editorPane);
        editorPane.addHyperlinkListener(hyperlinkListener);

        editorPane.setText("<a href='http://www.stackoverflow.com'>Go to the     stack</a>");

        editorPane.setToolTipText("if you click on <b>that link you go to     the stack");

        frame.setVisible(true);

    }
}