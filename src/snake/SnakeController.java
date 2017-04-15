package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeController implements Runnable, KeyListener {
	
	SnakeView view;
	SnakeModel model;
	Thread thread;

	public SnakeController() {
		
		int numRows = 30;
		int numCols = 30;
		int width = 400;
		int height = 400;
		
		model = new SnakeModel(numCols, numRows);
		view = new SnakeView(width, height, numCols, numRows, this);
		
		view.setupSnakeAndApple(model.headX, model.headY, model.appleX, model.appleY);
		
		thread = new Thread(this);
		thread.run();
	
	}
	
	

	@Override
	public void run() {
		
		while(true)
		{
			if(model.isRunning)
			{
				if(model.update()){
					
					view.updateScene(model.headX, model.headY, model.toRemoveX, model.toRemoveY, model.appleX, model.appleY);
					view.repaint();					
				}
			}
			try { 
				Thread.sleep(100);
			}
			catch (Exception e) { /* ignore exception */ }
		}
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {

		switch(arg0.getKeyChar()){
		case 'w':
			model.changeDirection(0);
			break;
		case 'd':
			model.changeDirection(1);
			break;
		case 's':
			model.changeDirection(2);
			break;
		case 'a':
			model.changeDirection(3);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		new SnakeController();
	
	}


}
