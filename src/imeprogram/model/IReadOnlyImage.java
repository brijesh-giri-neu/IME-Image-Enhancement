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
}
