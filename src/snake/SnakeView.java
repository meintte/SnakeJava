package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeView extends JPanel{
	
	int prefWidth, prefHeight;
	int numCols, numRows;
	
	int[][] board;

	int squareSize;
	
	final static int BG = 0;
	final static int SNAKE = 1;
	final static int APPLE = -1;
	
	int tailX, tailY, headX, headY, appleX, appleY;
	

	public SnakeView(int width, int height, int numCols, int numRows, SnakeController controller) {

		this.prefWidth = width;
		this.prefHeight = height;
		this.numCols = numCols;
		this.numRows = numRows;
		this.squareSize = Math.min(this.prefHeight / this.numRows, this.prefWidth / this.numCols);
		
		JFrame frame = new JFrame("Snake");
		
		this.setPreferredSize(new Dimension(this.prefWidth, this.prefHeight));
		
		frame.add(this);
		frame.addKeyListener(controller);
		frame.pack();
		frame.setVisible(true);
		
		board = new int[numCols][numRows];
		
		for(int i = 0; i < this.numCols; i++){
			for(int j = 0; j < this.numRows; j++) {
				board[i][j] = BG;
			}
		}
		
	}
		
		@Override
		public void paint(Graphics g) {
			
			for(int x = 0; x < this.numCols; x++) {
				for(int y = 0; y < this.numRows; y++){
					
					switch(board[x][y]) {
					case SNAKE:
						g.setColor(Color.yellow);
						break;
					case APPLE:
						g.setColor(Color.red);
						break;
					case BG:
					default:
						g.setColor(Color.blue);
						break;
					}
					
					g.fillRect(x*this.squareSize, y*this.squareSize, squareSize, squareSize);
					
				}
			}
			
		}

		public void setupSnakeAndApple(int headX, int headY, int appleX, int appleY) {

			this.board[headX][headY] = SNAKE;
			this.board[appleX][appleY] = APPLE;
			
		}

		public void updateScene(int headX, int headY, int toRemoveX, int toRemoveY, int appleX, int appleY) {

			this.board[headX][headY] = SNAKE;
			this.board[toRemoveX][toRemoveY] = BG;
			this.board[appleX][appleY] = APPLE;
			
		}
	
}
