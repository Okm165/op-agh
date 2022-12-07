package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{
    public GrassField(int grassNum) {
        this.randomizeGrass(grassNum);
    }

    private void randomizeGrass(int grassNum) {
        int range = (int) Math.round(Math.sqrt(grassNum * 10));
        Stream<Vector2d> vector2dStream = IntStream.rangeClosed(0, range)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(0, range)
                            .mapToObj(j -> new Vector2d(i, j))
        );

        ArrayList<Vector2d> freeSpaces = new ArrayList<>(vector2dStream.filter(pos -> this.mapElements.get(pos) == null).toList());
        Collections.shuffle(freeSpaces);
        freeSpaces.stream().limit(grassNum).forEach(pos -> {
            this.mapElements.put(pos, new Grass(pos));
            this.mapBoundary.place(pos);
        });
    }
}