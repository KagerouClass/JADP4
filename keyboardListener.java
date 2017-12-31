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
        //delete:127 up:38 down:40 left:37 right:39
        System.out.println(event.getKeyCode());
        if(event.getKeyCode() == 127)
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                panelOfCAD.listOfComponent.remove(panelOfCAD.currentShapeIndex);
                miniCAD.panel.repaint();
                panelOfCAD.currentShapeIndex = -1;
            }
        }
        else if(event.getKeyCode() == 38)//up
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                shapeComponent tmpshape = panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex);
                int changeY = tmpshape.getY_end()<0 ? 0 : tmpshape.getY_end() - 2;
                tmpshape.setEndPoint(tmpshape.getX_end(), changeY);
                miniCAD.panel.repaint();
            }
        }
        else if(event.getKeyCode() == 40)//down
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                shapeComponent tmpshape = panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex);
                int changeY = tmpshape.getY_end()>600 ? 600 : tmpshape.getY_end() + 2;
                tmpshape.setEndPoint(tmpshape.getX_end(), changeY);
                miniCAD.panel.repaint();
            }
        }
        else if(event.getKeyCode() == 37)//left
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                shapeComponent tmpshape = panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex);
                int changeX = tmpshape.getX_end()<0 ? 0 : tmpshape.getX_end() - 2;
                tmpshape.setEndPoint(changeX, tmpshape.getY_end());
                miniCAD.panel.repaint();
            }
        }
        else if(event.getKeyCode() == 39)//right
        {
            if(panelOfCAD.currentShapeIndex >= 0 )
            {
                shapeComponent tmpshape = panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex);
                int changeX = tmpshape.getX_end()>800 ? 8000 : tmpshape.getX_end() + 2;
                tmpshape.setEndPoint(changeX, tmpshape.getY_end());
                miniCAD.panel.repaint();
            }
        }
        else
        {
            //panelOfCAD.currentShapeIndex = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
