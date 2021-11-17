
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



class AdditionalInformation {
    private ArrayList<Color> arrErrorColor;
    private File filePath;
    private ReadFile rf;
    private ArrayList<String> listAdditFiles;
    private Color colorErrorFree;
    private Color colorHaveError;
        AdditionalInformation(File file) {
            filePath = file;
            arrErrorColor = new ArrayList<>();
            rf = new ReadFile();
            listAdditFiles = new ArrayList<>();
            colorErrorFree = new Color(100, 200, 100);
            colorHaveError = new Color(224, 100, 89);
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
                if(s.charAt(n+8) == '0') arrErrorColor.add(colorErrorFree);
                else arrErrorColor.add(colorHaveError);
            }
            if(s.indexOf("[ERRORS]") != -1){
                int n = s.indexOf("[ERRORS]");
                if(s.charAt(n+9) == '0') arrErrorColor.add(colorErrorFree);
                else arrErrorColor.add(colorHaveError);
            }
        }
        return arrErrorColor;
    }
    
}
