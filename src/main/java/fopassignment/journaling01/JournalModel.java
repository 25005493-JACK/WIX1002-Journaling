package fopassignment.journaling01;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JournalModel {

        private int journalId;   
        private int userId;      
        private LocalDate entryDate; 
        private String mood;       
        private String weather;    
        private String content;    
        private LocalDateTime createdAt;
        
        public JournalModel(int userId, LocalDate entryDate, String mood, String weather, String content)
        {
            this.userId = userId;
            this.entryDate = entryDate;
            this.mood = mood;
            this.weather = weather;
            this.content = content;
        }
        
        public JournalModel(int journalId, int userId, LocalDate entryDate, String mood, String weather, String content, LocalDateTime createdAt)
        {
           this.journalId = journalId;
           this.userId = userId;
           this.entryDate = entryDate;
           this.mood = mood;
           this.weather = weather;
           this.content = content;
           this.createdAt = createdAt;
        }
        
        public int getJournalId() 
        {
            return journalId; 
        }
        
        public int getUserId()
        { 
            return userId; 
        }
        
        public LocalDate getEntryDate() 
        { 
            return entryDate; 
        }
       
        public String getMood() 
        { 
            return mood; 
        }
        
        public String getWeather()
        {
            return weather;
        }
        
        public String getContent()
        {
            return content;
        }
        
        public LocalDateTime getCreatedAt()
        {
            return createdAt;
        }
        
       
        
}


