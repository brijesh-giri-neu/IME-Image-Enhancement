package imeprogram.controller;

/**
 * A helper class containing messages for the view.
 */
public class MessageHelper {

  // Not used in 2 cases - load, rgbSplit
  public static final String IMAGE_NAME_EXCEPTION_MSG =
      "Error: Mentioned destImage alias is invalid: %s";
  //Not used in 1 case - rgbCombine
  public static final String IMAGE_NOT_FOUND_EXCEPTION_MSG =
      "Error: Mentioned image alias does not exist: %s";
  public static final String NUMBER_FORMAT_EXCEPTION_MSG =
      "Error: Provided input value is not a valid number";
  public static final String LOAD_FILE_NOT_FOUND_EXCEPTION_MSG =
      "Error: Cannot load file. Please check path";
  public static final String LOAD_FILE_FORMAT_EXCEPTION_MSG =
      "Error: Cannot load file. Invalid file";
  public static final String SAVE_FILE_NOT_FOUND_EXCEPTION_MSG =
      "Error: Cannot save file. Please check provided path: %s";
  public static final String SAVE_FILE_FORMAT_EXCEPTION_MSG =
      "Error: Cannot save file. Unsupported file extension";
}