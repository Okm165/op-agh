package agh.ics.oop;

import java.util.Objects;

public record Grass(Vector2d position) implements IMapElement {

    @Override
    public boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String toResource() {
        return "src/main/resources/grass.png";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.position);
    }
}
