package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessPieceResizeUtil {

    public static void main(String[] args) {
        resizePieceImages("src/main/resources/chess_pieces_original", "src/main/resources/chess_pieces", 75, 75);
    }

    public static void resizePieceImages(String sourceFolder, String destinationFolder, int width, int height) {
        File folder = new File(sourceFolder);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    resizeImage(file.getAbsolutePath(), destinationFolder, width, height);
                }
            }
        }
    }

    public static void resizeImage(String imagePath, String destinationFolder, int width, int height) {
        try {
            // Read the image file
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Create a scaled version of the image
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create a blank BufferedImage with the desired dimensions
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // Get the Graphics object of the resized image
            Graphics2D g2d = resizedImage.createGraphics();

            // Draw the scaled image onto the resized image
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            // Extract the file name from the image path
            String fileName = new File(imagePath).getName();

            // Create the destination folder if it doesn't exist
            File destFolder = new File(destinationFolder);
            if (!destFolder.exists()) {
                destFolder.mkdirs();
            }

            // Save the resized image to the destination folder
            String destinationPath = destinationFolder + File.separator + fileName;
            ImageIO.write(resizedImage, "png", new File(destinationPath));

            System.out.println("Resized image saved: " + destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
