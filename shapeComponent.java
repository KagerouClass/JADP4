import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;
import java.util.HashSet;

public class shapeComponent
{
    private String type = "";
    private Color color;
    private int x_start = -1, y_start = -1;
    private int x_end   = -1, y_end   = -1;
    private float stroke;
    private HashSet <Point> pointHashSet = new HashSet<Point>();

    public shapeComponent(Color color, float stroke,
                          int x_start, int y_start,
                          int x_end,   int y_end)
    {
        this.color = color;
        this.x_start = x_start;
        this.y_start = y_start;
        this.x_end = x_end;
        this.y_end = y_end;
        this.stroke = stroke;
    }
    public void draw(Graphics2D g) {};
    public void setStartPoint(int x, int y)
    {
        x_start = x;
        y_start = y;
    }
    public void setEndPoint(int x, int y)
    {
        x_end = x;
        y_end = y;
    }
    public void moveShape(int delta_x, int delta_y)
    {
        x_start += delta_x;
        y_start += delta_y;
        x_end   += delta_x;
        y_end   += delta_y;
    }
    public int getX_start()
    {
        return x_start;
    }

    public int getX_end()
    {
        return x_end;
    }

    public int getY_start()
    {
        return y_start;
    }

    public int getY_end()
    {
        return y_end;
    }

    public String getType()
    {
        return type;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public float getStroke()
    {
        return stroke;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setStroke(float stroke)
    {
        this.stroke = stroke;
    }

    public void addPointToHashSet(int x,int y)
    {

        pointHashSet.add(new Point(x, y));
    }

    public boolean isContainPoint(int x,int y)
    {
        return pointHashSet.contains(new Point(x, y));
    }
}
