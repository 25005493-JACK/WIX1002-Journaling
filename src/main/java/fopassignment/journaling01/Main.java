//TanWeiFengStart
//Main flow of whole program
//(Login/Register)->Journal Page->Logout
package fopassignment.journaling01;

public class Main{
    public static void main(String[]args){

        StartUpPage SUP = new StartUpPage();
        WelcomingPage WP = new WelcomingPage();
        JournalPage JP = new JournalPage();
        Logout lo = new Logout();
        
        int LR = SUP.Intro();
        
        boolean loginstatus = false; 
        
        while (loginstatus == false){
            if (LR == 1){
                loginstatus = WP.Login();
            }
            else if (LR == 2){
                WP.Register();
                loginstatus = false;
                LR = 1;   
            }
        }
        
        
        do{
            JP.Journal();
            loginstatus =  lo.logout();
        }while (loginstatus == true);
        
    }
}
//TanWeiFengEnd