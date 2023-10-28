package IMEProgram.Model;

import java.util.Map;

/**
 * The class represents actions that can be taken in our application. It maintains the session
 * memory of the application.
 */
public class Model implements IModel {

  private Map<String, IImage> loadedImages;

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
}
