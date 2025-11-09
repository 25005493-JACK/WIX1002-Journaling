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

public class WelcomingPage extends User {
    public static boolean Login(){ //compare user input with data in csv/txt file
        Scanner sc = new Scanner(System.in);
        User user = new User();
        List <String[]> txtdata = new ArrayList<>();
        txtdata = user.txtfileReader(); 
            
        System.out.println("Welcome back! \nPlease enter the details to login.");
        System.out.println("Enter user email:");
        email = sc.next();
        
        System.out.println("Enter password:");
        pw = sc.next();
        
        for (String[]userdata: txtdata){
            temail = userdata[0];
            tname = userdata[1];
            tpw = userdata[2];
                
            if (email.equals(temail) && pw.equals(tpw)){
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
            
            else if(email.equals(temail) && !pw.equals(tpw)){
                System.out.println("Wrong password. Please login again.");
                return false;
            }
        }
        System.out.println("No data found, Please Register.");
        WelcomingPage WP = new WelcomingPage();
        WP.Register(); 
        return false;
    }           
    
    
    public static void Register() { //no return, save everything to csv/txt
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
        else {
            System.out.println("\nYour password and confirm password does not match. Please try again.");
            WP.Register(); //Java Recursion
        }
    }
}
//TanWeiFengEnd