
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import textpane_editor.TextAreaWithStyles;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
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
    private JMenuItem mfSaveAs, mfClose, meCut, meCopy, mePaste, mhExit, mhAbout;
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
    private Action openFile, saveFile, undo, redo, searchInFile, settings;
    private static final Logger log = LoggerFactory.getLogger(MainWindow.class.getName());

    MainWindow() {
        setTitle("MorbProgEditor");

        up = new UserPreferences();
        fc = new FileChooser();

        openFile = new EditAction("Открыть", new ImageIcon(getClass().getResource("Folder-Open.png")),
                0x41e,  // не работает
                KeyEvent.VK_O, "Открыть файл (Ctrl+O)",
                true);
        saveFile = new EditAction("Сохранить", new ImageIcon(getClass().getResource("Save-Icon.png")),
                0x411,  // не работает
                KeyEvent.VK_S, "Сохранить файл (Ctrl+S)",
                false);
        undo = new EditAction("Назад", new ImageIcon(getClass().getResource("Arrows-Undo.png")),
                KeyEvent.VK_U,  // не работает
                KeyEvent.VK_Z, "Действие назад (Ctrl+Z)",
                false);
        redo = new EditAction("Вперед", new ImageIcon(getClass().getResource("Arrows-Redo.png")),
                KeyEvent.VK_R,  // не работает
                KeyEvent.VK_Y, "Действие вперед (Ctrl+Y)",
                false);
        searchInFile = new EditAction("Поиск", new ImageIcon(getClass().getResource("Search-Icon.png")),
                KeyEvent.VK_7,
                KeyEvent.VK_F, "Поиск (Ctrl+F)",
                false);
        settings = new EditAction("Настройки", new ImageIcon(getClass().getResource("Settings-Icon.png")),
                KeyEvent.VK_R,  // не работает
                KeyEvent.VK_M, "Настройки программ (Ctrl+M)",
                true);


        jToolBar = new JToolBar();
        jToolBar.add(openFile);
        jToolBar.addSeparator();
        jToolBar.add(saveFile);
        jToolBar.addSeparator();
        jToolBar.add(undo);
        jToolBar.add(redo);
        jToolBar.addSeparator();
        jToolBar.add(searchInFile);
        jToolBar.addSeparator();
        jToolBar.add(settings);

        mFile = new JMenu("Файл");

        mfSaveAs = new JMenuItem("Сохранить как...");
        mfSaveAs.setEnabled(false);
        mfClose = new JMenuItem("Закрыть файл", new ImageIcon(getClass().getResource("Erase-Icon.png")));
        mfClose.setEnabled(false);
        mhExit = new JMenuItem("Выход", new ImageIcon(getClass().getResource("Exit-Icon.png")));
        mFile.add(openFile);
        mFile.add(saveFile);
        mFile.add(mfSaveAs);
        mFile.add(mfClose);
        mFile.add(mhExit);

        mEdit = new JMenu("Правка");
        mEdit.add(undo);
        mEdit.add(redo);
        mEdit.addSeparator();
        meCut = new JMenuItem(new DefaultEditorKit.CutAction());
        meCut.setText("Вырезать");
        meCut.setIcon(new ImageIcon(getClass().getResource("Cut-Icon.png")));
        meCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        meCut.setEnabled(false);

        meCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
        meCopy.setText("Копировать");
        meCopy.setIcon(new ImageIcon(getClass().getResource("Copy-Icon.png")));
        meCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        meCopy.setEnabled(false);

        mePaste = new JMenuItem(new DefaultEditorKit.PasteAction());
        mePaste.setText("Вставить");
        mePaste.setIcon(new ImageIcon(getClass().getResource("Paste-Icon.png")));
        mePaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        mePaste.setEnabled(false);

//        meSearch = new JMenuItem("Поиск");
//        meSearch.setEnabled(false);

        mEdit.add(meCut);
        mEdit.add(meCopy);
        mEdit.add(mePaste);
        mEdit.addSeparator();
        mEdit.add(searchInFile);


        mHelp = new JMenu("Помощь");
        mhAbout = new JMenuItem("О программе");
        mHelp.add(settings);
        mHelp.add(mhAbout);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mFile);
        menuBar.add(mEdit);
        menuBar.add(mHelp);

        mfSaveAs.addActionListener(this);
        mfClose.addActionListener(this);
        meCut.addActionListener(this);
        meCopy.addActionListener(this);
        mePaste.addActionListener(this);
        mhExit.addActionListener(this);
        mhAbout.addActionListener(this);


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
        conditionToggleButton.setToolTipText("Оборачивает строку или выделенные строки IF THEN ... IF");
        conditionOnBlockToggleButton = new JButton("Условн. инстр. блок IF THEN");
        conditionOnBlockToggleButton.setToolTipText("Оборачивает блок кода IF THEN ... IF");
        deleteBlockButton = new JButton("Резерв");

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

    private void setAddTitle(String name) {
        setTitle(name + "     | " + "     [ Length: " + mainTextArea.getText().length()
                + "    Lines: " + (mainTextArea.getText() + "|").split("\n").length
                + "    Words: " + mainTextArea.getText().trim().split("\\s+").length + " ]");
    }

    private void setCenterTextArea() {
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

    private void setTextToCommentButton(boolean b) {
        if (b) commentToggleButton.setText("Раскомментировать строку");
        else commentToggleButton.setText("Закомментировать строку");
    }

    String getTextFromTextArea() {
        return mainTextArea.getStyleText();
    }

    private void showImageOfProgram() {
        imageLabel.setIcon(controlProgramFile.getImage());
    }

    private void createBottomLabels(JTextArea bottomTextArea, String text, Color color) {
        bottomTextArea.setText(text);
        bottomTextArea.setEditable(false);
        bottomTextArea.setBackground(color);
        bottomTextArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        bottomTextArea.setFont(new Font("Tahoma", Font.BOLD, 11));
        bottomPanel.add(bottomTextArea);
    }

    void setInfoTextToBottomLabels(AdditionalInformation info) {
        ArrayList<String> arrayList = info.getDataFromAdditionalFiles();
        List<Color> errorColor = info.getColorOfError();
        createBottomLabels(leftBottomTextArea, arrayList.get(0), errorColor.get(0));
        createBottomLabels(rightBottomTextArea, arrayList.get(1), errorColor.get(1));
    }

    private void setVisualStyleOfProgram(int n) {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[n].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setVisibleMenuItem(boolean visible) {
        saveFile.setEnabled(visible);
        mfSaveAs.setEnabled(visible);
        mfClose.setEnabled(visible);
        undo.setEnabled(visible);
        redo.setEnabled(visible);
        meCut.setEnabled(visible);
        meCopy.setEnabled(visible);
        mePaste.setEnabled(visible);
        searchInFile.setEnabled(visible);
    }

    void clearAndCloseTextPane() {
        mainTextArea.clearTextPane();
        mainTextArea = null;
        jPanel.remove(scrollPane);
        scrollPane = null;
        MainWindow.this.repaint();
        setVisibleMenuItem(false);
    }

    void setProgram(ControlProgramFile c) {
        controlProgramFile = c;
        mainTextArea = new TextAreaWithStyles(controlProgramFile.getDataFromFile());    //getDataFromFile  читает файл в список, getStringDataFromFile читает в строку
        scrollPane = new JScrollPane(mainTextArea);
        mainTextArea.loadText();
        showImageOfProgram();
        setCenterTextArea();
        setAddTitle(controlProgramFile.getFileName());
        setVisibleMenuItem(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Сохранить как...":
                int result = fc.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File saveFile = fc.getSelectedFile();
                    prepareAndSave.workingWithFile(saveFile, this.getTextFromTextArea());

                    this.setInfoTextToBottomLabels(new AdditionalInformation(saveFile));

                    JOptionPane.showMessageDialog(this, "Файл '" + saveFile + " ) сохранен");
                    DeleteTrash.setFileName(saveFile);
                }
                break;
            case "Закрыть файл":
                clearAndCloseTextPane();
                break;
            case "Вырезать":
                new DefaultEditorKit.CutAction();
                break;
            case "Копировать":
                new DefaultEditorKit.CopyAction();
                break;
            case "Вставить":
                new DefaultEditorKit.PasteAction();
                break;
            case "Выход":
                DeleteTrash.deleteFile();
                System.exit(0);         //Возможно надо сменить на dispose();
                break;
            case "О программе":
                InputStream inputStream = getClass().getResourceAsStream("About.txt");
                String s = "";
                try (Scanner in = new Scanner(inputStream, "UTF-8")) {
                    while (in.hasNext())
                        s += (in.nextLine() + "\n");
                }
                JOptionPane.showMessageDialog(this, s);
                break;
            default:
                System.out.println(ae.getActionCommand());

        }
    }


    class EditAction extends AbstractAction {
        public EditAction(String name, Icon image, int mnem, int accel, String tTip, boolean visible) {
            super(name, image);
            putValue(ACTION_COMMAND_KEY, name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_DOWN_MASK));
            putValue(MNEMONIC_KEY, new Integer(mnem));
            putValue(SHORT_DESCRIPTION, tTip);
            setEnabled(visible);
        }

        public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "Открыть":
                    if (fc.openDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fc.getSelectedFile();
                        prepareAndSave = new PrepareAndSaveData(selectedFile);
                        controlProgramFile = new ControlProgramFile(selectedFile);

                        MainWindow.this.setProgram(controlProgramFile);

                        MainWindow.this.setInfoTextToBottomLabels(new AdditionalInformation(selectedFile));
                        DeleteTrash.setFileName(selectedFile);
                    }
                    break;
                case "Назад":
                    mainTextArea.undo();
                    break;
                case "Вперед":
                    mainTextArea.redo();
                    break;
                case "Сохранить":
                    prepareAndSave.workingWithFile(MainWindow.this.getTextFromTextArea());
                    break;
                case "Поиск":
                    mainTextArea.fin();
                    break;
                case "Настройки":
                    Settings settings = new Settings(MainWindow.this);
                    settings.showWindowSetting();
                    break;
            }
        }
    }
}
