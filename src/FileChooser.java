
import javax.swing.*;
import java.io.File;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 14:44
 */
class FileChooser {
    private JFileChooser chooser;
    String name;

    FileChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            name = chooser.getSelectedFile().getPath();
        }
    }
    String getf(){
        return name;
    }
}
