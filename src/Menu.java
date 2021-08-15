
import java.awt.event.*;
import java.io.File;
import javax.swing.*;


class Menu extends JMenuBar {
    NativeFileConverter nfc;
    Menu(){

        JMenu jmF = new JMenu("File");
        JMenu jmE = new JMenu("Edit");
        JMenu jmH = new JMenu("Help");

        JMenuItem  jmiOpen  =  new  JMenuItem("Открыть");
        JMenuItem  jmiSave  =  new  JMenuItem("Пока просто тест **Сохранить");
        JMenuItem  jmiClose  =  new  JMenuItem("Закрыть");
        JMenuItem  jmiExit  =  new  JMenuItem("Выход");

        jmF.add(jmiOpen);
        jmF.add(jmiSave);
        jmF.add(jmiClose);
        jmF.add(jmiExit);

        add(jmF);
        add(jmE);
        add(jmH);
        
        jmiOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                FileChooser jfc = new FileChooser();
                File s = jfc.getf().getSelectedFile();
                nfc = new NativeFileConverter(s);
                
            }
        });
        
        jmiSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               
              
            }
        });
        
        jmiExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });
    
    

    }
}
