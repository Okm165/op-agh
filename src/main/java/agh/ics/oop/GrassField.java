package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;

public class GrassField extends AbstractWorldMap{

    public GrassField(int grassNum) {
        this.randomizeGrass(grassNum);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    private void randomizeGrass(int grassNum) {
        int range = (int) Math.round(Math.sqrt(grassNum * 10));
        Vector2d topRight = new Vector2d(range, range);
        ArrayList<Vector2d> freeSpaces = new ArrayList<>(this.mapElements.keySet().stream().filter(pos -> pos.precedes(topRight) && this.mapElements.get(pos) == null).toList());
        Collections.shuffle(freeSpaces);
        freeSpaces.stream().limit(grassNum).forEach(pos -> this.mapElements.put(pos, new Grass(pos)));
//        int cnt = grassNum;
//        while (cnt > 0) {
//            int rx = getRandomNumber(range);
//            int ry = getRandomNumber(range);
//            Vector2d pos = new Vector2d(rx, ry);
//            if (!isOccupied(pos)) {
//                this.mapElements.put(pos, new Grass(pos));
//                cnt -= 1;
//            }
//        }
    }

    public BoundingRect boundingRect() {
        Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Vector2d pos : this.mapElements.keySet()) {
            lowerLeftCorner = lowerLeftCorner.lowerLeft(pos);
            upperRightCorner = upperRightCorner.upperRight(pos);
        }

        return new BoundingRect(lowerLeftCorner, upperRightCorner);
    }
}