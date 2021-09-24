import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    ReadFile(){
          nfc = new NativeFileConverter();
    }

    String getDataFromFile(File fileName){
        if(ExtensionHelper.getFileExtension(fileName.getName()).equals("pgm")) {
            nfc.convertPGM_to_XXL(fileName);
            return  readFile(ExtensionHelper.changeNameExtensionPGMtoXXL(fileName));
        } else {
            return readFile(fileName.getAbsolutePath());

        }
/*        try{
             br = new BufferedInputStream(new FileInputStream(new File(changeNameExtensionPGMtoXXL(fileName))));

        }catch(FileNotFoundException e){}
        try{
            int i;
            while((i = br.read()) != -1) {
                s += (char)i;
            }
        }catch (IOException e) {}
        return s;      */
    }
    private String readFile(String fileName){
        Path p = Paths.get(fileName);
        String s = null;
        try {
            s = new String(Files.readAllBytes(p));
        } catch (IOException e) {
            System.out.println(e);}
        return s;
    }


}

