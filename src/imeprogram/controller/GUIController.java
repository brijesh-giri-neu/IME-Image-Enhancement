package imeprogram.controller;

import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.model.IImage;
import imeprogram.model.IModel;
import imeprogram.model.IReadOnlyImage;
import imeprogram.model.Image;
import imeprogram.model.LineGraph2D;
import imeprogram.view.IGUIView;
import java.io.FileNotFoundException;

/**
 * This class represents a GUI Controller for the IMEProgram. It maintains a reference for the image
 * being shown on the UI.
 */
public class GUIController implements IFeatures {

  // How to implement multiple tabs?
  // When user switches image tabs, view hits the controller with a tab reference it has.
  // E.g. view maintains a list of tabs, user clicks tab3. View hits controller with input tab3.
  // Controller would get image associated with tab3 from the model, and tell view to display it.
  // So, view would always be the one passing the image reference on which operation is to be done.
  // In conclusion, my controller should always require a view to pass a string image reference.
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
      view.displayError(GUIMessageHelper.LOAD_FILE_NOT_FOUND_EXCEPTION_MSG);
    } catch (FileFormatException e) {
      view.displayError(GUIMessageHelper.LOAD_FILE_FORMAT_EXCEPTION_MSG);
    } catch (InvalidImageNameException e) {
      view.displayError(
          String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void saveImage(String imageName, String filePath) {
    try {
      model.saveImageToFile(imageName, filePath);
    } catch (FileNotFoundException e) {
      view.displayError(
          String.format(GUIMessageHelper.SAVE_FILE_NOT_FOUND_EXCEPTION_MSG, filePath));
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (FileFormatException e) {
      view.displayError("Error: Cannot save file. Unsupported file extension");
    }
  }

  @Override
  public void redComponent(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.redComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void greenComponent(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.greenComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void blueComponent(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.blueComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void lumaComponent(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.lumaComponent(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void horizontalFlip(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.horizontalFlip(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void verticalFlip(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.verticalFlip(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void blur(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.blur(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void sharpen(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.sharpen(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void sepia(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.sepia(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void compress(String sourceImage, int compressRatio) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.compress(sourceImage, destImage, compressRatio);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    } catch (IllegalArgumentException e) {
      view.displayError("Error: Provided compression percentage is invalid");
    }
  }

  @Override
  public void colorCorrect(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.colorCorrect(sourceImage, destImage);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void adjustLevels(String sourceImage, int black, int mid, int white) {
    String destImage = getPreviewReference(sourceImage);
    try {
      model.adjustLevels(sourceImage, destImage, black, mid, white);
      sendImageToView(destImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    } catch (IllegalArgumentException e) {
      view.displayError("Error: Provided black, mid, and white values are illegal");
    }
  }

  @Override
  public void splitView(String sourceImage, int splitRatio) {
    String operatedImage = getPreviewReference(sourceImage);
    String splitImage = getSplitViewReference(sourceImage);
    try {
      IReadOnlyImage operatedImageData = model.getImageData(operatedImage);
      // Create a copy of operated image. Split view will be shown in the copy.
      IImage operatedImageCopy = new Image(operatedImageData.getRgbValues(),
          operatedImageData.getWidth(),
          operatedImageData.getHeight());
      model.saveImageDataToMemory(operatedImageCopy, splitImage);
      model.splitView(sourceImage, splitImage, splitRatio);
      sendImageToView(splitImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    } catch (IllegalArgumentException e) {
      view.displayError("Given Split ratio argument is invalid");
    }
  }

  @Override
  public void histogram(String sourceImage) {
    String histImage = getHistogramReference(sourceImage);
    try {
      model.histogram(sourceImage, histImage, new LineGraph2D());
      sendHistogramToView(histImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void applyChanges(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    overWriteImage(destImage, sourceImage);
    model.removeImageFromMemory(destImage);
    model.removeImageFromMemory(getSplitViewReference(sourceImage));
    sendImageToView(sourceImage);
  }

  @Override
  public void cancelChanges(String sourceImage) {
    String destImage = getPreviewReference(sourceImage);
    //overWriteImage(sourceImage, destImage);
    model.removeImageFromMemory(destImage);
    model.removeImageFromMemory(getSplitViewReference(sourceImage));
    sendImageToView(sourceImage);
  }

  @Override
  public void getImageData(String imageName) {
    try {
      sendImageToView(imageName);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    }
  }

  @Override
  public void getImageHistogramData(String imageName) {
    String histImage = getHistogramReference(imageName);
    try {
      sendHistogramToView(histImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    }
  }

  /**
   * Overwrite data retrieved from "fromImage" to the given "toImage"
   *
   * @param fromImage get data from this image.
   * @param toImage   write to this image.
   */
  private void overWriteImage(String fromImage, String toImage) {
    try {
      IReadOnlyImage fromImageData = model.getImageData(fromImage);
      // Replace the dest image with the source image
      IImage fromImageCopy = new Image(fromImageData.getRgbValues(), fromImageData.getWidth(),
          fromImageData.getHeight());
      model.saveImageDataToMemory(fromImageCopy, toImage);
    } catch (ImageNotFoundException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, ""));
    } catch (InvalidImageNameException e) {
      view.displayError(String.format(GUIMessageHelper.IMAGE_NAME_EXCEPTION_MSG, ""));
    }
  }

  private void sendImageToView(String imageName) {
    IReadOnlyImage result = model.getImageData(imageName);
    view.displayImage(result);
  }

  private void sendHistogramToView(String histogramImageName) {
    IReadOnlyImage result = model.getImageData(histogramImageName);
    view.displayHistogram(result);
  }

  private String getPreviewReference(String imageName) {
    return imageName + "previewImage";
  }

  private String getSplitViewReference(String imageName) {
    return imageName + "splitViewImage";
  }

  private String getHistogramReference(String imageName) {
    return imageName + "histogramImage";
  }

  /**
   * A helper class containing messages for the GUI view.
   */
  public static class GUIMessageHelper {

    public static final String IMAGE_NAME_EXCEPTION_MSG =
        "Error: Given image alias is invalid";
    //Not used in 1 case - rgbCombine
    public static final String IMAGE_NOT_FOUND_EXCEPTION_MSG =
        "Error: Image does not exist";
    public static final String LOAD_FILE_NOT_FOUND_EXCEPTION_MSG =
        "Error: Cannot load file. Please check path";
    public static final String LOAD_FILE_FORMAT_EXCEPTION_MSG =
        "Error: Cannot load file. Invalid file";
    public static final String SAVE_FILE_NOT_FOUND_EXCEPTION_MSG =
        "Error: Cannot save file. Please check provided path: %s";
    public static final String SAVE_FILE_FORMAT_EXCEPTION_MSG =
        "Error: Cannot save file. Unsupported file extension";
  }
}
