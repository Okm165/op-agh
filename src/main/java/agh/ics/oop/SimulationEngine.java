package agh.ics.oop;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initial_positions) {
        for (Vector2d initial_position : initial_positions) {
            Animal animal = new Animal(map, initial_position);
            map.place(animal);
        }
        this.map = map;
        this.directions = directions;
    }

    @Override
    public void run() {
        int index = 0;
        for (MoveDirection direction : this.directions) {
            map.get(index).move(direction);
            index = (index + 1) % this.map.numOfAnimals();
        }
    }
}
