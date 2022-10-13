package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {
    @Test
    void next() {
        MapDirection direction = MapDirection.NORTH;
        direction = direction.next();
        assertEquals(direction, MapDirection.EAST);
        direction = direction.next();
        assertEquals(direction, MapDirection.SOUTH);
        direction = direction.next();
        assertEquals(direction, MapDirection.WEST);
        direction = direction.next();
        assertEquals(direction, MapDirection.NORTH);
    }

    @Test
    void previous() {
        MapDirection direction = MapDirection.NORTH;
        direction = direction.previous();
        assertEquals(direction, MapDirection.WEST);
        direction = direction.previous();
        assertEquals(direction, MapDirection.SOUTH);
        direction = direction.previous();
        assertEquals(direction, MapDirection.EAST);
        direction = direction.previous();
        assertEquals(direction, MapDirection.NORTH);
    }

    @Test
    void toUnitVector() {
        MapDirection direction = MapDirection.NORTH;
        assertEquals(direction.toUnitVector(), new Vector2d(0,1));
        direction = direction.next();
        assertEquals(direction.toUnitVector(), new Vector2d(1,0));
        direction = direction.next();
        assertEquals(direction.toUnitVector(), new Vector2d(0,-1));
        direction = direction.next();
        assertEquals(direction.toUnitVector(), new Vector2d(-1,0));
    }

}
