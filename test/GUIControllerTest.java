import static org.junit.Assert.assertEquals;

import imeprogram.controller.GUIController;
import imeprogram.controller.IFeatures;
import imeprogram.controller.MessageHelper;
import imeprogram.model.IModel;
import imeprogram.model.IReadOnlyImage;
import imeprogram.model.Image;
import imeprogram.model.ImageViewer;
import imeprogram.view.IGUIView;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the GUIController.
 */
public class GUIControllerTest {

  private IFeatures controller;
  private IModel model;
  private IGUIView view;
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  // Controller always send this image object to the view.
  // So we can compare input logs of view with this object to verify if view received correct args.
  private IReadOnlyImage expectedImageSentToView = new ImageViewer(new Image(3, 3));
  // Image on which controller is operating.
  private final String imageName = "tab1";
  private final String previewImage = "tab1previewImage";
  private final String splitViewImage = "tab1splitViewImage";

  @Before
  public void setUp() {
    modelLog = new StringBuilder();
    viewLog = new StringBuilder();
    model = new MockModel(modelLog);
    // Always return this output image, which will be sent to view by the controller.
    ((MockModel) model).setOutputImage(expectedImageSentToView);
    view = new MockView(viewLog);
  }

  @Test
  public void loadImage_FilePathHasSpace() {
    String filePath = "abc/dcs sda/dsd";
    String expected = getExpectedModelLog(filePath, this.imageName, this.imageName);

    controller = new GUIController(model, view);
    controller.loadImage(filePath, this.imageName);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void loadImage_FilePathHasNoSpace() {
    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(filePath, this.imageName, this.imageName);

    controller = new GUIController(model, view);
    controller.loadImage(filePath, this.imageName);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void loadImage_FileNotFoundException() {
    ((MockModel) model).setThrowFileNotFoundException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(filePath, this.imageName);

    controller = new GUIController(model, view);
    controller.loadImage(filePath, this.imageName);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        MessageHelper.LOAD_FILE_NOT_FOUND_EXCEPTION_MSG);
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void loadImage_FileFormatException() {
    ((MockModel) model).setThrowFileFormatException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(filePath, this.imageName);

    controller = new GUIController(model, view);
    controller.loadImage(filePath, this.imageName);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        MessageHelper.LOAD_FILE_FORMAT_EXCEPTION_MSG);
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void loadImage_InvalidException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(filePath, this.imageName);

    controller = new GUIController(model, view);
    controller.loadImage(filePath, this.imageName);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format("Error: %s cannot be used as an alias to refer to an image", this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasSpace() {
    String filePath = "abc/dcs sda/dsd";
    String expected = getExpectedModelLog(this.imageName, filePath);

    controller = new GUIController(model, view);
    controller.saveImage(this.imageName, filePath);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
  }

  @Test
  public void saveImage_FilePathHasNoSpace() {
    String filePath = "abc/dcs sda/dsd";

    String expected = getExpectedModelLog(this.imageName, filePath);

    controller = new GUIController(model, view);
    controller.saveImage(this.imageName, filePath);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
  }

  @Test
  public void saveImage_FileNotFoundException() {
    ((MockModel) model).setThrowFileNotFoundException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(this.imageName, filePath);

    controller = new GUIController(model, view);
    controller.saveImage(this.imageName, filePath);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.SAVE_FILE_NOT_FOUND_EXCEPTION_MSG, filePath));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void saveImage_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(this.imageName, filePath);

    controller = new GUIController(model, view);
    controller.saveImage(this.imageName, filePath);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void saveImage_FileFormatException() {
    ((MockModel) model).setThrowFileFormatException(true);

    String filePath = "abc/dcssda/dsd";
    String expected = getExpectedModelLog(this.imageName, filePath);

    controller = new GUIController(model, view);
    controller.saveImage(this.imageName, filePath);
    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(MessageHelper.SAVE_FILE_FORMAT_EXCEPTION_MSG);
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void redComponent() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.redComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void redComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.redComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void redComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.redComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void greenComponent() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.greenComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void greenComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.greenComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void greenComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.greenComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void blueComponent() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.blueComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void blueComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.blueComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void blueComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.blueComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void lumaComponent() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.lumaComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void lumaComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.lumaComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void lumaComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.lumaComponent(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void horizontalFlip() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.horizontalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void horizontalFlip_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.horizontalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void horizontalFlip_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.horizontalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void verticalFlip() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.verticalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void verticalFlip_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.verticalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void verticalFlip_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.verticalFlip(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void blur() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.blur(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void blur_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.blur(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void blur_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.blur(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void sharpen() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.sharpen(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void sharpen_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.sharpen(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void sharpen_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.sharpen(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void sepia() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.sepia(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void sepia_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.sepia(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void sepia_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.sepia(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void compress() {
    int compressRatio = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage,
        String.valueOf(compressRatio), this.previewImage);
    controller = new GUIController(model, view);
    controller.compress(this.imageName, compressRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void compress_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    int compressRatio = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage,
        String.valueOf(compressRatio));
    controller = new GUIController(model, view);
    controller.compress(this.imageName, compressRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void compress_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    int compressRatio = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage,
        String.valueOf(compressRatio));
    controller = new GUIController(model, view);
    controller.compress(this.imageName, compressRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void compress_IllegalArgumentException() {
    ((MockModel) model).setThrowIllegalArgumentException(true);

    int compressRatio = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage,
        String.valueOf(compressRatio));
    controller = new GUIController(model, view);
    controller.compress(this.imageName, compressRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        "Error: Provided compression percentage is invalid");
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void colorCorrect() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.colorCorrect(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void colorCorrect_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.colorCorrect(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void colorCorrect_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.colorCorrect(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void adjustLevels() {
    int black = 10;
    int mid = 10;
    int white = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage, String.valueOf(black),
        String.valueOf(mid), String.valueOf(white), this.previewImage);
    controller = new GUIController(model, view);
    controller.adjustLevels(this.imageName, black, mid, white);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void adjustLevels_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    int black = 10;
    int mid = 10;
    int white = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage, String.valueOf(black),
        String.valueOf(mid), String.valueOf(white));
    controller = new GUIController(model, view);
    controller.adjustLevels(this.imageName, black, mid, white);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void adjustLevels_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    int black = 10;
    int mid = 10;
    int white = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage, String.valueOf(black),
        String.valueOf(mid), String.valueOf(white));
    controller = new GUIController(model, view);
    controller.adjustLevels(this.imageName, black, mid, white);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void adjustLevels_IllegalArgumentException() {
    ((MockModel) model).setThrowIllegalArgumentException(true);

    int black = 10;
    int mid = 10;
    int white = 10;
    String expected = getExpectedModelLog(this.imageName, this.previewImage, String.valueOf(black),
        String.valueOf(mid), String.valueOf(white));
    controller = new GUIController(model, view);
    controller.adjustLevels(this.imageName, black, mid, white);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        "Error: Provided black, mid, and white values are illegal");
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void splitView() {
    int splitRatio = -100;
    String expected = getExpectedModelLog(this.previewImage,
        this.expectedImageSentToView.toString(), this.splitViewImage, this.imageName,
        this.splitViewImage, String.valueOf(splitRatio), this.splitViewImage);
    controller = new GUIController(model, view);
    controller.splitView(this.imageName, splitRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void splitView_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    int splitRatio = -100;
    String expected = getExpectedModelLog(this.previewImage);
    controller = new GUIController(model, view);
    controller.splitView(this.imageName, splitRatio);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG,
            this.imageName.concat(", " + this.previewImage)));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void splitView_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    int splitRatio = -100;
    String expected = getExpectedModelLog(this.previewImage,
        this.expectedImageSentToView.toString(), this.splitViewImage);
    controller = new GUIController(model, view);
    controller.splitView(this.imageName, splitRatio);

    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void splitView_IllegalArgumentException() {
    ((MockModel) model).setThrowIllegalArgumentException(true);

    int splitRatio = -100;
    String expected = getExpectedModelLog(this.previewImage,
        this.expectedImageSentToView.toString(), this.splitViewImage);
    controller = new GUIController(model, view);
    controller.splitView(this.imageName, splitRatio);

    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog("Given Split ratio argument is invalid");
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void histogram() {
    String expected = getExpectedModelLog(this.imageName, this.previewImage, this.previewImage);
    controller = new GUIController(model, view);
    controller.histogram(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void histogram_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.histogram(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, this.imageName));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void histogram_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String expected = getExpectedModelLog(this.imageName, this.previewImage);
    controller = new GUIController(model, view);
    controller.histogram(this.imageName);

    // Verify view received correct inputs.
    String expectedView = getExpectedFailureViewLog(
        String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, this.previewImage));
    assertEquals(expectedView, viewLog.toString());
  }

  @Test
  public void applyChanges() {
    String expected = getExpectedModelLog(this.previewImage,
        this.expectedImageSentToView.toString(), this.imageName, this.previewImage,
        this.splitViewImage, this.imageName);
    controller = new GUIController(model, view);
    controller.applyChanges(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  @Test
  public void cancelChanges() {
    String expected = getExpectedModelLog(this.previewImage, this.splitViewImage, this.imageName);
    controller = new GUIController(model, view);
    controller.cancelChanges(this.imageName);

    // Verify model received correct inputs.
    assertEquals(expected, modelLog.toString());
    // Verify view received correct inputs.
    assertEquals(getExpectedSuccessViewLog(), viewLog.toString());
  }

  private String getExpectedModelLog(String... args) {
    StringBuilder expectedLog = new StringBuilder();
    for (String arg : args) {
      expectedLog.append("\n" + arg);
    }
    return expectedLog.toString();
  }

  /**
   * Return the expected log in the view. The view log will always be the controller object followed
   * by the image object sent by the controller to the view.
   *
   * @return the expected view log.
   */
  private String getExpectedSuccessViewLog() {
    return controller.toString() + this.expectedImageSentToView.toString();
  }

  private String getExpectedFailureViewLog(String failureMsg) {
    return controller.toString() + failureMsg;
  }

  class MockView implements IGUIView {

    // View log will always contain the passed features object first.
    // And then any inputs received during callback execution.
    private StringBuilder viewLog;

    public MockView(StringBuilder viewLog) {
      this.viewLog = viewLog;
    }

    @Override
    public void displayImage(IReadOnlyImage image) {
      viewLog.append(image.toString());
    }

    @Override
    public void displayError(String errorMessage) {
      viewLog.append(errorMessage);
    }

    @Override
    public void addFeatures(IFeatures features) {
      viewLog.append(features.toString());
    }
  }
}