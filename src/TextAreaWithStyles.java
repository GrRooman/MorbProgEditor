import javax.swing.*;
import java.awt.Color;
import java.util.List;
import javax.swing.text.*;




class TextAreaWithStyles extends JTextPane{
    // Стили редактора
    private  Style     heading    = null; // стиль заголовка
    private  Style     normal     = null; // стиль текста
    private  final  String STYLE_heading = "heading",
            STYLE_normal  = "normal" ,
            FONT_style    = "Times New Roman";
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
            Document doc = this.getDocument();
            doc.insertString(doc.getLength(), string, style);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getStyleText(){
         return this.getText();
    }
    @Override
    public boolean getScrollableTracksViewportWidth(){
        return getUI().getPreferredSize(this).width<=getParent().getSize().width;
    }

}

