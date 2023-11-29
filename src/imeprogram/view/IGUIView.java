package imeprogram.view;

import imeprogram.controller.IFeatures;
import imeprogram.model.IReadOnlyImage;

/**
 * This interface represents a GUI based view for the IME program.
 */
public interface IGUIView {

  /**
   * Display the operated image in the view.
   *
   * @param image the image data to be displayed.
   */
  void displayOperatedImage(IReadOnlyImage image);


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
