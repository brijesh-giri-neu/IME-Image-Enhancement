package IMEProgram;

import IMEProgram.View.IView;
import IMEProgram.View.View;

/**
 * This class is the starting point of our IME application.
 */
public class Main {

  public static void main(String[] args) {
    // Instantiate the View, Model, and Controller.
    IView view = new View(System.out);
    // Instantiate View, hardcode pipe as System.out
    // Instantiate Model, idk
    // Instantiate Controller, pass model and view, hardcode pipe as System.in
  }
}
