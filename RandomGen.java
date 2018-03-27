
package Lab4;

/*
 * Random Number Generator:
 *      This class generates various kinds of random numbers for
 *      the Computer's usage.
 */

public class RandomGen {
    
    // Generator for the Computer's random choice (Rock, Paper, Scissors)
    public int move()
    {
        int num = 0;
        int loop = 1;
        do {
            num = (int)(Math.random()*9);
            switch (num)
               {case 1: 
                case 2: 
                case 3:
                    num = 1;
                    return num;
                case 4: 
                case 5:
                case 6:
                    num = 2;
                    return num;
                case 7: 
                case 8:
                case 9:
                    num = 3;
                    return num;
                default: 
                    break;}
            } while (loop != 0);
        
        return num;
    }
    
    // Generator for a random number
    public int random()
    {
        int num = (int)(Math.random()*10);
        return num;
    }
    
    // Generator for a coin toss
    public int coin()
    {
        int coin;
        int num = (int)(Math.random()*101);
        
        coin = num%2;
        
        return coin;
    }
    
}
