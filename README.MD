# Image Manipulation and Editing (IME) Application
## Overview
This program provides a set of operations for processing 24-bit images with Red, Green, and Blue channels through a text-based interface. It is structured around three primary components: the Model, View, and Controller to facilitate image operations, display information to the user, and handle user inputs.

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
### BinaryImageIO
- Purpose: Implements IImageFileIO to Perform Image IO for PNG and JPG Images.
- Function: Either reads file and returns or saves to image, a 3D RGBValues array.
### PPMImageIO
- Purpose: Implements IImageFileIO to Perform Image IO for PPM Images.
- Function: Either reads file and returns or saves to image, a 3D RGBValues array.
### ImageFileIOFactory
- Purpose: Determine the type of Image IO Operation to be performed on the Input image.
- Function: Routes control to PPMImageIO or BinaryImageIO based on File Extension.
### LineGraph2D
- Purpose: Generate a 2D Line graph.
- Function: Draws a Line Graph using the data given.

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
### IImageFileIO
- Purpose: Template to Perform Image IO for PNG , PPM and JPG Images.
- Function: Interface for BinaryImageIO and PPMImageIO.
### IImageFileIOFactory
- Purpose: Template to Determine the type of Image IO Operation to be performed on the Input image.
- Function: Interface for Image IO type Routing Factory Class.
### ILineGraph2D
- Purpose: Template to Generate a 2D Line graph.
- Function: Interface for the LineGraph2D class.
### IReadOnlyImage 
- Purpose: A readonly version 



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
            fileparser                                  (Folder contains classes for Image file IO.)    
                BinaryImageIO.java                      (Java class to perform IO operations on PPM image files.)
                IImageFileIO.java                       (Interface for the BinaryImageIO and PPMImageIO classes.)
                IImageFileIOFactory.java                (Interface for the IImageFileIOFactory class.)
                ImageFileIOFactory.java                 (Java class that uses file entension that routes to corresponding IO class.)
                PPMImageIO.java                         (Java class to perform IO operations on PNG and JPG image files.)
            model                                       (Model Folder contains Model Interface and class and Image Interface and class along with Helper Classes.)
                IImage.java                             (Image interface defines Image class methods.)
                Image.java                              (Image class performs operations on Image.)
                IModel.java                             (Model interface defines Model class methods.)
                Model.java                              (Model class passes operations to Image class.)
                ILineGraph.java                         (Interface for the LineGraph2D class.)
                LineGraph2D.java                        (Java Class to Draw a 2D Line Graph.)
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
- All Images used for testing and representation purposes are owned by the developers of this project by direct ownership.
- Images given in Assignment Sample Help Pool have also been used in this project.