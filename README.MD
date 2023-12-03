# Image Manipulation and Editing (IME) Application
## Overview
This program provides a set of operations for processing 24-bit images with Red, Green, and Blue channels through a text-based interface. It is structured around three primary components: the Model, View, and Controller to facilitate image operations, display information to the user, and handle user inputs.

## Classes
### Main
- Purpose: Entry point of the application.
- Function:
  - Initializes the application components (Model, View, Controller) and relinquishes control to the controller. 
  - Decides between different application modes (GUI, Text, Script) based on the arguments provided to it.
### Controller
- Purpose: Acts as an intermediary between the Model and View.
- Function: Scans user inputs, calls the respective action method in the model for processing, and communicates output to the View.
### GUIController
- Purpose: A controller for the GUI version of the application.
- Function: Provides callback functions to the GUI View. Determines what flow of operations should be orchestrated in a callback for a given user-interaction. 
  - Tells the model what operations to perform. 
  - Tells the view what to display. (Display Image, Update Histogram, Show preview, etc.)
### Model
- Purpose: Represents the application's logic and session data.
- Function: Maintains the session memory of loaded images.
  - It's sole purpose is to handle any memory optimizations in the application. 
  - And delegate to the correct Image operation method in the Image class (service).
### Image
- Purpose: Contains the logic for the operations to be performed on the Image.
- Function: Creates an Image object and operates on it.
### View
- Purpose: Provides a text-based user interface.
- Function: Displays messages and success notifications to the user via the console.
### GUIView
- Purpose: A GUI based view.
- Function: A graphical user interface for the application.
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
### ImageViewer
- Purpose: A read only version of an image.
- Function: An object adaptor around the Image class.

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
- Purpose: A readonly version of the Image.
- Function: Interface for ImageView class, which is an object adaptor for the Image class.
### IFeatures
- Purpose: Template for the features exposed to the graphical interface.
- Function: Features exposed to the view of the progarm.
### IGUIView
- Purpose: Template for GUI based view.
- Function: A graphical user interface for the application.

# Change Log
All notable changes to this project will be documented in this Change Log.

## [Latest Version v3.0] Assignment 6

### Added
- Added GUI Controller and GUI View  using Java Swing and their respective interfaces.
  - Justification for Controller: The existing controller could not be reused for the new Graphical based Interface due to the need for multiple GUI specific callbacks which was not offered by the existing controller.
  - Justification for View: To add graphical User interface functionality to our application.
- Implemented Read only Image in the ImageView class as an ObjectAdapter around existing image.
  - Justification: To return data to the Graphical view thus preventing the view from mutating the data.

### Modified
- Segregated Read only methods from Image interface to new Read only Image Interface.
  - Justification: The Existing interface was broken down due to the need for providing read only images to the view. This allowed the retention of only few specific methods from the existing interface.

## [v2.0] Assignment 5

### Added
- Added capability for launching and running program in script mode based on Command Line Arguments.
  - Justification -  Currently, there are no clients of our interface. So adding new methods to it does not force any other client of our interface to implement the additions.

- Added methdos for new image operations - Histogram, Color Correct, Compress, Colors Adjust and Split View.
  - Justification: We chose to edit existing code because The changes do not modify existing behaviour of our class, but rather only add 5 new functionalities

### Modified
- Moved the existing classes and interfaces for our file parser into a separate package.
  - Justification: We are adhering to "Separate of concerns." Since these classes are independent of the model, they have no place existing in the model package. In an image processing application, it is reasonable to expect new functionalities are going to be required quite frequently. If we create a new class for every added feature, the number of classes in our project could potentially explode, making it difficult to maintain our project structure.
- Added new class/interface to support drawing Line Graphs on Images.
  - Justification: Since it is used to provide additional functionality on an image, it exists in our model package.

## File Structure
    src                                                 
        imeprogram (Main package)                       (Folder contains all the code of the project.)
            controller                                  (Controller Folder contains Controller Interface and class.)
                Controller.java                         (Controller class controls both model and view.)
                IController.java                        (Controller interface defines Controller class methods.)
                GUIController.java                      (Contains methods that facilitate communication between the Controller and the GUIView class.)
                IFeatures.java                          (Defines the methods to be used by the GUI class of the application.)
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
                IReadOnlyImage                          (IReadOnlyImage interface defines the methods for a ReadOnlyImage Object.)
                ImageViewer                             (ImageViewer class implements the IReadOnlyImage interface.)
            view                                        (View Folder contains View Interface and class.)
                IView.java                              (View interface defines View class methods.)
                View.java                               (View class handles getting input and displays output.)
                GUIView                                 (GUIView class creates and handles UI of the application.)
                IGUIView                                (GUIView Interface defines the methods for the GUIView class.)
            Main.java                                   (Main class Invokes Controller and starts program.)
    test
        integrationImages                               (Input Folder for integration testing)
        integrationResults                              (Output Folder for integration testing)
        unitImages                                      (Input Folder for unit testing)
        unitResults                                     (Output Folder for unit testing)
        ControllerTest.java                             (JUnit Tests for Controller class)
        GUIControllerTest.java                          (JUnit Test for the GUIController class)        
        ImageTest.java                                  (JUnit Tests for Image class)                   
        IntegrationTest.java                            (Integration Test for imeprogram)
        MockModel.java                                  (Mock version of the Model class for testing purpose)
        ModelTest.java                                  (JUnit Test for the Model class)
        ViewTest.java                                   (JUnit Test for View class)
    res
        A4                                               (Output directory for Assignment4 Result Images)
        A5                                               (Output directory for Assignment5 Result Images)
        A6                                               (Output directory for Assignment6 Sample Images and Result Images)
        Galaxy.png                                       (Sample Image used in testscript-A5.txt for Levels Adjust and Color correct and their splitview versions)
        Image.png                                        (Sample Image used in testscript-A4.txt and testscript-A5.txt for assignment4 base operations and their assignment5 SplitView counterparts)
        testscript-A4.txt                                (Test script for Assignment4 to run with the jar file: Assignment4.jar)
        testscript-A5.txt                                (Test script for Assignment5 to run with the jar file: Assignment4.jar)
        ClassDiagram-Assignment5.pdf                     (Latest version of class diagram)
        Assignment4.jar                                  (Latest jar file of Assignment6, supports all operations and modes implemented till date)

## Citations for Resources used.
- The File Image.png and Galaxy.png and its corresponding formats are created and owned by the authors of this program.
- All Images used for testing and representation purposes are owned by the developers of this project by direct ownership.
- Images given in Assignment Sample Help Pool have also been used in this project.
- Image "nature" present in the res/A6/SampleImage folder belongs to Sasin Tipchai from Pixabay.