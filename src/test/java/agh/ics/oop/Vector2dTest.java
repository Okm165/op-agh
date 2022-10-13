package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void convertToString() {
        assertEquals("(10,12)", new Vector2d(10,12).toString());
        assertEquals("(-110,24)", new Vector2d(-110,24).toString());
    }

    @Test
    void equals() {
        Vector2d vec0 = new Vector2d(10,12);
        assertTrue(vec0.equals(new Vector2d(10,12)));
        assertFalse(vec0.equals(new Vector2d(-10,12)));
        assertFalse(vec0.equals(new Vector2d(10,11)));
        assertFalse(vec0.equals(new Vector2d(9,11)));
    }

    @Test
    void precedes () {
        Vector2d vec1 = new Vector2d(10, 17);
        assertFalse(vec1.precedes(new Vector2d(13, 9)));
        assertFalse(vec1.precedes(new Vector2d(10, 12)));
        assertTrue(vec1.precedes(new Vector2d(10, 17)));
        assertFalse(vec1.precedes(new Vector2d(1, 12)));
        assertFalse(vec1.precedes(new Vector2d(11, 12)));
        assertTrue(vec1.precedes(new Vector2d(11, 18)));
    }

    @Test
    void follows () {
        Vector2d vec1 = new Vector2d(10, 17);
        assertFalse(vec1.follows(new Vector2d(13, 9)));
        assertTrue(vec1.follows(new Vector2d(10, 12)));
        assertTrue(vec1.follows(new Vector2d(10, 17)));
        assertTrue(vec1.follows(new Vector2d(1, 12)));
        assertFalse(vec1.follows(new Vector2d(11, 12)));
        assertFalse(vec1.follows(new Vector2d(11, 18)));
    }

    @Test
    void add () {
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        assertTrue(vec1.add(vec2).equals(new Vector2d(23,26)));

        Vector2d vec3 = new Vector2d(-11, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        assertTrue(vec3.add(vec4).equals(new Vector2d(-1,99)));
    }

    @Test
    void subtract () {
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        assertTrue(vec1.subtract(vec2).equals(new Vector2d(-3, 8)));

        Vector2d vec3 = new Vector2d(-11, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        assertTrue(vec3.subtract(vec4).equals(new Vector2d(-21, 65)));
    }

    @Test
    void upperRight () {
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        assertTrue(vec1.upperRight(vec2).equals(new Vector2d(13, 17)));

        Vector2d vec3 = new Vector2d(110, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        assertTrue(vec3.upperRight(vec4).equals(new Vector2d(110, 82)));
    }

    @Test
    void lowerLeft () {
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        assertTrue(vec1.lowerLeft(vec2).equals(new Vector2d(10,9)));

        Vector2d vec3 = new Vector2d(110, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        assertTrue(vec3.lowerLeft(vec4).equals(new Vector2d(10,17)));
    }

    @Test
    void opposite () {
        assertTrue(new Vector2d(10, 17).opposite().equals(new Vector2d(-10, -17)));
        assertTrue(new Vector2d(-110, 82).opposite().equals(new Vector2d(110, -82)));
    }
}
