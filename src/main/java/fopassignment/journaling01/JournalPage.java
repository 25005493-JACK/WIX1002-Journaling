//ChengYingChenStart
package fopassignment.journaling01;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class JournalPage extends User{
    public static void Journal(){
        Scanner s = new Scanner(System.in);
        LocalDate jourDate = LocalDate.now();
        LocalDate regisDate = LocalDate.of(2025, 11, 1); //will set it later to real registration date according to each user 
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = regisDate.format(DTF);
         
        do
        {
            int day = 1;
            System.out.println("*******Journal Dates*******");
            LocalDate rD = regisDate;
            while(rD.isBefore(jourDate.plusDays(1)))
            {
                System.out.println(day + ". " + rD);
                rD = rD.plusDays(1);
                day ++;
            }

            System.out.println("Select date to view journal or create a journal.Enter 0 if you want to log out.");
            System.out.print("> ");
            int userC = s.nextInt();
            
            if(userC == 0) 
                {
                    System.out.println("Logged out...");
                    break; 
                }
            
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
                System.out.println("--- Journal Entry for " + userCDate + "---");
                String journalContent = j.readJ(userCDate);
                System.out.println(journalContent);
                System.out.println("\n\nWould you like to: \n" + "1. Edit This Journal \n" + "2. Back to Dates \n" );
                System.out.print("> ");
                int menuC = s.nextInt();
                s.nextLine();
                if(menuC == 1)
                {
                    System.out.println("Edit your journal entry for " + userCDate + " :");
                    String jConE = s.nextLine();
                    
                    //LeeXinYiStart
                    // read old jornal content for the selected date
                    String oldContent = journalContent;
                    
                    // find "\n\n" which separates weather from journal text
                    int firstNewLine = oldContent.indexOf("\n\n");
                    String weatherLine;
                    
                    // if double new line exists, extract the weather line;
                    if (firstNewLine != -1) {
                        weatherLine = oldContent.substring(0, firstNewLine);
                    } else {
                        weatherLine = "Weather: Unavailable";
                    }
                    
                    String finalJournal = weatherLine + "\n\n" + jConE;
                    //LeeXinYiEnd
                    
                    j.createJ(userCDate,finalJournal);
                    System.out.println("Journal edited and saved.\n");
                }else if(menuC !=2 || menuC !=1)
                {
                    System.out.println("Invalid entry. Please enter 1 or 2.");
                    System.out.println("\n\nWould you like to: \n" + "1. Edit This Journal \n" + "2. Back to Dates \n" );
                    System.out.print("> ");
                    menuC = s.nextInt();
                }
                

            } 
            else 
            {
                System.out.println("No journal yet.");
                s.nextLine();
                System.out.println("Enter your journal entry for " + userCDate +" :");
                String jCon = s.nextLine();
                
                //LeeXinYiStart
                // add weather
                WeatherRecording WR = new WeatherRecording();
                String weather = WR.getTodayWeather();
                
                String finalJournal = "Weather: " + weather + "\n\n" + jCon;
                //LeeXinYiEnd
                
                j.createJ(userCDate, finalJournal );
                System.out.println("Journal saved successfully!\n"); 
            }

       
        }while(true);
        
    }
}
//ChengYingChenEnd
