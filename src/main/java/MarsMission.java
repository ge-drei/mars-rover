import directions.*;

public class MarsMission {
    private Surface surface;
    private Rover rover;
    private Rover.RoverPosition initialPos;
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

    public void setSurface(Surface surface) {this.surface = surface;}
    public void setRover(Rover rover) {this.rover = rover;}

    public void turnRover(Relative relative) {
        rover.setDirection(rover.getTurnDirection(relative));
    }

    public boolean isRoverStepValid() {
        Coordinate destination = rover.calculateNewPosition();
        return surface.isValidCoordinate(destination);
    }

    public void begin() {
        boolean exit = false;
        Coordinate maxSurfaceSize = null;
        handler.promptMaxSurfaceSize();
        while (!exit) {
            try {
                maxSurfaceSize = handler.getMaxSurfaceSize();
                exit = true;
            } catch (IllegalArgumentException e) {
                handler.promptMaxSurfaceSize();
            }
        }
        setSurface(makeSurface(maxSurfaceSize));

        exit = false;
        Rover.RoverPosition roverPosition = null;
        handler.promptRoverPosition();
        while (!exit) {
            try {
                roverPosition = handler.getRoverPosition();
            } catch (IllegalArgumentException e) {
                handler.promptRoverPosition();
            }
            if (surface.isValidCoordinate(roverPosition.coordinates())) {
                exit = true;
            } else {
                handler.out(String.format("Position must be within maximum bounds %s", surface.getMaxCoordinates()));
                handler.promptRoverPosition();
            }
        }
        setRover(makeRover(roverPosition.coordinates(), roverPosition.direction()));
        initialPos = roverPosition;

        exit = false;
        String commandSequence = "";
        while(!exit) {
            boolean willBreak = false;
            handler.promptCommandSequence();
            try {
                commandSequence = handler.getCommandSequence();

                int step = 1;
                for (char c: commandSequence.toCharArray()) {
                    Command command = interpretCommand(c);
                    switch (command) {
                        case LEFT -> rover.setDirection(rover.getTurnDirection(Relative.LEFT));
                        case RIGHT -> rover.setDirection(rover.getTurnDirection(Relative.RIGHT));
                        case MOVE -> {
                            Coordinate oldPos = rover.getPosition();
                            Coordinate newPos = rover.calculateNewPosition();
                            if (surface.isValidCoordinate(newPos)) {
                                rover.setPosition(newPos);
                                handler.out(newPos.toString());
                            } else {
                                handler.out(String.format("Error in step %d: Rover went out of bounds from %s to %s", step, oldPos, newPos));
                                willBreak = true;
                            }
                        }
                    }
                    step++;
                    if (willBreak) {
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                // Loops to command prompt above try block
            }
            if (!willBreak) {
                exit = true;
            }
        }
    }
}