package imeprogram.controller;

/**
 * This interface represents the features exposed by our IME program to a view.
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
   * @param imageName The name of the source image.
   */
  void redComponent(String imageName);

  /**
   * Extracts the green component from the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void greenComponent(String imageName);

  /**
   * Extracts the blue component from the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void blueComponent(String imageName);

  /**
   * Extracts the luma component from the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void lumaComponent(String imageName);

  /**
   * Flips the source image horizontally and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void horizontalFlip(String imageName);

  /**
   * Flips the source image vertically and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void verticalFlip(String imageName);

  /**
   * Applies a blur effect to the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void blur(String imageName);

  /**
   * Sharpens the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void sharpen(String imageName);

  /**
   * Applies a sepia tone effect to the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void sepia(String imageName);

  /**
   * Compresses the source image with a specified compression ratio and saves it to the destination
   * image.
   *
   * @param imageName     The name of the source image.
   * @param compressRatio The compression ratio.
   */
  void compress(String imageName, int compressRatio);

  /**
   * Performs color correction on the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   */
  void colorCorrect(String imageName);

  /**
   * Adjusts the levels of the source image and saves it to the destination image.
   *
   * @param imageName The name of the source image.
   * @param black     The intensity level for shadows.
   * @param mid       The intensity level for mid.
   * @param white     The intensity level for highlights.
   */
  void adjustLevels(String imageName, int black, int mid, int white);

  /**
   * Generates a vertical split view by merging the given destination image with the source image in
   * that order. Saves the result in the destination image by overwriting it.
   *
   * @param imageName  The name of the source image.
   * @param splitRatio The ratio of the dest image in the split view, (1 - splitRatio) is the ratio
   *                   of the source image in the split view.
   */
  void splitView(String imageName, int splitRatio);

  /**
   * Generate a line graph of histogram of the given source image and save it with the specified
   * image name.
   *
   * @param imageName The name of the source image.
   */
  void histogram(String imageName);

  /**
   * Apply the changes that are present in the destImage to the source image.
   *
   * @param imageName The name of the source image.
   */
  void applyChanges(String imageName);

  /**
   * Remove modifications made to the destImage to sync it with the source image.
   *
   * @param imageName The name of the source image.
   */
  void cancelChanges(String imageName);

  /**
   * Return the data of the loaded image.
   *
   * @param imageName the data of the loaded image.
   */
  void getImageData(String imageName);
}
