package imeprogram.model;

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
    this.height = height;
    this.width = width;
    this.rgbValues = _getDeepCopy(rgbValues);
  }

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
    int[][][] lumaValues = new int[height][width][numChannels];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {

        int luma = (int) Math.round(0.2126 * this.rgbValues[i][j][0]
            + 0.7152 * this.rgbValues[i][j][1] + 0.0722 * this.rgbValues[i][j][2]);
        lumaValues[i][j][0] = luma;  // Red value
        lumaValues[i][j][1] = luma;  // Green value
        lumaValues[i][j][2] = luma;  // Blue value
      }
    }
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
    int[][][] blurredImage = new int[height][width][numChannels];

    // Define the Gaussian blur kernel
    double[][] kernel = {{1.0 / 16, 2.0 / 16, 1.0 / 16}, {2.0 / 16, 4.0 / 16, 2.0 / 16},
        {1.0 / 16, 2.0 / 16, 1.0 / 16}};

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        double r = 0;
        double g = 0;
        double b = 0;

        // Apply the kernel to each pixel
        for (int ki = -1; ki <= 1; ki++) {
          for (int kj = -1; kj <= 1; kj++) {
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
    return new Image(blurredImage, width, height);
  }

  @Override
  public IImage sharpen() {
    int[][][] sharpenedImage = new int[height][width][numChannels];

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

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        double r = 0;
        double g = 0;
        double b = 0;

        // Apply the kernel to each pixel
        for (int ki = -2; ki <= 2; ki++) {
          for (int kj = -2; kj <= 2; kj++) {
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
    return new Image(sharpenedImage, width, height);
  }

  @Override
  public IImage getHistogram(ILineGraph graph) {
    int histogramHeight = this.bitDepth;
    int histogramWidth = this.bitDepth;
    int[][] histogramData = _getHistogram();

    // Normalize the histogram. Scale its height to 256.
    int topMarginNormalize = 1;
    histogramData = _normalizeHistogram(histogramData, histogramHeight - (topMarginNormalize));
    return graph.drawLineGraph(histogramData, histogramHeight, histogramWidth);
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
    // black, mid, and white should be in ascending order. And should be in [0, 255] range.
    if (!(black < mid && black < white && mid < white) && (black >= 0 && black <= 255) && (mid >= 0
        && mid <= 255) && (white >= 0 && white <= 255)) {
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
  public IImage splitView(IImage other, int splitRatio) throws IllegalArgumentException {
    if (this.width != other.getWidth() || this.height != other.getHeight()) {
      throw new IllegalArgumentException("Dimensions of given images dont match");
    }

    int[][][] splitValues = new int[this.height][this.width][this.numChannels];
    int splitHorizontalPosition = _getSplitPosition(this.width, splitRatio);
    for (int i = 0; i < this.height; i++) {
      // Fill left portion of result
      for (int j = 0; j < splitHorizontalPosition; j++) {
        splitValues[i][j][0] = this.rgbValues[i][j][0]; // Red
        splitValues[i][j][1] = this.rgbValues[i][j][1]; // Green
        splitValues[i][j][2] = this.rgbValues[i][j][2]; // Blue
      }
      // Fill right portion of result
      for (int j = splitHorizontalPosition; j < this.width; j++) {
        splitValues[i][j][0] = other.getValueAtPixel(i, j, 0); // Red
        splitValues[i][j][1] = other.getValueAtPixel(i, j, 1); // Green
        splitValues[i][j][2] = other.getValueAtPixel(i, j, 2); // Blue
      }
    }
    return new Image(splitValues, width, height);
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
    int[][][] sepiaValues = new int[height][width][numChannels];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
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
    return new Image(sepiaValues, width, height);
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
    return _getDeepCopy(this.rgbValues);
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
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
        normalizedHistogram[i][c] = (int) (histogram[i][c] * ((double) scaleFactor / maxFrequency));
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
}