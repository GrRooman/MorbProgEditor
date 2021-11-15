
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



class AdditionalInformation {
    private ArrayList<Color> errorColor;
    private File filePath;
    private ReadFile rf;
    private ArrayList<String> listAdditFiles;
    public AdditionalInformation(File file) {
        filePath = file;
        rf = new ReadFile();
        errorColor = new ArrayList<>();
        listAdditFiles = new ArrayList<>();
    }
    ArrayList<String> getDataFromAdditionalFiles(){
        listAdditFiles.add(parseText(rf.readFile(ExtensionHelper.changeNameExtension(filePath, "lst"))));
        listAdditFiles.add(parseText(rf.readFile(ExtensionHelper.changeNameExtension(filePath, "inf"))));
        return listAdditFiles;
    }
    private String parseText(List<String> list){
        String str = "";
//        for(String s: list){
//            String[]arr = s.split("   ");
//        }
        str = list.stream().filter(x->!x.isEmpty()).map(x -> x+"\n").reduce(str, String::concat);
        return str;
    }

    List<Color> getColorOfError(){
        for(String s: listAdditFiles){
            if(s.indexOf("Errors") != -1) {
                int n = s.indexOf("Errors");
                if(s.charAt(n+8) == '0') errorColor.add(new Color(100, 200, 100));
                else errorColor.add(new Color(200, 36, 34));
            }
            if(s.indexOf("[ERRORS]") != -1){
                int n = s.indexOf("[ERRORS]");
                if(s.charAt(n+9) == '0') errorColor.add(new Color(100, 200, 100));
                else errorColor.add(new Color(200, 36, 34));
            }
        }
        return errorColor;
    }
    
}
