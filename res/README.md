# Image Manipulation and Editing (IME) Application
## Overview
The IME application is designed to manipulate and edit images through a text-based interface. It is structured around three primary components: the Model, View, and Controller to facilitate image operations, display information to the user, and handle user inputs.

## Classes
### Main
- Purpose: Entry point of the application.
- Function: Initializes the application components (Model, View, Controller) and starts the application.
### Model
- Purpose: Represents the application's logic and data.
- Function: Contains image-related functionalities, handles image operations, and maintains the session memory of loaded images.
### View
- Purpose: Provides a text-based user interface.
- Function: Displays messages and success notifications to the user via the console.
### Controller
- Purpose: Acts as an intermediary between the Model and View.
- Function: Orchestrates user inputs, interacts with the Model for processing, and communicates output to the View.
## Interfaces
### IModel
- Purpose: Defines the methods to be implemented by the Model.
- Function: Specifies functionalities related to image loading, manipulation, and storage.
### IView
- Purpose: Defines the methods to be implemented by the View.
- Function: Specifies methods to display messages and success notifications.
## File Structure
### IMEProgram (Main package)
- Model: Contains the Model class that handles image operations and maintains session memory.
- View: Includes the View class responsible for the text-based user interface.
- Controller: Contains the Controller class for orchestrating user inputs and communication between Model and View.
- Main: The entry point of the application that initializes the core components.
### Functionalities
- Image Loading: Loading images from file paths.
- Image Manipulation: Manipulating images by splitting components, applying effects, and saving results.
- Image Saving: Saving edited images to file paths.
- User Interface: Providing a text-based interface for displaying information and user interactions.
## Usage
- Run the Main class to start the application.
- Use the text-based interface to load, manipulate, and save images.

### Tips to use commands
- Operation, filepath, image alias are case-insensitive
- Image alias only accepts a-z, 0-1, dots, hyphen and underscores [a-zA-Z0-9._-].
- For Fileapath, has to be in double quotes if the file path has spaces. If it has no spaces, it can be in double quotes or without quotes as well.
- Nested scripts are not allowed. (for eg. Run command inside a script file is illegal).

### How to run the script file
- A script file (script.txt) has been included.
- To run it, run the Main.java file first.
- In the Console, type "run <path of script file>".
- This runs all the commands in the script file.

## Citations for Resources used.
- Image.png is created and owned by the authors of this program.

