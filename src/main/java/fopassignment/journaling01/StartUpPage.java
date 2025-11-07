//TanWeiFengStart
package fopassignment.journaling01;
import java.util.Scanner;

public class StartUpPage {
    public static int Intro(){
        Scanner sc = new Scanner(System.in);
        int LR;//LR=LoginRegis
        
        //User friendly message
        //String WelcomeText ="Welcome to XXX Journaling"; 
        //System.out.println("\033[0;1m" + WelcomeText + "\033[0;0m"); //bold text is not shown in netbeans
        System.out.println("Welcome to XXX Journaling!");
        System.out.println("In conjunction of Sustainable Development Goals(SDGs) Goal 3: mental health and well-being,");
        System.out.println("XXX Journaling is here to help you to record your daily life track along with your emotions.");
        System.out.println("This may help you to have a better understanding if your emotion patterns and reflect your own daily life.");
        System.out.println("Enjoy your journaling process with XXX Journaling!");
        System.out.println("\n1.Login \t\t\t 2.Register");
        
        System.out.println("\nPress 1 to login and press 2 to register as new user.");
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
                    System.out.println("\nPress 1 to login and press 2 to register as new user.");
                    LR = sc.nextInt();
                }
            }//java switch
        }       
        
        
        return LR;
        
    }
} 
//TanWeiFengEnd
//completed prompt user choose login or register and return to main *3/11/2025