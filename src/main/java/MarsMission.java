import io.*;
import rover.*;
import spatial.*;
import surface.BasicSurface;
import surface.Surface;

public class MarsMission {
    private Surface surface;
    private Rover rover;
    private InputHandler handler;

    public MarsMission() {
        this.handler = new InputHandler();
    }

    public Surface makeSurface(Coordinate max) {
        if (max.getX() <= 0 || max.getY() <= 0) {
            throw new IllegalArgumentException("Max plateau co-ordinates must be greater than zero");
        } else {
            return new BasicSurface(max);
        }
    }

    public Rover makeRover(Coordinate position, Cardinal direction) {
        if (position == null) {
            throw new IllegalArgumentException("Rover position cannot be null");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Rover direction cannot be null");
        }

        return new BasicRover(position, direction);
    }

    public Rover makeRover(RoverPosition position) {
        if (position == null) {
            throw new IllegalArgumentException("Rover position cannot be null");
        }

        return new BasicRover(position);
    }

    public void setSurface(Surface surface) {
        if (surface != null) {
            this.surface = surface;
        } else {
            throw new IllegalArgumentException("Surface cannot be null");
        }
    }

    public void setRover(Rover rover) {
        if (rover != null) {
            this.rover = rover;
        } else {
            throw new IllegalArgumentException("Rover cannot be null");
        }
    }

    public void turnRover(Relative relative) {
        if (rover == null) {
            throw new IllegalStateException("Rover has not been initialised correctly.");
        }

        rover.setDirection(rover.getTurnDirection(relative));
    }

    public boolean isRoverStepValid() {
        boolean somethingIsNull = false;
        String illegalStateMessage = "";
        if (rover == null) {
            somethingIsNull = true;
            illegalStateMessage += "Rover has not been initialised correctly. ";
        }
        if (surface == null) {
            somethingIsNull = true;
            illegalStateMessage += "Surface has not been initialised correctly.";
        }
        if (somethingIsNull) {
            throw new IllegalStateException(illegalStateMessage);
        }

        Coordinate destination = rover.calculateNewPosition();
        return surface.isValidCoordinate(destination);
    }

    public boolean executeRoverStep(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }
        boolean somethingIsNull = false;
        String illegalStateMessage = "";
        if (rover == null) {
            somethingIsNull = true;
            illegalStateMessage += "Rover has not been initialised correctly. ";
        }
        if (surface == null) {
            somethingIsNull = true;
            illegalStateMessage += "Surface has not been initialised correctly.";
        }

        if (somethingIsNull) {
            throw new IllegalStateException(illegalStateMessage);
        }
        switch (command) {
            case LEFT -> {
                rover.setDirection(rover.getTurnDirection(Relative.LEFT));
                return true;
            }
            case RIGHT -> {
                rover.setDirection(rover.getTurnDirection(Relative.RIGHT));
                return true;
            }
            case MOVE -> {
                Coordinate newPos = rover.calculateNewPosition();
                if (surface.isValidCoordinate(newPos)) {
                    rover.setPosition(newPos);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false; // Command not recognised
    }

    public SequenceResult executeCommandSequence(Command[] commandSequence) {
        if (commandSequence == null) {
            throw new IllegalArgumentException("Command sequence cannot be null");
        }
        int step = 0;
        boolean successful;
        for (Command command :commandSequence) {
            step++;
            successful = executeRoverStep(command);
            if (!successful) {
                return new SequenceResult(false, String.format("Error in step %d: Rover went out of bounds from %s",
                        step, rover.getPosition()));
            }
        }
        Coordinate finalPosition = rover.getPosition();
        return new SequenceResult(true, String.format("%d %d %s",
                finalPosition.getX(), finalPosition.getY(), rover.getDirection()));
    }

    public void begin() {
        setSurface(makeSurface(handler.getMaxSurfaceSize()));

        RoverPosition roverPosition = handler.getRoverPosition();
        boolean exit = false;
        while (!exit) {
            if (surface.isValidCoordinate(roverPosition.getCoordinates())) {
                exit = true;
            } else {
                handler.out(String.format("Position must be within maximum bounds %s", surface.getMaxCoordinates()));
                roverPosition = handler.getRoverPosition();
            }
        }
        setRover(makeRover(roverPosition.getCoordinates(), roverPosition.getDirection()));

        RoverPosition initialPos = roverPosition;

        exit = false;
        Command[] commandSequence;
        while (!exit) {
            commandSequence = handler.getCommandSequence();
            SequenceResult result = executeCommandSequence(commandSequence);
            if (result.succeeded()) {
                handler.out(result.message());
                exit = true;
            } else {
                handler.out(result.message());
                rover.setPosition(initialPos.getCoordinates());
                rover.setDirection(initialPos.getDirection());
            }
        }
        handler.exit();
    }
}