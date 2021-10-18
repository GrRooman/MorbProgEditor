

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




class WriteToFile {
    FileOutputStream fos;
    NativeFileConverter nfc;
    File openedFile;

    WriteToFile(File selectedFile){
         openedFile = selectedFile;

        nfc = new NativeFileConverter();
    }

    void workingWithFile(String strData){
        if (ExtensionHelper.getFileExtension(openedFile.getAbsolutePath()).equalsIgnoreCase("xxl")) {
            saveDataInFile(openedFile.getAbsolutePath(), strData);
        }
        if (ExtensionHelper.getFileExtension(openedFile.getAbsolutePath()).equalsIgnoreCase("pgm")) {
            String fileWithXXLExtension = ExtensionHelper.changeNameExtensionPGMtoXXL(openedFile);
            saveDataInFile(fileWithXXLExtension, strData);
            nfc.convertXXL_to_PGM(fileWithXXLExtension, openedFile.getAbsolutePath());
        }
    }

    void workingWithFile(File newFileName, String strData){
        if(ExtensionHelper.getFileExtension(newFileName.getAbsolutePath()).equalsIgnoreCase("xxl")){
            saveDataInFile(newFileName.getAbsolutePath(), strData);
        }
        if(ExtensionHelper.getFileExtension(newFileName.getAbsolutePath()).equalsIgnoreCase("pgm"))
            try {
                File tempFile = File.createTempFile(newFileName.getName(), ".xxl", newFileName.getParentFile());
                saveDataInFile(tempFile.getAbsolutePath(), strData);
                nfc.convertXXL_to_PGM(tempFile.getAbsolutePath(), newFileName.getAbsolutePath());
    //            temp.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    void saveDataInFile(String fname, String strdata){
        try{
            fos = new FileOutputStream(fname);
            byte[] b = strdata.getBytes();
            fos.write(b); 
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
