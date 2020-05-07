package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
	public static User player = new User();
	static Scanner hitorstand = new Scanner(System.in);
	static Scanner yesorno = new Scanner(System.in);
	public static void main(String[] args){
		player.CreatePlayer();
	    while(player.getCash()>0){
	        Deck deck = new Deck();//initialize deck, dealer, hands, and set the bet.
	        deck.shuffle();
	        Dealer dealer = new Dealer(deck);
	        List<Cards> hand = new ArrayList<>();
	        hand.add(deck.drawCard());
	        hand.add(deck.drawCard());
	        player.Gamble();
	        System.out.println(hand);
	        int handvalue = Hand.calcHandValue(hand);
	        System.out.println("The dealer is showing: ");
	        dealer.showFirstCard();
	        
	        //check if both the user and dealer have blackjack.
	        if(BJRules.hasBlackJack(handvalue, "player") && BJRules.hasBlackJack(handvalue, "dealer"))
	        {//player.setCash(BJRules.Push(player.getCash(), player));
	   
	        }
	        
	        //check if the user has blackjack.
	        else if(BJRules.hasBlackJack(handvalue, "player")){
	            System.out.println("You have BlackJack!");
	            System.out.println("You win 2x your money back!");
	            player.setCash(player.getCash()+player.getBet());
	            player.setCash(BJRules.Win(player.getCash(), player.getBet(), player));
	        }
	        
	        //check if the dealer has blackjack.
	        else if (BJRules.hasBlackJack(handvalue,"dealer")){
	            System.out.println("Here is the dealer's hand:");
	            System.out.println(dealer.getHand());
	            player.setBet(BJRules.Lose(player.getCash()));
	        }
	        else{player.DoubleDown();}
	        
	        System.out.println("Would you like to hit or stand?");//ask if the user will hit or stand
	        String hitter = hitorstand.nextLine();
	        while(!isHitorStand(hitter)){
	        	System.out.println("Please enter 'hit' or 'stand'.");
	            hitter = hitorstand.nextLine();
	        }
	            
	        //hits the user as many times as he or she pleases.
	        while(hitter.equalsIgnoreCase("hit")){
	        	Hand.Hit(deck, hand);
	            System.out.println("Your hand is now:");
	            System.out.println(hand);
	            handvalue = Hand.calcHandValue(hand);
	            
	            if(BJRules.CheckHand(handvalue, player, hand)==true) {break;}
	            System.out.println("Would you like to hit or stand?");
	            Hand.calcHandValue(hand);
	            hitter = hitorstand.nextLine();
	        }
	            
	        //lets the user stand.
	        if(hitter.equalsIgnoreCase("stand")){
	        	//takes the turn for the dealer.
	        	int dealerhand = dealer.takeTurn(deck);
  
	        	//BJRules.setWinner(dealerhand, player, handvalue);
	        }
	    	if(player.Contunegame().equals("End")){break;}
	    }
	    player.CurentCash();
	}
	
	//Determines if a user has input hit or stand.
	public static boolean isHitorStand(String hitter)
	{return hitter.equalsIgnoreCase("hit") || hitter.equalsIgnoreCase("stand");}	
}
