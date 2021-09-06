 

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 10:51
 */
class NativeFileConverter {
    private ProcessBuilder pb;
    private String file;
    private Path path;
    private final String nameNativeConverter = "Winxiso.exe";
    private String pathConverter = "\\src\\resources\\Bin\\"+nameNativeConverter;

    public NativeFileConverter(String file) {
        this.file = file;
        path = Paths.get("").toAbsolutePath();

        convertPGM_to_XXL();
    }

    void convertPGM_to_XXL() {
        try {
            pb = new ProcessBuilder(path+pathConverter, file.toString(), "-x", "-l", "-i");
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void convertXXL_to_PGM() {
        try {
            pb = new ProcessBuilder(path+pathConverter, file.toString(), "-x", "-l", "-i");
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
