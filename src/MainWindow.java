
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * User: Grooman Date: 27.07.21 Time: 15:40
 */
class MainWindow extends JFrame {

    private final JPanel rightPanel;
    private UserPreferences up;
    private JPanel jPanel;
    private JPanel bottomPanel;
    private TextAreaWithStyles textArea;
    private JTextArea leftBottomTextArea;
    private JTextArea rightBottomTextArea;
    private JButton commentButton;
    private JLabel imageLabel;
    private JLabel textInfoLabel;
    private ControlProgramFile controlProgramFile;

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

        commentButton = new JButton();

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(176, 181, 176));

        rightPanel.add(imageLabel);
        rightPanel.add(commentButton);
        rightPanel.add(new JButton("Закомментировать блок\';\'"));
        rightPanel.add(new JButton("Добавить блок IF THEN"));
        rightPanel.add(new JButton("Удалить блок"));
        rightPanel.add(textInfoLabel);
        textArea = new TextAreaWithStyles();
        //ОПРЕДЕЛЕНИЕ НИЖНЕГО ПОЛЯ . Boreder SOUTH
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        //  Указываем диспечер компановки
        jPanel.setLayout(new BorderLayout());

        jPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
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

        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int numLine = RXTextUtilities.getLineAtCaret(textArea);
                textInfoLabel.setText(String.valueOf(numLine));
                setTextToCommentButton(textArea.isComments(numLine));

            }
        });

        commentButton.addActionListener(new ActionListener() {
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

        setJMenuBar(new Menu(MainWindow.this));
        setVisible(true);
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
        System.out.println("****");
        if(b) commentButton.setText("Раскомментировать строку");
        else commentButton.setText("Закомментировать строку");
    }

    void setTextInTextArea(List<String> s) {
        textArea.loadText(s);
    }
    String getTextFromTextArea(){
        return textArea.getStyleText();
    }
    void showImageOfProgram(){
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
    void setProgram(ControlProgramFile c){
        controlProgramFile  = c;
        setTextInTextArea(controlProgramFile.getDataFromFile());
        showImageOfProgram();
    }

}
