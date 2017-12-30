import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class miniCAD {
    public static JFrame frame = new JFrame();
    public static panelOfCAD panel = new panelOfCAD();

    public static void main(String[] args)
    {
        initMenuBar();

        frame.setTitle("miniCAD");
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        frame.addKeyListener(new keyboardListener());
        panel.addMouseListener(new mouseListener());
        panel.addMouseWheelListener(new mouseListener());
        panel.addMouseMotionListener(new mouseListener());
        //panel.addMouseMotionListener(new Mouse_Listener());
        //test


    }
    private static void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // initial the "file" menu
        JMenu fileInMenu = new JMenu("File");
        menuBar.add(fileInMenu);

        JMenuItem openInItem = new JMenuItem("Open");

        fileInMenu.add(openInItem);

        JMenuItem saveInItem = new JMenuItem("Save");

        fileInMenu.add(saveInItem);

        JMenuItem exitInItem = new JMenuItem("Exit");
        exitInItem.addActionListener(new menuListener());
        fileInMenu.add(exitInItem);

        // initial the "shape" menu
        JMenu shapeInMenu = new JMenu("Shape");
        menuBar.add(shapeInMenu);

        JMenuItem lineInItem = new JMenuItem("line");
        lineInItem.addActionListener(new menuListener());
        shapeInMenu.add(lineInItem);

        JMenuItem rectInItem = new JMenuItem("rect");
        rectInItem.addActionListener(new menuListener());
        shapeInMenu.add(rectInItem);

        JMenuItem circleInItem = new JMenuItem("circle");
        circleInItem.addActionListener(new menuListener());
        shapeInMenu.add(circleInItem);
    }

}
