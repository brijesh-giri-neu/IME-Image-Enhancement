package imeprogram.model;

/**
 * An implementation for the ImageFileIOFactory used to create an IImageFileIO object.
 */
public class ImageFileIOFactory implements IImageFileIOFactory {

  @Override
  public IImageFileIO getImageParser(String filePath) throws IllegalArgumentException {
    String extension = getExtensionFromPath(filePath);
    if (extension != null) {
      switch (extension.toLowerCase()) {
        case "jpg":
        case "jpeg":
        case "png":
          return new BinaryImageIO();
        case "ppm":
          return new PPMImageIO();
        default:
          throw new IllegalArgumentException("Unsupported file format: " + filePath);
      }
    }
    return null;
  }

  private String getExtensionFromPath(String filePath) {
    // Extract the file extension from the filepath
    String extension = "";
    int i = filePath.lastIndexOf('.');
    if (i > 0) {
      extension = filePath.substring(i + 1);
    }
    return extension;
  }
}
