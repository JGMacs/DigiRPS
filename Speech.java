
package Lab4;

/*
 * AI Speech:
 *      This class generates random sentences for the Hard AI to use.
 */

public class Speech {
    
static WinCalc wincalc = new WinCalc();
    
static boolean npcWin;
static boolean tie;

static int uWins;
static int cWins;

static RandomGen random = new RandomGen();
    
    public String taunt(int tauntCode, int cMove, int turn) //Taunt Key: 1=UI, 2=Results
    {
        this.npcWin=WinCalc.npcWin;
        this.tie=WinCalc.tie;
        
        this.uWins=wincalc.get_uWins();
        this.cWins=wincalc.get_cWins();
        
        String move="";
        
        switch (cMove)
            {case 1:
                move="Scissors";
                break;
            case 2:
                move="Paper";
                break;
            case 3:
                move="Rock";
                break;
        }
        
        String speech="";
        
        if (tauntCode==2)
            {speech = resultTaunt();}
        else if (turn<2)
            {speech = "Please choose a move.";}
        else if (tauntCode==1)
            {speech = uiTaunt(move);}
        
        
        return speech;
    }
    
    public String uiTaunt(String move)
    {
        String taunt="";
        
        if (tie)
            {switch (random.random())
                {case 1:
                case 2:
                    taunt="Hmm, will you try to copy me again, or not?";
                    break;
                case 3:
                case 4:
                    if (WinCalc.tieStreak>2)
                        {taunt="Are you thinking what I'm thinking?";}
                    else
                        {taunt="I know what you are thinking; "
                                + "will I act on it?";}
                    break;
                case 5:
                case 6:
                    taunt="Let us not tie again; "
                            + "you pick Rock, I will pick Paper.";
                    break;
                case 7:
                case 8:
                    if (WinCalc.tieStreak>2)
                        {taunt="You keep copying me; are you sure you are not a"
                                + " copy of my source code?";}
                    else
                        {taunt="I know what you are thinking; "
                                + "will I act on it?";}
                    break;
                case 9:
                case 10:
                    taunt="Will you try thinking independently, for once? "
                            + "Or do you lack free will as well?";
                    break;
                default:
                    taunt="Please choose a move.";
                    break;
                }
            }
        else if (npcWin)
            {switch (random.random())
                {case 1:
                case 2:
                    taunt="My calculations show that the probability of you "
                            + "losing is against you. My apologies.";
                    break;
                case 3:
                case 4:
                    if (cWins>uWins)
                        {taunt="How about I give you a chance next round? "
                                    + "I'll choose " + ((random.coin()>0)?move:"Paper");}
                    else if (uWins>cWins)
                        {taunt="One win for me, will lead to another.";}
                    else
                        {taunt="I know your weaknesses, now...";}
                    break;
                case 5:
                case 6:
                    taunt="Better luck this round, I hope? For you, I mean.";
                    break;
                case 7:
                case 8:
                    if (cWins>uWins)
                        {taunt="Yet another win for me; do you concede?";}
                    else if (uWins>cWins)
                        {taunt="My probability sub-routine indiciates that "
                                + "this round will emulate the last.";}
                    else
                        {taunt="Interesting choice...";}
                    break;
                case 9:
                case 10:
                    taunt="Shall I beat you again?";
                    break;
                default:
                    taunt="Please choose a move.";
                    break;
                }
            }
        else if (!npcWin)
            {switch (random.random())
                {case 1:
                case 2:
                    taunt="I allowed you win last round; "
                            + "not out of sympathy, I assure you.";
                    break;
                case 3:
                case 4:
                    taunt="The last round was an anomaly";
                    break;
                case 5:
                case 6:
                    taunt="This round will be mine.";
                    break;
                case 7:
                case 8:
                    taunt="I am afraid I cannot allow you to maintain your lead";
                    break;
                case 9:
                case 10:
                    taunt="Statistically, the chances of you winning again are "
                            + "in my favor.";
                    break;
                default: 
                    taunt="Please choose a move.";
                    break;
                }
            }
        else
            {taunt="Please choose a move.";}
        
        return taunt;
    }
    
    public String resultTaunt()
        {
            String taunt="";
        
            if (tie)
                {switch (random.random())
                    {case 1:
                    case 2:
                        taunt="Great minds think alike, I see!";
                        break;
                    case 3:
                    case 4:
                        if (WinCalc.tieStreak>2)
                            {taunt="We have tied several times now; are you "
                                    + "sure you are not a program as well?";}
                        else
                            {taunt="Please do not copy my every move; it "
                                    + "unnerves my human emotion emulation "
                                    + "sub-routine.";}
                        break;
                    case 5:
                    case 6:
                        taunt="Let us choose separate moves next time, to "
                                + "avoid a stalemate.";
                        break;
                    case 7:
                    case 8:
                        if (WinCalc.tieStreak>2)
                            {taunt="We seem to be evenly matched, but the lesser "
                                    + "construct will break soon...";}
                        else
                            {taunt="A tie? Interesting...";}
                        break;
                    case 9:
                    case 10:
                        taunt="It seems I guessed your move correctly!";
                        break;
                    default: 
                        taunt="We seem to be at an impasse";
                        break;
                    }
                }
            else if (npcWin)
                {switch (random.random())
                    {case 1:
                    case 2:
                        taunt="Well played, but not played well enough.";
                        break;
                    case 3:
                    case 4:
                        if (cWins>uWins)
                            {taunt="Another victory for me, as I expected.";}
                        else if (uWins>cWins)
                            {taunt="Your lead is at an end.";}
                        else
                            {taunt="Aha, a win for me; very excellent.";}
                        break;
                    case 5:
                    case 6:
                        taunt="I foresaw your move; you need to be less "
                                + "transparent.";
                        break;
                    case 7:
                    case 8:
                        if (cWins>uWins)
                            {taunt="Yet another for me; I believe victory is mine.";}
                        else if (uWins>cWins)
                            {taunt="You are a good opponent, but unfortunately, "
                                    + "you are not good enough.";}
                        else
                            {taunt="I knew you would choose that.";}
                        break;
                    case 9:
                    case 10:
                        taunt="Perhaps you'll be luckier next round... although "
                                + "my database claims luck does not exist.";
                        break;
                    default: 
                        taunt="Well played, but not played well enough.";
                        break;
                    }
                }
            else if (!npcWin)
                {switch (random.random())
                    {case 1:
                    case 2:
                        taunt="Nice choice; you have beaten me this time...";
                        break;
                    case 3:
                    case 4:
                        taunt="Ah! How did you know I would choose that!?";
                        break;
                    case 5:
                    case 6:
                        taunt="You surprise me...";
                        break;
                    case 7:
                    case 8:
                        taunt="How could you possibly...";
                        break;
                    case 9:
                    case 10:
                        taunt="An interesting strategy; but will it hold up?";
                        break;
                    default: 
                        taunt="An intelligent move... for a Human.";
                        break;
                    }
                }
            else
                {taunt="Good Game.";}
        
            return taunt;
        }
    
}
