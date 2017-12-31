import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
abstract class action//使用RTTI+反射避免出现switch case，增强可维护性
{
    abstract public void func();
}
class SaveReadyToCreate extends action
{
    @Override
    public void func() {
        IOFile newOutput = new IOFile();
        newOutput.savePicture();
    }
}
class OpenReadyToCreate extends action
{
    @Override
    public void func() {
        IOFile newOutput = new IOFile();
        newOutput.openPicture();
    }
}
class ExitReadyToCreate extends action
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
class stringReadyToCreate extends action
{
    @Override
    public void func() {
        panelOfCAD.currentCreateShape = "stringCreate";
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
        catch (IllegalAccessException exceptionIAE)
        {
            exceptionIAE.printStackTrace();
        }
        catch (InstantiationException exceptionIE)
        {
            exceptionIE.printStackTrace();
        }
        catch (ClassNotFoundException exceptionCNFE)
        {
            exceptionCNFE.printStackTrace();
        }

    }
}
