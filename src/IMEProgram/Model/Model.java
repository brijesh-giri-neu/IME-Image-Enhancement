package IMEProgram.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * The class represents actions that can be taken in our application. It maintains the session
 * memory of the application.
 */
public class Model implements IModel {

  private Map<String, IImage> loadedImages;
  private static final String VALID_ALIAS_NAME_CHARS_REGEX =
      "[a-zA-Z0-9._-]+";

  public Model() {
    this.loadedImages = new HashMap<String, IImage>();
  }

  @Override
  public void loadImage(String filePath, String imageName) {

  }

  @Override
  public void saveImage(String filePath, String imageName) {

  }

  @Override
  public void redComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void greenComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void blueComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void valueComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void lumaComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void intensityComponent(String sourceImageName, String destImageName) {

  }

  @Override
  public void horizontalFlip(String sourceImageName, String destImageName) {

  }

  @Override
  public void verticalFlip(String sourceImageName, String destImageName) {

  }

  @Override
  public void brighten(int increment, String sourceImageName, String destImageName) {

  }

  @Override
  public void rgbSplit(String sourceImageName, String destImageNameRedComp,
      String destImageNameGreenComp, String destImageNameBlueComp) {

  }

  @Override
  public void rgbCombine(String destImageName, String sourceImageNameRedComp,
      String sourceImageNameGreenComp, String sourceImageNameBlueComp) {

  }

  @Override
  public void blur(String sourceImageName, String destImageName) {

  }

  @Override
  public void sharpen(String sourceImageName, String destImageName) {

  }

  @Override
  public void sepia(String sourceImageName, String destImageName) {

  }

  private boolean isValidAliasName(String input) {
    return input.matches(VALID_ALIAS_NAME_CHARS_REGEX);
  }
}
