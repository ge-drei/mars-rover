# Mars Rover
Simulates the movement of a rover over a plateau on mars, in the form of a bounded rectangular grid.

### Running the project
Run `main()` in `Main` to launch the project using console input.

You will be prompted to define the size of the plateau (where the maximum coordinate must be positive), where to land the rover (which must be a valid coordinate on the plateau), and a series of commands to execute.

### Documentation

#### Spatial
Package contains enums and classes for handling spatial data.
* `Cardinal`: The cardinal directions (North, East, South, West)
* `Relative`: Relative directions (Left and Right)
* `Coordinate`: Encapsulates a pair of X and Y integer coordinates, with overriden `.equals()` and `.toString()` methods
* `RoverPosition`: Encapsulates a `Coordinate` with a `Cardinal`, which defines a valid Rover position and orientation.
#### Rover
Package contains the Rover interface and its basic implementation.
* `getPosition`/`setPosition`: Retrieves and sets the current coordinates of the Rover, as a Coordinate.
* `getDirection`/`setDirection`: Retrieves and sets the current facing of the Rover, as a Cardinal.
* `getTurnDirection`: Returns what the facing of the Rover would be if given a command to turn, in the form of a Relative. The basic Rover makes quarter-turns left and right. 
* `calculateNewPosition`: Returns what the position of the Rover would be if given a command to move, in the form of a Coordinate. The basic Rover moves 1 unit in the direction it faces.
#### Surface
Package contains the Surface interface and its basic implementation.
The size and shape of a Surface is a rectangle bounded by (0,0) and the maximum coordinate.
* `getMaxCoordinates`: Returns the maximum Coordinate of the Surface.
* `isValidCoordinate`: When given a Coordinate, returns true or false depending on if it is a valid position for a Rover on this Surface. The basic Surface checks if the Coordinate is in bounds. 
#### IO
Package contains the Command enum and the InputHandler class.
* `Command`: Contains the valid commands that can be passed to a Rover (Turn left, turn right, move).
* `InputHandler`: Handles input and output to and from the console.

`InputHandler` uses a `Scanner` to read console output, and contains the following methods:
* `getInput`: Returns `scanner.nextLine()`
* Surface size:
* * `promptMaxSurfaceSize`: Prints prompt for user to input a maximum surface size.
* * `validateMaxSurfaceSize`: Checks if a string can be parsed as a valid Coordinate or not. Returns that Coordinate if it can, throws `IllegalArgumentException` if not.
* * `getMaxSurfaceSize`: Using above methods, prompts users for a valid maximum surface size and keeps prompting until they input one, then returns it as a Coordinate.
* Rover position:
* * `promptRoverPosition`: Prints prompt for user to input a valid initial rover position.
* * `validateRoverPosition`: Checks if a string can be parsed as a valid rover position or not. Returns that RoverPosition if it can, throws `IllegalArgumentException` if not.
* * `getRoverPosition`: Using above methods, prompts users for a valid initial rover position and keeps prompting until they input one, then returns it as a RoverPosition.
* Command sequence:
* * `promptCommandSequence`: Prints prompt for user to input a valid command sequence.
* * `validateCommandSequence`: Checks if a string can be parsed as a valid rover command sequence or not. Returns the same string if it can, throws `IllegalArgumentException` if not.
* * `interpretCommand`: Reads a character and returns the corresponding Command if it can be correctly interpreted, throws `IllegalArgumentException` if not.
* * `interpretCommandSequence`: Using `interpretCommand`, produces an array of Commands or throws `IllegalArgumentException` if string is invalid.
* * `getCommandSequence`: Using above methods, prompts users for a valid rover command sequence and keeps prompting until they input one, then returns it as an array of Commands.
* `out`: Prints a string to console.
* `exit`: Closes the handler's scanner. Used for cleanup at end of program.

#### Main
Contains `MarsMission` and `SequenceResult` classes.
* `SequenceResult` encapsulates the results of carrying out a command sequence and an accompanying message.
* * `succeeded`: `true` if the sequence executed successfully, `false` otherwise.

`MarsMission` is the core logic class of the project. It contains a reference to its InputHandler, and a reference to an active Surface and Rover. 
* `makeSurface`: Takes a Coordinate, and creates a basic surface with the Coordinate as its max position.
* `makeRover`: Overloaded methods to make a basic Rover, either with a Coordinate and Cardinal direction or with a RoverPosition encapsulating those.
* `setSurface`/`setRover`: Sets the active Surface and Rover.
* `turnRover`: Issues turn Commands to the active Rover. Throws `IllegalStateException` if called without an active Rover.
* `isRoverStepValid`: Queries the active Rover for what its next position would be if issued a Move command, and then checks with the active Surface if it would be a valid position. Throws `IllegalStateException` if called without both a valid active Rover and Surface. 
* `executeRoverStep`: Given a Command, issues it to the active Rover. Returns `true` if command was carried out successfully, `false` otherwise. Throws `IllegalStateException` if called without an active Rover or Surface.
* `executeCommandSequence`: Given an array of Commands, issues them to the active Rover. Returns a `SequenceResult` with the results of the sequence. Throws `IllegalStateException` if called without an active Rover or Surface.
* `begin`: Runs the program.