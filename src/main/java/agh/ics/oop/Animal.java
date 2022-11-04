package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
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

    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    public void move(MoveDirection direction) {
        Vector2d new_pos = this.position;
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> new_pos = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> new_pos = this.position.subtract(this.orientation.toUnitVector());
        }
        if (this.map.canMoveTo(new_pos) && !this.map.isOccupied(new_pos)) {
            this.position = new_pos;
        }
    }
}
