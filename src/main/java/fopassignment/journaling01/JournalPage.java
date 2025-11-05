//TanWeiFengStart
package fopassignment.journaling01;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
public class JournalPage {
    public static void Journal(){
        LocalDate myDate = LocalDate.now();
        LocalTime myTime = LocalTime.now();
        int hour = myTime.getHour();
                
        if (hour>=00 && hour<12)
            System.out.println("Good Morning!");
        else if (hour>=12 && hour<17)
            System.out.println("Good Afternoon!");
        else 
            System.out.println("Good Evening");
        
        System.out.println("Today is " + myDate);
        System.out.println("The time now is " + myTime);
//TanWeiFengEnd
//ChengYingChenStart
        LocalDate jourDate = LocalDate.now();
        LocalDate regisDate = LocalDate.of(2025, 11, 1); //will set it later to real registration date according to each user 
        LocalDate rD = regisDate;
        int day = 1;
        System.out.println("*******Journal Dates*******");
        while(rD.isBefore(jourDate.plusDays(1)))
        {
            System.out.println(day + ". " + rD);
            rD = rD.plusDays(1);
            day ++;
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("Select date to view journal or create a journal for today.");
        int UserC = s.nextInt();
        while(true)
        {
            if(UserC >= 1 && UserC < day)
            {
                break;
            }
            else
            {
                System.out.println("Invalid date. Please choose again.");
                UserC = s.nextInt();
            }
        }

//ChengYingChenEnd
    
    }


    
}

