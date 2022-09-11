
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
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


public class MainWindow extends JFrame implements ActionListener {

    private UserPreferences up;
    private JMenu mFile, mEdit, mHelp;
    private JMenuItem mfSaveAs, mfClose, meCut, meCopy, mePaste, mhExit, mhAbout;
    private JPanel mainPanel;
    private TextAreaWithStyles mainTextArea;
    private JTextArea leftBottomTextArea, rightBottomTextArea;
    private JButton commentToggleButton,
            commentBlockToggleButton,
            conditionToggleButton,
            conditionOnBlockToggleButton,
            deleteBlockButton;
    private JLabel imageLabel;
    private JToolBar jToolBar;
    private JSplitPane main;
    private JPanel topPanel;
    private JSeparator sepa2;
    private JButton button4;
    private JProgressBar progressBar1;
    private JScrollPane mainScrollPane;
    private JSplitPane bottomInfoPanel;
    private JLabel leftInfoAboutGProgram;
    private JLabel rightInfoAboutGProgram;
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


        leftBottomTextArea = new JTextArea();
        rightBottomTextArea = new JTextArea();


        commentToggleButton.setActionCommand("commLine");
        commentBlockToggleButton.setActionCommand("commBlock");
        conditionToggleButton.setActionCommand("condiLines");
        conditionOnBlockToggleButton.setActionCommand("condiBlock");

//        rightPanel = new JPanel();
//        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); //   new BoxLayout(rightPanel, BoxLayout.Y_AXIS)
//        rightPanel.setBackground(new Color(176, 181, 176));
//
//        rightPanel.add(imageLabel);
//        rightPanel.add(commentToggleButton);
//        rightPanel.add(commentBlockToggleButton);
//        rightPanel.add(conditionToggleButton);
//        rightPanel.add(conditionOnBlockToggleButton);
//        rightPanel.add(deleteBlockButton);
//        rightPanel.add(textInfoLabel);
//
        //ОПРЕДЕЛЕНИЕ НИЖНЕГО ПОЛЯ . Boreder SOUTH
//        bottomPanel = new JPanel();
//        bottomPanel.setLayout(new GridLayout(1, 2));

        //  Указываем диспечер компановки
//        mainPanel.setLayout(new BorderLayout());
//
//        mainPanel.add(jToolBar, BorderLayout.NORTH);
//        mainPanel.add(rightPanel, BorderLayout.EAST);
//        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
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
        commentToggleButton.addActionListener(ae -> {
            mainTextArea.setCommentOfLines(ae);
        });
        conditionToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainTextArea.setConditionAtCode(ae);
            }
        });
        conditionOnBlockToggleButton.addActionListener(ae -> {
            mainTextArea.setConditionAtCode(ae);
        });
//        deleteBlockButton.addActionListener(e -> {
//            mainTextArea.getListText();
//        });

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

    private void setInfoAboutGProgram() {
        leftInfoAboutGProgram.setText("Length: " + mainTextArea.getText().length()
                + "    Lines: " + (mainTextArea.getText() + "|").split("\n").length);
    }

    private void setCenterTextArea() {
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainScrollPane.add(mainTextArea);
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
    }

    void setInfoTextToBottomLabels(AdditionalInformation info) {
        ArrayList<String> arrayList = info.getDataFromAdditionalFiles();
        List<Color> errorColor = info.getColorOfError();

        createBottomLabels(leftBottomTextArea, arrayList.get(0), errorColor.get(0));
        bottomInfoPanel.setLeftComponent(leftBottomTextArea);
        createBottomLabels(rightBottomTextArea, arrayList.get(1), errorColor.get(1));
        bottomInfoPanel.setRightComponent(rightBottomTextArea);

    }

    private void setVisualStyleOfProgram(int n) {
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[n].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
            pack();
        } catch (Exception e) {
            log.error("", e);
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
        mainPanel.remove(mainScrollPane);
        mainScrollPane = null;
        MainWindow.this.repaint();
        setVisibleMenuItem(false);
    }

    void setProgram(ControlProgramFile c) {
        controlProgramFile = c;
        mainTextArea = new TextAreaWithStyles(controlProgramFile.getDataFromFile());    //getDataFromFile  читает файл в список, getStringDataFromFile читает в строку
        mainScrollPane.setViewportView(mainTextArea);
        mainTextArea.loadText();
        showImageOfProgram();
        setInfoAboutGProgram();
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setMinimumSize(new Dimension(500, 400));
        mainPanel.setPreferredSize(new Dimension(800, 500));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5), null));
        jToolBar = new JToolBar();
        jToolBar.setBorderPainted(false);
        mainPanel.add(jToolBar, BorderLayout.NORTH);
        jToolBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        main = new JSplitPane();
        main.setAlignmentX(0.0f);
        main.setAlignmentY(0.0f);
        main.setContinuousLayout(false);
        main.setDividerLocation(444);
        main.setDividerSize(10);
        main.setLastDividerLocation(-1);
        main.setOrientation(0);
        main.setResizeWeight(0.8);
        mainPanel.add(main, BorderLayout.CENTER);
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        topPanel.setMinimumSize(new Dimension(-1, -1));
        topPanel.setPreferredSize(new Dimension(-1, -1));
        main.setLeftComponent(topPanel);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setAlignmentY(0.0f);
        panel1.setPreferredSize(new Dimension(220, 1000));
        topPanel.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(230, -1), null, new Dimension(230, -1), 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(0);
        imageLabel.setMinimumSize(new Dimension(220, 105));
        imageLabel.setPreferredSize(new Dimension(220, 105));
        imageLabel.setText("");
        imageLabel.setVerticalAlignment(0);
        imageLabel.setVerticalTextPosition(0);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        panel1.add(imageLabel, gbc);
        commentToggleButton = new JButton();
        commentToggleButton.setAlignmentY(0.0f);
        commentToggleButton.setHorizontalAlignment(0);
        commentToggleButton.setText("Закомментировать строки';'");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(commentToggleButton, gbc);
        commentBlockToggleButton = new JButton();
        commentBlockToggleButton.setAlignmentY(0.0f);
        commentBlockToggleButton.setText("Закомментировать блок';'");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel1.add(commentBlockToggleButton, gbc);
        conditionToggleButton = new JButton();
        conditionToggleButton.setText("Вкл\\Выкл условн. инстр. IF THEN");
        conditionToggleButton.setToolTipText("Оборачивает строку или выделенные строки IF THEN ... IF");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(conditionToggleButton, gbc);
        conditionOnBlockToggleButton = new JButton();
        conditionOnBlockToggleButton.setText("Вкл\\Выкл условн. инстр. блок IF THEN");
        conditionOnBlockToggleButton.setToolTipText("Оборачивает блок кода IF THEN ... IF");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel1.add(conditionOnBlockToggleButton, gbc);
        button4 = new JButton();
        button4.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(button4, gbc);
        final JSeparator separator1 = new JSeparator();
        separator1.setAlignmentY(0.0f);
        separator1.setMinimumSize(new Dimension(1, 15));
        separator1.setPreferredSize(new Dimension(0, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel1.add(separator1, gbc);
        sepa2 = new JSeparator();
        sepa2.setMinimumSize(new Dimension(1, 15));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel1.add(sepa2, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        mainScrollPane = new JScrollPane();
        topPanel.add(mainScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setAlignmentX(0.5f);
        panel2.setMaximumSize(new Dimension(-1, -1));
        panel2.setMinimumSize(new Dimension(-1, -1));
        main.setRightComponent(panel2);
        bottomInfoPanel = new JSplitPane();
        bottomInfoPanel.setDividerLocation(451);
        bottomInfoPanel.setDividerSize(10);
        bottomInfoPanel.setResizeWeight(0.5);
        panel2.add(bottomInfoPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setMinimumSize(new Dimension(170, 20));
        panel3.setPreferredSize(new Dimension(170, 25));
        mainPanel.add(panel3, BorderLayout.SOUTH);
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        progressBar1 = new JProgressBar();
        progressBar1.setAlignmentX(0.0f);
        progressBar1.setAlignmentY(0.0f);
        progressBar1.setMaximumSize(new Dimension(32767, 5));
        progressBar1.setMinimumSize(new Dimension(10, 5));
        progressBar1.setPreferredSize(new Dimension(146, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 1, 2);
        panel3.add(progressBar1, gbc);
        leftInfoAboutGProgram = new JLabel();
        leftInfoAboutGProgram.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(leftInfoAboutGProgram, gbc);
        rightInfoAboutGProgram = new JLabel();
        rightInfoAboutGProgram.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(rightInfoAboutGProgram, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
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
