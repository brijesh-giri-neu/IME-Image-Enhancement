package IMEProgram.Controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import IMEProgram.Model.IImage;
import IMEProgram.Model.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.IIOImage;
import org.junit.Before;
import org.junit.Test;

/**
 * This Class tests the Image class.
 */
public class ImageTest {

  private Image testImgPPM;
  private Image testImgPNG;
  private Image testImgJPG;

  /**
   * Tests the working of the public constructor
   * of the Image class.
   */
  @Test
  public void testImageConstructor() {
    int height = 3;
    int width = 3;

    try {
      Image testImg = new Image(height, width);

      assertNotNull(testImg);

    } catch (Exception e) {
      fail("Exception thrown while creating the image: " + e.getMessage());
    }
  }

  /**
   * Tests the working of the LoadImageFromFile
   * method using PPM Image.
   */
  @Test
  public void testLoadImageFromFile_Working_PPM() {
    // Provide the file path of a test image
    String testImagePath = "res/ValueTest/bro.ppm";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File path for PPM Image.
   */
  @Test (expected = FileNotFoundException.class)
  public void testLoadImageFromFile_InvalidFilePath_PPM() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidPath/Koala.ppm";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail(" Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File name for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileName_PPM() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidFileName.ppm";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File Extension for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileExtension_PPM() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using No File Extension for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_NoFileExtension_PPM() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Height for PPM Image.
   */
  @Test
  public void testInvalidHeight_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);

      int height = testImgPPM.getHeight();
      int[][][] rgbValues = testImgPPM.getRgbValues();

      assertTrue("Image height should be non-zero", height > 0);
      assertEquals(testImgPPM.getHeight(), rgbValues[0].length);

    } catch (Exception e) {
      fail("Invalid Height: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Width for PPM Image.
   */
  @Test
  public void testInvalidWidth_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);

      int width = testImgPPM.getWidth();
      int[][][] rgbValues = testImgPPM.getRgbValues();

      assertTrue("Image width should be non-zero", width > 0);
      assertEquals(testImgPPM.getWidth(), rgbValues.length);

    } catch (Exception e) {
      fail("Invalid Width " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid rgbValues array for PPM Image.
   */
  @Test
  public void testInvalidRGBValues_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);

      int[][][] rgbValues = testImgPPM.getRgbValues();

      assertNotNull(rgbValues);

    } catch (Exception e) {
      fail("RGBValues shouldn't be null" + e.getMessage());
    }
  }

  /**
   * Tests the getRedComponent method
   * for PPM Image.
   */
  @Test
  public void test_getRedComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 0}, {0, 0, 0}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getRedComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getGreenComponent method
   * for PPM Image.
   */
  @Test
  public void test_getGreenComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 255, 0}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getGreenComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getBlueComponent method
   * for PPM Image.
   */
  @Test
  public void test_getBlueComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getBlueComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueComponent method
   * for PPM Image.
   */
  @Test
  public void test_getValueComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{255, 255, 255}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {255, 255, 255}, {255, 255, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getValueComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getIntensityComponent method
   * for PPM Image.
   */
  @Test
  public void test_getIntensityComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
        {{170, 170, 170}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {170, 170, 170}, {170, 170, 170}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getIntensityComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getLumaComponent method
   * for PPM Image.
   */
  @Test
  public void test_getLumaComponent_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
        {{237, 237, 237}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {73, 73, 73}, {201, 201, 201}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.getLumaComponent();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipHorizontal method
   * for PPM Image.
   */
  @Test
  public void test_flipHorizontal_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
        {{0, 0, 0}, {255, 255, 255}, {255, 255, 0}},
        {{0, 255, 255}, {255, 0, 255}, {0, 0, 0}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.flipHorizontal();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipVertical method
   * for PPM Image.
   */
  @Test
  public void test_flipVertical_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{0, 0, 0}, {255, 0, 255}, {0, 255, 255}},
        {{255, 255, 0}, {255, 255, 255}, {0, 0, 0}},
        {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.flipVertical();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by brightening for PPM Image.
   */
  @Test
  public void test_brighten_bright_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{255, 50, 50}, {50, 255, 50}, {50, 50, 255}},
        {{255, 255, 50}, {255, 255, 255}, {50, 50, 50}},
        {{50, 50, 50}, {255, 50, 255}, {50, 255, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.brighten(50);

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by darkening for PPM Image.
   */
  @Test
  public void test_brighten_dark_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{205, 0, 0}, {0, 205, 0}, {0, 0, 205}},
        {{205, 205, 0}, {205, 205, 205}, {0, 0, 0}},
        {{0, 0, 0}, {205, 0, 205}, {0, 205, 205}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.brighten(-50);

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the splitRGB method
   * for PPM Image.
   */
  @Test
  public void test_splitRGB_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] rExpectedValues = {
        {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        {{255, 0, 0}, {255, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 0}, {0, 0, 0}}
    };

    int[][][] gExpectedValues = {
        {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 255, 0}}
    };

    int[][][] bExpectedValues = {
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage[] testResultImgPPM = testImgPPM.splitRGB();

      int[][][] rValues = testResultImgPPM[0].getRgbValues();
      int[][][] gValues = testResultImgPPM[1].getRgbValues();
      int[][][] bValues = testResultImgPPM[2].getRgbValues();

      assertArrayEquals(rExpectedValues, rValues);
      assertArrayEquals(gExpectedValues, gValues);
      assertArrayEquals(bExpectedValues, bValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for PPM Image.
   */
  @Test
  public void test_combineRGB_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    int[][][] expectedValues = {
        {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
        {{255, 255, 0}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 255}, {0, 255, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage[] testSplitImgPPM = testImgPPM.splitRGB();
      IImage rImage = testSplitImgPPM[0];
      IImage gImage = testSplitImgPPM[1];
      IImage bImage = testSplitImgPPM[2];
      testImgPPM.combineRGB(rImage, gImage, bImage);

      int[][][] rgbValues = testImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);


    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the gaussianBlur method
   * for PPM Image.
   */
  @Test
  public void test_gaussianBlur_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{112, 80, 16}, {80, 112, 64}, {16, 48, 80}},
        {{143, 112, 48}, {143, 143, 128}, {48, 80, 112}},
        {{80, 48, 48}, {112, 80, 128}, {48, 80, 112}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.gaussianBlur();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the sharpen method
   * for PPM Image.
   */
  @Test
  public void test_sharpen_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
        {{255, 255, 0}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 255}, {0, 255, 255}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.sharpen();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToGrayscale method
   * for PPM Image.
   */
  @Test
  public void test_convertToGrayscale_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
        {{237, 237, 237}, {255, 255, 255}, {0, 0, 0}},
        {{0, 0, 0}, {73, 73, 73}, {201, 201, 201}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.convertToGrayscale();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToSepia method
   * for PPM Image.
   */
  @Test
  public void test_convertToSepia_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int[][][] expectedValues = {
        {{100, 89, 69}, {196, 175, 136}, {48, 43, 33}},
        {{255, 255, 206}, {255, 255, 239}, {0, 0, 0}},
        {{0, 0, 0}, {148, 132, 103}, {244, 218, 170}}
    };

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      IImage testResultImgPPM = testImgPPM.convertToSepia();

      int[][][] rgbValues = testResultImgPPM.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * for PPM Image.
   */
  @Test
  public void test_getValueAtPixel_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    int expectedValues = 255;

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      int testResultPPM = testImgPPM.getValueAtPixel(0,0, 0);

      assertEquals(expectedValues, testResultPPM);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PPM Image to PPM Image.
   */
  @Test
  public void test_saveToFile_PPM_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "result.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PPM Image to PNG Image.
   */
  @Test
  public void test_saveToFile_PPM_PNG() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "result.png";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PPM Image to JPG Image.
   */
  @Test
  public void test_saveToFile_PPM_JPG() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "result.jpg";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Out of Bounds position for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_OutOfBoundsPosition_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      int testResultPPM = testImgPPM.getValueAtPixel(5,5, 5);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Negative position for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_NegativePosition_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      int testResultPPM = testImgPPM.getValueAtPixel(-3,-5, -1);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for InvalidFilePath in PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFilePath_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "res/KoalaTest/TestInvalidPath/Koala.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * InvalidFileExtension in PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFileExtension_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for NoFileExtension in PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_NoFileExtension_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";
    String outputFilename = "res/KoalaTest/Koala";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      testImgPPM.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for inequality of size for PPM Image.
   */
  @Test (expected = AssertionError.class)
  public void test_combineRGB_InequalSize_PPM() {
    String testFilePathPPM = "res/ValueTest/bro.ppm";

    try {
      Image testImgPPM = Image.loadImageFromFile(testFilePathPPM);
      Image rImage = new Image(4, 3);
      Image gImage = new Image(3, 4);
      Image bImage = new Image(5, 5);
      testImgPPM.combineRGB(rImage, gImage, bImage);

      assertNotNull(testImgPPM);

    } catch (Exception e) {
      fail("Error Encountered: " + e.getMessage());
    }
  }

  /**
   * Tests the working of the LoadImageFromFile
   * method using PNG Image.
   */
  @Test
  public void testLoadImageFromFile_Working_PNG() {
    // Provide the file path of a test image
    String testImagePath = "res/ValueTest/bro.png";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File path for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFilePath_PNG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidPath/Koala.png";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail(" Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File name for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileName_PNG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidFileName.png";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File Extension for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileExtension_PNG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using No File Extension for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_NoFileExtension_PNG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Height for PNG Image.
   */
  @Test
  public void testInvalidHeight_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);

      int height = testImgPNG.getHeight();
      int[][][] rgbValues = testImgPNG.getRgbValues();

      assertTrue("Image height should be non-zero", height > 0);
      assertEquals(testImgPNG.getHeight(), rgbValues[0].length);

    } catch (Exception e) {
      fail("Invalid Height: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Width for PNG Image.
   */
  @Test
  public void testInvalidWidth_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);

      int width = testImgPNG.getWidth();
      int[][][] rgbValues = testImgPNG.getRgbValues();

      assertTrue("Image width should be non-zero", width > 0);
      assertEquals(testImgPNG.getWidth(), rgbValues.length);

    } catch (Exception e) {
      fail("Invalid Width " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid rgbValues array for PNG Image.
   */
  @Test
  public void testInvalidRGBValues_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);

      int[][][] rgbValues = testImgPNG.getRgbValues();

      assertNotNull(rgbValues);

    } catch (Exception e) {
      fail("RGBValues shouldn't be null" + e.getMessage());
    }
  }

  /**
   * Tests the getRedComponent method
   * for PNG Image.
   */
  @Test
  public void test_getRedComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{255, 0, 0}, {255, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getRedComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getGreenComponent method
   * for PNG Image.
   */
  @Test
  public void test_getGreenComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 255, 0}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getGreenComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getBlueComponent method
   * for PNG Image.
   */
  @Test
  public void test_getBlueComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 255}},
        {{0, 0, 255}, {0, 0, 0}, {0, 0, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getBlueComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueComponent method
   * for PNG Image.
   */
  @Test
  public void test_getValueComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{255, 255, 255}, {255, 255, 255}, {0, 0, 0}},
        {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
        {{255, 255, 255}, {0, 0, 0}, {255, 255, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getValueComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getIntensityComponent method
   * for PNG Image.
   */
  @Test
  public void test_getIntensityComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{85, 85, 85}, {170, 170, 170}, {0, 0, 0}},
        {{85, 85, 85}, {255, 255, 255}, {170, 170, 170}},
        {{85, 85, 85}, {0, 0, 0}, {170, 170, 170}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getIntensityComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getLumaComponent method
   * for PNG Image.
   */
  @Test
  public void test_getLumaComponent_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{54, 54, 54}, {237, 237, 237}, {0, 0, 0}},
        {{182, 182, 182}, {255, 255, 255}, {73, 73, 73}},
        {{18, 18, 18}, {0, 0, 0}, {201, 201, 201}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.getLumaComponent();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipHorizontal method
   * for PNG Image.
   */
  @Test
  public void test_flipHorizontal_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{0, 0, 0}, {255, 255, 0}, {255, 0, 0}},
        {{255, 0, 255}, {255, 255, 255}, {0, 255, 0}},
        {{0, 255, 255}, {0, 0, 0}, {0, 0, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.flipHorizontal();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipVertical method
   * for PNG Image.
   */
  @Test
  public void test_flipVertical_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{0, 0, 255}, {0, 0, 0}, {0, 255, 255}},
        {{0, 255, 0}, {255, 255, 255}, {255, 0, 255}},
        {{255, 0, 0}, {255, 255, 0}, {0, 0, 0}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.flipVertical();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by brightening for PNG Image.
   */
  @Test
  public void test_brighten_bright_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{255, 50, 50}, {255, 255, 50}, {50, 50, 50}},
        {{50, 255, 50}, {255, 255, 255}, {255, 50, 255}},
        {{50, 50, 255}, {50, 50, 50}, {50, 255, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.brighten(50);

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by darkening for PNG Image.
   */
  @Test
  public void test_brighten_dark_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{205, 0, 0}, {205, 205, 0}, {0, 0, 0}},
        {{0, 205, 0}, {205, 205, 205}, {205, 0, 205}},
        {{0, 0, 205}, {0, 0, 0}, {0, 205, 205}}

    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.brighten(-50);

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the splitRGB method
   * for PNG Image.
   */
  @Test
  public void test_splitRGB_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] rExpectedValues = {
        {{255, 0, 0}, {255, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {255, 0, 0}, {255, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };

    int[][][] gExpectedValues = {
        {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {0, 255, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 255, 0}}
    };

    int[][][] bExpectedValues = {
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        {{0, 0, 0}, {0, 0, 255}, {0, 0, 255}},
        {{0, 0, 255}, {0, 0, 0}, {0, 0, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage[] testResultImgPNG = testImgPNG.splitRGB();

      int[][][] rValues = testResultImgPNG[0].getRgbValues();
      int[][][] gValues = testResultImgPNG[1].getRgbValues();
      int[][][] bValues = testResultImgPNG[2].getRgbValues();

      assertArrayEquals(rExpectedValues, rValues);
      assertArrayEquals(gExpectedValues, gValues);
      assertArrayEquals(bExpectedValues, bValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for PNG Image.
   */
  @Test
  public void test_combineRGB_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    int[][][] expectedValues = {
        {{255, 0, 0}, {255, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {255, 255, 255}, {255, 0, 255}},
        {{0, 0, 255}, {0, 0, 0}, {0, 255, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage[] testSplitImgPNG = testImgPNG.splitRGB();
      IImage rImage = testSplitImgPNG[0];
      IImage gImage = testSplitImgPNG[1];
      IImage bImage = testSplitImgPNG[2];
      testImgPNG.combineRGB(rImage, gImage, bImage);

      int[][][] rgbValues = testImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);


    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the gaussianBlur method
   * for PNG Image.
   */
  @Test
  public void test_gaussianBlur_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{112, 80, 16}, {143, 112, 48}, {80, 48, 48}},
        {{80, 112, 64}, {143, 143, 128}, {112, 80, 128}},
        {{16, 48, 80}, {48, 80, 112}, {48, 80, 112}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.gaussianBlur();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the sharpen method
   * for PNG Image.
   */
  @Test
  public void test_sharpen_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{255, 0, 0}, {255, 255, 0}, {0, 0, 0}},
        {{0, 255, 0}, {255, 255, 255}, {255, 0, 255}},
        {{0, 0, 255}, {0, 0, 0}, {0, 255, 255}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.sharpen();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToGrayscale method
   * for PNG Image.
   */
  @Test
  public void test_convertToGrayscale_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{54, 54, 54}, {237, 237, 237}, {0, 0, 0}},
        {{182, 182, 182}, {255, 255, 255}, {73, 73, 73}},
        {{18, 18, 18}, {0, 0, 0}, {201, 201, 201}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.convertToGrayscale();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToSepia method
   * for PNG Image.
   */
  @Test
  public void test_convertToSepia_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int[][][] expectedValues = {
        {{100, 89, 69}, {255, 255, 206}, {0, 0, 0}},
        {{196, 175, 136}, {255, 255, 239}, {148, 132, 103}},
        {{48, 43, 33}, {0, 0, 0}, {244, 218, 170}}
    };

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      IImage testResultImgPNG = testImgPNG.convertToSepia();

      int[][][] rgbValues = testResultImgPNG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * for PNG Image.
   */
  @Test
  public void test_getValueAtPixel_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    int expectedValues = 255;

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      int testResultPNG = testImgPNG.getValueAtPixel(0,0, 0);

      assertEquals(expectedValues, testResultPNG);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PNG Image to PNG Image.
   */
  @Test
  public void test_saveToFile_PNG_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "result.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PNG Image to PNG Image.
   */
  @Test
  public void test_saveToFile_PNG_PPM() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "result.ppm";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for PNG Image to JPG Image.
   */
  @Test
  public void test_saveToFile_PNG_JPG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "result.jpg";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Out of Bounds position for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_OutOfBoundsPosition_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      int testResultPNG = testImgPNG.getValueAtPixel(5,5, 5);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Negative position for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_NegativePosition_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      int testResultPNG = testImgPNG.getValueAtPixel(-3,-5, -1);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for InvalidFilePath in PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFilePath_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "res/KoalaTest/TestInvalidPath/Koala.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * InvalidFileExtension in PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFileExtension_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for NoFileExtension in PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_NoFileExtension_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";
    String outputFilename = "res/KoalaTest/Koala";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      testImgPNG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for inequality of size for PNG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_combineRGB_InequalSize_PNG() {
    String testFilePathPNG = "res/ValueTest/bro.png";

    try {
      Image testImgPNG = Image.loadImageFromFile(testFilePathPNG);
      Image rImage = new Image(4, 3);
      Image gImage = new Image(3, 4);
      Image bImage = new Image(5, 5);
      testImgPNG.combineRGB(rImage, gImage, bImage);

      assertNotNull(testImgPNG);

    } catch (Exception e) {
      fail("Error Encountered: " + e.getMessage());
    }
  }

  /**
   * Tests the working of the LoadImageFromFile
   * method using JPG Image.
   */
  @Test
  public void testLoadImageFromFile_Working_JPG() {
    // Provide the file path of a test image
    String testImagePath = "res/ValueTest/bro.jpg";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File path for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFilePath_JPG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidPath/Koala.jpg";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail(" Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File name for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileName_JPG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/TestInvalidFileName.jpg";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using Invalid File Extension for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_InvalidFileExtension_JPG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests the LoadImageFromFile method
   * using No File Extension for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void testLoadImageFromFile_NoFileExtension_JPG() {
    // Provide the file path of a test image
    String testImagePath = "res/KoalaTest/Koala";

    try {
      // Load the image from the file
      Image image = Image.loadImageFromFile(testImagePath);

      // Verify that the image is not null
      assertNotNull(image);

    } catch (Exception e) {
      fail("Exception thrown while loading the image: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Height for JPG Image.
   */
  @Test
  public void testInvalidHeight_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);

      int height = testImgJPG.getHeight();
      int[][][] rgbValues = testImgJPG.getRgbValues();

      assertTrue("Image height should be non-zero", height > 0);
      assertEquals(testImgJPG.getHeight(), rgbValues[0].length);

    } catch (Exception e) {
      fail("Invalid Height: " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid Width for JPG Image.
   */
  @Test
  public void testInvalidWidth_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);

      int width = testImgJPG.getWidth();
      int[][][] rgbValues = testImgJPG.getRgbValues();

      assertTrue("Image width should be non-zero", width > 0);
      assertEquals(testImgJPG.getWidth(), rgbValues.length);

    } catch (Exception e) {
      fail("Invalid Width " + e.getMessage());
    }
  }

  /**
   * Tests for Invalid rgbValues array for JPG Image.
   */
  @Test
  public void testInvalidRGBValues_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);

      int[][][] rgbValues = testImgJPG.getRgbValues();

      assertNotNull(rgbValues);

    } catch (Exception e) {
      fail("RGBValues shouldn't be null" + e.getMessage());
    }
  }

  /**
   * Tests the getRedComponent method
   * for JPG Image.
   */
  @Test
  public void test_getRedComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{113, 0, 0}, {255, 0, 0}, {18, 0, 0}},
        {{209, 0, 0}, {255, 0, 0}, {138, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getRedComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getGreenComponent method
   * for JPG Image.
   */
  @Test
  public void test_getGreenComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{0, 47, 0}, {0, 222, 0}, {0, 0, 0}},
        {{0, 143, 0}, {0, 224, 0}, {0, 107, 0}},
        {{0, 28, 0}, {0, 50, 0}, {0, 225, 0}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getGreenComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getBlueComponent method
   * for JPG Image.
   */
  @Test
  public void test_getBlueComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{0, 0, 57}, {0, 0, 232}, {0, 0, 21}},
        {{0, 0, 153}, {0, 0, 234}, {0, 0, 141}},
        {{0, 0, 35}, {0, 0, 57}, {0, 0, 203}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getBlueComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueComponent method
   * for JPG Image.
   */
  @Test
  public void test_getValueComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{113, 113, 113}, {255, 255, 255}, {21, 21, 21}},
        {{209, 209, 209}, {255, 255, 255}, {141, 141, 141}},
        {{35, 35, 35}, {57, 57, 57}, {225, 225, 225}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getValueComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getIntensityComponent method
   * for JPG Image.
   */
  @Test
  public void test_getIntensityComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{72, 72, 72}, {236, 236, 236}, {13, 13, 13}},
        {{168, 168, 168}, {237, 237, 237}, {128, 128, 128}},
        {{21, 21, 21}, {35, 35, 35}, {142, 142, 142}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getIntensityComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getLumaComponent method
   * for JPG Image.
   */
  @Test
  public void test_getLumaComponent_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{62, 62, 62}, {230, 230, 230}, {5, 5, 5}},
        {{158, 158, 158}, {231, 231, 231}, {116, 116, 116}},
        {{23, 23, 23}, {40, 40, 40}, {176, 176, 176}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.getLumaComponent();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();
      System.out.println(Arrays.deepToString(rgbValues));

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipHorizontal method
   * for JPG Image.
   */
  @Test
  public void test_flipHorizontal_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{18, 0, 21}, {255, 222, 232}, {113, 47, 57}},
        {{138, 107, 141}, {255, 224, 234}, {209, 143, 153}},
        {{0, 225, 203}, {0, 50, 57}, {0, 28, 35}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.flipHorizontal();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the flipVertical method
   * for JPG Image.
   */
  @Test
  public void test_flipVertical_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
      {{0, 28, 35}, {0, 50, 57}, {0, 225, 203}},
      {{209, 143, 153}, {255, 224, 234}, {138, 107, 141}},
      {{113, 47, 57}, {255, 222, 232}, {18, 0, 21}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.flipVertical();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by brightening for JPG Image.
   */
  @Test
  public void test_brighten_bright_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{163, 97, 107}, {255, 255, 255}, {68, 50, 71}},
        {{255, 193, 203}, {255, 255, 255}, {188, 157, 191}},
        {{50, 78, 85}, {50, 100, 107}, {50, 255, 253}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.brighten(50);

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the brighten method
   * by darkening for JPG Image.
   */
  @Test
  public void test_brighten_dark_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{63, 0, 7}, {205, 172, 182}, {0, 0, 0}},
        {{159, 93, 103}, {205, 174, 184}, {88, 57, 91}},
        {{0, 0, 0}, {0, 0, 7}, {0, 175, 153}}

    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.brighten(-50);

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the splitRGB method
   * for JPG Image.
   */
  @Test
  public void test_splitRGB_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] rExpectedValues = {
        {{113, 0, 0}, {255, 0, 0}, {18, 0, 0}},
        {{209, 0, 0}, {255, 0, 0}, {138, 0, 0}},
        {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };

    int[][][] gExpectedValues = {
        {{0, 47, 0}, {0, 222, 0}, {0, 0, 0}},
        {{0, 143, 0}, {0, 224, 0}, {0, 107, 0}},
        {{0, 28, 0}, {0, 50, 0}, {0, 225, 0}}
    };

    int[][][] bExpectedValues = {
        {{0, 0, 57}, {0, 0, 232}, {0, 0, 21}},
        {{0, 0, 153}, {0, 0, 234}, {0, 0, 141}},
        {{0, 0, 35}, {0, 0, 57}, {0, 0, 203}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage[] testResultImgJPG = testImgJPG.splitRGB();

      int[][][] rValues = testResultImgJPG[0].getRgbValues();
      int[][][] gValues = testResultImgJPG[1].getRgbValues();
      int[][][] bValues = testResultImgJPG[2].getRgbValues();

      assertArrayEquals(rExpectedValues, rValues);
      assertArrayEquals(gExpectedValues, gValues);
      assertArrayEquals(bExpectedValues, bValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for JPG Image.
   */
  @Test
  public void test_combineRGB_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    int[][][] expectedValues = {
        {{113, 47, 57}, {255, 222, 232}, {18, 0, 21}},
        {{209, 143, 153}, {255, 224, 234}, {138, 107, 141}},
        {{0, 28, 35}, {0, 50, 57}, {0, 225, 203}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage[] testSplitImgJPG = testImgJPG.splitRGB();
      IImage rImage = testSplitImgJPG[0];
      IImage gImage = testSplitImgJPG[1];
      IImage bImage = testSplitImgJPG[2];
      testImgJPG.combineRGB(rImage, gImage, bImage);

      int[][][] rgbValues = testImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);


    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the gaussianBlur method
   * for JPG Image.
   */
  @Test
  public void test_gaussianBlur_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{102, 71, 77}, {134, 105, 115}, {70, 55, 67}},
        {{114, 90, 97}, {147, 140, 151}, {85, 100, 111}},
        {{42, 45, 50}, {54, 88, 92}, {33, 90, 90}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.gaussianBlur();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the sharpen method
   * for JPG Image.
   */
  @Test
  public void test_sharpen_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{255, 0, 0}, {255, 255, 255}, {0, 0, 0}},
        {{255, 255, 255}, {255, 255, 255}, {255, 242, 255}},
        {{0, 0, 0}, {0, 0, 0}, {0, 255, 255}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.sharpen();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToGrayscale method
   * for JPG Image.
   */
  @Test
  public void test_convertToGrayscale_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{62, 62, 62}, {230, 230, 230}, {5, 5, 5}},
        {{158, 158, 158}, {231, 231, 231}, {116, 116, 116}},
        {{23, 23, 23}, {40, 40, 40}, {176, 176, 176}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.convertToGrayscale();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the convertToSepia method
   * for JPG Image.
   */
  @Test
  public void test_convertToSepia_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int[][][] expectedValues = {
        {{91, 81, 63}, {255, 255, 218}, {11, 10, 8}},
        {{221, 197, 153}, {255, 255, 220}, {163, 145, 113}},
        {{28, 25, 20}, {49, 44, 34}, {211, 188, 147}}
    };

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      IImage testResultImgJPG = testImgJPG.convertToSepia();

      int[][][] rgbValues = testResultImgJPG.getRgbValues();

      assertArrayEquals(expectedValues, rgbValues);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * for JPG Image.
   */
  @Test
  public void test_getValueAtPixel_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    int expectedValues = 113;

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      int testResultJPG = testImgJPG.getValueAtPixel(0,0, 0);

      assertEquals(expectedValues, testResultJPG);

    } catch (Exception e) {
      fail("Calculated output is incorrect: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for JPG Image to JPG Image.
   */
  @Test
  public void test_saveToFile_JPG_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "result.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for JPG Image to JPG Image.
   */
  @Test
  public void test_saveToFile_JPG_PPM() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "result.ppm";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for JPG Image to JPG Image.
   */
  @Test
  public void test_saveToFile_JPG_PNG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "result.png";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Out of Bounds position for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_OutOfBoundsPosition_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      int testResultJPG = testImgJPG.getValueAtPixel(5,5, 5);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the getValueAtPixel method
   * using Negative position for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_getValueAtPixel_NegativePosition_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      int testResultJPG = testImgJPG.getValueAtPixel(-3,-5, -1);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for InvalidFilePath in JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFilePath_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "res/KoalaTest/TestInvalidPath/Koala.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * InvalidFileExtension in JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_InvalidFileExtension_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "res/KoalaTest/Koala.TestInvalidFileExtension";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the saveToFile method
   * for NoFileExtension in JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_saveToFile_NoFileExtension_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";
    String outputFilename = "res/KoalaTest/Koala";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      testImgJPG.saveToFile(outputFilename);

      Image image = Image.loadImageFromFile(outputFilename);

      assertNotNull(image);

    } catch (Exception e) {
      fail("Operation Failed: " + e.getMessage());
    }
  }

  /**
   * Tests the combineRGB method
   * for inequality of size for JPG Image.
   */
  @Test (expected = AssertionError.class)
  public void test_combineRGB_InequalSize_JPG() {
    String testFilePathJPG = "res/ValueTest/bro.jpg";

    try {
      Image testImgJPG = Image.loadImageFromFile(testFilePathJPG);
      Image rImage = new Image(4, 3);
      Image gImage = new Image(3, 4);
      Image bImage = new Image(5, 5);
      testImgJPG.combineRGB(rImage, gImage, bImage);

      assertNotNull(testImgJPG);

    } catch (Exception e) {
      fail("Error Encountered: " + e.getMessage());
    }
  }
}


