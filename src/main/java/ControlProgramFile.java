import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс который аккумулирует всю информацию об управляющей программе.
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class ControlProgramFile {
    private ReadFile readFile;
    private NativeFileConverter nfc;
    private Image image;
    private File pathToFile;

    ControlProgramFile(File file) {
        pathToFile = file;
        nfc = new NativeFileConverter();
        readFile = new ReadFile();
    }

    List<String> getDataFromFile() {
        if (ExtensionHelper.getFileExtension(pathToFile.getName()).equalsIgnoreCase("pgm")) {
            nfc.convertPGM_to_XXL(pathToFile);
            return readFile.getListFile(ExtensionHelper.changeNameExtensionPGMtoXXL(pathToFile));
        } else {
            return readFile.getListFile(pathToFile.getAbsolutePath());
        }
    }

    String getStringDataFromFile() {
        if (ExtensionHelper.getFileExtension(pathToFile.getName()).equalsIgnoreCase("pgm")) {
            nfc.convertPGM_to_XXL(pathToFile);
            return readFile.getStringFile(ExtensionHelper.changeNameExtensionPGMtoXXL(pathToFile));
        } else {
            return readFile.getStringFile(pathToFile.getAbsolutePath());
        }
    }

    String getFileName() {
        return pathToFile.getName();
    }

    ImageIcon getImage() {
        try {
            image = ImageIO.read(pathToFile);
            if (image == null) {
                image = ImageIO.read(getClass().getResource("unknownXXL.png"));
            }
        } catch (IOException ex) {
        }
        return new ImageIcon(image);
    }
}

