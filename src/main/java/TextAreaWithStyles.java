import javax.swing.*;
import java.awt.Color;
import java.util.List;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;




class TextAreaWithStyles extends JTextPane{
    List<String> listCom;
    // Стили редактора
    private  Style     heading    = null; // стиль заголовка
    private  Style     normal     = null; // стиль текста
    private  final  String STYLE_heading = "heading",
            STYLE_normal  = "normal" ,
            FONT_style    = "Times New Roman";
    Document doc;
    public TextAreaWithStyles()
    {
        // Определение стилей редактора
        createStyles();
        addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int numLine = RXTextUtilities.getLineAtCaret(TextAreaWithStyles.this);
            }
        });

    }
    /**
     * Процедура формирования стилей редактора
     */
    private void createStyles()
    {
        // Создание стилей
        normal = this.addStyle(STYLE_normal, null);
        StyleConstants.setFontFamily(normal, FONT_style);
        StyleConstants.setFontSize(normal, 16);
        // Наследуем свойстdо FontFamily
        heading = this.addStyle(STYLE_heading, normal);
        StyleConstants.setFontSize(heading, 18);
        StyleConstants.setBold(heading, true);
        StyleConstants.setBackground(heading, new Color(171, 171, 171));
    }
    /**
     * Процедура загрузки текста в редактор
     */
    void loadText(List<String> list)
    {
        listCom = list;
        // Загружаем в документ содержимое
        for (String s : list) {
            Style style = (s.startsWith("H")) ? heading : normal;
            insertText(s+"\n", style);
        }
        //  Определение функции для зменение стиля части текста
        changeDocumentStyle();
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

    boolean isComments(int line){

        if(listCom.get(line-1).charAt(0) == ';') return true;
        else return false;
    }

}

