package IMEProgram.View;

import java.io.PrintStream;

/**
 * A text-based view for the IME program.
 */
public class View implements IView {

  private final PrintStream outPipe;

  public View(PrintStream outPipe) {
    this.outPipe = outPipe;
  }

  @Override
  public void print(String message) {
    outPipe.print(message);
  }
}
