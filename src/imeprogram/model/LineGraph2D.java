package imeprogram.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class is used to draw a 2d line graph.
 */
public class LineGraph2D implements ILineGraph {

  @Override
  public IImage drawLineGraph(int[][] data, int height, int width) {
    int topMargin = 1;
    // Create a BufferedImage object to draw the line graph
    BufferedImage histImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = histImage.createGraphics();
    setupHistogramGrid(g2d, width, height);
    drawHistogramLines(data, height, width, topMargin, g2d);
    // Convert BufferedImage to IImage
    int[][][] histImageValues = convertBufferedImageToIImage(histImage, height, width);
    return new Image(histImageValues, width, height);
  }

  private int[][][] convertBufferedImageToIImage(BufferedImage histImage, int height, int width) {
    int[][][] histImageValues = new int[height][width][3];
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        Color pixel = new Color(histImage.getRGB(y, x));
        histImageValues[x][y][0] = pixel.getRed();   // Red
        histImageValues[x][y][1] = pixel.getGreen(); // Green
        histImageValues[x][y][2] = pixel.getBlue();  // Blue
      }
    }
    return histImageValues;
  }

  private void setupHistogramGrid(Graphics2D g2d, int width, int height) {
    // Fill the background with white color
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);
    // Set grid line colors. Light grey with transparency.
    g2d.setColor(new Color(192, 192, 192, 95));
    // Draw vertical grid lines
    int numVerticalLines = 10;
    for (int i = 1; i <= numVerticalLines; i++) {
      int x = i * (width / numVerticalLines);
      g2d.drawLine(x, 0, x, height);
    }
    // Draw horizontal grid lines
    int numHorizontalLines = 10;
    for (int i = 1; i < numHorizontalLines; i++) {
      int y = i * (height / numHorizontalLines);
      g2d.drawLine(0, y, width, y);
    }
  }

  private void drawHistogramLines(int[][] data, int height, int width, int topMargin,
      Graphics2D g2d) {
    // Set histogram line colors
    Color[] lineColors = {Color.RED, Color.GREEN, Color.BLUE};
    // Draw the actual histogram
    // Draw a line from each point from 0 to 254. Last line is from 254 to 255.
    for (int i = 0; i < width - 1; i++) {
      // Horizontal position of line
      int x1 = i;
      int x2 = (i + 1);
      for (int c = 0; c < 3; c++) {
        // Draw the line based on channel frequency
        int y1 = height - topMargin - data[i][c];
        int y2 = height - topMargin - data[i + 1][c];
        g2d.setColor(lineColors[c % lineColors.length]); // Sets color based on channel loop
        g2d.drawLine(x1, y1, x2, y2);
      }
    }
    g2d.dispose();
  }
}
