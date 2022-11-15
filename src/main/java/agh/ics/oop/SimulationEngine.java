package agh.ics.oop;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.map = map;
        this.directions = directions;
        placeAnimals(map, initialPositions);
    }

    private static void placeAnimals(IWorldMap map, Vector2d[] initialPositions) {
        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(map, initialPosition);
            map.place(animal);
        }
    }

    @Override
    public void run() {
        int index = 0;
        for (MoveDirection direction : this.directions) {
            map.getAnimal(index).move(direction);
            index = (index + 1) % this.map.numOfAnimals();
        }
    }
}
