//TanWeiFeng
package fopassignment.journaling01;
import java.util.Scanner;
public class Main{
    public static void main(String[]args){
        StartUpPage SUP = new StartUpPage();
        WelcomingPage WP = new WelcomingPage();
        int LR = SUP.Intro();
        //System.out.println(LR); //to check LR 
        
        boolean logout = false; //loop till logout
        outerloop:
        while (logout == false){
            if (LR == 1){
                WP.Login();
                break outerloop;
            }
            else if (LR == 2){
                WP.Register();
                break outerloop;
            }
            System.out.println("Do you wish to logout?");
            System.out.println("");
        }  
        
    }
}
