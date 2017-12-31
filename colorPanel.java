import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class colorPanel extends JPanel{
    private class Change implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if(panelOfCAD.currentShapeIndex < 0)
                miniCAD.panel.setBackground(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
            else
                panelOfCAD.listOfComponent.get(panelOfCAD.currentShapeIndex).setColor(new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue()));
            redValueText.setText(redSlider.getValue()+"");
            greenValueText.setText(greenSlider.getValue()+"");
            blueValueText.setText(blueSlider.getValue()+"");
            miniCAD.panel.repaint();
        }

    }
    private JTextField redValueText   = new JTextField("0",2);
    private JTextField greenValueText = new JTextField("0",2);
    private JTextField blueValueText  = new JTextField("0",2);
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;

    public colorPanel() {
        JLabel redLabel   = new JLabel("Red");
        JLabel greenLabel = new JLabel("Green");
        JLabel blueLabel  = new JLabel("Blue");

        redSlider = new JSlider(0, 255, 0);
        redSlider.addChangeListener(new Change());

        greenSlider = new JSlider(0, 255, 0);
        greenSlider.addChangeListener(new Change());

        blueSlider = new JSlider(0, 255, 0);
        blueSlider.addChangeListener(new Change());

        redValueText.setEditable(false);
        greenValueText.setEditable(false);
        blueValueText.setEditable(false);

        JPanel redPanel = new JPanel();
        redPanel.add(redLabel);
        redPanel.add(redSlider);
        redPanel.add(redValueText);

        JPanel greenPanel = new JPanel();
        greenPanel.add(greenLabel);
        greenPanel.add(greenSlider);
        greenPanel.add(greenValueText);

        JPanel bluePanel = new JPanel();
        bluePanel.add(blueLabel);
        bluePanel.add(blueSlider);
        bluePanel.add(blueValueText);

        this.setLayout(new GridLayout(1,3, 20, 20));
        this.add(redPanel);
        this.add(greenPanel);
        this.add(bluePanel);
    }
}