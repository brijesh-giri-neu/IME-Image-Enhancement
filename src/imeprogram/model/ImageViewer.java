package imeprogram.model;

/**
 * This class represents a read only version of an Image. It's an object adapter around IImage
 * interface.
 */
public class ImageViewer implements IReadOnlyImage {

  private final IImage image;

  /**
   * Initialize the image viewer with an image.
   *
   * @param image the image to be viewed.
   */
  public ImageViewer(IImage image) {
    this.image = image;
  }

  @Override
  public int[][][] getRgbValues() {
    return image.getRgbValues();
  }

  @Override
  public int getValueAtPixel(int horizontalPos, int verticalPos, int channel)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    return this.getValueAtPixel(horizontalPos, verticalPos, channel);
  }

  @Override
  public int getWidth() {
    return this.getWidth();
  }

  @Override
  public int getHeight() {
    return this.getHeight();
  }
}
