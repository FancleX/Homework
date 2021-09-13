import java.util.Random;
import java.util.Scanner;

public class WhackAMole {
    int score = 0;
    int molesLeft = 0;
    int attemptsLeft;
    char[][] moleGrid;

    /* Constructor for the game, specifies total number of whacks allowed, 
    and the grid dimension. After reading through the assignment, 
    make sure to initialize the moleGrid with the appropriate character. */
    WhackAMole(int numAttempts, int gridDimension) {
        this.attemptsLeft = numAttempts;
        this.moleGrid = new char[gridDimension][gridDimension];
        for (int i = 0; i < moleGrid.length; i++) {
            for (int j = 0; j < moleGrid[i].length; j++) {
                moleGrid[i][j] = '*';
            }
        }
    }

    /**
     * Given a location, place a mole at that location. 
       Return true if you can. (Also update number of moles left.)            
     * @param x x-axis value
     * @param y y-axis value
     * @return  boolean
     */
    boolean place(int x, int y) {
        if (moleGrid[x][y] == 'M') return false;
        moleGrid[x][y] = 'M';
        molesLeft += 1;
        return true;
    }

    /**
     * Given a location, take a whack at that location. 
     * If that location contains a mole, the score, number of attemptsLeft, 
     * and molesLeft is updated. If that location does not contain a mole, 
     * only attemptsLeft is updated.
     * @param x x-axis
     * @param y y-axis
     */
    void whack(int x, int y) {
        if (moleGrid[x][y] == 'M') {
            score += 1;
            attemptsLeft -= 1;
            molesLeft -= 1;
            moleGrid[x][y] = 'w';
        }
        else attemptsLeft -= 1;
    }

    /**
     * Print the grid without showing where the moles are. 
     * For every spot that has recorded a “whacked mole,” print out a W, or * otherwise.
     */
    void printGridToUser() {
        for (int i = 0; i < moleGrid.length; i++) {
            for (int j = 0; j < moleGrid[i].length; j++) {
                if (moleGrid[i][j] == 'w') System.out.print('W');
                else System.out.print('*');
            }
            System.out.println();
        }
    }

    /**
     * Print the grid completely. This is effectively dumping the 2d array on the screen. 
     * The places where the moles are should be printed as an ‘M’. 
     * The places where the moles have been whacked should be printed as a ‘W’. 
     * The places that don’t have a mole should be printed as *.
     */
    void printGrid() {
        for (int i = 0; i < moleGrid.length; i++) {
            for (int j = 0; j < moleGrid[i].length; j++) {
                if (moleGrid[i][j] == 'M') System.out.print('M');
                else if (moleGrid[i][j] == 'w') System.out.print('W');
                else System.out.print('*');
            }
            System.out.println();
        }
    }

    /**
     * In order to play this game you need to create a main method. 
     * Begin by creating a 10 by 10 grid where you randomly place the moles. Place a total of 10 moles.
     * Now allow the user (remember how to use Scanner?) to enter the x and y coordinates of where they would like to take a whack. 
       Tell them they have a maximum of 50 attempts to get all the moles. 
     * At any point in the game, they can input coordinates of -1, -1 in order to indicate that they are giving up. 
       If the user gives up they get to see the entire grid.  
     * The game ends if the user is able to uncover all the moles or if the user runs out of attempts. 
     * @param args
     */
    public static void main(String[] args) {
        WhackAMole game = new WhackAMole(50, 10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            game.place(random.nextInt(10), random.nextInt(10));
        }
        System.out.println("WhackAMole game begins, you have a maximum of 50 attempts. Please input coordinates from 0 to 9.");
        Scanner scanner = new Scanner(System.in);
        while (game.attemptsLeft > 0 && game.molesLeft > 0) {
            game.printGridToUser();
            System.out.println("Where would you like to take a whack?");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (x == -1 && y == -1) {
                game.printGrid();
                break;
            }
            game.whack(x, y);
            game.printGridToUser();
            System.out.println("Your scores: " + game.score);
            System.out.println("Attemps left: " + game.attemptsLeft);
        }
        scanner.close();
        System.out.println("Game Over.");
    }
}
