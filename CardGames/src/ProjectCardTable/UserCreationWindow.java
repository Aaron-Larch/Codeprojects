package ProjectCardTable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


import CardGameGUI.Main;

public class UserCreationWindow implements ActionListener {

    private static void createAndShowGui() {
		JFrame frame = new JFrame("Welcome to the Card table");
	    frame.setSize(400, 200);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
	    JFrame frame1 = new JFrame("Welcome to the Card table");
	    frame1.setSize(400, 400);
	    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Container containeruser = frame.getContentPane();
	    containeruser.setLayout(new FlowLayout());
	    Container containermoney = frame1.getContentPane();
	    containermoney.setLayout(new GridLayout(0, 1));
	    
	    JTextField textFielduser = new JTextField();
	    textFielduser.setPreferredSize(new Dimension(150, 25));
	         
	    JTextField textFieldMoney = new JTextField();
	    textFieldMoney.setPreferredSize(new Dimension(150, 25));

	    JLabel labelTitle = new JLabel("<html><div style='text-align: center;'>"+
	        			"Hello Welcom To my BlackJacktable<br>What is your name?<br>"+
	        				"</div></html>", SwingConstants.CENTER);

	    JLabel labelUser= new JLabel();
	       
	    JButton okButton = new JButton("OK");
	    JButton StartGamebtn = new JButton("OK");

	   
        containeruser.add(labelTitle);
        containeruser.add(textFielduser);
        containeruser.add(okButton);

        containermoney.add(labelUser);
        containermoney.add(textFieldMoney);
        containermoney.add(StartGamebtn);
	    
	    okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String input = textFielduser.getText();
                System.out.println("Input: " + input);
                String Message=new String("<html><div style='text-align: center;'>"+
                		"Hello "+input+", lets play some BlackJack!<br>"+
                		"How much cash do you want to start with?</div></html>");              
                labelUser.setText(Message);
                frame1.setVisible(true);
                frame.setVisible(false);
            }
        });

        StartGamebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try{
            		int input = Integer.parseInt(textFieldMoney.getText());
            		System.out.println("Input: " + input);

                    new Thread(new Main()).start();
                    frame1.setVisible(false);
            	}catch (NumberFormatException ex) {
            		String Error = "You did not enter a valid number Please try again.";
            		labelUser.setText(Error);
            	}
            }
        });
	    
	    frame.setVisible(true);
	    frame1.setVisible(false);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
        public void run() {
       	 createAndShowGui();
       	
        }
     });
	   }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
