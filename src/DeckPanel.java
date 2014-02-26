import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DeckPanel extends JPanel
{
    private Deck deck;
    private JLabel deckLabel;
    private CardImages images;

    public DeckPanel( Deck d, CardImages i )
    {
        deck = d;
        deckLabel = new JLabel();
        images = i;
        deckLabel.setIcon( images.faceDown() );

        setBackground( new Color( 51, 145, 58 ) );

        add( deckLabel );
    }

    public void updateImage()
    {
        deckLabel.setIcon( deck.numCardsLeft() == 0? images.get( null ):images.faceDown() );
    }

    public void checkIfEmpty()
    {
        if ( deck.numCardsLeft() == 0 )
        {
            JOptionPane.showMessageDialog( this.getParent(), "There are no cards left" );
        }
        updateImage();
    }

    public Deck getDeck()
    {
        return deck;
    }
}
