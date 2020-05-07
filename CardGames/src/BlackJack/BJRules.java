package BlackJack;

import java.util.ArrayList;
import java.util.List;

import CardGameGUI.Main;
import CardGameGUI.Message2;

public class BJRules {
	 //list of messages
	static ArrayList<Message> Log = new ArrayList<Message>();
	
	//Determines if a dealer or player has busted.
	public static boolean busted(int handvalue, String Role){
	    if(handvalue>21){
	        if(Role.equalsIgnoreCase("dealer")){
	        	Log.add(new Message("The dealer busted!", Role));
	        	System.out.println("The dealer busted!");
	        return true;
	        }
	        else if(Role.equalsIgnoreCase("player")){
	        	Log.add(new Message("You have busted!", Role));
	        	System.out.println("You have busted!");
	     	    return true; 
	        }
	    }
	    return false;
	}
	
	//Returns true if the dealer has blackjack.
	public static boolean hasBlackJack(int handvalue, String Role){
	    if(handvalue==21){
	    	if(Role.equalsIgnoreCase("dealer")){
	    		Log.add(new Message("The dealer has blackjack!", Role));
	    		System.out.println("The dealer has blackjack!");
	    		return true;
	        }
	    	else if(Role.equalsIgnoreCase("player")){
	    		Log.add(new Message("You have blackjack!", Role));
	    		System.out.println("You have BlackJack!");
	    		return true;
	        }
	    }
	    return false;
	}
	
	//Called if the user wins.
	public static int Win(int cash, int bet, User Player){
		Log.add(new Message("Congratulations, you win!", "Player"));
	    System.out.println("Congratulations, you win!");
	    cash+=(bet*2);
	    Player.setBet(0);
	    Log.add(new Message("Cash: "+cash, "Money"));
	    System.out.println("Cash: "+cash);
	    return cash;
	}
	
	//Called if the user loses.
	public static int Lose(int cash){
		Log.add(new Message("Sorry, you lose!", "Player"));
	    System.out.println("Sorry, you lose!");
	    Log.add(new Message("Cash: "+cash, "Money"));
	    System.out.println("Cash: "+cash);
	    return 0;
	}
	
	//Called if the user pushes
	public static int Push(int cash,int bet, User Player){
		Log.add(new Message("It's a push!", "Player"));
		System.out.println("It's a push!");
	    
		Log.add(new Message("You get your money back.", "Player"));
		System.out.println("You get your money back.");
		cash+=bet;
		Log.add(new Message("Cash: "+cash, "Money"));
	    System.out.println("Cash: "+cash);
	    Player.setBet(0);
	    return cash;
	}
	
	//Called if the user has a five card trick.
	public static int fivecardtrick(int cash, int bet, User Player){
		Log.add(new Message("You have achieved a five card trick!", "Player"));
	    System.out.println("You have achieved a five card trick!");
	    cash=Win(cash, bet, Player)+bet;
	    return cash;
	}
	
	public static void setWinner(int dealerhand, User player, int handvalue, GUIBord board) {
        //if the dealer busted, user wins.
        if(busted(dealerhand, "Dealer")){
        	player.setCash(Win(player.getCash(), player.getBet(), player));
        	board.setLog(getLog());
        	board.setDealer_turn(false);
			board.setPlay_more_q(true);
        }else{
        	int you = 21-handvalue;//check who is closer to 21 and determine winner
            int deal = 21-dealerhand;
            if(you==deal){
            	player.setCash(Push(player.getCash(), player.getBet(), player));
            	board.setLog(getLog());
            	board.setDealer_turn(false);
    			board.setPlay_more_q(true);
            }
            if(you<deal){
            	player.setCash(Win(player.getCash(), player.getBet(), player));
            	board.setLog(getLog());
            	board.setDealer_turn(false);
    			board.setPlay_more_q(true);
            }
            if(deal<you){
            	player.setBet(Lose(player.getCash()));
            	board.setLog(getLog());
            	board.setDealer_turn(false);
    			board.setPlay_more_q(true);
            }
        }
	}
	
	public static Boolean CheckHand(int handvalue, User player, List<Cards> hand) {    
		//checks if the user busted
		if(busted(handvalue, "player")){
			player.setBet(Lose(player.getCash()));
			return true;
		}
		//checks for a five card trick.
		else if(handvalue<=21 && hand.size()==5){
			player.setCash(fivecardtrick(player.getCash(), player.getBet(), player));
			return true;
        }
		//checks for a 21.
		else if(handvalue==21){
			player.setCash(BJRules.Win(player.getCash(), player.getBet(), player));
			return true;
		}
		else{return false;}
	}
	
	public static ArrayList<Message> getLog(){
		return Log;
	}
	
	public static void CheckHandGUI(GUIBord board, int handvalue, User player, List<Cards> hand) {    
		//checks if the user busted
		if(busted(handvalue, "player")){
			player.setBet(Lose(player.getCash()));
			board.setLog(getLog());
			board.setHit_stay_q(false);
			board.setPlay_more_q(true);
		}
		
		//checks for a five card trick.
		if(handvalue<=21 && hand.size()==5){
			player.setCash(fivecardtrick(player.getCash(), player.getBet(), player));
			board.setLog(getLog());
			board.setHit_stay_q(false);
			board.setPlay_more_q(true);
        }
		
		//checks for a 21.
		if(handvalue==21){
			player.setCash(BJRules.Win(player.getCash(), player.getBet(), player));
			board.setLog(getLog());
			board.setHit_stay_q(false);
			board.setPlay_more_q(true);
		}
	}
}
