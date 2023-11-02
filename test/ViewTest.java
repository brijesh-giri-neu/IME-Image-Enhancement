import static org.junit.Assert.assertEquals;

import imeprogram.view.IView;
import imeprogram.view.View;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

public class ViewTest {

  private IView view;

  // Pipe which will output data to the test class.
  private ByteArrayOutputStream bytes;

  @Before
  public void setUp() {
    bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    view = new View(out);
  }

  @Test
  public void print() {
    String expectedMessage = "Test message";
    view.print(expectedMessage);
    String actualReceived = new String(bytes.toByteArray());

    assertEquals("\n" + expectedMessage, actualReceived);
  }

  @Test
  public void success() {
    view.success();
    String actualReceived = new String(bytes.toByteArray());

    assertEquals("\n Operation successful", actualReceived);
  }
}