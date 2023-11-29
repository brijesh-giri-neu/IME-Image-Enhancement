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
}
