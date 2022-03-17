
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import textpane_editor.TextAreaWithStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.List;


class MainWindow extends JFrame implements ActionListener {

    private final JPanel rightPanel;
    private UserPreferences up;
    private JMenu mFile, mEdit, mHelp;
    private JMenuItem jmiOpen, jmiSave, jmiSaveAs, jmiClose, jmiExit,mUndo, mRedo, jmiSetting, jmiAbout;
    private JPanel jPanel, bottomPanel;
    private TextAreaWithStyles mainTextArea;
    private JTextArea leftBottomTextArea, rightBottomTextArea;
    private JButton commentToggleButton,
            commentBlockToggleButton,
            conditionToggleButton,
            conditionOnBlockToggleButton,
            deleteBlockButton;
    private JLabel imageLabel;
    private JToolBar jToolBar;
    //    private JLabel textInfoLabel;
    private JScrollPane scrollPane;
    private FileChooser fc;
    private ControlProgramFile controlProgramFile;
    private PrepareAndSaveData prepareAndSave;
    private AdditionalInformation additionalInformation;
    private static final Logger log = LoggerFactory.getLogger(MainWindow.class.getName());

    MainWindow() {
        setTitle("MorbProgEditor");

        up = new UserPreferences();
        fc = new FileChooser();

        Action openFile = new EditAction("Открыть", new ImageIcon(getClass().getResource("Folder-Open.png")),
                1,
                KeyEvent.VK_O, "Открыть файл (Ctrl+O)");
        Action saveFile = new EditAction("Сохранить", new ImageIcon(getClass().getResource("Save-Icon.png")),
                1,
                KeyEvent.VK_S, "Сохранить файл (Ctrl+S)");
        Action undo = new EditAction("Назад", new ImageIcon(getClass().getResource("Arrows-Undo.png")),
                KeyEvent.VK_Z,
                KeyEvent.VK_Z, "Действие назад (Ctrl+Z)");
        Action redo = new EditAction("Вперед", new ImageIcon(getClass().getResource("Arrows-Redo.png")),
                KeyEvent.VK_Y,
                KeyEvent.VK_Y, "Действие вперед (Ctrl+Y)");

        jToolBar = new JToolBar();
        jToolBar.add(openFile);
        jToolBar.addSeparator();
        jToolBar.add(saveFile);
        jToolBar.addSeparator();
        jToolBar.add(undo);
        jToolBar.add(redo);

        mFile = new JMenu("Файл");

        jmiSaveAs  =  new  JMenuItem("Сохранить как...");
        jmiClose  =  new  JMenuItem("Закрыть");
        jmiExit  =  new  JMenuItem("Выход");
        mFile.add(openFile);
        mFile.add(saveFile);
        mFile.add(jmiSaveAs);
        mFile.add(jmiClose);
        mFile.add(jmiExit);

        mEdit = new JMenu("Правка");
        mEdit.add(undo);
        mEdit.add(redo);

        mHelp = new JMenu("Помощь");
        jmiSetting  =  new  JMenuItem("Настройки");
        jmiAbout  =  new  JMenuItem("О программе");
        mHelp.add(jmiSetting);
        mHelp.add(jmiAbout);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mFile);
        menuBar.add(mEdit);
        menuBar.add(mHelp);

        jmiSaveAs.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiSetting.addActionListener(this);
        jmiAbout.addActionListener(this);


        jPanel = new JPanel();
        leftBottomTextArea = new JTextArea();
        rightBottomTextArea = new JTextArea();

        imageLabel = new JLabel("Место для вашей рекламы");
        imageLabel.setPreferredSize(new Dimension(220, 105));
        imageLabel.setMaximumSize(new Dimension(220, 105));

//        textInfoLabel = new JLabel("new data");
//        textInfoLabel.setPreferredSize(new Dimension(220, 105));
//        textInfoLabel.setMaximumSize(new Dimension(220, 105));

        commentToggleButton = new JButton("Закомментировать строки\';\'");
        commentBlockToggleButton = new JButton("Закомментировать блок\';\'");
        conditionToggleButton = new JButton("Условн. инстр. IF THEN");
        conditionOnBlockToggleButton = new JButton("Условн. инстр. блок IF THEN");
        deleteBlockButton = new JButton("Удалить выделенный блок");

        commentToggleButton.setActionCommand("comLine");
        commentBlockToggleButton.setActionCommand("comBlock");
        conditionToggleButton.setActionCommand("condiLines");
        conditionOnBlockToggleButton.setActionCommand("condiBlock");

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(176, 181, 176));

        rightPanel.add(imageLabel);
        rightPanel.add(commentToggleButton);
        rightPanel.add(commentBlockToggleButton);
        rightPanel.add(conditionToggleButton);
        rightPanel.add(conditionOnBlockToggleButton);
        rightPanel.add(deleteBlockButton);
//        rightPanel.add(textInfoLabel);
//
        //ОПРЕДЕЛЕНИЕ НИЖНЕГО ПОЛЯ . Boreder SOUTH
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        //  Указываем диспечер компановки
        jPanel.setLayout(new BorderLayout());

        jPanel.add(jToolBar, BorderLayout.NORTH);
        jPanel.add(rightPanel, BorderLayout.EAST);
        jPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(jPanel);
        setVisualStyleOfProgram(3);    //  Введите число от 0 до 3 для смены внешнего вида программы
        // Завершить работу программы, когда пользователь
        // закрывает приложение
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Установить начальные размеры фрейма
        setToPreferredSizeFrame();
        // Устанавливаем место появления окна программы
        centreWindow(this);

        commentBlockToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainTextArea.setCommentOfLines(ae);
            }
        });
        commentToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainTextArea.setCommentOfLines(ae);
            }
        });
        conditionToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainTextArea.setConditionAtCode(ae);
            }
        });
        conditionOnBlockToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainTextArea.setConditionAtCode(ae);
            }
        });
        deleteBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                mainTextArea.deleteBlockOfCode();
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DeleteTrash.deleteFile();
                up.setSizeFrame(MainWindow.this);
            }
        });
        setJMenuBar(menuBar);
        setVisible(true);
    }
    private void setAddTitle(String name){
        setTitle(name + "     | "  + "     [ Length: " + mainTextArea.getText().length()
                + "    Lines: " + (mainTextArea.getText() + "|").split("\n").length
                + "    Words: " + mainTextArea.getText().trim().split("\\s+").length + " ]");
    }

    private void setCenterTextArea(){
        jPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setToPreferredSizeFrame() {
        // Получить значение параметров и установить размеры формы
        int[] a = up.getSizeFrame();
        setSize(a[0], a[1]);
    }

    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    private void setTextToCommentButton(boolean b){
        if(b) commentToggleButton.setText("Раскомментировать строку");
        else commentToggleButton.setText("Закомментировать строку");
    }

    String getTextFromTextArea(){
        return mainTextArea.getStyleText();
    }
    private void showImageOfProgram(){
        imageLabel.setIcon( controlProgramFile.getImage());
    }
    private void createBottomLabels(JTextArea bottomTextArea, String text, Color color){
        bottomTextArea.setText(text);
        bottomTextArea.setEditable(false);
        bottomTextArea.setBackground(color);
        bottomTextArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        bottomTextArea.setFont(new Font("Tahoma", Font.BOLD, 11));
        bottomPanel.add(bottomTextArea);
    }
    void setInfoTextToBottomLabels(AdditionalInformation info){
        ArrayList<String> arrayList = info.getDataFromAdditionalFiles();
        List<Color> errorColor = info.getColorOfError();
        createBottomLabels(leftBottomTextArea, arrayList.get(0), errorColor.get(0));
        createBottomLabels(rightBottomTextArea, arrayList.get(1), errorColor.get(1));
    }
    private void setVisualStyleOfProgram(int n){
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[n].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void clearAndCloseTextPane(){
        mainTextArea.clearTextPane();
        mainTextArea = null;
        jPanel.remove(scrollPane);
        scrollPane = null;
        MainWindow.this.repaint();
    }
    void setProgram(ControlProgramFile c){
        controlProgramFile  = c;
        mainTextArea = new TextAreaWithStyles(controlProgramFile.getDataFromFile());
        scrollPane = new JScrollPane(mainTextArea);
        mainTextArea.loadText();
        showImageOfProgram();
        setCenterTextArea();
        setAddTitle(controlProgramFile.getFileName());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()){
            case "Сохранить как...":
                int result = fc.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION ){
                    File saveFile = fc.getSelectedFile();
                    prepareAndSave.workingWithFile(saveFile, this.getTextFromTextArea());

                    this.setInfoTextToBottomLabels(new AdditionalInformation(saveFile));

                    JOptionPane.showMessageDialog(this, "Файл '" + saveFile + " ) сохранен");
                    DeleteTrash.setFileName(saveFile);
                }
                break;
            case "Закрыть":
                ae.getActionCommand();
                break;
            case "Выход":
                System.exit(0);
                break;
            case "Настройки":
                Settings settings = new Settings(this);
                settings.showWindowSetting();
                break;
            case "О программе":
                InputStream inputStream = getClass().getResourceAsStream("About.txt");
                String s="";
                try (Scanner in =new Scanner(inputStream, "UTF-8"))
                {
                    while (in.hasNext())
                        s+=(in.nextLine() + "\n");
                }
                JOptionPane.showMessageDialog(null, s);
                break;
        }
    }


    class EditAction extends AbstractAction {
        public EditAction(String  name,  Icon  image,  int  mnem, int  accel,  String  tTip) {
            super(name,  image);
            putValue(ACCELERATOR_KEY,  KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, new  Integer(mnem));
            putValue(SHORT_DESCRIPTION,  tTip);
        }

        public void actionPerformed(ActionEvent event)  {
            switch(event.getActionCommand()){
                case "Открыть":
                    System.out.println(this.getClass().getName());
                    if (fc.openDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile =  fc.getSelectedFile();
                        prepareAndSave = new PrepareAndSaveData(selectedFile);
                        controlProgramFile = new ControlProgramFile(selectedFile);
                        /********************************/
                        MainWindow.this.setProgram(controlProgramFile);
                        /**********************************/
                        MainWindow.this.setInfoTextToBottomLabels(new AdditionalInformation(selectedFile));
                        DeleteTrash.setFileName(selectedFile);
                    }
            }

        }
    }
}
