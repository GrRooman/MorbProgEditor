import java.io.*;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
public class ReadFile {
    NativeFileConverter nfc;
    FileChooser jfc;
    File file;
    BufferedReader br;
    String s;

    ReadFile(){
        jfc = new FileChooser();
        //file = jfc.getf();
        nfc = new NativeFileConverter(jfc.getf());
        getDataFromFile(file);
    }

    String getDataFromFile(File f) {
        try{
             br = new BufferedReader(new FileReader(f));

        }catch(FileNotFoundException e){};
        try{
            while( (s = br.readLine()) != null) {
            s+=s;
            }
        }catch (IOException e) {};
        return s;
    }
}

