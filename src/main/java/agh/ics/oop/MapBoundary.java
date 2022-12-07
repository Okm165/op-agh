package agh.ics.oop;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Integer.signum;

public class MapBoundary implements IPositionChangeObserver{

    private final SortedSet<Integer> x = new TreeSet<>();
    private final SortedSet<Integer> y = new TreeSet<>();

    public void place(Vector2d pos) {
        x.add(pos.x);
        y.add(pos.y);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        x.remove(oldPosition.x);
        y.remove(oldPosition.y);
        x.add(newPosition.x);
        y.add(newPosition.y);
    }
    public Vector2d lowerLeft() {
        return new Vector2d(x.first(), y.first());
    }
    public  Vector2d upperRight() {
        return new Vector2d(x.last(), y.last());
    }
    public BoundingRect boundingRect() {
        return new BoundingRect(this.lowerLeft(), this.upperRight());
    }
}
