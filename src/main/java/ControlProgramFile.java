import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс который аккумулирует всю информацию об управляющей программе.
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class ControlProgramFile {
    private ReadFile rf;
    private NativeFileConverter nfc;
    private Image image;
    private File pathToFile;

    ControlProgramFile(File file){
        pathToFile = file;
        nfc = new NativeFileConverter();
        rf = new ReadFile();
    }

    List<String> getDataFromFile(){
        if(ExtensionHelper.getFileExtension(pathToFile.getName()).equalsIgnoreCase("pgm")) {
            nfc.convertPGM_to_XXL(pathToFile);
            return  rf.readFile(ExtensionHelper.changeNameExtensionPGMtoXXL(pathToFile));
        } else {
            return  rf.readFile(pathToFile.getAbsolutePath());
        }
    }
    private String listIntoText(List<String> list){
        String s="";
        s = list.stream().map(q -> q+"\n").reduce(s, String::concat);
        return s;
    }
    ImageIcon getImage(){
        try {
            image = ImageIO.read(pathToFile);
            if( image == null) {
                image = ImageIO.read(getClass().getResource("unknownXXL.png"));
            }
        } catch (IOException ex) {
        }
        return new ImageIcon(image);
    }
}

