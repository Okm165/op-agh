package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    public final int WIDTH;
    public final int HEIGHT;
    public final Vector2d ORIGIN;
    public final Vector2d BOUNDARY;
    private final ArrayList<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ORIGIN = new Vector2d(0,0);
        this.BOUNDARY = new Vector2d(this.WIDTH,this.HEIGHT);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.ORIGIN) && position.precedes(this.BOUNDARY);
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
        return new MapVisualizer(this).draw(this.ORIGIN, this.BOUNDARY);
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
