
package Lab4;

import javax.swing.JOptionPane;

/*
** Author: Joshua McMahan
** Class: CITC 1310 P01
** Program Name: digiRPS (Digital RPS/Rock Paper Scissors)
** Program Description:
**          This progam offers two simulations of a game of Rock-Paper-Scissors.
**          The user can choose between Easy and Hard difficulty; the former
**          game mode pits the user against a non-player character (npc) who
**          makes choices randomly, while the latter uses an AI that attempts
**          to beat the user with strategic planning and manipulation.
*/

public class Main {

//Constructors for Easy and Hard modes 
//And for Main, so main method can access non-static methods
    static Easy easy = new Easy();
    static Hard hard = new Hard();
    static Main sys = new Main();

//Initialize objects for alternate JOptionPane button text
    static Object[] options = 
                {"New Game", "Instructions", "Exit"};
    static Object[] diff = 
                {"Easy", "Hard"};

    //Main Method; activates main menu
    public static void main(String[] args)
    {
        sys.menu();
    }
    
    //Main Menu method
    public void menu()
    {
        int welcome = JOptionPane.showOptionDialog(null,
                "Welcome to Digital RPS!",
                "digiRPS - Main Menu",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
            
            sys.system(welcome);
    }
    
    /* System method; determines game difficulty, displays instructions,
     * and holds universal reference with which any class can close the program
     */
    public void system(int option)
    {
        switch (option)
        {
        case 0: //Game Difficulty
            int mode = JOptionPane.showOptionDialog(null, 
                    "Please choose a difficulty level.", 
                    "digiRPS - New Game", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    diff, 
                    diff[0]);
                
                if (mode==0)
                    {easy.start();}
                else
                    {hard.start();}
            break;
        case 1: //Display Instructions
            JOptionPane.showMessageDialog(null, 
                    "Welcome to digiRPS!" + "\n\n" +
                    "To start playing, simply select \"New Game\" " +
                    "on the Main Menu, and then choose a game type.\n\n" +
                    "Choose your move (Rock/Paper/Scissors) when prompted, " +
                    "and see if you win!\n\n" +
                    "Remember: \n" + 
                    "          Scissors beats Paper\n" +
                    "          Paper beats Rock\n" +
                    "          Rock beats Scissors\n", 
                    "digiRPS - Instructions", 
                    JOptionPane.INFORMATION_MESSAGE);
            menu();
            break;
        case 2: //Close program
            JOptionPane.showMessageDialog(null, 
                    "Thanks for playing!",
                    "digiRPS - Exit",
                    JOptionPane.PLAIN_MESSAGE,
                    null);
            System.exit(0);
            break;
        }
    }
    
    //Method to display total results/winner at end of game
    public void endGame(String output)
    {
            JOptionPane.showMessageDialog(null, 
                    output, 
                    "digiRPS - Game Over", 
                    JOptionPane.PLAIN_MESSAGE, 
                    null);
            
            int num = 2;
        
        sys.system(num);
    }
}
