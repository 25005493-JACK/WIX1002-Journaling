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
                    
                    String sameWeather = jM.getWeather();
                  
                    MoodClassification MC = new MoodClassification();
                    String newMood = MC.classifySentiment(jConE);
                            
                    JournalModel editjM = new JournalModel(currentUserId, userCDate, newMood, sameWeather, jConE);
                    dH.editJtoDB(editjM);

                    String header = "Weather: " + sameWeather + "\nMood: " + newMood;
                    String finalJournal = header + "\n\n" + jConE;
                    j.createJ(userCDate, finalJournal); 
                    
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
<<<<<<< HEAD

                String weather = "Xinyi you can call your api here.getWeather()"; 
                String mood = "mood here";       
                
                
=======
                 
                
//LeeXinYiStart
                // get weather data
                WeatherRecording WR = new WeatherRecording();
                String weather = WR.getTodayWeather();
                System.out.println("Weather: " + weather);
                
                // get mood/sentiment data
                MoodClassification MC = new MoodClassification();
                String mood = MC.classifySentiment(jCon);
                System.out.println("Mood: " + mood);
             }
//LeeXinYiEnd

>>>>>>> 20c8c5ef9db59394548aa47466c4022c5e78313c
                JournalModel createJM = new JournalModel(currentUserId, userCDate, mood, weather, jCon);

                boolean savetoDB = dH.createJtoDB(createJM); 

                if (savetoDB) 
                {
        //LeeXinYiStart
                    String header = "Weather: " + weather + "\nMood: " + mood;
                    String finalJournal = header + "\n\n" + jCon;
                    j.createJ(userCDate, finalJournal); 
        //:eeXinYiEnd
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
