import static org.junit.Assert.assertEquals;

import IMEProgram.Controller.Controller;
import IMEProgram.Controller.IController;
import IMEProgram.Exceptions.FileFormatException;
import IMEProgram.Exceptions.ImageNotFoundException;
import IMEProgram.Exceptions.InvalidImageNameException;
import IMEProgram.Model.IModel;
import IMEProgram.View.IView;
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

  private String expectedSuccess = "\n Operation successful";


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
    assertEquals(expected, modelLog.toString());
  }

  @Test
  public void loadImage_FilePathHasNoSpace() {
    String command = "load \"res/folderpath/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "res/folderpath/file.txt" + "\n" + "resImage";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void loadImage_FilePathHasNoQuotes() {
    String command = "load res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "res/folderpath/file.txt" + "\n" + "resImage";
    assertEquals(expected, modelLog.toString());
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
  public void saveImage_FilePathHasSpace() {
    String command = "save \"res/folder path/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folder path/file.txt";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasNoSpace() {
    String command = "save \"res/folderpath/file.txt\" resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folderpath/file.txt";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
  }

  @Test
  public void saveImage_FilePathHasNoQuotes() {
    String command = "save res/folderpath/file.txt resImage";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "resImage" + "\n" + "res/folderpath/file.txt";
    assertEquals(expected, modelLog.toString());
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
  public void horizontalFlip() {
    String command = "horizontal-flip koala koala-horizontalFlip";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expected = "\n" + "koala" + "\n" + "koala-horizontalFlip";
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
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
    assertEquals(expected, modelLog.toString());
    assertEquals(expectedSuccess, viewLog.toString());
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
  public void runScript() {
    String command = "run res/unitTestScript.txt";
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    String expectedModelLog = "\n"
        + "res/folder path/file.txt\n"
        + "resImage\n"
        + "koala\n"
        + "koala-red\n"
        + "koala\n"
        + "koala-blue\n"
        + "koala\n"
        + "koala-horizontalFlip\n"
        + "50\n"
        + "koala\n"
        + "koala-brighten\n"
        + "koala\n"
        + "koala-red\n"
        + "koala-green\n"
        + "koala-blue\n"
        + "koala-red-tint\n"
        + "koala-red\n"
        + "koala-green\n"
        + "koala-blue\n"
        + "koala\n"
        + "koala-sharpen\n"
        + "koala\n"
        + "koala-sepia\n"
        + "resImage\n"
        + "res/folderpath/file.txt";

    String expectedViewLog = "\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + "Invalid number of arguments\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + "Invalid number of arguments\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + " Operation successful\n"
        + " Operation successful";

    assertEquals(expectedModelLog, modelLog.toString());
    assertEquals(expectedViewLog, viewLog.toString());
  }

  class MockModel implements IModel {

    private StringBuilder modelLog;

    public MockModel(StringBuilder modelLog) {
      this.modelLog = modelLog;
    }

    private void logInputs(String[] orderedInputs) {
      for (int i = 0; i < orderedInputs.length; i++) {
        modelLog.append("\n" + orderedInputs[i]);
      }
    }

    @Override
    public void loadImageFromFile(String filePath, String imageName)
        throws FileNotFoundException, FileFormatException, InvalidImageNameException {
      logInputs(new String[]{filePath, imageName});
    }

    @Override
    public void saveImageToFile(String imageName, String filePath)
        throws ImageNotFoundException, FileNotFoundException, FileFormatException {
      logInputs(new String[]{imageName, filePath});
    }

    @Override
    public void redComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void greenComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void blueComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void valueComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void lumaComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void intensityComponent(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void horizontalFlip(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void verticalFlip(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void brighten(int increment, String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{String.valueOf(increment), sourceImageName, destImageName});
    }

    @Override
    public void rgbSplit(String sourceImageName, String destImageNameRedComp,
        String destImageNameGreenComp, String destImageNameBlueComp)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageNameRedComp, destImageNameGreenComp,
          destImageNameBlueComp});
    }

    @Override
    public void rgbCombine(String destImageName, String sourceImageNameRedComp,
        String sourceImageNameGreenComp, String sourceImageNameBlueComp)
        throws ImageNotFoundException, IllegalArgumentException, InvalidImageNameException {
      logInputs(new String[]{destImageName, sourceImageNameRedComp, sourceImageNameGreenComp,
          sourceImageNameBlueComp});
    }

    @Override
    public void blur(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void sharpen(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
    }

    @Override
    public void sepia(String sourceImageName, String destImageName)
        throws ImageNotFoundException, InvalidImageNameException {
      logInputs(new String[]{sourceImageName, destImageName});
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