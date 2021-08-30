import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;


public class FileIconView extends FileView
{
   private FileFilter filter;
   private Icon icon;

   public FileIconView(FileFilter aFilter, Icon anIcon)
   {
      filter = aFilter;
      icon = anIcon;
   }
   @Override
   public Icon getIcon(File f) {
    if (!f.isDirectory() && filter.accept(f)) return icon;
    String extension = getExt(f);

    if (extension != null) {
        if (extension.equals("xxl")) {
            icon =  new ImageIcon("src/resources/xxl.png");
        } else if (extension.equals("pgm")) {
            icon =  new ImageIcon("src/resources/pgm.png");
        }
        
    }
    return icon;
}
   private String getExt(File f){
       return "xxl";
   }
       
   
}
