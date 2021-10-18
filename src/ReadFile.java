import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class ReadFile {

    ReadFile(){
    }
    String readFile(String fileName){
        Path p = Paths.get(fileName);
        String s = null;
        try {
            s = new String(Files.readAllBytes(p));
        } catch (IOException e) { e.printStackTrace(); }
        return s;
    }


}

