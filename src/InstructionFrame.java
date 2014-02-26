import javax.swing.JFrame;


public class InstructionFrame
    extends JFrame
{
    private InstructionPanel panel;

    public InstructionFrame()
    {
        super( "Instructions" );
        panel = new InstructionPanel();
        add( panel );
        pack();
        setVisible(true);
    }
}
