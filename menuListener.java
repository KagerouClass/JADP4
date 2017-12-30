import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
abstract class action
{
    abstract public void func();
}
class Exit extends action
{
    @Override
    public void func() {
        System.exit(0);
    }
}
class lineReadyToCreate extends action
{
    @Override
    public void func() {
        panelOfCAD.currentCreateShape = "lineCreate";
        System.out.println("Change to create " + panelOfCAD.currentCreateShape);
    }
}
class rectReadyToCreate extends action
{
    @Override
    public void func() {
        panelOfCAD.currentCreateShape = "rectCreate";
        System.out.println("Change to create " + panelOfCAD.currentCreateShape);
    }
}
class circleReadyToCreate extends action
{
    @Override
    public void func() {
        panelOfCAD.currentCreateShape = "circleCreate";
        System.out.println("Change to create " + panelOfCAD.currentCreateShape);
    }
}
public class menuListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            System.out.println(e.getActionCommand());
            Class c = Class.forName(e.getActionCommand() + "ReadyToCreate");
            action response = (action)c.newInstance();
            response.func();
        }
        catch (ClassNotFoundException exceptionCNFE)
        {
            exceptionCNFE.printStackTrace();
        }
        catch (IllegalAccessException exceptionIAE)
        {
            exceptionIAE.printStackTrace();
        }
        catch (InstantiationException exceptionIE)
        {
            exceptionIE.printStackTrace();
        }
    }
}
