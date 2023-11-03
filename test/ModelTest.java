import static org.junit.Assert.fail;

import imeprogram.model.Model;
import org.junit.Test;

/**
 * Tests the working of the LoadImageFromFile method.
 */
public class ModelTest {

  @Test
  public void testLoadImageFromFile_Working() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the LoadImageFromFile method for Invalid Name.
   */
  @Test(expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "Invalid&&Name");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the LoadImageFromFile method for InvalidFilePath.
   */
  @Test(expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFilePath() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/InvalidFilePath/bro.png", "SampleImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the LoadImageFromFile method for InvalidFileName.
   */
  @Test(expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/InvalidFileName.png", "SampleImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the LoadImageFromFile method for InvalidFileExtension.
   */
  @Test(expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileExtension() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.InvalidFileExtension", "SampleImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the LoadImageFromFile method for NoExtension.
   */
  @Test(expected = AssertionError.class)
  public void testLoadImageFromFile_NoExtension() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro", "SampleImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the working of the SaveImageToFile method.
   */
  @Test
  public void testSaveImageToFile_Working() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.saveImageToFile("SampleImage", "test/unitResults/broresult.png");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the SaveImageToFile method for Invalid Name.
   */
  @Test(expected = AssertionError.class)
  public void testSaveImageToFile_InvalidName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.saveImageToFile("Invalid&&Name", "test/unitResults/broresult.png");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the SaveImageToFile method for InvalidFilePath.
   */
  @Test(expected = AssertionError.class)
  public void testSaveImageToFile_InvalidFilePath() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.saveImageToFile("SampleImage", "test/InvalidFilePath/bro.png");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the SaveImageToFile method for InvalidFileExtension.
   */
  @Test(expected = AssertionError.class)
  public void testSaveImageToFile_InvalidFileExtension() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.saveImageToFile("SampleImage", "test/unitResults/bro.InvalidFileExtension");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the SaveImageToFile method for NoExtension.
   */
  @Test(expected = AssertionError.class)
  public void testSaveImageToFile_NoExtension() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.saveImageToFile("SampleImage", "test/unitResults/broresult");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the redComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testRedComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.redComponent("Sample&&Image", "RedImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the redComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testRedComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.redComponent("SampleImage", "Red&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the greenComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testGreenComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.greenComponent("Sample&&Image", "GreenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the greenComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testGreenComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.greenComponent("SampleImage", "Green&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the blueComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testBlueComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.blueComponent("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the blueComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testBlueComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.blueComponent("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the valueComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testValueComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.valueComponent("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the valueComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testValueComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.valueComponent("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the lumaComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testLumaComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.lumaComponent("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the lumaComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testLumaComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.lumaComponent("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the intensityComponent method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testIntensityComponent_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.intensityComponent("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the intensityComponent method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testIntensityComponent_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.intensityComponent("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the horizontalFlip method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testHorizontalFlip_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.horizontalFlip("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the horizontalFlip method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testHorizontalFlip_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.horizontalFlip("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the verticalFlip method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testVerticalFlip_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.verticalFlip("Sample&&Image", "BlueImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the verticalFlip method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testVerticalFlip_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.verticalFlip("SampleImage", "Blue&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the brighten method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testBrighten_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.brighten(50, "Sample&&Image", "BrightenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the brighten method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testBrighten_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.brighten(50, "SampleImage", "Brighten&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the rgbSplit method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testRgbSplit_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.rgbSplit("Sample&&Image", "RedImage", "BlueImage", "GreenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the rgbSplit method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testRgbSplit_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.rgbSplit("SampleImage", "Red&&Image", "Blue&&Image", "Green&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the rgbCombine method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testrgbCombine_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.rgbSplit("SampleImage", "RedImage", "BlueImage", "GreenImage");
      model.rgbCombine("Sample&&Image", "RedImage", "BlueImage", "GreenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the rgbCombine method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testrgbCombine_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.rgbSplit("SampleImage", "RedImage", "BlueImage", "GreenImage");
      model.rgbCombine("SampleImage", "Red&&Image", "Blue&&Image", "Green&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the blur method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testBlur_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.blur("Sample&&Image", "BrightenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the blur method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testBlur_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.blur("SampleImage", "Brighten&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }


  /**
   * Tests the sepia method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testSepia_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.sepia("Sample&&Image", "BrightenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the sepia method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testSepia_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.sepia("SampleImage", "Brighten&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the sharpen method for Invalid SourceName.
   */
  @Test(expected = AssertionError.class)
  public void testSharpen_InvalidSourceName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.sharpen("Sample&&Image", "BrightenImage");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }

  /**
   * Tests the sharpen method for Invalid destName.
   */
  @Test(expected = AssertionError.class)
  public void testSharpen_InvalidDestName() {

    Model model = new Model();

    try {
      model.loadImageFromFile("test/unitImages/bro.png", "SampleImage");
      model.sharpen("SampleImage", "Brighten&&Image");
    } catch (Exception e) {
      fail("Exception not expected for a valid image file.");
    }

  }


}