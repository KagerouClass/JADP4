import java.awt.*;

public class MyCircle extends shapeComponent {
    public MyCircle(Color color, float stroke,
                     int x_start, int y_start,
                     int x_end, int y_end)
    {
        super(color, stroke, x_start, y_start, x_end, y_end);
        setType("Circle");
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(getStroke(), BasicStroke.JOIN_ROUND,BasicStroke.JOIN_BEVEL));
        g.drawOval(Math.min(getX_start(), getX_end()),
                Math.min(getY_start(), getY_end()),
                Math.abs(getX_end() - getX_start()), Math.abs(getX_end() - getX_start()));
        removePointFromHashSet();
        gatherShapePoint();
    }
    private void gatherShapePoint()
    {
        for (int i =0;i<360;i++)
        {
            int s = getStroke()>5 ? (int)(getStroke()/1.5) : 5;
            s = s<10? s :10;
            for(int out =-s; out <= s; ++out)
            {
                for(int in = -s; in <= s; in++)
                {
                    double a = (double)Math.max(Math.abs(getX_start()-getX_end()),Math.abs(getY_start()-getY_end()))/2;
                    int x = (int)((double)Math.min(getX_start(), getX_end()) + a + out+ a * Math.cos((double)i/180*Math.PI));
                    //x = x + cos(i)
                    int y = (int)((double)Math.min(getY_start(), getY_end()) + a + in + a * Math.sin((double)i/180*Math.PI));
                    //y = y + sin(i)
                    addPointToHashSet(x, y);
                }
            }
        }
    }
}