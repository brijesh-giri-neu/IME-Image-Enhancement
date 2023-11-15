package imeprogram.model;

import imeprogram.exceptions.FileFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This class represents a 24-bit Image with Red, Green, and Blue channels. And operations that can
 * be performed on it.
 */
public class Image implements IImage {

  private int[][][] rgbValues;
  private int height;
  private int width;
  private final int bitDepth = 256;
  private final int numChannels = 3;

  /**
   * Instantiate an Image object with all pixel values set to Zero.
   *
   * @param height height of the image
   * @param width  width of the image
   */
  public Image(int height, int width) {
    this.height = height;
    this.width = width;
    this.rgbValues = new int[height][width][3];
  }

  /**
   * Instantiate an Image object with the given width, height and rgbvalues.
   *
   * @param height    height of the image.
   * @param width     width of the image.
   * @param rgbValues array of pixel values.
   */
  public Image(int[][][] rgbValues, int width, int height) {
    int[][][] newValues = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Create deep copy
        newValues[i][j][0] = rgbValues[i][j][0];  // Red value
        newValues[i][j][1] = rgbValues[i][j][1];  // Green value
        newValues[i][j][2] = rgbValues[i][j][2];  // Blue value
      }

      this.rgbValues = newValues;
      this.width = width;
      this.height = height;
    }
  }

  /**
   * Loads an Image from the given filepath.
   *
   * @param filePath the input filepath
   * @return an Image object
   * @throws IllegalArgumentException if the provided file has corrupt data
   * @throws IOException              if the provided file does not exists
   */
  public static Image loadImageFromFile(String filePath)
      throws IllegalArgumentException, IOException {
    String fileExtension = getFileExtension(filePath);

    if (fileExtension != null) {
      switch (fileExtension.toLowerCase()) {
        case "jpg":
        case "jpeg":
        case "png":
          return loadJpgOrPngImage(filePath);
        case "ppm":
          return loadPpmImage(filePath);
        default:
          throw new IllegalArgumentException("Unsupported file format: " + fileExtension);
      }
    } else {
      throw new IllegalArgumentException("File path has no extension");
    }
  }

  /**
   * Loads a JPG or PNG Image.
   *
   * @param filePath path of the file.
   * @return Image object.
   */
  private static Image loadJpgOrPngImage(String filePath) throws IOException {
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

  /**
   * Loads a PPM Image.
   *
   * @param filePath path of the file.
   * @return Image object.
   */
  private static Image loadPpmImage(String filePath) throws IOException {

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
    int maxValue = sc.nextInt();

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

  /**
   * Gets the file extension of the file.
   *
   * @param filePath path of the file.
   * @return extension or null.
   */
  private static String getFileExtension(String filePath) {
    if (filePath != null && !filePath.isEmpty()) {
      int dotIndex = filePath.lastIndexOf('.');
      if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
        return filePath.substring(dotIndex + 1);
      }
    }
    return null;
  }

  // End of Image Builder Methods

  @Override
  public IImage getRedComponent() {
    int[][][] redValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redValues[i][j][0] = this.rgbValues[i][j][0];  // Red value
        redValues[i][j][1] = 0;  // Green value set to 0
        redValues[i][j][2] = 0;  // Blue value set to 0
      }
    }
    return new Image(redValues, width, height);
  }

  @Override
  public IImage getGreenComponent() {
    int[][][] greenValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        greenValues[i][j][0] = 0;  // Red value set to 0
        greenValues[i][j][1] = this.rgbValues[i][j][1];  // Green value
        greenValues[i][j][2] = 0;  // Blue value set to 0
      }
    }
    return new Image(greenValues, width, height);
  }

  @Override
  public IImage getBlueComponent() {
    int[][][] blueValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blueValues[i][j][0] = 0;  // Red value set to 0
        blueValues[i][j][1] = 0;  // Green value set to 0
        blueValues[i][j][2] = this.rgbValues[i][j][2];  // Blue value
      }
    }
    return new Image(blueValues, width, height);
  }

  @Override
  public IImage getValueComponent() {
    int[][][] valueValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int value = Math.max(rgbValues[i][j][0], Math.max(rgbValues[i][j][1], rgbValues[i][j][2]));
        valueValues[i][j][0] = value;  // Red value
        valueValues[i][j][1] = value;  // Green value
        valueValues[i][j][2] = value;  // Blue value
      }
    }
    return new Image(valueValues, width, height);
  }

  @Override
  public IImage getIntensityComponent() {
    int[][][] intensityValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int intensity = (rgbValues[i][j][0] + rgbValues[i][j][1] + rgbValues[i][j][2]) / 3;
        intensityValues[i][j][0] = intensity;  // Red value
        intensityValues[i][j][1] = intensity;  // Green value
        intensityValues[i][j][2] = intensity;  // Blue value
      }
    }
    return new Image(intensityValues, width, height);
  }

  @Override
  public IImage getLumaComponent() {
    Window clipWindow = new Window(0, this.height, 0, this.width);
    int[][][] lumaValues = _getLumaComponent(clipWindow);

    return new Image(lumaValues, width, height);
  }

  @Override
  public IImage flipHorizontal() {
    int[][][] flippedValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Flip the image horizontally
        flippedValues[i][j] = this.rgbValues[i][(width - 1) - j];
      }
    }
    return new Image(flippedValues, width, height);
  }

  @Override
  public IImage flipVertical() {
    int[][][] flippedValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      // Flip the image vertically
      System.arraycopy(this.rgbValues[(height - 1) - i], 0, flippedValues[i], 0, width);
    }
    return new Image(flippedValues, width, height);
  }

  @Override
  public IImage brighten(int increment) {
    int[][][] brightenedValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Brighten the image
        brightenedValues[i][j][0] = Math.min(255,
            Math.max(0, this.rgbValues[i][j][0] + increment));  // Red value
        brightenedValues[i][j][1] = Math.min(255,
            Math.max(0, this.rgbValues[i][j][1] + increment));  // Green value
        brightenedValues[i][j][2] = Math.min(255,
            Math.max(0, this.rgbValues[i][j][2] + increment));  // Blue value
      }
    }
    return new Image(brightenedValues, width, height);
  }

  @Override
  public IImage[] splitRGB() {
    int[][][] redValues = new int[height][width][3];
    int[][][] greenValues = new int[height][width][3];
    int[][][] blueValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redValues[i][j][0] = this.rgbValues[i][j][0];  // Red value
        greenValues[i][j][1] = this.rgbValues[i][j][1];  // Green value
        blueValues[i][j][2] = this.rgbValues[i][j][2];  // Blue value
      }
    }
    return new IImage[]{new Image(redValues, width, height), new Image(greenValues, width, height),
        new Image(blueValues, width, height)};
  }

  @Override
  public void combineRGB(IImage red, IImage green, IImage blue) throws IllegalArgumentException {
    boolean equalWidth = (red.getWidth() == green.getWidth() && (green.getWidth()
        == blue.getWidth()) && (red.getWidth() == blue.getWidth()));

    boolean equalHeight = (red.getHeight() == green.getHeight() && (green.getHeight()
        == blue.getHeight()) && (red.getHeight() == blue.getHeight()));

    if (!equalWidth || !equalHeight) {
      throw new IllegalArgumentException("Provided images have unequal dimensions");
    }

    // Assuming that the red, green, and blue images are of type Image and have the same dimensions
    int[][][] combinedValues = this.rgbValues;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        combinedValues[i][j][0] = red.getValueAtPixel(i, j, 0); // Red value
        combinedValues[i][j][1] = green.getValueAtPixel(i, j, 1);  // Green value
        combinedValues[i][j][2] = blue.getValueAtPixel(i, j, 2);  // Blue value
      }
    }
  }

  @Override
  public IImage gaussianBlur() {
    Window portionToOperate = new Window(0, this.height, 0, this.width);
    int[][][] blurredImage = _gaussianBlur(portionToOperate);

    return new Image(blurredImage, width, height);
  }

  @Override
  public IImage gaussianBlur(int splitWidth) {
    int splitHorizontalPosition = _getSplitPosition(this.width, splitWidth);

    Window portionToOperate = new Window(0, this.height, 0, splitHorizontalPosition);
    int[][][] blurredImage = _gaussianBlur(portionToOperate);

    return new Image(blurredImage, width, height);
  }

  @Override
  public IImage sharpen() {
    Window portionToOperate = new Window(0, this.height, 0, this.width);
    int[][][] sharpenedImage = _sharpen(portionToOperate);

    return new Image(sharpenedImage, width, height);
  }

  @Override
  public IImage sharpen(int splitWidth) {
    int splitHorizontalPosition = _getSplitPosition(this.width, splitWidth);

    Window portionToOperate = new Window(0, this.height, 0, splitHorizontalPosition);
    int[][][] sharpenedImage = _sharpen(portionToOperate);
    return new Image(sharpenedImage, width, height);
  }

  @Override
  public IImage convertToSepia(int splitWidth) {
    int splitHorizontalPosition = _getSplitPosition(this.width, splitWidth);

    Window clipWindow = new Window(0, this.height, 0, splitHorizontalPosition);
    int[][][] sepiaValues = _convertToSepia(clipWindow);
    return new Image(sepiaValues, width, height);
  }

  @Override
  public IImage getLumaComponent(int splitWidth) {
    int splitHorizontalPosition = _getSplitPosition(this.width, splitWidth);

    Window clipWindow = new Window(0, this.height, 0, splitHorizontalPosition);
    int[][][] lumaValues = _getLumaComponent(clipWindow);
    return new Image(lumaValues, width, height);
  }

  @Override
  public IImage getHistogram() {
    int histogramHeight = this.bitDepth;
    int histogramWidth = this.bitDepth;
    int[][] histogramData = _getHistogram();
    int[][][] histImageValues = new int[histogramHeight][histogramWidth][this.numChannels];

    int topMargin = 1;
    // Normalize the histogram. Scale its height to 256.
    histogramData = _normalizeHistogram(histogramData, histogramHeight - (topMargin));

    // Create a BufferedImage object to draw the line graph
    BufferedImage histImage = new BufferedImage(histogramWidth, histogramHeight,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = histImage.createGraphics();
    // Fill the background with white color
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, histogramWidth, histogramHeight);
    // Set grid line colors. Light grey with transparency.
    g2d.setColor(new Color(192, 192, 192, 95));
    // Draw vertical grid lines
    int numVerticalLines = 10;
    for (int i = 1; i <= numVerticalLines; i++) {
      int x = i * (histogramWidth / numVerticalLines);
      g2d.drawLine(x, 0, x, histogramHeight);
    }
    // Draw horizontal grid lines
    int numHorizontalLines = 10;
    for (int i = 1; i < numHorizontalLines; i++) {
      int y = i * (histogramHeight / numHorizontalLines);
      g2d.drawLine(0, y, histogramWidth, y);
    }
    // Set histogram line colors
    Color[] lineColors = {Color.RED, Color.GREEN, Color.BLUE};
    // Draw the actual histogram
    // Draw a line from each point from 0 to 254. Last line is from 254 to 255.
    for (int i = 0; i < this.bitDepth - 1; i++) {
      // Horizontal position of line
      int x1 = i;
      int x2 = (i + 1);
      for (int c = 0; c < this.numChannels; c++) {
        // Draw the line based on channel frequency
        int y1 = histogramHeight - topMargin - histogramData[i][c];
        int y2 = histogramHeight - topMargin - histogramData[i + 1][c];
        g2d.setColor(lineColors[c % lineColors.length]); // Sets color based on channel loop
        g2d.drawLine(x1, y1, x2, y2);
      }
    }
    // Convert BufferedImage to IImage
    for (int x = 0; x < histogramHeight; x++) {
      for (int y = 0; y < histogramWidth; y++) {
        Color pixel = new Color(histImage.getRGB(y, x));
        histImageValues[x][y][0] = pixel.getRed();   // Red
        histImageValues[x][y][1] = pixel.getGreen(); // Green
        histImageValues[x][y][2] = pixel.getBlue();  // Blue
      }
    }
    return new Image(histImageValues, histogramWidth, histogramHeight);
  }

  @Override
  public IImage colorCorrect() {
    int[][][] correctedPixels = new int[this.height][this.width][this.numChannels];
    int[][] histogramData = _getHistogram();
    int[][] channelPeaks = _getPeaks(histogramData, 11, 244);

    // Get position of individual channel peaks
    int redPeakPosition = channelPeaks[0][0];     // Red Peak
    int greenPeakPosition = channelPeaks[1][0];   // Green Peak
    int bluePeakPosition = channelPeaks[2][0];    // Blue Peak
    // Get the average position for alignment
    int alignedPosition = Math.round(redPeakPosition + greenPeakPosition + bluePeakPosition) / 3;
    // Calculate offset for each channel
    int redOffset = alignedPosition - redPeakPosition;
    int greenOffset = alignedPosition - greenPeakPosition;
    int blueOffset = alignedPosition - bluePeakPosition;
    // Align the peaks of each channel by applying an offset to the pixel values.
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        // Get the pixel value at (i, j) that is to be color corrected.
        int correctedRed = this.rgbValues[i][j][0];
        int correctedGreen = this.rgbValues[i][j][1];
        int correctedBlue = this.rgbValues[i][j][2];
        // Apply offsets to align histogram peaks
        correctedRed += redOffset;
        correctedGreen += greenOffset;
        correctedBlue += blueOffset;
        // Clamp the values to stay within the valid range (0-255)
        correctedRed = Math.min(Math.max(correctedRed, 0), 255);
        correctedGreen = Math.min(Math.max(correctedGreen, 0), 255);
        correctedBlue = Math.min(Math.max(correctedBlue, 0), 255);
        // Set the color corrected values in the result image
        correctedPixels[i][j][0] = correctedRed;
        correctedPixels[i][j][1] = correctedGreen;
        correctedPixels[i][j][2] = correctedBlue;
      }
    }
    return new Image(correctedPixels, width, height);
  }

  @Override
  public IImage adjustLevels(int black, int mid, int white) throws IllegalArgumentException {
    if (!(black < mid && black < white && mid < white)) {
      throw new IllegalArgumentException("Provided black, mid, and white levels are not valid");
    }

    int[][][] adjustedPixels = new int[this.height][this.width][this.numChannels];
    int b = black;
    int m = mid;
    int w = white;
    // Curve fitting formulas
    double denomA = ((b * b) * (m - w)) - (b * ((m * m) - (w * w))) + (w * m * m) - (m * w * w);
    double numA = (127 * b) + (128 * w) - (255 * m);
    double numB = (-127 * b * b) + (255 * m * m) - (128 * w * w);
    double numC = ((b * b) * ((255 * m) - (128 * w))) - (b * ((255 * m * m) - (128 * w * w)));
    // Get quadratic curve equation coefficients
    double coeffA = numA / denomA;
    double coeffB = numB / denomA;
    double coeffC = numC / denomA;
    // Apply the quadratic curve function to each pixel in each channel
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int c = 0; c < this.numChannels; c++) {
          int x = this.rgbValues[i][j][c];
          double y = (coeffA * Math.pow(x, 2)) + (coeffB * x) + coeffC;
          // Clamp the values, round-off to nearest, and set it in the image
          adjustedPixels[i][j][c] = Math.min(255, Math.max(0, (int) Math.round(y))); // Clamp values
        }
      }
    }
    return new Image(adjustedPixels, width, height);
  }

  @Override
  public IImage convertToGrayscale() {
    int[][][] grayscaleValues = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int gray = (int) Math.round(0.2126 * rgbValues[i][j][0] + 0.7152 * rgbValues[i][j][1]
            + 0.0722 * rgbValues[i][j][2]);

        gray = Math.min(255, Math.max(0, gray));

        grayscaleValues[i][j][0] = gray;  // Red value
        grayscaleValues[i][j][1] = gray;  // Green value
        grayscaleValues[i][j][2] = gray;  // Blue value
      }
    }
    return new Image(grayscaleValues, width, height);
  }

  @Override
  public IImage convertToSepia() {
    Window clipWindow = new Window(0, this.height, 0, this.width);
    int[][][] sepiaValues = _convertToSepia(clipWindow);
    return new Image(sepiaValues, width, height);
  }

  public void saveToFile(String filepath) throws FileNotFoundException, FileFormatException {
    // Extract the file extension from the filepath
    String extension = "";
    int i = filepath.lastIndexOf('.');
    if (i > 0) {
      extension = filepath.substring(i + 1);
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
        throw new FileFormatException("Unsupported file extension: " + extension);
      }
    } catch (IOException e) {
      throw new FileNotFoundException("Error writing image file: " + e.getMessage());
    }
  }

  @Override
  public int getValueAtPixel(int horizontalPos, int verticalPos, int channel)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    if (horizontalPos < 0 || verticalPos < 0 || channel < 0) {
      throw new IllegalArgumentException("Arguments cannot be negative.");
    }
    if (horizontalPos >= this.rgbValues.length || verticalPos >= this.rgbValues[0].length
        || channel >= this.rgbValues[0][0].length) {
      throw new IndexOutOfBoundsException("Arguments are out of bound.");
    }
    return this.rgbValues[horizontalPos][verticalPos][channel];
  }

  @Override
  public int[][][] getRgbValues() {
    int[][][] copyValues = new int[this.height][this.width][3];

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        copyValues[i][j][0] = this.rgbValues[i][j][0];  // Red value
        copyValues[i][j][1] = this.rgbValues[i][j][1];  // Green value
        copyValues[i][j][2] = this.rgbValues[i][j][2];  // Blue value
      }
    }
    return copyValues;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  // Blur a rectangular portion of the given image.
  // Pixel data is used from this.
  private int[][][] _gaussianBlur(Window clipWindow) {
    int[][][] blurredImage = _getDeepCopy(this.rgbValues);

    // Define the Gaussian blur kernel
    double[][] kernel = {{1.0 / 16, 2.0 / 16, 1.0 / 16}, {2.0 / 16, 4.0 / 16, 2.0 / 16},
        {1.0 / 16, 2.0 / 16, 1.0 / 16}};

    for (int i = clipWindow.heightStart; i < clipWindow.heightEnd; i++) {
      for (int j = clipWindow.widthStart; j < clipWindow.widthEnd; j++) {
        double r = 0;
        double g = 0;
        double b = 0;

        // Apply the kernel to each pixel
        for (int ki = -1; ki <= 1; ki++) {
          for (int kj = -1; kj <= 1; kj++) {
            // Consider pixels from entire image while applying kernel
            // And not just pixels within our clipping window
            if (i + ki >= 0 && i + ki < this.height && j + kj >= 0 && j + kj < this.width) {
              r += kernel[ki + 1][kj + 1] * this.rgbValues[i + ki][j + kj][0];
              g += kernel[ki + 1][kj + 1] * this.rgbValues[i + ki][j + kj][1];
              b += kernel[ki + 1][kj + 1] * this.rgbValues[i + ki][j + kj][2];
            }
          }
        }
        // Clamp the RGB values
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));
        // Round off to nearest int
        blurredImage[i][j][0] = (int) Math.round(r);
        blurredImage[i][j][1] = (int) Math.round(g);
        blurredImage[i][j][2] = (int) Math.round(b);
      }
    }
    return blurredImage;
  }

  // Sharpens a rectangular portion of the given image.
  // Pixel data is used from this.
  private int[][][] _sharpen(Window clipWindow) {
    int[][][] sharpenedImage = _getDeepCopy(this.rgbValues);

    // Define the sharpening kernel
    double[][] kernel = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};

    // Calculate the sum of the kernel values
    double sum = 0;
    for (double[] doubles : kernel) {
      for (double aDouble : doubles) {
        sum += aDouble;
      }
    }
    // Normalize the kernel
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[i].length; j++) {
        kernel[i][j] /= sum;
      }
    }

    for (int i = clipWindow.heightStart; i < clipWindow.heightEnd; i++) {
      for (int j = clipWindow.widthStart; j < clipWindow.widthEnd; j++) {
        double r = 0;
        double g = 0;
        double b = 0;

        // Apply the kernel to each pixel
        for (int ki = -2; ki <= 2; ki++) {
          for (int kj = -2; kj <= 2; kj++) {
            // Consider pixels from entire image while applying kernel
            // And not just pixels within our clipping window
            if (i + ki >= 0 && i + ki < this.height && j + kj >= 0 && j + kj < this.width) {
              r += kernel[ki + 2][kj + 2] * this.rgbValues[i + ki][j + kj][0];
              g += kernel[ki + 2][kj + 2] * this.rgbValues[i + ki][j + kj][1];
              b += kernel[ki + 2][kj + 2] * this.rgbValues[i + ki][j + kj][2];
            }
          }
        }
        // Clamp the RGB values
        r = Math.min(255, Math.max(0, r));
        g = Math.min(255, Math.max(0, g));
        b = Math.min(255, Math.max(0, b));
        // Round off to nearest int
        sharpenedImage[i][j][0] = (int) Math.round(r);
        sharpenedImage[i][j][1] = (int) Math.round(g);
        sharpenedImage[i][j][2] = (int) Math.round(b);
      }
    }
    return sharpenedImage;
  }

  // Sepia tones a rectangular portion of the given image.
  // Pixel data is used from this.
  private int[][][] _convertToSepia(Window clipWindow) {
    int[][][] sepiaValues = _getDeepCopy(this.rgbValues);

    for (int i = clipWindow.heightStart; i < clipWindow.heightEnd; i++) {
      for (int j = clipWindow.widthStart; j < clipWindow.widthEnd; j++) {
        int r = this.rgbValues[i][j][0];
        int g = this.rgbValues[i][j][1];
        int b = this.rgbValues[i][j][2];
        sepiaValues[i][j][0] = Math.min(255,
            Math.max(0, (int) Math.round(0.393 * r + 0.769 * g + 0.189 * b)));  // Red value
        sepiaValues[i][j][1] = Math.min(255,
            Math.max(0, (int) Math.round(0.349 * r + 0.686 * g + 0.168 * b)));  // Green value
        sepiaValues[i][j][2] = Math.min(255,
            Math.max(0, (int) Math.round(0.272 * r + 0.534 * g + 0.131 * b)));  // Blue value
      }
    }
    return sepiaValues;
  }

  // Luma transforms a rectangular portion of the given image.
  // Pixel data is used from this.
  private int[][][] _getLumaComponent(Window clipWindow) {
    int[][][] lumaValues = _getDeepCopy(this.rgbValues);

    for (int i = clipWindow.heightStart; i < clipWindow.heightEnd; i++) {
      for (int j = clipWindow.widthStart; j < clipWindow.widthEnd; j++) {
        int luma = (int) Math.round(
            0.2126 * this.rgbValues[i][j][0] + 0.7152 * this.rgbValues[i][j][1]
                + 0.0722 * this.rgbValues[i][j][2]);
        lumaValues[i][j][0] = luma;  // Red value
        lumaValues[i][j][1] = luma;  // Green value
        lumaValues[i][j][2] = luma;  // Blue value
      }
    }
    return lumaValues;
  }

  private int _getSplitPosition(int width, int splitPercentage) {
    return Math.round((width * splitPercentage) / 100);
  }

  /**
   * Returns the frequency histogram of this IImage.
   *
   * @return an int[pixelValue][channel] containing frequency values for each intensity value of the
   *     RGB channels. The output array size is int[256][3], where index0 -> pixel values, index1 ->
   *     channel.
   */
  private int[][] _getHistogram() {
    int[][] histogram = new int[this.bitDepth][this.numChannels];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int c = 0; c < this.numChannels; c++) {
          histogram[this.rgbValues[i][j][c]][c]++;
        }
      }
    }
    return histogram;
  }

  private int[][] _normalizeHistogram(int[][] histogram, int scaleFactor) {
    int[][] normalizedHistogram = new int[this.bitDepth][this.numChannels];
    int maxFreqChannel = 0;
    int maxFreqPosition = 0;
    // Find the maximum frequency in the histogram
    int maxFrequency = 0;
    // Discard boundary values (0,255) for max frequency calculation
    // This is done as presence of clamped pixels can alter the histogram drawn
    for (int i = 1; i < this.bitDepth - 1; i++) {
      for (int c = 0; c < this.numChannels; c++) {
        if (histogram[i][c] > maxFrequency) {
          maxFrequency = histogram[i][c];
          maxFreqPosition = i;
          maxFreqChannel = c;
        }
      }
    }
    // Normalize the histogram values
    for (int i = 0; i < 256; i++) {
      for (int c = 0; c < this.numChannels; c++) {
        normalizedHistogram[i][c] = (int) (histogram[i][c] * ((double) scaleFactor
            / maxFrequency));
      }
    }
    return normalizedHistogram;
  }

  /**
   * Returns the histogram peak for each channel of the given histogram.
   *
   * @param histogramData histogram whose peaks are required. Should be in the format
   *                      [pixelValue][channel].
   * @param minValue      the minimum value to be considered in the peak calculation.
   * @param maxValue      the maximum value to be considered in the peak calculation.
   * @return an int[][] containing the histogram peak for each channel in [channel][peak] format,
   *     where the indices for the peak represent the following, index0 -> position on horizontal
   *     axis, index1 -> height of the peak.
   */
  private int[][] _getPeaks(int[][] histogramData, int minValue, int maxValue) {
    int[][] peaks = new int[this.numChannels][2];

    for (int i = minValue; i <= maxValue; i++) {
      for (int c = 0; c < this.numChannels; c++) {
        // If frequency at (i) is greater than current peak frequency for the channel.
        if (histogramData[i][c] > peaks[c][1]) {
          // Peak frequency value
          peaks[c][1] = histogramData[i][c];
          // Peak frequency position
          peaks[c][0] = i;
        }
      }
    }
    return peaks;
  }

  private int[][][] _getDeepCopy(int[][][] inputArray) {
    int[][][] copyArray = new int[inputArray.length][inputArray[0].length][inputArray[0][0].length];

    for (int dim1 = 0; dim1 < inputArray.length; dim1++) {
      for (int dim2 = 0; dim2 < inputArray[0].length; dim2++) {
        for (int dim3 = 0; dim3 < inputArray[0][0].length; dim3++) {
          copyArray[dim1][dim2][dim3] = inputArray[dim1][dim2][dim3];
        }
      }
    }
    return copyArray;
  }

  /**
   * Helper class used represent a rectangular window, whose height and width start from top left.
   */
  private class Window {

    private int heightStart;
    private int heightEnd;
    private int widthStart;
    private int widthEnd;

    private Window(int heightStart, int heightEnd, int widthStart, int widthEnd) {
      this.heightStart = heightStart;
      this.heightEnd = heightEnd;
      this.widthStart = widthStart;
      this.widthEnd = widthEnd;
    }
  }
}