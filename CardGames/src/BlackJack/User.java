package BlackJack;

import java.awt.event.WindowAdapter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class User {
	JFrame frame;
	private String Name;
	private int cash;
	private int bet;
	boolean flag=false;
	Scanner scan = new Scanner(System.in);
	
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void CreatePlayer() {
		System.out.println("Hi! What is your name?");
	    Name = scan.nextLine();
	    System.out.println("Hello, "+Name+", lets play some BlackJack!");
	    System.out.println("How much cash do you want to start with?");
	    cash = scan.nextInt();
	    System.out.println("You start with cash: "+cash);
	}
	
	//Asks the user how much he or she would like to bet.
	public void Gamble(){
		boolean flag=true;
		String StringBet;
		frame = new JFrame("Wager");
		
		StringBet= JOptionPane.showInputDialog(frame,"Please input You wish to wager: ");
		while(flag==true) {
			try{
				setBet(Integer.parseInt(StringBet));
				setCash((cash-bet));
				if(bet>cash) {
					StringBet=JOptionPane.showInputDialog(frame,"The ammount you wish to wager is more than you have\nPlease try again:");
				}else {
					setFlag(true);
					flag=false;
					}
			}catch (NumberFormatException ex) {
				StringBet=JOptionPane.showInputDialog(frame,"The ammount you wish to wager is not a number\nPlease try again:");
	      }
		}
	}
	
	public void DoubleDown() {
		//check if the user can double down.
		frame = new JFrame("Wager");
		if(2*bet<cash){
           //allows the user to double down.
     		int a=JOptionPane.showConfirmDialog(frame,"Would you like to double Down");  
     		if(a==JOptionPane.YES_OPTION){  
     			System.out.println("You have opted to double down!");
     			cash-=bet;
     			bet=2*bet;
                System.out.println("Cash:"+ cash);
                System.out.println("Money on the table:"+bet);
     		}
     		if(a==JOptionPane.NO_OPTION){  
     			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
     		}
         }
	}
	
	public void CurentCash() {
		//if user doesn't want to play or runs out of cash, either congratulates them on their winnings or lets them know
		System.out.println("Your cash is: "+ cash);
		if(cash==0){System.out.println("You ran out of cash!");}
		else{System.out.println("Enjoy your winnings, "+Name+"!");}
	}
	
	public String Contunegame() {
        System.out.println("Would you like to play again?");//ask if the user wants to keep going
        String answer = scan.nextLine();
    	while(!isyesorno(answer)){
    		System.out.println("Please answer yes or no.");
    		answer = scan.nextLine();
    	}
    	if(answer.equals("no")){return "End";}
    	else{return "Continue";}
	}
	
	public void CreatePlayerGUI() {
		boolean flag=true;
		Name = JOptionPane.showInputDialog(frame,"Hi! What is your name?");
		
		String money=JOptionPane.showInputDialog(frame,"Hello, "+Name+", lets play some BlackJack!\n"+
				"How much cash do you want to start with?");
		while(flag==true) {
			try{
				cash=Integer.parseInt(money);
				flag=false;
			}catch (NumberFormatException ex) {
				money=JOptionPane.showInputDialog(frame,"The ammount you wish to Start with is not a number\nPlease try again:");
	      }
		}
	}
	
	// Determines if a user has input yes or no.
	public static boolean isyesorno(String answer)
	{return answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no");}
	
}
