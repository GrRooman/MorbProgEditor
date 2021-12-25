import java.io.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;


class FileIconView extends FileView
{
   private FileFilter filter;
   private Icon icon;

   public FileIconView(FileFilter aFilter)
   {
      filter = aFilter;
   }
   @Override
   public Icon getIcon(File f) {
    String extension = ExtensionHelper.getFileExtension(f);
    
    if (f.isDirectory() && filter.accept(f)) return null;

    if (extension != null) {
        if (extension.equals("xxl")) {
            icon =  new ImageIcon(getImagePath("xxl.png"));
        } else if (extension.equals("pgm")) {
            icon =  new ImageIcon(getImagePath("pgm.png"));
        } else {
            return null;
        }
    }
    return icon;
}
    private URL getImagePath(String name){
        URL url = getClass().getResource(name);
        return url;
    }
}
