package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        return Arrays.stream(args).map(arg -> switch (arg) {
                    case "f", "forward" -> MoveDirection.FORWARD;
                    case "b", "backward" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l", "left" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                }
        ).toArray(MoveDirection[]::new);
    }
}
