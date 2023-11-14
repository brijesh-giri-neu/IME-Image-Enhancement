package imeprogram.fileparser;

import imeprogram.exceptions.FileFormatException;
import imeprogram.model.IImage;
import imeprogram.model.Image;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Helper for file operations on a PPM format image file.
 */
class PPMImageIO implements IImageFileIO {

  @Override
  public IImage loadFromFile(String filePath) throws IOException, IllegalArgumentException {
    fileFormatCheck(filePath);

    if (!isValidPpmFileContent(filePath)) {
      throw new IllegalArgumentException("Provided ppm file is invalid");
    }

    Scanner sc;
    sc = new Scanner(new FileInputStream(filePath));

    StringBuilder builder = new StringBuilder();
    // Read the file line by line, ignoring comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());

    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Provided image is invalid.");
      // You might throw an IOException here if necessary
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int[][][] rgbValues = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        rgbValues[i][j][0] = sc.nextInt();  // Red value
        rgbValues[i][j][1] = sc.nextInt();  // Green value
        rgbValues[i][j][2] = sc.nextInt();  // Blue value
      }
    }

    return new Image(rgbValues, width, height);
  }

  @Override
  public void saveToFile(String filePath, IImage imageToSave) throws IOException {
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

    // Save as PPM
    FileWriter writer = new FileWriter(new File(filePath));
    writer.write("P3\n" + width + " " + height + "\n255\n");
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = rgbValues[y][x][0];
        int g = rgbValues[y][x][1];
        int b = rgbValues[y][x][2];
        writer.write(r + " " + g + " " + b + " ");
      }
      writer.write("\n");
    }
    writer.close();
  }

  /**
   * Checks if a PPM file contains valid data.
   *
   * @param filePath path of the file.
   * @return True or False.
   */
  private static boolean isValidPpmFileContent(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      // Read the first two lines and check if they comply with PPM format
      String magicNumber = reader.readLine();
      String dimensions = reader.readLine();

      if (magicNumber != null && dimensions != null) {
        if (!magicNumber.equals("P3") && !magicNumber.equals("P6")) {
          return false;
        }

        String[] dimensionValues = dimensions.trim().split("\\s+");

        if (dimensionValues.length != 2) {
          return false;
        }

        int width = Integer.parseInt(dimensionValues[0]);
        int height = Integer.parseInt(dimensionValues[1]);

        if (width <= 0 || height <= 0) {
          return false;
        }

        String maxValLine = reader.readLine();
        if (maxValLine != null) {
          int maxVal = Integer.parseInt(maxValLine.trim());
          if (maxVal != 255) {
            return false;
          }
        } else {
          return false;
        }

        // Check the content for RGB values
        String line;
        while ((line = reader.readLine()) != null) {
          String[] values = line.trim().split("\\s+");
          for (String val : values) {
            try {
              int rgb = Integer.parseInt(val);
              if (rgb < 0 || rgb > 255) {
                return false;
              }
            } catch (NumberFormatException e) {
              return false;
            }
          }
        }
      } else {
        return false;
      }

      return true; // All checks passed, PPM content is valid
    } catch (IOException e) {
      return false;
    }
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

    if (!extension.equals("ppm")) {
      throw new FileFormatException("Unsupported file extension: " + extension);
    }
  }
}
