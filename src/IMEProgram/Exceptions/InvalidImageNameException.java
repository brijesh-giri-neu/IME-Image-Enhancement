package IMEProgram.Exceptions;

/**
 * This class represents a custom exception thrown when an invalid name is encountered, which cannot
 * be assigned to an Image in the application.
 */
public class InvalidImageNameException extends RuntimeException {

  /**
   * Constructs an InvalidImageNameException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage()
   *                method)
   */
  public InvalidImageNameException(String message) {
    super(message);
  }
}
