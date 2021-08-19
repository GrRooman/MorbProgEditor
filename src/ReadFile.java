import java.io.*;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
public class ReadFile {
    NativeFileConverter nfc;
    BufferedInputStream br;
    String s="";

    ReadFile(String f){
        nfc = new NativeFileConverter(f);
        getDataFromFile(f);
    }

    String getDataFromFile(String f) {
        try{
             br = new BufferedInputStream(new FileInputStream(f));

        }catch(FileNotFoundException e){};
        try{
            int i;
            while((i = br.read()) != -1) {
                s += (char)i;
            }
        }catch (IOException e) {};
        System.out.print(s);
        return s;
    }
}

