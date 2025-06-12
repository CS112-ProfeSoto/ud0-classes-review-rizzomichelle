/**
 * Represents one playing card from a standard 52-card deck
 * (https://en.wikipedia.org/wiki/Playing_card)
 *
 * Class Invariant:
 * - Card value represents the number/letter printed on the card,
 * usually in the corners (A, 2, 3, ..., 9, 10, J, Q, K)
 * - Card value is stored as an integer to make error checking/validation easier,
 * but must be outputted appropriately (1 is A, 11 is J, 12 is Q, 13 is K) for user
 * - Card suit represents one of 4 suits (heart, diamond, club, spade)
 * - Card suit is stored as the unicode char representing the suit,
 * constant variables will be used throughout code for consistency
 * - Whenever value/suit is changed, it must be within the valid values
 *
 * @author ???
 * @version ???
 */

/*
 * UML CLASS DIAGRAM:
 * -------------------------------------------------------
 *   Card
 * -------------------------------------------------------
 * - value : int
 * - suit : char
 * + HEART : char			//static constant with value ♥
 * + DIAMOND : char			//static constant with value ♦
 * + CLUB : char			//static constant with value ♣
 * + SPADE : char			//static constant with value ♠
 * + DEFAULT_VALUE : int	//static constant with value 1
 * + DEFAULT_SUIT : char	//static constant with value ♥
 * -------------------------------------------------------
 * + Card()
 * + Card(value : int, suit : char)
 * + Card(original : Card)
 * + setValue(value : int) : boolean
 * + setSuit(suit : char) : boolean
 * + setAll(value : int, suit : char) : boolean
 * + getSuit() : char
 * + getValue() : int
 * + getPrintValue() : String
 * + getPrintCard() : String
 * + toString() : String
 * + equals(otherCard : Card) : boolean
 * + printCard() : void
 * -------------------------------------------------------
 */

public class Card {

    /*** CONSTANT VARIABLES ***/
    public static final char HEART = '♥';
    public static final char DIAMOND = '♦';
    public static final char CLUB = '♣';
    public static final char SPADE = '♠';

    public static final int DEFAULT_VALUE = 1;
    public static final char DEFAULT_SUIT = HEART;

    /*** INSTANCE VARIABLES ***/
    private int value;
    private char suit;



    /*** CONSTRUCTOR METHODS ***/
    /**
     * Default constructor, builds default card object as: A ♥
     */
    public Card() {
        this.value = 1;
        this.suit = HEART;
    }

    /**
     * Full constructor builds object with all data for instance variables provided.
     * If arguments are not valid, program shuts down with error message
     *
     * @param value numerical value of card (1-13), not what shows on card (A, 2-10,
     *              J, Q, K)
     * @param suit  one of four suit values (unicode value for heart, diamond,
     *              spade, or club)
     */
    public Card(int value, char suit) {
        if (!setAll(value, suit)) {
            System.out.println("ERROR: Invalid value or suit in full constructor");
            System.exit(1);
        }
    }

    /**
     * Copy constructor builds object with all data from Card object provided. No
     * changes made to original object, no shallow copying
     *
     * @param original Card object to be copied
     */
    public Card(Card other) {
        if (other == null) {
            System.out.println("ERROR: Null Card passed to copy constructor");
            System.exit(1);
        }
        this.value = other.value;
        this.suit = other.suit;
    }

    /*** MUTATOR METHODS (SETTERS) ***/
    /**
     * Sets value for card only if valid, otherwise will not change instance
     * variable. Returns boolean representing whether error occured (false) or
     * operation completed successfully (true)
     *
     * @param value numerical value of card (1-13), not what shows on card (A, 2-10,
     *              J, Q, K)
     *
     * @return true if card value is between 1 and 13 (inclusive), false otherwise
     */
    public boolean setValue(int value) {
        if (value >= 1 && value <= 13) {
            this.value = value;
            return true;
        }
        return false;
    }

    /**
     * Sets suit for card only if valid, otherwise will not change instance
     * variable. Returns boolean representing whether error occured (false) or
     * operation completed successfully (true)
     *
     * @param suit one of four suit values (unicode value for heart, diamond, spade,
     *             or club)
     *
     * @return true if card suit is unicode value for heart, diamond, club or spade.
     *         false otherwise
     */
    public boolean setSuit(char suit) {
        if (suit == HEART || suit == DIAMOND || suit == CLUB || suit == SPADE) {
            this.suit = suit;
            return true;
        }
        return false;
    }

    /**
     * Sets suit and value for card only if valid, returns boolean representing
     * whether error occured (false) or operation completed successfully (true)
     *
     * @param suit  one of four suit values (unicode value for heart, diamond,
     *              spade, or club)
     * @param value numerical value of card (1-13), not what shows on card (A, 2-10,
     *              J, Q, K)
     *
     * @return true if card suit AND value are valid, false otherwise
     */
    public boolean setAll(int value, char suit) {
        if (value >= 1 && value <= 13 &&
                (suit == HEART || suit == DIAMOND || suit == CLUB || suit == SPADE)) {
            this.value = value;
            this.suit = suit;
            return true;
        }
        return false;
    }

    /*** ACCESSOR METHODS (GETTERS) ***/
    /**
     * Access unicode character representing suit of card
     *
     * @return suit as unicode character for heart, spade, diamond, or club
     */
    public int getValue() {
        return this.value;
    }

    public char getSuit() {
        return this.suit;
    }


    /**
     * Access numerical value of card (1-13)
     *
     * @return value as raw integer 1-13 (not what player sees as A, 2-10, J, Q, K;
     *         see {@link #getPrintValue()})
     */
    public String getPrintValue() {
        switch (this.value) {
            case 1:  return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(this.value);
        }
    }

    /**
     * Access value of card as seen by user (A, 2-10, J, Q, K) that would be printed
     * on card
     *
     * @return value as String user sees on card (A, 2-10, J, Q, K), not numerical
     *         value 1-13 (see {@link #getValue()})
     */
    public boolean equals(Card other) {
        return this.value == other.value && this.suit == other.suit;
    }

    /**
     * Access ASCII art version of card data, each line separated by newline
     * character, no newline character at end of String
     *
     * @return String containing ASCII art with card suit and card print value
     */


    /*** OTHER REQUIRED METHODS ***/
    /**
     * String of all instance variables, no newline character at end of String.
     * Using print value to use as "condensed" version of printed card (ex: A ♥)
     *
     * @return String containing (print) value and suit, separated by a space
     */
    public String toString() {
        return "[" + getPrintValue() + " " + suit + "]";
    }

    /**
     * Checking for equality of Card objects, all instance variables exactly equal
     * to each other (case-sensitive). Argument object not changed
     *
     * @param other Card object to compare for equality
     *
     * @return boolean representing equality between both objects, all data is
     *         exactly equal to each other
     */


    /*** EXTRA METHODS ***/
    /**
     * Prints card ASCII art to console (see {@link #getPrintCard()})
     */
    public String[] getPrintCard() {
        String symbol = Character.toString(suit);
        String valueStr = getPrintValue();
        if (valueStr.length() == 1) valueStr = " " + valueStr + " ";
        else valueStr = " " + valueStr;
        return new String[]{
                "-------",
                "|" + symbol + "   " + symbol + "|",
                "|" + valueStr + " |",
                "|" + symbol + "   " + symbol + "|",
                "-------"
        };
    }
    public void printCard() {
        for (String line : getPrintCard()) {
            System.out.println(line);
        }
    }
}