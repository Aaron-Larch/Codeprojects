package BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private static int AceCounter;	//how many aces are in the user's hand
	private static boolean AceFlag;	//check the user's hand for two legal values
	private static int handvalue;	//the value of the user's hand
	
	//Calculates the value of a player's hand.
	public static int calcHandValue(List<Cards> hand){
		HeldCards(hand);
		// System.out.println("the value of your hand is: "+ handvalue);
		return handvalue;
	}
	
	//Adds a card to user's hand and calculates the value of that hand. Aces are taken into account.
	public static void Hit(Deck deck, List<Cards> hand){
		hand.add(deck.drawCard());
		HeldCards(hand);
	}
	
	public static void HeldCards(List<Cards> hand) {
		Cards[] aHand = new Cards[]{};
		aHand = hand.toArray(aHand);
		handvalue = 0;
		
		for(int i=0; i<aHand.length; i++)
			{
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue()==11){
				AceCounter++;
				AceFlag=true;
			}
			
			while(AceCounter>0 && handvalue>21)
			{
				handvalue-=10;
				AceCounter--;
				AceFlag=false;
			}
		}
	}
	
	public static int[] totalsPrint(ArrayList<Cards> Hand) {
		int value=calcHandValue(Hand);
		int[] printout= new int[2];
		
		if (AceFlag==true) {
			printout[0]=value-10;
			printout[1]=value;
			return printout;
		} else {
			printout[0]=value;
			printout[1]=0;
			return printout;
		}	
	}
}
