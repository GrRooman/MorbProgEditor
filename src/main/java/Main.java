
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("Start the program.");
        /**********************************/
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });

    }
}



