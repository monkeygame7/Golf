import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JPanel;


public class GameBoardPanel extends JPanel
{
    private PlayerPanel[] players;
    private DeckPanel deck;
    private DiscardPanel discard;
    private GridBagLayout grid;
    private GridBagConstraints c;


    public GameBoardPanel( PlayerPanel[] p, DeckPanel d, DiscardPanel disc )
    {
        Object input = null;

        grid = new GridBagLayout();
        c = new GridBagConstraints();
        c.insets = new Insets( 1, 5, 1, 5 );

        setLayout( grid );
        setBackground( new Color( 51, 145, 58 ) );

        players = p;

        deck = d;
        discard = disc;

        if ( players.length == 2 )
        {
            make2Players();
        }
        else if ( players.length == 3 )
        {
            make3Players();
        }
        else if ( players.length == 4 )
        {
            make4Players();
        }
    }

    private void addPanel( JPanel p, GridBagLayout grid, GridBagConstraints c )
    {
        grid.setConstraints(p, c);
        add(p);
    }

    private void make2Players()
    {
        c.gridwidth = GridBagConstraints.RELATIVE;
        addPanel( players[0], grid, c );
        c.gridwidth = GridBagConstraints.REMAINDER;
        addPanel( players[1], grid, c );
        JPanel panel = new JPanel();
        panel.setBackground( new Color( 51, 145, 58 ) );
        panel.add( deck );
        panel.add( discard );
        addPanel( panel, grid, c );
    }

    private void make3Players()
    {
        c.gridwidth = GridBagConstraints.REMAINDER;
        addPanel( players[1], grid, c );
        c.gridwidth = GridBagConstraints.RELATIVE;
        JPanel panel = new JPanel();
        panel.setBackground( new Color( 51, 145, 58 ) );
        panel.add( players[0] );
        panel.add( deck );
        panel.add( discard );
        panel.add( players[2] );
        c.gridwidth = GridBagConstraints.REMAINDER;
        addPanel( panel, grid, c );
    }

    private void make4Players()
    {
        JPanel topPart = new JPanel();
        topPart.setBackground( new Color( 51, 145, 58 ) );
        topPart.add( players[1] );
        topPart.add( players[2] );
        c.gridwidth = GridBagConstraints.REMAINDER;
        addPanel( topPart, grid, c );
        JPanel panel = new JPanel();
        panel.setBackground( new Color( 51, 145, 58 ) );
        panel.add( players[0] );
        panel.add( deck );
        panel.add( discard );
        panel.add( players[3] );
        c.gridwidth = GridBagConstraints.REMAINDER;
        addPanel( panel, grid, c );
    }

    public PlayerPanel[] getPlayers()
    {
        return players;
    }
}
