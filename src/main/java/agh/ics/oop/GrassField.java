package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        Stream<Vector2d> vector2dStream = IntStream.rangeClosed(0, range)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(0, range)
                            .mapToObj(j -> new Vector2d(i, j))
        );

        ArrayList<Vector2d> freeSpaces = new ArrayList<>(vector2dStream.filter(pos -> this.mapElements.get(pos) == null).toList());
        Collections.shuffle(freeSpaces);
        freeSpaces.stream().limit(grassNum).forEach(pos -> this.mapElements.put(pos, new Grass(pos)));
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