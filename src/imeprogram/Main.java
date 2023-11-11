package imeprogram;

import imeprogram.controller.Controller;
import imeprogram.controller.IController;
import imeprogram.model.IModel;
import imeprogram.model.ImageFileIOFactory;
import imeprogram.model.Model;
import imeprogram.view.IView;
import imeprogram.view.View;

/**
 * This class is the starting point of our IME application.
 */
public class Main {

  /**
   * Entry point of our program.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    // Instantiate the View with System.out. Will replace this in testing.
    IView view = new View(System.out);
    IModel model = new Model(new ImageFileIOFactory());
    // Instantiate the Controller with System.out. Will replace this in testing.
    // Instantiate Controller, pass model and view, hardcode pipe as System.in
    IController controller = new Controller(model, view, System.in);

    if (args.length > 0 && args[0] != null && args[0].equalsIgnoreCase("-file")) {
      // Run the provided script and exit
      controller.runScript(sliceArray(args, 1));
    } else {
      // Run interactive mode
      controller.start();
    }
  }

  private static String[] sliceArray(String[] inputs, int newStart) {
    String[] result = new String[inputs.length - newStart];
    for (int i = newStart, j = 0; i < inputs.length; i++, j++) {
      result[j] = inputs[i];
    }
    return result;
  }
}
