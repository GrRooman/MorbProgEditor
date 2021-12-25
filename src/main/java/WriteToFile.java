
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Date: 25.10.21
 * Time: 15:34
 */
class WriteToFile {
    static void saveDataInFile(String fileName, String strData){
        try (OutputStream out = new FileOutputStream(fileName); 
          Writer writer = new OutputStreamWriter(out, "Windows-1251")) {
          writer.write(strData);
    } catch(IOException e){
        }
    }
}
