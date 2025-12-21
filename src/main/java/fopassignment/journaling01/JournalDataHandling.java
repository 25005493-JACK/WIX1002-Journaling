//ChengYingChenStart
package fopassignment.journaling01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JournalDataHandling {
    
    public boolean createJtoDB(JournalModel jM) 
    {
        String sql = "INSERT INTO journals (user_id, entry_date, content, mood, weather) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {
            
            ps.setInt(1, jM.getUserId()); 
            ps.setString(2, jM.getEntryDate().toString());
            ps.setString(3, jM.getContent());
            ps.setString(4, jM.getMood());
            ps.setString(5, jM.getWeather());
            
            return ps.executeUpdate() > 0; 
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public JournalModel getJournalByDate(int userId, LocalDate entryDate) 
    {
        String sql = "SELECT journal_id, mood, weather, content, created_at FROM journals WHERE user_id = ? AND entry_date = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {
            
            ps.setInt(1, userId);
            ps.setString(2, entryDate.toString());
            
            try (ResultSet rs = ps.executeQuery()) 
            {
                if (rs.next()) 
                {
                    
                    int journalId = rs.getInt("journal_id");
                    String mood = rs.getString("mood");
                    String weather = rs.getString("weather");
                    String content = rs.getString("content");
                    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime(); 
                    
                    return new JournalModel(journalId, userId, entryDate, mood, weather, content, createdAt);
                }
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null; 
    }
    
   
    public int getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
            {
                return rs.getInt("user_id");
            }
        } 
        catch (Exception e) 
        { 
            e.printStackTrace();
        }
        return -1; 
    }

    public boolean editJtoDB(JournalModel jM) 
    {

        String sql = "UPDATE journals SET content = ?, mood = ?, weather = ? WHERE user_id = ? AND entry_date = ?";
    
        try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) 
        {

            ps.setString(1, jM.getContent());
            ps.setString(2, jM.getMood());
            ps.setString(3, jM.getWeather());
            ps.setInt(4, jM.getUserId()); 
            ps.setString(5, jM.getEntryDate().toString());
        
            return ps.executeUpdate() > 0;
        
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
}
}
//ChengYingChenEnd