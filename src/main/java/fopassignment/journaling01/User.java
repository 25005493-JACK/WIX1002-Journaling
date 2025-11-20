//TanWeiFengStart
package fopassignment.journaling01;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class User {
    static int userId=000001;//change later
    static String email, pw, name;
    static String txtemail, txtpw, txtname;
    static String temail, tpw, tname;
    static String content, date, weather=null, mood=null; 
    
    public static List <String[]> txtfileReader(){
        String txtFOP =System.getProperty("user.dir")+"\\data\\UserData.txt";
        try(
                FileWriter writer = new FileWriter(txtFOP, true);
                //if first user don't have txt file,this will create first if first user didn't go to register first
            ){}
        catch (IOException e){
                System.out.println("Error in file reader reaching file.");
                e.printStackTrace();
        }
        
        List <String[]> userdata = new ArrayList<>();
                
        try(
            FileReader reader = new FileReader(txtFOP);
            BufferedReader bReader = new BufferedReader(reader);   
        ){
            while((txtemail = bReader.readLine()) !=null){
                if (txtemail.trim().isEmpty())
                    continue;
                txtname = bReader.readLine();
                txtpw = bReader.readLine();
                userdata.add(new String[]{txtemail, txtname, txtpw});
            }
        }
        catch(IOException e){
            System.out.println("Error in file reader.");
            e.printStackTrace();
        }
        return userdata;        
    }
}
//TanWeiFengEnd