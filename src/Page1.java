import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Page1 extends JPanel
{
    private JLabel title;
    private JLabel instructions;

    public Page1()
    {
        title = new JLabel();
        title.setFont( new Font( "Helvetica", Font.BOLD, 32) );
        title.setHorizontalAlignment( JLabel.CENTER );
        title.setText( "Objective" );
        instructions = new JLabel();
        instructions.setHorizontalAlignment( JLabel.CENTER );
        instructions.setText(
            "<html>Golf is a card game for two or more players, in which the object is<br>" +
            " to score as little as possible, as in the sport of Golf. In front<br>" +
            "of each player is a layout of cards arranged in a rectangle, and<br>" +
            "players improve their scores by drawing new cards to replace<br>" +
            "unwanted cards, which they discard, and moving their cards around.</html>" );
        setLayout( new BorderLayout() );
        add( title, BorderLayout.NORTH );
        add( instructions, BorderLayout.CENTER);
        setBackground( Color.cyan );
    }
}
