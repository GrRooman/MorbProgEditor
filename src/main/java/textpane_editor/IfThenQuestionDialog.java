package textpane_editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;


class IfThenQuestionDialog extends JDialog {

    private JPanel pnPanel0;
    private JCheckBox cbBox0;
    private JLabel lbLabel1;
    private JTextField tfText0;
    private JLabel lbLabel2;
    private JTextField tfText1;
    private JButton btBut0;
    private JButton btBut1;
    private String[] sArray;

    private java.util.List<String> listGCommands;

    IfThenQuestionDialog(List<String> listGCommands){
        super();
        this.listGCommands = listGCommands;
    }
    private void initComponents(){
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

    public void getConditionAtCode(int[] val){
        String s = JOptionPane.showInputDialog("Введите условие вида+" +
                " \'word(слово) = 1(число)\'");

        if(!s.isEmpty()){
            listGCommands.add(val[0], "IF " + s + " THEN");
            listGCommands.add(val[1] + 2, "FI");
            listGCommands.add(1, "PAR "+ s + "\" \"");
        }
        else return;
    }

    // ПЕРЕДЕЛАТЬ ЭТУ ДИЧЬ!
    public void deleteCondition(int n){
        int end = 0;
        int start = 0;
        for (int i = n; i > 0; i--) {
            if ( listGCommands.get(i).contains("FI") ) {
                JOptionPane.showMessageDialog(null,"Блок был указан не верно.");
                return;
            }
            if( listGCommands.get(i).contains("IF") ) {
                start = i;
                break;
            }
        }
        for (int i = n; i < listGCommands.size(); i++) {
            if( listGCommands.get(i).contains("IF") )  {
                JOptionPane.showMessageDialog(null,"А нет вашего блока");
                break;
            }
            if( listGCommands.get(i).contains("FI") )  end = i;
        }
        System.out.println(listGCommands.get(start).substring(3, listGCommands.get(start).indexOf("THEN")-1));
        if (start == 0 | end == 0){
            JOptionPane.showMessageDialog(null,"А нет вашего блока");
            return;}
        else {
            listGCommands.remove(end);     //Удаляем с конца. Т.к. если удалить с начала массива
            listGCommands.remove(start);   //элементы сдвинутся и будут удалена другая строка
        }

    }


}
