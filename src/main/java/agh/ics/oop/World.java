package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 2)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();

            System.out.println(map);
        } catch(IllegalArgumentException err) {
            System.out.println(err);
        }
    }
}
