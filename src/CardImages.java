import javax.swing.ImageIcon;


public class CardImages
{
    private ImageIcon spades[];
    private ImageIcon clubs[];
    private ImageIcon hearts[];
    private ImageIcon diamonds[];
    private ImageIcon blueFaceDown;
    private ImageIcon redFaceDown;
    private ImageIcon blankCard;
    private boolean blue; //if the color of the cards is blue (red if false)

    public CardImages()
    {
        blue = true;
        spades = new ImageIcon[ 13 ];

        for ( int k = 0; k < 13; k++ )
        {
            spades[ k ] = new ImageIcon( getClass().getResource( "images/spades_" + ( k + 1 ) + ".png" ) );
        }

        clubs = new ImageIcon[ 13 ];
        for ( int k = 0; k < 13; k++ )
        {
            clubs[ k ] = new ImageIcon( getClass().getResource( "images/clubs_" + ( k + 1 ) + ".png" ) );
        }

        hearts = new ImageIcon[ 13 ];
        for ( int k = 0; k < 13; k++ )
        {
            hearts[ k ] = new ImageIcon( getClass().getResource( "images/hearts_" + ( k + 1 ) + ".png" ) );
        }

        diamonds = new ImageIcon[ 13 ];
        for ( int k = 0; k < 13; k++ )
        {
            diamonds[ k ] = new ImageIcon( getClass().getResource( "images/diamonds_" + ( k + 1 ) + ".png" ) );
        }

        blueFaceDown = new ImageIcon( getClass().getResource( "images/blue_facedown_card.png" ) );
        redFaceDown = new ImageIcon( getClass().getResource( "images/red_facedown_card.png" ) );
        blankCard = new ImageIcon( getClass().getResource( "images/blank.png" ) );
    }

    public ImageIcon faceDown()
    {
        return blue? blueFaceDown:redFaceDown;
    }

    public ImageIcon get( Card card )
    {
        if ( card == null )
        {
            return blankCard;
        }

        if ( !card.isFaceUp() )
        {
            return faceDown();
        }

        if ( card.getSuit() == Suits.SPADES )
        {
            return spades[ card.getValue() - 1 ];
        }
        else if ( card.getSuit() == Suits.CLUBS )
        {
            return clubs[ card.getValue() - 1 ];
        }
        else if ( card.getSuit() == Suits.HEARTS )
        {
            return hearts[ card.getValue() - 1 ];
        }
        else
        {
            return diamonds[ card.getValue() - 1 ];
        }
    }
    public void setBlue( boolean b )
    {
        blue = b;
    }
}
