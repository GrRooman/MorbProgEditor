
import java.io.*;


import java.io.FileOutputStream;
import java.io.IOException;




class WriteToFile {
    FileOutputStream fos;
    WriteToFile(){
        
    }
    void saveDataInFile(String fname,String strdata){
        try{
            fos = new FileOutputStream(fname);
            byte[] b = strdata.getBytes();
            fos.write(b); 
            
        } catch(IOException e){
            System.out.println(e);
        }
        //finally
    }

}
