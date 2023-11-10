 package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

 import main.*;

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

    public void draw(Graphics2D g2d) {
        for (int row = 0; row < gp.maxBoardRow; row++) {
            for (int col = 0; col < gp.maxBoardCol; col++) {
                // Water tiles being drawn on.
                if (waterFrame == 0)
                    g2d.drawImage(sm.waterTileSet[0], row*gp.scaledTileSize, col*gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);
                else if (waterFrame == 1)
                    g2d.drawImage(sm.waterTileSet[1], row*gp.scaledTileSize, col*gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);
                else if (waterFrame == 2)
                    g2d.drawImage(sm.waterTileSet[2], row*gp.scaledTileSize, col*gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);
                else if (waterFrame == 3)
                    g2d.drawImage(sm.waterTileSet[3], row*gp.scaledTileSize, col*gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);

                Ship ship = gp.gameBoard.getSpace(col, row);

                // Ship tiles being drawn on.
                if (ship.getShipType() != Ship.ShipType.EMPTY) {
                    BufferedImage shipTileImg;

                    if (ship.getShipType() == Ship.ShipType.DESTROYER)
                        shipTileImg = sm.destroyerTileSet[ship.getShipSection()];
                    else if (ship.getShipType() == Ship.ShipType.CRUISER)
                        shipTileImg = sm.cruiserTileSet[ship.getShipSection()];
                    else if (ship.getShipType() == Ship.ShipType.SUBMARINE)
                        shipTileImg = sm.submarineTileSet[ship.getShipSection()];
                    else if (ship.getShipType() == Ship.ShipType.BATTLESHIP)
                        shipTileImg = sm.battleshipTileSet[ship.getShipSection()];
                    else if (ship.getShipType() == Ship.ShipType.CARRIER)
                        shipTileImg = sm.carrierTileSet[ship.getShipSection()];
                    else
                        shipTileImg = null;

                    g2d.drawImage(rotate(shipTileImg, rotationToInt.get(ship.getRotation())*-90), row*gp.scaledTileSize, col*gp.scaledTileSize, gp.scaledTileSize, gp.scaledTileSize, null);
                }
                // TODO indicator tiles being drawn on.
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

    private BufferedImage rotate(BufferedImage img, int radians)
    {

        // Getting Dimensions of image
        int width = img.getWidth();
        int height = img.getHeight();

        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();

        // Rotating image by degrees using toRadians()
        // method
        // and setting new dimension t it
        g2.rotate(Math.toRadians(radians), width / 2,
                height / 2);
        g2.drawImage(img, null, 0, 0);

        // Return rotated buffer image
        return newImage;
    }
}