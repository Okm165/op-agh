package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{

    private final int grassnum;

    public GrassField(int grassNum) {
        this.grassnum = grassNum;
        this.randomizeGrass();
        this.rect = new BoundingRect(new Vector2d(0,0), new Vector2d(0,0));
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    private void randomizeGrass() {
        int range = (int) Math.round(Math.sqrt(this.grassnum *10));
        int cnt = this.grassnum;
        while (cnt > 0) {
            int rx = getRandomNumber(range);
            int ry = getRandomNumber(range);
            Vector2d pos = new Vector2d(rx, ry);
            if (!isOccupied(pos)) {
                this.mapElements.put(pos, new Grass(pos));
                cnt -= 1;
            }
        }
    }

    private int getRandomNumber(int range) {
        return (int) Math.round(((Math.random() * range)));
    }

    public BoundingRect boundingRect() {
        rect.lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        rect.upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.mapElements.forEach((pos, elem) -> {
            rect.lowerLeftCorner = rect.lowerLeftCorner.lowerLeft(pos);
            rect.upperRightCorner = rect.upperRightCorner.upperRight(pos);
        });
        return rect;
    }
}