# Image Manipulation and Editing (IME) Application
## Usage

### Graphical User Interface mode

#### How to run.
- Run the jar file with the command in terminal "java -jar Assignment4.jar"
- Application will open with GUI window

#### Overview of Interface
- The interface has 3 panel:
  - `Left Pane` - Contains Buttons for Image operations, a Zoom slider, and Histogram of current image 
  - `Center Pane` - Contains the Image loaded along with the Scroll functionality.
  - `Right Pane` - The thumbnail of the loaded image

#### How to perform operations
1. Click the load button to load an image in the application.
2. Click any if the operation buttons on the panel to perform an operation on this image.
3. User will be prompted with a preview pop-up that allows the user to either commit to click "OK" button to apply changes, or click "cancel" button to revert changes.
4. For operations that support a split preview, the preview popup contains a checkbox to enable the splitview, and a slider to toggle split percentages.
5. After committing to a change, or reverting it, the user can choose to perform other operations, or save the image to file.

#### List of operations and their Usage

```These functions open up a popup containing apply changes or cancel changes buttons.```

- `Flip Horizontally` - Flips the Image Horizontally.
- `Flip Vertically`- Flips the Image Vertically.
- `Visualize Red` - Gets the red component of the image.
- `Visualize Green` - Gets the green component of the image.
- `Visualize Blue` - Gets the blue copmponent of the image.
- `Compress Image` - Compresses the Image based on the given ratio.

```These functions open up a popup containing apply changes or cancel changes buttons. They also offer the functionality to toggle split view and change the ratio of original to filtered image.```
- `Blur Image` - Blurs the Image.
- `Sharpen Image` - Sharpens the Image
- `Sepia Image` - Applies the Sepia Filter to the Image.
- `Luma Image` - Applies the Luma Filter to the Image.
- `Color correct Image` - Performs color correction on the Image.
- `Adjust Levels` - Performs Adjust Levels operation on the Image based on the given b, m, w values.

```Miscellaneous Operations```
- `Open Image` - Opens a popup from which an image can be selected and opened to be operated on. png, jpg and ppm formats are supported.
- `Save Image` - Opens a popup in which, an image can be named and saved. png, jpg and ppm formats are supported.
- `Zoom` - Presents a slider that can be dragged to change the zoom level of the image.
- `File Path` - Displays the file path of the image.
- `Histogram` - Displays the histogram of the image at all times and updates whenever the image is operated on.

### Command Line Text based mode

#### How to run.
- Run the jar file with the command in terminal "java -jar Assignment4.jar -text"
- Application will open in the interactive command line mode

#### List of commands.
- `load image-path image-name` Load an image from the specified path and refer it to henceforth in the program by the given image name.
- `save image-path image-name` Save the image with the given name to the specified path which should include the name of the file.
- `red-component image-name dest-image-name` Create an image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported. Note that the images for value, luma and intensity will be greyscale images.
- `horizontal-flip image-name dest-image-name` Flip an image horizontally to create a new image, referred to henceforth by the given destination name.
- `vertical-flip image-name dest-image-name` Flip an image vertically to create a new image, referred to henceforth by the given destination name.
- `brighten increment image-name dest-image-name` brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).
- `rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue` split the given image into three images containing its red, green and blue components respectively. These would be the same images that would be individually produced with the red-component, green-component and blue-component commands.
- `rgb-combine image-name red-image green-image blue-image` Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.
- `blur image-name dest-image-name` blur the given image and store the result in another image with the given name.
- `sharpen image-name dest-image-name` sharpen the given image and store the result in another image with the given name.
- `sepia image-name dest-image-name` produce a sepia-toned version of the given image and store the result in another image with the given name.
- `compress percentage image-name dest-image-name` creates a compression version of an image to the given percentage and store the result in another image with the given name.
- `histogram image-name dest-image-name`  produce a 256 x 256 image that represents the histogram of a given image and store the result in another image with the given name.
- `color-correct image-name dest-image-name` performs color-correction on an image by aligning the meaningful peaks of its histogram and store the result in another image with the given name.
- `levels-adjust b m w image-name dest-image-name` adjusts the colour levels of an image and store the result in another image with the given name.
- `operation-name image-name dest-image split percent` produces an image with the image transformed with the operation to the given ratio. Supported operations are blur, sharpen, sepia, greyscale, color correction and levels adjustment.

#### Examples of the Commands.
#### load a png image file
- load Image.png resImage
- load Galaxy.png galaxyImage

#### Compress the image
- compress 40 resImage resImageCompress40
- save res/A5/Compress40-Image.jpg resImageCompress40

#### Compress the image
- compress 75 resImage resImageCompress75
- save res/A5/Compress75-Image.jpg resImageCompress75

#### Generate histogram of original Image
- histogram galaxyImage histResImage
- save res/A5/histogram-Image.png histResImage

#### Color correct
- color-correct galaxyImage colorCorrectResImage
- save res/A5/colorCorrect-Image.jpg colorCorrectResImage

#### Generate histogram of color corrected Image
- histogram colorCorrectResImage histcolorCorrectResImage
- save res/A5/histogram-colorCorrect-Image.png histcolorCorrectResImage

#### Levels adjust - Example 1
- levels-adjust 20 100 255 galaxyImage levelsAdjustedResImage
- save res/A5/levelsAdjust1-Image.jpg levelsAdjustedResImage

#### Generate histogram of levels adjusted Image
- histogram levelsAdjustedResImage histlevelsAdjustedResImage
- save res/A5/histogram-levelsAdjust1-Image.png histlevelsAdjustedResImage

#### Levels adjust - Example 2
- levels-adjust 5 115 255 galaxyImage levelsAdjustedResImage
- save res/A5/levelsAdjust2-Image.jpg levelsAdjustedResImage

#### Generate histogram of levels adjusted Image
- histogram levelsAdjustedResImage histlevelsAdjustedResImage
- save res/A5/histogram-levelsAdjust2-Image.png histlevelsAdjustedResImage

#### Split view operations
#### blur
- blur resImage blurredImage1 split 50
- blur blurredImage1 blurredImage2 split 50
- blur blurredImage2 blurredImage3 split 50
- blur blurredImage3 blurredImage4 split 50
- blur blurredImage4 blurredImage5 split 50
- save res/A5/SplitView/blur-5times-Split50-Image.jpg blurredImage5

#### sharpen
- sharpen resImage resImageSharpen split 50
- save res/A5/SplitView/sharpen-1time-Split50-Image.jpg resImageSharpen

#### sepia
- sepia resImage resImageSepia split 50
- save res/A5/SplitView/sepia-Split50-Image.jpg resImageSepia

#### Get luma-component - Grayscale command
- luma-component resImage lumaResImage split 50
- save res/A5/SplitView/luma-Split50-Image.jpg lumaResImage

#### Color correct split
- color-correct galaxyImage colorCorrectResImageSplit50 split 50
- save res/A5/SplitView/colorCorrect-Split50-Image.jpg colorCorrectResImageSplit50

#### Levels adjust split
- levels-adjust 20 100 255 galaxyImage levelsAdjustedResImageSplit50 split 50
- save res/A5/SplitView/levelsAdjust-Split50-Image.jpg levelsAdjustedResImageSplit50

### Script mode

#### How to run the script file
- Examples of script file (testscript-A5.txt, testscript-A4.txt) has been included in the "res" folder.
- In the Console, type `java -jar Assignment4.jar -file scipt-filename.extension`.
- This runs all the commands in the script file.
- Example, for Assignment 4 script the command "java -jar Assignment4.jar -file testscript-A4.txt"
- Example, for Assignment 5 script the command "java -jar Assignment4.jar -file testscript-A5.txt"

### Tips to use commands
- Operation, filepath, image alias are case-insensitive
- Image alias only accepts a-z, 0-1, dots, hyphen and underscores [a-zA-Z0-9._-].
- For Filepath, has to be in double quotes if the file path has spaces. If it has no spaces, it can be in double quotes or without quotes as well.
- Nested scripts are not allowed. (for eg. Run command inside a script file is illegal).
- To run the jar file, navigate to the res folder, and run the command `java -jar Assignment4.jar -file testscript-a5.txt`.
- `Assignment4.jar` is the jar file, which can be run using `testscript-a4.txt` or `testscripta5.txt`, the results of which would go to the folders under the respective names.