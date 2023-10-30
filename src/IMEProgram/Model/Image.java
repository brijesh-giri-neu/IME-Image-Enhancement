package IMEProgram.Model;

import IMEProgram.Exceptions.InvalidFilePathException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class represents a 24-bit Image with Red, Green, and Blue channels. And operations that can
 * be performed on it.
 */
public class Image implements IImage {

  private final int[][][] rgbValues;
  private int height;
  private int width;

  private Image(int[][][] rgbValues) {
    this.rgbValues = rgbValues;
  }

  @Override
  public IImage getRedComponent() {
    int[][][] redValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        redValues[i][j][0] = this.rgbValues[i][j][0];  // Red value
        redValues[i][j][1] = 0;  // Green value set to 0
        redValues[i][j][2] = 0;  // Blue value set to 0
      }
    }
    return new Image(redValues);
  }

  @Override
  public IImage getGreenComponent() {
    int[][][] greenValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        greenValues[i][j][0] = 0;  // Red value set to 0
        greenValues[i][j][1] = this.rgbValues[i][j][1];  // Green value
        greenValues[i][j][2] = 0;  // Blue value set to 0
      }
    }
    return new Image(greenValues);
  }

  @Override
  public IImage getBlueComponent() {
    int[][][] blueValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        blueValues[i][j][0] = 0;  // Red value set to 0
        blueValues[i][j][1] = 0;  // Green value set to 0
        blueValues[i][j][2] = this.rgbValues[i][j][2];  // Blue value
      }
    }
    return new Image(blueValues);
  }

  @Override
  public IImage getValueComponent() {
    int[][][] valueValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int value = Math.max(rgbValues[i][j][0], Math.max(rgbValues[i][j][1], rgbValues[i][j][2]));
        valueValues[i][j][0] = value;  // Red value
        valueValues[i][j][1] = value;  // Green value
        valueValues[i][j][2] = value;  // Blue value
      }
    }
    return new Image(valueValues);
  }

  @Override
  public IImage getIntensityComponent() {
    int[][][] intensityValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int intensity = (rgbValues[i][j][0] + rgbValues[i][j][1] + rgbValues[i][j][2]) / 3;
        intensityValues[i][j][0] = intensity;  // Red value
        intensityValues[i][j][1] = intensity;  // Green value
        intensityValues[i][j][2] = intensity;  // Blue value
      }
    }
    return new Image(intensityValues);
  }

  @Override
  public IImage getLumaComponent() {
    int[][][] lumaValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int luma = (int)Math.round(0.2126*rgbValues[i][j][0] + 0.7152*rgbValues[i][j][1] + 0.0722*rgbValues[i][j][2]);
        lumaValues[i][j][0] = luma;  // Red value
        lumaValues[i][j][1] = luma;  // Green value
        lumaValues[i][j][2] = luma;  // Blue value
      }
    }
    return new Image(lumaValues);
  }

  @Override
  public IImage flipHorizontal() {
    int[][][] flippedValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        // Flip the image horizontally
        flippedValues[i][j] = this.rgbValues[i][(width - 1) - j];
      }
    }
    return new Image(flippedValues);
  }

  @Override
  public IImage flipVertical() {
    int[][][] flippedValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      // Flip the image vertically
      System.arraycopy(this.rgbValues[(height - 1) - i], 0, flippedValues[i], 0, width);
    }
    return new Image(flippedValues);
  }


  @Override
  public IImage brighten(int increment) {
    int[][][] brightenedValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        // Brighten the image
        brightenedValues[i][j][0] = Math.min(255, this.rgbValues[i][j][0] + increment);  // Red value
        brightenedValues[i][j][1] = Math.min(255, this.rgbValues[i][j][1] + increment);  // Green value
        brightenedValues[i][j][2] = Math.min(255, this.rgbValues[i][j][2] + increment);  // Blue value
      }
    }
    return new Image(brightenedValues);
  }

  @Override
  public IImage[] splitRGB() {
    int[][][] redValues = new int[height][width][3];
    int[][][] greenValues = new int[height][width][3];
    int[][][] blueValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        redValues[i][j][0] = this.rgbValues[i][j][0];  // Red value
        greenValues[i][j][1] = this.rgbValues[i][j][1];  // Green value
        blueValues[i][j][2] = this.rgbValues[i][j][2];  // Blue value
      }
    }
    return new IImage[]{new Image(redValues), new Image(greenValues), new Image(blueValues)};
  }

  @Override
  public IImage combineRGB(Image red, Image green, Image blue) throws IllegalArgumentException {
    // Assuming that the red, green, and blue images are of type Image and have the same dimensions
    int[][][] combinedValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        combinedValues[i][j][0] = red.rgbValues[i][j][0];  // Red value
        combinedValues[i][j][1] = green.rgbValues[i][j][1];  // Green value
        combinedValues[i][j][2] = blue.rgbValues[i][j][2];  // Blue value
      }
    }
    return new Image(combinedValues);
  }

  @Override
  public IImage gaussianBlur() {
    // You'll need to implement the logic for applying a Gaussian blur to an image.
    // This typically involves convolving the image with a Gaussian kernel.
    // For now, let's return a new Image with the same rgbValues
    int[][][] blurredValues = new int[height][width][3];
    // Apply Gaussian blur logic here
    return new Image(blurredValues);
  }

  @Override
  public IImage sharpen() {
    // You'll need to implement the logic for sharpening an image.
    // This typically involves convolving the image with a sharpening kernel.
    int[][][] sharpenedValues = new int[height][width][3];
    // Apply sharpening logic here
    return new Image(sharpenedValues);
  }

  @Override
  public IImage convertToGrayscale() {
    int[][][] grayscaleValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int gray = (int)Math.round(0.2126*rgbValues[i][j][0] + 0.7152*rgbValues[i][j][1] + 0.0722*rgbValues[i][j][2]);
        grayscaleValues[i][j][0] = gray;  // Red value
        grayscaleValues[i][j][1] = gray;  // Green value
        grayscaleValues[i][j][2] = gray;  // Blue value
      }
    }
    return new Image(grayscaleValues);
  }

  @Override
  public IImage convertToSepia() {
    int[][][] sepiaValues = new int[height][width][3];
    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = rgbValues[i][j][0];
        int g = rgbValues[i][j][1];
        int b = rgbValues[i][j][2];
        sepiaValues[i][j][0] = Math.min(255, (int)Math.round(0.393*r + 0.769*g + 0.189*b));  // Red value
        sepiaValues[i][j][1] = Math.min(255, (int)Math.round(0.349*r + 0.686*g + 0.168*b));  // Green value
        sepiaValues[i][j][2] = Math.min(255, (int)Math.round(0.272*r + 0.534*g + 0.131*b));  // Blue value
      }
    }
    return new Image(sepiaValues);
  }

  @Override
  public void saveToFile(String filepath) throws InvalidFilePathException {
    // Extract the file extension from the filepath
    String extension = "";
    int i = filepath.lastIndexOf('.');
    if (i > 0) {
      extension = filepath.substring(i+1);
    }

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

    // Save the BufferedImage to a file
    try {
      if (extension.equalsIgnoreCase("ppm")) {
        // Save as PPM
        FileWriter writer = new FileWriter(new File(filepath));
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
      } else if (extension.equalsIgnoreCase("png")) {
        // Save as PNG
        ImageIO.write(image, "png", new File(filepath));
      } else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
        // Save as JPG
        ImageIO.write(image, "jpg", new File(filepath));
      } else {
        throw new InvalidFilePathException("Unsupported file extension: " + extension);
      }
    } catch (IOException e) {
      throw new InvalidFilePathException("Error writing image file: " + e.getMessage());
    }
  }
}
