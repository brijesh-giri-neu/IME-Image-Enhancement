import imeprogram.exceptions.FileFormatException;
import imeprogram.exceptions.ImageNotFoundException;
import imeprogram.exceptions.InvalidImageNameException;
import imeprogram.model.IImage;
import imeprogram.model.ILineGraph;
import imeprogram.model.IModel;
import imeprogram.model.IReadOnlyImage;
import java.io.FileNotFoundException;

class MockModel implements IModel {

  private StringBuilder modelLog;
  private boolean throwFileNotFoundException = false;
  private boolean throwFileFormatException = false;
  private boolean throwInvalidImageNameException = false;
  private boolean throwImageNotFoundException = false;
  private boolean throwIllegalArgumentException = false;
  private IReadOnlyImage outputImage;

  public MockModel(StringBuilder modelLog) {
    this.modelLog = modelLog;
  }

  public void setThrowFileNotFoundException(boolean throwFileNotFoundException) {
    this.throwFileNotFoundException = throwFileNotFoundException;
  }

  public void setThrowFileFormatException(boolean throwFileFormatException) {
    this.throwFileFormatException = throwFileFormatException;
  }

  public void setThrowInvalidImageNameException(boolean throwInvalidImageNameException) {
    this.throwInvalidImageNameException = throwInvalidImageNameException;
  }

  public void setThrowImageNotFoundException(boolean throwImageNotFoundException) {
    this.throwImageNotFoundException = throwImageNotFoundException;
  }

  public void setThrowIllegalArgumentException(boolean throwIllegalArgumentException) {
    this.throwIllegalArgumentException = throwIllegalArgumentException;
  }

  public void setOutputImage(IReadOnlyImage outputImage) {
    this.outputImage = outputImage;
  }

  private void checkExceptions() {
    if (throwFileFormatException) {
      throwFileFormatException = false;
      throw new FileFormatException("");
    }
    if (throwInvalidImageNameException) {
      throwInvalidImageNameException = false;
      throw new InvalidImageNameException("");
    }
    if (throwImageNotFoundException) {
      throwImageNotFoundException = false;
      throw new ImageNotFoundException("");
    }
    if (throwIllegalArgumentException) {
      throwIllegalArgumentException = false;
      throw new IllegalArgumentException("");
    }
  }

  private void checkFileNotFoundException() throws FileNotFoundException {
    if (throwFileNotFoundException) {
      throwFileNotFoundException = false;
      throw new FileNotFoundException("");
    }
  }

  private void logInputs(String[] orderedInputs) {
    for (int i = 0; i < orderedInputs.length; i++) {
      modelLog.append("\n" + orderedInputs[i]);
    }
  }

  @Override
  public void loadImageFromFile(String filePath, String imageName)
      throws FileNotFoundException, FileFormatException, InvalidImageNameException {
    logInputs(new String[]{filePath, imageName});
    checkExceptions();
    checkFileNotFoundException();
  }

  @Override
  public void saveImageToFile(String imageName, String filePath)
      throws ImageNotFoundException, FileNotFoundException, FileFormatException {
    logInputs(new String[]{imageName, filePath});
    checkExceptions();
    checkFileNotFoundException();
  }

  @Override
  public IReadOnlyImage getImageData(String sourceImageName) throws ImageNotFoundException {
    logInputs(new String[]{sourceImageName});
    checkExceptions();
    // Return dummy values. Since our only goal here is to log inputs received.
    return this.outputImage;
  }

  @Override
  public void saveImageDataToMemory(IImage imageData, String imageName)
      throws InvalidImageNameException {
    logInputs(new String[]{imageName});
    checkExceptions();
  }

  @Override
  public void removeImageFromMemory(String imageName) {
    logInputs(new String[]{imageName});
  }

  @Override
  public void redComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void greenComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void blueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void valueComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void lumaComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void intensityComponent(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void horizontalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void verticalFlip(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void brighten(String sourceImageName, String destImageName, int increment)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{String.valueOf(increment), sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void rgbSplit(String sourceImageName, String destImageNameRedComp,
      String destImageNameGreenComp, String destImageNameBlueComp)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageNameRedComp, destImageNameGreenComp,
        destImageNameBlueComp});
    checkExceptions();
  }

  @Override
  public void rgbCombine(String destImageName, String sourceImageNameRedComp,
      String sourceImageNameGreenComp, String sourceImageNameBlueComp)
      throws ImageNotFoundException, IllegalArgumentException, InvalidImageNameException {
    logInputs(new String[]{destImageName, sourceImageNameRedComp, sourceImageNameGreenComp,
        sourceImageNameBlueComp});
    checkExceptions();
  }

  @Override
  public void blur(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void sharpen(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void sepia(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void histogram(String sourceImageName, String destImageName, ILineGraph graph)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void colorCorrect(String sourceImageName, String destImageName)
      throws ImageNotFoundException, InvalidImageNameException {
    logInputs(new String[]{sourceImageName, destImageName});
    checkExceptions();
  }

  @Override
  public void adjustLevels(String sourceImageName, String destImageName, int black, int mid,
      int white)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    logInputs(
        new String[]{sourceImageName, destImageName, String.valueOf(black), String.valueOf(mid),
            String.valueOf(white)});
    checkExceptions();
  }

  @Override
  public void splitView(String sourceImageName, String destImageName, int splitRatio)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    logInputs(new String[]{sourceImageName, destImageName, String.valueOf(splitRatio)});
    checkExceptions();
  }

  @Override
  public void compress(String sourceImageName, String destImageName, int compressRatio)
      throws ImageNotFoundException, InvalidImageNameException, IllegalArgumentException {
    logInputs(new String[]{sourceImageName, destImageName, String.valueOf(compressRatio)});
    checkExceptions();
  }
}
