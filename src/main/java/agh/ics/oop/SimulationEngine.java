package agh.ics.oop;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final AbstractWorldMap map;

    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] initialPositions) {
        this.map = map;
        this.directions = directions;
        placeAnimals(map, initialPositions);
    }

    private static void placeAnimals(AbstractWorldMap map, Vector2d[] initialPositions) {
        for (Vector2d initialPosition : initialPositions) {
            Animal animal = new Animal(map, initialPosition);
            animal.addObserver(map);
//            if (map instanceof GrassField) animal.addObserver(map.mapBoundary);
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
