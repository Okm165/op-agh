package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTests {
    @Test
    void isOccupied() {
        // given
        GrassField map = new GrassField(10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        // then
        assertTrue(map.isOccupied(anim1_pos));
    }

    @Test
    void canMoveTo() {
        // given
        GrassField map = new GrassField(10);
        // then
        assertTrue(map.canMoveTo(new Vector2d(0,0)));
        assertTrue(map.canMoveTo(new Vector2d(100,100)));
        assertTrue(map.canMoveTo(new Vector2d(100,-100)));
        assertTrue(map.canMoveTo(new Vector2d(-100,100)));
        assertTrue(map.canMoveTo(new Vector2d(-100,-100)));
    }

    @Test
    void objectAt() {
        // given
        GrassField map = new GrassField(10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        // then
        assertTrue(map.objectAt(anim1_pos) instanceof Animal);
    }

    @Test
    void boundingRect() {
        // given
        GrassField map = new GrassField(10);
        Vector2d anim1_pos = new Vector2d(1,1);
        Animal anim1 = new Animal(map, anim1_pos);
        // when
        map.place(anim1);
        Vector2d lowerLeftCorner = map.animals.get(0).getPosition();
        Vector2d upperRightCorner = map.animals.get(0).getPosition();
        for (Vector2d pos : map.mapElements.keySet()) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(pos);
            upperRightCorner = upperRightCorner.upperRight(pos);
        }
        System.out.println(lowerLeftCorner);
        System.out.println(map.mapBoundary.lowerLeft());
        System.out.println(upperRightCorner);
        System.out.println(map.mapBoundary.upperRight());
        // then
        assertTrue(map.mapBoundary.lowerLeft().equals(lowerLeftCorner));
        assertTrue(map.mapBoundary.upperRight().equals(upperRightCorner));
    }

    @Test
    void animalPlacement() {
        // given
        GrassField map = new GrassField(10);
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
