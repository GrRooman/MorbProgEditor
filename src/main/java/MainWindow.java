
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import textpane_editor.TextAreaWithStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;


class MainWindow extends JFrame {

    private final JPanel rightPanel;
    private UserPreferences up;
    private JPanel jPanel;
    private JPanel bottomPanel;
    private TextAreaWithStyles mainTextArea;
    private JTextArea leftBottomTextArea;
    private JTextArea rightBottomTextArea;
    private JButton commentToggleButton;
    private JButton commentBlockToggleButton;
    private JButton conditionOnBlockToggleButton;
    private JButton deleteBlockButton;
    private JLabel imageLabel;
    private JLabel textInfoLabel;
    private JScrollPane scrollPane;
    private ControlProgramFile controlProgramFile;
    private static final Logger log = LoggerFactory.getLogger(MainWindow.class.getName());

    MainWindow() {
        setTitle("MorbProgEditor");
        up = new UserPreferences();
        jPanel = new JPanel();
        leftBottomTextArea = new JTextArea();
        rightBottomTextArea = new JTextArea();

        imageLabel = new JLabel("Место для вашей рекламы");
        imageLabel.setPreferredSize(new Dimension(220, 105));
        imageLabel.setMaximumSize(new Dimension(220, 105));

        textInfoLabel = new JLabel("new data");
        textInfoLabel.setPreferredSize(new Dimension(220, 105));
        textInfoLabel.setMaximumSize(new Dimension(220, 105));

        commentToggleButton = new JButton("Закомментировать\';\'");
        commentBlockToggleButton = new JButton("Закомментировать блок\';\'");
        conditionOnBlockToggleButton = new JButton("Условн. инстр. блок IF THEN");
        deleteBlockButton = new JButton("Удалить блок");

        commentToggleButton.setActionCommand("comLine");
        commentBlockToggleButton.setActionCommand("comLines");

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(176, 181, 176));

        rightPanel.add(imageLabel);
        rightPanel.add(commentToggleButton);
        rightPanel.add(commentBlockToggleButton);
        rightPanel.add(conditionOnBlockToggleButton);
        rightPanel.add(deleteBlockButton);
        rightPanel.add(textInfoLabel);
//
        //ОПРЕДЕЛЕНИЕ НИЖНЕГО ПОЛЯ . Boreder SOUTH
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        //  Указываем диспечер компановки
        jPanel.setLayout(new BorderLayout());

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
        conditionOnBlockToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainTextArea.setConditionAtCode();
            }
        });
        deleteBlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(mainTextArea);
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DeleteTrash.deleteFile();
                up.setSizeFrame(MainWindow.this);
            }
        });
        setJMenuBar(new Menu(MainWindow.this));
        setVisible(true);
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
    }

}
