import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    List<String> readFile(String fileName){
        List<String> lis = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Windows-1251"))){
            String s = "";
            while((s = reader.readLine()) != null){
                lis.add(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return lis;
    }
}

