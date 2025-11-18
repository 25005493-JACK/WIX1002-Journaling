//ChengYingChenStart
package fopassignment.journaling01;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class JournalPage extends User{
    public static void Journal(){
        LocalDate jourDate = LocalDate.now();
        LocalDate regisDate = LocalDate.of(2025, 11, 1); //will set it later to real registration date according to each user 
        LocalDate rD = regisDate;
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = regisDate.format(DTF);
                
        int day = 1;
        System.out.println("*******Journal Dates*******");
        while(rD.isBefore(jourDate.plusDays(1)))
        {
            System.out.println(day + ". " + rD);
            rD = rD.plusDays(1);
            day ++;
        }
        
        Scanner s = new Scanner(System.in);
        System.out.println("Select date to view journal or create a journal.");
        System.out.print("> ");
        int userC = s.nextInt();

        while(true)
        {
            if(userC >= 1 && userC < day)
            {
                break;
            }
            else
            {
                System.out.println("Invalid date. Please choose again.");
                userC = s.nextInt();
            }
        }
        
        JournalPageFH j = new JournalPageFH();
        LocalDate userCDate = regisDate.plusDays(userC - 1);

        if(j.JCExist(userCDate))
        {
            System.out.println("Journal exists");
        
        } 
        else 
        {
            System.out.println("No journal yet.");
            s.nextLine();
            System.out.println("Enter your journal:");
            String jCon = s.nextLine();
            j.createJ(userCDate, jCon );
            System.out.println("Journal created and saved successfully!"); 
        }
       
//ChengYingChenEnd
    
    }


    
}

