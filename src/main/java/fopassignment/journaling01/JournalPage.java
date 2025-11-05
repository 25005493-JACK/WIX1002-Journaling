//TanWeiFeng
package fopassignment.journaling01;
import java.time.LocalDate;
import java.time.LocalTime;
public class JournalPage {
    public static void Journal(){
        LocalDate myDate = LocalDate.now();
        LocalTime myTime = LocalTime.now();
        System.out.println("Today is " + myDate);
        System.out.println("The time now is " + myTime);
            
    }
}
