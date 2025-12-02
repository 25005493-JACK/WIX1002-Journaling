//ChengYingChenStart
package fopassignment.journaling01;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class JournalPage extends User{
    public static void Journal(){
        Scanner s = new Scanner(System.in);
        LocalDate jourDate = LocalDate.now();
        //LocalDate regisDate = LocalDate.of(2025, 11, 1); //will set it later to real registration date according to each user 
        LocalDate regisDate = User.createdD;
        if (regisDate == null) 
        {
            regisDate = LocalDate.now(); 
        }
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
                    System.out.println("Loading...");
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

            JournalDataHandling dH = new JournalDataHandling();
            int currentUserId = User.userId;
            JournalPageFH j = new JournalPageFH();
            LocalDate userCDate = regisDate.plusDays(userC - 1);
            
            JournalModel jM = dH.getJournalByDate(currentUserId, userCDate);
            
            
            if(jM != null) 
            {
                
                System.out.println("Journal exists");
                System.out.println("--- Journal Entry for " + userCDate + "---");
                System.out.println("Weather: " + jM.getWeather()); 
                System.out.println("Mood: " + jM.getMood()); 
                System.out.println("Content: " + jM.getContent()); 
                System.out.println("\n\nWould you like to: \n" + "1. Edit This Journal \n" + "2. Back to Dates \n");
                System.out.print("> ");
                int menuC = s.nextInt();
                s.nextLine();
                
                if(menuC == 1)
                {
                    System.out.println("Edit your journal entry for " + userCDate + " :");
                    String jConE = s.nextLine();
                    
                    String newWeather = "Xinyi you can call your api here.getWeather()"; 
                    String newMood = "mood here"; 
                    
                    JournalModel editjM = new JournalModel(currentUserId, userCDate, newMood, newWeather, jConE);
                    dH.editJtoDB(editjM);

                    String topLine = "Weather: " + newWeather + "\nMood: " + newMood;
                    j.createJ(userCDate, topLine + "\n\n" + jConE);
                    
                    System.out.println("Journal edited and saved.\n");
                    
                }
                else if(menuC !=2 && menuC !=1)
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

                String weather = "Xinyi you can call your api here.getWeather()"; 
                String mood = "mood here";       
                
                
                JournalModel createJM = new JournalModel(currentUserId, userCDate, mood, weather, jCon);

                boolean savetoDB = dH.createJtoDB(createJM); 

                if (savetoDB) 
                {
                    String topLine = "Weather: " + weather + "\nMood: " + mood;
                    j.createJ(userCDate, topLine + "\n\n" + jCon); 

                    System.out.println("Journal saved successfully to database and file!");
                } 
                else 
                {
                    System.out.println("Database save failed.");
                }
            }
        }while(true);
    }   
}
//ChengYingChenEnd
