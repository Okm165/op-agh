package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public class RectangularMap extends AbstractWorldMap {
    public final BoundingRect rect;

    public RectangularMap(int width, int height) {
        this.rect = new BoundingRect(new Vector2d(0,0), new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(@NotNull Vector2d position) {
        return position.follows(this.rect.lowerLeftCorner)
                && position.precedes(this.rect.upperRightCorner)
                && !(objectAt(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.stream()
                .filter(animal -> position.equals(animal.getPosition()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BoundingRect boundingRect() {
        return this.rect;
    }
}
