import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page4 extends JPanel
{
    public Page4()
    {
        setBackground( Color.cyan );
        setLayout( new BorderLayout() );

        JLabel title = new JLabel();
        title.setFont( new Font( "Helvetica", Font.BOLD, 32) );
        title.setHorizontalAlignment( JLabel.CENTER );
        title.setText( "Flip" );

        add( title, BorderLayout.NORTH );

        JLabel instructions = new JLabel();
        instructions.setText( "<html></html>" );

        add( instructions, BorderLayout.CENTER );
    }
}
