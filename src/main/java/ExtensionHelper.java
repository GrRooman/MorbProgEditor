import java.io.File;

class ExtensionHelper {
    static String changeNameExtension(File file, String newNameExtension){
        String newName="";
        String s = file.getAbsolutePath();

        getFileExtension(s);

        int i = s.lastIndexOf('.');
        if (i > 0) {
            newName = s.substring(0, i)+"."+newNameExtension;
        }
        return newName;
    }

    static String changeNameExtensionPGMtoXXL(File fileName){
        String newName="";
        String s = fileName.getAbsolutePath();

        getFileExtension(s);

        int i = s.lastIndexOf('.');
        if (i > 0) {
            newName = s.substring(0, i)+".xxl";
        }
        return newName;
    }

    static String getFileExtension(String s){
        int i = s.lastIndexOf('.');
        if (i > 0) {
            return s.substring(i+1);
        }
        return null;
    }

    static String getFileExtension(File f){
        String name = f.getName();
        String extension="";

        int i = name.lastIndexOf('.');

        if (i > 0) {
            return name.substring(i+1);
        }
        return null;
    }

}
