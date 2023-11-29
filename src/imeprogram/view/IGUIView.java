package imeprogram.view;

/**
 * This interface represents a GUI based view for the IME program.
 */
public interface IGUIView {

  /**
   * Display the given image in the view.
   * @param image the image data to be displayed.
   */
  void displayImage(int[][][] image);

  /**
   * Display an error message in the view.
   * @param errorMessage the error message to be displayed.
   */
  void displayError(String errorMessage);
}
