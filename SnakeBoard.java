/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	
 *	@since	
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height][width];
		for(int r=0;r<height;r++){
			for(int c=0;c<width;c++){
				if((r==0||r==height-1)&&(c==0||c==width-1)){
					board[r][c]='+';
				}
				else if(r==0|| r == height-1){board[r][c]='-';}
				else if(c==0|| c == width-1){board[r][c]='';}
				else{board[r][c]=' ';}
				System.out.print(board[r][c]);
			}
			System.out.println();
		}
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		for(int r=0;r<border.length;r++){
			for(int c=0;c<border[0].length;c++)
				System.out.print(board[r][c]);
			System.out.println();
		}
	}
	
	/* Helper methods go here	*/
	
	/*	Accessor methods	*/

	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
