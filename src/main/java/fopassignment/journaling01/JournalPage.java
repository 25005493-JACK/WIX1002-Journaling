//TanWeiFengStart
package fopassignment.journaling01;
import java.time.LocalDate;
import java.time.LocalTime;
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



//ChengYingChenEnd
    
    }
}
