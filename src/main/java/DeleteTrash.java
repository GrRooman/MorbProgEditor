import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class DeleteTrash {
    private static ArrayList<String> arrayList = new ArrayList<>();
    private static File pathToFile;

    static void setFileName(File fileName){
        pathToFile = fileName;
        pushExistFiles(pathToFile);
    }

    private static void pushExistFiles(File fileName){
        if (ExtensionHelper.getFileExtension(fileName).equalsIgnoreCase("PGM")){
            if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "xxl")))){
                arrayList.add(ExtensionHelper.changeNameExtension(fileName, "xxl"));
            }
            if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "inf")))){
                arrayList.add(ExtensionHelper.changeNameExtension(fileName, "inf"));
            }
            if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "lst")))){
                arrayList.add(ExtensionHelper.changeNameExtension(fileName, "lst"));
            }
            if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "bmp")))){
                arrayList.add(ExtensionHelper.changeNameExtension(fileName, "bmp"));
            }
        }
       if (ExtensionHelper.getFileExtension(fileName).equalsIgnoreCase("XXL")){

           if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "inf")))){
               arrayList.add(ExtensionHelper.changeNameExtension(fileName, "inf"));
           }
           if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "lst")))){
               arrayList.add(ExtensionHelper.changeNameExtension(fileName, "lst"));
           }
           if (Files.exists(Paths.get(ExtensionHelper.changeNameExtension(fileName, "bmp")))){
               arrayList.add(ExtensionHelper.changeNameExtension(fileName, "bmp"));
           }
       }
    }
    static void deleteFile(){
        if(!arrayList.isEmpty()){
            for(String s : arrayList){
                try {
                    Files.delete(Paths.get(s));
                } catch (IOException e) {
                }
            }
        }
    }

}
