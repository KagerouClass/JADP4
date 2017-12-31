import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class MyString extends shapeComponent {
    private String font, string;
    private int fontSize;
    private double Width, Height;

    public MyString(Color color, float stroke,
                    int x_start, int y_start,
                    int x_end,   int y_end,
                    String font, int fontSize, String string)
    {
        super(color, stroke, x_start, y_start, x_end, y_end);
        this.font = font;
        this.string = string;
        this.fontSize = fontSize;
        setType("String");
    }
    @Override
    public void draw(Graphics2D g)
    {
        g.setPaint(getColor());
        Font f = new Font(font, Font.PLAIN, Math.abs(getX_end() - getX_start())/2 );
        g.setFont(f);

        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D stringBounds = f.getStringBounds(string, context);
        Height = stringBounds.getHeight();
        Width = stringBounds.getWidth();
        if (string!= null )
            g.drawString(string,(int)(getX_start()-Width/2), (int)(getY_start()+Height/6));

        gatherShapePoint();
    }
    public String getString()
    {
        return string;
    }

    public String getFont()
    {
        return font;
    }
    public int getFontSize()
    {
        return fontSize;
    }
    private void gatherShapePoint()
    {
        for (int i = (int)(getX_start()-Width/2); i<=getX_start()+Width/2; ++i)
            for (int j = (int) (getY_start()-Height/2); j<=getY_start()+Height/2; ++j)
                addPointToHashSet(i,j);
    }
}
