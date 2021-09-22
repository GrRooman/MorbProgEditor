import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }

    String getDataFromFile(File fileName){
        Path p = Paths.get(changeNameExtension(fileName));
        String s = null;
        try {
            s = new String(Files.readAllBytes(p));
        } catch (IOException e) {

        }
/*        try{
             br = new BufferedInputStream(new FileInputStream(new File(changeNameExtension(fileName))));

        }catch(FileNotFoundException e){}
        try{
            int i;
            while((i = br.read()) != -1) {
                s += (char)i;
            }
        }catch (IOException e) {}  */
        return s;
    }

    private String changeNameExtension(File fileName){
        String newName="";
        String s = fileName.getAbsolutePath();
        int i = s.lastIndexOf('.');
        if (i > 0) {
            newName = s.substring(0, i)+".xxl";
        }
        return newName;
    }
}

