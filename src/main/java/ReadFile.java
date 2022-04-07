import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class ReadFile {
    private static final Logger log = LoggerFactory.getLogger(ReadFile.class.getName());

    List<String> getListFile(String fileName) {
        List<String> lis = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1251"))) {
            String s = "";
            while ((s = reader.readLine()) != null) {
                lis.add(s);
            }
        } catch (IOException e) {
            log.error("Ошибка чтения файла {}", e);
        }
        return lis;
    }

    String getStringFile(String fileName) {
        String fileContent = "";
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1251"))) {
            int c = 0;
            while ((c = r.read()) != -1) {
                fileContent += (char) c;
            }
        } catch (IOException e) {
            log.error("Ошибка чтения файла {}", e);
        }
        return fileContent;
    }
}

