package CardGameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.*;

public class GUI extends JFrame {
	//Int value for money gambled
	int cash;
	
	//randomizer for cards
	Random rand = new Random();
	
	//temporary integer used for used status
	int tempC;
	
	//boolean that indicates whether the dealer is thinking or not
	boolean dHitter = false;
	
	//list of cards
	ArrayList<Card> Cards = new ArrayList<Card>();
	
	//list of messages
	ArrayList<Message2> Log = new ArrayList<Message2>();
	
	//fonts used
	Font fontButton = new Font("Times New Roman", Font.PLAIN, 25);
	
	//colors used
	Color colorBackground = new Color(39,119,20);
	Color colorButton = new Color(204,204,0);
	
	//buttons used
	JButton bHit = new JButton();
	JButton bStay = new JButton();
	JButton bYes = new JButton();
	JButton bNo = new JButton();
	
	//screen resolution
	int sW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int sH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//window resolution
	int aW = 1300;
	int aH = 720;
	
	//player and dealer card array
	ArrayList<Card> pCards = new ArrayList<Card>();
	ArrayList<Card> dCards = new ArrayList<Card>();
		
	GUIBord board = new GUIBord();
	
	public GUI() {
		this.setTitle("Blackjack");
		this.setBounds((sW-aW-6)/2, (sH-aH-29)/2, aW+6, aH+29);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setContentPane(board);
		board.setLayout(null);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
		
		//button stuff
		
		ActHit actHit = new ActHit();
		bHit.addActionListener(actHit);
		bHit.setBounds(1000, 120, 100, 50);
		bHit.setBackground(colorButton);
		bHit.setFont(fontButton);
		bHit.setText("HIT");
		board.add(bHit);
		
		ActStay actStay = new ActStay();
		bStay.addActionListener(actStay);
		bStay.setBounds(1150, 120, 100, 50);
		bStay.setBackground(colorButton);
		bStay.setFont(fontButton);
		bStay.setText("STAY");
		board.add(bStay);
		
		ActYes actYes = new ActYes();
		bYes.addActionListener(actYes);
		bYes.setBounds(1000, 600, 100, 50);
		bYes.setBackground(colorButton);
		bYes.setFont(fontButton);
		bYes.setText("YES");
		board.add(bYes);
		
		ActNo actNo = new ActNo();
		bNo.addActionListener(actNo);
		bNo.setBounds(1150, 600, 100, 50);
		bNo.setBackground(colorButton);
		bNo.setFont(fontButton);
		bNo.setText("NO");
		board.add(bNo);
		
		//creating all cards
		
		String temp_str = "starting_temp_str_name";
		for (int i = 0; i < 52; i++) {
			if (i % 4 == 0) {
				temp_str = "Spades";
			} else if (i % 4 == 1) {
				temp_str = "Hearts";
			} else if (i % 4 == 2) {
				temp_str = "Diamonds";
			} else if (i % 4 == 3) {
				temp_str = "Clubs";
			}
			Cards.add(new Card((i/4) + 1, temp_str, i));
		}
		
		tempC = rand.nextInt(52);
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + pCards.get(0).name + " of " + pCards.get(0).shape + " added to the player's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + dCards.get(0).name + " of " + dCards.get(0).shape + " added to the dealer's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();
	//	System.out.println("Card " + pCards.get(1).name + " of " + pCards.get(1).shape + " added to the player's cards.");
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).used == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setUsed();

		board.setpCards(pCards);
		board.setdCards(dCards);
	}
	
	public void dealerHitStay() {
		dHitter = true;
		int dtest=CalcHandValue.totalsChecker(dCards);
		int test=CalcHandValue.totalsChecker(pCards);
		
		int dAvailable = 0;
		dAvailable=dtest;
		
		int pAvailable = 0;
		pAvailable = test;
		
		
		repaint();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ((dAvailable < pAvailable && pAvailable <= 21) || dAvailable < 16) {
			int tempMax = 0;
			tempMax=dtest;
			String mess = ("Dealer decided to hit! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message2(mess, "Dealer"));
			board.setLog(Log);
		//	System.out.println(mess);
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + dCards.get(dCards.size()-1).name + " of " + dCards.get(dCards.size()-1).shape + " added to the dealer's cards.");
		} else {
			int tempMax = 0;
			tempMax=dtest;
			String mess = ("Dealer decided to stay! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message2(mess, "Dealer"));
			board.setLog(Log);
			board.setLog(CardRules.setWinner(CalcHandValue.totalsChecker(pCards), CalcHandValue.totalsChecker(pCards)));
			board.setDealer_turn(false);
			board.setPlay_more_q(true);
		}
		dHitter = false;
	}
	
	public void refresher() {
		
		if (board.isHit_stay_q() == true) {
			bHit.setVisible(true);
			bStay.setVisible(true);
		} else {
			bHit.setVisible(false);
			bStay.setVisible(false);
		}
		
		if (board.isDealer_turn()== true) {
			if (dHitter == false)
				dealerHitStay();
		}
		
		if (board.isPlay_more_q() == true) {
			bYes.setVisible(true);
			bNo.setVisible(true);
		} else {
			bYes.setVisible(false);
			bNo.setVisible(false);
		}
		
		int test=CalcHandValue.totalsChecker(pCards);
		
		if (test >= 21 && board.isHit_stay_q() == true) {
			int tempMax = test;
			String mess = ("Auto pass! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message2(mess, "Player"));
			board.setLog(Log);
			board.setHit_stay_q(false);
			board.setDealer_turn(true);
		}
		
		int dtest=CalcHandValue.totalsChecker(dCards);
		if ( dtest >= 21 && board.isDealer_turn() == true) {
			int tempMax = dtest;			
			String mess = ("Dealer auto pass! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message2(mess, "Dealer"));
			board.setLog(Log);
			board.setLog(CardRules.setWinner(CalcHandValue.totalsChecker(pCards), CalcHandValue.totalsChecker(pCards)));
			board.setDealer_turn(false);
			board.setPlay_more_q(true);
		}
		
		repaint();
	}
	
	public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ActHit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int test=CalcHandValue.totalsChecker(pCards);
			if (board.isHit_stay_q() == true) {
			//	System.out.println("You clicked 'HIT'");
				
				int tempMax = test;
				String mess = ("You decided to hit! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message2(mess, "Player"));
				board.setLog(Log);
				tempC = rand.nextInt(52);
				while (Cards.get(tempC).used == true) {
					tempC = rand.nextInt(52);
				}
				pCards.add(Cards.get(tempC));
				Cards.get(tempC).setUsed();
			//	System.out.println("Card " + pCards.get(pCards.size()-1).name + " of " + pCards.get(pCards.size()-1).shape + " added to the player's cards.");
			}
		}
		
	}
	
	public class ActStay implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int test=CalcHandValue.totalsChecker(pCards);
			if (board.isHit_stay_q() == true) {
			//	System.out.println("You clicked 'STAY'");
				
				int tempMax = test;
				String mess = ("You decided to stay! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message2(mess, "Player"));
				board.setLog(Log);
				board.setHit_stay_q(false);
				board.setDealer_turn(true);
			}
		}
		
	}
	
	public class ActYes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println("You clicked 'YES'");
			System.out.print("This Page Reset");
			for (Card c : Cards) {
				c.setNotUsed();
			}
			
			pCards.clear();
			dCards.clear();
			Log.clear();
			board.setLog(Log);
			board.setSpace(0);
			
			board.setPlay_more_q(false);
			board.setHit_stay_q(true);
			
			tempC = rand.nextInt(52);
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + pCards.get(0).name + " of " + pCards.get(0).shape + " added to the player's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + dCards.get(0).name + " of " + dCards.get(0).shape + " added to the dealer's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
	//		System.out.println("Card " + pCards.get(1).name + " of " + pCards.get(1).shape + " added to the player's cards.");
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).used == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setUsed();
		//	System.out.println("Card " + dCards.get(1).name + " of " + dCards.get(1).shape + " added to the dealer's cards.");
			
		}
		
	}
	
	public class ActNo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		//	System.out.println("You clicked 'NO'");
			Main.terminator = true;
			dispose();
		}
		
	}
	
}

