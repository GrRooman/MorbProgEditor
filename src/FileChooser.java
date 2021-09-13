
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 14:44
 */
class FileChooser extends JFileChooser{
    private UserPreferences up;
   
    FileChooser() {
        up = new UserPreferences();
        setCurrentDirectory(new File(up.getPathToControlProgram()));

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Program files", "xxl", "pgm");
    setFileFilter(filter);

    setAccessory(new ImagePreviewer(this));

    setFileView(new FileIconView(filter));  //
    }
}
