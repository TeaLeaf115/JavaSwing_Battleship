package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import gameLogic.Ship;
import gameLogic.Ship.ShipType;
import graphicsManager.GamePanel;

 public class TileManager {
	private final GamePanel gp;

	private final SpriteManager sm;

	private int waterCounter = 0;
	private int waterFrame = 0;

	private final HashMap<Ship.Rotation, Integer> rotationToInt = new HashMap<>();

	public TileManager(GamePanel gp) {
		this.gp = gp;
		this.sm = new SpriteManager();

		rotationToInt.put(Ship.Rotation.DOWN, 1);
		rotationToInt.put(Ship.Rotation.LEFT, 0);
		rotationToInt.put(Ship.Rotation.UP, 3);
		rotationToInt.put(Ship.Rotation.RIGHT, 2);
	}

	public void drawPlayer(Graphics2D g2d, int tileXOffset, int tileYOffset) {
		for (int row = 0; row < gp.maxBoardRow; row++) {
			for (int col = 0; col < gp.maxBoardCol; col++) {
				// Water tiles being drawn on.
				int tileX = (row * gp.scaledTileSize) + (tileXOffset * gp.scaledTileSize);
				int tileY = (col * gp.scaledTileSize) + (tileYOffset * gp.scaledTileSize);

				if (waterFrame == 0)
					g2d.drawImage(sm.waterTileSet[0], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 1)
					g2d.drawImage(sm.waterTileSet[1], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 2)
					g2d.drawImage(sm.waterTileSet[2], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 3)
					g2d.drawImage(sm.waterTileSet[3], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);

				Ship ship = gp.playerBoard.getSpace(col, row);

				// Ship tiles being drawn on.
				if (ship.getShipType() != ShipType.EMPTY) {
					BufferedImage shipTileImg;

					if (ship.getShipType() == ShipType.DESTROYER)
						shipTileImg = sm.destroyerTileSet[ship.getShipSection()];
					else if (ship.getShipType() == ShipType.CRUISER)
						shipTileImg = sm.cruiserTileSet[ship.getShipSection()];
					else if (ship.getShipType() == ShipType.SUBMARINE)
						shipTileImg = sm.submarineTileSet[ship.getShipSection()];
					else if (ship.getShipType() == ShipType.BATTLESHIP)
						shipTileImg = sm.battleshipTileSet[ship.getShipSection()];
					else if (ship.getShipType() == ShipType.CARRIER)
						shipTileImg = sm.carrierTileSet[ship.getShipSection()];
					else
						shipTileImg = null;

					assert shipTileImg != null;
					if (ship.wasHit()) {
						g2d.drawImage(rotate(shipTileImg, rotationToInt.get(ship.getRotation()) * -85), row * gp.scaledTileSize, col * gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);
						g2d.drawImage(sm.indicatorTileSet[1], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
					}
					else
						g2d.drawImage(rotate(shipTileImg, rotationToInt.get(ship.getRotation())*-90), tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				}

				if (ship.wasMiss())
					g2d.drawImage(sm.indicatorTileSet[0], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
			}
		}
	}

	public void drawComp(Graphics2D g2d, int tileXOffset, int tileYOffset) {
		for (int row = 0; row < gp.maxBoardRow; row++) {
			for (int col = 0; col < gp.maxBoardCol; col++) {
				// Water tiles being drawn on.
				int tileX = (row * gp.scaledTileSize) + (tileXOffset * gp.scaledTileSize);
				int tileY = (col * gp.scaledTileSize) + (tileYOffset * gp.scaledTileSize);

				if (waterFrame == 0)
					g2d.drawImage(sm.waterTileSet[0], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 1)
					g2d.drawImage(sm.waterTileSet[1], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 2)
					g2d.drawImage(sm.waterTileSet[2], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (waterFrame == 3)
					g2d.drawImage(sm.waterTileSet[3], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);

				Ship ship = gp.compBoard.getSpace(col, row);

				if (ship.wasMiss())
					g2d.drawImage(sm.indicatorTileSet[0], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
				else if (ship.wasHit())
					g2d.drawImage(sm.indicatorTileSet[2], tileX, tileY, gp.scaledTileSize, gp.scaledTileSize, null);
			}
		}
	}

	public void update() {
		waterCounter++;
		if (waterCounter > 42) {
			if (waterFrame == 0)
				waterFrame = 1;
			else if (waterFrame == 1)
				waterFrame = 2;
			else if (waterFrame == 2)
				waterFrame = 3;
			else if (waterFrame == 3)
				waterFrame = 0;
			waterCounter = 0;
		}
	}

	private BufferedImage rotate(BufferedImage img, int radians) {
		// Getting Dimensions of image
		int width = img.getWidth();
		int height = img.getHeight();

		// Creating a new buffered image
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		// creating Graphics in buffered image
		Graphics2D g2 = newImage.createGraphics();

		// Rotating image by degrees using toRadians()
		// method
		// and setting new dimension t it
		g2.rotate(Math.toRadians(radians), width / 2, height / 2);
		g2.drawImage(img, null, 0, 0);

		// Return rotated buffer image
		return newImage;
	}
}