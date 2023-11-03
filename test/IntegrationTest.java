import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import imeprogram.controller.Controller;
import imeprogram.controller.Controller.MessageHelper;
import imeprogram.controller.IController;
import imeprogram.model.IModel;
import imeprogram.model.Image;
import imeprogram.model.ImageFileIOFactory;
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
  // Ensure the there is a file named "testImage.ppm" in the root directory.

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
    String command = "save test/integration/testImage-saveTest.ppm resImage";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    try {
      Image.loadImageFromFile("test/integration/testImage-saveTest.ppm");
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

    saveImage("test/integration/resImage-verticalFlip.ppm", "resImage-verticalFlip", controller);
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

    saveImage("test/integration/resImage-horizontalFlip.ppm", "resImage-horizontalFlip",
        controller);
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

    saveImage("test/integration/resImage-blur.ppm", "resImage-blur",
        controller);
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

    saveImage("test/integration/resImage-sharpen.ppm", "resImage-sharpen",
        controller);
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

    saveImage("test/integration/resImage-sepia.ppm", "resImage-sepia",
        controller);
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

    saveImage("test/integration/resImage-brighten-50.ppm", "resImage-brighten-50",
        controller);
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

    saveImage("test/integration/resImage-red.ppm", "resImage-red",
        controller);
    saveImage("test/integration/resImage-green.ppm", "resImage-green",
        controller);
    saveImage("test/integration/resImage-blue.ppm", "resImage-blue",
        controller);
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

    saveImage("test/integration/resImage-greyscale.ppm", "resImage-greyscale",
        controller);
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

    saveImage("test/integration/resImage-red.ppm", "resImage-red",
        controller);
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

    saveImage("test/integration/resImage-rgbcombine.ppm", "resImage-red-tint", controller);
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

    saveImage("test/integration/resImage-green.ppm", "resImage-green",
        controller);
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

    saveImage("test/integration/resImage-blue.ppm", "resImage-blue",
        controller);
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

    saveImage("test/integration/resImage-value.ppm", "resImage-value",
        controller);
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

    saveImage("test/integration/resImage-intensity.ppm", "resImage-intensity",
        controller);
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

    saveImage("test/integration/resImage-darken-50.ppm", "resImage-darken-50",
        controller);
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
