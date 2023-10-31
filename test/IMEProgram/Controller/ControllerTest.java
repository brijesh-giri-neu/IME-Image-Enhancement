package IMEProgram.Controller;

import IMEProgram.Model.IModel;
import IMEProgram.View.IView;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the controller.
 */
public class ControllerTest {

  private IModel model;
  private IView view;
  private InputStream in;

  @Before
  public void setUp() {
  }

  @Test
  public void loadImage() {
  }

  @Test
  public void saveImage() {
  }

  @Test
  public void redComponent() {
  }

  @Test
  public void greenComponent() {
  }

  @Test
  public void blueComponent() {
  }

  @Test
  public void valueComponent() {
  }

  @Test
  public void lumaComponent() {
  }

  @Test
  public void intensityComponent() {
  }

  @Test
  public void horizontalFlip() {
  }

  @Test
  public void verticalFlip() {
  }

  @Test
  public void brighten() {
  }

  @Test
  public void rgbSplit() {
  }

  @Test
  public void rgbCombine() {
  }

  @Test
  public void blur() {
  }

  @Test
  public void sharpen() {
  }

  @Test
  public void sepia() {
  }

  @Test
  public void start() {
  }

  @Test
  public void runScript() {
  }

  class MockModel {

  }

  class MockView implements IView {

    private StringBuilder viewLog;

    public MockView(StringBuilder viewLog) {
      this.viewLog = viewLog;
    }

    @Override
    public void print(String message) {
      viewLog.append(message);
    }

    @Override
    public void success() {
      viewLog.append("Operation Successful");
    }
  }
}