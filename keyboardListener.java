import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardListener implements KeyListener
{
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        //delete:127
        if(event.getKeyCode() == 127)
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                panelOfCAD.listOfComponent.remove(panelOfCAD.currentShapeIndex);
                miniCAD.panel.repaint();
                panelOfCAD.currentShapeIndex = 0;
            }
        }
        else
        {
            panelOfCAD.currentShapeIndex = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
