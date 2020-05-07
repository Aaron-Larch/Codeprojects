package BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private ArrayList<Cards> deck;//represents a deck of cards
	public Deck(){
	    deck = new ArrayList<Cards>();
	    for(int i=0; i<4; i++){ //There are 4 suits of cards
	        for(int j=1; j<=13; j++){ //there are 13 cards in a suit
	            deck.add(new Cards(i,j));
	        }
	    }
	}
	
	//Shuffles the deck by changing the indexes of 200 random pairs of cards in the deck.
	public void shuffle(){
	    Random random = new Random();
	    Cards temp;
	    for(int i=0; i<200; i++){
	        int index1 = random.nextInt(deck.size()-1);
	        int index2 = random.nextInt(deck.size()-1);
	        temp = deck.get(index2);
	        deck.set(index2, deck.get(index1));
	        deck.set(index1, temp);
	    }
	}
	
	public void shuffle2() {Collections.shuffle(deck);}
	
	// Draws a card from the deck.
	public Cards drawCard(){return deck.remove(0);}
}
