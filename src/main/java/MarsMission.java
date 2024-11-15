import spatial.*;

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
            return new Surface(max);
        }
    }

    public Rover makeRover(Coordinate position, Cardinal direction) {
        if (position == null) {
            throw new IllegalArgumentException("Rover position cannot be null");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Rover direction cannot be null");
        }

        return new Rover(position, direction);
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
        boolean errorOccurred = false;
        Command[] commandSequence;
        while(!exit) {
            if (errorOccurred) {
                rover.setPosition(initialPos.getCoordinates());
                rover.setDirection(initialPos.getDirection());
            }
            errorOccurred = false;
            int step = 0;
            handler.promptCommandSequence();
            commandSequence = handler.getCommandSequence();
            handler.printStep(step, rover);
            for (Command command: commandSequence) {
                step++;
                switch (command) {
                    case LEFT -> rover.setDirection(rover.getTurnDirection(Relative.LEFT));
                    case RIGHT -> rover.setDirection(rover.getTurnDirection(Relative.RIGHT));
                    case MOVE -> {
                        Coordinate oldPos = rover.getPosition();
                        Coordinate newPos = rover.calculateNewPosition();
                        if (surface.isValidCoordinate(newPos)) {
                            rover.setPosition(newPos);
                        } else {
                            handler.out(String.format("Error in step %d: Rover went out of bounds from %s to %s",
                                    step, oldPos, newPos));
                            errorOccurred = true;
                        }
                    }
                }
                handler.printStep(step, rover);
                if (errorOccurred) {
                    break;
                }
            }
            if (!errorOccurred) {
                exit = true;
            }
        }
    }
}