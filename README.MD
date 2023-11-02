# Image Manipulation and Editing (IME) Application
## Overview
This program provides a set of operations for processing 24-bit images with Red, Green, and Blue channels through a text-based interface. It is structured around three primary components: the Model, View, and Controller to facilitate image operations, display information to the user, and handle user inputs.

## Usage
### How to run.
- Run the Main class to start the application.
- Use the text-based interface to load, manipulate, and save images.

### List of commands.
- `load image-path image-name` Load an image from the specified path and refer it to henceforth in the program by the given image name.
- `save image-path image-name` Save the image with the given name to the specified path which should include the name of the file.
- `red-component image-name dest-image-name` Create an image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported. Note that the images for value, luma and intensity will be greyscale images.
- `horizontal-flip image-name dest-image-name` Flip an image horizontally to create a new image, referred to henceforth by the given destination name.
- `vertical-flip image-name dest-image-name` Flip an image vertically to create a new image, referred to henceforth by the given destination name.
- `brighten increment image-name dest-image-name` brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).
- `rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue` split the given image into three images containing its red, green and blue components respectively. These would be the same images that would be individually produced with the red-component, green-component and blue-component commands.
- `rgb-combine image-name red-image green-image blue-image` Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.
- `blur image-name dest-image-name` blur the given image and store the result in another image with the given name.
- `sharpen image-name dest-image-name` sharpen the given image and store the result in another image with the given name
- `sepia image-name dest-image-name` produce a sepia-toned version of the given image and store the result in another image with the given name.
 
### How to run the script file
- A script file (script.txt) has been included.
- To run it, run the Main.java file first.
- In the Console, type `run scipt-filename`.
- This runs all the commands in the script file.

### Tips to use commands
- Operation, filepath, image alias are case-insensitive
- Image alias only accepts a-z, 0-1, dots, hyphen and underscores [a-zA-Z0-9._-].
- For Filepath, has to be in double quotes if the file path has spaces. If it has no spaces, it can be in double quotes or without quotes as well.
- Nested scripts are not allowed. (for eg. Run command inside a script file is illegal).



## Classes
### Main
- Purpose: Entry point of the application.
- Function: Initializes the application components (Model, View, Controller) and relinquishes control to the controller.
### Controller
- Purpose: Acts as an intermediary between the Model and View.
- Function: Scans user inputs, calls the respective action method in the model for processing, and communicates output to the View.
### Model
- Purpose: Represents the application's logic and session data.
- Function: Handles the image operations, and maintains the session memory of loaded images.
### View
- Purpose: Provides a text-based user interface.
- Function: Displays messages and success notifications to the user via the console.
### Image
- Purpose: Contains the logic for the operations to be performed on the Image.
- Function: Creates an Image object and operates on it.

## Interfaces
### IController
- Purpose: Defines the methods to be implemented by the Controller.
- Function: Outlines the functionalities required to manage user inputs and interactions with the Model and View.
### IModel
- Purpose: Defines the methods to be implemented by the Model.
- Function: Specifies functionalities related to image loading, manipulation, and storage.
### IView
- Purpose: Defines the methods to be implemented by the View.
- Function: Specifies methods to display messages and success notifications.
### IImage
- Purpose: Defines the methods to be implemented by the Image Class.
- Function: Represents an image and defines the methods that perform operations on the Image.

## File Structure
    src                                                 
        imeprogram (Main package)                       (Folder contains all the code of the project.)
            controller                                  (Controller Folder contains Controller Interface and class.)
                Controller.java                         (Controller class controls both model and view.)
                IController.java                        (Controller interface defines Controller class methods.)
            exceptions                                  (Exceptions Folder contains Custom Exceptions.)
                FileFormatException.java                (Custom Exception for Invalid File Format.)
                ImageNotFoundException.java             (Custom Exception for Inmage not found.)
                InvalidImageNameException.java          (Custom Exception for Invalid Image Name.)
            model                                       (Model Folder contains Model Interface and class and Image Interface and class.)
                IImage.java                             (Image interface defines Image class methods.)
                Image.java                              (Image class performs operations on Image.)
                IModel.java                             (Model interface defines Model class methods.)
                Model.java                              (Model class passes operations to Image class.)
            view                                        (View Folder contains View Interface and class.)
                IView.java                              (View interface defines View class methods.)
                View.java                               (View class handles getting input and displays output.)
            Main.java                                   (Main class Invokes Controller and starts program.)
    test
        integration                                     (Output Folder for integration testing)
        integrationImages                               (Input Folder for integration testing)
        unitImages                                      (Input Folder for unit testing)
        ControllerTest.java                             (JUnit Tests for Controller class)
        ImageTest.java                                  (JUnit Tests for Image class)                   
        IntegrationTest.java                            (Integration Test for imeprogram)
        ViewTest.java                                   (JUnit Test for View class)

## Citations for Resources used.
- The File Image.png and its corresponding formats are created and owned by the authors of this program.