import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    // Variables
    int row, column, mineNumber, a, b, count, randomNumber, randomNumber2;
    String[][] mineMap, gameMap;
    boolean isTrue = true;

    // Define Classes
    Random random = new Random();
    Scanner input = new Scanner(System.in);

    MineSweeper(int row, int column) {
        this.row = row;
        this.column = column;
        this.gameMap = new String[row][column];
        this.mineMap = new String[row][column];
        this.mineNumber = ((row * column) / 4);
    }

    void mineMap() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                mineMap[i][j] = "-";
                gameMap[i][j] = "-";
            }
        }
    }

    void randomNumber() {
        for (int i = 0; i < this.mineNumber; i++) {
            randomNumber = random.nextInt(this.row);
            randomNumber2 = random.nextInt(this.column);
            if (!this.mineMap[randomNumber][randomNumber2].equals("*"))
                this.mineMap[randomNumber][randomNumber2] = "*";
        }
    }

    public void printMineMap() {
        System.out.println("Mines location:");
        randomNumber();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (!this.mineMap[i][j].equals("*")) {
                    this.mineMap[i][j] = "-";
                }
                System.out.print(this.mineMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================================");
        System.out.println("Welcome To Mine Sweeper Game!");
        printGameMap();
    }

    public void printGameMap() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.gameMap[i][j] = "-";
                System.out.print(this.gameMap[i][j] + " ");
            }
            System.out.println();

        }
    }

    public void indexSelection() {
        isTrue = false;
        while (!isTrue) {
            System.out.print("Enter a row: ");
            a = input.nextInt();
            System.out.print("Enter a column: ");
            b = input.nextInt();
            if (a > row || a < 0 || b > column || b < 0) {
                System.out.println("You have selected outside the map borders. Enter again!");
                continue;
            }
            if (mineMap[a][b].equals("*")) {
                System.out.println("Game Over!");
                break;

            }
            control();
            if (finish()) {
                System.out.println("You Win! :)))))");
                break;
            }
        }
    }

    public void control() {
        count = 0;
        for (int i = (a - 1); i <= (a + 1); i++) {
            for (int j = (b - 1); j <= (b + 1); j++) {
                if (i < 0 || j < 0 || i >= this.row || j >= this.column) {
                    continue;
                }
                if (this.mineMap[i][j].equals("*")) {
                    count++;
                }
            }
        }

        this.gameMap[a][b] = String.valueOf(count);
        this.mineMap[a][b] = String.valueOf(count);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.gameMap[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public boolean finish() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.mineMap[i][j].equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void run() {
        mineMap();
        printMineMap();
        indexSelection();
    }
}
