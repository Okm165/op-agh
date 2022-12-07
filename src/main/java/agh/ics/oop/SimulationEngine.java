package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final ArrayList<Animal> animalBuf = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.directions = directions;
        placeAnimals(map, initialPositions);
    }

    private void placeAnimals(IWorldMap map, Vector2d[] initialPositions) {
        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(map, initialPosition);
            animalBuf.add(animal);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        int index = 0;
        for (MoveDirection direction : this.directions) {
            animalBuf.get(index).move(direction);
            index = (index + 1) % this.animalBuf.size();
        }
    }
}
