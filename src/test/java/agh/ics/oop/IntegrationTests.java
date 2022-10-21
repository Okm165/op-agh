package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    @Test
    void movingSingleAnimalScenario1() {
        // given
        String[] args = {"f", "right", "backward", "b", "b", "left", "forward", "f", "f"};
        IWorldMap map = new RectangularMap(4, 4);
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        Animal animal = new Animal(map);
        for (MoveDirection move : directions) {
            animal.move(move);
        }
        // then
        assertArrayEquals(new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,}, directions);
        assertTrue(animal.isAt(new Vector2d(0, 4)));
        assertEquals("▲", animal.toString());
    }

    @Test
    void movingSingleAnimalScenario2() {
        // given
        String[] args = {"r", "f", "f", "f"};
        IWorldMap map = new RectangularMap(4, 4);
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        Animal animal = new Animal(map);
        for (MoveDirection move : directions) {
            animal.move(move);
        }
        // then
        assertArrayEquals(new MoveDirection[]{MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,}, directions);
        assertTrue(animal.isAt(new Vector2d(4, 2)));
        assertEquals("►", animal.toString());
    }

    @Test
    void movingTwoAnimalsScenario1() {
        // given
        String[] args = {"f", "b", "r", "l"};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        // then
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void movingTwoAnimalsScenario2() {
        // given
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        // then
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 5)));
    }
}
