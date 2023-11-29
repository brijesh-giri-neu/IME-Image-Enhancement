package imeprogram.controller;

import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.model.IModel;
import imeprogram.view.IGUIView;
import java.io.FileNotFoundException;

/**
 * This class represents a GUI Controller for the IMEProgram.
 */
public class GUIController implements IFeatures {

  private final IModel model;
  private final IGUIView view;

  /**
   * Instantiate the controller object.
   *
   * @param model the input model
   * @param view  the input GUI view
   */
  public GUIController(IModel model, IGUIView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void loadImage(String filePath, String imageName) {
    try {
      model.loadImageFromFile(filePath, imageName);
      sendImageToView(imageName);
    } catch (FileNotFoundException e) {
      view.displayError("Error: Cannot load file. Please check path");
    } catch (FileFormatException e) {
      view.displayError("Error: Cannot load file. Invalid file");
    } catch (InvalidImageNameException e) {
      view.displayError(
          String.format("Error: %s cannot be used as an alias to refer to an image", imageName));
    }
  }

  @Override
  public void saveImage(String imageName, String filePath) {
    try {
      model.saveImageToFile(imageName, filePath);
    } catch (FileNotFoundException e) {
      view.displayError("Error: Cannot save file. Please check provided path: " + filePath);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, imageName));
    } catch (FileFormatException e) {
      view.displayError("Error: Cannot save file. Unsupported file extension");
    }
  }

  @Override
  public void redComponent(String sourceImage, String destImage) {
    try {
      model.redComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void greenComponent(String sourceImage, String destImage) {
    try {
      model.greenComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void blueComponent(String sourceImage, String destImage) {
    try {
      model.blueComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void lumaComponent(String sourceImage, String destImage) {
    try {
      model.lumaComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void horizontalFlip(String sourceImage, String destImage) {
    try {
      model.horizontalFlip(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void verticalFlip(String sourceImage, String destImage) {
    try {
      model.verticalFlip(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void blur(String sourceImage, String destImage) {
    try {
      model.blur(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void sharpen(String sourceImage, String destImage) {
    try {
      model.sharpen(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void sepia(String sourceImage, String destImage) {
    try {
      model.sepia(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void compress(String sourceImage, String destImage, int compressRatio) {
    try {
      model.compress(sourceImage, destImage, compressRatio);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    } catch (IllegalArgumentException e) {
      view.displayError("Error: Provided compression percentage is invalid");
    }
  }

  @Override
  public void colorCorrect(String sourceImage, String destImage) {
    try {
      model.colorCorrect(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void adjustLevels(String sourceImage, String destImage, int black, int mid, int white) {
    try {
      model.adjustLevels(sourceImage, destImage, black, mid, white);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    } catch (NumberFormatException e) {
      view.displayError(MessageHelper.NUMBER_FORMAT_EXCEPTION_MSG);
    } catch (IllegalArgumentException e) {
      view.displayError("Error: Provided black, mid, and white values are illegal");
    }
  }

  @Override
  public void splitView(String sourceImage, String destImage, int splitRatio) {
    try {
      model.splitView(sourceImage, destImage, splitRatio);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    } catch (IllegalArgumentException e) {
      view.displayError("Given Split ratio argument is invalid");
    }
  }

  private void sendImageToView(String imageName) {
    int[][][] result = model.getImageData(imageName);
    view.displayImage(result);
  }
}
