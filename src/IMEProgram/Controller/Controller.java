package IMEProgram.Controller;

import IMEProgram.Exceptions.ImageNotFoundException;
import IMEProgram.Exceptions.InvalidFilePathException;
import IMEProgram.Model.IModel;
import IMEProgram.View.IView;
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
  private IModel model;
  private IView view;
  private InputStream in;

  //  private static final String VALID_ALIAS_NAME_CHARS_REGEX =
  //      "[a-zA-Z0-9._-]+";


  public Controller(IModel model, IView view, InputStream in) {
    this.model = model;
    this.view = view;
    this.in = in;
  }

  // Handles spaces in filepath by asking professor to allow quotes in filepath beginning and end
  @Override
  public void loadImage(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String filePath = args[0];
    String imageName = args[1];
    try {
      model.loadImage(filePath, imageName);
    } catch (FileNotFoundException e) {
      view.print("Error: Cannot load file. Please check path");
    }
  }

  @Override
  public void saveImage(String[] args) {
    if (!isValidNumberOfArgs(args, 2)) {
      return;
    }
    String filePath = args[0];
    String imageName = args[1];
    try {
      model.saveImage(filePath, imageName);
    } catch (InvalidFilePathException e) {
      view.print("Error: Cannot save file. Please check provided path: " + filePath);
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + imageName + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print("Error: Mentioned image alias does not exist. Please check the name");
    } catch (IllegalArgumentException e) {
      view.print("Error: Given images have different dimensions");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
    } catch (ImageNotFoundException e) {
      view.print(
          "Error: Mentioned image alias does not exist: " + sourceImage
              + " . Please check the name");
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
        if (command.equals("exit") || command.equals("quit")) {
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

    String scriptFilePath = args[0];

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
        if (command.equals("exit") || command.equals("quit")) {
          break;
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
    String[] tokens = command.split("\\s+");
    // Need at least 2 tokens
    if (tokens.length < 2) {
      view.print("Error: Please enter correct number of arguments.");
      return;
    }

    String operation = tokens[0].trim();
    // Remove operation from tokens
    tokens = getArrayBeginningFrom(tokens, 1);

    switch (operation) {
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

  // Returns a new array beginning from given index. Trims the string elements of the array for whitespaces.
  private String[] getArrayBeginningFrom(String[] array, int index) {
    String[] result = new String[array.length - index];
    for (int i = index, j = 0; i < array.length; i++, j++) {
      // Trim the tokens
      result[j] = array[i].trim();
    }
    return result;
  }

  private boolean isValidNumberOfArgs(String[] args, int requiredCount) {
    if (args.length != requiredCount) {
      view.print("Please provide adequate number of arguments");
      return false;
    }
    return true;
  }

  //  private boolean isValidAliasName(String input) {
  //    return input.matches(VALID_ALIAS_NAME_CHARS_REGEX);
  //  }
}
