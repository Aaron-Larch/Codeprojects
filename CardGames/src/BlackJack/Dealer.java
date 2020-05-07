package BlackJack;

import java.util.ArrayList;

//Creates a dealer that the user plays against.
class Dealer {
	ArrayList<Cards> hand;//represents the dealer's hand
	private int handvalue=0;//value of the dealer's hand (starts at 0)
	private Cards[] aHand;//used to convert the dealer's hand to an array
	Dealer(Deck deck){
		hand = new ArrayList<>();
		aHand = new Cards[]{};
		int AceCounter=0;
		for(int i=0; i<2; i++){
			hand.add(deck.drawCard());
		}
		aHand = hand.toArray(aHand);
		for(int i=0; i<aHand.length; i++){
			handvalue += aHand[i].getValue();
			if(aHand[i].getValue()==11){AceCounter++;}
			
			while(AceCounter>0 && handvalue>21){
				handvalue-=10;
				AceCounter--;
			}
    }
}
	// Prints the dealer's first card (the card face up at the beginning of a blackjack game).
	public void showFirstCard(){
		Cards[] firstCard = new Cards[]{};
		firstCard = hand.toArray(firstCard);
		System.out.println("["+firstCard[0]+"]");
	}

	//Determines if the dealer wants to hit according to classic Blackjack rules.
	public boolean wantsToHit(){
		if(handvalue<17){return true;}
		return false;
	}

	//Takes the turn for the dealer and returns the value of his hand.
	public int takeTurn(Deck deck){
		while(wantsToHit()){
			System.out.println("The dealer hits");
			Hand.Hit(deck, hand);
			handvalue=Hand.calcHandValue(hand);
			if(BJRules.busted(handvalue, "dealer")){break;}
		}
		if(handvalue<=21) {System.out.print("The dealer stands.");}
		
		System.out.println("");
        System.out.println("Here is the dealer's hand:");
        System.out.println(getHand());
        
		return handvalue;
	}
	
	//Prints the dealer's hand.
	public ArrayList<Cards> getHand(){return hand;}
	
	//Returns the value of the dealer's hand.
	public int getHandValue(){return handvalue;}
}