
import java.awt.event.*;
import java.io.File;
import javax.swing.*;


class Menu extends JMenuBar {
    FileChooser fc;
    MainWindow mw;
    ReadFile rf;
    WriteToFile wtf;
    File saveFile;

    Menu(MainWindow jf){
        mw=jf;
        rf = new ReadFile();

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

        JMenuItem  jmiAbout  =  new  JMenuItem("О программе");

        jmH.add(jmiAbout);

        add(jmF);
        add(jmE);
        add(jmH);
        
        jmiOpen.addActionListener((ActionEvent ae) -> {
            int result = fc.showOpenDialog(mw);
            if (result == JFileChooser.APPROVE_OPTION) {
                wtf = new WriteToFile(fc.getSelectedFile());
                mw.setTextInTextArea(rf.getDataFromFile(fc.getSelectedFile()));
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
    
    

    }
}
