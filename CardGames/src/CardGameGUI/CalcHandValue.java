package CardGameGUI;

import java.util.ArrayList;

public class CalcHandValue {
	//player and dealer totals
	static int hMinTotal = 0;
	static int hMaxTotal = 0;
	
	public static int totalsChecker(ArrayList<Card> Hand) {
		
		int acesCount;
		
		//calculation of player's totals
		hMinTotal = 0;
		hMaxTotal = 0;
		acesCount = 0;
		
		for (Card c : Hand) {
			hMinTotal += c.value;
			hMaxTotal += c.value;
			if (c.name == "Ace")
				acesCount++;
			
		}
		
		if (acesCount > 0) {hMaxTotal += 10;}
		
		acesCount = 0;
		
		if(hMaxTotal>21) {
			return hMinTotal;
		}else {
			return hMaxTotal;
		}
	}
	
	public static int[] totalsPrint(ArrayList<Card> Hand) {
		totalsChecker(Hand);
		int[] printout= new int[2];
		
		if (hMaxTotal==hMinTotal) {
			printout[0]=hMaxTotal;
			printout[1]=0;
			return printout;
		} else if(hMaxTotal>21 && hMinTotal>21) {
			printout[0]=hMinTotal;
			printout[1]=hMaxTotal;
			return printout;
		}else if(hMaxTotal>21 && hMinTotal<21) {
			printout[0]=hMinTotal;
			printout[1]=0;
			return printout;
		}else {
			return printout;
		}
		
	}
	
}
