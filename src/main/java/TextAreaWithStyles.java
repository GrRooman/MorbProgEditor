import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.Color;
import java.util.List;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;




class TextAreaWithStyles extends JTextPane{
    private List<String> list;
    // Стили редактора
    private  Style     heading    = null; // стиль заголовка
    private  Style     normal     = null; // стиль текста
    private  Style     comment    = null; // стиль коментария
    private  final  String STYLE_heading = "heading",
            STYLE_normal  = "normal" ,
            STYLE_comment = "comment" ,
            FONT_style    = "Times New Roman";
    private Document doc;
    private static final Logger log = LoggerFactory.getLogger(TextAreaWithStyles.class.getName());
    public TextAreaWithStyles()
    {
        // Определение стилей редактора
        createStyles();
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
     * Процедура загрузки текста в редактор
     */
    void loadText(List<String> list)
    {
        Style style=null;
        this.list = list;
        // Загружаем в документ содержимое
        for (String s : list) {
            if(s.startsWith("H")) style = heading;
            else if(s.startsWith(";")) style = comment;
            else style = normal;
            insertText(s+"\n", style);
        }
        //  Определение функции для зменение стиля части текста
        changeDocumentStyle();
//        setListenerForTextPane();
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
     * Процедура добавления в редактор строки определенного стиля
     * @param string строка
     * @param style стиль
     */
    private void insertText(String string, Style style)
    {
        try {
            doc = this.getDocument();
            doc.insertString(doc.getLength(), string, style);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    void setComOfLine(){
        int numLine = RXTextUtilities.getLineAtCaret(TextAreaWithStyles.this);
        int numLineForList = numLine-1;
        if(!isComments(numLine)){
            list.set(numLineForList, ";" + list.get(numLineForList));
            textAreaReset();
            loadText(list);
            RXTextUtilities.gotoStartOfLine(this, numLine+1);
        }
        else {
            list.set(numLineForList, (list.get(numLineForList).substring(1)));
            textAreaReset();
            loadText(list);
            RXTextUtilities.gotoStartOfLine(this, numLine+1);
        }
    }

    private boolean isComments(int line){

        if(list.get(line-1).charAt(0) == ';') return true;
        else return false;
    }

    private void textAreaReset(){
        this.setText("");
    }

    void setListenerForTextPane(){
        this.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int numCol = RXTextUtilities.getColumnAtCaret(TextAreaWithStyles.this);

            }
        });

    }

}

