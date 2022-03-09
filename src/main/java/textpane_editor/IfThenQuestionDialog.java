package textpane_editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

class IfThenQuestionDialog extends JDialog {

    private JPanel pnPanel0;
    private JCheckBox cbBox0;
    private JLabel lbLabel1, lbLabel2;
    private JTextField tfText0, tfText1;
    private JButton btBut0, btBut1;
    private String[] sArray;


    IfThenQuestionDialog() {
        super();
    }
    private void initComponents() {
        pnPanel0 = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        pnPanel0.setLayout( gbPanel0 );

        cbBox0 = new JCheckBox( "Добавить переменную в параметры программы"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 0;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 8,0,0,0 );
        gbPanel0.setConstraints( cbBox0, gbcPanel0 );
        pnPanel0.add( cbBox0 );

        lbLabel1 = new JLabel( "Имя переменной параметра"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 8,0,0,0 );
        gbPanel0.setConstraints( lbLabel1, gbcPanel0 );
        pnPanel0.add( lbLabel1 );

        tfText0 = new JTextField( "number" );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 2;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( tfText0, gbcPanel0 );
        pnPanel0.add( tfText0 );

        lbLabel2 = new JLabel( "Значение переменной параметра"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 6,0,0,0 );
        gbPanel0.setConstraints( lbLabel2, gbcPanel0 );
        pnPanel0.add( lbLabel2 );

        tfText1 = new JTextField( "1" );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 3;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbPanel0.setConstraints( tfText1, gbcPanel0 );
        pnPanel0.add( tfText1 );

        btBut0 = new JButton( "OK"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 5;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 5,0,0,0 );
        gbPanel0.setConstraints( btBut0, gbcPanel0 );
        pnPanel0.add( btBut0 );

        btBut1 = new JButton( "Cancel"  );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 5;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets( 5,0,0,0 );
        gbPanel0.setConstraints( btBut1, gbcPanel0 );
        pnPanel0.add( btBut1 );

        setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        setContentPane( pnPanel0 );
        pack();
        setVisible( true );

        btBut0.addActionListener((ActionEvent ae) -> {
            sArray = new String[2];
            sArray[0] = tfText0.getText();
            sArray[1] = tfText1.getText();
            setVisible(false);
        });

        btBut1.addActionListener((ActionEvent ae) -> {
            setVisible(false);
        });

    }

    static void setConditionAtCode(List<String> list, int[] val){
        String s = JOptionPane.showInputDialog("Введите условие вида+" + " \'word(слово) = 1(число)\'");
        if (s != null) {
            if (!s.isEmpty()) {
                list.add(val[0], "IF " + s + " THEN");
                list.add(val[1] + 2, "FI");
                list.add(1, "PAR " + s + "\" \"");
            }
        }
        else return;
    }
    // Функция для определения нахождения каретки или выделенного текста в блоке  IF THEN.
    // Если каретка или выделенный текст находится в блоке возвращает TRUE, иначе FALSE.
    static boolean defineIfThenBlock(List<String> list, int[] n){
        int start = -1, end = -1;
        for (int i = n[0]; i > 0; i--) {
            if (list.get(i).contains("FI")) {
//                start = -1;
                break;
            }
            if (list.get(i).contains("IF")) {
                start = i;
                break;
            }
        }
        for (int i = n[1]; i < list.size(); i++) {
            if (list.get(i).contains("IF")) {
//                end = -1;
                break;
            }
            if (list.get(i).contains("FI")) {
                end = i;
                break;
            }
        }
        if ((start != -1) && (end != -1)) return true;
        else return false;
    }

    static void deleteCondition(List<String> list, int[] n){
        int start = -1 , end = -1;
        for (int i = n[0]; i > 0; i--) {
            if (list.get(i).contains("IF")) {
                start = i;
                break;
            }
        }
        for (int i = n[1]; i < list.size(); i++) {
            if (list.get(i).contains("FI")) end = i;
        }
        if ((start != -1) &&(end != -1)) {
            list.remove(end);     //Удаляем с конца. Т.к. если удалить с начала массива
            list.remove(start);   //элементы сдвинутся и будут удалена другая строка
        } else {
            JOptionPane.showMessageDialog(null,
                    "\"Правильный\" блок IF THEN не обнаружен. Используйте другую кнопку.",
                    "Внимание!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
