package imeprogram.view;

import imeprogram.controller.IFeatures;

/**
 * This interface represents a GUI based view for the IME program.
 */
public interface IGUIView {

  /**
   * Display the given image in the view.
   *
   * @param image the image data to be displayed.
   */
  void displayImage(int[][][] image);

  /**
   * Display an error message in the view.
   *
   * @param errorMessage the error message to be displayed.
   */
  void displayError(String errorMessage);

  /**
   * Adds the given features in the view.
   *
   * @param features the features exposed to the view through the IFeatures interface.
   */
  void addFeatures(IFeatures features);
}
