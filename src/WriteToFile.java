import java.io.*;

/**
 * User: Grooman
 * Date: 24.08.21
 * Time: 11:05
 */
class WriteToFile {
    BufferedWriter br;
    WriteToFile(){


    }
    void saveTextInFile(){
        try(BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("we.txt")))){
            br.write("sdfsdfsdfsd");

        }catch(IOException e){
            System.out.println(e);
        }
       // try{


       // }catch (IOException e) {};
    }
}
