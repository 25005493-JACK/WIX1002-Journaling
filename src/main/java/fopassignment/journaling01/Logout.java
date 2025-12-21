//TanWeiFengStart
package fopassignment.journaling01;
import java.util.Scanner;
public class Logout {
    public static boolean logout(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Confirm logout?");
        System.out.println("\n1.Logout \t\t\t 2.Stay");
        System.out.print("\nEnter 1 to logout and enter 2 to stay.\n>");
        int LO = sc.nextInt();
            secondloop:
                while (true)
                    if (LO == 1){
                        System.out.println("\nLogged out. Thanks for using XXX Journaling. Have a nice day!");
                        return false;
                    }
                    else if (LO == 2){
                        System.out.println("\nTransmitting to journal page...\tLoading...\n");
                        return true;
                    }
                    else {
                        System.out.println("Invalid input.");
                        System.out.println("\nPress 1 to logout and press 2 to stay.");
                        LO = sc.nextInt();
                    }
    }
}
//TanWeiFengEnd
