package ProjectCardTable;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BlackJack.User;
import CardGameGUI.Main;

public class UserCreation implements ActionListener {
	JLabel labelUser=new JLabel();
	JButton button;
	JButton StartGamebtn;
	JButton PlaceWager;
	JFrame frame;
	JTextField textFielduser;  
	JTextField textFieldMoney;
	User Player = new User();
	
	public void StartWindo(){         
		frame = new JFrame("Welcome to the Card table");
		frame.setLayout(null);
		frame.setSize(400,400);

		Container pane =frame.getContentPane();
		pane.setLayout(null);
		Insets insets = pane.getInsets();
		Dimension size;

		JPanel p = new JPanel();        

		p = new JPanel();
		p.setLayout(new GridLayout(0, 1));
		pane.add(p);
		textFielduser = new JTextField();
	    textFielduser.setPreferredSize(new Dimension(25, 25));
	   
	    JLabel labelTitle = new JLabel("<html><div style='text-align: center;'>"+
	        			"Hello Welcom To my BlackJacktable<br>What is your name?<br>"+
	        				"</div></html>", SwingConstants.CENTER);
		button = new JButton("Close Frame");
		button.addActionListener(this);
		p.add(labelTitle);
		p.add(textFielduser);
		p.add(button);
		size = p.getPreferredSize();
		p.setBounds(150 + insets.left, 100 + insets.top,
		size.width, size.height);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// createUI() closed
    
	public void Altwindow(JLabel label){
		frame = new JFrame("Welcome to the Card table");
 		frame.setLayout(null);
 		frame.setSize(400,400);

 		Container pane =frame.getContentPane();
 		pane.setLayout(null);
 		Insets insets = pane.getInsets();
 		Dimension size;

 		JPanel p = new JPanel();        

 		p = new JPanel();
 		p.setLayout(new GridLayout(0, 1));
 		pane.add(p);
 	    textFieldMoney = new JTextField();
 	    textFieldMoney.setPreferredSize(new Dimension(150, 25));
 	    
 	    StartGamebtn = new JButton("OK");
 	    StartGamebtn.addActionListener(this);
 	   
        p.add(label);
        p.add(textFieldMoney);
        p.add(StartGamebtn);
        size = p.getPreferredSize();
 		p.setBounds(150 + insets.left, 100 + insets.top, size.width, size.height);

 		frame.setVisible(true);
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   	
     }

  public void actionPerformed(ActionEvent e){
	  UserCreation cfe = new UserCreation();
	  if(e.getSource()==button) {	
		  String input = textFielduser.getText();
		  Player.setName(input);
		  String Message=new String("<html><div style='text-align: center;'>"+
            		"Hello " + Player.getName()+", lets play some BlackJack!<br>"+
            		"How much cash do you want to start with?</div></html>");              
		  labelUser.setText(Message);
		  frame.dispose();		
		  cfe.Altwindow(labelUser);
	  }else if(e.getSource()==StartGamebtn) {
			if(numberCheker(textFieldMoney.getText())!=-1) {
	    		 int input = Integer.parseInt(textFieldMoney.getText());
	    		 System.out.println("Your starting cash total is: "+input);
	    		 Player.setCash(input);
	    		 frame.dispose();
	    		 System.out.println("Name:"+Player.getName());
	    		 //Player.Gamble("How much are you willing to bet on this game " + Player.getName()+"?");
	    		// new Thread(new Main()).start();
	         }else{
	         	String Error = "You did not enter a valid number Please try again.";
	            labelUser.setText(Error);
	            cfe.Altwindow(labelUser);
	         }
		}

	}
  
  private int numberCheker(String usrInput) {
	  try{
 		 int input = Integer.parseInt(usrInput);
 		return input;
      }catch (NumberFormatException ex) {
      	return -1;
      }
  }
  
  public static void main(String args[])
        {
	  UserCreation cfe = new UserCreation();
            cfe.StartWindo();
        }// main() closed
}
