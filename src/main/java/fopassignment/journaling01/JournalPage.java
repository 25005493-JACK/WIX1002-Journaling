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
            System.out.println("Enter -1 for Weekly Summary"); //LeeXinYi  
            System.out.print("> ");
            int userC = s.nextInt();
            
            if(userC == 0) 
                {
                    System.out.println("Loading...");
                    break; 
                } 
                else if (userC == -1) { //LeeXinYi
                    weeklySummary();
                    continue;
            }

            while(true)
            {
                if(userC >= 1 && userC < day)
                {
                    break;
                }
                else if (userC == -1) { //LeeXinYi
                    break;
                }
                else
                {
                    System.out.println("Invalid date. Please choose again.");
                    userC = s.nextInt();
                    s.nextLine();
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
                    String oldHeader;
                    int splitIndex = journalContent.indexOf("\n\n");
                    
                    if (splitIndex != -1) {
                        oldHeader = journalContent.substring(0, splitIndex);
                    } else {
                        oldHeader = "Weather: Unavailable\nMood: Unknown";
                    }
                    
                    String finalJournal = oldHeader + "\n\n" + jConE;
//LeeXinYiEnd
     
                    j.createJ(userCDate,finalJournal);
                    System.out.println("Journal edited and saved.\n");
                }else if(menuC !=2 && menuC !=1)
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
                // get weather data
                WeatherRecording WR = new WeatherRecording();
                String weather = WR.getTodayWeather();
                System.out.println("Weather: " + weather);
                
                // get mood/sentiment data
                MoodClassification MC = new MoodClassification();
                String mood = MC.classifySentiment(jCon);
                System.out.println("Mood: " + mood);
                
                String header = "Weather: " + weather + "\nMood: " + mood;
                String finalJournal = header + "\n\n" + jCon;
//LeeXinYiEnd
                
                j.createJ(userCDate, finalJournal);
                System.out.println("Journal saved successfully!\n"); 
            }

       
        }while(true);
        
    }
    
//ChengYingChenEnd

//LeeXinYiStart    
    public static void weeklySummary() {
        JournalPageFH j = new JournalPageFH();
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE, yyyy-MM-dd");
        
        System.out.println("\n*******Weekly Summary*******");
        System.out.println("From " + today.minusDays(6) + " to " + today + "\n");
        
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            
            System.out.println(date.format(fmt));
            
            if (j.JCExist(date)) {
                String content = j.readJ(date);
                
                String weather = extractField(content, "Weather: ");
                String mood = extractField(content, "Mood: ");
                
                System.out.println("Weather: " + weather);
                System.out.println("Mood: " + mood + "\n");
                
            } else {
                System.out.println("No journal\n");
            }
        }
    }
    
    private static String extractField(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) {
            return "Unavailable"; 
        }
        
        start += key.length();
        
        int end = text.indexOf("\n", start);
        if (end == -1) {
            return text.substring(start).trim();
        }
        
        return text.substring(start, end).trim();
    }
}
//LeeXinYiEnd
