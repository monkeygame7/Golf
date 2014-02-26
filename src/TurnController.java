import javax.swing.JOptionPane;

public class TurnController
{
    private PlayerPanel[] players;
    private Game game;
    private int currentLocation;

    public TurnController( Game g )
    {
        game = g;
        currentLocation = -1; //no array has been added
    }

    public void add( PlayerPanel[] p )
    {
        players = p;
        currentLocation = 0;
        players[currentLocation].setTurn( true );
    }

    public void checkGameEnd()
    {
        if ( gameOver() )
        {
            game.getWinner();
            int response = JOptionPane.showConfirmDialog( game.frame(), "Game Over!\n" + "New Game?" );

            if ( response == JOptionPane.YES_OPTION )
            {
                game.newGame();
            }
            else if ( response == JOptionPane.NO_OPTION )
            {
                System.exit( 0 );
            }
        }
    }

    public void endTurn()
    {
        players[currentLocation].setTurn( false );
        currentLocation = ( currentLocation + 1 ) % players.length;
        players[currentLocation].setTurn( true );
    }

    public boolean gameOver()
    {
        for ( int k = 0; k < players.length; k++ )
        {
            if ( players[k].gameOver() )
            {
                return true;
            }
        }
        return false;
    }
}
