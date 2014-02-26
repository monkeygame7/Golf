import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class CardPanel extends JPanel
{
    private JLabel image;
    private Card card;
    private CardImages images;
    private boolean isSelected;
    private Border unselectedBorder;
    private Border selectedBorder;

    public CardPanel( Card c, CardImages i )
    {
        this( c, Color.green, i );
    }

    public CardPanel( Card c, Color bg, CardImages i )
    {
        unselectedBorder = BorderFactory.createLineBorder(  bg, 3 );
        selectedBorder = BorderFactory.createLineBorder( Color.red, 3 );

        setBorder( unselectedBorder );
        setBackground( bg );

        card = c;

        isSelected = false;

        images = i;

        image = new JLabel();
        image.setBorder( unselectedBorder );
        image.setIcon( images.faceDown() );


        add( image );
    }

    public void updateImage()
    {
        if ( card != null && !( card.isFaceUp() ) )
        {
            image.setIcon( images.faceDown() );
        }
        else
        {
            image.setIcon( images.get( card ) );
        }
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard( Card c )
    {
        card = c;
        updateImage();
    }

    public void flip()
    {
        if( card != null )
        {
            card.flip();
        }
        updateImage();
    }

    public boolean faceUp()
    {
        return card.isFaceUp();
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void select( boolean s )
    {
        if ( card != null && card.isFaceUp() )
        {
                image.setBorder( s? selectedBorder:unselectedBorder );
                isSelected = s;
        }
    }
}
