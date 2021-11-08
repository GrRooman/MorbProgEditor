
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Date: 08.11.21
 * Time: 13:57
 */
class PreparePicture {
    File namePicture;

    PreparePicture(File firstSelectFile){
         namePicture = firstSelectFile;
    }

    void getRenamedPicture(File newName) {
        if(Files.exists(Paths.get(ExtensionHelper.changeNameExtension(namePicture, "bmp")))){
            try {
                Files.move(Paths.get(ExtensionHelper.changeNameExtension(namePicture, "bmp")),
                        Paths.get(ExtensionHelper.changeNameExtension(newName, "bmp")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {return ;}
    }
}
