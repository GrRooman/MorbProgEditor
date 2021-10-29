
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;


class Menu extends JMenuBar {
    FileChooser fc;
    MainWindow mw;
    PrepareFileForReading pfr;
    PrepareAndSaveData wtf;
    File saveFile;

    Menu(MainWindow jf){
        mw=jf;
        pfr = new PrepareFileForReading();

        fc = new FileChooser();

        JMenu jmF = new JMenu("Файл");
        JMenu jmE = new JMenu("Правка");
        JMenu jmH = new JMenu("Помощь");

        JMenuItem  jmiOpen  =  new  JMenuItem("Открыть");
        JMenuItem  jmiSave  =  new  JMenuItem("Сохранить");
        JMenuItem  jmiSaveAs  =  new  JMenuItem("Сохранить как...");
        JMenuItem  jmiClose  =  new  JMenuItem("Закрыть");
        JMenuItem  jmiExit  =  new  JMenuItem("Выход");

        jmF.add(jmiOpen);
        jmF.add(jmiSave);
        jmF.add(jmiSaveAs);
        jmF.add(jmiClose);
        jmF.add(jmiExit);

        JMenuItem  jmiSetting  =  new  JMenuItem("Настройки");
        JMenuItem  jmiAbout  =  new  JMenuItem("О программе");

        jmH.add(jmiSetting);
        jmH.add(jmiAbout);

        add(jmF);
        add(jmE);
        add(jmH);
        
        jmiOpen.addActionListener((ActionEvent ae) -> {
            int result = fc.showOpenDialog(mw);
            if (result == JFileChooser.APPROVE_OPTION) {
                wtf = new PrepareAndSaveData(fc.getSelectedFile());
                mw.setTextInTextArea(pfr.getDataFromFile(fc.getSelectedFile()));
                mw.setTextInBottomLabels(pfr.getDataFromAdditionalFiles(fc.getSelectedFile()));
            }
        });
        
        jmiSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                wtf.workingWithFile(mw.getTextFromTextArea());
            }
        });

        jmiSaveAs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int result = fc.showSaveDialog(mw);
                 if (result == JFileChooser.APPROVE_OPTION ){
                     saveFile = fc.getSelectedFile();
                     wtf.workingWithFile(saveFile, mw.getTextFromTextArea());
                    JOptionPane.showMessageDialog(mw,"Файл '" + fc.getSelectedFile() + " ) сохранен");
                 }
            }
        });

        jmiClose.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                mw.setTextInTextArea("");
            }
        });
        
        jmiExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });

        jmiSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings settings = new Settings();
                settings.showWindowSetting();
            }
        });

        jmiAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputStream inputStream = getClass().getResourceAsStream("resources/About.txt");
                String s="";
                try (Scanner in =new Scanner(inputStream, "UTF-8"))
                {
                    while (in.hasNext())
                        s+=(in.nextLine() + "\n");
                     }
                JOptionPane.showMessageDialog(null, s);
            }
        });

    }
}
