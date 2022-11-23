package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTests {

    @Test
    void isOccupied() {
        // given
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        // then
        assertTrue(map.isOccupied(anim1_pos));
        assertFalse(map.isOccupied(new Vector2d(0,0)));
    }

    @Test
    void canMoveTo() {
        // given
        RectangularMap map = new RectangularMap(10, 10);
        // then
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(0,5)));
        assertTrue(map.canMoveTo(new Vector2d(5,0)));
        assertTrue(map.canMoveTo(new Vector2d(10,10)));
        assertFalse(map.canMoveTo(new Vector2d(0,11)));
        assertFalse(map.canMoveTo(new Vector2d(11,0)));
        assertFalse(map.canMoveTo(new Vector2d(11,11)));
    }

    @Test
    void objectAt() {
        // given
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        // then
        assertTrue(map.objectAt(anim1_pos) instanceof Animal);
        assertNull(map.objectAt(new Vector2d(1,2)));
    }

    @Test
    void boundingRect() {
        // given
        RectangularMap map = new RectangularMap(10, 11);
        Vector2d origin = new Vector2d(0,0);
        Vector2d boundary = new Vector2d(10,11);
        // then
        assertEquals(map.rect.lowerLeftCorner, origin);
        assertEquals(map.rect.upperRightCorner, boundary);
    }

    @Test
    void animalPlacement() {
        // given
        RectangularMap map = new RectangularMap(10, 10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        // then
        assertTrue(map.isOccupied(anim1_pos));
        assertFalse(map.canMoveTo(anim1_pos));
        assertTrue(map.objectAt(anim1_pos) instanceof Animal);
    }
}
