package IMEProgram.Model;

/**
 * This class represents a 24-bit Image with Red, Green, and Blue channels. And operations that can
 * be performed on it.
 */
public class Image implements IImage {

  private final int[][][] rgbValues;

  private Image(int[][][] rgbValues) {
    this.rgbValues = rgbValues;
  }

  @Override
  public IImage getRedComponent() {
    return null;
  }

  @Override
  public IImage getGreenComponent() {
    return null;
  }

  @Override
  public IImage getBlueComponent() {
    return null;
  }

  @Override
  public IImage getValueComponent() {
    return null;
  }

  @Override
  public IImage getIntensityComponent() {
    return null;
  }

  @Override
  public IImage getLumaComponent() {
    return null;
  }

  @Override
  public IImage flipHorizontal() {
    return null;
  }

  @Override
  public IImage flipVertical() {
    return null;
  }

  @Override
  public IImage brighten(int increment) {
    return null;
  }

  @Override
  public IImage[] splitRGB() {
    return new IImage[0];
  }

  @Override
  public IImage combineRGB(IImage red, IImage green, IImage blue) throws IllegalArgumentException {
    return null;
  }

  @Override
  public IImage gaussianBlur() {
    return null;
  }

  @Override
  public IImage sharpen() {
    return null;
  }

  @Override
  public IImage convertToGrayscale() {
    return null;
  }

  @Override
  public IImage convertToSepia() {
    return null;
  }
}
