package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public class RectangularMap extends AbstractWorldMap {
    private final BoundingRect rect;
    public RectangularMap(int width, int height) {
        this.rect = new BoundingRect(new Vector2d(0,0), new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(@NotNull Vector2d position) {
        return position.follows(this.rect.lowerLeft())
                && position.precedes(this.rect.upperRight())
                && !(objectAt(position) instanceof Animal);
    }

    @Override
    public BoundingRect boundingRect() {
        return this.rect;
    }
}
