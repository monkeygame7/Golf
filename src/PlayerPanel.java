import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayerPanel extends JPanel
{
    private CardPanel[][] cards;
    private String name;
    private JPanel myCards; //holds and displays the player's cards
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private CardPanel selectedPanel;
    private DeckPanel deckPanel;
    private Deck deck;
    private DiscardPanel discard;
    private boolean myTurn;
    private TurnController turnController;
    private Border selectedBorder;
    private Border unselectedBorder;

    public PlayerPanel( String n, DeckPanel d, DiscardPanel p, TurnController t, CardImages i )
    {
        selectedPanel = null;

        turnController = t;

        deckPanel = d;
        deckPanel.addMouseListener( new DeckListener() );

        discard = p;
        discard.addMouseListener( new DiscardListener() );

        deck = deckPanel.getDeck();

        selectedBorder = new LineBorder( Color.red, 5 );
        unselectedBorder = new LineBorder( Color.green, 5 );

        setBackground( Color.green );

        myTurn = false;

        name = n;
        nameLabel = new JLabel( name , JLabel.CENTER );

        scoreLabel = new JLabel( "Score: 0", JLabel.CENTER );

        cards = new CardPanel[2][4];

        myCards = new JPanel();
        myCards.setBackground( Color.green );
        myCards.setLayout( new GridLayout( 2, 4 ) ); //sets the layout

        CardListener listener = new CardListener();

        for ( int k = 0; k < 2; k++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                cards[k][j] = new CardPanel( deck.draw(), i );
                cards[k][j].addMouseListener( listener );
                myCards.add( cards[k][j] );
            }
        }


        cards[(int) (Math.random() * 2)][((int) (Math.random() * 2) == 0)?
            0:3 ].flip(); //flips a random corner card to begin

        setLayout( new BorderLayout() );
        add( nameLabel, BorderLayout.NORTH );
        add( myCards, BorderLayout.CENTER );
        add( scoreLabel, BorderLayout.SOUTH );
        setBorder( unselectedBorder );
        updateScore();
    }

    private void updateScore()
    {
        scoreLabel.setText( "Score: " + score() );
    }

    private int scoreFor( Card c )
    {
        if ( c.getValue() == 1 )
        {
            return 0;
        }
        else if ( c.getValue() == 11 || c.getValue() == 12 )
        {
            return 10;
        }
        else if ( c.getValue() == 13 )
        {
            return -3;
        }
        return c.getValue();
    }

    public TurnController turn()
    {
        return turnController;
    }

    public void swapCards( CardPanel card1, CardPanel card2 )
    {
        Card temp = card1.getCard();
        card1.setCard( card2.getCard() );
        card2.setCard( temp );
    }

    public CardPanel[][] getCards()
    {
        return cards;
    }

    public int score()
    {
        int score = 0;

        for ( int k = 0; k < 4; k++ )
        {
            Card card1 = cards[0][k].getCard();
            Card card2 = cards[1][k].getCard();
            if ( card1.getValue() != card2.getValue() )
            {
                score += card1.isFaceUp()? scoreFor( card1 ) : 0;
                score += card2.isFaceUp()? scoreFor( card2 ) : 0;
            }
            else if ( !card1.isFaceUp() || !card2.isFaceUp() )
            {
                score += card1.isFaceUp()? scoreFor( card1 ) : 0;
                score += card2.isFaceUp()? scoreFor( card2 ) : 0;
            }
        }
        return score;
    }

    public String playerName()
    {
        return name;
    }

    public boolean gameOver()
    {
        for ( int k = 0; k < 2; k++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                if ( !cards[k][j].getCard().isFaceUp() )
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void setTurn( boolean b )
    {
        myTurn = b;
        setBorder( b? selectedBorder:unselectedBorder );

    }

    public boolean myTurn()
    {
        return myTurn;
    }

    public void flipAll()
    {
        for ( int k = 0; k < 2; k++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                if ( !cards[k][j].faceUp() )
                {
                    cards[k][j].flip();
                }
            }
        }
        updateScore();
    }

    public void setPlayerName( String s )
    {
        name = s;
        nameLabel.setText( name );
    }

    public void updateImages()
    {
        for ( int k = 0; k < 2; k++)
        {
            for ( int j = 0; j < 4; j++)
            {
                cards[k][j].updateImage();
            }
        }
    }

    private class CardListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if ( !myTurn )
            {
                return;
            }

            CardPanel clickedPanel = (CardPanel) e.getComponent();

            if ( !clickedPanel.faceUp() && selectedPanel == null ) //flips facedown card
            {
                clickedPanel.flip();
                turnController.endTurn();
                updateScore();
                turnController.checkGameEnd();
            }
            else //the clicked card is face up or tries to switch with a facedown
            {
                if ( selectedPanel == null ) //no card is currently selected
                {
                    selectedPanel = clickedPanel;
                    clickedPanel.select( true );
                }
                else //a card is selected
                {
                    if ( selectedPanel == clickedPanel ) //deselect
                    {
                        selectedPanel = null;
                        clickedPanel.select( false );
                    }
                    else //clicked on two different panels
                    {
                        selectedPanel.select( false ); //deselects it
                        swapCards( selectedPanel, clickedPanel );
                        discard.ensureFaceUp();
                        selectedPanel = null;
                        turnController.endTurn();
                        updateScore();
                        turnController.checkGameEnd();
                    }
                }
            }

        }
    }

    private class DeckListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if ( !myTurn || discard.isSelected() )
            {
                return;
            }

            if ( selectedPanel != null && deck.numCardsLeft() > 0 )
            {
                selectedPanel.select( false );
                discard.discard( selectedPanel.getCard() );
                selectedPanel.setCard( deck.draw() );
                selectedPanel.flip();
                selectedPanel = null;
                deckPanel.checkIfEmpty();
                turnController.endTurn();
                updateScore();
                turnController.checkGameEnd();
            }
        }
    }

    private class DiscardListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if ( !myTurn )
            {
                return;
            }

            if ( !discard.isEmpty() )
            {
                CardPanel clickedPanel = ((DiscardPanel) e.getComponent()).getPanel();

                if ( selectedPanel == null ) //no card is currently selected
                {
                    System.out.println("selecting");
                    selectedPanel = clickedPanel;
                    clickedPanel.select( true );
                }
                else
                {
                    if ( selectedPanel == clickedPanel )
                    {
                        System.out.println("deselected");
                        clickedPanel.select( false );
                        selectedPanel = null;
                    }
                    else
                    {
                        selectedPanel.select( false ); //deselects it
                        clickedPanel.select( false );
                        System.out.println("swap");
                        swapCards( selectedPanel, clickedPanel );
                        discard.ensureFaceUp();
                        selectedPanel = null;
                        turnController.endTurn();
                        updateScore();
                        turnController.checkGameEnd();
                    }
                }
            }
        }
    }
}