
package Lab4;

/*
 * Win Calculator:
 *      This class calculates and displays the winner of the round, and also
 *      tracks pertinent game information, such as winning streaks, moves used
 *      total wins, etc.
 */

import javax.swing.JOptionPane;

/***Move Key***
        * 'S' or 1=Scissors
        * 'P' or 2=Paper
        * 'R' or 3=Rock
        ***Result Key***
        * Win == (1, 2, 3) A tie + move used
        * Win == (4, 5, 6) User win + move used
        * Win == (7, 8, 9) Computer win + move used
        */

public class WinCalc {
    
//Constructors for Random Number Generator, Speech, Animation, and Main classes
    static RandomGen gen = new RandomGen();
    static Speech speak = new Speech();
    static Animation animate = new Animation();
    static Main sys = new Main();
    
//Variable for the User's Name
    static String userName;
//Variable for tracking rounds
    static int round;
    
//Variable for the winner
    static int Win;
//Variables to keep track of the last three wins/moves (for Hard)
    static int winOne=0, winTwo=0, winThree=0;
    static int uStreak=0, cStreak=0, tieStreak=0;
    static int[] cMovStreak={0, 0, 0};
//Variable to determine if computer won last game
    static boolean npcWin;
    static boolean tie;
    
//Variables to track number of times user/comp used a move
    private static int uRock=0, uPap=0, uSci=0, cRock=0, cPap=0, cSci=0;
//Variables to track number of times user/comp won with a move
    private static int uRockW=0, uPapW=0, uSciW=0, cRockW=0, cPapW=0, cSciW=0;
//Variables to track total wins/ties for user/comp
    private static int uWins=0, cWins=0, ties=0;
    
/* Was planning on turning all of the above tracking variables into arrays,
 * but I ended up running out of time.
*/
    
    // Hub method
    public void start(int uMove, int cMove, int round, String name, boolean hard)
    {
        //Update trackers
        this.round=round;
        userName=name;
        
        //Call find to calculate winner
        find(uMove, cMove);
        
        //Call streakCounter to update streak records
        streakCounter(cMove);
        
        display(uMove, cMove, hard);
        winAccountant();
    }
    
    // Method to "find" the winner
    public void find(int uMove, int cMove)
    {     
        switch (uMove)
            {case 1 : //Scissors
                switch (cMove)
                    {case 1: Win=1; //Tie with Scissors
                             break;
                     case 2: Win=4; //User wins with Scissors
                             break;
                     case 3: Win=9; //Comp wins with Rock
                             break;
                    }
                break;
            case 2 : //Paper
                switch (cMove)
                    {case 1: Win=7; //Comp wins with Scissors
                             break;
                     case 2: Win=2; //Tie with Paper
                             break;
                     case 3: Win=5; //User wins with Paper
                             break;
                    }
                break;
            case 3 ://Rock
                switch (cMove)
                    {case 1: Win=6; //User wins with Rock
                             break;
                     case 2: Win=8; //Comp wins with Paper
                             break;
                     case 3: Win=3; //Tie with Rock
                             break;
                    }
                break;
            default: JOptionPane.showMessageDialog(null,
                      "Error: Unknown Move Input - WinCalc.find 97", 
                      "digiRPS - Error", 
                      JOptionPane.ERROR_MESSAGE);
                break;
            }
        
        //Update boolean trackers for last game result
        npcWin=((Win==7) ^ (Win==8) ^ (Win==9));
        tie=((Win==1) ^ (Win==2) ^ (Win==3));
    }
    
    // Method to display the winner on Easy difficulty
    // Original dispEasy Method; replaced by display();
    public void dispEasy()
    {
        //Null block of code was original display method; keeping just in case
        
        /* switch (Win)
                {
                case 1: 
                case 2: 
                case 3: JOptionPane.showMessageDialog(null, 
                        "It's a tie!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                case 4: 
                case 5: 
                case 6: JOptionPane.showMessageDialog(null, 
                        userName + " won!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                case 7: 
                case 8: 
                case 9: JOptionPane.showMessageDialog(null, 
                        "Computer won!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
        */
    }
        
    // Method to display the winner on either difficulty
    public void display(int uMove, int cMove, boolean hard)
    {
        animate.start(uMove, cMove, hard);
    }  
    
    // Original dispHard Method; replaced by display();
    public void dispHardNull()
    {
        //Null block of code was original display method; keeping just in case
        /*switch (Win)
                {
                case 1: 
                case 2: 
                case 3: JOptionPane.showMessageDialog(null, 
                        "It's a tie!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                case 4: 
                case 5: 
                case 6: JOptionPane.showMessageDialog(null, 
                        userName + " won!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                case 7: 
                case 8: 
                case 9: JOptionPane.showMessageDialog(null, 
                        "Computer won!",
                        "digiRPS - Results",
                        JOptionPane.INFORMATION_MESSAGE);
                        break;
                }*/
    }
    
    // Method to update win-tracking variables
    public void winAccountant()
    {
        switch (Win)
            {case 1: //A tie with scissors
                    ties++; uSci++; cSci++;
                    break;
             case 2: //A tie with paper
                    ties++; uPap++; cPap++;
                    break;
             case 3: //A tie with rock
                    ties++; uRock++; cRock++;
                    break;
             case 4: //A win for user with Scissors
                    uWins++; uSci++; uSciW++;
                    cPap++;
                    break;
             case 5: //A win for user with Paper
                    uWins++; uPap++; uPapW++;
                    cRock++;
                    break;
             case 6: //A win for user with Rock
                    uWins++; uRock++; uRockW++;
                    cSci++;
                    break;
             case 7: //A win for Comp with Scissors
                    cWins++; cSci++; cSciW++;
                    uPap++;
                    break;
             case 8: //A win for Comp with Paper
                    cWins++; cPap++; cPapW++;
                    uRock++;
                    break;
             case 9: //A win for Comp with Rock
                    cWins++; cRock++; cRockW++;
                    uSci++;
                    break;
             default: JOptionPane.showMessageDialog(null,
                      "Error: Unknown Win Value - WinCalc.winAccountant 207", 
                      "digiRPS - Error", 
                      JOptionPane.ERROR_MESSAGE);
                    break;}
        
        // Update the last three wins
        winThree=winTwo;
        winTwo=winOne;
        winOne=Win;
                    
    }
    
    // Method to save the post-game results as a String, and send it back to Main
    public String tally()
    {
        String output = 
                "                       _-Final Tally-_\n\n" +
                "Total Rounds Played: " + round + "\n\n" +
                "Total Ties: " + ties + "\n\n" +
                "User Wins: " + uWins + "\n" +
                "   Rock win ratio: " + uRockW + " of " + uRock + "\n" +
                "   Paper win ratio: " + uPapW + " of " + uPap + "\n" +
                "   Scissors win ratio: " + uSciW + " of " + uSci + "\n\n" +
                "Computer Wins: " + cWins + "\n" +
                "   Rock win ratio: " + cRockW + " of " + cRock + "\n" +
                "   Paper win ratio: " + cPapW + " of " + cPap + "\n" +
                "   Scissors win ratio: " + cSciW + " of " + cSci + "\n";
        
        return output;
    }
    
    // Method to get the User or Computer's cumulative winning moves/move uses
    public int getMoves(int choose)
    {
        int uWinMov=0, uFavMov=0, cWinMov=0;
        
        switch (choose)
            {case 1: //AI uses code number "1" to get User's winning moves
                if ((uRockW>uPapW)&&(uRockW>uSciW))
                    {uWinMov=3;}
                else if ((uPapW>uRockW)&&(uPapW>uSciW))
                    {uWinMov=2;}
                else
                    {uWinMov=1;}
                return uWinMov;
                
             case 2: //AI uses code number "2" to get User's most used move
                if ((uRock>uPap)&&(uRock>uSci))
                    {uFavMov=3;}
                else if ((uPap>uRock)&&(uPap>uSci))
                    {uFavMov=2;}
                else
                    {uFavMov=1;}
                return uWinMov;
             
             case 3: //AI uses code number "3" to get its own winning moves
                if ((cRockW>cPapW)&&(cRockW>cSciW))
                    {cWinMov=3;}
                else if ((cPapW>cRockW)&&(cPapW>cSciW))
                    {cWinMov=2;}
                else
                    {cWinMov=1;}
                return cWinMov;
             
             default: //Error message for erronous input by AI
                 {JOptionPane.showMessageDialog(null, 
                    "Error: unknown chooser value - WinCalc.getMoves 272", 
                    "digiRPS - ERROR", 
                    JOptionPane.ERROR_MESSAGE);
                  break;
                 }
            }
        
        return uWinMov;
    }
    
    // Getter method for user's wins
    public int get_uWins()
    {
        return uWins;
    }
    
    // Getter method for computer's wins
    public int get_cWins()
    {
        return cWins;
    }
    
    // Method to count winning streaks
    public void streakCounter(int cMove)
    {        
        switch (Win)
            {case 1:
            case 2:
            case 3:
                tieStreak++;
                uStreak=0;
                cStreak=0;
                break;
            case 4:
            case 5:
            case 6:
                tieStreak=0;
                uStreak++;
                cStreak=0;
                break;
            case 7:
            case 8:
            case 9:
                tieStreak=0;
                uStreak=0;
                cStreak++;
                break;
            }
        
        switch (cMove)
            {case 1:
                cMovStreak[0]++;
                cMovStreak[1]=0;
                cMovStreak[2]=0;
                break;
            case 2:
                cMovStreak[0]=0;
                cMovStreak[1]++;
                cMovStreak[2]=0;
                break;
            case 3:
                cMovStreak[0]=0;
                cMovStreak[1]=0;
                cMovStreak[2]++;
                break;       
            }
    }
}
