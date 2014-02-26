import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class Game
{
    private GameBoardPanel board;
    private TurnController turn;
    private JFrame frame;
    private PlayerPanel[] players;
    private DeckPanel deck;
    private DiscardPanel discard;
    private JMenuBar menuBar;
    private CardImages images;

    public Game()
    {
        images = new CardImages();

        frame = null;

        Object input = null;

        input = JOptionPane.showInputDialog( null,
            "How Many Players?", "Players", JOptionPane.PLAIN_MESSAGE,
            null, new String[] { "2", "3", "4" }, "2" );
        if ( input == null )
        {
            System.exit( 0 );
        }



        int numPlayers = Integer.parseInt( (String) input );

        newGame( numPlayers, true );
    }

    private JMenuBar menu()
    {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu( "Game" );
        menuBar.add( menu );

        JMenuItem menuItem = new JMenuItem( "New Game" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                newGame();
            }
        });
        menu.add( menuItem );

        menuItem = new JMenuItem( "Instructions" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                new InstructionFrame();
            }
        });
        menu.add( menuItem );


        menuItem = new JMenuItem( "Quit" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                System.exit( 0 );
            }
        });
        menu.add( menuItem );

        menu = new JMenu( "Settings" );
        JMenu temp = new JMenu( "Number of Players" );
        menuItem = new JMenuItem( "2" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                newGame( 2, true );
            }
        });
        temp.add( menuItem );
        menu.add( temp );
        menuBar.add( menu );

        menuItem = new JMenuItem( "3" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                newGame( 3, true );
            }
        });
        temp.add( menuItem );

        menuItem = new JMenuItem( "4" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                newGame( 4, true );
            }
        });
        temp.add( menuItem );

        temp = new JMenu( "Change Names" );
        menu.add( temp );
        menuItem = new JMenuItem( "1" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                String name = JOptionPane.showInputDialog( "Player 1's name?", players[0].playerName() );
                players[0].setPlayerName( name == null? players[0].playerName():name );            }
        });
        temp.add( menuItem );

        menuItem = new JMenuItem( "2" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                String name = JOptionPane.showInputDialog( "Player 2's name?", players[1].playerName() );
                players[1].setPlayerName( name == null? players[1].playerName():name );            }
        });
        temp.add( menuItem );

        menuItem = new JMenuItem( "3" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                String name = JOptionPane.showInputDialog( "Player 3's name?", players[2].playerName() );
                players[2].setPlayerName( name == null? players[2].playerName():name );            }
        });
        if ( players.length < 3 )
        {
            menuItem.setEnabled( false );
        }
        temp.add( menuItem );

        menuItem = new JMenuItem( "4" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                String name = JOptionPane.showInputDialog( "Player 4's name?", players[3].playerName() );
                players[3].setPlayerName( name == null? players[3].playerName():name );
            }
        });
        if ( players.length < 4 )
        {
            menuItem.setEnabled( false );
        }
        temp.add( menuItem );

        temp = new JMenu( "Card Color" );
        menu.add( temp );
        menuItem = new JMenuItem( "Blue" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                images.setBlue( true );
                for ( int k = 0; k < players.length; k++ )
                {
                    players[k].updateImages();
                }
                deck.updateImage();
            }
        });
        temp.add( menuItem );

        menuItem = new JMenuItem( "Red" );
        menuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                images.setBlue( false );
                for ( int k = 0; k < players.length; k++ )
                {
                    players[k].updateImages();
                }
                deck.updateImage();
            }
        });
        temp.add( menuItem );

        return menuBar;
    }

    public void newGame()
    {
        newGame( players.length, false );
    }

    public void newGame( int numPlayers, boolean firstGame )
    {
        String[] names = null;
        if ( frame != null )
        {
            frame.dispose();
            frame.setVisible( false );
        }

        if ( firstGame )
        {
            if ( players != null )
            {
                names = new String[Math.max( numPlayers, players.length )];
                for ( int k = 0; k < players.length; k++ )
                {
                    names[k] = (k < players.length)? players[k].playerName():"";
                }
            }
            players = new PlayerPanel[numPlayers];
        }

        deck = new DeckPanel( new Deck(), images );
        discard = new DiscardPanel( images );
        turn = new TurnController( this );

        for ( int k = 0; k < numPlayers; k++ )
        {
            players[k] = new PlayerPanel( firstGame? JOptionPane.showInputDialog(
                "Player " + (k + 1) + "'s name?", ( names == null )? "":
                    names[k] ):players[k].playerName(), deck,
                    discard, turn, images );
        }

        turn.add( players );
        board = new GameBoardPanel( players, deck, discard ); //add players to the turn controller like acitonlisteners

        frame = new JFrame( "Golf" );
        frame.setJMenuBar( menu() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( board );
        frame.pack();
        frame.setVisible(true);
    }

    public void getWinner()
    {
        int lowestScore = 100;
        String winner = "";

        for ( int k = 0; k < players.length; k++ )
        {
            players[k].flipAll();
            int temp = players[k].score();
            if ( temp < lowestScore )
            {
                lowestScore = temp;
                winner = players[k].playerName();
            }
        }
        String message = "Congratulations, " + winner + ", you win!!";
        JOptionPane.showMessageDialog( frame, message );
    }

    public JFrame frame()
    {
        return frame;
    }
}
