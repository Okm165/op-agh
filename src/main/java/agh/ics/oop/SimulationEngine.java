package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {

    private final static int MOVEDELAY = 600;
    private MoveDirection[] directions;
    private final ArrayList<Animal> animalBuf = new ArrayList<>();

    public SimulationEngine(IWorldMap map, Animal[] animals) {
        for (Animal animal : animals) {
            animalBuf.add(animal);
            map.place(animal);
        }
    }
    public SimulationEngine(IWorldMap map, Animal[] animals, MoveDirection[] directions) {
        this(map, animals);
        this.directions = directions;
    }

    @Override
    public void run() {
        System.out.println("SimulationEngine started.");
        int index = 0;
        for (MoveDirection direction : this.directions) {
            try {
                Thread.sleep(MOVEDELAY);
            } catch (InterruptedException err) {
                System.out.println(err);
            }
            animalBuf.get(index).move(direction);
            index = (index + 1) % this.animalBuf.size();
        }
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }
}
