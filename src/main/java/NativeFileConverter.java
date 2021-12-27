//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * User: Grooman
 * Date: 26.07.21
 * Time: 10:51
 */
class NativeFileConverter {
    private ProcessBuilder pb;
    private UserPreferences userPreferences;
    private String pathConverter;

//    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public NativeFileConverter() {
        userPreferences = new UserPreferences();
       pathConverter = putPathToConvert();
    }

    public void convertPGM_to_XXL(File file) {
        try {
            pb = new ProcessBuilder(pathConverter, file.toString(), "-x", "-l", "-i", "-s");
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void convertXXL_to_PGM(String file, String newFileName) {
        try {
            pb = new ProcessBuilder(pathConverter, file, newFileName, "-f", "-l", "-i", "-s");
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String putPathToConvert(){
//        logger.info("test {}", userPreferences.getPathToConverter());  // понять почему вызывается дважды
        // написать предпреждение о том, что конвертер не выбран
        return   userPreferences.getPathToConverter();
    }
}