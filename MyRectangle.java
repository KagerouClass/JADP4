import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MyRectangle extends shapeComponent {
    public MyRectangle(Color color, float stroke,
                int x_start, int y_start,
                int x_end, int y_end)
    {
        super(color, stroke, x_start, y_start, x_end, y_end);
        setType("Rectangle");
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(getStroke(), BasicStroke.JOIN_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawRect(Math.min(getX_start(), getX_end()),
                   Math.min(getY_start(), getY_end()),
                   Math.abs(getX_start() - getX_end()),
                   Math.abs(getY_start() - getY_end()) );
        removePointFromHashSet();
        gatherShapePoint();
    }

    private void gatherShapePoint()
    {
        int s = getStroke() > 5 ? (int)(getStroke()/1.5) : 5;
        s = s<10 ? s : 10;//厚度控制
        for (int out = -s; out <= s; out++)
        {
            for(int in = -s; in <= s; in++)
            {
                for (int i = Math.min(getX_start(), getX_end()) + out;
                     i <= Math.max(getX_start(), getX_end()) + out;
                     ++i)
                {
                    int x = i;
                    int y11 = getY_start() + in;
                    int y22 = getY_end()   + in;
                    addPointToHashSet(x, y11);
                    addPointToHashSet(x, y22);
                }
                for (int i = Math.min(getY_start(), getY_end()) + in;
                     i <= Math.max(getY_start(), getY_end()) + in;
                     ++i)
                {
                    int y = i;
                    int x11 = getX_start() + out;
                    int x22 = getX_end()   + out;
                    addPointToHashSet(x11, y);
                    addPointToHashSet(x22, y);
                }
            }
        }
    }
}
