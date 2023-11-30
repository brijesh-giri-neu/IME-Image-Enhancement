package imeprogram.model;

import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.fileparser.IImageFileIO;
import imeprogram.fileparser.IImageFileIOFactory;
import imeprogram.model.IImage.Filter;
import imeprogram.model.IImage.ImageComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The class represents actions that can be taken in our application. It maintains the session
 * memory of the application.
 */
public class Model implements IModel {
  // NOTE - No action for convert to grayscale.

  // Represents the session memory of the application.
  // In the future, it can be moved to a database or the web.
  private Map<String, IImage> loadedImages;

  private IImageFileIOFactory imageIOFactory;
  private static final String VALID_ALIAS_NAME_CHARS_REGEX = "[a-zA-Z0-9._-]+";

  public Model(IImageFileIOFactory imageIOFactory) {
    this.loadedImages = new HashMap<String, IImage>();
    this.imageIOFactory = imageIOFactory;
  }

  @Override
  public void loadImageFromFile(String filePath, String imageName)
      throws FileNotFoundException, FileFormatException, InvalidImageNameException {
    // Delegate FileNotFoundException to the Image class.
    // Image class needs a public const,
    // which takes filepath as arg or a builder method that takes filepath.
    IImage sourceImage;
    try {
      // sourceImage = Image.loadImageFromFile(filePath);
      IImageFileIO imageIO = imageIOFactory.getImageParser(filePath);
      sourceImage = imageIO.loadFromFile(filePath);
    } catch (IOException e) {
      throw new FileNotFoundException(e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new FileFormatException(e.getMessage());
    }

    saveImageToMemory(sourceImage, imageName);
  }

  @Override
  public void saveImageToFile(String imageName, String filePath)
      throws ImageNotFoundException, FileNotFoundException, FileFormatException {
    IImage image = getImageFromMemory(imageName);

    try {
      // Delegate InvalidFilePathException to the Image class.
      // image.saveToFile(filePath);
      IImageFileIO imageIO = imageIOFactory.getImageParser(filePath);
      imageIO.saveToFile(filePath, image);
    } catch (IOException e) {
      throw new FileNotFoundException();
    } catch (IllegalArgumentException e){
      throw new FileFormatException("Invalid image file format");
    }
  }

  @Override
  public IReadOnlyImage getImageData(String sourceImageName) throws ImageNotFoundException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    return new ImageViewer(sourceImg);
  }

  @Override
  public void saveImageDataToMemory(IImage imageData, String imageName)
      throws InvalidImageNameException {
    saveImageToMemory(imageData, imageName);
  }

  @Override
  public void removeImageFromMemory(String imageName) {
    if (doesImageExist(imageName)) {
      loadedImages.remove(imageName);
    }
  }

  @Override
  public void redComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getComponent(ImageComponent.RED), destImageName);
  }

  @Override
  public void greenComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getComponent(ImageComponent.GREEN), destImageName);
  }

  @Override
  public void blueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getComponent(ImageComponent.BLUE), destImageName);
  }

  @Override
  public void valueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getValueComponent(), destImageName);
  }

  @Override
  public void lumaComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getLumaComponent(), destImageName);
  }

  @Override
  public void intensityComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getIntensityComponent(), destImageName);
  }

  @Override
  public void horizontalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.flipHorizontal(), destImageName);
  }

  @Override
  public void verticalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.flipVertical(), destImageName);
  }

  @Override
  public void brighten(String sourceImageName, String destImageName, int increment)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.brighten(increment), destImageName);
  }

  @Override
  public void rgbSplit(String sourceImageName, String destImageNameRedComp,
      String destImageNameGreenComp, String destImageNameBlueComp)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    IImage[] rgbComponents = sourceImg.splitRGB();
    IImage redComponent = rgbComponents[0];
    IImage greenComponent = rgbComponents[1];
    IImage blueComponent = rgbComponents[2];

    saveImageToMemory(redComponent, destImageNameRedComp);
    saveImageToMemory(greenComponent, destImageNameGreenComp);
    saveImageToMemory(blueComponent, destImageNameBlueComp);
  }

  @Override
  public void rgbCombine(String destImageName, String sourceImageNameRedComp,
      String sourceImageNameGreenComp, String sourceImageNameBlueComp)
      throws ImageNotFoundException, IllegalArgumentException, InvalidImageNameException {
    IImage redComponent = getImageFromMemory(sourceImageNameRedComp);
    IImage greenComponent = getImageFromMemory(sourceImageNameGreenComp);
    IImage blueComponent = getImageFromMemory(sourceImageNameBlueComp);

    // Create a new IImage to assign to destImageName from the given components.
    // Call builder/constructor of the IImage class
    IImage combinedImage = new Image(redComponent.getHeight(), redComponent.getWidth());
    combinedImage.combineRGB(redComponent, greenComponent, blueComponent);

    saveImageToMemory(combinedImage, destImageName);
  }

  @Override
  public void blur(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.applyFilter(Filter.GAUSSIAN_BLUR), destImageName);
  }

  @Override
  public void sharpen(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.applyFilter(Filter.SHARPEN), destImageName);
  }

  @Override
  public void sepia(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.convertToSepia(), destImageName);
  }

  @Override
  public void histogram(String sourceImageName, String destImageName, ILineGraph graph)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getHistogram(graph), destImageName);
  }

  @Override
  public void colorCorrect(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.colorCorrect(), destImageName);
  }

  @Override
  public void adjustLevels(String sourceImageName, String destImageName, int black, int mid,
      int white)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.adjustLevels(black, mid, white), destImageName);
  }

  @Override
  public void splitView(String sourceImageName, String destImageName, int splitRatio)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    IImage sourceImg = getImageFromMemory(sourceImageName);
    IImage destImg = getImageFromMemory(destImageName);

    saveImageToMemory(destImg.splitView(sourceImg, splitRatio), destImageName);
  }

  @Override
  public void compress(String sourceImageName, String destImageName, int compressRatio)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.haarCompress(compressRatio), destImageName);
  }

  /**
   * Gets an image from the application memory with the provided image name.
   *
   * @param imageName name of the image to be retrieved
   * @return an image from the application memory with the provided image name.
   * @throws ImageNotFoundException If an image with the provided image name does not exist
   */
  // Handles ImageNotFoundException at a single place to reduce code duplication.
  private IImage getImageFromMemory(String imageName) throws ImageNotFoundException {
    if (!doesImageExist(imageName)) {
      throw new ImageNotFoundException(String.format("'%s': does not exist", imageName));
    } else {
      return loadedImages.get(imageName);
    }
  }

  /**
   * Saves an image to the application memory with the provided alias.
   *
   * @param image     the image to be saved
   * @param imageName the alias to refer to the saved image
   * @throws InvalidImageNameException If the provided alias cannot be used to refer to the image
   */
  // Handles InvalidImageNameException at a single place to reduce code duplication.
  private void saveImageToMemory(IImage image, String imageName) throws InvalidImageNameException {
    if (!isValidAliasName(imageName)) {
      throw new InvalidImageNameException(
          String.format("'%s': cannot be used as an alias for the image", imageName));
    }

    // If Image object is null. System should break.
    loadedImages.put(imageName, image);
  }

  /**
   * Checks the memory of the application to determine if an image with the provided name exists.
   *
   * @param imageName the name of the image to be checked
   * @return True if an image with the provided image name exists, False otherwise
   */
  // In the near future, this method may call a database or the web
  // to check if an Image exists in the memory of the application.
  // Hence, a separate method has been created for a logic that seems too trivial to
  // deserve its own method.
  private boolean doesImageExist(String imageName) {
    return imageName != null && loadedImages.containsKey(imageName);
  }

  /**
   * Check if the name being assigned to an Image is valid.
   *
   * @param input the name being assigned to an Image
   * @return True if name is valid, False otherwise (Not valid regex, name already assigned, etc.)
   */
  private boolean isValidAliasName(String input) {
    // If name is a valid regex. Allow overwriting existing image alias.
    return input != null && input.matches(VALID_ALIAS_NAME_CHARS_REGEX);
  }
}
