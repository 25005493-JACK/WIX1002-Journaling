
package fopassignment.journaling01;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JournalDataHandling {
    
    public boolean createJtoDB(JournalModel jM) 
    {
        String sql = "INSERT INTO journal (user_id, entry_date, content, mood, weather) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) 
        {
            
            ps.setInt(1, jM.getUserId()); 
            ps.setString(2, jM.getEntryDate().toString());
            ps.setString(3, jM.getContent());
            ps.setString(4, jM.getMood());
            ps.setString(5, jM.getWeather());
            
            return pstmt.executeUpdate() > 0; // True if 1 or more rows affected
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    // --- R: Read (Get a single journal entry by date and user) ---
    public JournalModel getJournalByDate(int userId, LocalDate entryDate) {
        String sql = "SELECT journal_id, mood, weather, content, created_at FROM journal WHERE user_id = ? AND entry_date = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ps.setString(2, entryDate.toString());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 1. Extract data from the result set (rs)
                    int journalId = rs.getInt("journal_id");
                    String mood = rs.getString("mood");
                    String weather = rs.getString("weather");
                    String content = rs.getString("content");
                    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime(); 
                    
                    // 2. Use the SELECT constructor to build the Model object
                    return new JournalModel(journalId, userId, entryDate, mood, weather, content, createdAt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no entry is found
    }
    
    // --- Utility Method: Get User ID (Bridge) ---
    public int getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (Exception e) { // Catching Exception to handle potential issues from DBConnection.getConnection()
            e.printStackTrace();
        }
        return -1; // Return -1 if user is not found
    }
}
