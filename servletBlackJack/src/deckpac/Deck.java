package deckpac;

import java.util.*;

/**
 * This class implements filling the deck with the cards and provides methods to
 * operate with cards within the deck
 * 
 * @author max
 * 
 */
public class Deck {
	List<Card> deck = getCardList();

	private ArrayList<Card> getCardList() {
		ArrayList<Card> deck = new ArrayList<>();
		for (int k = 0; k < 2; k++)
			// Filling the deck with 104 cards
			for (int i = 0; i < CardValue.values().length; i++)
				for (int j = 0; j < Suites.values().length; j++)
					deck.add(new Card(CardValue.values()[i], Suites.values()[j]));
		return deck;
	}

	public Deck() {
		/*
		 * for (int k = 0; k < 2; k++) // Filling the deck with 104 cards for
		 * (int i = 0; i < CardValue.values().length; i++) for (int j = 0; j <
		 * Suites.values().length; j++) deck.add(new Card(CardValue.values()[i],
		 * Suites.values()[j]));
		 */
	}

	/**
	 * This method allows to take the card from the deck
	 */
	public Card takeCard() {
		Random rnd = new Random();
		if (deck.size() < 1) {
			deck = getCardList();
		}
		int number = rnd.nextInt(deck.size());
		Card card = deck.get(number);
		deck.remove(number);
		return card;
	}
}
