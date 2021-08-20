
import javax.swing.*;
import java.io.File;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 14:44
 */
class FileChooser extends JFileChooser{
   
    FileChooser() {
        setCurrentDirectory(new File("."));
    }

}
