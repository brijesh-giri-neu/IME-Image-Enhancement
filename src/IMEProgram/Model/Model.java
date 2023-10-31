package IMEProgram.Model;

import IMEProgram.Exceptions.FileFormatException;
import IMEProgram.Exceptions.ImageNotFoundException;
import IMEProgram.Exceptions.InvalidFilePathException;
import IMEProgram.Exceptions.InvalidImageNameException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * The class represents actions that can be taken in our application. It maintains the session
 * memory of the application.
 */
public class Model implements IModel {
  // NOTE - No action for convert to grayscale.

  // Represents the session memory of the application. In the future, it can be moved to a database or the web.
  private Map<String, IImage> loadedImages;
  private static final String VALID_ALIAS_NAME_CHARS_REGEX = "[a-zA-Z0-9._-]+";

  public Model() {
    this.loadedImages = new HashMap<String, IImage>();
  }

  @Override
  public void loadImageFromFile(String filePath, String imageName)
      throws FileNotFoundException, FileFormatException, InvalidImageNameException {
    // This check will be re-assigned to saveImageToMemory() after logic to load image is written.
    if (!isValidAliasName(imageName)) {
      throw new InvalidImageNameException(
          String.format("'%s': cannot be used as an alias for the image", imageName));
    }

    // Delegate FileNotFoundException to the Image class.
    // Image class needs a public const which takes filepath as arg or a builder method that takes filepath.
  }

  @Override
  public void saveImageToFile(String imageName, String filePath)
      throws ImageNotFoundException, InvalidFilePathException, FileFormatException {
    IImage image = getImageFromMemory(imageName);

    // Delegate InvalidFilePathException to the Image class.
    image.saveToFile(filePath);
  }

  @Override
  public void redComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getRedComponent(), destImageName);
  }

  @Override
  public void greenComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getGreenComponent(), destImageName);
  }

  @Override
  public void blueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.getBlueComponent(), destImageName);
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
  public void brighten(int increment, String sourceImageName, String destImageName)
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
  }

  @Override
  public void blur(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.gaussianBlur(), destImageName);
  }

  @Override
  public void sharpen(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.sharpen(), destImageName);
  }

  @Override
  public void sepia(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    IImage sourceImg = getImageFromMemory(sourceImageName);

    saveImageToMemory(sourceImg.convertToSepia(), destImageName);
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
  // In the near future, this method may call a database or the web to check if an Image exists in the memory of the application.
  // Hence, a separate method has been created for a logic that seems too trivial to deserve its own method.
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
    // If name is a valid regex and is not already assigned.
    return input != null && input.matches(VALID_ALIAS_NAME_CHARS_REGEX) && !doesImageExist(input);
  }
}