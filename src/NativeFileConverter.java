

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 10:51
 */
public class NativeFileConverter {
    private File file;
    private String pathConvert = "C:\\qw\\java\\ProgrammEdit\\src\\resources\\Bin\\Winxiso.exe";

    public NativeFileConverter(File file) {
        this.file = file;
        convertPGM_to_XXL();
    }

    public void convertPGM_to_XXL() {
        ProcessBuilder pb = new ProcessBuilder(pathConvert, file.toString(), "-x", "-l", "-i");
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
