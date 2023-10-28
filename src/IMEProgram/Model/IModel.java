package IMEProgram.Model;

import IMEProgram.Exceptions.ImageNotFoundException;
import IMEProgram.Exceptions.InvalidFilePathException;
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
   * @throws FileNotFoundException If the specified file path is invalid or the file does not
   *                               exist.
   */
  void loadImage(String filePath, String imageName) throws FileNotFoundException;

  /**
   * Save the image with the given name to the specified file path.
   *
   * @param filePath  The file path where the image will be saved.
   * @param imageName The name of the image to be saved.
   * @throws ImageNotFoundException   If the specified image does not exist.
   * @throws InvalidFilePathException If the specified file path is invalid.
   */
  void saveImage(String filePath, String imageName)
      throws ImageNotFoundException, InvalidFilePathException;

  /**
   * Extract the red component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the red component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void redComponent(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Extract the green component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the green component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void greenComponent(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Extract the blue component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the blue component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void blueComponent(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Extract the value component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the value component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void valueComponent(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Extract the luma component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the luma component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void lumaComponent(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Extract the intensity component of an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the intensity component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void intensityComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException;

  /**
   * Flip an image horizontally and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the horizontally flipped image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void horizontalFlip(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Flip an image vertically and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the vertically flipped image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void verticalFlip(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Brighten an image by the specified increment and save it with the specified image name.
   *
   * @param increment       The amount to brighten the image (positive integer).
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the brightened image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void brighten(int increment, String sourceImageName, String destImageName)
      throws ImageNotFoundException;

  /**
   * Split an image into its red, green, and blue components and save them with the specified image
   * names.
   *
   * @param sourceImageName        The name of the source image.
   * @param destImageNameRedComp   The name to assign to the red component image.
   * @param destImageNameGreenComp The name to assign to the green component image.
   * @param destImageNameBlueComp  The name to assign to the blue component image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void rgbSplit(String sourceImageName, String destImageNameRedComp, String destImageNameGreenComp,
      String destImageNameBlueComp) throws ImageNotFoundException;

  /**
   * Combine red, green, and blue component images to form a full-color image and save it with the
   * specified image name.
   *
   * @param destImageName            The name to assign to the combined image.
   * @param sourceImageNameRedComp   The name of the red component image.
   * @param sourceImageNameGreenComp The name of the green component image.
   * @param sourceImageNameBlueComp  The name of the blue component image.
   * @throws ImageNotFoundException   If any of the specified component images do not exist.
   * @throws IllegalArgumentException If the dimensions of the component images do not match.
   */
  void rgbCombine(String destImageName, String sourceImageNameRedComp,
      String sourceImageNameGreenComp, String sourceImageNameBlueComp)
      throws ImageNotFoundException, IllegalArgumentException;

  /**
   * Apply a blur effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the blurred image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void blur(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Apply a sharpening effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the sharpened image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void sharpen(String sourceImageName, String destImageName) throws ImageNotFoundException;

  /**
   * Apply a sepia tone effect to an image and save it with the specified image name.
   *
   * @param sourceImageName The name of the source image.
   * @param destImageName   The name to assign to the sepia-toned image.
   * @throws ImageNotFoundException If the specified image does not exist.
   */
  void sepia(String sourceImageName, String destImageName) throws ImageNotFoundException;
}
