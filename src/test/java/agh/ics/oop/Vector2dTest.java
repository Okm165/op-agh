package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void convertToString() {
        // given
        Vector2d v1 = new Vector2d(10, 12);
        Vector2d v2 = new Vector2d(-110, 24);
        // when
        String v1_str = v1.toString();
        String v2_str = v2.toString();
        // then
        assertEquals("(10,12)", v1_str);
        assertEquals("(-110,24)", v2_str);
    }

    @Test
    void equals() {
        // given
        Vector2d vec0 = new Vector2d(10, 12);
        // then
        assertEquals(vec0, new Vector2d(10, 12));
        assertNotEquals(vec0, new Vector2d(-10, 12));
        assertNotEquals(vec0, new Vector2d(10, 11));
        assertNotEquals(vec0, new Vector2d(9, 11));
    }

    @Test
    void precedes() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        // then
        assertFalse(vec1.precedes(new Vector2d(13, 9)));
        assertFalse(vec1.precedes(new Vector2d(10, 12)));
        assertTrue(vec1.precedes(new Vector2d(10, 17)));
        assertFalse(vec1.precedes(new Vector2d(1, 12)));
        assertFalse(vec1.precedes(new Vector2d(11, 12)));
        assertTrue(vec1.precedes(new Vector2d(11, 18)));
    }

    @Test
    void follows() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        // then
        assertFalse(vec1.follows(new Vector2d(13, 9)));
        assertTrue(vec1.follows(new Vector2d(10, 12)));
        assertTrue(vec1.follows(new Vector2d(10, 17)));
        assertTrue(vec1.follows(new Vector2d(1, 12)));
        assertFalse(vec1.follows(new Vector2d(11, 12)));
        assertFalse(vec1.follows(new Vector2d(11, 18)));
    }

    @Test
    void add() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        Vector2d vec3 = new Vector2d(-11, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        // when
        Vector2d vec1_add_vec2 = vec1.add(vec2);
        Vector2d vec3_add_vec4 = vec3.add(vec4);
        // then
        assertEquals(vec1_add_vec2, new Vector2d(23, 26));
        assertEquals(vec3_add_vec4, new Vector2d(-1, 99));
    }

    @Test
    void subtract() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        Vector2d vec3 = new Vector2d(-11, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        // when
        Vector2d vec1_sub_vec2 = vec1.subtract(vec2);
        Vector2d vec3_sub_vec4 = vec3.subtract(vec4);
        // then
        assertEquals(vec1_sub_vec2, new Vector2d(-3, 8));
        assertEquals(vec3_sub_vec4, new Vector2d(-21, 65));
    }

    @Test
    void upperRight() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        Vector2d vec3 = new Vector2d(110, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        // when
        Vector2d vec1_vec2_ur = vec1.upperRight(vec2);
        Vector2d vec3_vec4_ur = vec3.upperRight(vec4);
        // then
        assertEquals(vec1_vec2_ur, new Vector2d(13, 17));
        assertEquals(vec3_vec4_ur, new Vector2d(110, 82));
    }

    @Test
    void lowerLeft() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(13, 9);
        Vector2d vec3 = new Vector2d(110, 82);
        Vector2d vec4 = new Vector2d(10, 17);
        // when
        Vector2d vec1_vec2_ll = vec1.lowerLeft(vec2);
        Vector2d vec3_vec4_ll = vec3.lowerLeft(vec4);
        // then
        assertEquals(vec1_vec2_ll, new Vector2d(10, 9));
        assertEquals(vec3_vec4_ll, new Vector2d(10, 17));
    }

    @Test
    void opposite() {
        // given
        Vector2d vec1 = new Vector2d(10, 17);
        Vector2d vec2 = new Vector2d(-110, 82);
        // when
        Vector2d vec1_op = vec1.opposite();
        Vector2d vec2_op = vec2.opposite();
        // then
        assertEquals(vec1_op, new Vector2d(-10, -17));
        assertEquals(vec2_op, new Vector2d(110, -82));
    }
}
