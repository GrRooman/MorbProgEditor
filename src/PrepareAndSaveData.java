

import java.io.File;
import java.io.IOException;




class PrepareAndSaveData {
    NativeFileConverter nfc;
    File openedFile;

    PrepareAndSaveData(File selectedFile){
         openedFile = selectedFile;

        nfc = new NativeFileConverter();
    }

    void workingWithFile(String strData){
        //Если файл имеет раширение 'XXL' , данные записываются в файл с расширением XXL. 
        if (ExtensionHelper.getFileExtension(openedFile.getAbsolutePath()).equalsIgnoreCase("xxl")) {
            WriteToFile.saveDataInFile(openedFile.getAbsolutePath(), strData);
        }
        //Если файл имеет раширение 'PGM' , данные записываются в файл с расширением PGM.
        if (ExtensionHelper.getFileExtension(openedFile.getAbsolutePath()).equalsIgnoreCase("pgm")) {
            String fileWithXXLExtension = ExtensionHelper.changeNameExtensionPGMtoXXL(openedFile);
            WriteToFile.saveDataInFile(fileWithXXLExtension, strData);
            nfc.convertXXL_to_PGM(fileWithXXLExtension, openedFile.getAbsolutePath());
        }
    }

    void workingWithFile(File newFileName, String strData){
        if(ExtensionHelper.getFileExtension(newFileName.getAbsolutePath()).equalsIgnoreCase("xxl")){
            WriteToFile.saveDataInFile(newFileName.getAbsolutePath(), strData);
        }
        if(ExtensionHelper.getFileExtension(newFileName.getAbsolutePath()).equalsIgnoreCase("pgm"))
            try {
                File tempFile = File.createTempFile(newFileName.getName(), ".xxl", newFileName.getParentFile());
                WriteToFile.saveDataInFile(tempFile.getAbsolutePath(), strData);
                nfc.convertXXL_to_PGM(tempFile.getAbsolutePath(), newFileName.getAbsolutePath());
                tempFile.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
