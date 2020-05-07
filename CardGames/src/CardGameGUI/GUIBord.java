package CardGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIBord extends JPanel  {	
	private static final long serialVersionUID = 1L;

	//list of messages
	ArrayList<Message2> Log = new ArrayList<Message2>();

	//fonts used
	Font fontCard = new Font("Times New Roman", Font.PLAIN, 40);
	Font fontQuest = new Font("Times New Roman", Font.BOLD, 40);
	Font fontLog = new Font("Times New Roman", Font.ITALIC, 30);
		
	//Log message colors
	Color cDealer = Color.red;
	Color cPlayer = new Color(25,55,255);
		
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
	int aH = 720;
		
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
	ArrayList<Card> pCards = new ArrayList<Card>();
	ArrayList<Card> dCards = new ArrayList<Card>();
	ArrayList<Card> tempHand = new ArrayList<Card>();
	
	int space=0;
	int wager=3;
	
	public int getSpace() {
		return space;
	}
	public void setSpace(int space) {
		this.space = space;
	}
	public ArrayList<Card> getpCards() {
		return pCards;
	}
	public void setpCards(ArrayList<Card> pCards) {
		this.pCards = pCards;
	}
	public ArrayList<Card> getdCards() {
		return dCards;
	}
	public void setdCards(ArrayList<Card> dCards) {
		this.dCards = dCards;
	}
	public ArrayList<Message2> getLog() {
		return Log;
	}
	public void setLog(ArrayList<Message2> log) {
		Log = log;
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
	
	GUIDeck PlayerHand= new GUIDeck();
	public void paintComponent(Graphics g) {
		//background
		g.setColor(colorBackground);
		g.fillRect(0, 0, aW, aH);
		g.setColor(Color.black);
		g.setFont(fontQuest);
		g.drawString("Welcom persons name please enjoy the game:", gridX, gridY);
		
		//questions
		if (hit_stay_q == true) {
			g.setColor(Color.black);
			g.setFont(fontQuest);
			g.drawString(questHitStay, gridX+gridW+60, gridY+50);
			g.drawString("Total:", gridX+gridW+60, gridY+200);
			int[] print=CalcHandValue.totalsPrint(pCards);
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
		for (Message2 L : Log) {
			if (L.getWho().equalsIgnoreCase("Dealer")) {
				g.setColor(cDealer);
			} else {
				g.setColor(cPlayer);
			}
			g.drawString(L.getMessage(), gridX+20, gridY+480+logIndex*35);
			logIndex++;
		}
				
		//score
		g.setColor(Color.BLACK);
		g.setFont(fontQuest);
		String score="Total cash: ";
		g.drawString(score, gridX+gridW-15, gridY+gridH);
		
		String bet= "Money on the table: "+wager;
		g.drawString(bet, gridX+gridW-100, gridY+gridH+40);
		
		//player cards
		PlayerHand.paintCards(g, pCards, 10);
				
		if (dealer_turn == true || play_more_q == true) {
			//dealer cards
			tempHand.clear();
			PlayerHand.paintCards(g, dCards, 210);
			
			int dtest=CalcHandValue.totalsChecker(dCards);
			int test=CalcHandValue.totalsChecker(pCards);
			
			g.setColor(Color.black);
			g.setFont(fontQuest);
			g.drawString("Your total: ", gridX+gridW+60, gridY+40);
			g.drawString(Integer.toString(test), gridX+gridW+60, gridY+120);
			g.drawString("Dealer's total: ", gridX+gridW+60, gridY+240);
			g.drawString(Integer.toString(dtest), gridX+gridW+60, gridY+320);
		}else if(dealer_turn == false && space < 1) {
			space=space+1;
			wager=23;
			tempHand.add(new Card(0, "Blank", 0));
			for(int i=dCards.size(); i>0; i--) {
				int place=i-1;
				tempHand.add(dCards.get(place));
			}
			tempHand.remove(tempHand.get(tempHand.size()-1));
		}

		if(dealer_turn == false) {PlayerHand.paintCards(g, tempHand, 200);}
	}
}
