import java.io.*;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class ReadFile {
    NativeFileConverter nfc;
    BufferedInputStream br;
    String s="";
    File fileName;

    ReadFile(File f){
       nfc = new NativeFileConverter(f);
    }

    String getDataFromFile(File fileName) {
        try{
             br = new BufferedInputStream(new FileInputStream(fileName));

        }catch(FileNotFoundException e){}
        try{
            int i;
            while((i = br.read()) != -1) {
                s += (char)i;
            }
        }catch (IOException e) {}
        return s;
    }
}

