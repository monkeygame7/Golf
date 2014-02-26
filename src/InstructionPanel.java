import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class InstructionPanel
    extends JPanel
{
    private CardLayout layout;
    private JButton previous;
    private JButton next;
    private JPanel instruction;
    private int index;

    public InstructionPanel()
    {
        index = 1;

        instruction = new JPanel();
        layout = new CardLayout();
        instruction.setLayout( layout );

        setLayout( new BorderLayout() );

        instruction.add( new Page1(), "1" );
        instruction.add( new Page2(), "2" );
        instruction.add( new Page3(), "3" );

        previous = new JButton( "Back" );
        previous.setEnabled( false );
        previous.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                index--;
                layout.show( instruction, "" + index );
                if( index == 1 )
                {
                    previous.setEnabled( false );
                }
                next.setEnabled( true );
            }
        });

        next = new JButton( "Next" );
        next.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                index++;
                layout.show( instruction, "" + index );
                if( index == 3 )
                {
                    next.setEnabled( false );
                }
                previous.setEnabled( true );
            }
        });


        add( instruction, BorderLayout.CENTER );
        JPanel buttons = new JPanel();
        buttons.add( previous );
        buttons.add( next );
        add( buttons, BorderLayout.SOUTH );
    }
}
