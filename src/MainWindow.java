
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * User: Grooman Date: 27.07.21 Time: 15:40
 */
class MainWindow extends JFrame {

    private UserPreferences up;
    private JPanel jp;
    private JPanel bottomPanel;
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

        // Указываем название программы
        setTitle("MorbProgEditor");

        up = new UserPreferences();
        jp = new JPanel();
        jp.setBackground(new Color(138, 200, 158));
        ta = new JTextArea(0,50);
        ta.setFont(new Font("Tahoma", Font.PLAIN, 12));
        jsp = new JScrollPane(ta);
        jl = new JLabel("Test one s");

        //ОПРЕДЕЛЕНИЕ НИЖНЕГО ПОЛЯ . Boreder SOUTH
        bottomPanel = new JPanel();
//        bottomPanel.setMaximumSize(getMaximumSize());
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.setBackground(new Color(120, 160, 200));


        //  Указываем диспечер компановки
        jp.setLayout(new BorderLayout());

        jp.add(jsp, BorderLayout.WEST);
        jp.add(jl, BorderLayout.EAST);


        jp.add(bottomPanel,BorderLayout.SOUTH);
        add(jp);

        // Завершить работу программы, когда пользователь
        // закрывает приложение
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Установить начальные размеры фрейма
        setToPreferredSizeFrame();

        // Устанавливаем место появления окна программы
        centreWindow(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
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

    void setTextInTextArea(String s) {
        ta.setText(s);
    }
    String getTextFromTextArea(){
        return ta.getText();
    }
    private void createBottomLabels(String text){
        JLabel j = new JLabel("<html>"+text+"<html>");  //    JLabel не поддреживает перенос строки. Но зато поддерживает html
        j.setBorder(BorderFactory.createEmptyBorder(0,10, 0 ,0));
        bottomPanel.add(j);
        j.setFont(new Font("Tahoma", Font.BOLD, 11));
    }
    void setTextInBottomLabels(ArrayList<String> arrayList){
        createBottomLabels(arrayList.get(0));
        createBottomLabels(arrayList.get(1));
    }
}
