package FourInARow;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.Icon;

public class Player {
	
	public String palyerName;
	public int best_column ; 
	
	
	public void addMoves(Cells[][] cells, int col, String playerIcon) { ////&&&&&&/////5
		
		
		for(int i = 5 ; i >= 0 ; i-- ) { 
			if(cells[i][col].player_icon.equals(" ")) {
				cells[i][col].player_icon = playerIcon;      
				cells[i][col].lastAddition = true;
				break;
			}
		}
	}

	public boolean checkForWinner( Cells[][] cells, String playerIcon) {   ////&&&&&&/////
		
		// hirozonal winner
		for(int i = 0; i < 6 ; i++) {		//6
			String[] array = getRow(i, cells);
			for(int j = 0; j < 4; j++) {
				String[] newArray = Arrays.copyOfRange(array,j, j + 4);
				if(countIcon(newArray, playerIcon) == 4) {
					return true;
				}
			}
			
		}
		
		// vertival winner
		for(int i = 0; i < 7 ; i++) {		//7
			String[] array = getColumn(i, cells);
			for(int j = 0; j < 3; j++) {
				String[] newArray = Arrays.copyOfRange(array,j, j + 4);
				if(countIcon(newArray, playerIcon) == 4) {
					return true;
				}
			}
		}
		
		// check diagonal Rigth
		for(int i = 5 ; i > 2 ; i--) {	
			for(int j = 0 ; j < 4 ; j++) {
				String[] array = new String[4];
				for(int z = 0 ; z < 4 ; z++) {
					array[z] = cells[i - z][j + z].player_icon;
				}
				if(countIcon(array, playerIcon) == 4) {
					return true;
				}
				
			}
		}
		
		// check diagonal left
		for(int i = 5 ; i > 2 ; i--) {
			for(int j = 6 ; j > 2 ; j--) {
				String[] array = new String[4];
				for(int z = 0 ; z < 4 ; z++) {
					array[z] = cells[i - z][j - z].player_icon;
				}
				if(countIcon(array, playerIcon) == 4) {
					return true;
				}
			}
		}
		
		return false ;
	}
	public boolean checkForEqual(Cells[][] cells) {        ////&&&&&&/////7
		
		for(int i = 0; i < 7; i++) {
			if(cells[0][i].player_icon.equals(" ")) {
				return false;
			}
		}
		return true;
	}
		
	
	public String[] getRow(int row, Cells[][] cells ) {   ////&&&&&&/////7
		
		String[] array = new String[7];
		for(int i = 0; i < 7 ; i++) {
			array[i] = cells[row][i].player_icon;
		}
		return array;
	}
	public String[] getColumn(int col, Cells[][] cells ) {  ////&&&&&&/////6
		
		String[] array = new String[6];
		for(int i = 0; i < 6 ; i++) {
			array[i] = cells[i][col].player_icon;
		}
		return array;
	}
	public int countIcon(String[] array,String playerIcon) {
		int counter = 0;
		for(int i = 0; i < array.length ; i++) {
			if(array[i].equals(playerIcon)) {
				counter ++;
			}
		}
		return counter;
	}
	public int countEmpty(String[] array) {
		int counter = 0;
		for(int i = 0; i < array.length ; i++) {
			if(array[i].equals(" ")) {
				counter ++;
			}
		}
		return counter;
	}
	
	public int getScore(String[] array, int color) {
		int score = 0;

		if(color == 1) {
			if(countIcon(array, "o") == 4) {
				score = score + 10000;
			}
			else if (countIcon(array, "o") == 3 && countEmpty(array) == 1) {
				score = score + 100;
			}
			else if (countIcon(array, "o") == 2 && countEmpty(array) == 2) {
				score = score + 50;
			}
			else if (countIcon(array, "x") == 3 && countEmpty(array) == 1) {
				score = score - 200;
			}
		}
		else if(color == -1) {
			if(countIcon(array, "x") == 4) {
				score = score + 10000;
			}
			else if (countIcon(array, "x") == 3 && countEmpty(array) == 1) {
				score = score + 100;
			}
			else if (countIcon(array, "x") == 2 && countEmpty(array) == 2) {
				score = score + 50;
			}
			else if (countIcon(array, "o") == 3 && countEmpty(array) == 1) {
				score = score - 200;
			}
		}
		
		return score;
	}
	public boolean checkCenterScore(Cells[][] cells, int color) {
		
		String[] col1 = new String[4];
		String[] col2 = new String[4];
		
		col1 = getColumn(2, cells);
		for(int i = 0 ; i < 4 ; i++) {
			if(color == 1) {
				if(col1[i] == "x") {
					return false;
				}
				if(col1[i] == "o") {
					return true;
				}
			}
			else if(color == -1) {
				if(col1[i] == "o") {
					return false;
				}
				if(col1[i] == "x") {
					return true;
				}
			}
		}
		return false;
	}
	
	public int scorePossition(Cells[][] cells, int color) {     ////&&&&&&/////
		
		int score = 20;
		
		// check horizonal
		for(int i = 0; i < 6 ; i++) {
			String[] array = new String[7];
			array =	getRow(i, cells);
			for(int j = 0; j < 4; j++) {
				String[] newArray = Arrays.copyOfRange(array,j, j + 4);
				score = score + getScore(newArray,color);
			}
		}
		
		// check vertical
		for(int i = 0; i < 7 ; i++) {
			String[] array = getColumn(i, cells);
			for(int j = 0; j < 3 ; j++) {
				String[] newArray = Arrays.copyOfRange(array,j, j + 4);
				score = score + getScore(newArray,color);
			}
		}
		
		// check diagonal rigth
		for(int i = 5 ; i > 2 ; i--) {
			for(int j = 0 ; j < 4 ; j++) {
				String[] array = new String[4];
				for(int z = 0 ; z < 4 ; z++) {
					array[z] = cells[i - z][j + z].player_icon;
				}
				score = score + getScore(array,color);
			}
		}
		
		// check diagonal left
		for(int i = 5 ; i > 2 ; i--) {
			for(int j = 6 ; j > 2 ; j--) {
				String[] array = new String[4];
				for(int z = 0 ; z < 4 ; z++) {
					array[z] = cells[i - z][j - z].player_icon;
				}
				score = score + getScore(array,color);
			}
		}
		
		return score ;
	}
	public ArrayList<Integer> getValidColumns(Cells[][] cells){   ////&&&&&&/////
		
		ArrayList<Integer> validCols = new ArrayList<Integer>();
		for(int i = 0 ; i < 7; i++) {
			if(cells[0][i].player_icon.equals(" ")) {
				validCols.add(i);
			}
		}
		return validCols;
	}
	
	public Cells[][] getCoppy(Cells[][] cells) {  ////&&&&&&/////
		
		Cells[][] cells_coppy = new Cells[6][7];
		for(int i = 0 ; i < 6 ; i++) {
			for(int j = 0 ; j < 7 ; j++) {
				cells_coppy[i][j] = new Cells();
				cells_coppy[i][j].x = cells[i][i].x;
				cells_coppy[i][j].y = cells[i][j].y;
				cells_coppy[i][j].player_icon = cells[i][j].player_icon;
			}
		}
		return cells_coppy;
	}
	
	public boolean isTerminal(Cells[][] cells) {
		if(checkForEqual(cells) || checkForWinner(cells, "x") || checkForWinner(cells, "o")) {
			return true;
		}
		return false;
	}
	public int[] negaMax(Cells[][] cells,int depth, int alpha, int beta, int color) {  
		// color = 1 --> AI , color = -1 --> human
		
		int[] array = new int[2];  // array[0] --> column, array[1] --> value
		
		String playerIcon ;
		if(color == -1) {
			playerIcon = "x";
		}
		else {
			playerIcon = "o";
		}
		
		if(isTerminal(cells) || depth == 0) {
			if(isTerminal(cells)) {
				if(checkForWinner(cells,"o")) {  // if computer win
					if(color == 1) {
						array[1] = 10000;
						return array;
					}
					else {
						array[1] = -10000;
						return array;
					}
					
				}
				else if(checkForWinner(cells,"x")) {   //if humnan win
					if(color == 1) {
						array[1] = -10000;
						return array;
					}
					else {
						array[1] = 10000;
						return array;
					}
					
				}
				else {    // if no one win
					array[1] = 0;
					return array;
				}
			}
			else {   // if depth == 0
				array[1] = scorePossition(cells, color) ;
				return array;
			}
		}
	
		
		// if we are in the middle of the tree
		int value = Integer.MIN_VALUE ;
		
		ArrayList<Integer> validCols = getValidColumns(cells);
		
		for(int i = 0 ; i < validCols.size() ;i++) {
			Cells[][] coppy = getCoppy(cells);
			addMoves(coppy, validCols.get(i), playerIcon);
			
			int[] return_value = new int[2];
			int new_alpha = -(beta) ;
			int new_beta = - (alpha);
			return_value = negaMax(coppy, depth - 1,new_alpha, new_beta, -color);
			
			if (- return_value[1] > value) {
				value = - return_value[1];
				alpha = value;
				best_column = validCols.get(i);
				
				array[1] = value;
				array[0] = best_column;
			}
			
			if(alpha >= beta) {
				break;
			}
		}
		return array ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
