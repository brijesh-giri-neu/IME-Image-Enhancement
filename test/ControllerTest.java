import static org.junit.Assert.assertEquals;

import imeprogram.controller.Controller;
import imeprogram.controller.Controller.MessageHelper;
import imeprogram.controller.IController;
import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.model.ILineGraph;
import imeprogram.model.IModel;
import imeprogram.view.IView;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the controller.
 */
public class ControllerTest {

  private IController controller;
  private IModel model;
  private IView view;
  private InputStream in;
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  String expectedSuccess = "\n Operation successful";

  // Not used in 2 cases - load, rgbSplit
  String imageNameExceptionMsg = "\n" + MessageHelper.IMAGE_NAME_EXCEPTION_MSG;
  //Not used in 1 case - rgbCombine
  String imageNotFoundExpcetionMsg = "\n" + MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG;

  @Before
  public void setUp() {
    modelLog = new StringBuilder();
    viewLog = new StringBuilder();
    model = new MockModel(modelLog);
    view = new MockView(viewLog);
  }

  @Test
  public void loadImage_FilePathHasSpace() {
    String command = "load \"res/folder path/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "res/folder path/file.txt" + "\n" + "resImage";
    assertEquals(expected.toLowerCase(), modelLog.toString());
  }

  @Test
  public void loadImage_FilePathHasNoSpace() {
    String command = "load \"res/folderpath/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "res/folderpath/file.txt" + "\n" + "resImage";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void loadImage_FilePathHasNoQuotes() {
    String command = "load res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "res/folderpath/file.txt" + "\n" + "resImage";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void loadImage_InvalidNumArgs() {
    String command = "load res/folderpath/file.txt resImage abcd";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void loadImage_FileNotFoundException() {
    ((MockModel) model).setThrowFileNotFoundException(true);

    String command = "load res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "Error: Cannot load file. Please check path";
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void loadImage_FileFormatException() {
    ((MockModel) model).setThrowFileFormatException(true);

    String command = "load res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "Error: Cannot load file. Invalid file";
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void loadImage_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "load res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "Error: resimage cannot be used as an alias to refer to an image";
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasSpace() {
    String command = "save \"res/folder path/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folder path/file.txt";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasNoSpace() {
    String command = "save \"res/folderpath/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folderpath/file.txt";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasNoQuotes() {
    String command = "save res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folderpath/file.txt";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void saveImage_InvalidNumArgs() {
    String command = "save res/folderpath/file.txt";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void saveImage_FileNotFoundException() {
    ((MockModel) model).setThrowFileNotFoundException(true);

    String command = "save res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected =
        "\n" + "Error: Cannot save file. Please check provided path: res/folderpath/file.txt";
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void saveImage_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "save res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "resimage");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void saveImage_FileFormatException() {
    ((MockModel) model).setThrowFileFormatException(true);

    String command = "save res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "Error: Cannot save file. Unsupported file extension";
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void redComponent() {
    String command = "red-component koala koala-red";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-red";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void redComponent_InvalidArgs() {
    String command = "red-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void redComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "red-component koala koala-red";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void redComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "red-component koala koala-red";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-red");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void greenComponent() {
    String command = "green-component koala koala-green";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-green";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void greenComponent_InvalidArgs() {
    String command = "green-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void greenComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "green-component koala koala-green";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void greenComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "green-component koala koala-green";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-green");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void blueComponent() {
    String command = "blue-component koala koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-blue";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void blueComponent_InvalidArgs() {
    String command = "blue-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void blueComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "blue-component koala koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void blueComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "blue-component koala koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-blue");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void valueComponent() {
    String command = "value-component koala koala-value";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-value";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void valueComponent_InvalidArgs() {
    String command = "value-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void valueComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "value-component koala koala-value";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void valueComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "value-component koala koala-value";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-value");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void lumaComponent() {
    String command = "luma-component koala koala-luma";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-luma";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void lumaComponent_InvalidArgs() {
    String command = "luma-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void lumaComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "luma-component koala koala-luma";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void lumaComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "luma-component koala koala-luma";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-luma");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void intensityComponent() {
    String command = "intensity-component koala koala-intensity";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-intensity";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void intensityComponent_InvalidArgs() {
    String command = "intensity-component koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void intensityComponent_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "intensity-component koala koala-intensity";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void intensityComponent_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "intensity-component koala koala-intensity";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-intensity");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void horizontalFlip() {
    String command = "horizontal-flip koala koala-horizontalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-horizontalFlip";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void horizontalFlip_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "horizontal-flip koala koala-horizontalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void horizontalFlip_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "horizontal-flip koala koala-horizontalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-horizontalflip");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void horizontalFlip_InvalidArgs() {
    String command = "horizontal-flip koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void verticalFlip() {
    String command = "vertical-flip koala koala-verticalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-verticalFlip";
    assertEquals(expected.toLowerCase(), modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void verticalFlip_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "vertical-flip koala koala-verticalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void verticalFlip_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "vertical-flip koala koala-verticalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-verticalflip");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void verticalFlip_InvalidArgs() {
    String command = "vertical-flip koala ";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void brighten() {
    String command = "brighten 50 koala koala-brighten";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "50" + "\n" + "koala" + "\n" + "koala-brighten";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void brighten_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "brighten 50 koala koala-brighten";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void brighten_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "brighten 50 koala koala-brighten";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-brighten");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void brighten_InvalidArgs() {
    String command = "brighten koala";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void rgbSplit() {
    String command = "rgb-split koala koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected =
        "\n" + "koala" + "\n" + "koala-red" + "\n" + "koala-green" + "\n" + "koala-blue";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void rgbSplit_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "rgb-split koala koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void rgbSplit_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "rgb-split koala koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format("\nError: One of the mentioned destImage alias is invalid");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void rgbSplit_InvalidArgs() {
    String command = "rgb-split koala koala-red koala-green asas koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void rgbCombine() {
    String command = "rgb-combine koala-red-tint koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected =
        "\n" + "koala-red-tint" + "\n" + "koala-red" + "\n" + "koala-green" + "\n" + "koala-blue";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void rgbCombine_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "rgb-combine koala-red-tint koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(
        "\nError: Mentioned image alias does not exist. Please check the name");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void rgbCombine_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "rgb-combine koala-red-tint koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-red-tint");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void rgbCombine_IllegalArgumentException() {
    ((MockModel) model).setThrowIllegalArgumentException(true);

    String command = "rgb-combine koala-red-tint koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format("\nError: Given images have different dimensions");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void rgbCombine_InvalidArgs() {
    String command = "rgb-combine koala-red koala-green koala-blue";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void blur() {
    String command = "blur koala koala-blur";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-blur";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void blur_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "blur koala koala-blur";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void blur_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "blur koala koala-blur";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-blur");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void blur_InvalidArgs() {
    String command = "blur kola";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void sharpen() {
    String command = "sharpen koala koala-sharpen";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-sharpen";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void sharpen_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "sharpen koala koala-sharpen";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void sharpen_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "sharpen koala koala-sharpen";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-sharpen");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void sharpen_InvalidArgs() {
    String command = "sharpen kola";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void sepia() {
    String command = "sepia koala koala-sepia";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-sepia";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void sepia_ImageNotFoundException() {
    ((MockModel) model).setThrowImageNotFoundException(true);

    String command = "sepia koala koala-sepia";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNotFoundExpcetionMsg, "koala");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void sepia_InvalidImageNameException() {
    ((MockModel) model).setThrowInvalidImageNameException(true);

    String command = "sepia koala koala-sepia";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = String.format(imageNameExceptionMsg, "koala-sepia");
    assertEquals(expected, viewLog.toString());
  }

  @Test
  public void sepia_InvalidArgs() {
    String command = "sepia kola";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Invalid number of arguments";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void invalidCommand() {
    String command = "doNothing dude";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "";
    String expectedViewLog = "\n" + "Error: Invalid command. Please try again.";
    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void runScript() {
    String command = "run test/unitImages/unitTestScript.txt";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog =
        "\n" + "res/folder path/file.txt\n" + "resimage\n" + "koala\n" + "koala-red\n" + "koala\n"
            + "koala-blue\n" + "koala\n" + "koala-horizontalflip\n" + "50\n" + "koala\n"
            + "koala-brighten\n" + "koala\n" + "koala-red\n" + "koala-green\n" + "koala-blue\n"
            + "koala-red-tint\n" + "koala-red\n" + "koala-green\n" + "koala-blue\n" + "koala\n"
            + "koala-sharpen\n" + "koala\n" + "koala-sepia\n" + "resimage\n"
            + "res/folderpath/file.txt";

    String expectedViewLog =
        "\n" + " Operation successful\n" + " Operation successful\n" + " Operation successful\n"
            + "Invalid number of arguments\n" + " Operation successful\n"
            + " Operation successful\n" + " Operation successful\n"
            + "Invalid number of arguments\n" + " Operation successful\n"
            + " Operation successful\n" + " Operation successful\n" + " Operation successful";

    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  class MockModel implements IModel {

    private StringBuilder modelLog;
    private boolean throwFileNotFoundException = false;
    private boolean throwFileFormatException = false;
    private boolean throwInvalidImageNameException = false;
    private boolean throwImageNotFoundException = false;
    private boolean throwIllegalArgumentException = false;

    public MockModel(StringBuilder modelLog) {
      this.modelLog = modelLog;
    }

    public void setThrowFileNotFoundException(boolean throwFileNotFoundException) {
      this.throwFileNotFoundException = throwFileNotFoundException;
    }

    public void setThrowFileFormatException(boolean throwFileFormatException) {
      this.throwFileFormatException = throwFileFormatException;
    }

    public void setThrowInvalidImageNameException(boolean throwInvalidImageNameException) {
      this.throwInvalidImageNameException = throwInvalidImageNameException;
    }

    public void setThrowImageNotFoundException(boolean throwImageNotFoundException) {
      this.throwImageNotFoundException = throwImageNotFoundException;
    }

    public void setThrowIllegalArgumentException(boolean throwIllegalArgumentException) {
      this.throwIllegalArgumentException = throwIllegalArgumentException;
    }

    private void checkExceptions() {
      if (throwFileFormatException) {
        throwFileFormatException = false;
        throw new FileFormatException("");
      }
      if (throwInvalidImageNameException) {
        throwInvalidImageNameException = false;
        throw new InvalidImageNameException("");
      }
      if (throwImageNotFoundException) {
        throwImageNotFoundException = false;
        throw new ImageNotFoundException("");
      }
      if (throwIllegalArgumentException) {
        throwIllegalArgumentException = false;
        throw new IllegalArgumentException("");
      }
    }

    private void checkFileNotFoundException() throws FileNotFoundException {
      if (throwFileNotFoundException) {
        throwFileNotFoundException = false;
        throw new FileNotFoundException("");
      }
    }

    private void logInputs(String[] orderedInputs) {
      for (int i = 0; i < orderedInputs.length; i++) {
        modelLog.append("\n" + orderedInputs[i]);
      }
    }

    @Override
    public void loadImageFromFile(String filePath, String imageName)
        throws FileNotFoundException, FileFormatException, InvalidImageNameException {
      checkExceptions();
      checkFileNotFoundException();
      logInputs(new String[]{filePath, imageName});
    }

    @Override
    public void saveImageToFile(String imageName, String filePath)
        throws ImageNotFoundException, FileNotFoundException, FileFormatException {
      checkExceptions();
      checkFileNotFoundException();
      logInputs(new String[]{imageName, filePath});
    }

    @Override
    public int[][][] getImageData(String sourceImageName) throws ImageNotFoundException {
      checkExceptions();
      logInputs(new String[]{sourceImageName});
      return new int[3][3][3];
    }

    @Override
    public void redComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void greenComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void blueComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void valueComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void lumaComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void intensityComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void horizontalFlip(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void verticalFlip(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void brighten(String sourceImageName, String destImageName, int increment)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{String.valueOf(increment), sourceImageName, destImageName});
    }

    @Override
    public void rgbSplit(String sourceImageName, String destImageNameRedComp,
        String destImageNameGreenComp, String destImageNameBlueComp)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageNameRedComp, destImageNameGreenComp,
          destImageNameBlueComp});
    }

    @Override
    public void rgbCombine(String destImageName, String sourceImageNameRedComp,
        String sourceImageNameGreenComp, String sourceImageNameBlueComp)
        throws ImageNotFoundException, IllegalArgumentException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{destImageName, sourceImageNameRedComp, sourceImageNameGreenComp,
          sourceImageNameBlueComp});
    }

    @Override
    public void blur(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void sharpen(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void sepia(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void histogram(String sourceImageName, String destImageName, ILineGraph graph)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName, graph.toString()});
    }

    @Override
    public void colorCorrect(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      checkExceptions();
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void adjustLevels(String sourceImageName, String destImageName, int black, int mid,
        int white)
        throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
      checkExceptions();
      logInputs(
          new String[]{sourceImageName, destImageName, String.valueOf(black), String.valueOf(mid),
              String.valueOf(white)});
    }

    @Override
    public void splitView(String sourceImageName, String destImageName, int splitRatio)
        throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
      checkExceptions();
      logInputs(
          new String[]{sourceImageName, destImageName, String.valueOf(splitRatio)});
    }

    @Override
    public void compress(String sourceImageName, String destImageName, int compressRatio)
        throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
      checkExceptions();
      logInputs(
          new String[]{sourceImageName, destImageName, String.valueOf(compressRatio)});
    }
  }

  class MockView implements IView {

    private StringBuilder viewLog;

    public MockView(StringBuilder viewLog) {
      this.viewLog = viewLog;
    }

    @Override
    public void print(String message) {
      viewLog.append("\n" + message);
    }

    @Override
    public void success() {
      viewLog.append(expectedSuccess);
    }
  }
}