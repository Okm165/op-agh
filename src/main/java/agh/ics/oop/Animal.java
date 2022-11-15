package agh.ics.oop;

import java.util.Objects;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "▲";
            case EAST -> "►";
            case SOUTH -> "▼";
            case WEST -> "◄";
        };
    }

    public void move(MoveDirection direction) {
        Vector2d newPos = this.position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> newPos = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newPos = this.position.subtract(this.orientation.toUnitVector());
        }
        if (this.map.canMoveTo(newPos)) {
            this.position = newPos;
        }
    }
}
