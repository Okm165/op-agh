package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    @Test
    void movingTest() {
        String[] args = new String[] {"f", "right", "backward", "b", "b", "left", "forward", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        Animal animal = new Animal();

        assertArrayEquals(new MoveDirection[] {
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
        }, directions);

        for (MoveDirection move : directions){
            animal.move(move);
        }

        assertTrue(animal.isAt(new Vector2d(0,4)));
    }
}
