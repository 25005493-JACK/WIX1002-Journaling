//TanWeiFengStart
package fopassignment.journaling01;
public class Main{
    public static void main(String[]args){

        StartUpPage SUP = new StartUpPage();
        WelcomingPage WP = new WelcomingPage();
        JournalPage JP = new JournalPage();
        Logout lo = new Logout();
        
        int LR = SUP.Intro();
        //System.out.println(LR); //to check LR can be returned or not
        
        boolean loginstatus = false; 
        
        while (loginstatus == false){
            if (LR == 1){
                WP.Login();//boolean here, fix later after csv.
                loginstatus = true;
                break;
            }
            else if (LR == 2){
                WP.Register();
                loginstatus = false;
            }
        }
        
        while (loginstatus == true){
        JP.Journal();
        loginstatus =  lo.logout();
        //System.out.println(loginstatus); //to check loginstatus can be returned or not
        }
    }
}
//TanWeiFengEnd
