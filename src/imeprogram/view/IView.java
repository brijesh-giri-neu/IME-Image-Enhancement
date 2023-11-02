package imeprogram.view;

/**
 * A text-based view for the IME program.
 */
public interface IView {

  /**
   * Display message to the user.
   */
  void print(String message);

  /**
   * Display success message to user.
   */
  void success();
}
