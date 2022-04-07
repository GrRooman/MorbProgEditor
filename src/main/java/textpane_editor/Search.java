package textpane_editor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Search extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    int startIndex = 0;
    int select_start = -1;
    JLabel lab1, lab2;
    JTextField textF, textR;
    JButton findBtn, findNext, replace, replaceAll, cancel;
    private JTextPane txt;

    public Search(JTextPane text) {
        this.txt = text;
        txt.requestFocusInWindow();              //Устанавливает фокус на текстовую область
        lab1 = new JLabel("Найти:");
        lab2 = new JLabel("Заменить:");
        textF = new JTextField(30);
        textR = new JTextField(30);
        findBtn = new JButton("Найти");
        findNext = new JButton("Найти следующее");
        replace = new JButton("Заменить");
        replaceAll = new JButton("Заменить все");
        cancel = new JButton("Отменить");

        // Set Layout NULL
        setLayout(null);

        // Set the width and height of the label
        int labWidth = 80;
        int labHeight = 20;

        // Adding labels
        lab1.setBounds(10, 10, labWidth, labHeight);
        add(lab1);
        textF.setBounds(10 + labWidth, 10, 120, 20);
        add(textF);
        lab2.setBounds(10, 10 + labHeight + 10, labWidth, labHeight);
        add(lab2);
        textR.setBounds(10 + labWidth, 10 + labHeight + 10, 120, 20);
        add(textR);

        // Adding buttons
        findBtn.setBounds(225, 6, 115, 20);
        add(findBtn);
        findBtn.addActionListener(this);

        findNext.setBounds(225, 28, 115, 20);
        add(findNext);
        findNext.addActionListener(this);

        replace.setBounds(225, 50, 115, 20);
        add(replace);
        replace.addActionListener(this);

        replaceAll.setBounds(225, 72, 115, 20);
        add(replaceAll);
        replaceAll.addActionListener(this);

        cancel.setBounds(225, 94, 115, 20);
        add(cancel);
        cancel.addActionListener(this);


        // Set the width and height of the window
        int width = 360;
        int height = 160;

        // Set size window
        setSize(width, height);
        // center the frame on the frame
        setLocationRelativeTo(txt);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        System.out.println(txt.getText());
    }


    public void find() {
        select_start = txt.getText().toLowerCase().indexOf(textF.getText().toLowerCase());
        if (select_start == -1) {
            startIndex = 0;
            JOptionPane.showMessageDialog(null, "Ничего не найдено \"" + textF.getText() + "\"!");
            return;
        }
        if (select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase())) {
            startIndex = 0;
        }
        int select_end = select_start + textF.getText().length();
        txt.select(select_start, select_end);
    }

    public void findn() {
        int pos;
        int currentPos = 0;
        if (findNext.getText().isEmpty())
            return;
        String context = txt.getText();
        String wordToFind = textF.getText();
        pos = context.indexOf(wordToFind, currentPos);
        if (pos == -1)
            currentPos = 0;
        pos = context.indexOf(wordToFind, currentPos);
        currentPos = pos;
        if (pos != -1) {
            txt.setSelectionStart(pos);
            txt.setSelectionEnd(pos + wordToFind.length());
            txt.requestFocusInWindow();
            currentPos = (currentPos + 1);
        } else {
            currentPos = 0;
//            notFound.setVisible(true);
        }
    }

    public void findNext() {
        String selection = txt.getSelectedText();
        try {
            selection.equals("");
        } catch (NullPointerException e) {
            selection = textF.getText();
            try {
                selection.equals("");
            } catch (NullPointerException e2) {
                selection = JOptionPane.showInputDialog("Поиск:");
                textF.setText(selection);
            }
        }
        try {
            int select_start = txt.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
            int select_end = select_start + selection.length();
            txt.select(select_start, select_end);
            System.out.println(select_start + "  " + select_end);
            startIndex = select_end + 1;

            if (select_start == txt.getText().toLowerCase().lastIndexOf(selection.toLowerCase())) {
                startIndex = 0;
            }
        } catch (NullPointerException e) {
        }
    }

    public void replace() {
        try {
            find();
            if (select_start != -1)
                txt.replaceSelection(textR.getText());
        } catch (NullPointerException e) {
            System.out.print("Null Pointer Exception: " + e);
        }
    }

    public void replaceAll() {
        txt.setText(txt.getText().toLowerCase().replaceAll(textF.getText().toLowerCase(), textR.getText()));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findBtn) {
            find();
        } else if (e.getSource() == findNext) {
            findNext();
        } else if (e.getSource() == replace) {
            replace();
        } else if (e.getSource() == replaceAll) {
            replaceAll();
        } else if (e.getSource() == cancel) {
            this.setVisible(false);
        }
    }

}