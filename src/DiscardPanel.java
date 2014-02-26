import java.awt.Color;
import javax.swing.JPanel;


public class DiscardPanel extends JPanel
{
    private CardPanel topCard;

    public DiscardPanel( CardImages i )
    {
        topCard = new CardPanel( null, new Color( 51, 145, 58 ), i );
        topCard.flip();
        setBackground( new Color( 51, 145, 58 ) );

        add( topCard );
    }

    public void updateImage()
    {
        topCard.setCard( top() );
    }

    public Card top()
    {
        return topCard.getCard();
    }

    public void discard( Card c )
    {
        topCard.setCard( c );
        updateImage();
    }

    public void ensureFaceUp()
    {
        if ( top() != null && !top().isFaceUp() )
        {
            topCard.flip();
        }
    }

    public boolean isEmpty()
    {
        return top() == null;
    }

    public boolean isSelected()
    {
        return topCard.isSelected();
    }

    public CardPanel getPanel()
    {
        return topCard;
    }
}
