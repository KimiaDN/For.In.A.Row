package FourInARow;

import java.util.Scanner;
/**
 * 1 = X = human palyer
 * 2 = O = computer player
 */

public class Main {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("Which one start the game ? ");
		System.out.println("1) palyer");
		System.out.println("2) computer");
		int starter = s.nextInt();
		
		Game game = new Game(6,7);
		game.printBoard();
		
		while(true) {
			if(starter == 1) {
				Player palyer = new Player();
				System.out.println("1 2 3 4 5 6 7");
				System.out.println("Choose a column (1-7) :");
				int choosenColumn = s.nextInt() - 1;
				//palyer.humanPlayer(, choosenColumn);
				
			}
			else if(starter ==2 ) {
				
			}

		}
		
	}

}
