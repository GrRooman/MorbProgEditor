
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;
import java.util.ArrayList;

class CaretClass
{
    JTextArea text;
    Caret caret;
    public CaretClass(){
        // Создание курсора
        caret = text.getCaret();
        // Выделение текста
//		caret.setDot ( 7);
//		caret.moveDot(15);
        // Частота мерцания курсора
        //	caret.setBlinkRate(50);

        // Вырезаем текст и выводим его в консоль
        String temp = text.getText().substring(caret.getDot());
        System.out.println (temp);
        temp = text.getText().substring(caret.getMark(), caret.getDot());
        System.out.println (temp);
        // Выводим окно
        caret.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                text.append(String.valueOf(caret.getDot()));
            }
        });
    }
}


