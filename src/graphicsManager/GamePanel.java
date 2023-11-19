package graphicsManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gameLogic.Board;
import gameLogic.GameStates;
import gameLogic.Ship;
import gameLogic.Ship.Rotation;
import gameLogic.Ship.ShipType;

import tile.TileManager;

import static gameLogic.GameStates.*;

public class GamePanel extends JPanel implements Runnable {
	// -----------------
	// SCREEN SETTINGS
	// -----------------

	// Each tile on the map has a default texture resolution of 16x16 pixels.
	final int defaultTileSize = 16;
	// How many times we scale the sprite to match modern screen graphics.
	final double spriteScaleMultiplier = 1.8;

	// The upscale multiplier of the sprite tiles.
	public final int scaledTileSize = (int)Math.ceil(defaultTileSize * spriteScaleMultiplier);

	// The amount of columns and rows for the board.
	public final int maxBoardCol = 10;
	public final int maxBoardRow = 10;

	// The pixel amount for the board size.
	final int boardWidth = scaledTileSize * maxBoardCol;
	final int boardHeight = scaledTileSize * maxBoardRow;

	// The thread that the game will be run on.
	Thread gameThread;

	// How many Frames Per Second (FPS) the game screen will be updates.
	final int FPS = 60;

	// Creates the two boards for the computer and player.
	public Board playerBoard = new Board();
	public Board compBoard = new Board();

	// Sets the sprites for the GUI aspects of the game.
	TileManager tileM = new TileManager(this);
	GUI gui = new GUI(this);

	// The different game states for the game.
	public GameStates gameState;

	public GamePanel() {
		this.setPreferredSize(new Dimension((int) (gui.screenCoverWidth() * spriteScaleMultiplier), (int) (gui.screenCoverHeight() * spriteScaleMultiplier)));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void setupGame() {
		gameState = SHIP_PLACEMENT;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// 1 Billion nsec or 1 sec divided by the amount of FPS, giving us how many FPS we will get in nanoseconds.
		double drawInterval = 1000000000/*nanoseconds*/ / FPS;
		// 'delta' id the time period between the last frame and the current frame.
		double delta = 0;
		// The time, in nanoseconds, as the "last frame" to start the game loop.
		long lastFrame = System.nanoTime();
		// The declaration of the 'currentFrame' variable, used to calculate the delta time in the game loop.
		long currentFrame;

		// While the 'gameThread' is running, do the loop.
		while (gameThread != null) {
			// Sets the current time, in nanoseconds, for the current frame to calculate 'delta'.
			currentFrame = System.nanoTime();
			// Adds the time difference between 'currentFrame' and 'lastFrame', then divides it by the drawing interval to 'delta'.
			delta += (currentFrame - lastFrame) / drawInterval;
			// Sets time of the last frame to what the time of the current frame was.
			lastFrame = currentFrame;

			// If 'delta' is greater than or equal to 1, then update the display and subtract one from 'delta'.
			if (delta >= 1) {
				update();
				repaint();
				// System.out.println(delta);
				delta--;
			}
		}
	}

	public void update() {
		if (gameState == GAMEPLAY) {
			playerBoard.addShip(new Ship(2, 2, ShipType.CARRIER, 0, Rotation.RIGHT));
			playerBoard.addShip(new Ship(2, 5, ShipType.DESTROYER, 0, Rotation.DOWN));
			compBoard.hit(2, 5);
			playerBoard.miss(0, 0);
			tileM.update();
		}
		if (gameState == PAUSED) {
			System.out.println("Game is Paused.");
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

//		tileM.drawPlayer(g2d, 8, 3);
//		tileM.drawComp(g2d, 8, 15);

//		g.setColor(new Color(0, 0, 0, 81));
//		for (int i = 48; i < 480; i+=48) {
//			g.drawLine(i, 0, i, boardHeight);
//		}
//		for (int i = 48; i < 480; i+=48) {
//			g.drawLine(0, i, boardWidth, i);
//		}

//		gui.drawGameBoard(g2d);
		gui.drawShipPlacementScreen(g2d);

		g2d.dispose();
	}
}