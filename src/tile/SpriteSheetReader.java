package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheetReader {
    private BufferedImage spriteSheet;
    private final int tileWidth;
    private final int tileHeight;
    private final int numRows;
    private final int numCols;

    /*
     * @param fileName the image file path/name
     * @param rows the amount of rows in the sprite sheet
     * @param cols the amount of columns in the sprite sheet
     * @param width the width of each sprite
     * @param height the height of each sprite
     */
    public SpriteSheetReader(String fileName, int tileWidth, int tileHeight) {
        // Creates a BufferedImage of the original image from 'fileName'.
        try {
            this.spriteSheet = ImageIO.read(new File(fileName));
        }
        catch (IOException e) {
			e.printStackTrace();
        }

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.numRows = spriteSheet.getHeight() / tileHeight;
        this.numCols = spriteSheet.getWidth() / tileWidth;
    }

    /*
     * Turns a sprite sheet into an array of each sprite.
     * @return a BufferedImage[] array of each sprite from the sprite sheet going from left to right.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public BufferedImage[] spriteSheetToArray() throws IOException {

        // Creates an array for the sprites from the sheet.
        BufferedImage[] spriteArray = new BufferedImage[numRows * numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                spriteArray[(i * numCols) + j] = spriteSheet.getSubimage(j*tileHeight, i*tileWidth, tileWidth, tileHeight);
            }
        }

        return spriteArray;
    }

    public BufferedImage getSpriteFromSheet(int row, int col, int spriteWidth, int spriteHeight) {
        if (row * tileWidth >= spriteSheet.getWidth() || col * tileHeight >= spriteSheet.getHeight() || row < 0 || col < 0)
            throw new IndexOutOfBoundsException("There was an issue with grabbing the sprite you wanted.");

        return spriteSheet.getSubimage(col*tileHeight, row*tileWidth, spriteWidth, spriteHeight);
    }
}