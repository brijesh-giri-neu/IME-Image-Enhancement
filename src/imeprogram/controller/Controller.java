package imeprogram.controller;

import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.model.IModel;
import imeprogram.view.IView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class represents the Controller of the IMEProgram.
 */
public class Controller implements IController {

  // TO DO - CATCH ONLY CUSTOM EXCEPTIONS IN ACTIONS
  private final IModel model;
  private final IView view;
  private final InputStream in;

  /**
   * Instantiate the controller object.
   *
   * @param model the input model
   * @param view  the input view
   * @param in    the source of input commands
   */
  public Controller(IModel model, IView view, InputStream in) {
    this.model = model;
    this.view = view;
    this.in = in;
  }

  @Override
  public void loadImage(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    // Replace starting and ending quote character. Both single and double.
    String filePath = args[0].replaceAll("^['\"]+|['\"]+$", "");
    String imageName = args[1];
    try {
      model.loadImageFromFile(filePath, imageName);
      view.success();
    } catch (FileNotFoundException e) {
      view.print("Error: Cannot load file. Please check path");
    } catch (FileFormatException e) {
      view.print("Error: Cannot load file. Invalid file");
    } catch (InvalidImageNameException e) {
      view.print(
          String.format("Error: %s cannot be used as an alias to refer to an image", imageName));
    }
  }

  @Override
  public void saveImage(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    // Replace starting and ending quote character. Both single and double.
    String filePath = args[0].replaceAll("^['\"]+|['\"]+$", "");
    String imageName = args[1];
    try {
      model.saveImageToFile(imageName, filePath);
      view.success();
    } catch (FileNotFoundException e) {
      view.print("Error: Cannot save file. Please check provided path: " + filePath);
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, imageName));
    } catch (FileFormatException e) {
      view.print("Error: Cannot save file. Unsupported file extension");
    }
  }

  @Override
  public void redComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.redComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void greenComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.greenComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void blueComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.blueComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void valueComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.valueComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void lumaComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.lumaComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void intensityComponent(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.intensityComponent(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void horizontalFlip(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.horizontalFlip(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void verticalFlip(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.verticalFlip(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void brighten(String[] args) {
    if (!isValidNumberOfArgs(args, 3)) {
      return;
    }
    String sourceImage = args[1];
    String destImage = args[2];
    try {
      int increment = Integer.parseInt(args[0]);
      model.brighten(increment, sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void rgbSplit(String[] args) {
    if (!isValidNumberOfArgs(args, 4)) {
      return;
    }
    String sourceImage = args[0];
    String destImageRed = args[1];
    String destImageGreen = args[2];
    String destImageBlue = args[3];
    try {
      model.rgbSplit(sourceImage, destImageRed, destImageGreen, destImageBlue);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print("Error: One of the mentioned destImage alias is invalid");
    }
  }

  @Override
  public void rgbCombine(String[] args) {
    if (!isValidNumberOfArgs(args, 4)) {
      return;
    }
    String destImage = args[0];
    String sourceImageRed = args[1];
    String sourceImageGreen = args[2];
    String sourceImageBlue = args[3];
    try {
      model.rgbCombine(destImage, sourceImageRed, sourceImageGreen, sourceImageBlue);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print("Error: Mentioned image alias does not exist. Please check the name");
    } catch (IllegalArgumentException e) {
      view.print("Error: Given images have different dimensions");
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void blur(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.blur(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void sharpen(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.sharpen(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void sepia(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String sourceImage = args[0];
    String destImage = args[1];
    try {
      model.sepia(sourceImage, destImage);
      view.success();
    } catch (ImageNotFoundException e) {
      view.print(String.format(MessageHelper.IMAGE_NOT_FOUND_EXCEPTION_MSG, sourceImage));
    } catch (InvalidImageNameException e) {
      view.print(String.format(MessageHelper.IMAGE_NAME_EXCEPTION_MSG, destImage));
    }
  }

  @Override
  public void start() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(this.in));
    String command;
    try {
      while ((command = reader.readLine()) != null) {
        // Remove Whitespaces at start and end. And convert to lowercase.
        command = command.trim().toLowerCase();

        // Ignore comments (lines starting with #)
        if (command.startsWith("#") || command.isBlank()) {
          continue;
        }

        // Quit the program
        if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
          break;
        }

        // Process the given command
        executeCommand(command);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void runScript(String[] args) {
    if (!isValidNumberOfArgs(args, 1)) {
      return;
    }

    // Replace starting and ending quote character. Both single and double.
    String scriptFilePath = args[0].replaceAll("^['\"]+|['\"]+$", "");

    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
      String command;
      while ((command = reader.readLine()) != null) {
        // Remove Whitespaces at start and end. And convert to lowercase.
        command = command.trim().toLowerCase();

        // Ignore comments (lines starting with #)
        if (command.startsWith("#") || command.isEmpty()) {
          continue;
        }

        // Quit the program
        if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
          break;
        }

        // Quit if run script called within a script
        if (command.split("\\s+")[0].equalsIgnoreCase("run")) {
          view.print("Cannot execute a script from inside a script");
        }

        // Process the given command
        executeCommand(command);
      }
    } catch (IOException e) {
      view.print("Error reading or executing the script: " + e.getMessage());
    }
  }

  private void executeCommand(String command) {
    // At this point program has at least 1 token.

    // If command has an even number of quotes, split on whitespace while ignoring quotes.
    // Otherwise, split on only whitespaces.
    String[] tokens =
        isEvenNumberOfQuotes(command) ? command.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")
            : command.split("\\s+");

    // Need at least 2 tokens
    if (tokens.length < 2) {
      view.print("Error: Please enter correct number of arguments. At least 2 required.");
      return;
    }

    String operation = tokens[0].trim();
    // Remove operation from tokens
    tokens = getArrayBeginningFrom(tokens, 1);

    switch (operation.toLowerCase()) {
      case "load":
        loadImage(tokens);
        break;
      case "save":
        saveImage(tokens);
        break;
      case "red-component":
        redComponent(tokens);
        break;
      case "green-component":
        greenComponent(tokens);
        break;
      case "blue-component":
        blueComponent(tokens);
        break;
      case "value-component":
        valueComponent(tokens);
        break;
      case "luma-component":
        lumaComponent(tokens);
        break;
      case "intensity-component":
        intensityComponent(tokens);
        break;
      case "vertical-flip":
        verticalFlip(tokens);
        break;
      case "horizontal-flip":
        horizontalFlip(tokens);
        break;
      case "brighten":
        brighten(tokens);
        break;
      case "rgb-split":
        rgbSplit(tokens);
        break;
      case "rgb-combine":
        rgbCombine(tokens);
        break;
      case "blur":
        blur(tokens);
        break;
      case "sharpen":
        sharpen(tokens);
        break;
      case "sepia":
        sepia(tokens);
        break;
      case "run":
        runScript(tokens);
        break;
      default:
        view.print("Error: Invalid command. Please try again.");
    }
  }

  // Returns a new array beginning from given index.
  // Trims the string elements of the array for whitespaces.
  private String[] getArrayBeginningFrom(String[] array, int index) {
    String[] result = new String[array.length - index];
    for (int i = index, j = 0; i < array.length; i++, j++) {
      // Trim the tokens
      result[j] = array[i].trim();
    }
    return result;
  }

  /**
   * Checks if the number of provided args is valid. If not, sends an error message to the view.
   *
   * @param args          the provided args
   * @param requiredCount the number of args required
   * @return True if number of provided args is valid, false otherwise
   */
  private boolean isValidNumberOfArgs(String[] args, int requiredCount) {
    if (args.length != requiredCount) {
      view.print("Invalid number of arguments");
      return false;
    }
    return true;
  }

  private boolean isEvenNumberOfQuotes(String input) {
    int quoteCount = 0;
    for (char c : input.toCharArray()) {
      if (c == '"') {
        quoteCount++;
      }
    }

    return quoteCount % 2 == 0 ? true : false;
  }

  /**
   * A helper class containing messages for the view.
   */
  public static class MessageHelper {

    // Not used in 2 cases - load, rgbSplit
    public static final String IMAGE_NAME_EXCEPTION_MSG =
        "Error: Mentioned destImage alias is invalid: %s";
    //Not used in 1 case - rgbCombine
    public static final String IMAGE_NOT_FOUND_EXCEPTION_MSG =
        "Error: Mentioned image alias does not exist: %s";
  }
}
