package imeprogram;

import imeprogram.controller.Controller;
import imeprogram.controller.GUIController;
import imeprogram.controller.IController;
import imeprogram.controller.IFeatures;
import imeprogram.fileparser.ImageFileIOFactory;
import imeprogram.model.IModel;
import imeprogram.model.Model;
import imeprogram.view.GUIView;
import imeprogram.view.IGUIView;
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
    if (args.length == 0) {
      openGUI();
    } else if (args.length == 1 && args[0].equals("-text")) {
      openCLI();
    } else if (args.length > 0 && args[0].equals("-file")) {
      openScriptMode(sliceArray(args, 1));
    } else {
      System.out.println("Invalid command-line arguments.");
      System.out.println("Usage:");
      System.out.println("java -jar Program.jar -file path-of-script-file");
      System.out.println("java -jar Program.jar -text");
      System.out.println("java -jar Program.jar");
      // Exit the program with non-zero status indicating an error
      System.exit(1);
    }
  }

  private static void openGUI() {
    // Invoke model with a factory of image file parsers
    IModel model = new Model(new ImageFileIOFactory());
    // Create GUI window
    IGUIView guiView = new GUIView();
    // Create controller. This will initiate the program
    IFeatures guiController = new GUIController(model, guiView);
  }

  private static void openScriptMode(String[] arg) {
    // Instantiate the View with System.out. Will replace this in testing.
    IView view = new View(System.out);
    IModel model = new Model(new ImageFileIOFactory());
    // Instantiate the Controller with System.out. Will replace this in testing.
    // Instantiate Controller, pass model and view, hardcode pipe as System.in
    IController controller = new Controller(model, view, System.in);
    controller.runScript(arg);
  }

  private static void openCLI() {
    // Instantiate the View with System.out. Will replace this in testing.
    IView view = new View(System.out);
    IModel model = new Model(new ImageFileIOFactory());
    // Instantiate the Controller with System.out. Will replace this in testing.
    // Instantiate Controller, pass model and view, hardcode pipe as System.in
    IController controller = new Controller(model, view, System.in);
    controller.start();
  }

  // Creates a new array with given start index.
  private static String[] sliceArray(String[] inputs, int newStart) {
    String[] result = new String[inputs.length - newStart];
    for (int i = newStart, j = 0; i < inputs.length; i++, j++) {
      result[j] = inputs[i];
    }
    return result;
  }
}
