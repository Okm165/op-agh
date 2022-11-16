package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTests {
    @Test
    void parseShort() {
        // given
        String[] args = {"f", "r", "b", "b", "b", "l", "f", "f", "f"};
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        // then
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
    }

    @Test
    void parseLong() {
        // given
        String[] args = {"forward", "right", "backward", "backward", "backward", "left", "forward", "forward", "forward"};
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        // then
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
    }

    @Test
    void parseMixed() {
        // given
        String[] args = {"f", "right", "backward", "b", "b", "left", "forward", "f", "f"};
        // when
        MoveDirection[] directions = OptionsParser.parse(args);
        // then
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
    }
}
