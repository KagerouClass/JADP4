import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class MyLine extends shapeComponent {
    public MyLine(Color color, float stroke,
                  int x_start, int y_start,
                  int x_end, int y_end)
    {
        super(color, stroke, x_start, y_start, x_end, y_end);
        setType("Line");
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(getStroke(), BasicStroke.JOIN_ROUND,BasicStroke.JOIN_BEVEL));
        g.drawLine(getX_start(), getY_start(), getX_end(), getY_end());
        gatherShapePoint();
    }
    private void gatherShapePoint()
    {
        int s = getStroke()>5 ? (int)(getStroke()/1.5) : 5;
        s = s<30? s :30;
        if(getY_start() - getY_end() == 0)
        {
            for(int out = -s; out < s; ++out)
                for (int i = Math.min(getX_start(), getX_end());
                 i <= Math.max(getX_start(), getX_end());
                 ++i) {
                    addPointToHashSet(i, getY_start() + s);
                    addPointToHashSet(i, getY_start() + s);
                }
            return;
        }

        float k = (getY_end()-getY_start())/(float)(getX_end()-getX_start());

        for(int out = -s; out < s; ++out)
            for (int i = Math.min(getX_start(), getX_end());
                 i <= Math.max(getX_start(), getX_end());
                  ++i)
            {
                int x = i + s;
                int y = (int)(k*(x-s-(getX_start()))+getY_end());
                addPointToHashSet(x, y);
            }
    }
}