package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {

    @Test
        //  animals leaving map is forbidden
    void animalsKeptInMapBoundaries() {
        // given
        String[] args = {"l", "b", "f", "b", "f", "b", "f", "b"};
        AbstractWorldMap map = new RectangularMap(4, 4);
        Vector2d[] initial_positions = {new Vector2d(0, 0), new Vector2d(1, 1)};
        Animal[] animals = Arrays.stream(initial_positions).map(pos -> new Animal(map, pos)).toArray(Animal[]::new);
        // when
        SimulationEngine engine = new SimulationEngine(map, animals, OptionsParser.parse(args));
        engine.run();
        // then
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertTrue(map.isOccupied(new Vector2d(1, 0)));
    }

    @Test
        //  only one animal can occupy given cell
    void animalsOccupationExclusion() {
        // given
        String[] args = {"f", "l", "r", "l", "f", "l", "f", "l"};
        AbstractWorldMap map = new RectangularMap(4, 4);
        Vector2d[] initial_positions = {new Vector2d(0, 0), new Vector2d(1, 1)};
        Animal[] animals = Arrays.stream(initial_positions).map(pos -> new Animal(map, pos)).toArray(Animal[]::new);
        // when
        SimulationEngine engine = new SimulationEngine(map, animals, OptionsParser.parse(args));
        engine.run();
        // then
        assertTrue(map.isOccupied(new Vector2d(0, 1)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
    }
}
