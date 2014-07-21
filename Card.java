import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Card {
	protected String card;
	protected String suit;
	protected int cardRank, suitRank;
	protected final String[] cardRanks = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
	protected final String[] suitRanks = {"Spades","Diamonds","Clubs","Hearts"};
	
	// constructor duhhhhhh

	public Card(String card, String suit) {
		this.card = card;
		this.suit = suit;
		for (int i = 0; i < cardRanks.length;i++)
		{
			if (cardRanks[i].equalsIgnoreCase(this.card))
				cardRank = i;
		}
		for (int i = 0; i < suitRanks.length; i++)
		{
			if (suitRanks[i].equalsIgnoreCase(this.suit))
				suitRank = i;
		}
	}
	public Card(Card copy){
		this.card = copy.card;
		this.suit = copy.suit;
	}
	// organize by grouping like suits together, only for flush checking
	public static void sortBySuitFlop(Card[] suits){
		int min_value = 0;
		for (int h = 0; h < 5; h++)
		{
			min_value = h;
			for (int i = h+1; i < 5; i++)
			{
				if (suits[i].suitRank() < suits[min_value].suitRank())
				{
					Card switchCard = suits[min_value];
					suits[min_value] = suits[i];
					suits[i] = switchCard;
				}
			}
		}
	}
	// organize suits including turn card
	public static void sortBySuitTurn(Card[] suits){
		int min_value = 0;
		for (int h = 0; h < 6; h++)
		{
			min_value = h;
			for (int i = h+1; i < 6; i++)
			{
				if (suits[i].suitRank() < suits[min_value].suitRank())
				{
					Card switchCard = suits[min_value];
					suits[min_value] = suits[i];
					suits[i] = switchCard;
				}
			}
		}
	}
	// organize suits including river
	public static void sortBySuitRiver(Card[] suits){
		int min_value = 0;
		for (int h = 0; h < 7; h++)
		{
			min_value = h;
			for (int i = h+1; i < 7; i++)
			{
				if (suits[i].suitRank() < suits[min_value].suitRank())
				{
					Card switchCard = suits[min_value];
					suits[min_value] = suits[i];
					suits[i] = switchCard;
				}
			}
		}
	}
	// arbitrary suit value for organizing suits
	public int suitRank(){
		return suitRank;
	}
	// organize hand by face value
	public static void sortByCardFlop(Card[] cards){
		int min_value = 0;
		for (int h = 0; h < 5; h++)
		{
			min_value = h;
			for (int i = h+1; i < 5; i++)
			{
				if (cards[i].cardRank() < cards[min_value].cardRank())
				{
					Card switchCard = cards[min_value];
					cards[min_value] = cards[i];
					cards[i] = switchCard;
				}
			}
		}
	}
	// organize hand on turn by face value
	public static void sortByCardTurn(Card[] cards){
		for (int h = 0; h < 6; h++)
		{
			int min_value = h;
			for (int i = h+1; i < 6; i++)
			{
				if (cards[i].cardRank() < cards[min_value].cardRank())
				{
					Card switchCard = cards[min_value];
					cards[min_value] = cards[i];
					cards[i] = switchCard;
				}
			}
		}
	}
	// organize hand on river by face value
	public static void sortByCardRiver(Card[] cards){
		int min_value = 0;
		for (int h = 0; h < 7; h++)
		{
			min_value = h;
			for (int i = h+1; i < 7; i++)
			{
				if (cards[i].cardRank() < cards[min_value].cardRank())
				{
					Card switchCard = cards[min_value];
					cards[min_value] = cards[i];
					cards[i] = switchCard;
				}
			}
		}
	}
	// checking face value
	public int cardRank(){
		return cardRank;
	}// for checking for straight flushes
	public static void sortBySuitAndCardTurn(Card[] cards){
		sortBySuitTurn(cards);
		if (cards[0].suitRank() == cards[5].suitRank())
			sortByCardTurn(cards);
		else if (cards[0].suitRank == cards[4].suitRank())
			sortByCardFlop(cards);
		else if (cards[0].suitRank == cards[3].suitRank() && cards[4].suitRank() == cards[5].suitRank())
		{
			for (int h = 0; h < 4; h++)
			{
				int minValue = h;
				for (int i = 1; i < 4; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 4; h < 6; h++)
			{
				int minValue = h;
				for (int i = 5; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[3].suitRank())
		{
			for (int h = 0; h < 4; h++)
			{
				int minValue = h;
				for (int i = 1; i < 4; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[2].suitRank() && cards[3].suitRank == cards[5].suitRank())
		{
			for (int h = 0; h < 3; h++)
			{
				int minValue = h;
				for (int i = 1; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[2].suitRank() && cards[3].suitRank == cards[4].suitRank())
		{
			for (int h = 0; h < 3; h++)
			{
				int minValue = h;
				for (int i = 1; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 5; h++)
			{
				int minValue = h;
				for (int i = 4; i < 5; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[2].suitRank() && cards[4].suitRank == cards[5].suitRank())
		{
			for (int h = 0; h < 3; h++)
			{
				int minValue = h;
				for (int i = 1; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 4; h < 6; h++)
			{
				int minValue = h;
				for (int i = 5; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[2].suitRank())
		{
			for (int h = 0; h < 3; h++)
			{
				int minValue = h;
				for (int i = 1; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[1].suitRank() && cards[3].suitRank == cards[5].suitRank())
		{
			for (int h = 0; h < 2; h++)
			{
				int minValue = h;
				for (int i = 1; i < 2; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[2].suitRank() && cards[3].suitRank == cards[5].suitRank())
		{
			for (int h = 1; h < 3; h++)
			{
				int minValue = h;
				for (int i = 2; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[3].suitRank == cards[5].suitRank())
		{
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[0].suitRank == cards[1].suitRank() && cards[2].suitRank() == cards[5].suitRank())
		{
			for (int h = 0; h < 2; h++)
			{
				int minValue = h;
				for (int i = 1; i < 2; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 2; h < 6; h++)
			{
				int minValue = h;
				for (int i = 3; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[2].suitRank() == cards[5].suitRank())
		{
			for (int h = 2; h < 6; h++)
			{
				int minValue = h;
				for (int i = 3; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			for (int h = 1; h < 6; h++)
			{
				int minValue = h;
				for (int i = 2; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank() == cards[4].suitRank())
		{
			for (int h = 1; h < 5; h++)
			{
				int minValue = h;
				for (int i = 2; i < 5; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[3].suitRank() && cards[4].suitRank() == cards[5].suitRank())
		{
			for (int h = 1; h < 4; h++)
			{
				int minValue = h;
				for (int i = 2; i < 4; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 4; h < 6; h++)
			{
				int minValue = h;
				for (int i = 5; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[3].suitRank())
		{
			for (int h = 1; h < 4; h++)
			{
				int minValue = h;
				for (int i = 2; i < 4; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[2].suitRank() && cards[3].suitRank() == cards[5].suitRank())
		{
			for (int h = 1; h < 3; h++)
			{
				int minValue = h;
				for (int i = 2; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[3].suitRank() == cards[5].suitRank())
		{
			for (int h = 3; h < 6; h++)
			{
				int minValue = h;
				for (int i = 4; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[2].suitRank() && cards[3].suitRank == cards[4].suitRank())
		{
			for (int h = 1; h < 3; h++)
			{
				int minValue = h;
				for (int i = 2; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
			for (int h = 3; h < 5; h++)
			{
				int minValue = h;
				for (int i = 4; i < 5; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[1].suitRank == cards[2].suitRank())
		{
			for (int h = 1; h < 3; h++)
			{
				int minValue = h;
				for (int i = 2; i < 3; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[3].suitRank == cards[4].suitRank())
		{
			for (int h = 3; h < 5; h++)
			{
				int minValue = h;
				for (int i = 4; i < 5; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
		else if (cards[2].suitRank == cards[3].suitRank())
		{
			for (int h = 2; h < 4; h++)
			{
				int minValue = h;
				for (int i = 3; i < 4; i++)
				{
					if (cards[i].cardRank() < cards[minValue].cardRank())
					{
						Card swap = cards[minValue];
						cards[minValue] = cards[i];
						cards[i] = swap;
					}
				}
			}
		}
	}
	public static void sortBySuitAndCardRiver(Card[] cards){
		sortBySuitRiver(cards);
		if (cards[0].suitRank() == cards[6].suitRank())
			sortByCardRiver(cards);
		else if (cards[0].suitRank() == cards[5].suitRank())
			sortByCardTurn(cards);
		else if (cards[0].suitRank() == cards[4].suitRank())
			sortByCardFlop(cards);
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			for (int h = 1; h < 6; h++)
			{
				int min_value = h;
				for (int i = h+1; i < 6; i++)
				{
					if (cards[i].cardRank() < cards[min_value].cardRank())
					{
						Card switchCard = cards[min_value];
						cards[min_value] = cards[i];
						cards[i] = switchCard;
					}
				}
			}
		}
		else if (cards[2].suitRank() == cards[6].suitRank())
		{
			for (int h = 2; h < 7; h++)
			{
				int min_value = h;
				for (int i = h+1; i < 7; i++)
				{
					if (cards[i].cardRank() < cards[min_value].cardRank())
					{
						Card switchCard = cards[min_value];
						cards[min_value] = cards[i];
						cards[i] = switchCard;
					}
				}
			}
		}
	}
	// check to see if we have a flush on the flop
	public static boolean isFlushFlop(Card[] cards){
		boolean flush = false;
		sortBySuitFlop(cards);
		if (cards[0].suitRank() == cards[4].suitRank())
			flush = true;
		return flush;
	}
	// check for a flush draw on the flop
	public static boolean isFlushDrawFlop(Card[] cards){
		boolean flushDraw = false;
		if (isFlushFlop(cards))
			return flushDraw;
		sortBySuitFlop(cards);
		if (cards[0].suitRank() == cards[3].suitRank())
			flushDraw = true;
		else if (cards[1].suitRank() == cards[4].suitRank())
			flushDraw = true;
		return flushDraw;
	}
	// check to see if we have a flush on the turn
	public static boolean isFlushTurn(Card[] cards){
		boolean flush = false;
		sortBySuitTurn(cards);
		if (cards[0].suitRank() == cards[4].suitRank() || cards[1].suitRank() == cards[5].suitRank())
			flush = true;
		return flush;
	}
	// check for a flush draw on the turn
	public static boolean isFlushDrawTurn(Card[] cards){
		boolean flush = false;
		if (isFlushTurn(cards))
			return flush;
		sortBySuitTurn(cards);
		if (cards[0].suitRank() == cards[3].suitRank())
			flush = true;
		else if (cards[1].suitRank() == cards[4].suitRank())
			flush = true;
		else if (cards[2].suitRank() == cards[5].suitRank())
			flush = true;
		return flush;
	}
	// check to see if we have a flush on the river
	public static boolean isFlushRiver(Card[] cards){
		boolean flush = false;
		sortBySuitRiver(cards);
		if (cards[0].suitRank() == cards[4].suitRank() || cards[1].suitRank() == cards[5].suitRank() || cards[2].suitRank() == cards[6].suitRank())
			flush = true;
		return flush;
	}
	// check to see if we have a straight on the flop
	public static boolean isStraightFlop(Card[] cards){
		boolean straight = false;
		sortByCardFlop(cards);
		// if there's an Ace, we need specific check for wheel (Ace through five) and broadway (10 through Ace) straights
		if (cards[4].card.equalsIgnoreCase("Ace"))
		{
			if (cards[0].card.equals("Two") && cards[1].card.equals("Three") && cards[2].card.equals("Four") && cards[3].card.equals("Five"))
				straight = true;
			else if (cards[0].card.equals("Ten") && cards[1].card.equals("Jack") && cards[2].card.equals("Queen") && cards[3].card.equals("King"))
				straight = true;
		}
		else // check for any other straights (five sequential cards)
		{
			int incrementCard = cards[0].cardRank()+1;
			for (int i = 1; i < 5; i++)
			{
				if (cards[i].cardRank() != incrementCard)
					return straight;
				incrementCard++;
			}
			straight = true;
		}
		return straight;
	}
	// check for open ended straight draw on flop
	public static boolean isOpenStraightDrawFlop(Card[] cards){
		boolean straightDraw = false;
		if (isStraightFlop(cards))
			return straightDraw;
		sortByCardFlop(cards);
		int counterA = 1, counterB = 1;
		int incrementCard = cards[0].cardRank()+1;
		for (int i = 1; i < 4; i++)
		{
			if (cards[i].cardRank() == incrementCard)
				counterA++;
			incrementCard++;
		}
		incrementCard = cards[1].cardRank()+1;
		for (int i = 2; i < 5; i++)
		{
			if (cards[i].cardRank() == incrementCard)
				counterB++;
			incrementCard++;
		}
		if (isPairFlop(cards) && cards[0].cardRank() != cards[1].cardRank())
		{
			counterA -= 1;
			counterB -= 1;
		}
		if (counterA == 4 || counterB == 4)
			straightDraw = true;
		return straightDraw;
	}
	public static boolean isDoubleGutStraightDrawFlop(Card[] cards){
		boolean straightDraw = false;
		if (isOpenStraightDrawFlop(cards))
			return straightDraw;
		sortByCardFlop(cards);
		int counterA = 1, counterB = 1;
		for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[1].cardRank()+5; incrementCard++)
		{	
			for (int i = 2; i < 5; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterB++;
			}
		}
		for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
		{	
			for (int i = 1; i < 4; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterA++;
			}
		}
		if (counterA == 4 && counterB == 4)
			straightDraw = true;
		return straightDraw;
	}
	public static boolean isGutStraightDrawFlop(Card[] cards){
		boolean straightDraw = false;
		if (isDoubleGutStraightDrawFlop(cards))
			return straightDraw;
		sortByCardFlop(cards);
		int counterA = 2, counterB = 2, counterC = 2;
		if (cards[4].cardRank() == 12 && cards[0].cardRank() == 0)
		{
			for (int incrementCard = 1; incrementCard < 4; incrementCard++)
				{
					for (int s = 1; s < 4; s++)
					{
						if (cards[s].cardRank() == incrementCard)
							counterC++;
					}
				}
		}
		for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
		{	
			for (int i = 1; i < 4; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterA++;
			}
		}
		for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[1].cardRank()+5; incrementCard++)
		{	
			for (int i = 2; i < 5; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterB++;
			}
		}
		if (isPairFlop(cards) && cards[0].cardRank() != cards[1].cardRank())
		{
			counterA -= 1;
			counterB -= 1;
			counterC -= 1;
		}
		if (counterA == 4 || counterB == 4 || counterC == 4)
			straightDraw = true;
		return straightDraw;
	}
	// check for straight on the turn.  using a counter to count number of instances of cards within a straight range (five or more sequential cards)
	// and a check for pairs or more to account for duplicates
		public static boolean isStraightTurn(Card[] cards){
			boolean straight = false;
			int counterA = 1, counterB = 1, wheel = 2, broadway = 2;
			sortByCardTurn(cards);
			// if there's an ace, need a separate check for a wheel (Ace through five straight) or broadway (10 through Ace straight)
			if ((cards[5].cardRank() == 12 && cards[0].cardRank() == 0) || (cards[5].cardRank() == 12 && cards[0].cardRank() == 8)
					|| (cards[5].cardRank() == 12 && cards[1].cardRank() == 8))
			{// check for wheel
				for (int incrementCard = 1; incrementCard < 4; incrementCard++)
				{
					for (int i = 1; i < 5; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							wheel++;
					}
				}// check for broadway
				for (int incrementCard = 9; incrementCard < 12; incrementCard++)
				{
					for (int i = 1; i < 5; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							broadway++;
					}
				}
				// readjusting counter to account for multiple cards (pairs, trips)
				if (isTripsTurn(cards) || isTripsFlop(cards))
				{
					wheel = wheel - 2;
					broadway  = broadway - 2;
				}
				else if (isTwoPairTurn(cards) || isTwoPairTurn(cards))
				{
					wheel = wheel - 2;
					broadway  = broadway - 2;
				}
				else if (isPairTurn(cards) || isPairFlop(cards))
				{
					wheel = wheel - 1;
					broadway = broadway - 1;
				}
				if (wheel >= 5 || broadway >= 5)
					straight = true;
			}
			else // check for any other straight
			{// checking for a straight from the first element of the array
				for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
				{
					for (int i = 1; i < 6; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							counterA++;
					}
				}// checking for a straight in the second five elements of the array
				for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
				{
					for (int i = 2; i < 6; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							counterB++;
					}
				}// adjusting the straight counter to remove any duplicates (pairs or more)
				if (isTripsTurn(cards) || isTripsFlop(cards))
				{
					counterA = counterA - 2;
					counterB = counterB - 2;
				}
				else if (isTwoPairTurn(cards) || isTwoPairTurn(cards))
				{
					counterA = counterA - 2;
					counterB = counterB - 2;
				}
				else if (isPairTurn(cards) || isPairFlop(cards))
				{
					counterA = counterA - 1;
					counterB = counterB - 1;
				}
				if (counterB >= 5 || counterA >= 5)
					straight = true;
			}
			return straight;
		}
		// check for open ended straight draw on the turn
		public static boolean isOpenStraightDrawTurn(Card[] cards){
			boolean straightDraw = false;
			if (isStraightTurn(cards))
				return straightDraw;
			sortByCardTurn(cards);
			int counterA = 1, counterB = 1, counterC = 1;
			int incrementCard = cards[0].cardRank()+1;
			for (int i = 1; i < 4; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterA++;
				incrementCard++;
			}
			incrementCard = cards[1].cardRank()+1;
			for (int i = 2; i < 5; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterB++;
				incrementCard++;
			}
			incrementCard = cards[2].cardRank()+1;
			for (int i = 3; i < 6; i++)
			{
				if (cards[i].cardRank() == incrementCard)
					counterC++;
				incrementCard++;
			}
			if (isTwoPairTurn(cards) && cards[0].cardRank() == cards[1].cardRank())
			{
				counterA -= 1;
				counterB -= 1;
				counterC -= 1;
			}
			else if (isTwoPairTurn(cards))
			{
				counterA -= 2;
				counterB -= 2;
				counterC -= 2;
			}
			else if (isPairTurn(cards) && cards[0].cardRank() != cards[1].cardRank())
			{
				counterA -= 1;
				counterB -= 1;
				counterC -= 1;
			}
			if (counterA == 4 || counterB == 4 || counterC == 4)
				straightDraw = true;
			return straightDraw;
		}
		public static boolean isDoubleGutStraightDrawTurn(Card[] cards){
			boolean straightDraw = false;
			if (isOpenStraightDrawTurn(cards))
				return straightDraw;
			sortByCardTurn(cards);
			int counterA = 1, counterB = 1, counterC = 1;
			for (int incrementCard = cards[2].cardRank()+1; incrementCard < cards[2].cardRank()+5; incrementCard++)
			{	
				for (int i = 3; i < 6; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterC++;
				}
			}
			for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[1].cardRank()+5; incrementCard++)
			{	
				for (int i = 2; i < 5; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterB++;
				}
			}
			for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
			{	
				for (int i = 1; i < 4; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterA++;
				}
			}
			if (isTwoPairTurn(cards) && cards[0].cardRank() == cards[1].cardRank())
			{
				counterA -= 1;
				counterB -= 1;
				counterC -= 1;
			}
			else if (isTwoPairTurn(cards))
			{
				counterA -= 2;
				counterB -= 2;
				counterC -= 2;
			}
			else if (isPairTurn(cards) && cards[0].cardRank() != cards[1].cardRank())
			{
				counterA -= 1;
				counterB -= 1;
				counterC -= 1;
			}
			if (counterA == 4 && counterB == 4 || counterB == 4 && counterC == 4)
				straightDraw = true;
			return straightDraw;
		}
		public static boolean isGutStraightDrawTurn(Card[] cards){
			boolean straightDraw = false;
			if (isDoubleGutStraightDrawTurn(cards))
				return straightDraw;
			sortByCardTurn(cards);
			int counterA = 1, counterB = 1, counterC = 1, counterD = 2;
			if (cards[4].cardRank() == 12 && cards[0].cardRank() == 0 || cards[5].cardRank() == 12 && cards[0].cardRank() == 0 || cards[5].cardRank() == 12 && cards[1].cardRank() == 0)
			{
				if (cards[4].cardRank == 12 && cards[0].cardRank == 0)
				{
					for (int incrementCard = 1; incrementCard < 4; incrementCard++)
					{
						for (int s = 1; s < 4; s++)
						{
							if (cards[s].cardRank() == incrementCard)
								counterD++;
						}
					}
				}
				else if (cards[5].cardRank == 12 && cards[0].cardRank == 0)
				{
					for (int incrementCard = 1; incrementCard < 4; incrementCard++)
					{
						for (int s = 1; s < 5; s++)
						{
							if (cards[s].cardRank() == incrementCard)
								counterD++;
						}
					}
				}
				else if (cards[5].cardRank == 12 && cards[1].cardRank == 0)
				{
					for (int incrementCard = 1; incrementCard < 4; incrementCard++)
					{
						for (int s = 1; s < 5; s++)
						{
							if (cards[s].cardRank() == incrementCard)
								counterD++;
						}
					}
				}
				
			}
			for (int incrementCard = cards[2].cardRank()+1; incrementCard < cards[2].cardRank()+5; incrementCard++)
			{	
				for (int i = 3; i < 6; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterC++;
				}
			}
			for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[1].cardRank()+5; incrementCard++)
			{	
				for (int i = 2; i < 5; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterB++;
				}
			}
			for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
			{	
				for (int i = 1; i < 4; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counterA++;
				}
			}
			if (isTwoPairTurn(cards) && cards[0].cardRank() == cards[1].cardRank())
			{
				counterA -= 1;
				counterB -= 1;
				counterC -= 1;
				counterD -= 1;
			}
			else if (isTwoPairTurn(cards))
			{
				counterA -= 2;
				counterB -= 2;
				counterC -= 2;
				counterD -= 2;
			}
			else if (isPairTurn(cards))
			{
				if (!(cards[0].cardRank() == cards[1].cardRank() && cards[1].cardRank()+1 != cards[2].cardRank()) &&
						!(cards[4].cardRank() == cards[5].cardRank() && cards[4].cardRank()-1 != cards[3].cardRank()))
				{
					counterA -= 1;
					counterB -= 1;
					counterC -= 1;
					counterD -= 1;
				}
			}
			if (counterB == 4 || counterA == 4 || counterC == 4 || counterD == 4)
				straightDraw = true;
			return straightDraw;
		}
		// check for straight on the river.  using a counter to count number of instances of cards within a straight range (five or more sequential cards)
		// and a check for pairs or more to account for duplicates
		public static boolean isStraightRiver(Card[] cards){
			boolean straight = false;
			int counterA = 1, counterB = 1, counterC = 1, wheel = 2, broadway = 2;
			sortByCardRiver(cards);
			// using a counter to count number of cards in a wheel or broadway straight range
			if ((cards[6].cardRank() == 12 && cards[0].cardRank() == 0) || (cards[6].cardRank() == 12 && cards[0].cardRank() == 8) ||
					(cards[6].cardRank() == 12 && cards[1].cardRank() == 8) || (cards[6].cardRank() == 12 && cards[2].cardRank() == 8))
			{
				for (int incrementCard = 0; incrementCard < 4; incrementCard++)
				{
					for (int i = 0; i < 6; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							wheel++;
					}
				}
				for (int incrementCard = 8; incrementCard < 12; incrementCard++)
				{
					for (int i = 0; i < 6; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							broadway++;
					}
				} 
				// readjusting counter to account for multiple cards (pairs, trips)
				if (isTripsRiver(cards) || isTripsTurn(cards) || isTripsFlop(cards))
				{
					wheel = wheel - 2;
					broadway = broadway - 2;
				}
				else if (isTwoPairRiver(cards) || isTwoPairTurn(cards) || isTwoPairFlop(cards))
				{
					wheel = wheel - 2;
					broadway  = broadway - 2;
				}
				else if (isPairRiver(cards) || isPairTurn(cards) || isPairFlop(cards))
				{
					wheel = wheel - 1;
					broadway = broadway - 1;
				}
				if (wheel >= 5 || broadway >= 5)
					straight = true;
			}
			else
			{// checking for a straight starting from first element
				for (int incrementCard = cards[0].cardRank()+1; incrementCard < cards[0].cardRank()+5; incrementCard++)
				{
					for (int i = 1; i < 7; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							counterA++;
					}
				}// checking for straight starting from second element
				for (int incrementCard = cards[1].cardRank()+1; incrementCard < cards[1].cardRank()+5; incrementCard++)
				{
					for (int i = 2; i < 7; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							counterB++;
					}
				}// checking for straight in last five cards
				for (int incrementCard = cards[2].cardRank()+1; incrementCard < cards[2].cardRank()+5; incrementCard++)
				{
					for (int i = 3; i < 7; i++)
					{
						if (cards[i].cardRank() == incrementCard)
							counterC++;
					}
				}
				// readjusting counter to account for multiple cards (pairs, trips)
				if (isTripsRiver(cards) || isTripsTurn(cards) || isTripsFlop(cards))
				{
					counterA = counterA - 2;
					counterB = counterB - 2;
					counterC = counterC - 2;
				}
				else if (isTwoPairRiver(cards) || isTwoPairTurn(cards) || isTwoPairFlop(cards))
				{
					counterA = counterA - 2;
					counterB = counterB - 2;
					counterC = counterC - 2;
				} // accounts for duplicate cards that are unrelated to our sequential cards
				else if ((cards[0].cardRank() == cards[1].cardRank()) && cards[1].cardRank()+1 != cards[2].cardRank())
				{
					counterA = counterA - 0;
					counterB = counterB - 0;
					counterC = counterC - 0;
				}
				else if ((cards[6].cardRank() == cards[5].cardRank()) && cards[5].cardRank()-1 != cards[4].cardRank())
				{
					counterA = counterA - 0;
					counterB = counterB - 0;
					counterC = counterC - 0;
				}
				else if (isPairRiver(cards) || isPairTurn(cards) || isPairRiver(cards))
				{
					counterA = counterA - 1;
					counterB = counterB - 1;
					counterC = counterC - 1;
				}
				if (counterC >= 5 || counterB >= 5 || counterA >= 5 )
					straight = true;
			}
			return straight;
		}
	// check for royal flush on flop
	public static boolean isRoyalFlushFlop(Card[] cards){
		boolean royalFlush = false;
		sortByCardFlop(cards);
		if (cards[0].suitRank() == cards[4].suitRank() && cards[0].cardRank() == 8 && cards[1].cardRank() == 9 && 
				cards[2].cardRank() == 10 && cards[3].cardRank() == 11 && cards[4].cardRank() == 12)
			royalFlush = true;
		return royalFlush;
	}
	// check for a royal flush draw
	public static boolean isRoyalFlushDrawFlop(Card[] cards){
		boolean royalDraw = false;
		sortBySuitFlop(cards);
		int counterA = 0;
		if (isRoyalFlushFlop(cards) || isStraightFlushFlop(cards) || isStraightFlop(cards) || isFlushFlop(cards))
			return royalDraw;
		if (cards[0].suitRank() == cards[3].suitRank())
		{
			for (int increment = 8; increment < 13; increment++)
			{
				for (int s = 0; s < 4; s++)
				{
					if (cards[s].cardRank() == increment)
						counterA++;
				}
			}
		}
		else if (cards[1].suitRank() == cards[4].suitRank())
		{
			for (int increment = 8; increment < 13; increment++)
			{
				for (int s = 1; s < 5; s++)
				{
					if (cards[s].cardRank() == increment)
						counterA++;
				}
			}
		}
		
		if (counterA ==4)
			royalDraw = true;
		return royalDraw;
	}
	// check for royal flush on turn
	public static boolean isRoyalFlushTurn(Card[] cards){
		boolean royalFlush = false;
		int increment = 0;
		sortBySuitTurn(cards);
		if (cards[0].suitRank() == cards[4].suitRank())
		{
			for (int royalCards = 8; royalCards < 13; royalCards++)
			{
				for (int i = 0; i < 5; i++)
				{
					if (cards[i].cardRank() == royalCards)
						increment++;
				}
			}
			if (increment == 5)
				royalFlush = true;
		}
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			for (int royalCards = 8; royalCards < 13; royalCards++)
			{
				for (int i = 1; i < 6; i++)
				{
					if (cards[i].cardRank() == royalCards)
						increment++;
				}
			}
			if (increment == 5)
				royalFlush = true;
		}
		return royalFlush;
	}
	public static boolean isRoyalFlushDrawTurn(Card[] cards){
		boolean royalDraw = false;
		sortBySuitFlop(cards);
		int counterA = 0;
		if (isRoyalFlushTurn(cards) || isStraightFlushTurn(cards) || isStraightTurn(cards) || isFlushTurn(cards))
			return royalDraw;
		if (cards[0].suitRank() == cards[3].suitRank())
		{
			for (int increment = 8; increment < 13; increment++)
			{
				for (int s = 0; s < 4; s++)
				{
					if (cards[s].cardRank() == increment)
						counterA++;
				}
			}
		}
		else if (cards[1].suitRank() == cards[4].suitRank())
		{
			for (int increment = 8; increment < 13; increment++)
			{
				for (int s = 1; s < 5; s++)
				{
					if (cards[s].cardRank() == increment)
						counterA++;
				}
			}
		}
		else if (cards[2].suitRank() == cards[5].suitRank())
		{
			for (int increment = 8; increment < 13; increment++)
			{
				for (int s = 2; s < 6; s++)
				{
					if (cards[s].cardRank() == increment)
						counterA++;
				}
			}
		}
		
		if (counterA ==4)
			royalDraw = true;
		return royalDraw;
	}
	// check for royal flush on river
	public static boolean isRoyalFlushRiver(Card[] cards){
		boolean royalFlush = false;
		int increment = 0;
		sortBySuitRiver(cards);
		if (cards[0].suitRank() == cards[4].suitRank())
		{
			for (int royalCards = 8; royalCards < 13; royalCards++)
			{
				for (int i = 0; i < 5; i++)
				{
					if (cards[i].cardRank() == royalCards)
						increment++;
				}
			}
			if (increment == 5)
				royalFlush = true;
		}
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			for (int royalCards = 8; royalCards < 13; royalCards++)
			{
				for (int i = 1; i < 6; i++)
				{
					if (cards[i].cardRank() == royalCards)
						increment++;
				}
			}
			if (increment == 5)
				royalFlush = true;
		}
		else if (cards[2].suitRank() == cards[6].suitRank())
		{
			for (int royalCards = 8; royalCards < 13; royalCards++)
			{
				for (int i = 2; i < 7; i++)
				{
					if (cards[i].cardRank() == royalCards)
						increment++;
				}
			}
			if (increment == 5)
				royalFlush = true;
		}
			
		return royalFlush;
	}
	// check for straight flush on flop
	public static boolean isStraightFlushFlop(Card[] cards){
		boolean straightFlush = false;
		sortByCardFlop(cards);
		if (isRoyalFlushFlop(cards))
			return straightFlush;
		else if (isStraightFlop(cards) && isFlushFlop(cards))
		{
			return straightFlush;
		}
		return straightFlush;
	}
	// check for straight flush on turn
	public static boolean isStraightFlushTurn(Card[] cards){
		boolean straightFlush = false;
		sortBySuitAndCardTurn(cards);
		if (isRoyalFlushTurn(cards))
			return straightFlush;
		else if (cards[0].suitRank() == cards[4].suitRank())
		{
			if (cards[4].card.equalsIgnoreCase("Ace"))
			{
				if (cards[0].card.equals("Two") && cards[1].card.equals("Three") && cards[2].card.equals("Four") && cards[3].card.equals("Five"))
					straightFlush = true;
			}
			else // check for any other straights (five sequential cards)
			{
				int incrementCard = cards[0].cardRank()+1;
				for (int i = 1; i < 5; i++)
				{
					if (cards[i].cardRank() != incrementCard)
						return straightFlush;
					incrementCard++;
				}
				straightFlush = true;
			}
		}
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			if (cards[5].card.equalsIgnoreCase("Ace"))
			{
				if (cards[1].card.equals("Two") && cards[2].card.equals("Three") && cards[3].card.equals("Four") && cards[4].card.equals("Five"))
					straightFlush = true;
			}
			else // check for any other straights (five sequential cards)
			{
				int incrementCard = cards[1].cardRank()+1;
				for (int i = 2; i < 5; i++)
				{
					if (cards[i].cardRank() != incrementCard)
						return straightFlush;
					incrementCard++;
				}
				straightFlush = true;
			}
		}
		
		return straightFlush;
	}
	public static boolean isStraightFlushRiver(Card[] cards){
		boolean straightFlush = false;
		sortBySuitAndCardRiver(cards);
		for (Card s : cards)
			System.out.println(s);
		if (isRoyalFlushRiver(cards))
			return straightFlush;
		else if (cards[0].suitRank() == cards[4].suitRank())
		{
			if (cards[4].card.equalsIgnoreCase("Ace"))
			{
				if (cards[0].card.equals("Two") && cards[1].card.equals("Three") && cards[2].card.equals("Four") && cards[3].card.equals("Five"))
					straightFlush = true;
			}
			else // check for any other straights (five sequential cards)
			{
				int incrementCard = cards[0].cardRank()+1;
				for (int i = 1; i < 5; i++)
				{
					if (cards[i].cardRank() != incrementCard)
						return straightFlush;
					incrementCard++;
				}
				straightFlush = true;
			}
		}
		else if (cards[1].suitRank() == cards[5].suitRank())
		{
			if (cards[5].card.equalsIgnoreCase("Ace"))
			{
				if (cards[1].card.equals("Two") && cards[2].card.equals("Three") && cards[3].card.equals("Four") && cards[4].card.equals("Five"))
					straightFlush = true;
			}
			else // check for any other straights (five sequential cards)
			{
				int incrementCard = cards[1].cardRank()+1;
				for (int i = 2; i < 6; i++)
				{
					if (cards[i].cardRank() != incrementCard)
						return straightFlush;
					incrementCard++;
				}
				straightFlush = true;
			}
		}
		else if (cards[2].suitRank() == cards[6].suitRank())
		{
			if (cards[6].card.equalsIgnoreCase("Ace"))
			{
				if (cards[2].card.equals("Two") && cards[3].card.equals("Three") && cards[4].card.equals("Four") && cards[5].card.equals("Five"))
					straightFlush = true;
			}
			else // check for any other straights (five sequential cards)
			{
				int incrementCard = cards[2].cardRank()+1;
				for (int i = 3; i < 7; i++)
				{
					if (cards[i].cardRank() != incrementCard)
						return straightFlush;
					incrementCard++;
				}
				straightFlush = true;
			}
		}
		return straightFlush;
	}
	
	// check for four of a kind on the flop
	public static boolean isQuadsFlop(Card[] cards){
		boolean quads = false;
		sortByCardFlop(cards);
		if (cards[0].cardRank() == cards[1].cardRank() &&  cards[1].cardRank() == cards[2].cardRank() && cards[2].cardRank() == cards[3].cardRank())
			quads = true;
		else if (cards[1].cardRank() == cards[2].cardRank() &&  cards[2].cardRank() == cards[3].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			quads = true;
		return quads;
	}
	// check for four of a kind on the turn
	public static boolean isQuadsTurn(Card[] cards){
		boolean quads = false;
		sortByCardTurn(cards);
		if (cards[2].cardRank() == cards[3].cardRank() &&  cards[3].cardRank() == cards[4].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			quads = true;
		return quads;
	}
	// check for four of a king on the river
	public static boolean isQuadsRiver(Card[] cards){
		boolean quads = false;
		sortByCardRiver(cards);
		if (cards[3].cardRank() == cards[4].cardRank() &&  cards[4].cardRank() == cards[5].cardRank() && cards[5].cardRank() == cards[6].cardRank())
			quads = true;
		return quads;
	}
	// check for a full house on the flop
	public static boolean isFullHouseFlop(Card[] cards){
		boolean fullHouse = false;
		sortByCardFlop(cards);
		if (cards[0].cardRank() == cards[1].cardRank() && cards[1].cardRank() == cards[2].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			fullHouse = true;
		else if (cards[0].cardRank() == cards[1].cardRank() && cards[2].cardRank() == cards[3].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			fullHouse = true;
		return fullHouse;
	}
	// check for a full house on the turn
	public static boolean isFullHouseTurn(Card[] cards){
		boolean fullHouse = false;
		sortByCardTurn(cards);
		if (cards[1].cardRank() == cards[2].cardRank() && cards[2].cardRank() == cards[3].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			fullHouse = true;
		else if (cards[1].cardRank() == cards[2].cardRank() && cards[3].cardRank() == cards[4].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			fullHouse = true;
		return fullHouse;
	}
	// check for a full house on the river
	public static boolean isFullHouseRiver(Card[] cards){
		boolean fullHouse = false;
		sortByCardRiver(cards);
		if (cards[2].cardRank() == cards[3].cardRank() && cards[3].cardRank() == cards[4].cardRank() && cards[5].cardRank() == cards[6].cardRank())
			fullHouse = true;
		else if (cards[2].cardRank() == cards[3].cardRank() && cards[4].cardRank() == cards[5].cardRank() && cards[5].cardRank() == cards[6].cardRank())
			fullHouse = true;
		return fullHouse;
	}
	
	// check for three of a kind on the flop
	public static boolean isTripsFlop(Card[] cards){
		boolean trips = false;
		sortByCardFlop(cards);
		if (isFullHouseFlop(cards) || isQuadsFlop(cards))
			return trips;
		else if (cards[0].cardRank() == cards[1].cardRank() && cards[1].cardRank() == cards[2].cardRank())
			trips = true;
		else if (cards[1].cardRank() == cards[2].cardRank() && cards[2].cardRank() == cards[3].cardRank())
			trips = true;
		else if (cards[2].cardRank() == cards[3].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			trips = true;
		return trips;
	}
	// check for three of a kind on the turn
	public static boolean isTripsTurn(Card[] cards){
		boolean trips = false;
		sortByCardTurn(cards);
		if (isFullHouseTurn(cards) || isQuadsTurn(cards) || isFullHouseFlop(cards) || isQuadsFlop(cards))
			return trips;
		else if (cards[3].cardRank() == cards[4].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			trips = true;
		return trips;
	}
	// check for three of a kind on the river
	public static boolean isTripsRiver(Card[] cards){
		boolean trips = false;
		sortByCardRiver(cards);
		if (isFullHouseRiver(cards) || isQuadsRiver(cards) || isFullHouseTurn(cards) || isQuadsTurn(cards) || isFullHouseFlop(cards) || isQuadsFlop(cards))
			return trips;
		else if (cards[0].cardRank() == cards[1].cardRank() && cards[1].cardRank() == cards[2].cardRank())
			trips = true;
		return trips;
	}
	// check for two pairs on the flop
	public static boolean isTwoPairFlop(Card[] cards){
		boolean twoPair = false;
		sortByCardFlop(cards);
		if (isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards))
			return twoPair;
		else if (cards[0].cardRank() == cards[1].cardRank() && cards[2].cardRank() == cards[3].cardRank())
			twoPair = true;
		else if (cards[0].cardRank() == cards[1].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			twoPair = true;
		else if (cards[1].cardRank() == cards[2].cardRank() && cards[3].cardRank() == cards[4].cardRank())
			twoPair = true;
		return twoPair;
	}
	// check for two pairs on the turn
	public static boolean isTwoPairTurn(Card[] cards){
		boolean twoPair = false;
		sortByCardTurn(cards);
		if (isFullHouseTurn(cards) || isQuadsTurn(cards) || isTripsTurn(cards) || isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards))
			return twoPair;
		else if (isTwoPairFlop(cards))
			twoPair = true;
		else if (cards[2].cardRank() == cards[3].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			twoPair = true;
		else if (cards[1].cardRank() == cards[2].cardRank() && cards[4].cardRank() == cards[5].cardRank())
			twoPair = true;
		return twoPair;
	}
	// check for two pairs on the river
	public static boolean isTwoPairRiver(Card[] cards){
		boolean twoPair = false;
		sortByCardRiver(cards);
		if (isFullHouseRiver(cards) || isQuadsRiver(cards) || isTripsRiver(cards) ||isFullHouseTurn(cards) 
				|| isQuadsTurn(cards) || isTripsTurn(cards) ||isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards))
			return twoPair;
		else if (isTwoPairTurn(cards))
			twoPair = true;
		else if (cards[3].cardRank() == cards[4].cardRank() && cards[5].cardRank() == cards[6].cardRank())
			twoPair = true;
		else if (cards[2].cardRank() == cards[3].cardRank() && cards[5].cardRank() == cards[6].cardRank())
			twoPair = true;
		return twoPair;
	}
	
	// check for one pair on the flop
	public static boolean isPairFlop(Card[] cards){
		boolean pair = false;
		if (isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards) || isTwoPairFlop(cards))
			return pair;
		else if (cards[0].cardRank() == cards[1].cardRank())
			pair = true;
		else if (cards[1].cardRank() == cards[2].cardRank())
			pair = true;
		else if (cards[2].cardRank() == cards[3].cardRank())
			pair = true;
		else if (cards[3].cardRank() == cards[4].cardRank())
			pair = true;
		return pair;
	}
	// check for one pair on the turn
	public static boolean isPairTurn(Card[] cards){
		boolean pair = false;
		if (isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards) || isTwoPairFlop(cards) ||
				isFullHouseTurn(cards) || isQuadsTurn(cards) || isTripsTurn(cards) || isTwoPairTurn(cards))
			return pair;
		else if (cards[4].cardRank() == cards[5].cardRank())
			pair = true;
		return pair;
	}
	// check for one pair on the river
	public static boolean isPairRiver(Card[] cards){
		boolean pair = false;
		if (isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards) || isTwoPairFlop(cards) ||
				isFullHouseTurn(cards) || isQuadsTurn(cards) || isTripsTurn(cards) || isTwoPairTurn(cards) ||
				isFullHouseRiver(cards) || isQuadsRiver(cards) || isTripsRiver(cards) || isTwoPairRiver(cards))
			return pair;
		else if (cards[5].cardRank() == cards[6].cardRank())
			pair = true;
		return pair;
	}
	// return high card if no hand is made flop
	public static String isHighCardFlop(Card[] cards){
		sortByCardFlop(cards);
		return cards[4].card;
	}
	// return high card if no hand is made on turn
	public static String isHighCardTurn(Card[] cards){
		sortByCardFlop(cards);
		return cards[5].card;
	}
	// return high card if no hand is made on river
	public static String isHighCardRiver(Card[] cards){
		sortByCardFlop(cards);
		return cards[6].card;
	}
	// show what card this instance holds
	public String toString(){
		return (card + " of "+suit);
	}
}
