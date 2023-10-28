package IMEProgram;

import IMEProgram.Controller.Controller;
import IMEProgram.Controller.IController;
import IMEProgram.Model.IModel;
import IMEProgram.Model.Model;
import IMEProgram.View.IView;
import IMEProgram.View.View;

/**
 * This class is the starting point of our IME application.
 */
public class Main {

  public static void main(String[] args) {
    // Instantiate the View with System.out. Will replace this in testing.
    IView view = new View(System.out);
    IModel model = new Model();
    // Instantiate the Controller with System.out. Will replace this in testing.
    // Instantiate Controller, pass model and view, hardcode pipe as System.in
    IController controller = new Controller(model, view, System.in);
    controller.start();
  }
}
