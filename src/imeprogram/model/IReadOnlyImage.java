package imeprogram.model;

/**
 * This interface represents a read-only 24-bit Image with Red, Green, and Blue channels.
 */
public interface IReadOnlyImage {

  /**
   * Gets the RGB values of the Image.
   *
   * @return an int[][][] where, the first dim corresponds to the height, the second dim corresponds
   *     to the width, and the third dim corresponds to the channel. The channels are returned in
   *     the following order, index0 -> Red, index1 -> Green, index2 -> Blue.
   */
  int[][][] getRgbValues();

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
}
