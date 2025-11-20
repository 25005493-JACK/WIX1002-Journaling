package fopassignment.journaling01;


import java.io.*;
import java.time.LocalDate;

public class JournalPageFH {   

    private static final String J_FOLDER = "JournalContent/";
    
    public JournalPageFH() 
    { 
        File folder = new File(J_FOLDER);
        if (!folder.exists()) {
            folder.mkdir(); 
        }
    }
   
    public boolean JCExist(LocalDate date) 
    {
        File file = new File(J_FOLDER + date + ".txt");
        return file.exists();
    }
    
    public void createJ(LocalDate userCDate, String jCon)
    {
        try (FileWriter createFile = new FileWriter(J_FOLDER + userCDate + ".txt")) 
        {
            createFile.write(jCon); 
        } 
        catch (IOException exc) 
        {
            exc.printStackTrace();
        }
    }
    
    public String readJ(LocalDate date)
    {
        
        File file = new File(J_FOLDER + date + ".txt");

        if (!file.exists()) {
            return null; // no journal found
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } 
        catch (IOException exc) {
            exc.printStackTrace();
            return null;
        }

        return content.toString().trim(); // return journal content
    }

    
}

    
