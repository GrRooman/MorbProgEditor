import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
            return  rf.readFile(ExtensionHelper.changeNameExtensionPGMtoXXL(fileName));
        } else {
            return rf.readFile(fileName.getAbsolutePath());
        }
    }
    ArrayList<String> getDataFromAdditionalFiles(File file){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add( rf.readFile(ExtensionHelper.changeNameExtension(file, "lst")));
        arrayList.add( rf.readFile(ExtensionHelper.changeNameExtension(file, "inf")));
        return arrayList;
    }
}

