package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.mapBoundary.place(new Vector2d(0,0));
        this.mapBoundary.place(new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(@NotNull Vector2d position) {
        if (!super.canMoveTo(position)) return false;
        return position.follows(this.mapBoundary.lowerLeft())
                && position.precedes(this.mapBoundary.upperRight());
    }
}
