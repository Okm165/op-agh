package agh.ics.oop;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Animal implements IMapElement {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    // using set for efficient removal of observers
    private final SortedSet<IPositionChangeObserver> observerSet = new TreeSet<>();

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    @Override
    public Vector2d position() {
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

    @Override
    public String toResource() {
        return "src/main/resources/" + switch (this.orientation) {
            case NORTH -> "up.png";
            case EAST -> "right.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
        };
    }

    public void move(MoveDirection direction) {
        Vector2d newPos = this.position;
        MapDirection newOrientation = this.orientation;
        switch (direction) {
            case RIGHT -> newOrientation = this.orientation.next();
            case LEFT -> newOrientation = this.orientation.previous();
            case FORWARD -> newPos = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newPos = this.position.subtract(this.orientation.toUnitVector());
        }
        if (this.map.canMoveTo(newPos)) {
            this.positionChanged(this.position, newPos);
            this.position = newPos;
        }
        if (!this.orientation.equals(newOrientation)) {
            this.orientationChanged(this.orientation, newOrientation);
            this.orientation = newOrientation;
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observerSet.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        this.observerSet.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.observerSet.forEach(obs -> obs.positionChanged(oldPosition, newPosition));
    }
    private void orientationChanged(MapDirection oldOrientation, MapDirection newOrientation){
        this.observerSet.forEach(obs -> obs.orientationChanged(oldOrientation, newOrientation));
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.position, this.orientation);
    }
}
