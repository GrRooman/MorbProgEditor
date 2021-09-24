
import java.util.prefs.Preferences;
import javax.swing.JFrame;




class UserPreferences {
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
        int height = p.getInt("height", 500);
        int width = p.getInt("width", 500);
        int[] a = {width,height};
        return a; 
    }
    // Методы, чтобы установить и получить путь к конвертеру   
    void setPathToConver(){
        p.put("pathToConverter", "C:\\qw\\java\\MorbProgEditor\\src\\resources\\Bin");
    }
    String getPathToConverter(){
        String s = p.get("pathToConverter", ".");
        return s;
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
