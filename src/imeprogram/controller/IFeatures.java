package imeprogram.controller;

/**
 * This interface represents the features exposed by our IME program.
 */
public interface IFeatures {

  /**
   * Loads an image from the specified file path with the given name.
   *
   * @param filePath  The path where the image file is located.
   * @param imageName The name to assign to the loaded image.
   */
  void loadImage(String filePath, String imageName);

  /**
   * Saves the image with the given name to the specified file path.
   *
   * @param imageName The name of the image to be saved.
   * @param filePath  The path where the image file will be saved.
   */
  void saveImage(String imageName, String filePath);

  /**
   * Extracts the red component from the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the red component image.
   */
  void redComponent(String sourceImage, String destImage);

  /**
   * Extracts the green component from the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the green component image.
   */
  void greenComponent(String sourceImage, String destImage);

  /**
   * Extracts the blue component from the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the blue component image.
   */
  void blueComponent(String sourceImage, String destImage);

  /**
   * Extracts the luma component from the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the luma component image.
   */
  void lumaComponent(String sourceImage, String destImage);

  /**
   * Flips the source image horizontally and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the horizontally flipped image.
   */
  void horizontalFlip(String sourceImage, String destImage);

  /**
   * Flips the source image vertically and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the vertically flipped image.
   */
  void verticalFlip(String sourceImage, String destImage);

  /**
   * Applies a blur effect to the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the blurred image.
   */
  void blur(String sourceImage, String destImage);

  /**
   * Sharpens the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the sharpened image.
   */
  void sharpen(String sourceImage, String destImage);

  /**
   * Applies a sepia tone effect to the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the sepia-toned image.
   */
  void sepia(String sourceImage, String destImage);

  /**
   * Compresses the source image with a specified compression ratio and saves it to the destination
   * image.
   *
   * @param sourceImage   The name of the source image.
   * @param destImage     The name of the destination image.
   * @param compressRatio The compression ratio.
   */
  void compress(String sourceImage, String destImage, int compressRatio);

  /**
   * Performs color correction on the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the color-corrected image.
   */
  void colorCorrect(String sourceImage, String destImage);

  /**
   * Adjusts the levels of the source image and saves it to the destination image.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the levels-adjusted image.
   * @param black       The intensity level for shadows.
   * @param mid         The intensity level for mid.
   * @param white       The intensity level for highlights.
   */
  void adjustLevels(String sourceImage, String destImage, int black, int mid, int white);

  /**
   * Generates a vertical split view by merging the given destination image with the source image in
   * that order. Saves the result in the destination image by overwriting it.
   *
   * @param sourceImage The name of the source image.
   * @param operatedImage   The name of the operated dest image.
   * @param splitImage  The name of the splitview image.
   * @param splitRatio  The ratio of the dest image in the split view, (1 - splitRatio) is the ratio
   *                    of the source image in the split view.
   */
  void splitView(String sourceImage, String operatedImage, String splitImage, int splitRatio);

  /**
   * Generate a line graph of histogram of the given source image and save it with the specified
   * image name.
   *
   * @param sourceImage The name of the source image.
   * @param destImage   The name to assign to the histogram line graph.
   */
  void histogram(String sourceImage, String destImage);
}
