package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final ArrayList<Animal> animals = new ArrayList<>();
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected final MapBoundary mapBoundary = new MapBoundary();

    public void place(@NotNull Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!canMoveTo(pos)) throw new IllegalArgumentException("Placing failed at: "+pos+" space already occupied!");
        this.animals.add(animal);
        this.mapElements.put(pos, animal);
        this.mapBoundary.place(pos);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public Animal getAnimal(int index) { return this.animals.get(index); }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public int numOfAnimals() { return this.animals.size(); }

    public BoundingRect boundingRect() {
        return mapBoundary.boundingRect();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (!isOccupied(oldPosition) || !canMoveTo(newPosition)) return;
        this.mapElements.put(newPosition, this.mapElements.remove(oldPosition));
        this.mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.mapBoundary.lowerLeft(), this.mapBoundary.upperRight());
    }

    public Stream<IMapElement> mapElements(){
        return this.mapElements.values().stream();
    }
}
