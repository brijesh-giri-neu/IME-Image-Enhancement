package imeprogram.model;

/**
 * This interface represents a 24-bit Image with Red, Green, and Blue channels. And operations that
 * can be performed on it.
 */
public interface IImage {

  /**
   * Gets the value of a specific channel at a given pixel position.
   *
   * @param horizontalPos The column (horizontal position) of the pixel.
   * @param verticalPos   The verticalPos (vertical position) of the pixel.
   * @param channel       The color channel to retrieve (0 for Red, 1 for Green, 2 for Blue).
   * @return The value of the specified channel at the given pixel position.
   * @throws IndexOutOfBoundsException If the provided horizontalPos or verticalPos values are out
   *                                   of bounds.
   * @throws IllegalArgumentException  If an invalid channel value is provided.
   */
  int getValueAtPixel(int horizontalPos, int verticalPos, int channel)
      throws IndexOutOfBoundsException, IllegalArgumentException;

  /**
   * Gets the width of the Image.
   *
   * @return the width of the Image.
   */
  int getWidth();

  /**
   * Gets the height of the Image.
   *
   * @return the height of the Image.
   */
  int getHeight();

  /**
   * Gets the RGB values of the Image.
   *
   * @return an int[][][] where, the first dim corresponds to the height, the second dim corresponds
   *     to the width, and the third dim corresponds to the channel. The channels are returned in
   *     the following order, index0 -> Red, index1 -> Green, index2 -> Blue.
   */
  int[][][] getRgbValues();

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
   * Overwrite this IImage by combining the red, green, and blue components from the given IImages.
   * Picks one channel from each of the given arguments.
   *
   * @param red   IImage whose Red component is selected.
   * @param green IImage whose Green component is selected.
   * @param blue  IImage whose Blue component is selected.
   * @throws IllegalArgumentException If the input images have different dimensions.
   */
  void combineRGB(IImage red, IImage green, IImage blue) throws IllegalArgumentException;

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
   * Applies a Gaussian blur effect to a portion of the Image determined by the splitWidth. The
   * Image is split vertically into two halves, with the split position being determined by the
   * splitWidth, and the effect is applied to the left portion of the Image.
   *
   * @param splitWidth horizontal position of the split in terms of percentage of width.
   * @return a new IImage with Gaussian blur filter applied.
   */
  IImage gaussianBlur(int splitWidth);

  /**
   * Applies a Sharpening effect to a portion of the Image determined by the splitWidth. The Image
   * is split vertically into two halves, with the split position being determined by the
   * splitWidth, and the effect is applied to the left portion of the Image.
   *
   * @param splitWidth horizontal position of the split in terms of percentage of width.
   * @return a new IImage with the sharpening effect applied.
   */
  IImage sharpen(int splitWidth);

  /**
   * Applies a sepia tone effect to a portion of the Image determined by the splitWidth. The Image
   * is split vertically into two halves, with the split position being determined by the
   * splitWidth, and the effect is applied to the left portion of the Image.
   *
   * @param splitWidth horizontal position of the split in terms of percentage of width.
   * @return a new IImage with the sepia tone filter applied.
   */
  IImage convertToSepia(int splitWidth);

  /**
   * Applies luma function to each channel of a portion of the Image determined by the splitWidth.
   * The Image is split vertically into two halves, with the split position being determined by the
   * splitWidth, and the effect is applied to the left portion of the Image.
   *
   * @param splitWidth horizontal position of the split in terms of percentage of width.
   * @return an IImage where values for all 3 channels are calculated by using the Luma function.
   */
  IImage getLumaComponent(int splitWidth);

  /**
   * Returns a 256x256 line graph representing the normalized histogram of this IImage.
   *
   * @return a 256x256 line graph representing the normalized histogram of this IImage.
   */
  IImage getHistogram();

  /**
   * Color corrects an Image by aligning the meaningful peaks of its histogram. The corrected peak
   * position in its histogram is determined by the average of peak pixel values across channels
   * i.e. AVG(PixelValue(Peak_Red), PixelValue(Peak_Green), PixelValue(Peak_Blue))
   *
   * @return a new IImage with aligned histogram peaks, that visually looks black and white.
   */
  IImage applyColorCorrection();
}
