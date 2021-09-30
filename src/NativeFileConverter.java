 

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
    private Path path;
    private final String nameNativeConverter = "Winxiso.exe";
    private String pathConverter = "\\src\\resources\\Bin\\"+nameNativeConverter;

    public NativeFileConverter() {
        path = Paths.get("").toAbsolutePath();
    }

    public void convertPGM_to_XXL(File file) {
        try {
            pb = new ProcessBuilder(path+pathConverter, file.toString(), "-x", "-l", "-i", "-s");
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void convertXXL_to_PGM(File file) {
        try {                                          //  "C:/Books/MorbProgEditor/test/test.pgm"
            System.out.println(file.toString());
            pb = new ProcessBuilder(path+pathConverter, file.toString(), "-o", , "-l", "-i", "-s" );
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}