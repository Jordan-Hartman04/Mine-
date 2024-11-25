import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
public class MineModel implements Serializable{
	
	private int numRows = 10;
    private int numCols = 10;
    private int numMines = 15;
	private final int mine = -1;
	
    private int[][] mineField;
    private boolean[][] revealed;

    private boolean gameOver = false;
    private boolean gameWon = false;
	
	public MineModel(){
		
	}
	public boolean[][] getRevealed(){
		return revealed;
	}
	public void setRevealed(boolean f){
		for(int i=0;i<revealed.length;i++){
			for(int j=0;j<revealed[0].length;j++){
				revealed[i][j] = f;
			}
		}
	}
	public int getNumMines(){
		return numMines;
	}
	public int getNumRows(){
		return numRows;
	}
	public int getNumCols(){
		return numCols;
	}
	public void setNumMines(int x){
		 numMines = x;
	}
	public void setNumRows(int x){
		 numRows = x;
	}
	public void setNumCols(int x){
		 numCols = x;
	}
	
public void setMineField() {
    mineField = new int[numRows][numCols];
    revealed = new boolean[numRows][numCols];
    Random rnd = new Random();
    int mineCounter = getNumMines();

    while (mineCounter > 0) {
        int randomRows = rnd.nextInt(getNumRows()); 
        int randomCols = rnd.nextInt(getNumCols()); 

        if (mineField[randomRows][randomCols] != mine) {
            mineField[randomRows][randomCols] = mine;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int row = randomRows + i;
                    int col = randomCols + j;

                    if (row >= 0 && row < numRows && col >= 0 && col < numCols && mineField[row][col] != mine) {
                        mineField[row][col] += 1;
                    }
                }
            }
        
            mineCounter--;
        }
    }
}
	public void setMineField(int x, int y, int z) {
    numRows = x;
    numCols = y;
    numMines = z;
    mineField = new int[x][y];
    revealed = new boolean[x][y];
    Random rnd = new Random();
    int mineCounter = z;

    while (mineCounter > 0) {
        int randomRows = rnd.nextInt(x); 
        int randomCols = rnd.nextInt(y); 

        
        if (mineField[randomRows][randomCols] != -1) {
            mineField[randomRows][randomCols] = -1; 

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int row = randomRows + i;
                    int col = randomCols + j;

                    if (row >= 0 && row < x && col >= 0 && col < y && mineField[row][col] != -1) {
                        mineField[row][col] += 1;
                    }
                }
            }

            mineCounter--;
        }
    }
}
	public int[][] getMineField(){
		return mineField;
	}
	
	public void resetMines(){
		mineField = new int[0][0];
		 gameOver = false;
	}
	public void gameOver(){
		gameOver = true;
	}
	
	public boolean getGameOver(){
		return gameOver;
	}
	

}