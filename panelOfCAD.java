import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class panelOfCAD extends JPanel
{
    public static ArrayList<shapeComponent> listOfComponent = new ArrayList<shapeComponent>();
    public static int currentShapeIndex = 0;
    public static String currentCreateShape = "";
    private boolean flag = false;

    public panelOfCAD(){
        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(800,600));
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        if(!flag)
        {
            MyRectangle test1 = new MyRectangle(Color.black, 1, 50, 100, 90, 300);
            listOfComponent.add(test1);
            test1.draw(g2d);
            flag = true;
        }
        for( shapeComponent s : listOfComponent)
        {
            s.draw(g2d);
        }
    }

    public void add(shapeComponent s)
    {
        listOfComponent.add(s);
    }

    public int findPointBelongShapeIndex(int x, int y)
    {
        for(int i = 0; i < listOfComponent.size(); ++i)
        {
            shapeComponent shape = listOfComponent.get(i);
            if(shape.isContainPoint(x, y))
            {
                return i;
            }
        }
        return -1;
    }

    public shapeComponent getShape(int index)
    {
        return listOfComponent.get(index);
    }

    public void setShape(int index, shapeComponent shape)
    {
        listOfComponent.set(index, shape);
    }

    public int getNumberOfShape(int index, shapeComponent shape)
    {
        return listOfComponent.size();
    }

    public void removeAllShapes()
    {
        listOfComponent.clear();
    }
}
