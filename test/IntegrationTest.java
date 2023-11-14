import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import imeprogram.controller.Controller;
import imeprogram.controller.Controller.MessageHelper;
import imeprogram.controller.IController;
import imeprogram.model.IModel;
import imeprogram.model.Image;
import imeprogram.fileparser.ImageFileIOFactory;
import imeprogram.model.Model;
import imeprogram.view.IView;
import imeprogram.view.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for integration testing the IMEProgram.
 */
public class IntegrationTest {
  // Ensure the there is a file named "testImage.ppm" in the test/integrationImages directory.

  private IController controller;
  private IModel model;
  private IView view;
  private InputStream in;
  String expectedSuccess = "\n Operation successful";

  // Not used in 2 cases - load, rgbSplit
  String imageNameExceptionMsg = "\n" + MessageHelper.IMAGE_NAME_EXCEPTION_MSG;
  //Not used in 1 case - rgbCombine
  String imageNotFoundExpcetionMsg = "\n" + MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG;

  String inputFilePath = "test/integrationImages/testImage.ppm";

  @Before
  public void setUp() {
    model = new Model(new ImageFileIOFactory());
  }

  @Test
  public void loadImage() {
    String command = String.format("load %s resImage", inputFilePath);

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void saveImageTest() {
    loadImage();
    String command = "save test/integrationResults/testImage-saveTest.ppm resImage";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    try {
      Image.loadImageFromFile("test/integrationResults/testImage-saveTest.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void flipVertical() {
    loadImage();
    String command = "vertical-flip resImage resImage-verticalFlip";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-verticalFlip.ppm", "resImage-verticalFlip", controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-verticalFlip.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void flipHorizontal() {
    loadImage();
    String command = "horizontal-flip resImage resImage-horizontalFlip";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-horizontalFlip.ppm", "resImage-horizontalFlip",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-horizontalFlip.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void blur() {
    loadImage();
    String command = "blur resImage resImage-blur";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-blur.ppm", "resImage-blur",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-blur.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void sharpen() {
    loadImage();
    String command = "sharpen resImage resImage-sharpen";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-sharpen.ppm", "resImage-sharpen",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-sharpen.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void sepia() {
    loadImage();
    String command = "sepia resImage resImage-sepia";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-sepia.ppm", "resImage-sepia",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-sepia.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void brighten() {
    loadImage();
    String command = "brighten 50 resImage resImage-brighten-50";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-brighten-50.ppm", "resImage-brighten-50",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-brighten-50.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void rgbSplit() {
    loadImage();
    String command = "rgb-split resImage resImage-red resImage-green resImage-blue";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-redSplit.ppm", "resImage-red",
        controller);
    saveImage("test/integrationResults/testImage-greenSplit.ppm", "resImage-green",
        controller);
    saveImage("test/integrationResults/testImage-blueSplit.ppm", "resImage-blue",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-redSplit.ppm");
      Image.loadImageFromFile("test/integrationResults/testImage-greenSplit.ppm");
      Image.loadImageFromFile("test/integrationResults/testImage-blueSplit.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void lumaComponenet() {
    loadImage();
    String command = "luma-component resImage resImage-greyscale";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-greyscale.ppm", "resImage-greyscale",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-greyscale.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void redComponent() {
    loadImage();
    String command = "red-component resImage resImage-red";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-red.ppm", "resImage-red",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-red.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void rgbCombine() {
    loadImage();
    rgbSplit();
    String command = "rgb-combine resImage-red-tint resImage-red resImage-green resImage-blue";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-rgbcombine.ppm", "resImage-red-tint", controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-rgbcombine.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void greenComponent() {
    loadImage();
    String command = "green-component resImage resImage-green";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-green.ppm", "resImage-green",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-green.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void blueComponent() {
    loadImage();
    String command = "blue-component resImage resImage-blue";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-blue.ppm", "resImage-blue",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-blue.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void valueComponent() {
    loadImage();
    String command = "value-component resImage resImage-value";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-value.ppm", "resImage-value",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-value.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void intensityComponent() {
    loadImage();
    String command = "intensity-component resImage resImage-intensity";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-intensity.ppm", "resImage-intensity",
        controller);
    try {
      Image.loadImageFromFile("test/integrationResults/testImage-intensity.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(expectedSuccess, bytes.toString());
  }

  @Test
  public void darken() {
    loadImage();
    String command = "brighten -50 resImage resImage-darken-50";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    saveImage("test/integrationResults/testImage-darken-50.ppm", "resImage-darken-50",
        controller);

    try {
      Image.loadImageFromFile("test/integrationResults/testImage-darken-50.ppm");
    } catch (IOException e) {
      fail(e.getMessage());
    }

    assertEquals(expectedSuccess, bytes.toString());
  }


  private void saveImage(String inputFilePath, String imagename, IController controller) {
    String command = String.format("save %s %s", inputFilePath, imagename);

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();
  }

}
