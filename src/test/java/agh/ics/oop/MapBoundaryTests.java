package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapBoundaryTests {
    @Test
    void xOrder(){
        // given
        MapBoundary boundary = new MapBoundary();
        boundary.place(new Vector2d(4,1));
        boundary.place(new Vector2d(3,1));
        boundary.place(new Vector2d(1,1));
        boundary.place(new Vector2d(2,1));
        // then
        assertEquals(
                new BoundingRect(new Vector2d(1,1), new Vector2d(4,1)),
                boundary.boundingRect()
        );
    }

    @Test
    void yOrder(){
        // given
        MapBoundary boundary = new MapBoundary();
        boundary.place(new Vector2d(1,3));
        boundary.place(new Vector2d(1,2));
        boundary.place(new Vector2d(1,1));
        boundary.place(new Vector2d(1,4));
        // then
        assertEquals(
                new BoundingRect(new Vector2d(1,1), new Vector2d(1,4)),
                boundary.boundingRect()
        );
    }

    @Test
    void xyOrder(){
        // given
        MapBoundary boundary = new MapBoundary();
        boundary.place(new Vector2d(7,3));
        boundary.place(new Vector2d(2,2));
        boundary.place(new Vector2d(1,9));
        boundary.place(new Vector2d(12,12));
        // then
        assertEquals(
                new BoundingRect(new Vector2d(1,2), new Vector2d(12,12)),
                boundary.boundingRect()
        );
    }

    @Test
    void positonChanged(){
        // given
        MapBoundary boundary = new MapBoundary();
        boundary.place(new Vector2d(7,3));
        boundary.place(new Vector2d(2,2));
        boundary.place(new Vector2d(1,9));
        boundary.place(new Vector2d(12,12));
        // when
        boundary.positionChanged(new Vector2d(1,9), new Vector2d(14,1));
        boundary.positionChanged(new Vector2d(7,3), new Vector2d(14,10));
        // then
        assertEquals(
                new BoundingRect(new Vector2d(2,1), new Vector2d(14,12)),
                boundary.boundingRect()
        );
    }
}
