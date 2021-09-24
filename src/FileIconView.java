import java.io.*;
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
    String extension = getExtensionFile(f);
    
    if (f.isDirectory() && filter.accept(f)) return null;

    if (extension != null) {
        if (extension.equals("xxl")) {
            icon =  new ImageIcon("src/resources/xxl.png");
        } else if (extension.equals("pgm")) {
            icon =  new ImageIcon("src/resources/pgm.png");
        } else {
            return null;
        }
    }
    return icon;
}
   
    private String getExtensionFile(File f){
        
        String name = f.getName();
        String extension="";
        
        int i = name.lastIndexOf('.');
        
        if (i > 0) {
          extension = name.substring(i+1);
        }
       return extension;
   }
}
