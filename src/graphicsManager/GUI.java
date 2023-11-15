package graphicsManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import graphicsManager.GamePanel;

public class GUI {
	private final GamePanel gp;

// 	private final SpriteManager sm;

	private BufferedImage gameBoardCover;
	private BufferedImage shipPlacementScreen;

	public GUI(GamePanel gp) {
		this.gp = gp;

		try {
			gameBoardCover = ImageIO.read(new File("res/images/GUI_v1.png"));
			shipPlacementScreen = ImageIO.read(new File("res/images/ShipPlacementScreen.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawGameBoard(Graphics2D g2d) {
		g2d.drawImage(gameBoardCover, 0, 0, (int) (getBoardCoverWidth() * gp.spriteScaleMultiplier), (int) (getBoardCoverHeight() * gp.spriteScaleMultiplier), null);
	}

	public void drawShipPlacementScreen(Graphics2D g2d) {
		g2d.drawImage(shipPlacementScreen, 0, 0, (int) (getBoardCoverWidth() * gp.spriteScaleMultiplier), (int) (getBoardCoverHeight() * gp.spriteScaleMultiplier), null);
	}

	public void update() {

	}

	public int getBoardCoverWidth() {
		return gameBoardCover.getWidth();
	}

	public int getBoardCoverHeight() {
		return gameBoardCover.getHeight();
	}
}