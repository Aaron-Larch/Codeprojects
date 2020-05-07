package BlackJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUIBord extends JPanel  {	
	private static final long serialVersionUID = 1L;

	//list of messages
	ArrayList<Message> Log = new ArrayList<Message>();

	//fonts used
	Font fontCard = new Font("Times New Roman", Font.PLAIN, 40);
	Font fontQuest = new Font("Times New Roman", Font.BOLD, 40);
	Font fontLog = new Font("Times New Roman", Font.ITALIC, 30);
		
	//Log message colors
	Color cDealer = Color.red;
	Color cPlayer = new Color(25,55,255);
	Color cMoney = Color.green;
	
	//Gambling field
	JTextField textFieldMoney;
		
	//strings used
	String questHitStay = new String("Hit or Stay?");
	String questPlayMore = new String("Play more?");
		
	//colors used
	Color colorBackground = new Color(39,119,20);
	Color colorButton = new Color(204,204,0);
		
	//window resolution
	int aW = 1300;
	int aH = 750;
		
	//card grid position and dimensions
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
		
	//booleans about phases
	boolean hit_stay_q = true;
	boolean dealer_turn=false;
	boolean play_more_q = false;
	
	//player and dealer card array
	ArrayList<Cards> pCards = new ArrayList<Cards>();
	ArrayList<Cards> dCards = new ArrayList<Cards>();
	ArrayList<Cards> tempHand = new ArrayList<Cards>();
	
	//set auto win lose flag
	boolean autowin;

	User user;	
	int space=0;
	
	//set Dynamic board assets
	public GUIBord(User pimport) {
		user=pimport;
	}
	
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	public ArrayList<Cards> getpCards() {
		return pCards;
	}
	public void setpCards(ArrayList<Cards> pCards) {
		this.pCards = pCards;
	}
	public ArrayList<Cards> getdCards() {
		return dCards;
	}
	public void setdCards(ArrayList<Cards> dCards) {
		this.dCards = dCards;
	}
	public ArrayList<Message> getLog() {
		return Log;
	}
	public void setLog(ArrayList<Message> log2) {
		Log = log2;
	}
	public boolean isDealer_turn() {
		return dealer_turn;
	}
	public void setDealer_turn(boolean dealer_turn) {
		this.dealer_turn = dealer_turn;
	}
	public boolean isHit_stay_q() {
		return hit_stay_q;
	}
	public void setHit_stay_q(boolean hit_stay_q) {
		this.hit_stay_q = hit_stay_q;
	}
	public boolean isPlay_more_q() {
		return play_more_q;
	}
	public void setPlay_more_q(boolean play_more_q) {
		this.play_more_q = play_more_q;
	}
	
	public boolean isAutowin() {
		return autowin;
	}

	public void setAutowin(boolean autowin) {
		this.autowin = autowin;
	}
	
	
	GUIDeck PlayerHand= new GUIDeck();
	public void paintComponent(Graphics g) {
		//background
		g.setColor(colorBackground);
		g.fillRect(0, 0, aW, aH);
		g.setColor(Color.black);
		g.setFont(fontQuest);
		g.drawString("Welcome "+user.getName()+" please enjoy your game:", gridX, gridY);
		
		//questions
		if (hit_stay_q == true) {
			g.setColor(Color.black);
			g.setFont(fontQuest);
			g.drawString(questHitStay, gridX+gridW+60, gridY+50);
			g.drawString("Total:", gridX+gridW+60, gridY+200);
			int[] print=Hand.totalsPrint(pCards);
			if (print[print.length-1]==0) {
				g.drawString(Integer.toString(print[0]), gridX+gridW+60, gridY+250);
			} else {
				g.drawString(Integer.toString(print[0]) + " or " + Integer.toString(print[1]), gridX+gridW+60, gridY+250);
			}
		} else if (play_more_q == true) {
			g.setColor(Color.black);
			g.setFont(fontQuest);
			g.drawString(questPlayMore, gridX+gridW+70, gridY+490);
		}
		
		g.setColor(Color.black);
		g.fillRect(gridX, gridY+gridH+50, gridW, 500);
					
		//Log
		g.setFont(fontLog);
		int logIndex = 0;
		for (Message L : Log) {
			if (L.getWho().equalsIgnoreCase("Dealer")) {
				g.setColor(cDealer);
			} else if(L.getWho().equalsIgnoreCase("Player")) {
				g.setColor(cPlayer);
			} else {
				g.setColor(cPlayer);
			}
			g.drawString(L.getMessage(), gridX+20, gridY+480+logIndex*35);
			logIndex++;
		}
						
		//score
		g.setColor(Color.BLACK);
		g.setFont(fontQuest);
		String score="Total cash: "+user.getCash();
		g.drawString(score, gridX+gridW-15, gridY+gridH);
				
		String bet= "Money on the table: "+user.getBet();
		g.drawString(bet, gridX+gridW-100, gridY+gridH+40);
				
		//player cards
		PlayerHand.paintCards(g, pCards, 10);
						
		if (dealer_turn == true || play_more_q == true) {
			//dealer cards
			tempHand.clear();
			PlayerHand.paintCards(g, dCards, 210);

			int dtest=Hand.calcHandValue(dCards);
			int test=Hand.calcHandValue(pCards);
					
			g.setColor(Color.black);
			g.setFont(fontQuest);
			g.drawString("Your total: ", gridX+gridW+60, gridY+40);
			g.drawString(Integer.toString(test), gridX+gridW+60, gridY+120);
			g.drawString("Dealer's total: ", gridX+gridW+60, gridY+240);
			g.drawString(Integer.toString(dtest), gridX+gridW+60, gridY+320);
		}else if(dealer_turn == false && space < 1) {
			space+=1;
			user.Gamble();
			bet+=user.getBet();
			score+=user.getCash();
			tempHand.add(new Cards(4, 14));
			
			for(int i=dCards.size(); i>0; i--) {
				int place=i-1;
				tempHand.add(dCards.get(place));
			}
			tempHand.remove(tempHand.get(tempHand.size()-1));
		} else if(dealer_turn == false && user.isFlag()==true) {
			user.setFlag(false);
			FirstCheck(g);
		}
		
		if(dealer_turn == false) {
			if(isAutowin()) {
				PlayerHand.paintCards(g, dCards, 200);
			}else {
				PlayerHand.paintCards(g, tempHand, 200);
			}
		}
	}
	public void FirstCheck(Graphics g) {
		int dtest=Hand.calcHandValue(dCards);
		int test=Hand.calcHandValue(pCards);
		
		//check if both the user and dealer have blackjack.
        if(BJRules.hasBlackJack(test, "Player") && BJRules.hasBlackJack(dtest, "Dealer")){
        	Log.add(new Message("It's a tie. you get your money back", "Player"));
        	user.setCash(BJRules.Push(user.getCash(), user.getBet(), user));
        	setAutowin(true);
        }
        //check if the user has blackjack.
        else if(BJRules.hasBlackJack(test, "Player")){
        	Log.add(new Message("You have BlackJack!", "Player"));
        	Log.add(new Message("You win 2x your money back!", "Player"));
        	user.setCash(user.getCash()+(user.getBet()*2));
        	user.setCash(BJRules.Win(user.getCash(), user.getBet(), user)-user.getBet());
        	setAutowin(true);
        }
        //check if the dealer has blackjack.
        else if (BJRules.hasBlackJack(dtest,"Dealer")){
        	Log.add(new Message("Here is the dealer's hand:", "Dealer"));
            user.setBet(BJRules.Lose(user.getCash()));
            setAutowin(true);
        }
        else{
        	setAutowin(false);
        	user.DoubleDown();
        	}
	}
	
}
