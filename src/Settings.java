import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: AL8973
 * Date: 28.10.21
 * Time: 11:05
 */
 class Settings extends JFrame {
    UserPreferences userPreferences;
    JTextField jTextField;
    JTextField jTextField2;
    JButton okButton;
    JButton closeButton;
    JButton resetButton;
    Settings(){
        super("Настройки программы");
        setSize(300,300);
        setLocation(300,300);
        setLayout(new GridLayout(5,1));

        userPreferences = new UserPreferences();

        jTextField = new JTextField(userPreferences.getPathToConverter());
        jTextField2 = new JTextField(userPreferences.getPathToControlProgram());

        resetButton = new JButton("Сбросить настройки");
        closeButton = new JButton("Закрыть настройки");
        okButton = new JButton("Подтвердить введенные данные");

        add(jTextField);
        add(jTextField2);
        add(okButton);
        add(resetButton) ;
        add(closeButton);

        okButton.addActionListener((ActionEvent ae) ->{
            userPreferences.setPathToConver(jTextField.getText());
            userPreferences.setPathToControlProgram(jTextField2.getText());
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSettings();
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindowSetting();
            }
        });

    }
    void resetSettings(){
        int i = JOptionPane.showConfirmDialog(this,
                "Вы уверены, что хотите сбросить настройки программы?",
                "Подтверждение",
                JOptionPane.OK_CANCEL_OPTION);

        if(i == 1) userPreferences.clearUserPreferences();
        else return;
    }


    void showWindowSetting(){
        setVisible(true);
    }
    void closeWindowSetting(){
        setVisible(false);
    }
}
