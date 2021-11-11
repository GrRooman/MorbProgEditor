import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        String s = "";
        String sa = "";
        List<String> lis = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1251"))){
            while((s = reader.readLine()) != null){
                lis.add(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        for(String q:lis){
            sa+=q+"\n";
        }
        return sa;
    }


}

