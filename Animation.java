
package Lab4;

import java.awt.*;
import javax.swing.*;

/*
 * Animation:
 *      This class displays the results of a round via an ASCII
 *      character animation.
 */

public class Animation {

//Constructors for JFrame, Win Calculator, Speech, and Random Number Generator
    static JFrame j = new JFrame("digiRPS - Results");
    static WinCalc wincalc = new WinCalc();
    static Speech aiSays = new Speech();
    static RandomGen random = new RandomGen();

//Static variables to create and update the portions of the JLabel's text
    static String top;
    static String resultText;
    static String bottom;
    static String text=top + resultText + bottom;

//Construct JLabel after text variables have been initialized
    static JLabel label = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>", SwingConstants.CENTER);
    
//Variables to hold the User and Computer's last moves
    static int uMove=0;
    static int cMove=0;
    
    //Hub method
    public void start(int uMove, int cMove, boolean hard)
    {
        this.uMove=uMove;
        this.cMove=cMove;
        
        top="_";
        resultText="<br>Results<br>";
        bottom="_";
        
        //This block of code was copied from a help question on Stack Exchange
        fontSizer(12); //This method was not in the original code, but the line it executes was
        j.add(label);
        j.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        j.pack();
        j.setSize(500, 350);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
        //This block of code was copied from a help question on Stack Exchange
        
        animate(hard);
    }
    
    //Method to enact and display the animation
    public void animate(boolean hard)
    {
        //Pre-Results Animation
        try {
                text="ROCK";
                fontSizer(14);
                repainter();
                Thread.currentThread().sleep(425);
                
                text="PAPER";
                fontSizer(16);
                repainter();
                Thread.currentThread().sleep(425);
                
                text="SCISSORS";
                fontSizer(18);
                repainter();
                Thread.currentThread().sleep(425);
                
                text="SHOOT!";
                fontSizer(28);
                repainter();
                Thread.currentThread().sleep(550);
                
                text="SH&nbsp;&nbsp;T!";
                repainter();
                Thread.currentThread().sleep(75);
                
                text="S&nbsp;&nbsp;&nbsp;&nbsp;!";
                repainter();
                Thread.currentThread().sleep(75);
                
                text="";
                repainter();
                Thread.currentThread().sleep(200);
        } catch(InterruptedException e) {}
        
        //Reset font
        fontSizer(12);
        
        //Animate Result's page header        
        for (int frame=0; frame<11; frame++)
            {
                try {
                        top+="___";
                        bottom+="___";
                        text=top + resultText + bottom + "<br><br><br><br><br><br><br><br><br><br><br>";
                        repainter();
                        Thread.currentThread().sleep(25);
                    } catch(InterruptedException e){}
            }
        
        //Store Results header in additional variable for reuse
        text=text=top + resultText + bottom + "<br><br>";
        String textTemp=text;
        String resultsFrame="";
        
        //Results Display animation        
        try {
            for (int frame=1; frame<4; frame++)
                
                {resultsFrame=outline(frame);
                 text=textTemp+resultsFrame;
                 repainter();
                 
                 if (frame==1)
                    {Thread.currentThread().sleep(600);}
                 else
                    {Thread.currentThread().sleep(500);}
                }
            
        } catch (InterruptedException e){}
        
        //Additional Dialogue Animation
        try {
            text+="<br>" + findWinner();
            repainter();
            Thread.currentThread().sleep(800);
            
            text+="<br> Computer says: ";            
            repainter();
            Thread.currentThread().sleep(800);
            
            text+="\"" + ((!hard)?
                    ((random.coin()==0)?"Well Played!":"Good Game!")
                        :aiSays.taunt(2, cMove, uMove)) + "\"";
            repainter();
            Thread.currentThread().sleep(1000);
            
        } catch (InterruptedException e){}
    }
    
    //Method to repaint the JLabel when called
    public void repainter()
    {
        this.label.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
    }
    
    //Method to change font size
    public void fontSizer(int size)
    {
        label.setFont(new Font("Monospaced", Font.PLAIN, size));
    }
    
    //Method to determine and display the moves of the User and Computer
    public String filler(int choose, int move)
    {
        String frame="";
        
        switch (choose)
            {case 0:
                frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                break;
            case 1:
                switch (move)
                    {case 1: //Scissors
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/\\/\\&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    case 2: //Paper
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;__________";
                        break;
                    case 3: //Rock
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;____&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    }
                break;
            case 2:
                switch (move)
                    {case 1: //Scissors
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\\&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    case 2: //Paper
                        frame="&nbsp;&nbsp;&nbsp;/&nbsp;~~~&nbsp;&nbsp;~&nbsp;&nbsp;/";
                        break;
                    case 3: //Rock
                        frame="&nbsp;&nbsp;&nbsp;_/&nbsp;_&nbsp;&nbsp;\\_&nbsp;&nbsp;&nbsp;";
                        break;
                    }
                break;
            case 3:
                switch (move)
                    {case 1: //Scissors
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;\\&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    case 2: //Paper
                        frame="&nbsp;&nbsp;/&nbsp;~&nbsp;~~~~&nbsp;&nbsp;/&nbsp;";
                        break;
                    case 3: //Rock
                        frame="&nbsp;&nbsp;/&nbsp;-&nbsp;&nbsp;&nbsp;-_&nbsp;\\&nbsp;&nbsp;";
                        break;
                    }
                break;
            case 4:
                switch (move)
                    {case 1: //Scissors
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;/\\&nbsp;\\&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    case 2: //Paper
                        frame="&nbsp;/&nbsp;~~&nbsp;&nbsp;~~~&nbsp;/&nbsp;&nbsp;";
                        break;
                    case 3: //Rock
                        frame="&nbsp;{&nbsp;&nbsp;_&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;}&nbsp;";
                        break;
                    }
                break;
            case 5:
                switch (move)
                    {case 1: //Scissors
                        frame="&nbsp;&nbsp;&nbsp;&nbsp;\\/&nbsp;&nbsp;\\/&nbsp;&nbsp;&nbsp;&nbsp;";
                        break;
                    case 2: //Paper
                        frame="/_________/&nbsp;&nbsp;&nbsp;";
                        break;
                    case 3: //Rock
                        frame="&nbsp;&nbsp;\\__-_____/&nbsp;&nbsp;";
                        break;
                    }
                break;
            default:
                frame="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                break;
            }
        
        return frame;
    }
    
    //Method to display the blank result's page
    public String outline(int count)
    {
        String outerFrame="";
        
        switch (count)
            {case 1:
                outerFrame="&nbsp;&nbsp;&nbsp;______________&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;______________<br>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;User&nbsp;Plays&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|Computer&nbsp;Plays|<br>" +
                "&nbsp;&nbsp;|--------------| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|--------------|<br>" +
                "&nbsp;&nbsp;|" + filler(0, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(0, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(0, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(0, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(0, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|______________| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|______________|";
                break;
            case 2:
                outerFrame=
                "&nbsp;&nbsp;&nbsp;______________&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;______________<br>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;User&nbsp;Plays&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|Computer&nbsp;Plays|<br>" +
                "&nbsp;&nbsp;|--------------| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|--------------|<br>" +
                "&nbsp;&nbsp;|" + filler(1, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(2, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(3, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(4, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(5, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(0, cMove) + "|<br>" +
                "&nbsp;&nbsp;|______________| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|______________|";
                break;
                
            case 3:
                outerFrame=
                "&nbsp;&nbsp;&nbsp;______________&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;______________<br>" +
                "&nbsp;&nbsp;|&nbsp;&nbsp;User&nbsp;Plays&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|Computer&nbsp;Plays|<br>" +
                "&nbsp;&nbsp;|--------------| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|--------------|<br>" +
                "&nbsp;&nbsp;|" + filler(1, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(1, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(2, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(2, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(3, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(3, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(4, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(4, cMove) + "|<br>" +
                "&nbsp;&nbsp;|" + filler(5, uMove) + "| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|" + filler(5, cMove) + "|<br>" +
                "&nbsp;&nbsp;|______________| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;|______________|";
                break;
            }
            
        return outerFrame;
    }
    
    //Method to determine which victor should be declared in the display
    public String findWinner()
        {
            String winner="";
            
            String userName = WinCalc.userName;
            int Win = WinCalc.Win;
        
            switch (Win)
                {
                case 1: 
                case 2: 
                case 3: 
                    winner = "It's a tie!";
                    break;
                case 4: 
                case 5: 
                case 6: 
                    winner = userName + " won!";
                    break;
                case 7: 
                case 8: 
                case 9: 
                    winner = "Computer won!";
                    break;
                }
        
            return winner;
        }
}
