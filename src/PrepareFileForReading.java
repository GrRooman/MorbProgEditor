import java.io.File;
import java.util.List;

/**
 * User: Grooman
 * Date: 17.08.21
 * Time: 14:04
 */
class PrepareFileForReading {
    ReadFile rf;
    NativeFileConverter nfc;

    PrepareFileForReading(){
        nfc = new NativeFileConverter();
        rf = new ReadFile();
    }

    String getDataFromFile(File fileName){
        if(ExtensionHelper.getFileExtension(fileName.getName()).equalsIgnoreCase("pgm")) {
            nfc.convertPGM_to_XXL(fileName);
            return  listIntoText(rf.readFile(ExtensionHelper.changeNameExtensionPGMtoXXL(fileName)));
        } else {
            return listIntoText(rf.readFile(fileName.getAbsolutePath()));
        }
    }
    String listIntoText(List<String> lis){
        String s="";
        s = lis.stream().map(q -> q+"\n").reduce(s, String::concat);
        return s;
    }
}

