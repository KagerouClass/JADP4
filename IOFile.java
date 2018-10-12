import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOFile
{
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    static String input;
    public void savePicture()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle("Select Pictire");
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.showSaveDialog(fileChooser);

        if(null == fileChooser.getSelectedFile())//not choose any file
            return;
        File newFile = new File(fileChooser.getSelectedFile().getPath());
        if(!newFile.getAbsolutePath().contains(".mycad"))
            newFile = new File(newFile.getAbsolutePath() + ".mycad");
        //在文件尾部加上后缀名
        try {
            fileWriter = new FileWriter(newFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            String background;
            background = miniCAD.panel.getBackground().getRed()+","+
                    miniCAD.panel.getBackground().getRed()+","+
                    miniCAD.panel.getBackground().getRed();
            bufferedWriter.write(background);
            bufferedWriter.newLine();
            for(shapeComponent eachShape : panelOfCAD.listOfComponent)
            {
                String temp;
                if(!eachShape.getType().equals("String"))//逗号做分隔符而不是空格的原因是字体可能带空格
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
                            tmpString.getY_end()+","+
                            tmpString.getFont()+","+
                            tmpString.getFontSize()+","+
                            tmpString.getString();
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
    public class MyFileFilter extends FileFilter
    {
        public boolean accept(File file) {
            if(file.getName().contains(".mycad"))
                return true;
            else
                return false;
        }

        public String getDescription()
        {
            return "mycad file(*.mycad)";
        }
    }
    public void openPicture()
    {
        MyFileFilter myFileFilter = new MyFileFilter();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(myFileFilter);
        fileChooser.showDialog(null, null);

        if(null == fileChooser.getSelectedFile())
            return;
        File file = new File(fileChooser.getSelectedFile().getPath());
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString;
            miniCAD.panel.removeAllShapes();
            tempString = bufferedReader.readLine();
            input = tempString + ",";
            int R          = Integer.parseInt(parseLine());
            int G          = Integer.parseInt(parseLine());
            int B          = Integer.parseInt(parseLine());
            miniCAD.panel.setBackground(new Color(R, G, B));
            while ((tempString = bufferedReader.readLine()) != null)
            {
                input = tempString + ",";
                openCADFile();
            }
            miniCAD.panel.repaint();
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void openCADFile()
    {
        String type    = parseLine();
        int R          = Integer.parseInt(parseLine());
        int G          = Integer.parseInt(parseLine());
        int B          = Integer.parseInt(parseLine());
        float Stroke   = Float.parseFloat(parseLine());
        int x_start    = Integer.parseInt(parseLine());
        int y_start    = Integer.parseInt(parseLine());
        int x_end      = Integer.parseInt(parseLine());
        int y_end      = Integer.parseInt(parseLine());

        if(type.equals("Rectangle"))
        {
            MyRectangle tmp = new MyRectangle(new Color(R,G,B), Stroke, x_start, y_start, x_end, y_end);
            panelOfCAD.listOfComponent.add(tmp);
        }
        else if(type.equals("String"))
        {
            String font = parseLine();
            int fontSize = Integer.parseInt(parseLine());
            String string = parseLine();
            MyString tmp = new MyString(new Color(R,G,B), Stroke, x_start, y_start, x_end, y_end, font, fontSize, string);
            panelOfCAD.listOfComponent.add(tmp);
        }
        else if(type.equals("Line"))
        {
            MyLine tmp = new MyLine(new Color(R,G,B), Stroke, x_start, y_start, x_end, y_end);
            panelOfCAD.listOfComponent.add(tmp);
        }
        else if(type.equals("Circle"))
        {
            MyCircle tmp = new MyCircle(new Color(R,G,B), Stroke, x_start, y_start, x_end, y_end);
            panelOfCAD.listOfComponent.add(tmp);
        }
        input = "";
    }
    private static String parseLine()
    {
        int indexOfSpace = input.indexOf(",");// find next space in the given sentence
        String res = input.substring(0, indexOfSpace);
        input = input.substring(indexOfSpace + 1);
        return res;
    }
}
