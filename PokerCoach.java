import java.io.File;
import java.io.IOException;
import java.util.*;

public class PokerCoach {
	public static void main(String[] args) throws IOException{
		String[] cards = new String[7];
		String[] suits = new String[7];	
		HashMap<String, String> cardRanks = new HashMap<String, String>();
		cardRanks.put("Ace", "A");
		cardRanks.put("Two", "2");
		cardRanks.put("Three", "3");
		cardRanks.put("Four", "4");
		cardRanks.put("Five", "5");
		cardRanks.put("Six", "6");
		cardRanks.put("Seven","7");
		cardRanks.put("Eight", "8");
		cardRanks.put("Nine", "9");
		cardRanks.put("Ten", "T");
		cardRanks.put("Jack", "J");
		cardRanks.put("Queen", "Q");
		cardRanks.put("King", "K");
		Card[] hand = new Card[7];
		Scanner keyboard = new Scanner(System.in);
		boolean cardCheck = false, seeFlop = false, seeTurn = false, seeRiver = false, seeAnotherHand = false;
		
		do // after river check to see if user wants to play another hand
		{
		do // while loop to see if user wants to see river
		{
		do // while loop to see if user wants to see turn
		{
		do // while loop to see if user wants another hand
		{
		do // get hole cards and see if user wants to see flop
		{
		// get hole cards
		System.out.println("Enter your hole cards: ");
		System.out.println("First card:");
		cards[0] = getCard();
		System.out.println("Suit: ");
		suits[0] = getSuit();
		System.out.println("Second card:");
		cards[1] = getCard();
		System.out.println("Suit: ");
		suits[1] = getSuit();
		// hole card checking
		System.out.println("Your hole cards are: ");
		System.out.println(cards[0]+" of "+suits[0]);
		System.out.println(cards[1]+" of "+suits[1]);
		System.out.println("Is this right?");
		System.out.println("1:  Yes.");
		System.out.println("2:  No.");
		if (keyboard.nextInt() == 1)
			cardCheck = true;
		else
			cardCheck = false;
		
		} while (cardCheck == false);
		File probabilityChart = new File("C:/Users/MontePC/Documents/PreFlop Odds.txt");
		Scanner allProbabilities = new Scanner(probabilityChart);
		String holeCardA = cardRanks.get(cards[0]) + cardRanks.get(cards[1]);
		String holeCardB = cardRanks.get(cards[1]) + cardRanks.get(cards[0]);
		if (!cards[0].equalsIgnoreCase(cards[1]))
		{
			if (suits[0].equalsIgnoreCase(suits[1]))
			{
				holeCardA += "s";
				holeCardB += "s";
			}
			else
			{
				holeCardA += "o";
				holeCardB += "o";
			}
		}
		HashMap<String, String> oddsChart = new HashMap<String, String>();
		while (allProbabilities.hasNext())
		{
			oddsChart.put(allProbabilities.next(), allProbabilities.nextLine());
		}
		System.out.println("Probability of winning if played to showdown");
		System.out.println("    	2 plyrs	3 plyrs	4 plyrs	5 plyrs	6 plyrs	7 plyrs	8 plyrs	9 plyrs	10 plyrs");
		if(oddsChart.containsKey(holeCardA))
			System.out.println(oddsChart.get(holeCardA));
		else
			System.out.println(oddsChart.get(holeCardB));
		// see if user wants to continue or fold
		System.out.println("1.  See Flop.");
		System.out.println("2.  Fold.");
		if (keyboard.nextInt() == 1)
			seeFlop = true;
		else
		{
			System.out.println("See another hand?");
			System.out.println("1.  Yes.");
			System.out.println("2.  No.");
			if (keyboard.nextInt() == 1)
				seeFlop = false;
			else
				System.exit(0);
		}
		} while (seeFlop == false);
		// take flop cards and check input
		do
		{
		System.out.println("Enter the flop cards: ");
		System.out.println("First card:");
		cards[2] = getCard();
		System.out.println("Suit: ");
		suits[2] = getSuit();
		System.out.println("Second card:");
		cards[3] = getCard();
		System.out.println("Suit: ");
		suits[3] = getSuit();
		System.out.println("Third card:");
		cards[4] = getCard();
		System.out.println("Suit: ");
		suits[4] = getSuit();
		System.out.println("The flop is: ");
		System.out.println(cards[2]+" of "+suits[2]);
		System.out.println(cards[3]+" of "+suits[3]);
		System.out.println(cards[4]+" of "+suits[4]);
		System.out.println("Is this right?");
		System.out.println("1:  Yes.");
		System.out.println("2:  No.");
		if (keyboard.nextInt() == 1)
			cardCheck = true;
		else
			cardCheck = false;
		} while (cardCheck == false);
		
		// add cards to Card array for evaluation
		for (int i = 0; i < hand.length; i++)
		{
			hand[i] = new Card(cards[i],suits[i]);
		}

		if (Card.isRoyalFlushFlop(hand))
		{
			System.out.println("You have a Royal Flush.  Cannot be beat.");
		}
		else if (Card.isStraightFlushFlop(hand))
		{
			System.out.println("You have a Straight Flush.");
			System.out.println("1 out to a higher Straight Flush.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("2.13%	2.17%	4.26%");
		}
		else if (Card.isQuadsFlop(hand))
		{
			System.out.println("You have Four of a Kind.  No outs to draw to.");
		}
		else if (Card.isFullHouseFlop(hand))
		{
			System.out.println("You have a Full House.");
			System.out.println("1 out to Quads.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("2.13%	2.17%	4.26%");
			System.out.println("2 outs to other Full House.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("4.26%	4.35%	8.42%");
		}
		else if (Card.isFlushFlop(hand))
		{
			System.out.println("You have a "+Card.isHighCardFlop(hand)+" high Flush.");
			if (!hand[4].card.equals("Ace"))
			{
				int outs = 12 - hand[4].cardRank();
				if (outs > 0)
					{
					System.out.println(outs+" outs to a higher Flush.");
					switch (outs){
					case 1:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("2.13%	2.17%	4.26%");
						break;
					}
					case 2:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("4.26%	4.35%	8.42%");
						break;
					}
					case 3:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("6.38%	6.52%	12.49%");
						break;
					}
					case 4:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("8.51%	8.70%	16.47%");
						break;
					}
					case 5:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("10.64%	10.87%	20.35%");
						break;
					}
					case 6:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("12.77%	13.04%	24.14%");
						break;
					}
					case 7:
					{
						System.out.println("Turn	River	If Played To Showdown");
						System.out.println("14.89%	15.22%	27.84%");
						break;
					}
				}
			}
			}
		}
		else if (Card.isStraightFlop(hand))
		{
			System.out.println("You have a "+Card.isHighCardFlop(hand)+" high Straight.");
			System.out.println("4 outs to a higher Straight.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("8.51%	8.70%	16.47%");
		}
		else if (Card.isTripsFlop(hand))
		{
			System.out.println("You have Three of a Kind.");
			System.out.println("1 out to Quads.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("2.13%	2.17%	4.26%");
			System.out.println("4 outs to a Full House on Turn. 6 outs from Turn to River.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("8.51%	13.04%	~21%");
		}
		else if (Card.isTwoPairFlop(hand))
		{
			System.out.println("You have Two Pair.");
			System.out.println("4 outs to a Full House.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("8.51%	8.70%	16.47%");
		}
		else if (Card.isPairFlop(hand)){
			System.out.println("You have One Pair.");
			System.out.println("2 outs to Three of a Kind.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("4.26%	4.35%	8.42%");
			System.out.println("9  outs to Two Pair.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("19.15%	19.57%	34.97%");
		}
		else 
		{
			System.out.println("You have "+Card.isHighCardFlop(hand)+" high.");
			System.out.println("18 outs to One Pair.");
			System.out.println("Turn	River	If Played To Showdown");
			System.out.println("38.3%	39.13%	62.44%");
		}
		// check if user wants to see the turn
		System.out.println("1.  See Turn.");
		System.out.println("2.  Fold.");
		if (keyboard.nextInt() == 1)
			seeTurn = true;
		else
		{
			// no turn but see if player wants to play another hand
			System.out.println("See another hand?");
			System.out.println("1.  Yes.");
			System.out.println("2.  No.");
			if (keyboard.nextInt() == 1)
				seeTurn = false;
			else
				System.exit(0);
		}
	}while (seeTurn == false);
	// input and check turn card
	do
	{
		System.out.println("Enter the turn card: ");
		System.out.println("Card:");
		cards[5] = getCard();
		System.out.println("Suit: ");
		suits[5] = getSuit();
		System.out.println("The turn is: ");
		System.out.println(cards[5]+" of "+suits[5]);
		System.out.println("Is this right?");
		System.out.println("1:  Yes.");
		System.out.println("2:  No.");
		if (keyboard.nextInt() == 1)
			cardCheck = true;
		else
			cardCheck = false;
	}while (cardCheck == false);
	
	// evaluate full board after turn for best hand available
	hand[5] = new Card(cards[5],suits[5]);
	if (Card.isRoyalFlushTurn(hand))
	{
		System.out.println("You have a Royal Flush.  Cannot be beat.");
	}
	else if (Card.isStraightFlushTurn(hand))
	{
		System.out.println("You have a Straight Flush.");
		System.out.println("1 out to a higher Straight Flush - 2.17%");
	}
	else if (Card.isQuadsTurn(hand) || Card.isQuadsFlop(hand))
	{
		System.out.println("You have Four of a Kind. No outs.");
	}
	else if (Card.isFullHouseTurn(hand) || Card.isFullHouseFlop(hand))
	{
		System.out.println("You have a Full House.");
		System.out.println("1 out to Quads - 2.17%");
		System.out.println("2 outs to other Full House - 4.35%");
	}
	else if (Card.isFlushTurn(hand) || Card.isFlushFlop(hand))
	{
		System.out.println("You have a "+Card.isHighCardTurn(hand)+" high Flush.");
		int outs = 12 - hand[5].cardRank();
		if (outs > 0)
		{
			System.out.print(outs+" outs to a higher Flush - ");
			switch (outs){
			case 1:
			{
				System.out.println(" 2.17%.");
				break;
			}
			case 2:
			{
				System.out.println(" 4.35%.");
				break;
			}
			case 3:
			{
				System.out.println(" 6.52%.");
				break;
			}
			case 4:
			{
				System.out.println(" 8.70%.");
				break;
			}
			case 5:
			{
				System.out.println(" 10.87%.");
				break;
			}
			case 6:
			{
				System.out.println(" 13.04%.");
				break;
			}
			case 7:
			{
				System.out.println(" 15.22%.");
				break;
			}
		}
	}
	}
	else if (Card.isStraightTurn(hand))
	{
		System.out.println("You have a "+Card.isHighCardTurn(hand)+" high Straight.");
		System.out.println("4 outs to a higher Straight - 8.70%.");
	}
	else if (Card.isTripsTurn(hand) || Card.isTripsFlop(hand))
	{
		System.out.println("You have Three of a Kind.");		
		System.out.println("1 out to Quads - 2.17%.");
		System.out.println("6 outs to a Full House - 13.04%.");		
	}
	else if (Card.isTwoPairTurn(hand) || Card.isTwoPairFlop(hand))
	{
		System.out.println("You have Two Pair.");
		System.out.println("4 outs to a Full House - 8.70%.");
	}
	else if (Card.isPairTurn(hand) || Card.isPairFlop(hand)){
		System.out.println("You have One Pair.");
		System.out.println("2 outs to Three of a Kind - 4.35%.");
		System.out.println("12 outs to Two Pair - 26.09%");
	}
	else 
	{
		System.out.println("You have "+Card.isHighCardTurn(hand)+" high.");
	}
	System.out.println("1.  See River.");
	System.out.println("2.  Fold.");
	if (keyboard.nextInt() == 1)
		seeRiver = true;
	else
	{
		// no river but see if player wants to play another hand
		System.out.println("See another hand?");
		System.out.println("1.  Yes.");
		System.out.println("2.  No.");
		if (keyboard.nextInt() == 1)
			seeRiver = false;
		else
			System.exit(0);
	}
	}while (seeRiver == false);
	do
	{
		System.out.println("Enter the river card: ");
		System.out.println("Card:");
		cards[6] = getCard();
		System.out.println("Suit: ");
		suits[6] = getSuit();
		System.out.println("The turn is: ");
		System.out.println(cards[6]+" of "+suits[6]);
		System.out.println("Is this right?");
		System.out.println("1:  Yes.");
		System.out.println("2:  No.");
		if (keyboard.nextInt() == 1)
			cardCheck = true;
		else
			cardCheck = false;
	}while (cardCheck == false);
		
	// evaluate full board after river for best hand available
	hand[6] = new Card(cards[6],suits[6]);
	if (Card.isRoyalFlushRiver(hand))
	{
		System.out.println("You have a Royal Flush.");
	}
	else if (Card.isStraightFlushRiver(hand))
	{
		System.out.println("You have a Straight Flush.");
	}
	else if (Card.isQuadsRiver(hand) || Card.isQuadsTurn(hand) || Card.isQuadsFlop(hand))
	{
		System.out.println("You have Four of a Kind.");
	}
	else if (Card.isFullHouseRiver(hand) || Card.isFullHouseTurn(hand) || Card.isFullHouseFlop(hand))
	{
		System.out.println("You have a Full House.");
	}
	else if (Card.isFlushRiver(hand) || Card.isFlushTurn(hand) || Card.isFlushFlop(hand))
	{
		System.out.println("You have a "+Card.isHighCardRiver(hand)+ "Flush.");
	}
	else if (Card.isStraightRiver(hand))
	{
		System.out.println("You have a "+Card.isHighCardRiver(hand)+ " high Straight.");
	}
	else if (Card.isTripsRiver(hand) || Card.isTripsTurn(hand) || Card.isTripsFlop(hand))
	{
		System.out.println("You have Three of a Kind.");
	}
	else if (Card.isTwoPairRiver(hand) || Card.isTwoPairTurn(hand) || Card.isTwoPairFlop(hand))
	{
		System.out.println("You have Two Pair.");
	}
	else if (Card.isPairRiver(hand) || Card.isPairTurn(hand) || Card.isPairFlop(hand)){
		System.out.println("You have One Pair.");
	}
	else 
	{
		System.out.println("You have "+Card.isHighCardRiver(hand)+" high.");
	}
	System.out.println("See another hand?");
	System.out.println("1.  Yes.");
	System.out.println("2.  No.");
	if (keyboard.nextInt() == 1)
		seeAnotherHand = false;
	else
		System.exit(0);
	}while (seeAnotherHand == false);
	
	}
	
	
	
	
	public static String getCard(){
		int choice = 0;
		System.out.println("1: Ace");
		System.out.println("2. Two");
		System.out.println("3. Three");
		System.out.println("4: Four");
		System.out.println("5. Five");
		System.out.println("6. Six");
		System.out.println("7: Seven");
		System.out.println("8. Eight");
		System.out.println("9. Nine");
		System.out.println("10: Ten");
		System.out.println("11. Jack");
		System.out.println("12. Queen");
		System.out.println("13: King");
		Scanner keyboard = new Scanner(System.in);
		choice = keyboard.nextInt();
		if (choice == 1)
			return "Ace";
		else if (choice == 2)
			return "Two";
		else if (choice == 3)
			return "Three";
		else if (choice == 4)
			return "Four";
		else if (choice == 5)	
			return "Five";
		else if (choice == 6)
			return "Six";
		else if (choice == 7)
			return "Seven";
		else if (choice == 8)
			return "Eight";
		else if (choice == 9)
			return "Nine";
		else if (choice == 10)
			return "Ten";
		else if (choice == 11)
			return "Jack";
		else if (choice == 12)
			return "Queen";
		else
			return "King";
	}
	
	public static String getSuit(){
		int choice = 0;
		System.out.println("1: Spades");
		System.out.println("2. Diamonds");
		System.out.println("3. Clubs");
		System.out.println("4: Hearts");
		Scanner keyboard = new Scanner(System.in);
		choice = keyboard.nextInt();
		if (choice == 1)
			return "Spades";
		else if (choice == 2)
			return "Diamonds";
		else if (choice == 3)
			return "Clubs";
		else
			return "Hearts";
	}
}
