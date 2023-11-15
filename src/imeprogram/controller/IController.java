package imeprogram.controller;

/**
 * This interface represents a Controller of the IME program.
 */
public interface IController {

  /**
   * Initiate the controller of the IME program, which will stay alive till the end of program.
   */
  void start();

  /**
   * Loads an image with the specified arguments.
   *
   * @param args The arguments for loading the image.
   */
  void loadImage(String[] args);

  /**
   * Saves an image with the specified arguments.
   *
   * @param args The arguments for saving the image.
   */
  void saveImage(String[] args);

  /**
   * Generates the red component of an image using the specified arguments.
   *
   * @param args The arguments for generating the red component.
   */
  void redComponent(String[] args);

  /**
   * Generates the green component of an image using the specified arguments.
   *
   * @param args The arguments for generating the green component.
   */
  void greenComponent(String[] args);

  /**
   * Generates the blue component of an image using the specified arguments.
   *
   * @param args The arguments for generating the blue component.
   */
  void blueComponent(String[] args);

  /**
   * Generates the value component of an image using the specified arguments.
   *
   * @param args The arguments for generating the value component.
   */
  void valueComponent(String[] args);

  /**
   * Generates the luma component of an image using the specified arguments.
   *
   * @param args The arguments for generating the luma component.
   */
  void lumaComponent(String[] args);

  /**
   * Generates the intensity component of an image using the specified arguments.
   *
   * @param args The arguments for generating the intensity component.
   */
  void intensityComponent(String[] args);

  /**
   * Flips an image horizontally using the specified arguments.
   *
   * @param args The arguments for flipping the image horizontally.
   */
  void horizontalFlip(String[] args);

  /**
   * Flips an image vertically using the specified arguments.
   *
   * @param args The arguments for flipping the image vertically.
   */
  void verticalFlip(String[] args);

  /**
   * Brightens an image using the specified arguments.
   *
   * @param args The arguments for brightening the image.
   */
  void brighten(String[] args);

  /**
   * Splits the RGB channels of an image using the specified arguments.
   *
   * @param args The arguments for splitting the RGB channels.
   */
  void rgbSplit(String[] args);

  /**
   * Combines the RGB channels of different images into one using the specified arguments.
   *
   * @param args The arguments for combining the RGB channels.
   */
  void rgbCombine(String[] args);

  /**
   * Applies a blur effect to an image using the specified arguments.
   *
   * @param args The arguments for applying the blur effect.
   */
  void blur(String[] args);

  /**
   * Sharpens an image using the specified arguments.
   *
   * @param args The arguments for sharpening the image.
   */
  void sharpen(String[] args);

  /**
   * Applies a sepia filter to an image using the specified arguments.
   *
   * @param args The arguments for applying the sepia filter.
   */
  void sepia(String[] args);

  /**
   * Executes a script using the specified arguments.
   *
   * @param args The arguments for executing the script.
   */
  void runScript(String[] args);

  /**
   * Generate a line graph of the histogram of given image.
   *
   * @param args The arguments for generating histogram.
   */
  void histogram(String[] args);

  /**
   * Apply color correction to the given image.
   *
   * @param args The arguments for color correcting an image.
   */
  void colorCorrect(String[] args);

  /**
   * Apply levels adjustment to the given image.
   *
   * @param args The arguments for levels adjustment on an image.
   */
  void adjustLevels(String[] args);
}
