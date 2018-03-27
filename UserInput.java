
package Lab4;

/*
 * User Input:
 *      This class collects the User's moves, and sends them back to either
 *      the Easy or Hard class, depending on difficulty.
 */

import javax.swing.JOptionPane;

public class UserInput {

//Constructor    
static Speech aiSays = new Speech();

//Alternative text for JOptionPane buttons
static Object[] options = {"Rock", "Paper", "Scissors"};

//Variable to track rounds
static int round;
    
    // Hub method
    public int prompt(boolean hard, int turn, int cMove)
    {
        int result;
        
        round = turn;
        
        if (hard)
            {result = hard(cMove, turn);}
        else
            {result = easy();}
        
        return result;
    }
    
    //Method to collect user's choice for Easy difficulty
    public int easy()
    {
        int choose=0;
        int move = JOptionPane.showOptionDialog(null, 
                "Please choose your move.", 
                "digiRPS - Game " + round, 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                options, 
                options[0]);
        
        switch (move)
            {
                case JOptionPane.YES_OPTION: 
                    choose = 3; //R
                    return choose;
                case JOptionPane.NO_OPTION: 
                    choose = 2; //P
                    return choose;
                case JOptionPane.CANCEL_OPTION: 
                    choose = 1; //S
                    return choose;
            }
        
        return choose;
    }
    
    //Method to collect user's choice for Hard difficulty
    public int hard(int cMove, int turn)
    {
        int tauntCode=1; //Taunt Code "1" = UI Taunts
        int choose=0;
        int move = JOptionPane.showOptionDialog(null, 
                aiSays.taunt(tauntCode, cMove, turn), 
                "digiRPS - Game " + round, 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                options, 
                options[0]);
        
        switch (move)
            {
                case JOptionPane.YES_OPTION: 
                    choose = 3; //R
                    return choose;
                case JOptionPane.NO_OPTION: 
                    choose = 2; //P
                    return choose;
                case JOptionPane.CANCEL_OPTION: 
                    choose = 1; //S
                    return choose;
            }
        
        return choose;
    }
    
}
