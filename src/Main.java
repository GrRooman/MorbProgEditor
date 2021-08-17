
import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        
        /**********************************/
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });

    }
}



