package imeprogram.exceptions;

/**
 * This exception is thrown when an unsupported File Format is encountered by the application.
 */
public class FileFormatException extends RuntimeException {

  /**
   * Constructs a new FileFormatException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the getMessage()
   *                method)
   */
  public FileFormatException(String message) {
    super(message);
  }
}
