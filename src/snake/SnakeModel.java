package snake;

public class SnakeModel {
	
	public boolean isRunning = true;
	
	int widht, height;
	int[][] board;
	
	final static int UP = 0;
	final static int RIGHT = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;
	final static int APPLE = -1;
	
	int appleIncrease = 1;
	
	int currentDirection = RIGHT;
	public int headX, headY, appleX, appleY, toRemoveX, toRemoveY;
	int snakeLength = 1;
	
	public SnakeModel(int numCols, int numRows) {
		
		this.widht = numCols;
		this.height = numRows;
		
		board = new int[this.widht][this.height];
		
		for(int x = 0; x < this.widht; x++) {
			for(int y = 0; y < this.height; y++) {
				board[x][y] = 0;
			}
		}
		
		headX = this.widht/2;
		headY = this.height/2;
		board[headX][headY] = snakeLength;

		generateApple();
		
	}
	
	public void changeDirection(int direction) {
		
		switch(direction) {
		case UP:
			if(currentDirection != DOWN) {
				currentDirection = UP;				
			}
			break;
		case DOWN:
			if(currentDirection != UP) {
				currentDirection = DOWN;				
			}
			break;
		case LEFT:
			if(currentDirection != RIGHT) {
				currentDirection = LEFT;				
			}
			break;
		case RIGHT:
		default:
			if(currentDirection != LEFT) {
				currentDirection = RIGHT;				
			}
			break;
		}
		
	}
	
	public boolean update() {
	
		for(int x = 0; x < this.widht; x++) {
			for(int y = 0; y < this.height; y++) {
				
				if( board[x][y] > 0 ) {
					if(board[x][y] == 1) {
						toRemoveX = x;
						toRemoveY = y;
					}
					board[x][y]--;
				};
			}
		}
		
		switch(currentDirection) {
		case UP:
			headY -= 1;
			break;
		case DOWN:
			headY += 1;
			break;
		case LEFT:
			headX -= 1;
			break;
		case RIGHT:
		default:
			headX += 1;				
			break;
		}
		
		if((this.headX >= 0 && this.headX < this.widht) && (this.headY >= 0 && this.headY < this.height)) {
			
			switch(this.board[headX][headY]) {
			case APPLE:
				this.snakeLength += appleIncrease;
				this.board[headX][headY] = snakeLength;		
				generateApple();
			case 0:
				this.board[headX][headY] = snakeLength;
				break;
			default:
				isRunning = false;
				System.out.println("GAME OVER");
				return false;
			}
		} else {
			isRunning = false;
			System.out.println("GAME OVER");		
			return false;
		}
		
		return true;
		
	}
	
	private void generateApple() {
		int x = (int) Math.floor(Math.random()*this.widht);
		int y = (int) Math.floor(Math.random()*this.height);

		if(this.board[x][y] != 0) {
			generateApple();
		} else {
			this.board[x][y] = APPLE;
			this.appleX = x;
			this.appleY = y;
		}
	}

}
