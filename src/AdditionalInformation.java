
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



class AdditionalInformation {
    private Color errorColor;
    private File filePath;
    private ReadFile rf;
    public AdditionalInformation(File file) {
        filePath = file;
        rf = new ReadFile();
    }
    ArrayList<String> getDataFromAdditionalFiles(){
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(parseText(rf.readFile(ExtensionHelper.changeNameExtension(filePath, "lst"))));
            arrayList.add(parseText(rf.readFile(ExtensionHelper.changeNameExtension(filePath, "inf"))));
            return arrayList;
    }
    private String parseText(List<String> list){
        String str = "";
//        for(String s: list){
//            String[]arr = s.split("   ");
//        }
        str = list.stream().filter(x->!x.isEmpty()).map(x -> x+"\n").reduce(str, String::concat);
        return str;
    }
    Color getColorOfError(){
        if(true) errorColor = new Color(100, 200, 100);
        else errorColor = new Color(200, 100, 100);
        return errorColor;
    }   
    
}
