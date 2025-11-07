//TanWeiFengStart
package fopassignment.journaling01;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class User {
    static int userID;
    static String email, pw, name;
    static String csvemail, csvpw, csvname;
    static String temail, tpw, tname;
    
    public static List <String[]> fileReader(){
        String csvFOP =System.getProperty("user.dir")+"\\data\\UserData.txt";
        try(
                FileWriter writer = new FileWriter(csvFOP, true);
                //if first user don't have txt file,this will create first if first user didn't go to register first
            ){}
        catch (IOException e){
                System.out.println("Error in file reader reaching file.");
                e.printStackTrace();
        }
        
        List <String[]> userdata = new ArrayList<>();
                
        try(
            FileReader reader = new FileReader(csvFOP);
            BufferedReader bReader = new BufferedReader(reader);   
        ){
            while((csvemail = bReader.readLine()) !=null){
                if (csvemail.trim().isEmpty())
                    continue;
                csvname = bReader.readLine();
                csvpw = bReader.readLine();
                userdata.add(new String[]{csvemail, csvname, csvpw});
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