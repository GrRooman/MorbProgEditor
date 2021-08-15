
import java.util.prefs.Preferences;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: Grooman
 * Date: 27.07.21
 * Time: 15:40
 */
class MainWindow {
    private Preferences prefs;
    private JFrame jfrm;
    JPanel jp;
    MainWindow() {
        // Создать новый контейнер JFrame
        jp = new JPanel(); 
        JTextArea ta = new JTextArea(100,100);
        // Создать новый контейнер JFrame
        jfrm = new JFrame("Programm Editor");
        jp.add(ta);
        jfrm.add(jp);
        // Здесь мы создаем объект класса Preferences
        prefs = Preferences.userRoot().node("programmeditor");
        
        // Установить начальные размеры фрейма
        setToPreferredSizeFrame();
        
        
        // Завершить работу программы, когда пользователь
        // закрывает приложение
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  Указываем диспечер компановки
        jfrm.setLayout(new FlowLayout());
        // Устанавливаем место появления окна программы
        centreWindow(jfrm);
        
        jfrm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                prefs.putInt("height", jfrm.getHeight());
                prefs.putInt("width", jfrm.getWidth()); 
            }
        });
        

        jfrm.setJMenuBar(new  Menu());
        jfrm.setVisible(true);
    }
    
     private void setToPreferredSizeFrame(){
        // Получить значение параметров и установить размеры формы
        int height = prefs.getInt("height",300);
        int width = prefs.getInt("width",300);
        jfrm.setSize(width,height);
    }
     
    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
}
}
