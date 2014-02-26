import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page3 extends JPanel
{
    public Page3()
    {
        setBackground( Color.cyan );
        setLayout( new BorderLayout() );

        JLabel title = new JLabel();
        title.setFont( new Font( "Helvetica", Font.BOLD, 32) );
        title.setHorizontalAlignment( JLabel.CENTER );
        title.setText( "Moves" );

        add( title, BorderLayout.NORTH );

        JLabel instructions = new JLabel();
        instructions.setText( "<html>Each turn you may do one of the following four moves:<br>" +
        		"<ul><li>Flip up a facedown card" +
        		"<li>Swap two of your cards" +
        		"<li>Swap a card with a the top card of the deck" +
        		"<li>Swap a card with the top card of the discard pile</ul></html>" );

        add( instructions, BorderLayout.CENTER );
    }
}
