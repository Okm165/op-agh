package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final ArrayList<Animal> animals = new ArrayList<>();
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();

    public boolean place(@NotNull Animal animal) {
        Vector2d pos = animal.getPosition();
        if (canMoveTo(pos)) {
            this.animals.add(animal);
            this.mapElements.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public Animal getAnimal(int index) { return this.animals.get(index); }

    public int numOfAnimals() { return this.animals.size(); }

    abstract public BoundingRect boundingRect();

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (!isOccupied(oldPosition) || !canMoveTo(newPosition)) return;
        IMapElement element = this.mapElements.remove(oldPosition);
        this.mapElements.put(newPosition, element);
    }

    public String toString() {
        BoundingRect rect = boundingRect();
        return new MapVisualizer(this).draw(rect.lowerLeft(), rect.upperRight());
    }
}
