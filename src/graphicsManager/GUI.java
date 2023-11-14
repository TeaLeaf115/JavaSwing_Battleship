package graphicsManager;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

import graphicsManager.GamePanel;

public class GUI {
	private final GamePanel gp;

// 	private final SpriteManager sm;

	private BufferedImage boardCover;

	public GUI(GamePanel gp) {
		this.gp = gp;

		try {
			boardCover = ImageIO.read(new File("res/images/GUI_v1.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(boardCover, 0, 0, gp.scaledTileSize, gp.scaledTileSize, null);
	}

	public void update() {

	}

	public int getBoardCoverWidth() {
		return boardCover.getWidth();
	}

	public int getBoardCoverHeight() {
		return boardCover.getHeight();
	}
}