package imeprogram.model;

/**
 * A factory class that creates an IImageFileIO object.
 */
public interface IImageFileIOFactory {

  /**
   * Creates an IImageFileIO object for a given file extension.
   *
   * @param filePath The path of the image file, used to get the extension.
   * @return An IImageFileIO object for handling file I/O operations on images.
   * @throws IllegalArgumentException If the provided filePath is invalid or unsupported.
   */
  IImageFileIO getImageParser(String filePath) throws IllegalArgumentException;
}
