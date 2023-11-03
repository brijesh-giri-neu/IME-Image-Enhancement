package imeprogram.model;

import imeprogram.exceptions.FileFormatException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Helper for file operations on a PPM format image file.
 */
class BinaryImageIO implements IImageFileIO {

  /**
   * Initialize the BinaryImageIO object.
   */
  BinaryImageIO() {
  }

  @Override
  public IImage loadFromFile(String filePath) throws IOException, FileFormatException {
    fileFormatCheck(filePath);

    BufferedImage bufferedImage = ImageIO.read(new File(filePath));
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    int[][][] rgbValues = new int[height][width][3];

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int pixel = bufferedImage.getRGB(y, x);
        rgbValues[x][y][0] = (pixel >> 16) & 0xFF; // Red
        rgbValues[x][y][1] = (pixel >> 8) & 0xFF;  // Green
        rgbValues[x][y][2] = pixel & 0xFF;         // Blue
      }
    }
    return new Image(rgbValues, width, height);
  }

  @Override
  public void saveToFile(String filePath, IImage imageToSave)
      throws IOException, FileFormatException {
    String extension = getExtensionFromPath(filePath);
    fileFormatCheck(filePath);

    int width = imageToSave.getWidth();
    int height = imageToSave.getHeight();
    int[][][] rgbValues = imageToSave.getRgbValues();

    // Convert the 3D array of RGB values to a BufferedImage
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = rgbValues[y][x][0];
        int g = rgbValues[y][x][1];
        int b = rgbValues[y][x][2];
        Color color = new Color(r, g, b);
        image.setRGB(x, y, color.getRGB());
      }
    }

    ImageIO.write(image, extension.toLowerCase(), new File(filePath));
  }

  private String getExtensionFromPath(String filePath) {
    // Extract the file extension from the filepath
    String extension = "";
    int i = filePath.lastIndexOf('.');
    if (i > 0) {
      extension = filePath.substring(i + 1);
    }
    return extension;
  }

  private void fileFormatCheck(String filePath) {
    // Extract the file extension from the filepath
    String extension = getExtensionFromPath(filePath).toLowerCase();

    if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg"))) {
      throw new FileFormatException("Unsupported file extension: " + extension);
    }
  }
}
