package IMEProgram.Exceptions;

/**
 * This exception is thrown when an invalid file path is encountered.
 */
public class InvalidFilePathException extends RuntimeException {

  /**
   * Constructs a new InvalidFilePathException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage()
   *                method)
   */
  public InvalidFilePathException(String message) {
    super(message);
  }
}
