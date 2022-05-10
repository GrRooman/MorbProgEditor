import javax.swing.*;
import java.awt.*;

public class MainWind extends JFrame {
    private JPanel bottomInfoPanel;
    private JSplitPane main;
    private JPanel topPanel;
    private JProgressBar progressBar1;
    private JTextPane textPane1;
    private JButton button1;
    private JButton button2;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button3;
    private JSeparator sepa2;
    private JPanel maimp;
    private JSeparator sepa;

    public MainWind() {
        this.getContentPane().add(maimp);
    }

    public static void main(String[] args) {
        // Создаем инстанцию класса MainWindow
        MainWind mainWindow = new MainWind();

        // Упаковываем все элементы с нашей формы
        mainWindow.pack();

        // Изменяем размеры окна
        mainWindow.setSize(new Dimension(500, 400));

        // Отображаем созданное окно
        mainWindow.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
