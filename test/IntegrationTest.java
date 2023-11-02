import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import imeprogram.controller.Controller;
import imeprogram.controller.Controller.MessageHelper;
import imeprogram.controller.IController;
import imeprogram.model.IModel;
import imeprogram.model.Image;
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
  String imageNameExceptionMsg = "\n" + MessageHelper.imageNameExceptionMsg;
  //Not used in 1 case - rgbCombine
  String imageNotFoundExpcetionMsg = "\n" + MessageHelper.imageNotFoundExceptionMsg;

  String inputFilePath = "testImage.ppm";

  @Before
  public void setUp() {
    model = new Model();
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
  public void saveImage() {
    loadImage();
    String command = "save \"testImage-saveTest.ppm\" resImage";

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    view = new View(new PrintStream(bytes));
    in = new ByteArrayInputStream(command.getBytes());
    controller = new Controller(model, view, in);
    controller.start();

    try {
      Image.loadImageFromFile("testImage-saveTest.ppm");
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
  public void redComponenet() {
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

    saveImage("testImage-rgbcombine.ppm", "test/integration/resImage-red-tint", controller);
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