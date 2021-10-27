import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Date: 25.10.21
 * Time: 15:34
 */
class WriteToFile {
    static void saveDataInFile(String fname, String strdata){
        try(FileOutputStream fos = new FileOutputStream(fname)){
            byte[] b = strdata.getBytes();
            fos.write(b);

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
