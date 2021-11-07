
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;




class UserPreferences {
    private final Preferences p;
    public UserPreferences(){
        p = Preferences.userRoot().node("morbprogeditor");

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
    void setPathToConver(String pathToConver){
        p.put("pathToConverter", pathToConver);
    }
    String getPathToConverter(){
        String s = p.get("pathToConverter", "");
        return s;
    }

    // Методы, чтобы установить и получить путь к управляющим программам
    void setPathToControlProgram(String pathToControlProgram){
        p.put("pathToControlProgram", pathToControlProgram);
        
    }
    String getPathToControlProgram(){
        String s = p.get("pathToControlProgram", "");
        return s;
    }
    void clearUserPreferences(){
        try {
            p.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }
  
    
}
