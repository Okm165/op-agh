package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    final private int width;
    final private int height;
    private final List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x <= this.width && position.x >= 0 && position.y <= this.height && position.y >= 0;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (canMoveTo(pos) && !isOccupied(pos)) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.stream().anyMatch(animal -> position.equals(animal.getPosition()));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.stream().filter(animal -> position.equals(animal.getPosition())).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(
                new Vector2d(0, 0),
                new Vector2d(this.width, this.height)
        );
    }

    @Override
    public Animal get(int index) {
        return this.animals.get(index);
    }

    @Override
    public int numOfAnimals() {
        return this.animals.size();
    }
}
