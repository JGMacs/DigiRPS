
package Lab4;

/*
 * Game - Hard Mode:
 *      This class runs the game with a Harder difficulty; as opposed to
 *      the Easy mode, this version uses an AI that considers multiple
 *      strategies in an attempt to outwit the User.
 */

import javax.swing.JOptionPane;

public class Hard {

//Universal Boolean to determine if game is in Hard mode   
    static boolean hard = true;

//Constructors for AI, Win Calculator, User Input, AI Speech, and Main classes
    static Main sys = new Main();
    static AI npc = new AI();
    static WinCalc wincalc = new WinCalc();
    static UserInput user = new UserInput();
    static Speech aiSays = new Speech();

//Variables to determine if computer won last game
    static boolean npcWin=false;
    static boolean tie=false;
    
//Variable to track rounds played
    int rounds;
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
        } catch (NullPointerException e){}
    }
    
    //Method for the actual running of the game
    public void game()
    {
        int loop;
        
        do {
            rounds++;
            
            //Get the Computer's move
            int cMove = npc.hard(npcWin, tie, rounds);
            
            //Get the User's move
            int uMove = user.prompt(hard, rounds, cMove);
            
            //Determine the winner and update records
            wincalc.start(uMove, cMove, rounds, userName, hard);
            
            this.npcWin=WinCalc.npcWin;
            this.tie=WinCalc.tie;
            
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
