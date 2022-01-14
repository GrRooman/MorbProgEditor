
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger log =  LoggerFactory.getLogger(NativeFileConverter.class.getName());

    public NativeFileConverter() {
        userPreferences = new UserPreferences();
    }

    public void convertPGM_to_XXL(File file) {
        try {
            pb = new ProcessBuilder(putPathToConvert(), file.toString(), "-x", "-l", "-i", "-s");
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            log.error("convertPGM_to_XXL {}", e);
        } catch (InterruptedException e) {
            log.error("convertPGM_to_XXL {}", e);
        }

    }
    public void convertXXL_to_PGM(String file, String newFileName) {
        try {
            pb = new ProcessBuilder(putPathToConvert(), file, newFileName, "-f", "-l", "-i", "-s");
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            log.error("convertXXL_to_PGM {}", e);
        } catch (InterruptedException e) {
            log.error("convertXXL_to_PGM {}", e);
        }
    }
    private String putPathToConvert(){
        return   userPreferences.getPathToConverter();
    }
}