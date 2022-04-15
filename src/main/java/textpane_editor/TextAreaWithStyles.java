package textpane_editor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;


public class TextAreaWithStyles extends JTextPane {
    private List<String> listGCommands;
    private String strData;
    private IfThenQuestionDialog ifThenQuestionDialog;
    // Стили редактора
    private String[] firstLetterOfComandString =
            {"H", "G0", "G1", "G2", "G3", "B", "GIN", "GOUT",
                    "C", "XG0", "XB", "XGIN", "XGOUT",
                    "XL2P", "XAR", "", "MSG", "N"};
    private Style heading = null; // стиль заголовка
    private Style normal = null; // стиль текста
    private Style comment = null; // стиль коментария
    private final String STYLE_heading = "heading",
            STYLE_normal = "normal",
            STYLE_comment = "comment",
            FONT_style = "Times New Roman";
    private Document doc;
    private final UndoManager undo;
    private static final Logger log = LoggerFactory.getLogger(TextAreaWithStyles.class.getName());

    static int n = 0;

    public TextAreaWithStyles(List<String> list) {
        listGCommands = list;
        ifThenQuestionDialog = new IfThenQuestionDialog(listGCommands);

        // Привязка Undo Redo функций к JTextPane
        doc = this.getDocument();
        undo = new UndoManager();

        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });


        // Определение стилей редактора
//        createStyles();
        setListenerForTextPane();
    }

    /**
     * Процедура формирования стилей редактора
     */
    public void undo() {
        try {
            if (undo.canUndo()) {
                undo.undo();
            }
        } catch (CannotUndoException e) {
        }
    }

    public void redo() {
        try {
            if (undo.canRedo()) {
                undo.redo();
            }
        } catch (CannotRedoException e) {
        }
    }

    public void fin() {
        new Search(this);
    }

    private void createStyles() {
        // Создание стилей
        comment = this.addStyle(STYLE_comment, null);
        StyleConstants.setFontSize(comment, 15);
        StyleConstants.setItalic(comment, true);
        StyleConstants.setStrikeThrough(comment, true);

        normal = this.addStyle(STYLE_normal, null);
        StyleConstants.setFontFamily(normal, FONT_style);
        StyleConstants.setFontSize(normal, 15);

        // Наследуем свойстdо FontFamily
        heading = this.addStyle(STYLE_heading, normal);
        StyleConstants.setFontSize(heading, 17);
        StyleConstants.setBold(heading, true);
        StyleConstants.setBackground(heading, new Color(171, 171, 171));
    }

    /**
     * Процедура изменения стиля документа
     */
    private void changeDocumentStyle() {
        // Изменение стиля части текста
        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setForeground(blue, Color.blue);
        StyledDocument doc = this.getStyledDocument();
        doc.setCharacterAttributes(30, 9, blue, false);
    }

    /**
     * Процедура загрузки текста в редактор
     */

    public void loadText() {
        int n = 1;
        String str = "";
        Style style = null;
        // Загружаем в документ содержимое
        for (String s : listGCommands) {
            str += n < listGCommands.size() ? s + "\n" : s;
//            if (s.startsWith("H")) style = heading;
//            else if (s.startsWith(";")) style = comment;
//            else style = normal;
//            insertText(n < listGCommands.size() ? s + "\n" : s, null);   // Запрет перевода каретки на новую строку
//            n++;

        }
        setText(str);
        //  Определение функции для именения стиля части текста
//        changeDocumentStyle();
//        setListenerForTextPane();
    }

    /**
     * Процедура добавления в редактор строки определенного стиля
     *
     * @param string строка
     * @param style  стиль
     */
    private void insertText(String string, Style style) {
        try {
//            doc = this.getDocument();
            doc.insertString(doc.getLength(), string, style);
        } catch (Exception e) {
            log.error("Ошибка вставки данных в документ {}", e);
        }
        repaint();
    }

    public String getStyleText() {
        return this.getText();
    }

    // Метод позволяет появиться горизонтальному ScrollBar,
    // тем самым запрещает переноситься тексту на новую строку.
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getUI().getPreferredSize(this).width <= getParent().getSize().width;
    }

    public void clearTextPane() {
        textAreaReset();
    }

    public void setCommentOfLines(ActionEvent ae) {
        int[] val = {0, 0};
        int i;
        if (ae.getActionCommand().equals("comLine")) val = getStartAndEndSelectedText();
        else if (ae.getActionCommand().equals("comBlock")) val = defineBlockOfCodeByCaret();
        else log.error("Был сделан какой-то неожиданный выбор при нажатии чего-то.");
        for (i = val[0]; i <= val[1]; i++) {
            if (!isComments(i)) {
                listGCommands.set(i, ";" + listGCommands.get(i));
            } else {
                listGCommands.set(i, (listGCommands.get(i).substring(1)));
            }
        }
        loadText();
        RXTextUtilities.gotoStartOfLine(this, i + 1);
    }

    public void setConditionAtCode(ActionEvent ae) {
        int[] val = {0, 0};
        if (ae.getActionCommand().equals("condiLines")) val = getStartAndEndSelectedText();
        else if (ae.getActionCommand().equals("condiBlock")) val = defineBlockOfCodeByCaret();
        if (ifThenQuestionDialog.defineIfThenBlock(listGCommands, val)) {
            int answer = JOptionPane.showConfirmDialog(null,
                    "Удалить условие IF THEN ?",
                    "Предупреждение!",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (answer == 0) {
                ifThenQuestionDialog.deleteCondition(listGCommands, val);
            }
            if (answer == 1) {
                ifThenQuestionDialog.setConditionAtCode(val);
            }
        } else {
            ifThenQuestionDialog.setConditionAtCode(val);
        }
        loadText();
        RXTextUtilities.gotoStartOfLine(this, 1);
    }

    private boolean isComments(int line) {
        if (listGCommands.get(line).charAt(0) == ';') return true;
        else return false;
    }

    private void textAreaReset() {
        this.setText("");
    }

    private int[] getStartAndEndSelectedText() {      // пересмотрнеть возможно вывести один руут будет лучше
        Element root = TextAreaWithStyles.this.getDocument().getDefaultRootElement();
        int startText, endText;
        int a = root.getElementIndex(TextAreaWithStyles.this.getSelectionStart());  //Начало выделения
        int b = root.getElementIndex(TextAreaWithStyles.this.getSelectionEnd());  //Конец выделения
        if (a <= b) {
            startText = a;
            endText = b;
        } else {
            startText = b;
            endText = a;
        }
        return new int[]{startText, endText};
    }

    private int[] defineBlockOfCodeByCaretS() {
        int end = 0, start = 0;
        int n = this.getCaretPosition();
        start = strData.lastIndexOf("GIN", n);
        end = strData.indexOf("GOUT", n);
        if ((start == -1) || (end == -1)) {
//            JOptionPane.showMessageDialog(null, "Блок не найден");
        }
        System.out.println(start + "  " + end);
        return new int[]{start, end};
    }

    private int[] defineBlockOfCodeByCaret() {
        int n = RXTextUtilities.getLineAtCaret(this) - 1;
        int end = 0, start = 0;
        for (int i = n; i > 0; i--) {
            if (listGCommands.get(i).contains("GIN")) {
                if (listGCommands.get(i - 1).contains("C")) {
                    start = i - 1;
                    break;
                } else start = i;
                break;
            }
        }
        for (int i = n; i < listGCommands.size(); i++) {
            if (listGCommands.get(i).contains("GOUT")) {
                end = i;
                break;
            }
        }
        return new int[]{start, end};
    }

    void setListenerForTextPane() {
        this.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
//                defineBlockOfCodeByCaretS();
//                int n;
//                n = RXTextUtilities.getLineAtCaret(TextAreaWithStyles.this);
//                System.out.println(ifThenQuestionDialog.defineIfThenBlock(listGCommands,new int[]{n,n}));
            }
        });
    }

    class IfThenQuestionDialog extends JDialog {

        private JPanel pnPanel0;
        private JCheckBox cbBox0;
        private JLabel lbLabel1, lbLabel2;
        private JTextField tfText0, tfText1;
        private JButton btBut0, btBut1;
        private String[] sArray;
        List<String> list;


        IfThenQuestionDialog(List<String> list) {
            super();
            this.list = list;
        }

        private void initComponents(int[] val) {
            pnPanel0 = new JPanel();
            GridBagLayout gbPanel0 = new GridBagLayout();
            GridBagConstraints gbcPanel0 = new GridBagConstraints();
            pnPanel0.setLayout(gbPanel0);

            cbBox0 = new JCheckBox("Добавить переменную в параметры программы");
            gbcPanel0.gridx = 0;
            gbcPanel0.gridy = 0;
            gbcPanel0.gridwidth = 3;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbcPanel0.insets = new Insets(8, 0, 0, 0);
            gbPanel0.setConstraints(cbBox0, gbcPanel0);
            pnPanel0.add(cbBox0);

            lbLabel1 = new JLabel("Имя переменной параметра");
            gbcPanel0.gridx = 0;
            gbcPanel0.gridy = 1;
            gbcPanel0.gridwidth = 3;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 1;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbcPanel0.insets = new Insets(8, 0, 0, 0);
            gbPanel0.setConstraints(lbLabel1, gbcPanel0);
            pnPanel0.add(lbLabel1);

            tfText0 = new JTextField("number");
            gbcPanel0.gridx = 0;
            gbcPanel0.gridy = 2;
            gbcPanel0.gridwidth = 3;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints(tfText0, gbcPanel0);
            pnPanel0.add(tfText0);

            lbLabel2 = new JLabel("Значение переменной параметра");
            gbcPanel0.gridx = 0;
            gbcPanel0.gridy = 3;
            gbcPanel0.gridwidth = 3;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 1;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbcPanel0.insets = new Insets(6, 0, 0, 0);
            gbPanel0.setConstraints(lbLabel2, gbcPanel0);
            pnPanel0.add(lbLabel2);

            tfText1 = new JTextField("1");
            gbcPanel0.gridx = 0;
            gbcPanel0.gridy = 4;
            gbcPanel0.gridwidth = 3;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbPanel0.setConstraints(tfText1, gbcPanel0);
            pnPanel0.add(tfText1);

            btBut0 = new JButton("OK");
            gbcPanel0.gridx = 1;
            gbcPanel0.gridy = 5;
            gbcPanel0.gridwidth = 1;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbcPanel0.insets = new Insets(5, 0, 0, 0);
            gbPanel0.setConstraints(btBut0, gbcPanel0);
            pnPanel0.add(btBut0);

            btBut1 = new JButton("Cancel");
            gbcPanel0.gridx = 2;
            gbcPanel0.gridy = 5;
            gbcPanel0.gridwidth = 1;
            gbcPanel0.gridheight = 1;
            gbcPanel0.fill = GridBagConstraints.BOTH;
            gbcPanel0.weightx = 1;
            gbcPanel0.weighty = 0;
            gbcPanel0.anchor = GridBagConstraints.NORTH;
            gbcPanel0.insets = new Insets(5, 0, 0, 0);
            gbPanel0.setConstraints(btBut1, gbcPanel0);
            pnPanel0.add(btBut1);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            setContentPane(pnPanel0);
            pack();
            setVisible(true);

            btBut0.addActionListener((ActionEvent ae) -> {
                setCondition(cbBox0.isSelected(), tfText0.getText(), tfText1.getText(), val);
                setVisible(false);
            });

            btBut1.addActionListener((ActionEvent ae) -> {
                dispose();
            });

        }

        void setConditionAtCode(int[] val) {
            initComponents(val);
        }

        private void setCondition(boolean checkBox, String fText, String sText, int[] val) {
            if (!fText.isEmpty()) {
                list.add(val[0], "IF " + fText + "=" + sText + " THEN");
                list.add(val[1] + 2, "FI");
                if (checkBox == true) {
                    list.add(1, "PAR " + fText + "=" + sText + "\" \"");
                }
                TextAreaWithStyles.this.loadText();
            } else {
                JOptionPane.showMessageDialog(null, "Не ввели значение в первой строке.");
            }
        }

        // Функция для определения нахождения каретки или выделенного текста в блоке  IF THEN.
        // Если каретка или выделенный текст находится в блоке возвращает TRUE, иначе FALSE.
        boolean defineIfThenBlock(List<String> list, int[] n) {
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

        void deleteCondition(List<String> list, int[] n) {
            int start = -1, end = -1;
            for (int i = n[0]; i > 0; i--) {
                if (list.get(i).contains("IF")) {
                    start = i;
                    break;
                }
            }
            for (int i = n[1]; i < list.size(); i++) {
                if (list.get(i).contains("FI")) {
                    end = i;
                }
            }

            if ((start != -1) && (end != -1)) {
                String string = list.get(start);   //Получаем строку заранее, т.к. она потом будет удалена

                list.remove(end);     //Удаляем с конца. Т.к. если удалить с начала массива
                list.remove(start);   //элементы сдвинутся и будут удалена другая строка

                String condName = string.substring(3, string.indexOf("THEN"));
                String[] val = condName.split("[<>=]");


                for (int i = 1; i < list.size() / 3; i++) {         // Если цикл не находит " PAR ", он проходит весь массив до конца
                    if (list.get(i).startsWith("PAR")) {          // В управляющей программе может быть  не больше 20 строк " PAR "
                        if (list.get(i).indexOf(val[0]) != -1) {
                            list.remove(i);
                            break;
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "\"Правильный\" блок IF THEN не обнаружен. Используйте другую кнопку.",
                        "Внимание!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


}

