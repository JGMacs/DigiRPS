
package Lab4;

/*
 * Game - Easy Mode:
 *      This class runs the game at normal difficulty, by pitting the User
 *      against a Computer player that choose moves at random.
 */

import javax.swing.JOptionPane;

public class Easy {

//Universal Boolean to determine if game is in Hard mode    
    static boolean hard = false;

//Constructors for AI, Win Calculator, User Input, and Main classes
    static Main sys = new Main();
    static AI npc = new AI();
    static WinCalc wincalc = new WinCalc();
    static UserInput user = new UserInput();

//Variable to track rounds played
    static int rounds;
    static String userName;
    
    static Object[] choices= {"Next Game", "Quit"};
    
    //Method to start the game, or return the user to main menu
    public void start()
    {
        int cont;
        
        do{
            name();
            
            //If user cancels out of name input, send them here
            cont = JOptionPane.showConfirmDialog(null, 
                    "Return to Main Menu?", 
                    "digiRPS - New Player", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE);
        } while (cont==JOptionPane.NO_OPTION);
        
        sys.menu();
    }
    
    //Method to learn user's name
    public void name()
    {
        try{
            userName=JOptionPane.showInputDialog(null, 
                "Please enter your name: ", 
                "digiRPS - New Player", 
                JOptionPane.PLAIN_MESSAGE);
            if ((userName.length()<1))
                {JOptionPane.showMessageDialog(null, 
                    "Error: Please enter a username!", 
                    "digiRPS - Error", 
                    JOptionPane.ERROR_MESSAGE);
                 name();}
            else if ((userName.length()>0)&&(userName!=null))
                {game();}
        } catch (NullPointerException e){ }
    }
    
    //Method for the actual running of the game
    public void game()
    {
        int loop;
        
        do {
            rounds++;
            
            //Get the Computer's move
            int cMove = npc.easy();
            
            //Get the User's move
            int uMove = user.prompt(hard, rounds, cMove);
            
            //Determine the winner and update records
            wincalc.start(uMove, cMove, rounds, userName, hard);
            
            loop = JOptionPane.showOptionDialog(null, 
                    "Continue?", 
                    "digiRPS - Continue", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    choices,
                    choices[0]);
            
            } while (loop == JOptionPane.YES_OPTION);
        
        //At game end, tally final results, and send to endGame method in Main
        sys.endGame(wincalc.tally());
    }
   
}
