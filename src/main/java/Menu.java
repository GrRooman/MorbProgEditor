
import textpane_editor.TextAreaWithStyles;

import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.*;


class Menu extends JMenuBar {
    private FileChooser fc;
    private ControlProgramFile controlProgramFile;
    private PrepareAndSaveData prepareAndSave;
    private AdditionalInformation additionalInformation;

    Menu(MainWindow mw){


        fc = new FileChooser();

        JMenu jmF = new JMenu("Файл");
        JMenu jmE = new JMenu("Правка");
        JMenu jmH = new JMenu("Помощь");

        JMenuItem  jmiOpen  =  new  JMenuItem("Открыть");
        JMenuItem  jmiSave  =  new  JMenuItem("Сохранить");
        JMenuItem  jmiSaveAs  =  new  JMenuItem("Сохранить как...");
        JMenuItem  jmiClose  =  new  JMenuItem("Закрыть");
        JMenuItem  jmiExit  =  new  JMenuItem("Выход");

        jmiOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        jmiSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));

        jmF.add(jmiOpen);
        jmF.add(jmiSave);
        jmF.add(jmiSaveAs);
        jmF.add(jmiClose);
        jmF.add(jmiExit);

        JMenuItem  jmiSetting  =  new  JMenuItem("Настройки");
        JMenuItem  jmiAbout  =  new  JMenuItem("О программе");

        jmH.add(jmiSetting);
        jmH.add(jmiAbout);

//        add(jmF);
//        add(jmE);
//        add(jmH);
        
        jmiOpen.addActionListener((ActionEvent ae) -> {

        });
        
        jmiSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                prepareAndSave.workingWithFile(mw.getTextFromTextArea());
            }
        });






    }
}
