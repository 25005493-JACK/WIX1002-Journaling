//TanWeiFengStart
//Start Up Page, display before user login or register
package fopassignment.journaling01;
import java.util.Scanner;

public class StartUpPage{
    public static int Intro(){
        Scanner sc = new Scanner(System.in);
        int LR;
        
        System.out.println("Welcome to XXX Journaling!");
        System.out.println("In conjunction of Sustainable Development Goals(SDGs) Goal 3: mental health and well-being,");
        System.out.println("XXX Journaling is here to help you to record your daily life track along with your emotions.");
        System.out.println("This may help you to have a better understanding if your emotion patterns and reflect your own daily life.");
        System.out.println("Enjoy your journaling process with XXX Journaling!");
        System.out.println("\n1.Login \t\t\t 2.Register");
        
        System.out.print("\nEnter 1 to login and enter 2 to register as new user.\n>");
        LR = sc.nextInt();
        
        outerloop:
        while (true){
            switch(LR){
                case 1:{
                    System.out.println("\nLoading...\tLogin Page...\n");
                    break outerloop;
                }
                case 2:{
                    System.out.println("\nLoading...\tRegister Page\n");
                    break outerloop;
                }
                default: {
                    System.out.print("\nEnter 1 to login and enter 2 to register as new user.\n>");
                    LR = sc.nextInt();
                }
            }
        }       
        
        
        return LR;
        
    }
} 
//TanWeiFengEnd