
import java.awt.event.*;
import java.io.File;
import javax.swing.*;


class Menu extends JMenuBar {
    FileChooser fc = new FileChooser();
    MainWindow mw;
    ReadFile rf;
    WriteToFile wtf;

    Menu(MainWindow jf){
        mw=jf;

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
        
        jmiOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int result = fc.showOpenDialog(mw);
                    if (result == JFileChooser.APPROVE_OPTION){
                        String name = fc.getSelectedFile().getPath();
                        rf = new ReadFile(name);
                        mw.setTextInTextArea(rf.getDataFromFile(name));
                    }
            }
        });
        
        jmiSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

              
            }
        });

        jmiSaveAs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

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
