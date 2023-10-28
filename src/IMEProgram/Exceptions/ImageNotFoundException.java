package IMEProgram.Exceptions;

/**
 * This class represents a Custom Exception thrown when an image is not found in the program.
 */
public class ImageNotFoundException extends RuntimeException {

  /**
   * Constructs an ImageNotFoundException with the specified detail message.
   *
   * @param message the detail message.
   */
  public ImageNotFoundException(String message) {
    super(message);
  }
}
