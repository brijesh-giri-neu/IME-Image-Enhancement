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
    controller.start();
  }
}
