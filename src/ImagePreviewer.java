import java.awt.*;
import java.beans.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class ImagePreviewer extends JLabel
{
Image image;
   public ImagePreviewer(JFileChooser chooser)
   {
      setPreferredSize(new Dimension(220, 105));       // Устанавливает размеры окна превью
      setBorder(BorderFactory.createEtchedBorder());  // Устанавливает рамку для окна превью

      chooser.addPropertyChangeListener(new PropertyChangeListener()
         {
            public void propertyChange(PropertyChangeEvent event)
            {
               if (event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
               {
                  
                  File f = (File) event.getNewValue();
                  if (f == null)
                  {
                     setIcon(null);
                     return;
                  }

                   try {
                       image = ImageIO.read(f);
                   } catch (IOException ex) {
                       
                   }
                  ImageIcon icon = new ImageIcon(image);

                  // Уменьшение размера превью если оно слишком большое
                  if (icon.getIconWidth() > getWidth()) icon = new ImageIcon(icon.getImage()
                        .getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));

                  setIcon(icon);
               }
            }
         });
   }
}
