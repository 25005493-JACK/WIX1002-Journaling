//ChengYingChen Start
package fopassignment.journaling01;


import java.io.*;
import java.time.LocalDate;

public class JournalPageFH extends User{   

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
<<<<<<< HEAD
    
   

    
}

    
=======
}

//ChengYingChen End
>>>>>>> 68a2d4d94c1509c9ff6e1de7e07191650ede4ab6
