//TanWeiFengStart
package fopassignment.journaling01;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WelcomingPage extends User {
    public static boolean Login(){ //compare user input with data in csv/txt file
        Scanner sc = new Scanner(System.in);
        User user = new User();
        List <String[]> csvdata = new ArrayList<>();
        csvdata = user.fileReader(); 
            
        System.out.println("Welcome back! \nPlease enter the details to login.");
        System.out.println("Enter user email:");
        email = sc.next();
        
        System.out.println("Enter password:");
        pw = sc.next();
        
        for (String[]userdata: csvdata){
            temail = userdata[0];
            tname = userdata[1];
            tpw = userdata[2];
                
            if (email.equals(temail) && pw.equals(tpw)){
                System.out.println("\nHello "+ tname);
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
            String csvFOP = System.getProperty("user.dir")+"\\data\\UserData.txt";//check if csv/txt file exist, if not, create
            
            try(
                FileWriter writer = new FileWriter(csvFOP, true);
                PrintWriter pwriter = new PrintWriter(writer);
            ){
                pwriter.println(email + "\n" + name + "\n" + pw + "\n" + " ");
            }
            catch (IOException e){
                System.out.println("Error in register.");
                e.printStackTrace();
            }
            //save all variable to csv/txt here
        }
        else {
            System.out.println("\nYour password and confirm password does not match. Please try again.\n");
            WP.Register(); //Java Recursion
        }
    }
}
//TanWeiFengEnd
