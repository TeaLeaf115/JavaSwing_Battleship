import javax.swing.*;

import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

import graphicsManager.GamePanel;
import tile.SpriteSheetReader;

public class Main {
    public static void main(String[] args) {
        // readSS();
        JFrame gameWindow = new JFrame("Battleship || Now With Graphics!");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);
        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void readSS() {
        SpriteSheetReader ssr = new SpriteSheetReader("res/BattleshipSpritesheet.png", 16, 16);
        try {
            BufferedImage[] ssArr = ssr.spriteSheetToArray();
            int i = 0;
            for (BufferedImage img : ssArr) {

                File f = new File("zSpriteSheet_" + i + ".png");
                ImageIO.write(img, "png", f);
                i++;
            }
            // File f = new File("airCraftCarrier.png");
            // ImageIO.write(ssr.getSpriteFromSheet(3, 0, 80, 16), "png", f);
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
    }
}