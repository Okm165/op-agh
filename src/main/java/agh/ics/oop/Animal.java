package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;
    private final Vector2d upperRightBoundary = new Vector2d(4,4);
    private final Vector2d lowerLeftBoundary = new Vector2d(0,0);

    @Override
    public String toString() {
        return "pos: %s, orient: %s".formatted(position, orientation);
    }

    public boolean isAt(Vector2d position) {
        return this.position.x == position.x && this.position.y == position.y;
    }

    public void move (MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> this.position =  this.position.add(this.orientation.toUnitVector())
                    .lowerLeft(this.upperRightBoundary)
                    .upperRight(this.lowerLeftBoundary);
            case BACKWARD -> this.position =  this.position.subtract(this.orientation.toUnitVector())
                    .lowerLeft(this.upperRightBoundary)
                    .upperRight(this.lowerLeftBoundary);
        }
    }
}
