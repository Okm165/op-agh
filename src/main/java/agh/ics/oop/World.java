package agh.ics.oop;
import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        Direction[] list = Arrays.stream(args).map(
                (arg) -> switch (arg) {
                    case "f" -> Direction.FORWARD;
                    case "b" -> Direction.BACKWARD;
                    case "r" -> Direction.RIGHT;
                    case "l" -> Direction.LEFT;
                    default -> null;
                }
        ).toArray(size -> new Direction[size]);
        run(list);
        System.out.println("Stop");
    }

    public static void run(Direction[] directions) {
        for (Direction direction : directions) {
            String message = switch (direction) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
            };
            System.out.println(message);
        }
    }
}
