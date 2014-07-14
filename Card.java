public class Card {
	private String card;
	private String suit;
	private int cardRank, suitRank;
	private final String[] cardRanks = {"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
	private final String[] suitRanks = {"Spades","Diamonds","Clubs","Hearts"};
	
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
		int min_value = 0;
		for (int h = 0; h < 6; h++)
		{
			min_value = h;
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
	}
	// check to see if we have a flush on the flop
	public static boolean isFlushFlop(Card[] cards){
		boolean flush = false;
		sortBySuitFlop(cards);
		if (cards[0].suitRank() == cards[4].suitRank())
			flush = true;
		return flush;
	}
	// check to see if we have a flush on the turn
	public static boolean isFlushTurn(Card[] cards){
		boolean flush = false;
		sortBySuitTurn(cards);
		if (cards[0].suitRank() == cards[4].suitRank() || cards[1].suitRank() == cards[5].suitRank())
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
		if (cards[4].card.equalsIgnoreCase("Ace"))
		{
			if (cards[0].card.equals("Two") && cards[1].card.equals("Three") && cards[2].card.equals("Four") && cards[3].card.equals("Five"))
				straight = true;
			else if (cards[0].card.equals("Ten") && cards[1].card.equals("Jack") && cards[2].card.equals("Queen") && cards[3].card.equals("King"))
				straight = true;
		}
		else
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
	// check for straight on the turn
	public static boolean isStraightTurn(Card[] cards){
		boolean straight = false;
		int counter = 1, wheel = 1, broadway = 1;
		sortByCardTurn(cards);
		if ((cards[5].cardRank() == 12))
		{
			for (int incrementCard = 0; incrementCard < 4; incrementCard++)
			{
				for (int i = 0; i < 5; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						wheel++;
				}
			}
			for (int incrementCard = 8; incrementCard < 12; incrementCard++)
			{
				for (int i = 0; i < 5; i++)
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
			else if (isPairTurn(cards) || isPairFlop(cards))
			{
				wheel = wheel - 1;
				broadway = broadway - 1;
			}
			if (wheel >= 5 || broadway >= 5)
				straight = true;
		}
		else
		{
			for (int incrementCard = cards[0].cardRank()+1; incrementCard < 6; incrementCard++)
			{
				for (int i = 1; i < 6; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counter++;
				}
			}
			if (isTripsTurn(cards) || isTripsFlop(cards))
			{
				counter = counter - 2;
			}
			else if (isPairTurn(cards) || isPairFlop(cards))
			{
				counter = counter - 1;
			}
			if (counter >= 5)
				straight = true;
		}
		return straight;
	}
	// check for straight on the river
	public static boolean isStraightRiver(Card[] cards){
		boolean straight = false;
		int counter = 1, wheel = 1, broadway = 1;
		sortByCardRiver(cards);
		// using a counter to count number of cards in a wheel or broadway straight range
		if (cards[6].cardRank() == 12)
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
			else if (isPairRiver(cards) || isPairTurn(cards) || isPairFlop(cards))
			{
				wheel = wheel - 1;
				broadway = broadway - 1;
			}
			if (wheel >= 5 || broadway >= 5)
				straight = true;
		}
		else
		{
			for (int incrementCard = cards[0].cardRank()+1; incrementCard < 6; incrementCard++)
			{
				for (int i = 1; i < 7; i++)
				{
					if (cards[i].cardRank() == incrementCard)
						counter++;
				}
			}
			// readjusting counter to account for multiple cards (pairs, trips)
			if (isTripsRiver(cards) || isTripsTurn(cards) || isTripsFlop(cards))
			{
				counter = counter - 2;
			}
			else if (isPairRiver(cards) || isPairTurn(cards) || isPairRiver(cards))
			{
				counter = counter - 2;
			}
			if (counter >= 5)
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
	// check for royal flush on turn
	public static boolean isRoyalFlushTurn(Card[] cards){
		boolean royalFlush = false;
		sortByCardTurn(cards);
		if (cards[1].suitRank() == cards[5].suitRank() && cards[1].cardRank() == 8 && 
				cards[2].cardRank() == 9 && cards[3].cardRank() == 10 && cards[4].cardRank() == 11 && cards[5].cardRank() == 12)
			royalFlush = true;
		return royalFlush;
	}
	// check for royal flush on river
	public static boolean isRoyalFlushRiver(Card[] cards){
		boolean royalFlush = false;
		sortByCardRiver(cards);
		if (cards[2].suitRank() == cards[6].suitRank() &&  cards[2].cardRank() == 8 && 
				cards[3].cardRank() == 9 && cards[4].cardRank() == 10 && cards[5].cardRank() == 11 && cards[6].cardRank() == 12)
			royalFlush = true;
		return royalFlush;
	}
	// check for straight flush on flop
	public static boolean isStraightFlushFlop(Card[] cards){
		boolean straightFlush = false;
		sortByCardFlop(cards);
		if (isRoyalFlushFlop(cards))
			return straightFlush;
		else if (cards[4].card.equalsIgnoreCase("Ace"))
		{
			if (cards[0].suitRank() == cards[4].suitRank() && cards[0].card.equals("Two") && 
					cards[1].card.equals("Three") && cards[2].card.equals("Four") && cards[3].card.equals("Five"))
				straightFlush = true;
		}
		else
		{
			int incrementCard = cards[0].cardRank()+1;
			for (int i = 1; i < 5; i++)
			{
				if (cards[i].cardRank() != incrementCard)
					return straightFlush;
				incrementCard++;
			}
			if (cards[0].suitRank() == cards[4].suitRank())
				straightFlush = true;
			else
				return straightFlush;
		}
		return straightFlush;
	}
	// check for straight flush on turn
	public static boolean isStraightFlushTurn(Card[] cards){
		boolean straightFlush = false;
		sortByCardFlop(cards);
		if (isRoyalFlushTurn(cards))
			return straightFlush;
		else if (cards[5].card.equalsIgnoreCase("Ace"))
		{
			if (cards[1].suitRank() == cards[5].suitRank() && cards[1].card.equals("Two") && 
					cards[2].card.equals("Three") && cards[3].card.equals("Four") && cards[4].card.equals("Five"))
				straightFlush = true;
		}
		else
		{
			int incrementCard = cards[1].cardRank()+1;
			for (int i = 2; i < 6; i++)
			{
				if (cards[i].cardRank() != incrementCard)
					return straightFlush;
				incrementCard++;
			}
			if (cards[1].suitRank() == cards[5].suitRank())
				straightFlush = true;
			else
				return straightFlush;
		}
		return straightFlush;
	}public static boolean isStraightFlushRiver(Card[] cards){
		boolean straightFlush = false;
		sortByCardFlop(cards);
		if (isRoyalFlushRiver(cards))
			return straightFlush;
		else if (cards[6].card.equalsIgnoreCase("Ace"))
		{
			if (cards[2].suitRank() == cards[6].suitRank() && cards[2].card.equals("Two") && 
					cards[3].card.equals("Three") && cards[4].card.equals("Four") && cards[5].card.equals("Five"))
				straightFlush = true;
		}
		else
		{
			int incrementCard = cards[2].cardRank()+1;
			for (int i = 3; i < 7; i++)
			{
				if (cards[i].cardRank() != incrementCard)
					return straightFlush;
				incrementCard++;
			}
			if (cards[2].suitRank() == cards[6].suitRank())
				straightFlush = true;
			else
				return straightFlush;
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
		if (isFullHouseRiver(cards) || isQuadsRiver(cards) || isTripsRiver(cards) ||isFullHouseTurn(cards) || isQuadsTurn(cards) || isTripsTurn(cards) ||isFullHouseFlop(cards) || isQuadsFlop(cards) || isTripsFlop(cards))
			return twoPair;
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
		String highCard = cards[4].card;
		return highCard;
	}
	// return high card if no hand is made on turn
	public static String isHighCardTurn(Card[] cards){
		String highCard = cards[5].card;
		return highCard;
	}
	// return high card if no hand is made on river
	public static String isHighCardRiver(Card[] cards){
		String highCard = cards[6].card;
		return highCard;
	}
	// show what card this instance holds
	public String toString(){
		return (card + " of "+suit);
	}
}
