import java.io.File;

/**
 * User: Grooman
 * Date: 24.09.21
 * Time: 13:45
 */
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
        return s.substring(i+1);
    }

}
