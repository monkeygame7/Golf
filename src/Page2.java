import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Page2 extends JPanel
{
    private JLabel title;
    private JLabel instructions;
    private JLabel picture;
    private CardImages pics;

    public Page2()
    {
        title = new JLabel();
        title.setFont( new Font( "Helvetica", Font.BOLD, 32) );
        title.setHorizontalAlignment( JLabel.CENTER );
        title.setText( "Scoring" );

        pics = new CardImages();

        instructions = new JLabel();
        instructions.setHorizontalAlignment( JLabel.CENTER );
        instructions.setText(
            "<html>A = 0<br>" +
            "2-10 = value of card (i.e. 4 = 4, 7 = 7)<br>" +
            "Q and J = 10<br>" +
            "K = -3</html>" );

        JPanel bottom = new JPanel();
        bottom.setLayout( new GridLayout( 2, 2 ) );
        bottom.setBackground( Color.cyan );
        bottom.setAlignmentY( CENTER_ALIGNMENT );

        picture = new JLabel();
        picture.setIcon( pics.get( new Card( Suits.SPADES, 13, true ) ) );
        picture.setText( "King = -3" );
        picture.setBorder( new EmptyBorder( 5, 0, 5, 0) );
        bottom.add( picture );

        picture = new JLabel();
        picture.setIcon( pics.get( new Card( Suits.DIAMONDS, 12, true ) ) );
        picture.setText( "Q = 10" );
        picture.setBorder( new EmptyBorder( 5, 0, 5, 0) );
        bottom.add( picture );

        picture = new JLabel();
        picture.setIcon( pics.get( new Card( Suits.HEARTS, 1, true ) ) );
        picture.setText( "A = 0" );
        picture.setBorder( new EmptyBorder( 5, 0, 5, 0) );
        bottom.add( picture );

        picture = new JLabel();
        picture.setIcon( pics.get( new Card( Suits.CLUBS, 5, true ) ) );
        picture.setText( "5 = 5" );
        picture.setBorder( new EmptyBorder( 5, 5, 5, 0) );
        bottom.add( picture );

        setLayout( new BorderLayout() );
        add( title, BorderLayout.NORTH );
        add( instructions, BorderLayout.CENTER );
        add( bottom, BorderLayout.SOUTH );
        setBackground( Color.cyan );
    }
}
