
package Lab4;

import javax.swing.JOptionPane;

/*
 * Artificial Intelligence:
 *      This class contains both the Easy and Hard mode AI.
 *      The Easy mode AI simply chooses a move at random, while the Hard
 *      mode AI tracks the game's progress, learns about the player, and 
 *      employs several different tactics in an attempt to outwit them.
 */

public class AI {

static RandomGen random = new RandomGen();
static WinCalc wincalc = new WinCalc();

    //Variable to hold computer's move choice
    static int result;

    //Variables to determine outcome of last game
    static boolean npcWin;
    static boolean tie;
    
    //Variables to keep track of total rounds, and last three game outcomes
    static int rounds;
    static int winOne, winTwo, winThree;

    //Variable to determine if a drastic strategy change is needed to counter 
    //the usage of a streak-based strategy
    static int emergencyReset=0;

    //Variables to track winning streaks and Computer move streaks
    static int[] streak = {0, 0, 0};
    static int[] cMovStreak = {0, 0, 0};
    static int cMov=0, cMovMax=0, cMovLoop=0;

    //Array to keep track of moves used in game
    static int[] movTracker = {0, 0, 0, 0, 0, 0, 0, 0, 0};
//                              [0-2]    [3-5]    [6-8]
//                             uWinMov  uFavMov  cWinMov
    
    //Static variable to track Computer's move considerations
    static int[] choose = {0, 0, 0};
    //choose[0]=Scissors  choose[1]=Paper choose[2]=Rock

    //Easy AI difficulty 
    public int easy()
    {
        result = random.move();
                
        return result;
    }
    
    //Hard AI difficulty hub
    public int hard(boolean npcWin, boolean tie, int rounds)
    {
        //Update winning/move streak variables
        updater(npcWin, tie, rounds);
        
        //Initialize result variable
        result=1;
        
        if ((wincalc.get_cWins()<wincalc.get_uWins())&&(random.random()==6)||(random.random()==2))
                    {result=easy();}
        
        //If there's a user winning streak, a tie streak, or the computer
        //has overused a move, call either comboBreaker or loopBreaker
        //(Random number argument included to add a measure of unpredictability)
        else if ((streak[0]>2)||(streak[1]>1))
            {result=comboBreaker();
             emergencyReset++;}
        else if (cMovMax>random.move())
            {result=loopBreaker();
             emergencyReset++;}
        
        //If neither of the above apply, do one of the following strategies
        //depending on last game's outcome
        else
            {if (rounds==1)
                {result=stratOne();
                    if (emergencyReset>0)
                        {emergencyReset--;}
                }
            else if (npcWin)
                {result=stratTwo();
                    if (emergencyReset>0)
                        {emergencyReset--;}}
            else
                {result=stratThree();
                    if (emergencyReset>0)
                        {emergencyReset--;}}
            }
        
        parser(result);
        
        return result;
    }
    
//The following methods belong to the Hard method declared above
    
    //Strategy for first game (choose something other than rock)
    public int stratOne()
    {
        int move = random.coin();
        
        switch (move)
           {case 0: 
                move = 2; //Paper
                break;
            case 1:
                move = 3; //Rock
                break;}
        
        return move;
    }

//*************************************
//     Main Strategies for Hard AI
//*************************************
    
    //Strategy if computer won last game
    public int stratTwo()
    {
        nextSequence();
        pastSequenceComp();
        moveCounts();
        
        //Enact emergency reset if needed to potentially alter move choice
        if (emergencyReset>0)
            {switch (random.move())
                {case 1: 
                    choose[0]++;
                    break;
                case 2:
                    choose[1]++;
                    break;
                case 3:
                    choose[2]++;
                    break;
                }
            }
        
        moveCounts();
        
        int move=choose[0];
        
        for (int i=1; i<3; i++)
            {
             if (move<choose[i])
                    {move=choose[i];}
            }
        
        return move;
    }
    
    //Strategy if computer lost last game
    public int stratThree()
    {
        nextSequence();
        pastSequenceUser();
        moveCounts();
        
        //Enact emergency reset if needed to potentially alter move choice
        if (emergencyReset>0)
            {switch (random.move())
                {case 1: 
                    choose[0]++;
                    break;
                case 2:
                    choose[1]++;
                    break;
                case 3:
                    choose[2]++;
                    break;
                }
            }
        
        int move=choose[0];
        
        for (int i=1; i<3; i++)
            {
             if (move<choose[i])
                    {move=choose[i];}
            }
        
        return move;
    }
    
    //Strategy if the user is on a winning streak, or a tie streak
    public int comboBreaker()
    {
        breaker();
        
        int move=choose[0];
        
        for (int i=1; i<3; i++)
            {
             if (move<choose[i])
                    {move=choose[i];}
            }
        
        return move;
    }
    
    //Strategy if the Computer is stuck in a loop (using same move too much)
    public int loopBreaker()
    {
        deLooper();
        
        int move=choose[0];
        
        for (int i=1; i<3; i++)
            {
             if (move<choose[i])
                    {move=choose[i];}
            }
        
        return move;
    }
    
//*************************************
//     Sub-Strategies for Hard AI
//*************************************
    
    //Sub-Strategies regarding the last game for the computer to consider
    public void nextSequence()
    {
        //If comp won last game, move forward one move in sequence (R-P-S)
        if (npcWin)
            {switch (winOne)
                {case 7: //Scissors
                    choose[2]++; //Rock
                    break;
                case 8: //Paper
                    choose[0]++; //Scissors
                    break;
                case 9: //Rock
                    choose[1]++; //Paper
                    break;
                default: //Error for win code # unrelated to npc
                    JOptionPane.showMessageDialog(null, 
                            "Error - Unknown npc Win value - AI.nextSequence 108", 
                            "digiRPS - ERROR", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        else if (tie)
            {switch (random.move())
                {case 1: //Scissors
                    choose[2]++; //Rock
                    break;  
                case 2: //Paper
                    choose[0]++; //Scissors
                    break;
                case 3: //Rock
                    choose[1]++; //Paper
                    break;
                default: //Error for win code # unrelated to npc
                    JOptionPane.showMessageDialog(null, 
                            "Error - Unknown npc Win value - AI.nextSequence 142", 
                            "digiRPS - ERROR", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        else //If comp lost last game, move forward two moves in sequence (R-P-S)
            {switch (winOne)
                {case 1:
                case 4: //Scissors
                    choose[1]++; //Paper
                    break;
                case 2:    
                case 5: //Paper
                    choose[2]++; //Rock
                    break;
                case 3:
                case 6: //Rock
                    choose[0]++; //Scissors
                    break;
                default: //Error for win code # unrelated to npc
                    JOptionPane.showMessageDialog(null, 
                            "Error - Unknown npc Win value - AI.nextSequence 163", 
                            "digiRPS - ERROR", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
    }
    
    //Sub-Strategies that cover the last three moves, if computer won last game
    public void pastSequenceComp()
    {
        int uWins=wincalc.get_uWins();
        int cWins=wincalc.get_cWins();
        
        int coin=random.coin();
        
        //Determine strategy based on last moves, and if computer is winning
        
        if (cWins>uWins) //If computer is winning, use these strategies
            {if (winOne!=winTwo)
                {switch(winOne)
                    {case 7: //Scissors
                        choose[2]++; //Rock
                        break;
                    case 8: //Paper
                        choose[0]++; //Scissors
                        break;
                    case 9: //Rock
                        choose[1]++; //Paper
                        break;
                    }
                }
            else if ((winOne==winTwo)&&(winOne!=winThree))
                {switch(winOne)
                    {case 7: //Scissors
                        choose[0]++; //Scissors
                        break;
                    case 8: //Paper
                        choose[1]++; //Paper
                        break;
                    case 9: //Rock
                        choose[2]++; //Rock
                        break;
                    }
                }
            else if ((winOne==winTwo)&&(winOne==winThree))
                {switch(winOne)
                    {case 7: //Scissors
                        if (coin==0)
                            {choose[1]++;} //Paper
                        else
                            {choose[2]++;} //Rock
                        break;
                    case 8: //Paper
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[2]++;} //Rock
                        break;
                    case 9: //Rock
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[1]++;} //Paper
                        break;
                    }
                }
            }
        else if (cWins<uWins) //If computer is losing, use these strategies
            {if ((winOne==winTwo)&&(winOne==winThree))
                {switch(winOne)
                    {case 7: //Scissors
                        if (coin==0)
                            {choose[1]++;} //Paper
                        else
                            {choose[2]++;} //Rock
                        break;
                    case 8: //Paper
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[2]++;} //Rock
                        break;
                    case 9: //Rock
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[1]++;} //Paper
                        break;
                    }
                }
            else
                {switch (coin)
                    {case 0:
                        {switch(winOne)
                            {case 7: //Scissors
                                choose[2]++; //Rock
                                break;
                            case 8: //Paper
                                choose[0]++; //Scissors
                                break;
                            case 9: //Rock
                                choose[1]++; //Paper
                                break;
                            }
                        }
                    case 1:
                        switch(winOne)
                            {case 7: //Scissors
                                choose[0]++; //Scissors
                                break;
                            case 8: //Paper
                                choose[1]++; //Paper
                                break;
                            case 9: //Rock
                                choose[2]++; //Rock
                                break;
                            }
                    }
                }
            }
    }
    
    //Sub-Strategies that cover the last three moves, if computer lost last game
    public void pastSequenceUser()
    {
        int uWins=wincalc.get_uWins();
        int cWins=wincalc.get_cWins();
        
        int coin=random.coin();
        
        if (cWins>uWins) //If computer is winning, use these less risky strategies
            {if (winOne!=winTwo)
                {switch(winOne)
                    {case 4: //Scissors
                        choose[2]++; //Rock
                        break;
                    case 5: //Paper
                        choose[0]++; //Scissors
                        break;
                    case 6: //Rock
                        choose[1]++; //Paper
                        break;
                    }
                }
            else if ((winOne==winTwo)&&(winOne!=winThree))
                {switch(winOne)
                    {case 4: //Scissors
                        choose[2]++; //Rock
                        break;
                    case 5: //Paper
                        choose[0]++; //Scissors
                        break;
                    case 6: //Rock
                        choose[1]++; //Paper
                        break;
                    }
                }
            else if ((winOne==winTwo)&&(winOne==winThree))
                {switch(winOne)
                    {case 4: //Scissors
                        choose[2]++; //Rock
                        break;
                    case 5: //Paper
                        choose[0]++; //Scissors
                        break;
                    case 6: //Rock
                        choose[1]++; //Paper
                        break;
                    }
                }
            }
        else if (cWins<uWins) //If computer is losing, use these strategies
            {if ((winOne==winTwo)&&(winOne==winThree))
                {switch(winOne)
                    {case 4: //Scissors
                        if (coin==0)
                            {choose[1]++;} //Paper
                        else
                            {choose[2]++;} //Scissors
                        break;
                    case 5: //Paper
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[2]++;} //Rock
                        break;
                    case 6: //Rock
                        if (coin==0)
                            {choose[0]++;} //Scissors
                        else
                            {choose[2]++;} //Rock
                        break;
                    }
                }
            else
                {switch (coin)
                    {case 0:
                        {switch(winOne)
                            {case 4: //Scissors
                                choose[2]++; //Rock
                                break;
                            case 5: //Paper
                                choose[0]++; //Scissors
                                break;
                            case 6: //Rock
                                choose[1]++; //Paper
                                break;
                            }
                        }
                    case 1:
                        switch(winOne)
                            {case 4: //Scissors
                                choose[0]++; //Scissors
                                break;
                            case 5: //Paper
                                choose[1]++; //Paper
                                break;
                            case 6: //Rock
                                choose[2]++; //Rock
                                break;
                            }
                    }
                }
            }
    }
    
    //Method to track the user and computer's moves so far
    public void moveCounts()
    {
        /* Added redundant move counters so that AI can purge move counts
         * during an emergency reset.
         */
        switch (wincalc.getMoves(1)) //AI uses code number "1" to get User's winning moves
            {case 1: //Scissors
                 movTracker[0]++;
                 break;
            case 2: //Paper
                 movTracker[1]++;
                 break;
            case 3: //Rock
                 movTracker[2]++;
                 break;
            }
        
        switch (wincalc.getMoves(2)) //AI uses code number "2" to get User's most used move
            {case 1: //Scissors
                 movTracker[3]++;
                 break;
            case 2: //Paper
                 movTracker[4]++;
                 break;
            case 3: //Rock
                 movTracker[5]++;
                 break;
            }
        
        switch (wincalc.getMoves(3)) //AI uses code number "3" to get its own winning moves
            {case 1: //Scissors
                 movTracker[6]++;
                 break;
            case 2: //Paper
                 movTracker[7]++;
                 break;
            case 3: //Rock
                 movTracker[8]++;
                 break;
            }
        
        //For loop to convert redundant move trackers into readable move code
        int uWinMov=1;
        int uWinMax=movTracker[0];
        
        int uFavMov=1;
        int uFavMax=movTracker[3];
        
        int cWinMov=1;
        int cWinMax=movTracker[6];
        
        for (int i=1; i<9; i++)
            {
                if ((i<3)&&(uWinMax<movTracker[i]))
                    {uWinMax=movTracker[i];
                    
                     if (i==1)
                        {uWinMov=2;}
                     if (i==2)
                        {uWinMov=3;}
                    }
                if ((i>2)&&(i<6)&&(uFavMax<movTracker[i]))
                    {uFavMax=movTracker[i];
                    
                     if (i==3)
                        {uFavMov=1;}
                     if (i==4)
                        {uFavMov=2;}
                     if (i==5)
                        {uFavMov=3;}
                    }
                if ((i>5)&&(cWinMax<movTracker[i]))
                    {cWinMax=movTracker[i];
                    
                     if (i==6)
                        {cWinMov=1;}
                     if (i==7)
                        {cWinMov=2;}
                     if (i==8)
                        {cWinMov=3;}
                    }
            }
        
        //Find user's winning moves, and plan accordingly
        switch (uWinMov)
            {case 1: //Scissors
                 choose[2]++; //Rock
                 break;
            case 2: //Paper
                 choose[0]++; //Scissors
                 break;
            case 3: //Rock
                 choose[1]++; //Paper
                 break;
            }
        
        //Find user's most used moves, and plan accordingly
        switch (uFavMov)
            {case 1: //Scissors
                choose[2]++; //Rock
                break;
            case 2: //Paper
                choose[0]++; //Scissors
                break;
            case 3: //Rock
                choose[1]++; //Paper
                break;
            }
        
        //Find computer's own winning moves, and plan accordingly
        switch (cWinMov)
            {case 1: //Scissors
                choose[0]++; //Scissors
                break;
            case 2: //Paper
                choose[1]++; //Paper
                break;
            case 3: //Rock
                choose[2]++; //Rock
                break;
            }
    }
    
    //Sub-Strategies for the comboBreaker method
    public void breaker()
    {
        //Determines if user changed their move after playing the same one twice
        //If so, computer makes that move next turn to prevent any streaks
        if ((winOne!=winTwo)&&(winTwo==winThree)&&(winTwo<7)&&(winOne<7))
            {switch (winOne)
                {case 1:
                    if ((random.random()-1)>1)
                    {choose[0]+=4;}
                    break;
                case 2:
                    if ((random.random()-1)>1)
                    {choose[1]+=4;}
                    break;
                case 3:
                    if ((random.random()-1)>1)
                    {choose[2]+=4;}
                    break;
                case 4:
                    if ((random.random()-1)>1)
                    {choose[0]+=4;}
                    break;
                case 5:
                    if ((random.random()-1)>1)
                    {choose[1]+=4;}
                    break;
                case 6:
                    if ((random.random()-1)>1)
                    {choose[2]+=4;}
                    break;
                }
            }
        
        switch (winOne)
                {case 1: //Scissors
                    {choose[2]++;}
                    break;
                case 2: //Paper
                    {choose[0]++;}
                    break;
                case 3: //Rock
                    {choose[1]++;}
                    break;
                case 4: //Scissors
                    {choose[2]++;}
                    break;
                case 5: //Paper
                    {choose[0]++;}
                    break;
                case 6: //Rock
                    {choose[1]++;}
                    break;
                }
        
            switch (winTwo)
                {case 1: //Scissors
                    {choose[2]++;}
                    break;
                case 2: //Paper
                    {choose[0]++;}
                    break;
                case 3: //Rock
                    {choose[1]++;}
                    break;
                case 4: //Scissors
                    {choose[2]++;}
                    break;
                case 5: //Paper
                    {choose[0]++;}
                    break;
                case 6: //Rock
                    {choose[1]++;}
                    break;
                }
            
            switch (winThree)
                {case 1: //Scissors
                    {choose[2]++;}
                    break;
                case 2: //Paper
                    {choose[0]++;}
                    break;
                case 3: //Rock
                    {choose[1]++;}
                    break;
                case 4: //Scissors
                    {choose[2]++;}
                    break;
                case 5: //Paper
                    {choose[2]++;}
                    break;
                case 6: //Rock
                    {choose[1]++;}
                    break;
                }
    }
    
    //Sub-Strategies for the loopBreaker method
    public void deLooper()
    {
        if ((streak[2]>2)&&(random.random()>1))
            {switch (cMov)
                {case 1: //Scissors
                    if (random.move()<2)
                        {choose[0]++;}
                    else
                        {choose[2]++;}
                    break;
                case 2: //Paper
                    if (random.move()<2)
                        {choose[1]++;}
                    else
                        {choose[0]++;}
                    break;
                case 3: //Rock
                    if (random.move()<2)
                        {choose[2]++;}
                    else
                        {choose[1]++;}
                    break;}
            }
        else
            {switch (cMov)
                {case 1: //Scissors
                    if (random.move()<2)
                        {choose[0]++;}
                    else
                        {choose[2]++;}
                    break;
                case 2: //Paper
                    if (random.move()<2)
                        {choose[1]++;}
                    else
                        {choose[0]++;}
                    break;
                case 3: //Rock
                    if (random.move()<2)
                        {choose[2]++;}
                    else
                        {choose[1]++;}
                    break;
            }
        }
    }
    
    //Method to update static variables at start of new round
    public void updater(boolean npcWin, boolean tie, int rounds)
    {
        //Make sure move chooser array is reset to 0
        for (int i=0; i<3; i++)
                {choose [i]=0;}
        
        //Enact emergencyReset if needed, and purge move counts
        if (emergencyReset>1)
            {for (int i=0; i<9; i++)
                {movTracker[i]=0;}
            }
        
        //Update values for tracking variables for last three rounds
        winThree=winTwo;
        winTwo=winOne;
        this.winOne=WinCalc.winOne;
        
        //Update number of rounds elapsed
        this.rounds=rounds;
        
        //Update npcWin and tie booleans
        this.npcWin=npcWin;
        this.tie=tie;
        
        //Update general winning/tie streak tracking array
        streak[0]=WinCalc.uStreak;
        streak[1]=WinCalc.tieStreak;
        streak[2]=WinCalc.cStreak;
        
        //Update computer move streak tracking array
        System.arraycopy(WinCalc.cMovStreak, 0, 
                         this.cMovStreak, 0, 
                         WinCalc.cMovStreak.length);
        
        //Parse cMoveStreak array values into proper move code
        cMovMax=cMovStreak[0]; //Most used move - Array Form
        cMov=1; //Most used move - Move Code Form
        
        for (int i=1; i<3; i++)
            {if (cMovMax<cMovStreak[i])
                    {cMovMax=cMovStreak[i];
                    
                     if (i==1)
                        {cMov=2;}
                     else if (i==2)
                        {cMov=3;}
                    }
            }
    }
    
    //Method for final parsing of computer's move choice into correct code
    public void parser(int result)
    {
        for (int i=0; i<2; i++)
            {
            if ((choose[0]==choose[1])||(choose[0]==choose[2])||(choose[1]==choose[2]))
                {result=random.move();}
            else if (result==choose[0])
                {result=1;}
            else if (result==choose[1])
                {result=2;}
            else if (result==choose[2])
                {result=3;}
            else
                {result=random.move();}
        
            /* If Level 2 emergency reset is active, rerun the loop to
             * potentially alter the outcome
             */
            if ((emergencyReset>2)&&(i==0))
                {switch (result)
                    {case 1: 
                        if (random.coin()==0)
                            {choose[1]+=2;}
                        else
                            {choose[2]+=2;}
                        break;
                    case 2:
                        if (random.coin()==0)
                            {choose[0]+=2;}
                        else
                            {choose[2]+=2;}
                        break;
                    case 3:
                        if (random.coin()==0)
                            {choose[0]+=2;}
                        else
                            {choose[1]+=2;}
                        break;
                    }
                }
            }
    }
}