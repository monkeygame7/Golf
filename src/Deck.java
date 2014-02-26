import java.util.Random;
import java.util.ArrayList;


public class Deck
{
    private ArrayList<Card> cards;
    private Random rand;

    public Deck()
    {
        cards = new ArrayList<Card>( 52 );

        rand = new Random();

        for ( int suit = 0; suit < 4; suit++ )
        {
            for ( int value = 1; value <= 13; value++ )
            {
                cards.add( new Card( suit, value ) );
            }
        }
    }

    public Card draw()
    {
        return cards.remove( rand.nextInt( cards.size() ) );
    }

    public int numCardsLeft()
    {
        return cards.size();
    }
}
