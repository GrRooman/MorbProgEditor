
import java.util.prefs.Preferences;
import javax.swing.JFrame;




public class UserPreferences {
    private final Preferences p;
    public UserPreferences(){
        p = Preferences.userRoot().node("programeditor");
    }
    // Методы, чтобы установить и получить размеры основного окна
    void setSizeFrame(JFrame f){
        p.putInt("height", f.getHeight());
        p.putInt("width", f.getWidth());
    }
    int[] getSizeFrame(){
        int height = p.getInt("height", 300);
        int width = p.getInt("width", 300);
        int[] a = {width,height};
        return a; 
    }
    // Методы, чтобы установить и получить путь к конвертеру   
    void setPathToConver(){
        
    }
    String getPathToConverter(){
        
        return "";
    }
    // Методы, чтобы установить и получить путь к управляющим программам
    void setPathToControlProgram(){
        p.put("pathToControlProgram", "C:\\qw\\java\\MorbProgEditor\\src\\resources");
        
    }
    String getPathToControlProgram(){
        String s = p.get("pathToControlProgram", ".");
        return s;
    }
    
    
}
