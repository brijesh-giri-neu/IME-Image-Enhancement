package imeprogram.model;

import imeprogram.exceptions.FileFormatException;
import java.io.IOException;

/**
 * A helper to perform File IO operations on images.
 */
interface IImageFileIO {

  /**
   * Loads an image from a file.
   *
   * @param filePath The path to the image file.
   * @return An IImage object representing the loaded image.
   */
  IImage loadFromFile(String filePath) throws IOException, FileFormatException;

  /**
   * Saves an image to a file.
   *
   * @param filePath    The path where the image will be saved.
   * @param imageToSave The IImage object to be saved.
   */
  void saveToFile(String filePath, IImage imageToSave) throws IOException, FileFormatException;
}
