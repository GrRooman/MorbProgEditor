package textpane_editor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;




public class TextAreaWithStyles extends JTextPane{
    private List<String> listGCommands;
    // Стили редактора
    private String[] firstLetterOfComandString =
            {"H","G0", "G1", "G2", "G3", "B", "GIN", "GOUT",
                    "C", "XG0", "XB", "XGIN", "XGOUT",
                    "XL2P", "XAR", "", "MSG", "N"};
    private  Style     heading    = null; // стиль заголовка
    private  Style     normal     = null; // стиль текста
    private  Style     comment    = null; // стиль коментария
    private  final  String STYLE_heading = "heading",
            STYLE_normal  = "normal" ,
            STYLE_comment = "comment" ,
            FONT_style    = "Times New Roman";
    private Document doc;
    private static final Logger log = LoggerFactory.getLogger(TextAreaWithStyles.class.getName());

    static int n=0;

    public TextAreaWithStyles(List<String> list) {
        listGCommands = list;
        // Определение стилей редактора
        createStyles();
        System.out.println(n++);
    }
    /**
     * Процедура формирования стилей редактора
     */
    private void createStyles()
    {
        // Создание стилей
        comment = this.addStyle(STYLE_comment, null);
        StyleConstants.setFontSize(comment, 15);
        StyleConstants.setItalic(comment, true);
        StyleConstants.setStrikeThrough(comment, true);

        normal = this.addStyle(STYLE_normal, null);
        StyleConstants.setFontFamily(normal ,FONT_style);
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
    private void changeDocumentStyle()
    {
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
        int n=1;
        Style style=null;
        // Загружаем в документ содержимое
        for (String s : listGCommands) {
            if(s.startsWith("H")) style = heading;
            else if(s.startsWith(";")) style = comment;
            else style = normal;
            insertText( n < listGCommands.size() ? s+"\n" : s, style );   // Запрет перевода каретки на новую строку
            n++;
        }
        //  Определение функции для зменение стиля части текста
        changeDocumentStyle();
//        setListenerForTextPane();
    }
    /**
     * Процедура добавления в редактор строки определенного стиля
     * @param string строка
     * @param style стиль
     */
    private void insertText(String string, Style style) {
        try {
            doc = this.getDocument();
            doc.insertString(doc.getLength(), string, style);
        } catch (Exception e) {
            log.error("Ошибка вставки данных в документ {}",e);
        }
        repaint();
    }
    public String getStyleText(){
        return this.getText();
    }

    // Метод позволяет появиться горизонтальному ScrollBar,
    // тем самым запрещает переноситься тексту на новую строку.
    @Override
    public boolean getScrollableTracksViewportWidth(){
        return getUI().getPreferredSize(this).width <= getParent().getSize().width;
    }
    public void clearTextPane(){
//        listGCommands.clear();
        textAreaReset();
//        System.out.println("clear");
    }

    public void setCommentOfLines(ActionEvent ae){
        int[] val = {0,0};
        if (ae.getActionCommand().equals("comLine"))  val = getStartAndEndSelectedText();
        else if (ae.getActionCommand().equals("comLines")) val = defineBlockOfCodeByCaret();
        else log.error("Был сделан какой-то неожиданный выбор при нажатии чего-то.");
        for (int i = val[0]; i <= val[1]; i++) {
            if(!isComments(i)){
                listGCommands.set(i, ";" + listGCommands.get(i));
            }
            else {
                listGCommands.set(i, (listGCommands.get(i).substring(1)));
            }
            textAreaReset();
            loadText();
            RXTextUtilities.gotoStartOfLine(this, i+1);
        }
    }

    public void setConditionAtCode(){
        IfThenQuestionDialog i = new IfThenQuestionDialog(listGCommands);
        i.getConditionAtCode(defineBlockOfCodeByCaret());
        textAreaReset();
        loadText();
        RXTextUtilities.gotoStartOfLine(this, 1);
    }
    public void deleteBlockOfCode(){
        System.out.println(this.getText());
//        IfThenQuestionDialog i = new IfThenQuestionDialog(listGCommands);
//        i.deleteCondition(RXTextUtilities.getLineAtCaret(this));
//        textAreaReset();
//        loadText();
//        RXTextUtilities.gotoStartOfLine(this, 1);
    }

    private boolean isComments(int line){
        if(listGCommands.get(line).charAt(0) == ';') return true;
        else return false;
    }

    private void textAreaReset(){
        this.setText("");
    }

    private int[] getStartAndEndSelectedText(){      // пересмотрнеть возможно вывести один руут будет лучше
        Element root = TextAreaWithStyles.this.getDocument().getDefaultRootElement();
        int startText,endText;
        int a = root.getElementIndex( TextAreaWithStyles.this.getSelectionStart() );  //Начало выделения
        int b = root.getElementIndex( TextAreaWithStyles.this.getSelectionEnd()   );  //Конец выделения
        if ( a<=b ) {
            startText = a;
            endText   = b;
        }
        else {
            startText = b;
            endText   = a;
        }
        return new int[]{startText, endText};
    }

    private int[] defineBlockOfCodeByCaret(){
        int n = RXTextUtilities.getLineAtCaret(this)-1;
        int end = 0;
        int start = 0;
        for (int i = n; i > 0; i--) {
            if( listGCommands.get(i).contains("GIN") ) {
                if( listGCommands.get(i-1).contains("C") ) {start = i-1; break;}
                else start = i;
                break;
            }
        }
        for (int i = n; i < listGCommands.size(); i++) {
            if( listGCommands.get(i).contains("GOUT") ) {
                end = i;
                break;
            }
        }
        return new int[]{start, end};
    }

    void setListenerForTextPane(){
        this.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
//                getStartAndEndSelectedText();
//                System.out.println(startText+"  "+endText);
            }
        });
    }
}

