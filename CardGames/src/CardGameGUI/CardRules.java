package CardGameGUI;

import java.util.ArrayList;

public class CardRules {
	public static ArrayList<Message2> setWinner(int player, int dealer) {
		//list of messages
		ArrayList<Message2> Log = new ArrayList<Message2>();
		
		if ((player > 21 && dealer > 21)) {
			Log.add(new Message2("Nobody wins!", "Push"));
		} else if ((player < 21 && dealer < 21) && dealer < player) {
			Log.add(new Message2("You win!", "Player"));
			Main.pWins++;
		} else if ((player < 21 && dealer < 21) && dealer > player) {
			Log.add(new Message2("Dealer wins!", "Dealer"));
			Main.dWins++;
		} else if (player == 21) {
			Log.add(new Message2("You win! Blackjack", "Player"));
			Main.pWins++;
			
		} else {
			Log.add(new Message2("Dealer wins! Blackjack", "Dealer"));
			Main.dWins++;
		}
		return Log;
	}
}
