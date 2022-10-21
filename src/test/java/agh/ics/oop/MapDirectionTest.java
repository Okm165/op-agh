package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {
    @Test
    void next() {
        // given
        MapDirection direction = MapDirection.NORTH;
        // when
        MapDirection direction_n = direction.next();
        MapDirection direction_nn = direction_n.next();
        MapDirection direction_nnn = direction_nn.next();
        MapDirection direction_nnnn = direction_nnn.next();
        // then
        assertEquals(MapDirection.EAST, direction_n);
        assertEquals(MapDirection.SOUTH, direction_nn);
        ;
        assertEquals(MapDirection.WEST, direction_nnn);
        assertEquals(MapDirection.NORTH, direction_nnnn);
    }

    @Test
    void previous() {
        // given
        MapDirection direction = MapDirection.NORTH;
        // when
        MapDirection direction_p = direction.previous();
        MapDirection direction_pp = direction_p.previous();
        MapDirection direction_ppp = direction_pp.previous();
        MapDirection direction_pppp = direction_ppp.previous();
        // then
        assertEquals(MapDirection.WEST, direction_p);
        assertEquals(MapDirection.SOUTH, direction_pp);
        assertEquals(MapDirection.EAST, direction_ppp);
        assertEquals(MapDirection.NORTH, direction_pppp);
    }

    @Test
    void toUnitVector() {
        // given
        MapDirection direction_n = MapDirection.NORTH;
        MapDirection direction_e = MapDirection.EAST;
        MapDirection direction_s = MapDirection.SOUTH;
        MapDirection direction_w = MapDirection.WEST;
        // when
        Vector2d direction_n_unit = direction_n.toUnitVector();
        Vector2d direction_e_unit = direction_e.toUnitVector();
        Vector2d direction_s_unit = direction_s.toUnitVector();
        Vector2d direction_w_unit = direction_w.toUnitVector();
        // then
        assertEquals(new Vector2d(0, 1), direction_n_unit);
        assertEquals(new Vector2d(1, 0), direction_e_unit);
        assertEquals(new Vector2d(0, -1), direction_s_unit);
        assertEquals(new Vector2d(-1, 0), direction_w_unit);
    }

}
