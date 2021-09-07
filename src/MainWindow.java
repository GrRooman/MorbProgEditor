
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: Grooman Date: 27.07.21 Time: 15:40
 */
class MainWindow extends JFrame {

    private UserPreferences up;
    private JPanel jp;
    private JTextArea ta;
    private JLabel jl;
    private JScrollPane jsp;

    MainWindow() {

        // Test     style
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        try {
            UIManager.setLookAndFeel(infos[3].getClassName());
            SwingUtilities.updateComponentTreeUI(MainWindow.this);
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Test    style

        setTitle("MorbProgEditor");

        up = new UserPreferences();
        jp = new JPanel();
        ta = new JTextArea(40, 30);
        jsp = new JScrollPane(ta);
        jl = new JLabel("Test one");

        jp.add(jsp, BorderLayout.CENTER);
        jp.add(jl, BorderLayout.EAST);
        add(jp);

        // Завершить работу программы, когда пользователь
        // закрывает приложение
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  Указываем диспечер компановки
        setLayout(new FlowLayout());

        // Установить начальные размеры фрейма
        setToPreferredSizeFrame();

        // Устанавливаем место появления окна программы
        centreWindow(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                up.setSizeFrame(MainWindow.this);
                //  prefs.putInt("height", MainWindow.this.getHeight());
                //  prefs.putInt("width", MainWindow.this.getWidth());
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

    void setTextInTextArea(String s) {
        ta.setText(s);
        pack();
    }

    String getTextFromTextArea() {
        return ta.getText();
    }
}
