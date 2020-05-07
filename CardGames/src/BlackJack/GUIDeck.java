package BlackJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class GUIDeck {
	//fonts used
	Font fontCard = new Font("Times New Roman", Font.PLAIN, 40);
	
	//card grid position and dimensions
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
	
	//card spacing and dimensions
	int spacing = 10;
	int rounding = 10;
	int tCardW = (int) gridW/6;
	int tCardH = (int) gridH/2;
	int cardW = tCardW - spacing*2;
	int cardH = tCardH - spacing*2;
	
	//polygons for diamond shapes
	int[] polyX = new int[4];
	int[] polyY = new int[4];
	
	//Create a card
	public void paintCards(Graphics g, ArrayList<Cards> Cards, int location) {
		int index = 0;
		for (Cards c : Cards) {
			g.setColor(Color.white);
			g.fillRect(gridX+spacing+tCardW*index+rounding, gridY+spacing+location, cardW-rounding*2, cardH);
			g.fillRect(gridX+spacing+tCardW*index, gridY+spacing+rounding+location, cardW, cardH-rounding*2);
			g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+location, rounding*2, rounding*2);
			g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+cardH-rounding*2+location, rounding*2, rounding*2);
			g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+location, rounding*2, rounding*2);
			g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+cardH-rounding*2+location, rounding*2, rounding*2);
	
			g.setFont(fontCard);
			if (c.getSuit().equalsIgnoreCase("Hearts") || c.getSuit().equalsIgnoreCase("Diamonds")) {
				g.setColor(Color.red);
			} else if (c.getSuit().equalsIgnoreCase("Spades") || c.getSuit().equalsIgnoreCase("Clubs")) {
				g.setColor(Color.black);
			}else {
				g.setColor(Color.DARK_GRAY);
			}
		
			g.drawString(c.getSymbol(), gridX+spacing+tCardW*index+rounding, gridY+spacing+cardH-rounding+location);
		
			if (c.getSuit().equalsIgnoreCase("Hearts")) {
				g.fillOval(gridX+tCardW*index+42, gridY+70+location, 35, 35);
				g.fillOval(gridX+tCardW*index+73, gridY+70+location, 35, 35);
				g.fillArc(gridX+tCardW*index+30, gridY+90+location, 90, 90, 51, 78);
			} else if (c.getSuit().equalsIgnoreCase("Diamonds")) {
				polyX[0] = gridX+tCardW*index+75;
				polyX[1] = gridX+tCardW*index+50;
				polyX[2] = gridX+tCardW*index+75;
				polyX[3] = gridX+tCardW*index+100;
				polyY[0] = gridY+60+location;
				polyY[1] = gridY+100+location;
				polyY[2] = gridY+140+location;
				polyY[3] = gridY+100+location;
				g.fillPolygon(polyX, polyY, 4);
			} else if (c.getSuit().equalsIgnoreCase("Spades")) {
				g.fillOval(gridX+tCardW*index+42, gridY+90+location, 35, 35);
				g.fillOval(gridX+tCardW*index+73, gridY+90+location, 35, 35);
				g.fillArc(gridX+tCardW*index+30, gridY+15+location, 90, 90, 51+180, 78);
				g.fillRect(gridX+tCardW*index+70, gridY+100+location, 10, 40);
			}  else if (c.getSuit().equalsIgnoreCase("clubs")){
				g.fillOval(gridX+tCardW*index+40, gridY+90+location, 35, 35);
				g.fillOval(gridX+tCardW*index+75, gridY+90+location, 35, 35);
				g.fillOval(gridX+tCardW*index+58, gridY+62+location, 35, 35);
				g.fillRect(gridX+tCardW*index+70, gridY+75+location, 10, 70);
			}else {	//Hide card value
				g.fillRect(gridX+spacing+tCardW*index+rounding, gridY+spacing+location, cardW-rounding*2, cardH);
				g.fillRect(gridX+spacing+tCardW*index, gridY+spacing+rounding+location, cardW, cardH-rounding*2);
				g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+location, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index, gridY+spacing+cardH-rounding*2+location, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+location, rounding*2, rounding*2);
				g.fillOval(gridX+spacing+tCardW*index+cardW-rounding*2, gridY+spacing+cardH-rounding*2+location, rounding*2, rounding*2);
			}
		
			//-------------------------
			index++;
		}
	}

}
