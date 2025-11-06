//TanWeiFengStart
package fopassignment.journaling01;
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.concurrent.atomic.AtomicInteger;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
        
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;
public class User {
        
    static int userID;
    static String email;
    static String pw;
    static String name;
    
//    private static final Path UserDataPath = Paths.get("UserData.csv");
//    private static AtomicInteger idcounter;
//    
//    private static AtomicInteger prevNum(){
//        int initialValue = 0;
//        if (Files.exists(UserDataPath)){
//            try{
//                String prev = Files.readString(UserDataPath).trim();
//                initialValue = Integer.parseInt(prev);
//            }catch (IOException | NumberFormatException e){
//                System.err.println("Error" + e.getMessage());
//            }
//            return idcounter = new AtomicInteger(initialValue);      
//        }
//        else
//            return idcounter = new AtomicInteger();
//        
//    }
//    
//    public static int updateID(){
//        User readPrevNum = new User();
//        AtomicInteger id = readPrevNum.prevNum();
//        return id.incrementAndGet();
//    } 

    
    public static List <String[]> fileReader(){
        String csvFOP =System.getProperty("user.dir")+"\\data\\UserData.txt";
        List <String[]> userdata = new ArrayList<>();
                
        try(
            FileReader reader = new FileReader(csvFOP);
            BufferedReader bReader = new BufferedReader(reader);   
        ){
            String line;
            while((line = bReader.readLine())!=null){
                String [] values = line.split("\n");
                userdata.add(values);
            }
        }
        
        catch(IOException e){
            System.out.println("Error in file reader.");
            e.printStackTrace();
        }
        return userdata;
                
    }
            
    
        
    public static boolean emailCheck(){
        return true;
        
    }
}
//TanWeiFengEnd