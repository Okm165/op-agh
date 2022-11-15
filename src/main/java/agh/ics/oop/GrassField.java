package agh.ics.oop;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{

    private final int grassnum;
    public final ArrayList<Grass> grass = new ArrayList<>();
    public BoundingRect rect;

    public GrassField(int grassNum) {
        this.grassnum = grassNum;
        this.randomizeGrass();
        this.rect = new BoundingRect(new Vector2d(0,0), new Vector2d(0,0));
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public Object objectAt(Vector2d position) {
        return Stream.concat(this.animals.stream(), this.grass.stream())
                .filter(element -> element.isAt(position))
                .findFirst()
                .orElse(null);
    }

    private void randomizeGrass() {
        int range = (int) Math.round(Math.sqrt(this.grassnum *10));
        int cnt = this.grassnum;
        while (cnt > 0) {
            int rx = getRandomNumber(range);
            int ry = getRandomNumber(range);
            Vector2d pos = new Vector2d(rx, ry);
            if (!isOccupied(pos)) {
                cnt -= 1;
                this.grass.add(new Grass(pos));
            }
        }
    }

    private int getRandomNumber(int range) {
        return (int) Math.round(((Math.random() * range)));
    }

    public BoundingRect boundingRect() {
        rect.lowerLeftCorner = this.animals.get(0).getPosition();
        rect.upperRightCorner = this.animals.get(0).getPosition();
        for (Animal animal : this.animals) {
            rect.lowerLeftCorner = rect.lowerLeftCorner.lowerLeft(animal.getPosition());
            rect.upperRightCorner = rect.upperRightCorner.upperRight(animal.getPosition());
        }
        for (Grass grass : this.grass) {
            rect.lowerLeftCorner = rect.lowerLeftCorner.lowerLeft(grass.getPosition());
            rect.upperRightCorner = rect.upperRightCorner.upperRight(grass.getPosition());
        }
        return rect;
    }
}