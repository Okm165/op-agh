package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {

    protected final ArrayList<Animal> animals = new ArrayList<>();

    public BoundingRect rect;

    abstract public boolean canMoveTo(@NotNull Vector2d position);

    public boolean place(@NotNull Animal animal) {
        Vector2d pos = animal.getPosition();
        if (canMoveTo(pos)) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    abstract public Object objectAt(Vector2d position);

    public Animal getAnimal(int index) { return this.animals.get(index); };

    public int numOfAnimals() { return this.animals.size(); };

    abstract public BoundingRect boundingRect();

    public String toString() {
        this.rect = boundingRect();
        return new MapVisualizer(this).draw(rect.lowerLeftCorner, rect.upperRightCorner);
    }
}
