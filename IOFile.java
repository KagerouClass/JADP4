import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOFile
{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    public void savePicture()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Select Pictire");
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.showSaveDialog(fileChooser);
        if(null == fileChooser.getSelectedFile())
            return;
        File newFile = new File(fileChooser.getSelectedFile().getPath());
        try {
            fileWriter = new FileWriter(newFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(shapeComponent eachShape : panelOfCAD.listOfComponent)
            {
                String temp = null;
                if(!eachShape.getType().equals("String"))
                {
                    temp = eachShape.getType()+","+
                            eachShape.getColor().getRed()+","+
                            eachShape.getColor().getGreen()+","+
                            eachShape.getColor().getBlue()+","+
                            eachShape.getStroke()+","+
                            eachShape.getX_start()+","+
                            eachShape.getY_start()+","+
                            eachShape.getX_end()+","+
                            eachShape.getY_end();
                }
                else
                {
                    MyString tmpString = (MyString)eachShape;
                    temp = tmpString.getType()+","+
                            tmpString.getColor().getRed()+","+
                            tmpString.getColor().getGreen()+","+
                            tmpString.getColor().getBlue()+","+
                            tmpString.getStroke()+","+
                            tmpString.getX_start()+","+
                            tmpString.getY_start()+","+
                            tmpString.getX_end()+","+
                            tmpString.getFont()+","+
                            tmpString.getFontSize()+","+
                            tmpString.getString()
                            ;
                }
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                bufferedWriter.close();
                fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
