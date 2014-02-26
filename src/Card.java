
public class Card
{
    private int suit;
    private int value;
    private boolean faceUp;

    public Card( int s, int v )
    {
        this( s, v, false );
    }

    public Card( int s, int v, boolean f )
    {
        suit = s;
        value = v;
        faceUp = f;
    }

    public int getSuit()
    {
        return suit;
    }

    public int getValue()
    {
        return value;
    }

    public boolean isFaceUp()
    {
        return faceUp;
    }

    public void flip()
    {
        faceUp = !faceUp;
    }
}
