package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected final MapBoundary mapBoundary = new MapBoundary();

    public void place(@NotNull Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!canMoveTo(pos)) throw new IllegalArgumentException("Placing failed at: "+pos+" space already occupied!");
        this.mapElements.put(pos, animal);
        this.mapBoundary.place(pos);
        animal.addObserver(this);
        animal.addObserver(mapBoundary);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public BoundingRect boundingRect() {
        return mapBoundary.boundingRect();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (!isOccupied(oldPosition) || !canMoveTo(newPosition)) return;
        this.mapElements.put(newPosition, this.mapElements.remove(oldPosition));
    }

    public String toString() {
        return new MapVisualizer(this).draw(this.mapBoundary.lowerLeft(), this.mapBoundary.upperRight());
    }

    public Stream<IMapElement> mapElements(){
        return this.mapElements.values().stream();
    }
}
