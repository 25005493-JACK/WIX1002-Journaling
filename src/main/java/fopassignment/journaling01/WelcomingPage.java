//TanWeiFengStart
package fopassignment.journaling01;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

public class WelcomingPage extends User {
    public static boolean Login(){ //compare user input with data in csv/txt/sql file
        UserDAO dao = new UserDAO();
        Scanner sc = new Scanner(System.in);
        User user = new User();
        pwHashing pwh = new pwHashing();
        List <String[]> txtdata = new ArrayList<>();
        txtdata = user.txtfileReader();
        System.out.println("\nWelcome back! \nPlease enter the details to login.");
        System.out.println("Enter user email:");
        email = sc.next();
        
        System.out.println("Enter password:");
        pw = sc.next();
        
        name = dao.getUserByEmail(email);
        if (name == null){
            System.out.println("No data found, Please Register.");
            WelcomingPage WP = new WelcomingPage();
            WP.Register(); 
            return false;
        }
        for (String[]userdata: txtdata){//for each loop 
            temail = userdata[0];
            tname = userdata[1];
            tpw = userdata[2];
            String hashedpw = dao.getPwByEmail(email);
            
            if (email.equals(temail) && pwh.verifyPassword(pw, hashedpw) && name.equals(tname)){ //compare & crosscheck user input email pw username with txt n sql file
//ChengYingChenStarts                
                JournalDataHandling jDH = new JournalDataHandling();
       
                int getUId = jDH.getUserIdByEmail(email); 

                LocalDate regisD = dao.getCreatedDByEmail(email); 

                if (getUId > 0) {
                    User.userId = getUId; // Store the ID (e.g., 1 or 3)
                } else {
                    return false;
                }
                
                if (regisD != null) 
                {
                    User.createdD = regisD; 
                } 
                else 
                {                    
                    User.createdD = LocalDate.now(); 
                }
//ChengYingChenEnds
                LocalDate jourDate = LocalDate.now();
                LocalTime Time = LocalTime.now();
                DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("HH:mm:ss");
                String jourTime = Time.format(formatter24Hour);
                int hour = Time.getHour();
                
                if (hour>=00 && hour<12)
                    System.out.println("\nGood Morning!");
                else if (hour>=12 && hour<17)
                    System.out.println("\nGood Afternoon!");
                else 
                    System.out.println("\nGood Evening");
        
                System.out.println("Welcome back, "+ tname +"!");
                System.out.println("Today is " + jourDate);
                System.out.println("The time now is " + jourTime);
                return true;
            }            
            
            else if(email.equals(temail) && !pwh.verifyPassword(pw, hashedpw)){
                System.out.println("Wrong password. Please login again.");
                return false;
            }
        }
        System.out.println("No data found, Please Register.");
        WelcomingPage WP = new WelcomingPage();
        WP.Register(); 
        return false;
    }           
    
    
    public static void Register() { //no return, save everything to csv/txt and sql
        Scanner sc = new Scanner(System.in);
        WelcomingPage WP = new WelcomingPage();
        
        System.out.println("\nWelcome to XXX Journaling!");
        System.out.println("Enter your name:");
        name = sc.nextLine();
        
        System.out.println("Enter your email address:");
        email = sc.nextLine();
        
        System.out.println("Set your password:");
        String pw1 = sc.next();
        System.out.println("Confirm your password:");
        String pw2 = sc.next();
              
        if (pw1.equals(pw2)){
            pw = pw1;         
            boolean success;
            try (Connection conn = DBConnection.getConnection()) {
                System.out.println("\nDatabase connected successfully!");
                pwHashing pwh = new pwHashing();
                String hashedpw = pwh.hashPassword(pw);
                UserDAO dao = new UserDAO();
                success = dao.saveUser(userId, name, email, hashedpw);//save data to sql database
                if (success){//only save to txt after save to sql
                    System.out.println("User saved!");
                    String txtFOP = System.getProperty("user.dir")+"\\data\\UserData.txt";//check if txt file exist, if not, create
            
                    try(
                        FileWriter writer = new FileWriter(txtFOP, true);
                        PrintWriter pwriter = new PrintWriter(writer);
                    ){
                        pwriter.println(email + "\n" + name + "\n" + pw + "\n" + " ");
                    }
                    catch (IOException e){
                        System.out.println("Error in register.");
                        e.printStackTrace();
                    }
                    //save all variable to txt here
                }
                else{
                    System.out.println("\nFail to connect database.");
                    System.out.println("This email has already linked to an account.\nPlease register using another email or try login again.");
                    while (true){
                        System.out.println("Enter 1 to try login again and enter 2 to register using another email.");
                        int relogin=sc.nextInt();
                        if (relogin== 1){
                            return;
                        }
                        else if (relogin ==2){
                            break;
                        }
                        else{
                            System.out.print("Invalid. ");
                        }
                    }
                    WP.Register(); //Java Recursion
                }
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("\nYour password and confirm password does not match. Please try again.");
            WP.Register(); //Java Recursion
        }
    }
}
//TanWeiFengEnd
