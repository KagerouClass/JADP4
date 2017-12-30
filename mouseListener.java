import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
abstract class createShape
{
    abstract public void create();
}
class lineCreate extends createShape
{
    @Override
    public void create() {
        MyLine newLine = new MyLine(Color.BLACK, 1,
                mouseListener.start_x,mouseListener.start_y,
                mouseListener.end_x, mouseListener.end_y);
        miniCAD.panel.listOfComponent.add(newLine);
    }
}
class rectCreate extends createShape
{
    @Override
    public void create() {
        MyRectangle newRectangle = new MyRectangle(Color.BLACK, 1,
                mouseListener.start_x,mouseListener.start_y,
                mouseListener.end_x, mouseListener.end_y);
        miniCAD.panel.listOfComponent.add(newRectangle);
    }
}
class circleCreate extends createShape
{
    @Override
    public void create() {
        MyCircle newCircle = new MyCircle(Color.BLACK, 1,
                mouseListener.start_x,mouseListener.start_y,
                mouseListener.end_x, mouseListener.end_y);
        miniCAD.panel.listOfComponent.add(newCircle);
    }
}
public class mouseListener implements MouseListener, MouseMotionListener, MouseWheelListener
{
    static int start_x;
    static int start_y;
    static int end_x;
    static int end_y;
    static int prev_x;
    static int prev_y;
    @Override
    public void mouseClicked(MouseEvent event)
    {

    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        start_x = event.getX();
        start_y = event.getY();
        prev_x = start_x;
        prev_y = start_y;

        System.out.println("currentMouseLocation: x:" + start_x + "y: " + start_y);
        panelOfCAD.currentShapeIndex = miniCAD.panel.findPointBelongShapeIndex(start_x, start_y);
        System.out.println(panelOfCAD.currentShapeIndex);
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
        end_x = event.getX();
        end_y = event.getY();
        if(!panelOfCAD.currentCreateShape.equals(""))//当前需要创建图形
        {
            try {
                System.out.println(panelOfCAD.currentCreateShape);
                Class c = Class.forName(panelOfCAD.currentCreateShape);
                createShape response = (createShape)c.newInstance();
                response.create();
                panelOfCAD.currentCreateShape = "";
                miniCAD.panel.repaint();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent event)
    {
        int move_x = event.getX();
        int move_y = event.getY();
        int delta_x = move_x - prev_x;
        int delta_y = move_y - prev_y;

        System.out.println("drag" + event.getX() + event.getY());
        if(panelOfCAD.currentShapeIndex >= 0)//有形状被选中
            if(Math.abs(delta_x) > 10 || Math.abs(delta_y) > 10)
            {
                prev_x = move_x;
                prev_y = move_y;
                miniCAD.panel.getShape(panelOfCAD.currentShapeIndex).moveShape(delta_x, delta_y);
                miniCAD.panel.repaint();
            }
        //moveShape();

    }

    @Override
    public void mouseMoved(MouseEvent event)
    {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        System.out.println("Mouse Wheel Rotating @:[" + event.getX() +
                ", " + event.getY() + "]");
        if(panelOfCAD.currentShapeIndex >= 0)
        {
            float currentStroke =
                panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex).getStroke();
            panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex).setStroke(
                currentStroke - event.getWheelRotation()>0 ? currentStroke-event.getWheelRotation() : 1);
            miniCAD.panel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event){}

    @Override
    public void mouseExited(MouseEvent event){}
}
