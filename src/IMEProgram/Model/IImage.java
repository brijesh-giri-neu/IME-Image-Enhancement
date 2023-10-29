package IMEProgram.Model;

import IMEProgram.Exceptions.InvalidFilePathException;

/**
 * This interface represents a 24-bit Image with Red, Green, and Blue channels. And operations that
 * can be performed on it.
 */
public interface IImage {

  /**
   * Return an IImage where [G,B] channels of the Image are set to 0.
   *
   * @return an IImage where [G,B] channels of the Image are set to 0.
   */
  IImage getRedComponent();

  /**
   * Return an IImage where [R,B] channels of the Image are set to 0.
   *
   * @return an IImage where [R,B] channels of the Image are set to 0.
   */
  IImage getGreenComponent();

  /**
   * Return an IImage where [R,G] channels of the Image are set to 0.
   *
   * @return an IImage where [R,G] channels of the Image are set to 0.
   */
  IImage getBlueComponent();

  /**
   * Return an IImage where values for all 3 channels are calculated by using the Value function:
   * MAX(R,G,B).
   *
   * @return an IImage where values for all 3 channels are calculated by using the Value function:
   *     MAX(R,G,B).
   */
  IImage getValueComponent();

  /**
   * Return an IImage where values for all 3 channels are calculated by using the Intensity
   * function: AVG(R,G,B).
   *
   * @return an IImage where values for all 3 channels are calculated by using the Intensity
   *     function: AVG(R,G,B).
   */
  IImage getIntensityComponent();

  /**
   * Return an IImage where values for all 3 channels are calculated by using the Luma function.
   *
   * @return an IImage where values for all 3 channels are calculated by using the Luma function.
   */
  IImage getLumaComponent();

  /**
   * Return an IImage that is generated by horizontally flipping this IImage.
   *
   * @return a horizontally flipped version of this IImage.
   */
  IImage flipHorizontal();

  /**
   * Return an IImage that is generated by vertically flipping this IImage.
   *
   * @return a vertically flipped version of this IImage.
   */
  IImage flipVertical();

  /**
   * Return an IImage that is generated by applying a constant increment value to each pixel of this
   * IImage.
   *
   * @param increment the constant increment value to be applied to each pixel.
   * @return a new IImage with modified pixel values
   */
  IImage brighten(int increment);

  /**
   * Return the individual [R,G,B] components of this IImage. Each component will be an IImage where
   * the other component values are set to 0.
   *
   * @return an array of exactly THREE IImages in the mentioned order : index 0 -> Red Component,
   *     index 1 -> Green Component, index 2 -> Blue Component
   */
  IImage[] splitRGB();

  /**
   * Generate an IImage by combining the red, green, and blue components from the given IImages.
   * Picks one channel from each of the given arguments.
   *
   * @param red   IImage whose Red component is selected.
   * @param green IImage whose Green component is selected.
   * @param blue  IImage whose Blue component is selected.
   * @return an IImage representing the combination of the RGB components from the given IImages.
   * @throws IllegalArgumentException If the input images have different dimensions.
   */
  IImage combineRGB(IImage red, IImage green, IImage blue) throws IllegalArgumentException;

  /**
   * Applies a Gaussian blur effect to the image.
   *
   * @return a new IImage with a Gaussian blur filter applied.
   */
  IImage gaussianBlur();

  /**
   * Applies a sharpening effect to the image using the standard sharpen filter.
   *
   * @return a new IImage with the sharpening effect applied.
   */
  IImage sharpen();

  /**
   * Applies a Grayscale filter to the image using the conversion formula: R'=G'=B'=0.2126R +
   * 0.7152G + 0.0722B.
   *
   * @return a new IImage with the Grayscale filter applied.
   */
  IImage convertToGrayscale();

  /**
   * Applies a sepia tone effect to the image.
   *
   * @return a new IImage with the sepia tone filter applied.
   */
  IImage convertToSepia();

  /**
   * Saves this IImage to the specified file path.
   *
   * @param filepath The file path where the image will be saved.
   * @throws InvalidFilePathException If the image cannot be saved at the specified file path.
   */
  void saveToFile(String filepath) throws InvalidFilePathException;
}
