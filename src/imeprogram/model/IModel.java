package imeprogram.model;

import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import java.io.FileNotFoundException;

/**
 * The model of the IME program. This interface represents actions that can be taken in our
 * application.
 */
public interface IModel {

  /**
   * Load an image from the specified file path and refer to it with the given image name.
   *
   * @param filePath  The file path of the image.
   * @param imageName The name to assign to the loaded image.
   * @throws FileNotFoundException     If the specified file path is invalid or the file does not
   *                                   exist.
   * @throws FileFormatException       If the provided file format is not supported
   * @throws InvalidImageNameException If the specified image name cannot be assigned to an image in
   *                                   the application.
   */
  void loadImageFromFile(String filePath, String imageName)
      throws FileNotFoundException, FileFormatException, InvalidImageNameException;

  /**
   * Save the image with the given name to the specified file path.
   *
   * @param imageName The name of the image to be saved.
   * @param filePath  The file path where the image will be saved.
   * @throws ImageNotFoundException If the specified source image does not exist.
   * @throws FileNotFoundException  If the image cannot be saved at the specified file path.
   * @throws FileFormatException    If the provided file format is not supported
   */
  void saveImageToFile(String imageName, String filePath)
      throws ImageNotFoundException, FileNotFoundException, FileFormatException;

  /**
   * Extract the red component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the red component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void redComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Extract the green component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the green component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void greenComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Extract the blue component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the blue component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void blueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Extract the value component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the value component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void valueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Extract the luma component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the luma component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void lumaComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Extract the intensity component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the intensity component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void intensityComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Flip an image horizontally and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the horizontally flipped image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void horizontalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Flip an image vertically and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the vertically flipped image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void verticalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Brighten an image by the specified increment and save it with the specified image name.
   *
   * @param increment       The amount to brighten the image (positive integer).
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the brightened image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void brighten(int increment, String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Split an image into its red, green, and blue components and save them with the specified image
   * names.
   *
   * @param sourceImageName        The name of the source image.
   * @param destImageNameRedComp   The name to assign to the red component image.
   * @param destImageNameGreenComp The name to assign to the green component image.
   * @param destImageNameBlueComp  The name to assign to the blue component image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void rgbSplit(String sourceImageName, String destImageNameRedComp, String destImageNameGreenComp,
      String destImageNameBlueComp) throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Combine red, green, and blue component images to form a full-color image and save it with the
   * specified image name.
   *
   * @param destImageName            The name to assign to the combined image.
   * @param sourceImageNameRedComp   The name of the red component image.
   * @param sourceImageNameGreenComp The name of the green component image.
   * @param sourceImageNameBlueComp  The name of the blue component image.
   * @throws ImageNotFoundException    If any of the specified component images do not exist.
   * @throws IllegalArgumentException  If the dimensions of the component images do not match.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void rgbCombine(String destImageName, String sourceImageNameRedComp,
      String sourceImageNameGreenComp, String sourceImageNameBlueComp)
      throws ImageNotFoundException, IllegalArgumentException, InvalidImageNameException;

  /**
   * Apply a blur effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the blurred image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void blur(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Apply a sharpening effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the sharpened image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void sharpen(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;

  /**
   * Apply a sepia tone effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the sepia-toned image.
   * @throws ImageNotFoundException    If the specified source image does not exist.
   * @throws InvalidImageNameException If the specified destination image name cannot be assigned to
   *                                   an image in the application.
   */
  void sepia(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException;
}
